package pro.tech_rdham.Job.Recommender.App;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @PostMapping("/api")
    public ResponseEntity<String> formSubmission(@RequestParam("pdf") MultipartFile file,
                                            @RequestParam(name="jobType") String jobType,
                                            @RequestParam(name = "country") String country,
                                            @RequestParam(name = "location", required = false) String location,
                                            @RequestParam(name = "distance", required = false) String distance){
        try {
            File temp = new File("userFile.pdf");
            InputStream input = file.getInputStream();
            FileOutputStream output = new FileOutputStream(temp);
            byte[] buffer = new byte[20000];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            output.close();
            String query = String.format("?jobType=%s&country=%s&location=%s&distance=%s", jobType, country, location, distance);
            return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/jobs/api" + query).build();
        }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
    }

    @GetMapping("/api")
    public ResponseEntity<List<Job>> jobPostings(@RequestParam("jobType") String jobType,
                                                 @RequestParam("country") String country,
                                                 @RequestParam("location") String location,
                                                 @RequestParam("distance") String distance){
        Service service = new Service();
        //        service.printAPIData();
        ArrayList<String> resumeData = new ResumeParser("userFile.pdf").parseResume();
        System.out.println(resumeData);
        boolean isInternship = jobType.compareTo("internship") == 0;
        int newDist = Integer.parseInt(distance);
        JSONObject jobData = service.makeCall(country, resumeData, location, newDist, isInternship);
        JSONArray jobs = (JSONArray) jobData.get("results");
        ArrayList<Job> jobList = service.getJobsFromJSON(jobs);
//        System.out.println(jobList);
        System.out.println("Resume parsed successfully");
        File file = new File("backend/userFile.pdf");
        if(file.delete()){
            System.out.println("File deleted successfully");
        }
        return new ResponseEntity<List<Job>>(jobList, HttpStatus.OK);
    }
}
