package pro.tech_rdham.Job.Recommender.App;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public User getUser(String email){
        return repo.findByEmail(email).orElse(null);
    }

    public String getPassword(String email) {
        Optional<User> user = repo.findByEmail(email);
        return user.map(User::getPassword).orElse(null);
    }

    public User saveToDB(User user){
        return repo.save(user);
    }
}
