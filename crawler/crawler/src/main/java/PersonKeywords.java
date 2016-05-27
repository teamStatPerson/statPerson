import java.util.ArrayList;

/**
 * Created by alexey_n on 25.05.2016.
 */
public class PersonKeywords {
    private String personName;
    private int personID;
    private ArrayList<String> keywords;

    public PersonKeywords(String personName, int personID, ArrayList<String> keywords) {
        this.personName = personName;
        this.personID = personID;
        this.keywords = keywords;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }
}
