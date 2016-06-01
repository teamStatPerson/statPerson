package com.proba.statperson.view.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.proba.statperson.R;

public class DailyStatActivity extends AppCompatActivity {

    TextView textViewPersonName;
    TextView textViewSiteName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_stat);

        textViewPersonName = (TextView) findViewById(R.id.textViewPersonName);
        textViewSiteName = (TextView) findViewById(R.id.textViewSiteName);
    }

    public void onClickPerson(View view) {
        showPopupMenuPersons(view);
    }

    private void showPopupMenuPersons(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.popupmenu_persons);

        popupMenu
                .setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {

                            case R.id.putin:
                                showPersonName(" " + getString(R.string.putin));
                                return true;
                            case R.id.medvedev:
                                showPersonName(" " + getString(R.string.medvedev));
                                return true;
                            case R.id.navalny:
                                showPersonName(" " + getString(R.string.navalny));
                                return true;
                            default:
                                return false;
                        }
                    }
                });

        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {

            @Override
            public void onDismiss(PopupMenu menu) {
//                Toast.makeText(getApplicationContext(), "onDismiss",
//                        Toast.LENGTH_SHORT).show();
            }
        });
        popupMenu.show();
    }

    public void showPersonName(String data) {
        textViewPersonName.setText(data);
    }

    public void showSiteName(String data) {
        textViewSiteName.setText(data);
    }

    public void onClickSite(View view) {
        showPopupMenuSites(view);
    }
    private void showPopupMenuSites(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.popupmenu_sites);

        popupMenu
                .setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {

                            case R.id.lenta:
                                showSiteName(" " + getString(R.string.site_lenta));
                                return true;
                            case R.id.test:
                                showSiteName(" " + getString(R.string.site_test));
                                return true;
                            case R.id.sample:
                                showSiteName(" " + getString(R.string.site_sample));
                                return true;
                            default:
                                return false;
                        }
                    }
                });

        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {

            @Override
            public void onDismiss(PopupMenu menu) {
//                Toast.makeText(getApplicationContext(), "onDismiss",
//                        Toast.LENGTH_SHORT).show();
            }
        });
        popupMenu.show();
    }
}
