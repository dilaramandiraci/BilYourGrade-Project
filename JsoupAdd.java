import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;

public class JsoupAdd
{
    public static void main(String[] args) throws IOException 
    {
        String url ="https://stars.bilkent.edu.tr/homepage/offerings.php?COURSE_CODE=CS&SEMESTER=20212";//burdaki numaralar sıkıntılı
        try {
            final Document doc= Jsoup.connect(url).get();
            Elements body = doc.select("tbody.scrollingContent");
            Elements rows = body.select("tr");

            for(Element row : rows)
            {
                if(row.select("td:nth-of-type(1)").text().equals(""))
                    {
                        continue;
                    }
                    else
                    {
                        final String course = row.select("td:nth-of-type(1)").text();
                        final String name = row.select("td:nth-of-type(2)").text();
                        System.out.println(course + name);  
                    }
            }
            
        } catch (Exception e) {
           System.out.println("You've got mail");
        }
    } 
    
}    
