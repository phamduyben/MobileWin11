package com.example.baivolley.contronller;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.baivolley.R;
import com.example.baivolley.api.Constants;
import com.example.baivolley.api.VolleySingle;
import com.example.baivolley.model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
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
    ProgressBar progressBar;

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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
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
            validateLogin();
        });
    }

    public void validateLogin() {
        String email = edtEmail.getText()
                               .toString();

        if (TextUtils.isEmpty(email)) {
            edtEmail.setError("Email is required");
            edtEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            textInputEditText.setError("Password is required");
            textInputEditText.requestFocus();
            return;
        }
        //if everything is fine
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);
                        try {
//converting response to json object
                            JSONObject obj = new JSONObject(response);
//if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(
                                             getApplicationContext(),
                                             obj.getString(
                                                     "message"),
                                             Toast.LENGTH_SHORT
                                     )
                                     .show();
//getting the user from the response
                                JSONObject userJson = obj.getJSONObject("user");
                                //creating a new user object
                                User user = new User(
                                        userJson.getInt("id"),
                                        userJson.getString("username"),
                                        userJson.getString("email"),
                                        userJson.getString("gender"),
                                        userJson.getString("images")
                                );

//storing the user in shared preferences
                                SharedPrefManager.getInstance(getApplicationContext())
                                                 .userLogin(user);
//starting the profile activity
                                finish();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(
                                             getApplicationContext(),
                                             obj.getString(
                                                     "message"),
                                             Toast.LENGTH_SHORT
                                     )
                                     .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(
                                     getApplicationContext(),
                                     error.getMessage(),
                                     Toast.LENGTH_SHORT
                             )
                             .show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", email);
                params.put("password", password);
                return params;
            }
        };
        VolleySingle.getInstance(this)
                    .addToRequestQueue(stringRequest);
    }

    public void temp() {
        //            if (email.equals("admin") && password.equals("Admin12345@")) {
//
//                intentLogin = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intentLogin);
//
//            } else {
//                Toast.makeText(
//                             this,
//                             "sai tai khoan or pass word",
//                             Toast.LENGTH_LONG
//                     )
//                     .show();
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }

    public void setupUI() {
        if (SharedPrefManager.getInstance(this)
                             .isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
            return;
        }
        progressBar = findViewById(R.id.progressBar);
        edtEmail = findViewById(R.id.editText_email);
        textInputLayout = findViewById(R.id.editText_password);
        textInputEditText = findViewById(R.id.textinput_password);
        imvLogin = findViewById(R.id.imageView_login);
        tvRegister = findViewById(R.id.textView_register);
    }

}