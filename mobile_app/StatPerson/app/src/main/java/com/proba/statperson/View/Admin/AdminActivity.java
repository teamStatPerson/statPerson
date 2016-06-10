package com.proba.statperson.view.admin;

import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.proba.statperson.Constants;
import com.proba.statperson.R;
import com.proba.statperson.interfaces.DeleteConfirmListener;
import com.proba.statperson.interfaces.EditorDialogListener;
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
        implements NavigationView.OnNavigationItemSelectedListener,
        DeleteConfirmListener,
        EditorDialogListener {

    private IPresenter presenter;
    public FloatingActionButton fab;

    FragmentPersons fragmentPersons;
    FragmentSites fragmentSites;
    FragmentStatus fragmentStatus;
    FragmentTotalStat fragmentTotalStat;
    FragmentDailyStat fragmentDailyStat;
    FragmentDate fragmentDate;
    FragmentKeyWords fragmentKeyWords;
    FragmentUsers fragmentUsers;

    public static final String CATALOG_INDEX = "catalogIndex";
    public static final String ELEMENT_ID = "elementID";
    public static final String PERSON_ID = "personID";

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

        // TODO: 06.06.2016 do switch, not else if
        if (id == R.id.status) {
            fragmentTransaction.replace(R.id.container, fragmentStatus);
        } else if (id == R.id.sites) {
            fab.setImageResource(R.drawable.ic_add_white_24dp);
            fragmentTransaction.replace(R.id.container, fragmentSites);
            getCatalogElements(Constants.SITES_CATALOG_INDEX, 0);
        } else if (id == R.id.persons) {
            fab.setImageResource(R.drawable.ic_add_white_24dp);
            fragmentTransaction.replace(R.id.container, fragmentPersons);
            getCatalogElements(Constants.PERSONS_CATALOG_INDEX, 0);
        } else if (id == R.id.total_stat) {
            fragmentTransaction.replace(R.id.container, fragmentTotalStat);
        } else if (id == R.id.daily_stat) {
            fragmentTransaction.replace(R.id.container, fragmentDailyStat);
        } else if (id == R.id.date) {
            fragmentTransaction.replace(R.id.container, fragmentDate);
        } else if (id == R.id.key_words) {
            fab.setImageResource(R.drawable.ic_add_white_24dp);
            fragmentTransaction.replace(R.id.container, fragmentKeyWords);
            getCatalogElements(Constants.PERSONS_CATALOG_INDEX, 0);
        } else if (id == R.id.users) {
            fragmentTransaction.replace(R.id.container, fragmentUsers);
        }
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getCatalogElements(int catalogIndex, int personID) {
        switch (catalogIndex) {
            case Constants.PERSONS_CATALOG_INDEX:
                presenter.adminGetListOfCatalogElements(Constants.PERSONS_CATALOG_INDEX, 0);
                break;
            case Constants.SITES_CATALOG_INDEX:
                presenter.adminGetListOfCatalogElements(Constants.SITES_CATALOG_INDEX, 0);
                break;
            case Constants.KEYWORDS_CATALOG_INDEX:
                presenter.adminGetListOfCatalogElements(Constants.KEYWORDS_CATALOG_INDEX, personID);
                break;
        }
    }

    public void addElement(CharSequence input, int catalogIndex, int personId) {
        switch (catalogIndex) {
            case Constants.PERSONS_CATALOG_INDEX:
                presenter.adminAddElement(input.toString(), catalogIndex, 0);
                break;
            case Constants.SITES_CATALOG_INDEX:
                presenter.adminAddElement(input.toString(), catalogIndex, 0);
                break;
            case Constants.KEYWORDS_CATALOG_INDEX:
                presenter.adminAddElement(input.toString(), catalogIndex, personId);
                break;
        }
    }

    public void deleteElement(int elementID, int catalogIndex, int personID) {
        switch (catalogIndex) {
            case Constants.PERSONS_CATALOG_INDEX:
//                                Person person = new Person(elementName);
                presenter.adminDeleteElement(elementID, Constants.PERSONS_CATALOG_INDEX, 0);
                break;
            case Constants.SITES_CATALOG_INDEX:
//                                Site site = new Site(elementName, null);
                presenter.adminDeleteElement(elementID, Constants.SITES_CATALOG_INDEX, 0);
                break;
            case Constants.KEYWORDS_CATALOG_INDEX:
//                                Keyword keyword = new Keyword(elementName, 0);
                presenter.adminDeleteElement(elementID, Constants.KEYWORDS_CATALOG_INDEX, personID);
                break;
        }
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Toast.makeText(this, "Вы подтвердили удаление!", Toast.LENGTH_LONG).show();
        deleteElement(dialog.getArguments().getInt(ELEMENT_ID), dialog.getArguments().getInt(CATALOG_INDEX),
                dialog.getArguments().getInt(PERSON_ID));
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(this, "Вы отменили удаление!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFinishEditDialog(String newElementName, int oldElementID, int catalogIndex, int personID) {
        Toast.makeText(getApplicationContext(), "Вы ввели, " + newElementName, Toast.LENGTH_SHORT).show();

        deleteElement(oldElementID, catalogIndex, personID);
        addElement(newElementName, catalogIndex, personID);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
