package pro.tech_rdham.Job.Recommender.App;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "accounts")
public class User {
    private String username;
    private String password;
    private String email;
    private List<String> resume_data;
    private List<Job> jobs;
}
