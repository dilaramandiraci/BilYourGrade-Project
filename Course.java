/**
 *  @author Dilara MANDIRACI
 *  and edited by Isil OZGU
 */
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.Jsoup;

public class Course implements Modifiable{

    private ArrayList <Assesement> assesements= new ArrayList<Assesement>(); //Final %40, Lab %35 etc.//ArrayList was not created properly.
    private String name; //CS
    private String numericCode; //102

    public Course(String aName, String numericCode)
    {
        this.name = aName;
        this.numericCode = numericCode;
        try {
            assesements = getAssesementsFromCurriculum();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Assesement> getAssesements() {
        return assesements;
    }

    public void setAssesements(ArrayList<Assesement> assesements) {
        this.assesements = assesements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public String getFulName()
    {
        return this.name + numericCode;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }

    public ArrayList<Assesement> getAssesementsFromCurriculum() throws IOException
    {
        JSoup sylabbusscraper = new JSoup();
        return sylabbusscraper.scrapeSyllabuss(this);
        
        /* ArrayList<Assesement> assesmentList = new ArrayList<Assesement>();

        String url ="https://stars.bilkent.edu.tr/syllabus/view/"+this.getName()+"/"+this.getNumericCode()+"/";
        try {
            final Document doc= Jsoup.connect(url).get();
            
            //System.out.println(doc.outerHtml());
            for(Element row:doc.select("table.bordered tr"))
            {
                if(row.select("td:nth-of-type(5)").text().equals(""))
                {
                    continue;
                }
                else
                {
                    final String weight = row.select("td:nth-of-type(5)").text();
                    final String name = row.select("td:nth-of-type(2)").text();
                    assesmentList.add(new Assesement(name, Integer.parseInt(weight)));  
                }
            }  
        } catch (Exception e) {
           System.out.println("You've got mail");
        }
        return assesmentList; */
    }

    @Override
    public String getAssessment() {
        // TODO Auto-generated method stub
        return null;
    }
}
