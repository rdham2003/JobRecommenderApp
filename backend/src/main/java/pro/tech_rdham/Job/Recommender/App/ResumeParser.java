package pro.tech_rdham.Job.Recommender.App;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class ResumeParser{
    private final File resume;
    public ResumeParser(String pdf){
        resume = new File(pdf);
    }
    public String[] parseResume(){
        Map<String, ArrayList<String>> catagories = Map.of(
                "Education", new ArrayList<>(),
                "Experience", new ArrayList<>(),
                "Projects", new ArrayList<>(),
                "Skills", new ArrayList<>()
        );
        Map<String, ArrayList<String>> buzzwords = Map.of(
                "Education", new ArrayList<>(Arrays.asList("Education", "School")),
                "Experience", new ArrayList<>(Arrays.asList("Work Experience", "Experience", "Employment History", "Professional Experience", "Employment")),
                "Projects", new ArrayList<>(Arrays.asList("Projects", "Project Experience")),
                "Skills", new ArrayList<>(Arrays.asList("Skills", "Technical Skills", "Languages", "Tools", "Languages & Tools"))
        );
        try{
            PDDocument parsable = new PDDocument().load(resume);
            String resumeData = new PDFTextStripper().getText(parsable);
            String[] data = resumeData.split("\r\n");
            for (int i = 0; i < data.length; i++){
                if (Character.compare(data[i].charAt(0), ' ') == 0){
                    data[i] = data[i].substring(1);
                }
            }
            return data;
        }
        catch(Exception E){
            System.out.println("Invalid file");
            E.printStackTrace();
            return null;
        }
    }

//    public ArrayList<String> getEducation(String resume);
//    public ArrayList<String> getExpereince(String resume);
//    public ArrayList<String> getProjects(String resume);
//    public ArrayList<String> getSkills(String resume);


}
