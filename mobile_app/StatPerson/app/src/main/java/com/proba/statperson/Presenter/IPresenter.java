package com.proba.statperson.Presenter;

import com.proba.statperson.Presenter.CatalogElement.CatalogElement;

/**
 * Created by vadik on 30.05.2016.
 */
public interface IPresenter {
    void adminGetListOfCatalogElements(int catalogIndex);

    void adminAddElement(String[] elementParameters);

    void adminDeleteElement(int elementIndex);

    void adminEditElement(int elementIndex);

    void adminOnRetrieveListOfCatalogElements (CatalogElement[] elements);
}
