package com.example.bai2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    EditText edtEmail;
    ImageButton imvLogin;
    TextView tvRegister;
    Intent intentLogin, intentRegister;

    TextInputLayout textInputLayout;
    TextInputEditText textInputEditText;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupUI();
        setupProcess();
    }

    private void setupProcess() {
        setupLogin();
        setupRegister();
    }

    public void setupRegister() {
        tvRegister.setOnClickListener(v -> {
            intentRegister = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intentRegister);
        });
    }

    public void setupLogin() {
        textInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                    int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                    int count) {
                password = s.toString();

                if (password.length() >= 8) {
                    Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
                    Matcher matcher = pattern.matcher(password);
                    boolean passwordsMatch = matcher.find();
                    if (passwordsMatch) {
                        textInputLayout.setHelperText("Your password are strong");
                        textInputLayout.setError("");
                    } else {
                        textInputLayout.setError(
                                "Mix of letters(upper and lower case), number and symbols");
                    }
                } else {
                    textInputLayout.setHelperText("Password must 8 characters long");
                    textInputLayout.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        imvLogin.setOnClickListener(v -> {
            try {
                String email = edtEmail.getText()
                                       .toString();

                if (email.equals("admin") && password.equals("Admin12345@")) {

                    intentLogin = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intentLogin);

                } else {
                    Toast.makeText(this,
                                   "sai tai khoan or pass word",
                                   Toast.LENGTH_LONG)
                         .show();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public void setupUI() {
        edtEmail = findViewById(R.id.editText_email);
        textInputLayout = findViewById(R.id.editText_password);
        textInputEditText = findViewById(R.id.textinput_password);
        imvLogin = findViewById(R.id.imageView_login);
        tvRegister = findViewById(R.id.textView_register);
    }

}