package pro.tech_rdham.Job.Recommender.App;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@ToString
@Document(collection = "accounts")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private List<String> resume_data;
    private List<Job> jobs;

    public User(String username, String password, String email, List<String> resume_data, List<Job> jobs){
        this.username = username;
        this.password = password;
        this.email = email;
        this.resume_data = resume_data;
        this.jobs = jobs;
    }
}
