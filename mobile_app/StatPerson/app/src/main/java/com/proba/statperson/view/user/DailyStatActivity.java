package com.proba.statperson.view.user;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DailyStatActivity extends AppCompatActivity {

    public String from_date;
    public String to_date;
    TextView textViewPersonName;
    TextView textViewSiteName;
    TextView textViewDateFrom;
    TextView textViewDateTill;
    int DIALOG_DATE_FROM = 1;
    int DIALOG_DATE_TILL = 2;
    GregorianCalendar calendarFrom;
    GregorianCalendar calendarTill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_stat);

        textViewPersonName = (TextView) findViewById(R.id.textViewPersonName);
        textViewSiteName = (TextView) findViewById(R.id.textViewSiteName);
        textViewDateFrom = (TextView) findViewById(R.id.textViewDateFrom);
        textViewDateTill = (TextView) findViewById(R.id.textViewDateTill);
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

    // определяем текущую дату
    final Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);

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
            month = monthOfYear + 1;
            day = dayOfMonth;
            calendarFrom = new GregorianCalendar(year, month, day);
            textViewDateFrom.setTextSize(20);
            from_date = day + "." + month + "." + year;
            textViewDateFrom.setText(from_date);
        }
    };

    DatePickerDialog.OnDateSetListener callBackTill = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year = year;
            month = monthOfYear + 1;
            day = dayOfMonth;
            calendarTill = new GregorianCalendar(year, month, day);
            if (calendarTill.after(calendarFrom)) {
                textViewDateTill.setTextSize(20);
                to_date = day + "." + month + "." + year;
                textViewDateTill.setText(to_date);
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.from_less_till), Toast.LENGTH_LONG).show();
            }
        }
    };
}
