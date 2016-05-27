import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey_n on 24.05.2016.
 */
public class QueryString {

    private static String stringForUpdatePersonPageRank;
    private static String stringForDeletePersonPageRank;

    public static String getStringForUpdatePages(List<String> partListUrl, int siteId) {
        StringBuilder query = new StringBuilder("INSERT INTO pages (ID, Url, SiteID, FoundDateTime, LastScanDate) VALUES ");
        for (int i = 0; i < partListUrl.size(); i++) {
            String adUrl = "(null,'" + partListUrl.get(i) + "'," + siteId + ",now(),null),";
            query.append(adUrl);
        }
        int zpt = query.lastIndexOf(",");
        query.deleteCharAt(zpt);
        System.out.println(query.toString());
        return query.toString();
    } //todo предполагается что мы только добавляем в первый раз ссылки из sitemap сайта (повторный обход и проверка на то что есть ли ссылка в базе не реализовано пока)

    public static String getStringForUpdatePersonPageRank(int pageID , int personId, boolean found) {
        int rank = found ? 1 : 0;
        String query = "INSERT INTO personpagerank (PersonID,PageID,Rank) VALUES (" + personId +"," + pageID + "," + rank + ") ON DUPLICATE KEY UPDATE Rank =" + rank;
        return query;
    }
}
