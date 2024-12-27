package pro.tech_rdham.Job.Recommender.App;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

@SpringBootApplication
public class JobRecommenderAppApplication {

	public static void printResume(Map<String, ArrayList<String>> map){
		for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}
	public static void main(String[] args) {
//		SpringApplication.run(JobRecommenderAppApplication.class, args);
		Service service = new Service();
		service.printAPIData();
		ArrayList<String> resumeData = new ResumeParser("Resume Base-SWE.pdf").parseResume();
//		System.out.println(resumeData);
		JSONObject jobData = service.makeCall("US", resumeData, "Minneapolis", 500, false);
		JSONArray jobs = (JSONArray) jobData.get("results");
		System.out.println(jobs);
		System.out.println("Resume parsed successfully");
	}
}
