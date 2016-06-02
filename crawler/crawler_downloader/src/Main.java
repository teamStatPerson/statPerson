/**
 * Created by Андрей on 22.05.2016.
 */
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

        public static void main(String[] args) {
            List<String> urlSites = new ArrayList<String>();



            String fileNameRobots = "http://www.mvideo.ru/robots.txt";

            String s1 = "http://www.statperson.webtm.ru";
            String s2 = "https://lenta.ru";
            String s3 =  "http://www.mvideo.ru/";
            ParsingRobots parsingRobots = new ParsingRobots(s2);
            parsingRobots.getRobotsFileFound();
            parsingRobots.getURLsiteMapFound();

                if (parsingRobots.getURLsiteMapFound() == true) {
                    ParsingXML parsingXML = new ParsingXML(s2, parsingRobots.getUrlSiteMap());
                    urlSites = parsingXML.getListUrl();
                   // printUrl(urlSites);
                } else {
                    ParsingXML parsingXML = new ParsingXML(s2, s2+"/sitemap.xml");
                    urlSites = parsingXML.getListUrl();
                    printUrl(urlSites);
                }

            //поиск сайтов
            //поиск упоминаний
            ArrayList<String>  Keywords = new ArrayList<String>();
            Keywords.add("Путина");
            Keywords.add("Путин");

Integer raitngTotal = 0;
            for (String url : urlSites) {
                ParsingHTML parsingHTML = new ParsingHTML(url, Keywords);
                int raitingPutin = parsingHTML.raitingPerson();
                raitngTotal = raitngTotal + raitingPutin;
              //  System.out.println("Рейтинг равен " + raitingPutin);
                System.out.println("Общий рейтинг " + raitngTotal);
            }

            System.out.println("Общий рейтинг " + raitngTotal);



        }




    public static void printUrl(List<String> listUrl) {
        for (String url : listUrl) {
            System.out.println("url= " + url);
        }
        System.out.println("Количество найденных url = " + listUrl.size() );
    }

}
