package com.example.bt_29_3_2024.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.bt_29_3_2024.R;
import com.example.bt_29_3_2024.api.APIService;
import com.example.bt_29_3_2024.api.RetrofitClient;
import com.example.bt_29_3_2024.models.NewMeal;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    APIService apiService;
    ImageView imageViewDetail;
    TextView textViewPrice, textViewMeal, textViewArea, textViewCategory, textViewInstructions;
    NewMeal newMeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setUpUI();

        addNewMeal(getIntent().getIntExtra("id", -1));
    }

    public void setUpUI() {
        // Hook UI elements
        imageViewDetail = findViewById(R.id.img_detail);
        textViewPrice = findViewById(R.id.tv_price);
        textViewInstructions = findViewById(R.id.tv_desc);
    }

    private void addNewMeal(int id) {
        // get new meal by id

        apiService = RetrofitClient.getRetrofit()
                                   .create(APIService.class);

        apiService.getNewMeals(id)
                  .enqueue(new Callback<ResponseBody>() {
                      @Override
                      public void onResponse(
                              @NonNull Call<ResponseBody> call,
                              @NonNull Response<ResponseBody> response
                      ) {
                          if (response.isSuccessful()) {
                              try {
                                  String res = response.body()
                                                       .string();

                                  // get json object from response
                                  JSONObject jsonObject =
                                          new JSONObject(res).getJSONArray("result")
                                                             .getJSONObject(0);

                                  // get new meal from json object
                                  newMeal = new NewMeal(
                                          jsonObject.getString("id"),
                                          jsonObject.getString("meal"),
                                          jsonObject.getString("area"),
                                          jsonObject.getString("category"),
                                          jsonObject.getString("instructions"),
                                          jsonObject.getString("strmealthumb"),
                                          jsonObject.getString("price")
                                  );

                                  textViewInstructions.setText(newMeal.getInstructions());

                                  Glide.with(DetailActivity.this)
                                       .load(newMeal.getStrmealthumb())
                                       .into(imageViewDetail);

                                  textViewPrice.setText(newMeal.getPrice());
                                  
                              } catch (IOException | JSONException e) {
                                  e.printStackTrace();
                              }
                          } else {
                              int statusCode = response.code();
                              Log.e("Error", "onResponse: " + statusCode);
                          }
                      }

                      @Override
                      public void onFailure(
                              @NonNull Call<ResponseBody> call,
                              @NonNull Throwable t
                      ) {
                          Log.e("Error", "onFailure: " + t.getMessage());
                      }
                  });
    }
}