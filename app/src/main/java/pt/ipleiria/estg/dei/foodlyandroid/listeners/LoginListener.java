package pt.ipleiria.estg.dei.foodlyandroid.listeners;

import org.json.JSONObject;

public interface LoginListener {
    void onValidateLogin(boolean canLogin, JSONObject profile);
}
