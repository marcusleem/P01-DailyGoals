package com.marcus.p01_dailygoals;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText et1;
    TextView tv1, tv2, tv3;
    RadioGroup rg1, rg2, rg3;
    Button b1;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.editText);
        tv1 = findViewById(R.id.textView);
        tv2 = findViewById(R.id.textView3);
        tv3 = findViewById(R.id.textView5);
        rg1 = findViewById(R.id.radiogroup1);
        rg2 = findViewById(R.id.radiogroup2);
        rg3 = findViewById(R.id.radiogroup3);
        b1 = findViewById(R.id.buttonOk);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String[] checkedButtonsId = {"c1", "c2", "c3"};
        for (String i: checkedButtonsId) {
            int j = prefs.getInt(i, 0);
            if (j != 0) {
                RadioButton rbc = findViewById(j);
                rbc.setChecked(true);
            }
        }
        et1.setText(prefs.getString("rj", ""));

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rb1 = findViewById(rg1.getCheckedRadioButtonId());
                RadioButton rb2 = findViewById(rg2.getCheckedRadioButtonId());
                RadioButton rb3 = findViewById(rg3.getCheckedRadioButtonId());

                ArrayList<String> info = new ArrayList<>();
                RadioButton[] responses = {rb1, rb2, rb3};
                TextView[] texts = {tv1, tv2, tv3};
                for (int i=0; i<3; i++) {
                    info.add(texts[i].getText().toString() + ": " + responses[i].getText().toString());
                }
                info.add("Reflection: " + et1.getText().toString());

                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putInt("c1", rg1.getCheckedRadioButtonId());
                prefEdit.putInt("c2", rg2.getCheckedRadioButtonId());
                prefEdit.putInt("c3", rg3.getCheckedRadioButtonId());
                prefEdit.putString("rj", et1.getText().toString());
                prefEdit.commit();

                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                i.putExtra("info", info);
                startActivity(i);
            }
        });

    }
}