package pro.tech_rdham.Job.Recommender.App;
import io.github.cdimascio.dotenv.Dotenv;


public class Service {
    Dotenv dotenv = Dotenv.load();

    public String API_ID = dotenv.get("API_ID");
    public String API_KEY = dotenv.get("API_KEY");

    public void printAPIData(){
        System.out.println(API_ID);
        System.out.println(API_KEY);
    }
}
