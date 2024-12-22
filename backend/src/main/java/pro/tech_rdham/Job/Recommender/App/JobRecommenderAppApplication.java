package pro.tech_rdham.Job.Recommender.App;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@SpringBootApplication
public class JobRecommenderAppApplication {

	public static void main(String[] args) {
//		SpringApplication.run(JobRecommenderAppApplication.class, args);
		String resumeData = new ResumeParser("RDSWE2025.pdf").parseResume();
		System.out.println(resumeData + "\nResume parsed successfully");
	}

}
