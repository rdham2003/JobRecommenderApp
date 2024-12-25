package pro.tech_rdham.Job.Recommender.App;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class readCSV {
    public HashMap<String, Integer> readCSVFile(String filepath) throws FileNotFoundException {
        HashMap<String, Integer> skills = new HashMap<>();
        try (Scanner scanner = new Scanner(new File(filepath))) {
            while (scanner.hasNextLine()) {
                skills.put(scanner.nextLine(), 0);
            }
            return skills;
        } catch (FileNotFoundException e) {
            System.out.println("CANT FIND FILE!!!!!!!!!");
            e.printStackTrace();
            return null;
        }
    }
}
