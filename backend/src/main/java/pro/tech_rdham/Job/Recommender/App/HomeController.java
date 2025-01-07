package pro.tech_rdham.Job.Recommender.App;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.Boolean;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class HomeController {
    boolean isLoggedIn = false;
    @GetMapping("/")
    public ResponseEntity<Boolean> homePage() {

        return ResponseEntity.ok(isLoggedIn);
    }
}
