package pt.ipleiria.estg.dei.foodlyandroid.modelos;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.listeners.LoginListener;
import pt.ipleiria.estg.dei.foodlyandroid.listeners.RestaurantesListener;
import pt.ipleiria.estg.dei.foodlyandroid.utils.GenericUtils;
import pt.ipleiria.estg.dei.foodlyandroid.utils.RestauranteJsonParser;

public class SingletonFoodly {
    private ArrayList<Restaurante> restaurantes;
    private ArrayList<Ingrediente> ingredientes;
    private ArrayList<Ementa> ementas;
    private ArrayList<Review> reviews;

    private static SingletonFoodly instance = null;
    private static RequestQueue volleyQueue;

    private static final int ADICIONAR_BD = 1;
    private static final int EDITAR_BD = 2;
    private static final int REMOVER_BD = 3;
    private FoodlyBDHelper foodlyBDHelper = null;

    private RestaurantesListener restaurantesListener;
    private LoginListener loginListener;

    private static final String mUrlAPILogin = "";
    private static final String mUrlAPIResturantes = "http://192.168.1.8/FoodlyWeb/frontend/web/api/restaurants";

    public static synchronized SingletonFoodly getInstance(Context context) {
        if (instance == null)
            instance = new SingletonFoodly(context);
        volleyQueue = Volley.newRequestQueue(context);
        return instance;
    }

    private SingletonFoodly(Context context) {
        restaurantes = new ArrayList<>();

        foodlyBDHelper = new FoodlyBDHelper(context);
    }

    //region LOGIN
    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    //region API
    public void loginAPI(final String email, final String password, final Context context) {
        StringRequest req = new StringRequest(Request.Method.POST, mUrlAPILogin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String token = GenericUtils.parserJsonLogin(response);

                if (loginListener != null)
                    loginListener.onValidateLogin(token, email);
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
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        volleyQueue.add(req);
    }//endregion
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

    //region API
    public void getAllRestaurantesAPI(final Context context) {
        if (!GenericUtils.isConnectionInternet(context)) {
            Toast.makeText(context, "Não há internet", Toast.LENGTH_SHORT).show();

            if (restaurantesListener != null)
                restaurantesListener.onRefreshListaRestaurantes(foodlyBDHelper.getAllRestaurantesDB());

        } else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIResturantes, null, new Response.Listener<JSONArray>() {
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

    public void adicionarRestauranteAPI(final Restaurante restaurante, final Context context, final String token) {
        StringRequest req = new StringRequest(Request.Method.POST, mUrlAPIResturantes, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Restaurante r = RestauranteJsonParser.parserJsonRestaurante(response);
                onUpdateListaRestaurantesBD(r, ADICIONAR_BD);

                if (restaurantesListener != null)
                    restaurantesListener.onRefreshDetalhes();
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
                params.put("maxPeople", restaurante.getMaxPeople() + "");
                params.put("currentPeople", restaurante.getCurrentPeople() + "");
                params.put("name", restaurante.getName());
                params.put("image", restaurante.getImage());
                params.put("phone", restaurante.getPhone());
                params.put("email", restaurante.getEmail());
                params.put("description", restaurante.getDescription());
                params.put("location", restaurante.getLocation());
                params.put("openingHour", restaurante.getOpeningHour());
                params.put("closingHour", restaurante.getClosingHour());
                params.put("wifiPassword", restaurante.getWifiPassword());
                params.put("allowsPets", restaurante.getAllowsPets() + "");
                params.put("hasVegan", restaurante.getVegan() + "");
                params.put("token", token);
                return params;
            }
        };
        volleyQueue.add(req);
    }

    public void editarRestauranteAPI(final Restaurante restaurante, final Context context, final String token) {
        StringRequest req = new StringRequest(Request.Method.PUT, mUrlAPIResturantes + '/' + restaurante.getRestaurantId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Restaurante r = RestauranteJsonParser.parserJsonRestaurante(response);
                onUpdateListaRestaurantesBD(r, EDITAR_BD);

                if (restaurantesListener != null)
                    restaurantesListener.onRefreshDetalhes();
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
                params.put("maxPeople", restaurante.getMaxPeople() + "");
                params.put("currentPeople", restaurante.getCurrentPeople() + "");
                params.put("name", restaurante.getName());
                params.put("image", restaurante.getImage());
                params.put("phone", restaurante.getPhone());
                params.put("email", restaurante.getEmail());
                params.put("description", restaurante.getDescription());
                params.put("location", restaurante.getLocation());
                params.put("openingHour", restaurante.getOpeningHour());
                params.put("closingHour", restaurante.getClosingHour());
                params.put("wifiPassword", restaurante.getWifiPassword());
                params.put("allowsPets", restaurante.getAllowsPets() + "");
                params.put("hasVegan", restaurante.getVegan() + "");
                params.put("token", token);
                return params;
            }
        };
        volleyQueue.add(req);
    }

    public void removerRestauranteAPI(final Restaurante restaurante, final Context context) {
        StringRequest req = new StringRequest(Request.Method.DELETE, mUrlAPIResturantes + '/' + restaurante.getRestaurantId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onUpdateListaRestaurantesBD(restaurante, REMOVER_BD);

                if (restaurantesListener != null)
                    restaurantesListener.onRefreshDetalhes();
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

    //region BDHelper
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

    public void editarRestauranteBD(Restaurante restaurante) {
        Restaurante restauranteAux = getRestaurante(restaurante.getRestaurantId());
        if (restauranteAux != null)
            foodlyBDHelper.editarRestauranteDB(restauranteAux);
    }

    public void removerRestauranteBD(int id) {
        Restaurante restaurante = getRestaurante(id);
        if (restaurante != null)
            foodlyBDHelper.removerRestauranteDB(id);
    }

    private void onUpdateListaRestaurantesBD(Restaurante restaurante, int operacao) {
        switch (operacao) {
            case ADICIONAR_BD:
                adicionarRestauranteBD(restaurante);
                break;
            case EDITAR_BD:
                editarRestauranteBD(restaurante);
                break;
            case REMOVER_BD:
                removerRestauranteBD(restaurante.getRestaurantId());
                break;
        }
    }
    //endregion
    //endregion

    private void gerarFakeDataIngrediente() {
        ingredientes = new ArrayList<>();
        ingredientes.add(new Ingrediente(1, "sal"));
        ingredientes.add(new Ingrediente(2, "salsa"));
        ingredientes.add(new Ingrediente(3, "frango"));
        ingredientes.add(new Ingrediente(4, "peixe"));
    }

    public ArrayList<Ingrediente> getIngredientes() {
        return new ArrayList<>(ingredientes);
    }

    private void gerarFakeDataEmenta() {
        ementas = new ArrayList<>();
        ementas.add(new Ementa(1, "Pão", 0.50, null));
        ementas.add(new Ementa(2, "Sopa", 1, getIngredientes()));
        ementas.add(new Ementa(3, "Bitoque", 5, getIngredientes()));
        ementas.add(new Ementa(4, "Gelado", 1.20, null));
    }

    public ArrayList<Ementa> getEmentas() {
        return new ArrayList<>(ementas);
    }

    private void gerarFakeDataReviews() {
        reviews = new ArrayList<>();
        reviews.add(new Review(1, R.drawable.gordon, 4.8, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummyLorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummyLorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy", "Pedro", "29/11/2020"));
        reviews.add(new Review(2, R.drawable.gordon, 3, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy", "Joao", "02/10/2020"));
        reviews.add(new Review(3, R.drawable.gordon, 3.5, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy", "Tiago", "10/09/2020"));
        reviews.add(new Review(4, R.drawable.gordon, 2, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy", "Alex", "12/11/2020"));
        reviews.add(new Review(5, R.drawable.gordon, 5, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy", "Ricardo", "20/08/2020"));
    }

    public ArrayList<Review> getReviews() {
        return new ArrayList<>(reviews);
    }

    public Review getReview(int id) {
        for (Review r : reviews)
            if (r.getId() == id)
                return r;
        return null;
    }
}
