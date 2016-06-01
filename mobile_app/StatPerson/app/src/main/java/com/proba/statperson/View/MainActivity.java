package com.proba.statperson.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;

import com.proba.statperson.R;
import com.proba.statperson.view.admin.AdminActivity;
import com.proba.statperson.view.user.UserActivity;

/**
 * Created by vadik on 28.05.2016.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RadioGroup radioGroup;
        radioGroup = (RadioGroup) findViewById(R.id.rg_choose_role);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                startUserOrAdminActivity(checkedRadioButtonId);
            }
        });
    }

    private void startUserOrAdminActivity(int checkedRadioButtonId) {
        switch (checkedRadioButtonId) {
            case R.id.rb_admin:
                Intent intentAdmin = new Intent(getApplicationContext(), AdminActivity.class);
                startActivity(intentAdmin);
                break;
            case R.id.rb_user:
                Intent intentUser = new Intent(getApplicationContext(), UserActivity.class);
                startActivity(intentUser);
                break;
        }
    }
}
