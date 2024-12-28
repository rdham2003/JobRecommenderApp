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
    public ResponseEntity<String> uploadPDF(@RequestParam("pdf") MultipartFile file){
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
            return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/jobs/api").build();
        }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
    }

    @GetMapping("/api")
    public ResponseEntity<List<Job>> jobPostings(){
        Service service = new Service();
        //        service.printAPIData();
        ArrayList<String> resumeData = new ResumeParser("userFile.pdf").parseResume();
        System.out.println(resumeData);
        JSONObject jobData = service.makeCall("US", resumeData, "California", 200, false);
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
