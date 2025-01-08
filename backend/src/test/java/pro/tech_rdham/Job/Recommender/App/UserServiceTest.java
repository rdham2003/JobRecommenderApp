package pro.tech_rdham.Job.Recommender.App;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.bson.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testGetUser() {
        User user = userService.getUser("test@example.com");
        System.out.println("Retrieved User: " + user);
        assertEquals("test@example.com", user.getEmail());
    }
}

