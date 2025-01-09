package pro.tech_rdham.Job.Recommender.App;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save_job")
    public ResponseEntity<User> getUserData(@RequestParam("email") String email,
                                            @RequestParam("jobId") long jobId,
                                            @RequestParam("title") String title,
                                            @RequestParam("company") String company,
                                            @RequestParam("description") String description,
                                            @RequestParam("location") String location,
                                            @RequestParam("job_url") String job_url,
                                            @RequestParam("job_posted") String job_posted,
                                            @RequestParam("min_salary") double min_salary,
                                            @RequestParam("max_salary") double max_salary){
        Job job = new Job(jobId, title, company, description, location, job_url, job_posted, min_salary, max_salary);
        System.out.println(job);
        User user = userService.saveJobtoUserDB(email, job);
        return ResponseEntity.ok(user);
    }


    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam("email") String email,
                                        @RequestParam("password") String password) {
        try {
            User user = userService.getUser(email);
            System.out.println(user);
            System.out.println(userService.getPassword(email).compareTo(password));
            System.out.println((user != null && userService.getPassword(email).compareTo(password) == 0));
            if (user != null && userService.getPassword(email).compareTo(password) == 0) {
                return ResponseEntity.ok(user);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestParam("email") String email,
                                      @RequestParam("username") String username,
                                      @RequestParam("password") String password){
        User user = userService.getUser(email);
        System.out.println(user);
        if (user != null){
            return null;
        }
        else{
            User newUser = new User(username, password, email, new ArrayList<>(), new ArrayList<>());
            return ResponseEntity.ok(userService.saveToDB(newUser));
        }
    }

}
