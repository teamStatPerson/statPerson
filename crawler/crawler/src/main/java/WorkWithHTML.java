import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alexey_n on 24.05.2016.
 */
public class WorkWithHTML {

    public WorkWithHTML() {

    }


    public static boolean search(String html, PersonKeywords personKeywords) { //todo добавить обработку keywords в разных регистрах
        ArrayList<String> keywords = new ArrayList<>();
        keywords = personKeywords.getKeywords();
        String regexp = "";
        for (String keyword : keywords) {
            regexp += keyword + "|";
        }
        regexp = regexp.substring(0, regexp.length() - 1);
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(html);
        boolean found = matcher.find();
        return found;
    }
}
