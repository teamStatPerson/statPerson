import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey_n on 24.05.2016.
 */
public class Crawler { //todo почистить клас и перенести работу по классам
    private static DataSource dataSource; //todo реализовать как сингтон чтоб использовать в классах

    Crawler() {
        dataSource = new DataSource();
    }

    public static void main(String[] args) {
        Crawler crawler = new Crawler();

//         собирает и пишет в базу все найденные по всем sitemap-ам ссылки на страницы
//        crawler.workWithSitemap("http://lenta.ru", 1);

//        простой анализ страниц в таблице по всем персонам
//        ArrayList<Integer> listPersonId = new ArrayList<>();
//        for (int i = 1; i <= 5; i++) {
//            listPersonId.add(i);
//        }
//        for (int i = 1; i <= 100; i++) {
//            crawler.workWithHTML(i, listPersonId);
//        }
    }

    private void workWithSitemap(String urlRootXML, int siteId) {
        WorkWithSitemap workWithSitemap = new WorkWithSitemap(urlRootXML);
        workWithSitemap.getListUrl();
        boolean isNextPart = workWithSitemap.isNextPart();
        while (isNextPart) {
            List<String> partListUrl = new ArrayList<>();
            partListUrl = workWithSitemap.getNextPartOfListUrl();
            String queryString = QueryString.getStringForUpdatePages(partListUrl, siteId);
            dataSource.executeUpdate(queryString);
            isNextPart = workWithSitemap.isNextPart();
        }
    }

    private PersonKeywords getPersonKeywords(int personId) {
        String personName = dataSource.getPersonNameById(personId);
        ArrayList<String> keywords = dataSource.getListKeywordsByPersonId(personId);
        PersonKeywords personKeywords = new PersonKeywords(personName, personId, keywords);
        return personKeywords;


    }

    private ArrayList<PersonKeywords> getListPersonKeywords(ArrayList<Integer> listPersonId) {
        ArrayList<PersonKeywords> listpersonKeywords = new ArrayList<>();
        for (Integer personId : listPersonId) {
            PersonKeywords personKeywords = getPersonKeywords(personId);
            listpersonKeywords.add(personKeywords);
        }
        return listpersonKeywords;
    }

    //При обработке страницы в таблице personpagerank ничего не удаляем, а только добавляем или обновляем информацию (ставим rank 1 или 0)
    //todo не обрабатываем ситуацию, когда по проверяемой странице вдруг кто-то перестал упоминаться (отложим пока)
    private void workWithHTML(int pageID, int personId) {
        PersonKeywords personKeywords = getPersonKeywords(personId);
        String url = dataSource.getPageUrlById(pageID);
        String html = Downloader.downloadHTML(url);
        if (html != null) {
            boolean found = false;
            found = WorkWithHTML.search(html, personKeywords);
            if (found) {
                String queryString = QueryString.getStringForUpdatePersonPageRank(pageID, personId, found);
                dataSource.executeUpdate(queryString);
            }
        }
        dataSource.updateLastScanDate(pageID);
    }

    private void workWithHTML(int pageID, ArrayList<Integer> listPersonId) {
        String url = dataSource.getPageUrlById(pageID);
        String html = Downloader.downloadHTML(url);
        if (html != null) {
            System.out.println("Загрузили страницу - " + pageID);
            for (int personId : listPersonId) {
                PersonKeywords personKeywords = getPersonKeywords(personId);
                boolean found = false;
                found = WorkWithHTML.search(html, personKeywords);
                if (found) {
                    String queryString = QueryString.getStringForUpdatePersonPageRank(pageID, personId, found);
                    dataSource.executeUpdate(queryString);
                }
            }
        } else {
            System.out.println("АЛАРМ - " + pageID);
        }
        dataSource.updateLastScanDate(pageID);
    }
}
