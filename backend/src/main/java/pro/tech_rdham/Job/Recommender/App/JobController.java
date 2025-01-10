package pro.tech_rdham.Job.Recommender.App;

import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/jobs")
public class JobController {
    @PostMapping("/api")
    public ResponseEntity<List<Job>> formSubmission(@RequestParam("pdf") MultipartFile file,
                                                    @RequestParam(name = "jobType") String jobType,
                                                    @RequestParam(name = "country") String country,
                                                    @RequestParam(name = "location", required = false) String location,
                                                    @RequestParam(name = "distance", required = false) String distance) {
        Path tempFilePath = null;
        try {
            System.out.println(jobType + ", " + country + ", " + location + ", " + distance);
            Random random = new Random();
            String tmpfilenum = Integer.toString(random.nextInt((9999999-1000000)+1));
            tempFilePath = Files.createTempFile("userFile-" + tmpfilenum, ".pdf");
            System.out.println(tempFilePath);
            File temp = tempFilePath.toFile();
            InputStream input = file.getInputStream();
            FileOutputStream output = new FileOutputStream(temp);
            byte[] buffer = new byte[20000];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            output.close();
            Service service = new Service();
            //        service.printAPIData();
            ArrayList<String> resumeData = new ResumeParser(temp.getAbsolutePath()).parseResume();
            System.out.println(resumeData);
            if (resumeData.isEmpty()) {
                return null;
            }
            boolean isInternship = jobType.compareTo("internship") == 0;
            int newDist = Integer.parseInt(distance);
            JSONObject jobData = service.makeCall(country, resumeData, location, newDist, isInternship);
            JSONArray jobs = (JSONArray) jobData.get("results");
            ArrayList<Job> jobList = service.getJobsFromJSON(jobs);
//            System.out.println(jobList);
            System.out.println(jobList.size());
            System.out.println("Resume parsed successfully! Jobs being returned!");
            return ResponseEntity.ok(jobList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            // Ensure temp file is deleted after processing
            if (tempFilePath != null) {
                try {
                    Files.deleteIfExists(tempFilePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

//    @GetMapping("/api")
//    public ResponseEntity<List<Job>> jobPostings(@RequestParam("jobType") String jobType,
//                                                 @RequestParam("country") String country,
//                                                 @RequestParam("location") String location,
//                                                 @RequestParam("distance") String distance){
//        Service service = new Service();
//        //        service.printAPIData();
//        ArrayList<String> resumeData = new ResumeParser("userFile.pdf").parseResume();
//        System.out.println(resumeData);
//        boolean isInternship = jobType.compareTo("internship") == 0;
//        int newDist = Integer.parseInt(distance);
//        JSONObject jobData = service.makeCall(country, resumeData, location, newDist, isInternship);
//        JSONArray jobs = (JSONArray) jobData.get("results");
//        ArrayList<Job> jobList = service.getJobsFromJSON(jobs);
////        System.out.println(jobList);
//        System.out.println("Jobs found!");
//        File file = new File("backend/userFile.pdf");
//        if(file.delete()){
//            System.out.println("File deleted successfully");
//        }
//        return ResponseEntity.ok(jobList);
//    }

//    @GetMapping("/api")
//    public ResponseEntity<Void> redirectToFrontend(@RequestParam("jobType") String jobType,
//                                                   @RequestParam("country") String country,
//                                                   @RequestParam("location") String location,
//                                                   @RequestParam("distance") String distance){
//        String url = createRedirectLink(jobType, country, location, distance);
//        return ResponseEntity.status(HttpStatus.FOUND).header("Location", url).build();
//    }
//
//    private String createRedirectLink(String jobType, String country, String location, String distance){
//        return "http://localhost:3000/jobs/query/?jobType=" + jobType + "&country=" + country + "&location=" + location + "&distance=" + distance;
//    }
