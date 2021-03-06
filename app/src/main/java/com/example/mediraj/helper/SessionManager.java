package com.example.mediraj.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.mediraj.R;
import com.example.mediraj.activity.Welcome;
import com.example.mediraj.webapi.ApiInterface;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SessionManager {
    public static final String PREF_NAME = "Mediraj";
    public static final int MODE = Context.MODE_PRIVATE;


    public static void writeString(Context context, String key, String value) {
        getEditor(context).putString(key, value).commit();

    }

    public static String readString(Context context, String key, String defValue) {
        return getPreferences(context).getString(key, defValue);
    }


    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, MODE);
    }

    public static SharedPreferences.Editor getEditor(Context context) {
        return getPreferences(context).edit();
    }

    public static void logout(final Context context, ApiInterface apiInterface) {
        final Activity activity = (Activity) context;
        DataManager.getInstance().showProgressMessage(activity, activity.getString(R.string.please_wait));
        Call<Map<String,String>> logoutCall = apiInterface.userLogout(Constant.AUTH, DataManager.getInstance().getUserData(activity).data.id);
        logoutCall.enqueue(new Callback<Map<String,String>>() {
            @Override
            public void onResponse(Call<Map<String,String>> call, Response<Map<String,String>> response) {
                DataManager.getInstance().hideProgressMessage();
                try {
                    Map<String,String> data = response.body();
                    if (data.get("response").equals("200")) {
                        getEditor(context).clear().commit();
                        if (context instanceof Activity) {
                            Toast.makeText(activity,data.get("message"),Toast.LENGTH_SHORT).show();
                            context.startActivity(new Intent(activity, Welcome.class));
                            activity.finish();
                        }

                    } else {
                        Toast.makeText(activity,data.get("message"),Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Map<String,String>> call, Throwable t) {
                call.cancel();
                DataManager.getInstance().hideProgressMessage();
            }
        });
    }

    public static void writeBoolean(Context context, String key, boolean value) {
        getEditor(context).putBoolean(key, value).commit();

    }

    public static boolean readBoolean(Context context, String key, boolean defValue) {
        return getPreferences(context).getBoolean(key, defValue);
    }

}
