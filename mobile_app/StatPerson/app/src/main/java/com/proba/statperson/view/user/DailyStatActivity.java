package com.proba.statperson.view.user;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.proba.statperson.Constants;
import com.proba.statperson.R;
import com.proba.statperson.events.NewCatalogElementsListEvent;
import com.proba.statperson.events.SetDateFromEvent;
import com.proba.statperson.events.SetDateTillEvent;
import com.proba.statperson.interfaces.DailyStatDate;
import com.proba.statperson.interfaces.IPresenter;
import com.proba.statperson.presenter.PresenterImpl;
import com.proba.statperson.view.user.fragments.DailyStatListFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;
import java.util.GregorianCalendar;

import statPerson.element.person.Person;
import statPerson.element.site.Site;

public class DailyStatActivity extends AppCompatActivity implements DailyStatDate {

    public String from_date;
    public String to_date;
    public String today_date;
    TextView textViewPersonName;
    TextView textViewSiteName;
    TextView textViewDateFrom;
    TextView textViewDateTill;
    int DIALOG_DATE_FROM = 1;
    int DIALOG_DATE_TILL = 2;
    Calendar calendarFrom;
    Calendar calendarTill;
    Calendar calendarToday;
    private int year;
    private int month;
    private int day;

    private IPresenter presenter;
    private String[] sites;
    private String[] persons;

    private FloatingActionButton fab;
    public static boolean isSitesListRetrieved;
    public static String siteName;
    public static String personName;
    public static boolean isPersonChosen;
    public static boolean isSiteChosen;
    public static boolean isDateFromChosen;
    public static boolean isDateTillChosen;
    private static final String KEY_SITE_NAME = "SITE_NAME";
    private static final String KEY_PERSON_NAME = "PERSON_NAME";
    private static final String KEY_DATE_FROM = "DATE_FROM";
    private static final String KEY_DATE_TO = "DATE_TO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_stat);

        init();
        determineCurrentDate();

        if (savedInstanceState != null) {
            siteName = savedInstanceState.getString(KEY_SITE_NAME, getString(R.string.fragment_sites));
            personName = savedInstanceState.getString(KEY_PERSON_NAME, getString(R.string.fragment_sites));
            from_date = savedInstanceState.getString(KEY_DATE_FROM, "");
            to_date = savedInstanceState.getString(KEY_DATE_TO, "");
            textViewSiteName.setText(siteName);
            textViewPersonName.setText(personName);
            textViewDateFrom.setText(from_date);
            textViewDateTill.setText(to_date);
            initFAB();
            FragmentManager fragmentManager = getSupportFragmentManager();
            DailyStatListFragment dailyStatListFragment = (DailyStatListFragment) fragmentManager
                    .findFragmentById(R.id.dailyStatListFragment);

        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_SITE_NAME, siteName);
        outState.putString(KEY_PERSON_NAME, personName);
        outState.putString(KEY_DATE_FROM, from_date);
        outState.putString(KEY_DATE_TO, to_date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_daily_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_total_stat) {
            Intent intent = new Intent(DailyStatActivity.this, UserActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.daily_stat_graph) {
            Intent intent = new Intent(DailyStatActivity.this, DailyStatGraphView.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        isPersonChosen = false;
        isSiteChosen = false;
        isDateFromChosen = false;
        isDateTillChosen = false;

        fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setVisibility(View.INVISIBLE);
        }

        textViewPersonName = (TextView) findViewById(R.id.textViewPersonName);
        textViewSiteName = (TextView) findViewById(R.id.textViewSiteName);
        textViewDateFrom = (TextView) findViewById(R.id.textViewDateFrom);
        textViewDateTill = (TextView) findViewById(R.id.textViewDateTill);

        textViewPersonName.setVisibility(View.INVISIBLE);
        textViewSiteName.setVisibility(View.INVISIBLE);
        textViewDateFrom.setVisibility(View.INVISIBLE);
        textViewDateTill.setVisibility(View.INVISIBLE);
        findViewById(R.id.textViewSite).setVisibility(View.INVISIBLE);
        findViewById(R.id.textViewPerson).setVisibility(View.INVISIBLE);
        findViewById(R.id.linearLayoutPeriodFrom).setVisibility(View.INVISIBLE);
        findViewById(R.id.linearLayoutPeriodTill).setVisibility(View.INVISIBLE);

        isSitesListRetrieved = false;
        presenter = new PresenterImpl();
        presenter.adminGetListOfCatalogElements(Constants.SITES_CATALOG_INDEX, null);
    }

    private void determineCurrentDate() {
        calendarToday = Calendar.getInstance();
        year = calendarToday.get(Calendar.YEAR);
        month = calendarToday.get(Calendar.MONTH);
        day = calendarToday.get(Calendar.DAY_OF_MONTH);
        calendarFrom = calendarToday;
        calendarTill = calendarToday;
        today_date = day + "." + month + "." + year;
    }

    @Subscribe
    public void displayCatalogElements(NewCatalogElementsListEvent catalogElements) {
        if (isSitesListRetrieved) {
            isSitesListRetrieved = false;
            removeProgressBar();
            persons = catalogElements.message;
            textViewPersonName.setVisibility(View.VISIBLE);
            textViewDateFrom.setVisibility(View.VISIBLE);
            textViewDateTill.setVisibility(View.VISIBLE);
            findViewById(R.id.textViewPerson).setVisibility(View.VISIBLE);
            findViewById(R.id.linearLayoutPeriodFrom).setVisibility(View.VISIBLE);
            findViewById(R.id.linearLayoutPeriodTill).setVisibility(View.VISIBLE);
            setOnClickListenerOnPersonsPopup();
        } else {
            isSitesListRetrieved = true;
            sites = catalogElements.message;
            textViewSiteName.setVisibility(View.VISIBLE);
            findViewById(R.id.textViewSite).setVisibility(View.VISIBLE);
            setOnClickListenerOnSitesPopup();

            if (presenter == null) {
                presenter = new PresenterImpl();
            }
            presenter.adminGetListOfCatalogElements(Constants.PERSONS_CATALOG_INDEX, null);
        }
    }

    public void removeProgressBar() {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar3);
        progressBar.setVisibility(View.GONE);
    }

    private void setProgressBar() {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar3);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void setOnClickListenerOnPersonsPopup() {
        TextView tvChoosePerson = (TextView) findViewById(R.id.textViewPersonName);
        TextView tvChoosePerson2 = (TextView) findViewById(R.id.textViewPerson);

        tvChoosePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenuPersons(v);
            }
        });

        tvChoosePerson2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenuPersons(v);
            }
        });
    }

    public void showPopupMenuPersons(View v) {
        PopupMenu popupMenu = populatePersonsPopupMenu(new PopupMenu(this, v));

        popupMenu
                .setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        showPersonName(item.getTitle().toString());
                        personName = item.getTitle().toString();
                        isPersonChosen = true;
                        checkIfItIsPossibleToInitFab();
//                        setProgressBar();
//                        Person person = new Person(item.getTitle().toString());
//                        Toast.makeText(getActivity(), person.getName(), Toast.LENGTH_SHORT).show();

//                        ((AdminActivity) getActivity()).
//                                getCatalogElements(Constants.KEYWORDS_CATALOG_INDEX, item.getTitle().toString());

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

    private PopupMenu populatePersonsPopupMenu(PopupMenu popupMenu) {
        popupMenu.inflate(R.menu.popupmenu_persons);
        popupMenu.getMenu().clear();

        for (String person : persons) {
            popupMenu.getMenu().add(person);
        }

        return popupMenu;
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


    public void showPersonName(String data) {
        textViewPersonName.setText(data);
    }

    public void showSiteName(String data) {
        textViewSiteName.setText(data);
    }


    public void showPopupMenuSites(View v) {
        PopupMenu popupMenu = populateSitesPopupMenu(new PopupMenu(this, v));

        popupMenu
                .setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        showSiteName(item.getTitle().toString());
                        siteName = item.getTitle().toString();
                        isSiteChosen = true;
                        checkIfItIsPossibleToInitFab();
//                        setProgressBar();
//                        Person person = new Person(item.getTitle().toString());
//                        Toast.makeText(getActivity(), person.getName(), Toast.LENGTH_SHORT).show();

//                        ((AdminActivity) getActivity()).
//                                getCatalogElements(Constants.KEYWORDS_CATALOG_INDEX, item.getTitle().toString());

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

    private void checkIfItIsPossibleToInitFab() {
        if (isSiteChosen && isPersonChosen && isDateFromChosen && isDateTillChosen) {
            initFAB();
        }
    }

    private PopupMenu populateSitesPopupMenu(PopupMenu popupMenu) {
        popupMenu.inflate(R.menu.popupmenu_sites);
        popupMenu.getMenu().clear();

        for (String site : sites) {
            popupMenu.getMenu().add(site);
        }

        return popupMenu;
    }

    public void onClickDateFrom(View view) {
        showDialog(DIALOG_DATE_FROM);
    }

    public void onClickDateTill(View view) {
        showDialog(DIALOG_DATE_TILL);
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DATE_FROM) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, callBackFrom, year, month, day);
            return datePickerDialog;
        } else if (id == DIALOG_DATE_TILL) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, callBackTill, year, month, day);
            return datePickerDialog;
        }
        return super.onCreateDialog(id);
    }

    DatePickerDialog.OnDateSetListener callBackFrom = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year = year;
            month = monthOfYear;
            day = dayOfMonth;
            calendarFrom = new GregorianCalendar(year, month, day);
            if (calendarFrom.before(Constants.INITDATE)) {
                Toast.makeText(getApplicationContext(), getString(R.string.from_less_initdate), Toast.LENGTH_LONG).show();
            } else {
                if (calendarFrom.before(calendarToday)) {
                    textViewDateFrom.setTextSize(20);
                    String sday = DateFormater.DayFormater(day);
                    String smonth = DateFormater.MonthFormater(month);
                    from_date = " " + sday + "." + smonth + "." + year;
                    EventBus.getDefault().post(new SetDateFromEvent(from_date));
                    textViewDateFrom.setText(from_date);
                    isDateFromChosen = true;
                    checkIfItIsPossibleToInitFab();
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.from_less_today), Toast.LENGTH_LONG).show();
                    calendarFrom = calendarToday;
                }
            }
        }
    };

    DatePickerDialog.OnDateSetListener callBackTill = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year = year;
            month = monthOfYear;
            day = dayOfMonth;
            calendarTill = new GregorianCalendar(year, month, day);
            if (calendarTill.before(calendarToday)) {
                if (calendarTill.after(calendarFrom)) {
                    textViewDateTill.setTextSize(20);
                    String sday = DateFormater.DayFormater(day);
                    String smonth = DateFormater.MonthFormater(month);
                    to_date = " " + sday + "." + smonth + "." + year;
                    EventBus.getDefault().post(new SetDateTillEvent(to_date));
                    textViewDateTill.setText(to_date);
                    isDateTillChosen = true;
                    checkIfItIsPossibleToInitFab();
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.from_less_till), Toast.LENGTH_LONG).show();
                    calendarTill = calendarFrom;
                }
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.till_less_today), Toast.LENGTH_LONG).show();
                calendarTill = calendarToday;
            }
        }
    };

    public void initFAB() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setProgressBar();
                presenter.userGetDailyStatistics(new Site(siteName, null), new Person(personName), to_date, from_date);
            }
        });
    }

    @Override
    public void onDateSelected(String date) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DailyStatListFragment dailyStatListFragment = (DailyStatListFragment) fragmentManager
                .findFragmentById(R.id.dailyStatListFragment);

        if (dailyStatListFragment != null && dailyStatListFragment.isInLayout()) {
            dailyStatListFragment.getStartDate(from_date);
        }
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
