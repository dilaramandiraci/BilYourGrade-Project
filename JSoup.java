/**
 * @author s Dilara Mandıracı , Işıl Özgü
 *
 */
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;

public class JSoup {
    String SyllabusUrl;
    Document doc;
    Element table;
    Element rows;
    String course;
    String weight;
    String name;

    /**
     * This method goes to the url and brings syllabuss information from bilkent University's sylabbus page according to given course
     * @param aCourse which course's syllabuss needed
     * @return Assessments of that course
     * @throws IOException
     */
    public ArrayList <Assesement> scrapeSyllabuss(Course aCourse) throws IOException
    {
        ArrayList<Assesement> assesmentList = new ArrayList<Assesement>();

        String url ="https://stars.bilkent.edu.tr/syllabus/view/"+aCourse.getName()+"/"+aCourse.getNumericCode()+"/";
        try {
            final Document doc= Jsoup.connect(url).get();
            
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
                    if(!(name.equals("None")) && !(weight.equals("0")))
                    {
                       assesmentList.add(new Assesement(name, Integer.parseInt(weight))); 
                    }
                      
                }
            }  
        } catch (Exception e) {
           System.out.println("You've got mail");
        }
        return assesmentList;
    }
    
    /**
     * This method brings information from Bilkent University's department page accordingly given semester and year
     * @param aPerson we need to get person's year and semester information
     * @return an arraylist of the courses that the person needs to take according to curriculum. (default courses)
     * @throws IOException
     */
    public ArrayList<Course> scrapeCurriculum(Person aPerson) throws IOException
    {
        ArrayList<Course> thisYearsCourses = new ArrayList<Course>();
        int tableCode = (aPerson.getYearCode() -1 )*2 + aPerson.getSemesterCode();
        Database mysql ;int deptNumber=11;
        try {
            mysql = new Database();
            deptNumber = Integer.parseInt(mysql.getDeptNo(aPerson.getDeptCode().toUpperCase()));
        } catch (Exception e1) {
            System.out.println(e1);
            e1.printStackTrace();
        }
        
        String url ="https://catalog.bilkent.edu.tr/dep/d"+deptNumber+".html";
        try {
            final Document doc= Jsoup.connect(url).get();
            Element table = doc.select("table").get(4*tableCode-1);
            Elements rows = table.select("tr");
            
                for(Element row:rows)
                {
                    String courseName="";
                    String courseNumericCode="";
                    String scrape=row.select("td:nth-of-type(1)").text();
                    if(scrape.equals("Code")||scrape.equals("Lec.")||scrape.equals(""))
                    {
                        continue;
                    }
                    else
                    {
                        final String course = row.select("td:nth-of-type(1)").text();
                        for(int i = 0; i<course.length(); i++)
                        {
                            if(course.charAt(i)!=' ')
                            courseName += course.charAt(i); 
                            else
                            {
                                for(int j =i+1; j<course.length(); j++)
                                {
                                    courseNumericCode += course.charAt(j); 
                                }
                                break;
                            }
                        }  
                        thisYearsCourses.add(new Course(courseName, courseNumericCode));
                    }
                }
        } catch (Exception e) {
           System.out.println("You've got mail");
        }
        return thisYearsCourses;
    } 
}
