package pro.tech_rdham.Job.Recommender.App;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    Job myJob = new Job(123, "Software Engineer", "Fortnite", "Code", "Antarctica", "website.gov", "12-21-2024", 101000.00, 158000.00);
    Job myJob2 = new Job(125, "Data Scientist", "Google", "Use Python", "USA", "website.com", "12-22-2004", 250000.00, 400000.00);

    @GetMapping("/api")
    public ResponseEntity<List<Job>> jobPostings(){
        Service service = new Service();
//        service.printAPIData();
        ArrayList<String> resumeData = new ResumeParser("RDSWE2025.pdf").parseResume();
		System.out.println(resumeData);
        JSONObject jobData = service.makeCall("US", resumeData, "Minneapolis", 500, false);
        JSONArray jobs = (JSONArray) jobData.get("results");
        ArrayList<Job> jobList = service.getJobsFromJSON(jobs);
        System.out.println(jobList);
        System.out.println("Resume parsed successfully");
        return new ResponseEntity<List<Job>>(jobList, HttpStatus.OK);
    }
}
