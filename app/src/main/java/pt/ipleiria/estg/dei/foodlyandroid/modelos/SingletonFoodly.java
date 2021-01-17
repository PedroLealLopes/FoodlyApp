package pt.ipleiria.estg.dei.foodlyandroid.modelos;

import android.content.Context;
import android.speech.RecognitionService;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pt.ipleiria.estg.dei.foodlyandroid.listeners.EmentasListener;
import pt.ipleiria.estg.dei.foodlyandroid.listeners.LoginListener;
import pt.ipleiria.estg.dei.foodlyandroid.listeners.RestaurantesListener;
import pt.ipleiria.estg.dei.foodlyandroid.listeners.ReviewsListener;
import pt.ipleiria.estg.dei.foodlyandroid.utils.EmentaJsonParser;
import pt.ipleiria.estg.dei.foodlyandroid.utils.GenericUtils;
import pt.ipleiria.estg.dei.foodlyandroid.utils.ProfileJsonParser;
import pt.ipleiria.estg.dei.foodlyandroid.utils.RestauranteJsonParser;
import pt.ipleiria.estg.dei.foodlyandroid.utils.ReviewJsonParser;

public class SingletonFoodly {
    private ArrayList<Restaurante> restaurantes;
    private ArrayList<Ementa> ementas;
    private ArrayList<Restaurante> favRestaurants;
    private ArrayList<Review> reviewsUsers;
    private ArrayList<Review> reviews;

    private static SingletonFoodly instance = null;
    private static RequestQueue volleyQueue;

    private static final int ADICIONAR_BD = 1;
    private static final int EDITAR_BD = 2;
    private static final int REMOVER_BD = 3;
    private FoodlyBDHelper foodlyBDHelper = null;
    public Profile profile;
    public Review review;

    private RestaurantesListener restaurantesListener;
    private EmentasListener ementasListener;
    private LoginListener loginListener;
    private ReviewsListener reviewsListener;

    private static final String IP_MiiTU = "192.168.1.8";
    private static final String IP_Luckdude = "192.168.1.229";
    private static final String IP_Johnny = "192.168.1.253";
    private static final String mUrlAPI = "http://"+ IP_MiiTU +"/FoodlyWeb/frontend/web/api";
    private static final String mUrlAPILogin = "http://"+ IP_MiiTU +"/FoodlyWeb/frontend/web/api/users/login";

    public static synchronized SingletonFoodly getInstance(Context context) {
        if (instance == null)
            instance = new SingletonFoodly(context);
        volleyQueue = Volley.newRequestQueue(context);
        return instance;
    }

    private SingletonFoodly(Context context) {
        restaurantes = new ArrayList<>();
        ementas = new ArrayList<>();
        favRestaurants = new ArrayList<>();
        reviews = new ArrayList<>();
        reviewsUsers = new ArrayList<>();

        foodlyBDHelper = new FoodlyBDHelper(context);
    }

    public String getUrlAPI(){
        return mUrlAPI;
    }

    //region LOGIN
    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    public void setProfile(Profile profile){
        this.profile = profile;
    }

    public Profile getProfile(){
        return this.profile;
    }

    public int getProfileId(){
        return getProfile().getProfileId();
    }

    //region LOGIN_API
    public void loginAPI(final String username, final String password, final Context context) {
        StringRequest req = new StringRequest(Request.Method.POST, mUrlAPILogin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject profileResponse = new JSONObject(response);
                    if(profileResponse.getInt("id") >= 0){
                        setProfile(ProfileJsonParser.parserJsonProfiles(profileResponse));
                        loginListener.onValidateLogin(true, profileResponse.getString("username"));
                    }
                    else{
                        loginListener.onValidateLogin(false, "");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };
        volleyQueue.add(req);
    }
    //endregion

    //region RESTAURANTES
    public void setRestaurantesListener(RestaurantesListener restaurantesListener) {
        this.restaurantesListener = restaurantesListener;
    }

    public Restaurante getRestaurante(int id) {
        for (Restaurante r : restaurantes)
            if (r.getRestaurantId() == id)
                return r;
        return null;
    }

    //region RESTAURANTES_API
    public void getAllRestaurantesAPI(final Context context) {
        if (!GenericUtils.isConnectionInternet(context)) {
            Toast.makeText(context, "Não há internet", Toast.LENGTH_SHORT).show();

            if (restaurantesListener != null)
                restaurantesListener.onRefreshListaRestaurantes(foodlyBDHelper.getAllRestaurantesDB());

        } else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPI + "/restaurants", null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    restaurantes = RestauranteJsonParser.parserJsonRestaurantes(response);
                    adicionarRestaurantesBD(restaurantes);

                    if (restaurantesListener != null)
                        restaurantesListener.onRefreshListaRestaurantes(restaurantes);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            volleyQueue.add(req);
        }
    }
    //endregion

    //region RESTAURANTES_BDHelper
    public ArrayList<Restaurante> getRestaurantesBD() {
        restaurantes = foodlyBDHelper.getAllRestaurantesDB();
        return restaurantes;
    }

    public void adicionarRestauranteBD(Restaurante restaurante) {
        foodlyBDHelper.adicionarResturanteDB(restaurante);
    }

    public void adicionarRestaurantesBD(ArrayList<Restaurante> restaurantes) {
        foodlyBDHelper.removerAllRestaurantesBD();
        for (Restaurante r : restaurantes)
            adicionarRestauranteBD(r);
    }
    //endregion
    //endregion

    //region EMENTA
    public void setEmentasListener(EmentasListener ementasListener) {
        this.ementasListener = ementasListener;
    }

    public Ementa getEmenta(int id) {
        for (Ementa e : ementas)
            if (e.getDishId() == id)
                return e;
        return null;
    }

    public ArrayList<Ementa> getEmentaType(int restaurantId) {
        ArrayList<Ementa> foo = new ArrayList<>();
        for (Ementa e : ementas){
            if(e.getRestaurantId() == restaurantId)
                foo.add(e);
        }
        return foo;
    }

    //region EMENTA_API
    public void getAllEmentasAPI(final int restaurantId, final Context context) {

        if (!GenericUtils.isConnectionInternet(context)) {
            Toast.makeText(context, "Não há internet", Toast.LENGTH_SHORT).show();
        } else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPI + "/dishes/restaurant/" + restaurantId, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    ementas = EmentaJsonParser.parserJsonEmentas(response, restaurantId);

                    if (ementasListener != null)
                        ementasListener.onRefreshListaEmentas(ementas);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            volleyQueue.add(req);
        }
    }
    //endregion
    //endregion

    //region FAVORITOS

    public void setFavRestaurants(ArrayList<Restaurante> restaurants){
        this.favRestaurants = restaurants;
    }

    public ArrayList<Restaurante> getFavRestaurants(){
        return this.favRestaurants;
    }

    public void getAllFavoritosAPI(final Context context) {

        if (!GenericUtils.isConnectionInternet(context)) {
            Toast.makeText(context, "Não há internet", Toast.LENGTH_SHORT).show();
        } else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPI + "/profile-restaurant-favorites/user/" + getProfileId(), null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    ArrayList<Restaurante> restaurantesfavoritos = new ArrayList<>();

                    for( int i = 0 ; i < response.length(); i++)
                    {
                        try {
                            JSONObject restaurante = (JSONObject) response.get(i);
                            restaurantesfavoritos.add(SingletonFoodly.getInstance(context).getRestaurante(restaurante.getInt("restaurant_restaurantId")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    setFavRestaurants(restaurantesfavoritos);
                    if (restaurantesListener != null)
                        restaurantesListener.onRefreshListaRestaurantes(restaurantes);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            volleyQueue.add(req);
        }
    }
    //endregion

    //region REVIEWS

    public void setReviewsListener(ReviewsListener reviewsListener) {
        this.reviewsListener = reviewsListener;
    }

    public Review getReview(int id) {
        for (Review r : reviews)
            if (r.getRestaurantId() == id)
                return r;
        return null;
    }

    public void getAllReviewsAPI(final int restaurantId, final Context context) {

        if (!GenericUtils.isConnectionInternet(context)) {
            Toast.makeText(context, "Não há internet", Toast.LENGTH_SHORT).show();
        } else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPI + "/restaurant-reviews/restaurant/" + restaurantId, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    reviews = ReviewJsonParser.parserJsonReviews(response);

                    if (reviewsListener != null)
                        reviewsListener.onRefreshListaReviews(reviews);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            volleyQueue.add(req);
        }
    }

    public void adicionarReviewAPI(final Review review, final int restaurantId, final Context context, final String token) {
        StringRequest req = new StringRequest(Request.Method.POST, mUrlAPI + "/restaurant-reviews/restaurant/" + restaurantId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (reviewsListener != null)
                    reviewsListener.onRefreshDetalhes();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("restaurant_restaurantId", restaurantId+"");
                params.put("profiles_userId", getProfile()+"");
                params.put("stars", review.getStars()+"");
                params.put("comment", review.getComment()+"");
                params.put("creation_date", review.getCreation_date());
                params.put("username", review.getUsername());
                params.put("image", review.getImage());
                params.put("token", token);
                return params;
            }
        };
        volleyQueue.add(req);
    }
    //endregion

    //region REVIEWS_USER
    public void getAllReviewsUsersAPI(final Context context) {

        if (!GenericUtils.isConnectionInternet(context)) {
            Toast.makeText(context, "Não há internet", Toast.LENGTH_SHORT).show();
        } else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPI + "/restaurant-reviews/user/" + getProfileId(), null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    reviews = ReviewJsonParser.parserJsonReviews(response);

                    if (reviewsListener != null)
                        reviewsListener.onRefreshListaReviews(reviews);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            volleyQueue.add(req);
        }
    }

    public void removerReviewUserAPI(Review review, final Context context) {
        StringRequest req = new StringRequest(Request.Method.DELETE, mUrlAPI + "/restaurant-reviews/restaurant/" + getProfileId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (reviewsListener != null)
                    reviewsListener.onRefreshDetalhes();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        volleyQueue.add(req);
    }
    //endregion
}
