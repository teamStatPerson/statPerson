package com.proba.statperson.view.admin;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.proba.statperson.presenter.CatalogElement.CatalogElement;
import com.proba.statperson.presenter.CatalogElement.Person;
import com.proba.statperson.presenter.PresenterImpl;
import com.proba.statperson.R;
import com.proba.statperson.Constants;
import com.proba.statperson.interfaces.IPresenter;
import com.proba.statperson.view.admin.fragments.FragmentDailyStat;
import com.proba.statperson.view.admin.fragments.FragmentDate;
import com.proba.statperson.view.admin.fragments.FragmentKeyWords;
import com.proba.statperson.view.admin.fragments.FragmentPersons;
import com.proba.statperson.view.admin.fragments.FragmentSites;
import com.proba.statperson.view.admin.fragments.FragmentStatus;
import com.proba.statperson.view.admin.fragments.FragmentTotalStat;
import com.proba.statperson.view.admin.fragments.FragmentUsers;

public class AdminActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private IPresenter presenter;

    FragmentPersons fragmentPersons;
    FragmentSites fragmentSites;
    FragmentStatus fragmentStatus;
    FragmentTotalStat fragmentTotalStat;
    FragmentDailyStat fragmentDailyStat;
    FragmentDate fragmentDate;
    FragmentKeyWords fragmentKeyWords;
    FragmentUsers fragmentUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        init();
    }

    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        presenter = new PresenterImpl();

        fragmentPersons = new FragmentPersons();
        fragmentSites = new FragmentSites();
        fragmentStatus = new FragmentStatus();
        fragmentTotalStat = new FragmentTotalStat();
        fragmentDailyStat = new FragmentDailyStat();
        fragmentDate = new FragmentDate();
        fragmentKeyWords = new FragmentKeyWords();
        fragmentUsers = new FragmentUsers();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        if (id == R.id.status) {
            fragmentTransaction.replace(R.id.container, fragmentStatus);
        } else if (id == R.id.sites) {
            fragmentTransaction.replace(R.id.container, fragmentSites);

            getCatalogElements(Constants.SITES_CATALOG_INDEX, null);

        } else if (id == R.id.persons) {
            fragmentTransaction.replace(R.id.container, fragmentPersons);

            getCatalogElements(Constants.PERSONS_CATALOG_INDEX, null);

        } else if (id == R.id.total_stat) {
            fragmentTransaction.replace(R.id.container, fragmentTotalStat);
        } else if (id == R.id.daily_stat) {
            fragmentTransaction.replace(R.id.container, fragmentDailyStat);
        } else if (id == R.id.date) {
            fragmentTransaction.replace(R.id.container, fragmentDate);
        } else if (id == R.id.key_words) {
            fragmentTransaction.replace(R.id.container, fragmentKeyWords);

            getCatalogElements(Constants.PERSONS_CATALOG_INDEX, null);

        } else if (id == R.id.users) {
            fragmentTransaction.replace(R.id.container, fragmentUsers);
        }
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getCatalogElements(int catalogIndex, String param) {
        switch (catalogIndex) {
            case Constants.PERSONS_CATALOG_INDEX:
                presenter.adminGetListOfCatalogElements(Constants.PERSONS_CATALOG_INDEX, null);
                break;
            case Constants.SITES_CATALOG_INDEX:
                presenter.adminGetListOfCatalogElements(Constants.SITES_CATALOG_INDEX, null);
                break;
            case Constants.KEYWORDS_CATALOG_INDEX:
                presenter.adminGetListOfCatalogElements(Constants.KEYWORDS_CATALOG_INDEX, param);
                break;
        }
    }

}
