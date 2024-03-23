package com.example.baivolley.api;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.baivolley.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class SignupApi {

    public static void signUp(
            Context context, String name, String email, String password, Uri imageUri,
            final SignupListener listener
    ) {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || imageUri == null) {
            listener.onSignupError("All fields are required");
            return;
        }

        // Convert Uri to Bitmap
        Bitmap bitmap = /* Convert your Uri to Bitmap */ uriToBitmap(context, imageUri);

        // Convert Bitmap to Base64 string
        String imageString = bitmapToBase64(bitmap);

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                JSONObject userJson = obj.getJSONObject("user");
                                User user = new User(
                                        userJson.getInt("id"),
                                        userJson.getString("username"),
                                        userJson.getString("email"),
                                        userJson.getString("gender")
//                                        userJson.getString("images")
                                );
                                listener.onSignupSuccess(user);
                            } else {
                                listener.onSignupError(obj.getString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            listener.onSignupError("JSON parsing error");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onSignupError("Signup request failed");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", name);
                params.put("email", email);
                params.put("password", password);
                params.put("gender", "nam");
                params.put("images", imageString);
                return params;
            }
        };

        VolleySingle.getInstance(context)
                    .addToRequestQueue(stringRequest);
    }

    private static String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return android.util.Base64.encodeToString(byteArray, android.util.Base64.DEFAULT);
    }

    private static Bitmap uriToBitmap(Context context, Uri uri) {
        try {
            return android.provider.MediaStore.Images.Media.getBitmap(
                    context.getContentResolver(),
                    uri
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public interface SignupListener {
        void onSignupSuccess(User user);

        void onSignupError(String errorMessage);
    }
}
