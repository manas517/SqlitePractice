package com.nayanatech.sqlitedatabasedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nayanatech.sqlitedatabasedemo.Database.MyLocalDb;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText enter_data;
    TextView show_data;
    Button submit;
    Button show_data_btn;
    Button clear_btn;
    MyLocalDb myLocalDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myLocalDb = new MyLocalDb(this);
        findViewById();
        initEventListener();
    }

    private void initEventListener() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!enter_data.getText().toString().isEmpty()) {
                    myLocalDb.insertToMyPersonalInfo(enter_data.getText().toString());
                    enter_data.getText().clear();
                    Toast.makeText(getApplicationContext(), "Name sunbmitted.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        show_data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myLocalDb.getDataFromMyPersonalInfo().size() > 0) {
                    show_data.setVisibility(View.VISIBLE);
                    ArrayList<String> nameList = new ArrayList<>();
                    nameList = myLocalDb.getDataFromMyPersonalInfo();
                    String nametext="";
                    for (String namestr : nameList) {
                        nametext=nametext+namestr+"\n";
                    }
                    show_data.setText(nametext);
                }else {
                    Toast.makeText(getApplicationContext(),"No data found ",Toast.LENGTH_SHORT).show();
                }
            }
        });
        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myLocalDb.clearMyPersonalInfo();
            }
        });
    }

    private void findViewById() {
        enter_data = (EditText) findViewById(R.id.et_enter_data);
        show_data = (TextView) findViewById(R.id.tv_show_data);
        submit = (Button) findViewById(R.id.btn_submit);
        show_data_btn = (Button) findViewById(R.id.btn_show_data);
        clear_btn = (Button) findViewById(R.id.btn_clear_data);
    }
}
