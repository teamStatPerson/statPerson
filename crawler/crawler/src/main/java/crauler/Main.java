package crauler;

import crauler.work.Downloader;
import crauler.work.WorkWithHTML;
import crauler.work.WorkWithSitemap;
import statPerson.element.page.Page;
import statPerson.element.page.PageDao;
import statPerson.element.person.Person;
import statPerson.element.person.PersonDao;
import statPerson.element.person_page_rank.PersonPageRank;
import statPerson.element.person_page_rank.PersonPageRankDao;
import statPerson.element.site.Site;
import statPerson.element.site.SiteDao;

import java.util.Date;
import java.util.List;

/**
 * Created by alexey_n on 09.06.2016.
 */
public class Main {
    public static void main(String[] args) {

//        обход всех новых сайтов (обработка только сайтмап и запись страниц в базу)
//        addAllNewSites();


//        это кол-во страниц на каждом сайте, которые хотим спарсить (это самые свежие страницы)
//        фактически костыль, запускаем один раз, можно несколько подходов, смотрит только те что еще не обходил
//         (в секунду примерно 3-4 страницы) оцени по времени на сколько хочешь запустить работу
//        int count = 50;
//        parseNewestPage(count);
    }

    private static void parseNewestPage(int count) {
        List<Site> sites = SiteDao.getAllSites();
        List<Person> persons = PersonDao.getAllPerson();
        for (Site site : sites) {
            int idSite = site.getId();
            List<Page> pages = PageDao.getNewestPageForSite(idSite, count);
            for (Page page : pages) {
                workWithPage(page,persons);
            }
        }
    }

    private static void addSite(int idSite) {
        WorkWithSitemap workWithSitemap = new WorkWithSitemap(idSite);
        workWithSitemap.start();
        setStartDateStatisticsForSite(idSite);
    }

    private static void setStartDateStatisticsForSite(int idSite) {
        Date startStatistics = PageDao.getMinimumDateForSite(idSite);
        Site site = SiteDao.getSite(idSite);
        if (startStatistics == null) {
            site.setStartDateStatistics(new Date());
        } else {
            site.setStartDateStatistics(startStatistics);
        }
        SiteDao.updateSite(site);
    }

    private static List<Site> getAllNewSites() {
        return SiteDao.getAllNewSites();
    }

    private static void addAllNewSites() {
        List<Site> newSites = getAllNewSites();
        for (Site site : newSites) {
            addSite(site.getId());
        }
    }

    private static void workWithPage(Page page, List<Person> persons) {
        String url = page.getUrl();
        int pageId = page.getId();
        String html = Downloader.downloadHTML(url);
        if (html != null) {
            updatePage(url, pageId, html);
            for (Person person : persons) {
                int idPerson = person.getId();
                int rank = WorkWithHTML.search(html, idPerson);
                PersonPageRank personPageRank = PersonPageRankDao.getByPersonPage(idPerson, pageId);
                if (personPageRank == null) {
                    personPageRank = new PersonPageRank();
                    personPageRank.setPageId(pageId);
                    personPageRank.setPersonId(idPerson);
                }
                personPageRank.setRank(rank);
                PersonPageRankDao.saveOrUpdate(personPageRank);
            }
        } else {
            //todo прописать если html не получили
        }
    }

    private static void updatePage(String url, int pageId, String html) {
        Date lastModified = Downloader.getLastModified(url);
        Page page = PageDao.getPageById(pageId);
        if (lastModified != null) {
            page.setFoundDateTime(lastModified);
        }
        page.setHtml(html);
        page.setLastScanDate(new Date());
        PageDao.updatePage(page);
    }
}
