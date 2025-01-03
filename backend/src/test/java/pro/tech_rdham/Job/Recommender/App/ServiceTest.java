package pro.tech_rdham.Job.Recommender.App;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {

    @Test
    public void testPrintAPIData() {
        Service service = new Service();
        assertNotNull(service.getAPI_ID(), "API_ID should not be null");
        assertNotNull(service.getAPI_KEY(), "API_KEY should not be null");
    }

    @Test
    public void testMakeCallWithValidInputs() {
        Service service = new Service();
        ArrayList<String> skills = new ArrayList<>();
        skills.add("Java");
        skills.add("Spring");
        JSONObject result = service.makeCall("us", skills, "San Francisco", 10, false);
        assertNotNull(result, "Response should not be null");
    }

    @Test
    public void testMakeCallWithEmptySkills() {
        Service service = new Service();
        ArrayList<String> skills = new ArrayList<>();
        JSONObject result = service.makeCall("us", skills, "San Francisco", 10, false);
        assertNotNull(result, "Response should not be null even if skills are empty");
    }

    @Test
    public void testGetJobsFromJSON() {
        Service service = new Service();
        JSONArray jobs = new JSONArray("[{ \"id\": 1, \"title\": \"Software Engineer\", \"company\": { \"display_name\": \"Tech Corp\" }, \"description\": \"Develop software\", \"location\": { \"display_name\": \"San Francisco\" }, \"redirect_url\": \"http://example.com\", \"created\": \"2025-01-01\", \"salary_min\": 60000, \"salary_max\": 90000 }]");
        ArrayList<Job> result = service.getJobsFromJSON(jobs);
        assertEquals(1, result.size(), "Should return one job");
        assertEquals("Software Engineer", result.get(0).getTitle(), "Job title should match");
    }

    @Test
    public void testGetJobsFromJSONWithEmptyArray() {
        Service service = new Service();
        JSONArray jobs = new JSONArray("[]");
        ArrayList<Job> result = service.getJobsFromJSON(jobs);
        assertTrue(result.isEmpty(), "Result should be empty for an empty JSON array");
    }
}