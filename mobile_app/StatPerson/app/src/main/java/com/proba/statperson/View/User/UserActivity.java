package com.proba.statperson.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.proba.statperson.Constants;
import com.proba.statperson.R;
import com.proba.statperson.events.NewCatalogElementsListEvent;
import com.proba.statperson.interfaces.IPresenter;
import com.proba.statperson.presenter.PresenterImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class UserActivity extends AppCompatActivity {

    private IPresenter presenter;

    private FloatingActionButton fab;
    TextView textViewPersonName;
    TextView textViewSiteName;

    private String[] sites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        init();
    }

    private void init() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new PresenterImpl();
        presenter.adminGetListOfCatalogElements(Constants.SITES_CATALOG_INDEX, null);

//        textViewPersonName = (TextView) findViewById(R.id.textViewPersonName);
        textViewSiteName = (TextView) findViewById(R.id.textViewSiteName);

        findViewById(R.id.tableTotalStat).setVisibility(View.GONE);

        findViewById(R.id.textViewSite).setVisibility(View.INVISIBLE);
        findViewById(R.id.textViewSiteName).setVisibility(View.INVISIBLE);

    }

    @Subscribe
    public void displayCatalogElements(NewCatalogElementsListEvent catalogElements) {
        removeProgressBar();

        sites = catalogElements.message;

        findViewById(R.id.textViewSite).setVisibility(View.VISIBLE);
        findViewById(R.id.textViewSiteName).setVisibility(View.VISIBLE);

        setOnClickListenerOnSitesPopup();
    }

    private void setOnClickListenerOnSitesPopup() {
        TextView tvChooseSite = (TextView) findViewById(R.id.textViewSite);
        TextView tvChooseSite2 = (TextView) findViewById(R.id.textViewSiteName);

        tvChooseSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenuSites(v);
            }
        });

        tvChooseSite2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenuSites(v);
            }
        });
    }

    public void showPopupMenuSites(View v) {
        PopupMenu popupMenu = populatePopupMenu(new PopupMenu(this, v));

        popupMenu
                .setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        showSiteName(item.getTitle().toString());
//                        setProgressBar();
//                        Person person = new Person(item.getTitle().toString());
//                        Toast.makeText(getActivity(), person.getName(), Toast.LENGTH_SHORT).show();

//                        ((AdminActivity) getActivity()).
//                                getCatalogElements(Constants.KEYWORDS_CATALOG_INDEX, item.getTitle().toString());

                        initFAB();
                        return false;
                    }
                });

        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {

            @Override
            public void onDismiss(PopupMenu menu) {

            }
        });
        popupMenu.show();
    }

    private void initFAB() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "FAB", Toast.LENGTH_SHORT).show();

//                Intent intent = new Intent(UserActivity.this, DailyStatActivity.class);
//                startActivity(intent);
            }
        });
    }

    private PopupMenu populatePopupMenu(PopupMenu popupMenu) {
        popupMenu.inflate(R.menu.popupmenu_sites);
        popupMenu.getMenu().clear();

        for (String site : sites) {
            popupMenu.getMenu().add(site);
        }

        return popupMenu;
    }

    private void removeProgressBar() {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_daily_stat) {
            Intent intent = new Intent(UserActivity.this, DailyStatActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showSiteName(String data) {
        textViewSiteName.setText(data);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
