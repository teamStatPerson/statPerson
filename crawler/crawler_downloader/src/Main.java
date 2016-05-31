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
/*            String fileNameRobots = "C://temp/robots.txt";

            ParsingRobots parsingRobots = new ParsingRobots(fileNameRobots);
            String URLSiteMap = parsingRobots.getURLSiteMap();
            parsingRobots.printURLSiteMap();
            System.out.println(URLSiteMap);*/
            //ParsingXML parsingXML = new ParsingXML("C://temp/sitemap7/sitemap7.xml");
           // String s1 = "http://www.news29.ru/sitemap.xml";
            String s3 = "http://www.statperson.webtm.ru";
            String s2 = "https://lenta.ru";


//поиск сайтов
/*            ParsingXML parsingXML = new ParsingXML(s2);
            List urlSites = parsingXML.doParseXML();
            printUrl(urlSites);*/


            //поиск упоминаний
            ArrayList<String>  Keywords = new ArrayList<String>();
            String site = "https://lenta.ru/";
            Keywords.add("Путина");
            Keywords.add("Путин");


            ParsingHTML parsingHTML = new ParsingHTML(site, Keywords);
            int raitingPutin = parsingHTML.raitingPerson();
            System.out.println("Рейтинг равен " + raitingPutin);

        }
    public static void printUrl(List<String> listUrl) {
        for (String url : listUrl) {
            System.out.println("url= " + url);
        }
        System.out.println("Количество найденных url = " + listUrl.size() );
    }

}
