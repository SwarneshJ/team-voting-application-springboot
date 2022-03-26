package votingapp.services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import votingapp.models.TeamMember;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {

    private static String ENDPOINT_URL = "https://coding-assignment.g2crowd.com";
    public static List<TeamMember> teamMembers = new ArrayList<>();

    @PostConstruct
    public void fetchData() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ENDPOINT_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        StringReader in = new StringReader(response.body());
        JSONArray jsonArray = new JSONArray(response.body());

        List<TeamMember> newList = new ArrayList<> ();

        int votes = 0;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            TeamMember teamMember = new TeamMember();
            teamMember.setMemberId(i);
            teamMember.setName((String) obj.get("name"));
            teamMember.setImageUrl((String) obj.get("image_url"));
            teamMember.setTitle((String) obj.get("title"));
            teamMember.setBio((String) obj.get("bio"));
            teamMember.setVotes(votes);
            newList.add(teamMember);
        }
        this.teamMembers = newList;
    }
}
