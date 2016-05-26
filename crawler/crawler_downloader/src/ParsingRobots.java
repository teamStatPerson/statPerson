/**
 * Created by Андрей on 25.05.2016.
 */
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsingRobots {
    private String URLSiteMap;
    private String fileName;
    private Boolean isSiteMapFound;

    public ParsingRobots(String _fileName) {
        URLSiteMap = "";
        fileName = _fileName;
        isSiteMapFound = false;
        foundURLSiteMap();
    }

    public String getURLSiteMap() {
        return null;
    }

    private void foundURLSiteMap() {
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader(fileName));

            while ((line = reader.readLine()) != null) {
                if (isFoundSiteMap(line) == true) {
                    URLSiteMap = line.substring(8, line.length());
                    isSiteMapFound = true;
                    break;
                };
            }
        }

        catch (FileNotFoundException ex) {
            System.out.println("Файл robots.txt не найден");
        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void  printURLSiteMap(){
        if (isSiteMapFound == true) {
            System.out.println("URLSiteMap " + URLSiteMap);
        } else System.out.println("Файл Sitemap не найден");
    }

    public static boolean isFoundSiteMap(String testString){
        Pattern p = Pattern.compile("^Sitemap:.*$");
        Matcher m = p.matcher(testString);
        return m.matches();
    }




}
