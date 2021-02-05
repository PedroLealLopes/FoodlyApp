package pt.ipleiria.estg.dei.foodlyandroid.utils;

import org.json.JSONException;
import org.json.JSONObject;

import pt.ipleiria.estg.dei.foodlyandroid.modelos.Profile;

public class ProfileJsonParser {

    public static Profile parserJsonProfiles(JSONObject response) {
        Profile profile = new Profile(0, "", "", "", "", "", "", "", "", "");
        if (response != null) {
            try {

                profile.setProfileId(response.getInt("id"));
                profile.setFullname(response.getString("fullname"));
                profile.setAge(response.getString("age"));
                profile.setAlergias(response.getString("alergias"));
                profile.setGenero(response.getString("genero"));
                profile.setTelefone(response.getString("telefone"));
                profile.setMorada(response.getString("morada"));
                profile.setUsername(response.getString("username"));
                profile.setEmail(response.getString("email"));
                profile.setImage(response.getString("image"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return profile;
    }
}
