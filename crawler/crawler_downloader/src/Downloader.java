/**
 * Created by Андрей on 22.05.2016.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Downloader {

    public String Download(String url) {
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(
           new InputStreamReader(new URL(url).openConnection().getInputStream(), "UTF-8"));
                line = reader.readLine();
            if (line == null) {
                line = "";
            }
                System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

}

