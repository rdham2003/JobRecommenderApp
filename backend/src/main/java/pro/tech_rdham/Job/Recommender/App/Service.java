package pro.tech_rdham.Job.Recommender.App;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

@Getter
public class Service {
    Dotenv dotenv = Dotenv.load();

    public String API_ID = dotenv.get("API_ID");
    public String API_KEY = dotenv.get("API_KEY");

    public void printAPIData(){
        System.out.println(API_ID);
        System.out.println(API_KEY);
    }

    public JSONObject makeCall(String country, ArrayList<String> skills, String location, int distance, boolean isInternship){
        StringBuilder skillset = new StringBuilder();

        for (int i = 0; i < skills.size(); i++){
            if (i == skills.size()-1){
                skillset.append(skills.get(i)) ;
            }
            else {
                skillset.append(skills.get(i));
                skillset.append("%2C%20");
            }
        }
//        System.out.println(skillset);

        StringBuilder apiUrl = new StringBuilder();
        if (isInternship){
            apiUrl.append("https://api.adzuna.com/v1/api/jobs/us/search/1?app_id=");
            apiUrl.append(API_ID);
            apiUrl.append("&app_key=");
            apiUrl.append(API_KEY);
            apiUrl.append("&results_per_page=50&what_or=intern%2C%20");
            apiUrl.append(skillset);
            apiUrl.append("&where=") ;
            apiUrl.append(location) ;
            apiUrl.append("&distance=");
            apiUrl.append(distance);
            apiUrl.append("&max_days_old=7");
        }
        else{
            apiUrl.append("https://api.adzuna.com/v1/api/jobs/us/search/1?app_id=");
            apiUrl.append(API_ID);
            apiUrl.append("&app_key=");
            apiUrl.append(API_KEY);
            apiUrl.append("&results_per_page=50&what_or=");
            apiUrl.append(skillset);
            apiUrl.append("&where=") ;
            apiUrl.append(location) ;
            apiUrl.append("&distance=");
            apiUrl.append(distance);
            apiUrl.append("&max_days_old=7");
        }

        String apiStr = apiUrl.toString();
//        System.out.println(apiStr);

        try{
            URL url = new URL(apiStr);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            StringBuilder jobData;

            if (con.getResponseCode() != 200){
                throw new RuntimeException("Bad Response code: " + con.getResponseCode());
            }
            else{
                jobData = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()){
                    jobData.append(scanner.nextLine());
                }
                scanner.close();
//                System.out.println(jobData);
            }

            return new JSONObject(jobData.toString());

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Job> getJobsFromJSON(JSONArray jobs){
        ArrayList<Job> jobData = new ArrayList<>();
        for (JSONObject job: jobs){
            Job newJob = new Job(job.get("created"))
        }
    }
}
