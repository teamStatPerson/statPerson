/**
 * Created by Андрей on 22.05.2016.
 */

import java.util.ArrayList;

public class Main {

        public static void main(String[] args) {
            String s1 = "http://www.statperson.webtm.ru";
            String s2 = "https://lenta.ru";
            String s3 =  "http://www.mvideo.ru/";
            String s4 = "http://www.mvideo.ru/sitemaps/sitemap-product-www.mvideo.ru-2";
            String s5 =  "http://29.ru/";
            ArrayList<String>  Keywords = new ArrayList<String>();
            Keywords.add("Путин");
            Keywords.add("погода");
           Crawler crawler = new Crawler();
            crawler.doJob(s2, Keywords);
        }
}
