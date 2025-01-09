package pro.tech_rdham.Job.Recommender.App;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static pro.tech_rdham.Job.Recommender.App.HomeController.isLoggedIn;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save_job")
    public ResponseEntity<User> getUserData(@RequestParam("username") String username){
        System.out.println("Hi");
        User user = userService.getUsername(username);
        System.out.println(user);
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
                isLoggedIn = true;
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
            isLoggedIn = true;
            return ResponseEntity.ok(userService.saveToDB(newUser));
        }
    }

}
