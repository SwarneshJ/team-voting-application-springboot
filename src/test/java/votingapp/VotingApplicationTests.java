package votingapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import votingapp.services.DataService;

import java.io.IOException;

@SpringBootTest
class VotingApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void fetchDataTest() throws IOException, InterruptedException {
		DataService ds = new DataService();
		ds.fetchData();
	}

}
