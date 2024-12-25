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
    public Map<String, ArrayList<String>> parseResume(){
        Map<String, ArrayList<String>> catagories = Map.of(
                "education", new ArrayList<>(),
                "experience", new ArrayList<>(),
                "projects", new ArrayList<>(),
                "skills", new ArrayList<>()
        );
        Map<String, ArrayList<String>> buzzwords = Map.of(
                "education", new ArrayList<>(Arrays.asList("education", "school")),
                "experience", new ArrayList<>(Arrays.asList("work experience", "experience", "employment History", "professional experience", "employment")),
                "projects", new ArrayList<>(Arrays.asList("projects", "project experience", "projects experience")),
                "skills", new ArrayList<>(Arrays.asList("skills", "technical Skills", "languages", "tools", "languages & tools"))
        );
        try{
            PDDocument parsable = new PDDocument().load(resume);
            String resumeData = new PDFTextStripper().getText(parsable);
//            System.out.println(resumeData);
            String[] data = resumeData.split("\r\n");
//            System.out.println(Arrays.toString(data));
            for (int i = 0; i < data.length; i++){
                if (data[i].charAt(0) == ' '){
                    data[i] = data[i].substring(1);
                }
                data[i] = data[i].toLowerCase();
            }
            for (int i = 0; i < data.length; i++){
                boolean value = inHashMap(data[i], buzzwords);
                if (value){
                    String key = getKeyOfValue(data[i], buzzwords);
                    i++;
                    while (!(inHashMap(data[i], buzzwords))){
                        catagories.get(key).add(data[i]);
                        i++;
                        if (i == data.length){
                            break;
                        }
                    }
                    i--;
                }
            }
            return catagories;
        }
        catch(Exception E){
            System.out.println("Invalid file");
            E.printStackTrace();
            return null;
        }
    }

    public boolean inHashMap(String value, Map<String, ArrayList<String>> map){
        for (Map.Entry<String, ArrayList<String>> entry: map.entrySet()){
            for (String element: entry.getValue()){
                if (element.toLowerCase().compareTo(value) == 0){
                    return true;
                }
            }
        }
        return false;
    }

    public String getKeyOfValue(String value, Map<String, ArrayList<String>> map){
        for (Map.Entry<String, ArrayList<String>> entry: map.entrySet()) {
            for (String element : entry.getValue()) {
                if (element.toLowerCase().compareTo(value) == 0) {
                    return entry.getKey();
                }
            }
        }
        return null;
    }
}
