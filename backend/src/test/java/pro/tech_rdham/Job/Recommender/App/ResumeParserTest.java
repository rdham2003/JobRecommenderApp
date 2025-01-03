package pro.tech_rdham.Job.Recommender.App;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ResumeParserTest {

    @Test
    public void testParseResumeWithValidFile() {
        ResumeParser parser = new ResumeParser("validResume.pdf");
        ArrayList<String> skills = parser.parseResume();
        assertNotNull(skills, "Skills list should not be null");
        assertFalse(skills.isEmpty(), "Skills list should not be empty");
    }

    @Test
    public void testParseResumeWithInvalidFile() {
        ResumeParser parser = new ResumeParser("invalid.pdf");
        ArrayList<String> skills = parser.parseResume();
        assertNull(skills, "Skills list should be null for an invalid file");
    }

    @Test
    public void testInHashMapWithValidValue() {
        ResumeParser parser = new ResumeParser("dummy.pdf");
        boolean result = parser.inHashMap("education", Map.of("education", new ArrayList<>(List.of("education", "school"))));
        assertTrue(result, "Should return true for a value in the map");
    }

    @Test
    public void testInHashMapWithInvalidValue() {
        ResumeParser parser = new ResumeParser("dummy.pdf");
        boolean result = parser.inHashMap("unknown", Map.of("education", new ArrayList<>(List.of("education", "school"))));
        assertFalse(result, "Should return false for a value not in the map");
    }

    @Test
    public void testGetUserSkillsWithValidSkills() {
        ResumeParser parser = new ResumeParser("dummy.pdf");
        HashMap<String, Integer> skillMap = new HashMap<>();
        skillMap.put("Java", 0);
        skillMap.put("Python", 0);
        ArrayList<String> result = parser.getUserSkills(skillMap, Map.of("skills", "Java Python"));
        assertEquals(2, result.size(), "Should identify two skills");
        assertTrue(result.contains("Java") && result.contains("Python"), "Result should contain identified skills");
    }
}
