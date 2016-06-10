package crauler;

import crauler.work.WorkWithSitemap;

/**
 * Created by alexey_n on 09.06.2016.
 */
public class Main {
    public static void main(String[] args) {

        //добавление сайта по id (обработка только сайтмап и запись страниц в базу)
        addSite(1);

    }

    private static void addSite(int idSite) {
        WorkWithSitemap workWithSitemap = new WorkWithSitemap(idSite);
        workWithSitemap.start();
    }

}
