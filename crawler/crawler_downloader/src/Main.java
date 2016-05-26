/**
 * Created by Андрей on 22.05.2016.
 */
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

        public static void main(String[] args) {
            String fileNameRobots = "C://temp/robots.txt";
            ParsingRobots parsingRobots = new ParsingRobots(fileNameRobots);
            String URLSiteMap = parsingRobots.getURLSiteMap();
            parsingRobots.printURLSiteMap();
            System.out.println(URLSiteMap);
        }
}
