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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.listeners.EmentasListener;
import pt.ipleiria.estg.dei.foodlyandroid.listeners.ItensPedidosListener;
import pt.ipleiria.estg.dei.foodlyandroid.listeners.LoginListener;
import pt.ipleiria.estg.dei.foodlyandroid.listeners.PedidosListener;
import pt.ipleiria.estg.dei.foodlyandroid.listeners.ProfileListener;
import pt.ipleiria.estg.dei.foodlyandroid.listeners.RestaurantesListener;
import pt.ipleiria.estg.dei.foodlyandroid.listeners.ReviewsListener;
import pt.ipleiria.estg.dei.foodlyandroid.utils.EmentaJsonParser;
import pt.ipleiria.estg.dei.foodlyandroid.utils.GenericUtils;
import pt.ipleiria.estg.dei.foodlyandroid.utils.PedidoJsonParser;
import pt.ipleiria.estg.dei.foodlyandroid.utils.ProfileJsonParser;
import pt.ipleiria.estg.dei.foodlyandroid.utils.RestauranteJsonParser;
import pt.ipleiria.estg.dei.foodlyandroid.utils.ReviewJsonParser;

public class SingletonFoodly {
    private ArrayList<Restaurante> restaurantes;
    private ArrayList<Ementa> ementas;
    private ArrayList<Restaurante> favRestaurants;
    private ArrayList<Review> reviewsUsers;
    private ArrayList<Review> reviews;
    private ArrayList<Ementa> orderItems;
    private ArrayList<Pedido> pedidos;

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
    private ProfileListener profileListener;
    private ReviewsListener reviewsListener;
    private ItensPedidosListener itensPedidosListener;
    private PedidosListener pedidosListener;

    private static final String IP_MiiTU = "192.168.1.8";
    private static final String IP_Luckdude = "192.168.1.229";
    private static final String IP_Johnny = "192.168.1.253";
    private static final String IP_PC_ESCOLA = "";
    private static final String mUrlAPI = "http://" + IP_PC_ESCOLA + "/FoodlyWeb/frontend/web/api";

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
        pedidos = new ArrayList<>();

        foodlyBDHelper = new FoodlyBDHelper(context);
    }

    public String getUrlAPI() {
        return mUrlAPI;
    }

    //region LOGIN
    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    public void loginAPI(final String username, final String password, final Context context) {
        if (!GenericUtils.isConnectionInternet(context)) {
            Toast.makeText(context, R.string.noConnection, Toast.LENGTH_SHORT).show();

            if (loginListener != null)
                loginListener.onValidateLogin(false, null);
        }
        StringRequest req = new StringRequest(Request.Method.POST, mUrlAPI + "/users/login", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject profileResponse = new JSONObject(response);
                    if (profileResponse.getInt("id") >= 0) {
                        setProfile(ProfileJsonParser.parserJsonProfiles(profileResponse));
                        if (profileListener != null)
                            profileListener.onRefreshProfile(profile);

                        loginListener.onValidateLogin(true, profileResponse);
                    } else {
                        loginListener.onValidateLogin(false, null);
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

    //region REGISTAR USER
    public void registarUtilizadorAPI(final Profile profile, final Context context) {
        StringRequest req = new StringRequest(Request.Method.POST, mUrlAPI + "/users/register", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (profileListener != null)
                    profileListener.onRefreshDetalhes();
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
                params.put("password", profile.getPassword());
                params.put("username", profile.getUsername());
                params.put("fullname", profile.getFullname());
                params.put("age", profile.getAge());
                params.put("alergias", profile.getAlergias());
                params.put("telefone", profile.getTelefone());
                params.put("morada", profile.getMorada());
                params.put("genero", profile.getGenero());
                params.put("email", profile.getEmail());
                System.out.println("--->params " + params.toString());
                return params;
            }
        };
        volleyQueue.add(req);
    }
    //endregion

    //region PROFILE
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Profile getProfile() {
        return this.profile;
    }

    public int getProfileId() {
        return getProfile().getProfileId();
    }

    public void editProfileAPI(final String fullname, final String age, final String alergias, final String genero, final String telefone, final String morada, final Context context) {
        StringRequest req = new StringRequest(Request.Method.PUT, mUrlAPI + "/profiles/" + profile.getProfileId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                profile.setFullname(fullname);
                profile.setAge(age);
                profile.setAlergias(alergias);
                profile.setGenero(genero);
                profile.setTelefone(telefone);
                profile.setMorada(morada);

                if (profileListener != null)
                    profileListener.onRefreshProfile(profile);
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
                params.put("fullname", fullname);
                params.put("age", age);
                params.put("alergias", alergias);
                params.put("genero", genero);
                params.put("telefone", telefone);
                params.put("morada", morada);
                return params;
            }
        };
        volleyQueue.add(req);
    }

    public void adicionarImagemApi(final String image, final Context context) {
        StringRequest req = new StringRequest(Request.Method.PUT, mUrlAPI + "/profiles/" + profile.getProfileId() + "/upload", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                profile.setImage(image);

                if (profileListener != null)
                    profileListener.onRefreshProfile(profile);
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
                params.put("image", image);
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

    public void getAllRestaurantesAPI(final Context context) {
        if (!GenericUtils.isConnectionInternet(context)) {
            Toast.makeText(context, R.string.noConnection, Toast.LENGTH_SHORT).show();

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

    //BD HELPER
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

    //region EMENTA
    public void setEmentasListener(EmentasListener ementasListener) {
        this.ementasListener = ementasListener;
    }

    public void setProfileListener(ProfileListener profileListener) {
        this.profileListener = profileListener;
    }

    public Ementa getEmenta(int id) {
        for (Ementa e : ementas)
            if (e.getDishId() == id)
                return e;
        return null;
    }

    public ArrayList<Ementa> getEmentaType(int restaurantId) {
        ArrayList<Ementa> foo = new ArrayList<>();
        for (Ementa e : ementas) {
            if (e.getRestaurantId() == restaurantId)
                foo.add(e);
        }
        return foo;
    }

    public void getAllEmentasAPI(final int restaurantId, final Context context) {
        if (!GenericUtils.isConnectionInternet(context)) {
            Toast.makeText(context, R.string.noConnection, Toast.LENGTH_SHORT).show();
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

    //region FAVORITOS
    public void setFavRestaurants(ArrayList<Restaurante> restaurants) {
        this.favRestaurants = restaurants;
    }

    public ArrayList<Restaurante> getFavRestaurants() {
        return this.favRestaurants;
    }

    public void getAllFavoritosAPI(final Context context) {
        if (!GenericUtils.isConnectionInternet(context)) {
            Toast.makeText(context, R.string.noConnection, Toast.LENGTH_SHORT).show();
        } else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPI + "/profile-restaurant-favorites/user/" + getProfileId(), null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        ArrayList<Restaurante> restaurantesfavoritos = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject restaurante = (JSONObject) response.get(i);
                                restaurantesfavoritos.add(SingletonFoodly.getInstance(context).getRestaurante(restaurante.getInt("restaurant_restaurantId")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        setFavRestaurants(restaurantesfavoritos);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

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

    public void adicionarFavoritoAPI(final int profiles_userId, final int restaurant_restaurantId, final Context context) {
        StringRequest req = new StringRequest(Request.Method.POST, mUrlAPI + "/profile-restaurant-favorites", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
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
                params.put("profiles_userId", profiles_userId + "");
                params.put("restaurant_restaurantId", restaurant_restaurantId + "");
                return params;
            }
        };
        volleyQueue.add(req);
    }

    public void removerFavoritoAPI(final int restaurant_id, final Context context) {
        StringRequest req = new StringRequest(Request.Method.POST, mUrlAPI + "/profile-restaurant-favorites/delete", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
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
                params.put("restaurant_id", restaurant_id + "");
                params.put("profile_id", getProfileId() + "");
                return params;
            }
        };
        volleyQueue.add(req);
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
            Toast.makeText(context, R.string.noConnection, Toast.LENGTH_SHORT).show();
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

    public void adicionarReviewAPI(final Review review, final int restaurantId, final Context context) {
        StringRequest req = new StringRequest(Request.Method.POST, mUrlAPI + "/restaurant-reviews", new Response.Listener<String>() {
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
                params.put("restaurant_restaurantId", restaurantId + "");
                params.put("profiles_userId", getProfileId() + "");
                params.put("stars", review.getStars() + "");
                params.put("comment", review.getComment());
                return params;
            }
        };
        volleyQueue.add(req);
    }
    //endregion

    //region REVIEWS_USER
    public void getAllReviewsUsersAPI(final Context context) {

        if (!GenericUtils.isConnectionInternet(context)) {
            Toast.makeText(context, R.string.noConnection, Toast.LENGTH_SHORT).show();
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

    public void removerReviewUserAPI(final Review review, final Context context) {
        StringRequest req = new StringRequest(Request.Method.POST, mUrlAPI + "/restaurant-reviews/delete", new Response.Listener<String>() {
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
                params.put("restaurant_id", review.getRestaurantId() + "");
                params.put("profile_id", review.getProfileId() + "");
                return params;
            }
        };
        volleyQueue.add(req);
    }
    //endregion

    //region PEDIDOS

    public void setPedidosListener(PedidosListener pedidosListener) {
        this.pedidosListener = pedidosListener;
    }

    public void inicializarListaPedido() {
        orderItems = new ArrayList<>();
    }

    public ArrayList<Ementa> setListaPedido(ArrayList<Ementa> orderItems) {
        this.orderItems = orderItems;
        return orderItems;
    }

    public ArrayList<Ementa> getListaPedido() {
        return orderItems;
    }

    public void getAllPedidosAPI(final Context context) {
        if (!GenericUtils.isConnectionInternet(context)) {
            Toast.makeText(context, R.string.noConnection, Toast.LENGTH_SHORT).show();
        } else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPI + "/orders", null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    pedidos = PedidoJsonParser.parserJsonPedidos(response);

                    if (pedidosListener != null)
                        pedidosListener.onRefreshListaPedidos(pedidos);
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

    public void adicionarPedidoAPI(final Context context) {
        StringRequest req = new StringRequest(Request.Method.POST, mUrlAPI + "/orders/create", new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                if (pedidosListener != null)
                    pedidosListener.onRefreshDetalhes(Integer.parseInt(response));

                final ArrayList<Ementa> listaEmenta = getListaPedido();

                for (int i = 0; i < listaEmenta.size(); i++) {
                    final int j = i;
                    StringRequest req2 = new StringRequest(Request.Method.POST, mUrlAPI + "/order-items/create", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (itensPedidosListener != null)
                                itensPedidosListener.onRefreshDetalhes();
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
                            params.put("orderId", Integer.parseInt(response) + "");
                            params.put("dishId", listaEmenta.get(j).getDishId() + "");
                            params.put("quantity", listaEmenta.get(j).getQuantity() + "");
                            return params;
                        }
                    };
                    volleyQueue.add(req2);
                }
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
                params.put("userId", getProfileId() + "");
                return params;
            }
        };
        volleyQueue.add(req);
    }
    //endregion
}
