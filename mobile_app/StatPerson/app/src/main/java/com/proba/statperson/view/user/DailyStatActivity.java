package com.proba.statperson.view.user;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.proba.statperson.R;
import com.proba.statperson.events.SetDateEvent;
import com.proba.statperson.interfaces.DailyStatDate;
import com.proba.statperson.view.user.fragments.DailyStatListFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.GregorianCalendar;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_stat);

        textViewPersonName = (TextView) findViewById(R.id.textViewPersonName);
        textViewSiteName = (TextView) findViewById(R.id.textViewSiteName);
        textViewDateFrom = (TextView) findViewById(R.id.textViewDateFrom);
        textViewDateTill = (TextView) findViewById(R.id.textViewDateTill);

        // определяем текущую дату
        calendarToday = Calendar.getInstance();
        year = calendarToday.get(Calendar.YEAR);
        month = calendarToday.get(Calendar.MONTH);
        day = calendarToday.get(Calendar.DAY_OF_MONTH);
        calendarFrom = calendarToday;
        calendarTill = calendarToday;
        today_date = day + "." + month + "." + year;

        FragmentManager fragmentManager = getSupportFragmentManager();
        DailyStatListFragment dailyStatListFragment = (DailyStatListFragment) fragmentManager
                .findFragmentById(R.id.dailyStatListFragment);

        if (dailyStatListFragment != null && dailyStatListFragment.isInLayout()) {
            dailyStatListFragment.getStartDate(from_date);
//            dailyStatListFragment.getEndDate(to_date);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        // определяем текущую дату
        calendarToday = Calendar.getInstance();
        year = calendarToday.get(Calendar.YEAR);
        month = calendarToday.get(Calendar.MONTH);
        day = calendarToday.get(Calendar.DAY_OF_MONTH);
        calendarFrom = calendarToday;
        calendarTill = calendarToday;
        today_date = day + "." + month + "." + year;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // определяем текущую дату
        calendarToday = Calendar.getInstance();
        year = calendarToday.get(Calendar.YEAR);
        month = calendarToday.get(Calendar.MONTH);
        day = calendarToday.get(Calendar.DAY_OF_MONTH);
        calendarFrom = calendarToday;
        calendarTill = calendarToday;
        today_date = day + "." + month + "." + year;
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

    public void onClickDateFrom(View view) {
        calendarToday = Calendar.getInstance();
        calendarFrom = calendarToday;
        showDialog(DIALOG_DATE_FROM);
    }

    public void onClickDateTill(View view) {
        calendarToday = Calendar.getInstance();
        calendarTill = calendarToday;
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
            if (calendarFrom.before(calendarToday)) {
                textViewDateFrom.setTextSize(20);
                String sday = day + "";
                if (sday.length() < 2) {
                    sday = "0" + sday;
                }
                month = month + 1;
                String smonth = month + "";
                if (smonth.length() < 2) {
                    smonth = "0" + smonth;
                }
                from_date = " " + sday + "." + smonth + "." + year;
                EventBus.getDefault().post(new SetDateEvent(from_date));
                textViewDateFrom.setText(from_date);
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.from_less_today), Toast.LENGTH_LONG).show();
                calendarFrom = calendarToday;
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
//            calendarTill = calendarToday;
            if (calendarTill.before(calendarToday)) {
                if (calendarTill.after(calendarFrom)) {
                    textViewDateTill.setTextSize(20);
                    String sday = day + "";
                    if (sday.length() < 2) {
                        sday = "0" + sday;
                    }
                    month = month + 1;
                    String smonth = month + "";
                    if (smonth.length() < 2) {
                        smonth = "0" + smonth;
                    }
                    to_date = " " + sday + "." + smonth + "." + year;
                    textViewDateTill.setText(to_date);
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
/*
    @Override
    public void dailyStatDate (String date){

        Bundle bundle = new Bundle();
        bundle.putInt(key_from_date, from_date);
        fragment.setArguments(bundle);




    }
*/
    @Override
    public void onDateSelected(String date) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DailyStatListFragment dailyStatListFragment = (DailyStatListFragment) fragmentManager
                .findFragmentById(R.id.dailyStatListFragment);

        if (dailyStatListFragment != null && dailyStatListFragment.isInLayout()) {
            dailyStatListFragment.getStartDate(from_date);
//            dailyStatListFragment.getEndDate(to_date);
        }


    }
}
