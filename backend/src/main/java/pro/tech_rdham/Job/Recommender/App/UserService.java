package pro.tech_rdham.Job.Recommender.App;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public User getUser(String id){
        return repo.findById(id).orElse(null);
    }
}
