package com.proba.statperson.Presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by SAMSUNG on 07.06.2016.
 */
public class InternetConnectionCheck {

    public boolean isInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if ((networkInfo!=null)&&(networkInfo.isConnected())){
            return true;
        }else return false;
    }

}
