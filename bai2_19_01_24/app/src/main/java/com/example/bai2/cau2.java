package com.example.bai2;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class cau2 extends AppCompatActivity {
    EditText tx1, tx2;
    TextView txtAlert, txtal;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow()
            .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                      WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau2);
        init();
        process();
    }

    public void init() {
        tx1 = (EditText) findViewById(R.id.editTextText);
        tx2 = (EditText) findViewById(R.id.editTextText2);
        txtAlert = (TextView) findViewById(R.id.txtalert);
        txtal = (TextView) findViewById(R.id.textView5);
        btnLogin = (Button) findViewById(R.id.button);
    }

    public void process() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tx1.getText()
                       .toString()
                       .equals("admin") && tx2.getText()
                                              .toString()
                                              .equals("admin")) {
                    txtAlert.setText("Login successful");
                    tx1.setHint("");
                    tx2.setHint("");
                    tx1.setVisibility(View.GONE);
                    tx2.setVisibility(View.GONE);
                    btnLogin.setVisibility(View.GONE);
                    txtal.setVisibility(View.GONE);
                } else {
                    if (!tx1.getText()
                            .toString()
                            .equals("admin") && tx2.getText()
                                                   .toString()
                                                   .equals("admin")) {
                        txtal.setText("Login failed" + " " + "Please enter account");
                        tx1.setText("");
                    } else if (!tx2.getText()
                                   .toString()
                                   .equals("admin") && tx1.getText()
                                                          .toString()
                                                          .equals("admin")) {
                        txtal.setText("Login failed" + " " + "Please enter password");
                        tx2.setText("");
                    } else {
                        txtal.setText("login failed");
                        tx1.setText("");
                        tx2.setText("");
                    }
                }
            }
        });
    }
}