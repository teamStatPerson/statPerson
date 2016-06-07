package interfaces;

import entity.Sites;

/**
 * Created by alexey_n on 02.06.2016.
 */
public interface SitesInterface extends BaseInterface<Sites> {
    public Sites getSiteByID(int idSite);
}
