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

    public ArrayList <Assesement> scrapeSyllabuss(Course aCourse) throws IOException
    {
        ArrayList<Assesement> assesmentList = new ArrayList<Assesement>();

        String url ="https://stars.bilkent.edu.tr/syllabus/view/"+aCourse.getName()+"/"+aCourse.getNumericCode()+"/";
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
    
    public ArrayList<Course> scrapeCurriculum(Person aPerson) throws IOException
    {
        ArrayList<Course> thisYearsCourses = new ArrayList<Course>();
        int tableCode = (aPerson.getYearCode() -1 )*2 + aPerson.getSemesterCode();
        Database mysql ;int deptNumber=11;
        try {
            mysql = new Database();
            deptNumber = Integer.parseInt(mysql.getDeptNo(aPerson.getDeptCode().toUpperCase()));//uppercase is just in case
        } catch (Exception e1) {
            System.out.println(e1);
            e1.printStackTrace();
        }
        
        String url ="https://catalog.bilkent.edu.tr/dep/d"+deptNumber+".html";//burdaki numaralar sıkıntılı
        try {
            //kaçıncı table=(year-1)*2+semester.
            //index=4*kaçıncı table-1
            final Document doc= Jsoup.connect(url).get();
            Element table = doc.select("table").get(4*tableCode-1);//3-->1.1,7-->1.2,11-->2.1//4er artıyo4*tableCode-1
            Elements rows = table.select("tr");
            
           // for(Element table: tables){
            //System.out.println(doc.outerHtml());
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
                        //final String name = row.select("td:nth-of-type(2)").text();
                        for(int i = 0; i<course.length(); i++)
                        {
                            if(course.charAt(i)!=' ')
                            courseName += course.charAt(i); //course name MATH
                            else
                            {
                                for(int j =i+1; j<course.length(); j++)
                                {
                                    courseNumericCode += course.charAt(j); //numeric kodu 132
                                }
                                break;
                            }
                        }  
                        thisYearsCourses.add(new Course(courseName, courseNumericCode)); //course name ve numeric codu ayrı ayrı alıyorum ki obje açmak kolay olsun.
                    }
                }
           // }  
        } catch (Exception e) {
           System.out.println("You've got mail");
        }
        return thisYearsCourses;
    } 
}
