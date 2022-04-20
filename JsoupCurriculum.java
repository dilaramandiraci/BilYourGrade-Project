import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;

/**
 * I have gotten this code snippet from:
 * https://github.com/ShaneLee/Java-Web-Stock-Scraper/blob/master/WebScrape.java
 * and from:
 * https://www.tallan.com/blog/2012/07/26/parsing-html-using-jsoup-library/
 * and modified them to fit our project's needs.
 */
public class JsoupCurriculum
{
    public ArrayList<Course> getCoursesFromRelatedYear(Person aPerson) throws IOException
    {
        ArrayList<Course> thisYearsCourses = new ArrayList<Course>();
        int courseCodeBasedOnUrl = (aPerson.getYearCode() -1 )*2 + aPerson.getSemesterCode();
        String url ="https://catalog.bilkent.edu.tr/dep/d"+courseCodeBasedOnUrl+".html";//burdaki numaralar sıkıntılı
        try {
            //kaçıncı table=(year-1)*2+semester.
            //index=4*kaçıncı table-1
            final Document doc= Jsoup.connect(url).get();
            Element table = doc.select("table").get(7);//3-->1.1,7-->1.2,11-->2.1//4er artıyo
            Elements rows = table.select("tr");
            
           // for(Element table: tables){
            //System.out.println(doc.outerHtml());
                for(Element row:rows)
                {
                    String courseName="";
                    String courseNumericCode="";
                    if(row.select("td:nth-of-type(1)").text().equals(""))
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

    public static void main(String[] args) throws IOException 
    {
        /* // String html = "<html><head><title>First parse</title></head>"
        // + "<body><p>Parsed HTML into a doc.</p></body></html>";
        // Document doc = Jsoup.parse(html);

        String url ="https://catalog.bilkent.edu.tr/dep/d11.html";//burdaki numaralar sıkıntılı
        try {
            //kaçıncı table=(year-1)*2+semester.
            //index=4*kaçıncı table-1
            final Document doc= Jsoup.connect(url).get();
            Element table = doc.select("table").get(7);//3-->1.1,7-->1.2,11-->2.1//4er artıyo
            Elements rows = table.select("tr");
            
           // for(Element table: tables){
            //System.out.println(doc.outerHtml());
                for(Element row:rows)
                {
                    if(row.select("td:nth-of-type(1)").text().equals(""))
                    {
                        continue;
                    }
                    else
                    {
                        final String course = row.select("td:nth-of-type(1)").text();
                        //final String name = row.select("td:nth-of-type(2)").text();
                        System.out.println(course);  
                    }
                }
           // }
           
            
        } catch (Exception e) {
           System.out.println("You've got mail");
        }
    } */
    } 
}    
