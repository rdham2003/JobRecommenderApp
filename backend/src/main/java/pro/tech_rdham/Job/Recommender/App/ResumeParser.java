package pro.tech_rdham.Job.Recommender.App;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class ResumeParser{
    private final File resume;
    public ResumeParser(String pdf){
        resume = new File(pdf);
    }
    public String parseResume(){
        try{
            PDDocument parsable = new PDDocument().load(resume);
            String resumeData = new PDFTextStripper().getText(parsable);
            return resumeData;
        }
        catch(Exception E){
            System.out.println("Invalid file");
            E.printStackTrace();
            return null;
        }
    }

//    public String getSkills(PDDocument resume);
//    public String getExpereince(PDDocument resume);


}
