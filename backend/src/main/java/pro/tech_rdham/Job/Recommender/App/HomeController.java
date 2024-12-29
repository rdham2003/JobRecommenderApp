package pro.tech_rdham.Job.Recommender.App;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String homePage() {
        return "forward:/index.html";
    }
}
