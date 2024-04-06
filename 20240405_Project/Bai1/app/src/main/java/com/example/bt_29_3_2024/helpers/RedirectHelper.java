package com.example.bt_29_3_2024.helpers;

import android.content.Context;
import android.content.Intent;

public interface RedirectHelper {

    // This method is used to redirect to another activity
    public static void redirect(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    // This method is used to redirect to another activity with data Integer
    public static void redirect(Context context, Class<?> cls, String key, Integer value) {
        Intent intent = new Intent(context, cls);
        intent.putExtra(key, value);
        context.startActivity(intent);
    }

    // This method is used to redirect to another activity with data String and Integer
    public static void redirect(
            Context context, Class<?> cls, String key, Integer value, String key2, String value2
    ) {
        Intent intent = new Intent(context, cls);
        intent.putExtra(key, value);
        intent.putExtra(key2, value2);
        context.startActivity(intent);
    }
}
