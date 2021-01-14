package pt.ipleiria.estg.dei.foodlyandroid.utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

public class GenericUtils{

    public static boolean isConnectionInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();

        return ni != null && ni.isConnected();
    }

    public static String parserJsonLogin(String response) {
        String token = null;
        try {
            JSONObject login = new JSONObject(response);
            if (login.getBoolean("success"))
                token = login.getString("token");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return token;
    }
}
