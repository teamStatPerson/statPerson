package crauler.work;

import statPerson.element.keyword.Keyword;
import statPerson.element.keyword.KeywordDao;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alexey_n on 10.06.2016.
 */
public class WorkWithHTML {

    public static int search(String html, int idPerson) {
        List<Keyword> keywords = KeywordDao.getKeywordOfPerson(idPerson);
        String regexp = "";
        for (Keyword keyword : keywords) {
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
