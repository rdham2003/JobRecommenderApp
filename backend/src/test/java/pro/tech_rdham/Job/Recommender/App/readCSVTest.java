package pro.tech_rdham.Job.Recommender.App;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class readCSVTest {

    @Test
    void testReadCSVFile() {
        String testFilePath = "test_skills.csv";
        try (FileWriter writer = new FileWriter(testFilePath)) {
            writer.write("Java\n");
            writer.write("Python\n");
            writer.write("Docker\n");
        } catch (IOException e) {
            fail("Failed to create test CSV file.");
        }

        readCSV csvReader = new readCSV();
        try {
            HashMap<String, Integer> skills = csvReader.readCSVFile(testFilePath);

            assertNotNull(skills, "Skills map should not be null.");
            assertEquals(3, skills.size(), "Skills map should contain 3 entries.");
            assertTrue(skills.containsKey("Java"), "Skills map should contain 'Java'.");
            assertTrue(skills.containsKey("Python"), "Skills map should contain 'Python'.");
            assertTrue(skills.containsKey("Docker"), "Skills map should contain 'Docker'.");
            assertEquals(0, skills.get("Java"), "'Java' should be initialized with 0.");
            assertEquals(0, skills.get("Python"), "'Python' should be initialized with 0.");
            assertEquals(0, skills.get("Docker"), "'Docker' should be initialized with 0.");
        } catch (Exception e) {
            fail("Exception occurred during testing: " + e.getMessage());
        } finally {
            File file = new File(testFilePath);
            if (!file.delete()) {
                System.err.println("Failed to delete test file.");
            }
        }
    }
}