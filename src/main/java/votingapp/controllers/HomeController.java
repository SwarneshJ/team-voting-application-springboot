package votingapp.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import votingapp.models.ResponseClass;
import votingapp.models.Email;
import votingapp.models.TeamMember;
import votingapp.repositories.EmailRepository;
import votingapp.repositories.TeamMemberRepository;
import votingapp.services.DataService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private TeamMemberRepository teamMemberRepository;
    @Autowired
    private EmailRepository emailRepository;

    @GetMapping("/")
    public String home(Model model) {
        System.out.println("---- Loading index page ----");
        model.addAttribute("teamMembers", DataService.teamMembers);
        for (TeamMember teamMember : DataService.teamMembers) {
            // search the table whether this a team member with this name already exists
            TeamMember existingMember = teamMemberRepository.findByName(teamMember.getName());
            // If team member already exists then reflect it's current vote count else add a new team member
            try {
                teamMember.setVotes(existingMember.getVotes());
            }
            catch (Exception e) {
                teamMemberRepository.save(teamMember);
            }
        }

        return "index";
    }

    @ResponseBody
    @GetMapping(path="/initiate_vote/{id}/{email}")
    public String initiateVote(@PathVariable int id, @PathVariable String email) {
        System.out.println("Email "+email+" attempting to vote for id "+id);

        Optional existingEmail = emailRepository.findById(email);
        ResponseClass res = new ResponseClass();
        try {
            existingEmail.get();
            System.out.println("You have already voted!");
            res.setStatusCode(400);
            res.setMessage("ERROR");
        }
        catch (NoSuchElementException e) {
            List<TeamMember> teamMembers = teamMemberRepository.findByMemberId(id);
            TeamMember teamMember = teamMembers.get(0);
            System.out.println(teamMember);
            int newVotes = teamMember.getVotes()+1;
            teamMember.setVotes(newVotes);
            emailRepository.save(new Email(email));
            teamMemberRepository.save(teamMember);
            res.setStatusCode(200);
            res.setMessage(Integer.toString(newVotes));
        }
        Gson gson = new Gson();
        return gson.toJson(res);
    }

}
