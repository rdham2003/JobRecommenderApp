package pro.tech_rdham.Job.Recommender.App;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/data")
    public ResponseEntity<User> getUserData(){
        System.out.println("Hi");
        User user = userService.getUser("677af0d997f8066190e04704");
        System.out.println(user);
        return ResponseEntity.ok(user);
    }

}
