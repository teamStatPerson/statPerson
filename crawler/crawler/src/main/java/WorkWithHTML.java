import entity.Keywords;
import implementation.KeywordsImpl;
import interfaces.KeywordsInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alexey_n on 06.06.2016.
 */
public class WorkWithHTML {

    public static int search(String html, int idPerson) {
        KeywordsInterface keywordsInterface = new KeywordsImpl();
        List<Keywords> keywords = keywordsInterface.getKeywordsByIDPerson(idPerson);
        String regexp = "";
        for (Keywords keyword : keywords) {
            String key = keyword.getName();
            regexp += key + "|";
        }
        regexp = regexp.substring(0, regexp.length() - 1);
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(html);
        int rank = 0;
        while (matcher.find()) {
            rank++;
        }
        return rank;
    }
}
