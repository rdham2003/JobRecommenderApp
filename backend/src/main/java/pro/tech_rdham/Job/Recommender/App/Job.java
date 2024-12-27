package pro.tech_rdham.Job.Recommender.App;

import lombok.*;

//import org.bson.types.ObjectId;
//import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Job {
    private long jobId;
    private String title;
    private String company;
    private String description;
    private String location;
    private String job_url;
    private String job_posted;
    private double min_salary;
    private double max_salary;
}
