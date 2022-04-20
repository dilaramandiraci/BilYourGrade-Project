import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.Jsoup;

/**
 * I have gotten this code snippet from:
 * https://github.com/ShaneLee/Java-Web-Stock-Scraper/blob/master/WebScrape.java
 * and modified it to fit our project's needs.
 */
public class JsoupRunner
{
    private static ArrayList<Assesement> assesmentList = new ArrayList<Assesement>();
    
    public static void main(String[] args) throws IOException 
    {
        // String html = "<html><head><title>First parse</title></head>"
        // + "<body><p>Parsed HTML into a doc.</p></body></html>";
        // Document doc = Jsoup.parse(html);

        String url ="https://stars.bilkent.edu.tr/syllabus/view/CS/102/";
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
                    System.out.println(name+": "+weight);  
                }
            }
           
            
        } catch (Exception e) {
           System.out.println("You've got mail");
        }
    }
}