package pro.tech_rdham.Job.Recommender.App;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.bson.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testGetUser() {
        User user = userService.getUser("hello@gmail.com");
        System.out.println("Retrieved User: " + user);
        assertEquals("hello@gmail.com", user.getEmail());
    }

    @Test
    public void testMergeSort(){
        List<Job> sortedJobs = new ArrayList<>();

        sortedJobs.add(new Job(101, "Software Engineer", "TechCorp", "Develop software", "San Francisco, CA", "https://techcorp.com/jobs/101", "2025-01-08", 120000, 150000));
        sortedJobs.add(new Job(102, "Software Engineer", "TechCorp", "Develop software", "San Francisco, CA", "https://techcorp.com/jobs/101", "2025-01-08", 120000, 150000));
        sortedJobs.add(new Job(104, "Software Engineer", "TechCorp", "Develop software", "San Francisco, CA", "https://techcorp.com/jobs/101", "2025-01-08", 120000, 150000));
        sortedJobs.add(new Job(106, "Software Engineer", "TechCorp", "Develop software", "San Francisco, CA", "https://techcorp.com/jobs/101", "2025-01-08", 120000, 150000));
        sortedJobs.add(new Job(107, "Software Engineer", "TechCorp", "Develop software", "San Francisco, CA", "https://techcorp.com/jobs/101", "2025-01-08", 120000, 150000));
        sortedJobs.add(new Job(111, "Software Engineer", "TechCorp", "Develop software", "San Francisco, CA", "https://techcorp.com/jobs/101", "2025-01-08", 120000, 150000));

        List<Job> jobs = new ArrayList<>();

        jobs.add(new Job(106, "Software Engineer", "TechCorp", "Develop software", "San Francisco, CA", "https://techcorp.com/jobs/101", "2025-01-08", 120000, 150000));
        jobs.add(new Job(111, "Software Engineer", "TechCorp", "Develop software", "San Francisco, CA", "https://techcorp.com/jobs/101", "2025-01-08", 120000, 150000));
        jobs.add(new Job(104, "Software Engineer", "TechCorp", "Develop software", "San Francisco, CA", "https://techcorp.com/jobs/101", "2025-01-08", 120000, 150000));
        jobs.add(new Job(101, "Software Engineer", "TechCorp", "Develop software", "San Francisco, CA", "https://techcorp.com/jobs/101", "2025-01-08", 120000, 150000));
        jobs.add(new Job(107, "Software Engineer", "TechCorp", "Develop software", "San Francisco, CA", "https://techcorp.com/jobs/101", "2025-01-08", 120000, 150000));
        jobs.add(new Job(102, "Software Engineer", "TechCorp", "Develop software", "San Francisco, CA", "https://techcorp.com/jobs/101", "2025-01-08", 120000, 150000));
        List<Job> newJobs = userService.jobSort(jobs);

        assertEquals(newJobs.toString(), sortedJobs.toString());
    }
}

