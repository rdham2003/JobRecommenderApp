package pro.tech_rdham.Job.Recommender.App;

import java.io.File;
import java.util.*;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class ResumeParser{
    private final File resume;
    public ResumeParser(String pdf){resume = new File(pdf);}

    public ArrayList<String> parseResume(){
        Map<String, ArrayList<String>> categories = Map.of(
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
                        categories.get(key).add(data[i]);
                        i++;
                        if (i == data.length){
                            break;
                        }
                    }
                    i--;
                }
            }

            HashMap<String, Integer> skills = new readCSV().readCSVFile("src/main/resources/skills.csv");
//          System.out.println(skills);
            HashMap<String,String> strCat = new HashMap<>();

            for (Map.Entry<String, ArrayList<String>> entry: categories.entrySet()) {
                String strCatag = String.join(" ", entry.getValue()).replaceAll(",", "");
//                System.out.println(strCatag);
                strCat.put(entry.getKey(), strCatag);
            }
//            System.out.println(getUserSkills(skills, strCat));
            parsable.close();
            return getUserSkills(skills, strCat);
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

    public ArrayList<String> getUserSkills(HashMap<String, Integer> skills, Map<String, String> map){
        ArrayList<String> userSkills = new ArrayList<>();
        for (Map.Entry<String, String> entry: map.entrySet()){
            ArrayList<String> parsedList = new ArrayList<>(List.of(entry.getValue().split(" ")));
            for (String element : parsedList){
                if (skills.get(element) != null && skills.get(element) != 1){
                    userSkills.add(element);
                    skills.put(element, 1);
                }
            }
        }
        return userSkills;
    }
}
