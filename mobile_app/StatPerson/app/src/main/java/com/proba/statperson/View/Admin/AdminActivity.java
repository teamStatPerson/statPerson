package com.proba.statperson.view.admin;

import android.app.FragmentTransaction;
import android.graphics.Typeface;
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
import android.widget.TextView;
import android.widget.Toast;

import com.proba.statperson.Constants;
import com.proba.statperson.R;
import com.proba.statperson.interfaces.FabProvider;
import com.proba.statperson.interfaces.IPresenter;
import com.proba.statperson.presenter.PresenterImpl;
import com.proba.statperson.view.admin.fragments.FragmentDailyStat;
import com.proba.statperson.view.admin.fragments.FragmentDate;
import com.proba.statperson.view.admin.fragments.FragmentKeyWords;
import com.proba.statperson.view.admin.fragments.FragmentPersons;
import com.proba.statperson.view.admin.fragments.FragmentSites;
import com.proba.statperson.view.admin.fragments.FragmentStatus;
import com.proba.statperson.view.admin.fragments.FragmentTotalStat;
import com.proba.statperson.view.admin.fragments.FragmentUsers;

public class AdminActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FabProvider {

    private IPresenter presenter;

    FragmentPersons fragmentPersons;
    FragmentSites fragmentSites;
    FragmentStatus fragmentStatus;
    FragmentTotalStat fragmentTotalStat;
    FragmentDailyStat fragmentDailyStat;
    FragmentDate fragmentDate;
    FragmentKeyWords fragmentKeyWords;
    FragmentUsers fragmentUsers;
    private FloatingActionButton fab;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        init();
    }

    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fabAdd = (FloatingActionButton) findViewById(R.id.fab_add);
        fab.hide();
        fabAdd.show();
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
    public FloatingActionButton getFloatingActionButton() {
        return fab;
    }



    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView toolbarTitle = null;
        for (int i = 0; i < toolbar.getChildCount(); ++i) {
            View child = toolbar.getChildAt(i);

            // assuming that the title is the first instance of TextView
            // you can also check if the title string matches
            if (child instanceof TextView) {
                toolbarTitle = (TextView) child;
                toolbarTitle.setTypeface(Typeface.createFromAsset(
                        getAssets(), "fonts/plain.ttf"));
                break;
            }
        }
//        Navigator.initToolbar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
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

            getCatalogElements(Constants.SITES_CATALOG_INDEX);

        } else if (id == R.id.persons) {
            fragmentTransaction.replace(R.id.container, fragmentPersons);

            getCatalogElements(Constants.PERSONS_CATALOG_INDEX);

        } else if (id == R.id.total_stat) {
            fragmentTransaction.replace(R.id.container, fragmentTotalStat);
        } else if (id == R.id.daily_stat) {
            fragmentTransaction.replace(R.id.container, fragmentDailyStat);
        } else if (id == R.id.date) {
            fragmentTransaction.replace(R.id.container, fragmentDate);
        } else if (id == R.id.key_words) {
            fragmentTransaction.replace(R.id.container, fragmentKeyWords);

//            getCatalogElements(Constants.PERSONS_CATALOG_INDEX);

        } else if (id == R.id.users) {
            fragmentTransaction.replace(R.id.container, fragmentUsers);
        }
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void getCatalogElements(int catalogIndex) {
        switch (catalogIndex) {
            case Constants.PERSONS_CATALOG_INDEX:
                presenter.adminGetListOfCatalogElements(Constants.PERSONS_CATALOG_INDEX);
                break;
            case Constants.SITES_CATALOG_INDEX:
                presenter.adminGetListOfCatalogElements(Constants.SITES_CATALOG_INDEX);
                break;
        }
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
                                Toast.makeText(getApplicationContext(),
                                        "Вы выбрали Путин",
                                        Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.medvedev:
                                Toast.makeText(getApplicationContext(),
                                        "Вы выбрали Медведев",
                                        Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.navalny:
                                Toast.makeText(getApplicationContext(),
                                        "Вы выбрали Навальный",
                                        Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;
                        }
                    }
                });

        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {

            @Override
            public void onDismiss(PopupMenu menu) {
                Toast.makeText(getApplicationContext(), "onDismiss",
                        Toast.LENGTH_SHORT).show();
            }
        });
        popupMenu.show();
    }
}
