package interfaces;

import entity.Keywords;

import java.util.List;

/**
 * Created by alexey_n on 02.06.2016.
 */
public interface KeywordsInterface extends BaseInterface<Keywords>  {

    public List<Keywords> getKeywordsByIDPerson(int personId);
}
