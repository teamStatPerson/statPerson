package com.proba.statperson.view.user;

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
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.proba.statperson.events.StatusEvent;
import com.proba.statperson.R;
import com.proba.statperson.interfaces.FabProvider;
import com.proba.statperson.utils.EventBus;
import com.proba.statperson.view.user.fragments.FragmentDailyStat;
import com.proba.statperson.view.user.fragments.FragmentDate;
import com.proba.statperson.view.user.fragments.FragmentKeyWords;
import com.proba.statperson.view.user.fragments.FragmentPersons;
import com.proba.statperson.view.user.fragments.FragmentSites;
import com.proba.statperson.view.user.fragments.FragmentStatus;
import com.proba.statperson.view.user.fragments.FragmentTotalStat;
import com.proba.statperson.view.user.fragments.FragmentUsers;
import com.squareup.otto.Subscribe;

public class UserActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentPersons fragmentPersons;
    FragmentSites fragmentSites;
    FragmentStatus fragmentStatus;
    FragmentTotalStat fragmentTotalStat;
    FragmentDailyStat fragmentDailyStat;
    FragmentDate fragmentDate;
    FragmentKeyWords fragmentKeyWords;
    FragmentUsers fragmentUsers;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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

        if (id == R.id.sites) {
            fragmentTransaction.replace(R.id.container, fragmentSites);
        } else if (id == R.id.persons) {
            fragmentTransaction.replace(R.id.container, fragmentPersons);
        } else if (id == R.id.total_stat) {
            fragmentTransaction.replace(R.id.container, fragmentTotalStat);
        } else if (id == R.id.daily_stat) {
            fragmentTransaction.replace(R.id.container, fragmentDailyStat);
        } else if (id == R.id.date) {
            fragmentTransaction.replace(R.id.container, fragmentDate);
        } else if (id == R.id.key_words) {
            fragmentTransaction.replace(R.id.container, fragmentKeyWords);
        }
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getInstance().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getInstance().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onStatusEvent(StatusEvent event) {
        fab = ((FabProvider) this).getFloatingActionButton();
        if (event.equals("user")) {
            fab.hide();
        } else {
            fab.show();
        }
    }
}
