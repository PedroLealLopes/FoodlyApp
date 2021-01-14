package pt.ipleiria.estg.dei.foodlyandroid.modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class FoodlyBDHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "foodlyDB";
    private static final int DB_VERSION = 2;

    private static final String RESTAURANTS_TABLE = "Restaurants";
    private static final String RESTAURANT_ID = "restaurantId";
    private static final String RESTAURANT_MAX_PEOPLE = "maxPeople";
    private static final String RESTAURANT_CURR_PEOPLE = "currentPeople";
    private static final String RESTAURANT_NAME = "name";
    private static final String RESTAURANT_IMAGE = "image";
    private static final String RESTAURANT_PHONE = "phone";
    private static final String RESTAURANT_EMAIL = "email";
    private static final String RESTAURANT_DESCRIPTION = "description";
    private static final String RESTAURANT_LOCATION = "location";
    private static final String RESTAURANT_OPENING_HOUR = "openingHour";
    private static final String RESTAURANT_CLOSING_HOUR = "closingHour";
    private static final String RESTAURANT_WIFI_PASSWORD = "wifiPassword";
    private static final String RESTAURANT_ALLOWS_PETS = "allowsPets";
    private static final String RESTAURANT_HAS_VEGAN = "hasVegan";

    private final SQLiteDatabase db;

    public FoodlyBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateTableRestaurante = "CREATE TABLE " + RESTAURANTS_TABLE + " (" +
                RESTAURANT_ID + " INTEGER PRIMARY KEY, " +
                RESTAURANT_MAX_PEOPLE + " INTEGER NOT NULL, " +
                RESTAURANT_CURR_PEOPLE + " INTEGER NOT NULL, " +
                RESTAURANT_NAME + " TEXT, " +
                RESTAURANT_IMAGE + " TEXT NOT NULL, " +
                RESTAURANT_PHONE + " TEXT NOT NULL, " +
                RESTAURANT_EMAIL + " TEXT NOT NULL, " +
                RESTAURANT_DESCRIPTION + " TEXT NOT NULL, " +
                RESTAURANT_LOCATION + " TEXT NOT NULL, " +
                RESTAURANT_OPENING_HOUR + " TEXT NOT NULL, " +
                RESTAURANT_CLOSING_HOUR + " TEXT NOT NULL, " +
                RESTAURANT_WIFI_PASSWORD + " TEXT NOT NULL, " +
                RESTAURANT_ALLOWS_PETS + " INTEGER NOT NULL," +
                RESTAURANT_HAS_VEGAN + " INTEGER NOT NULL );";
        db.execSQL(sqlCreateTableRestaurante);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlDropTableRestaurante = " DROP TABLE IF EXISTS " + RESTAURANTS_TABLE;
        db.execSQL(sqlDropTableRestaurante);
        this.onCreate(db);
    }

    //METODOS CRUD

    public void adicionarResturanteDB(Restaurante restaurante) {
        ContentValues values = new ContentValues();
        values.put(RESTAURANT_ID, restaurante.getRestaurantId());
        values.put(RESTAURANT_MAX_PEOPLE, restaurante.getMaxPeople());
        values.put(RESTAURANT_CURR_PEOPLE, restaurante.getCurrentPeople());
        values.put(RESTAURANT_NAME, restaurante.getName());
        values.put(RESTAURANT_IMAGE, restaurante.getImage());
        values.put(RESTAURANT_PHONE, restaurante.getPhone());
        values.put(RESTAURANT_EMAIL, restaurante.getEmail());
        values.put(RESTAURANT_DESCRIPTION, restaurante.getDescription());
        values.put(RESTAURANT_LOCATION, restaurante.getLocation());
        values.put(RESTAURANT_OPENING_HOUR, restaurante.getOpeningHour());
        values.put(RESTAURANT_CLOSING_HOUR, restaurante.getClosingHour());
        values.put(RESTAURANT_WIFI_PASSWORD, restaurante.getWifiPassword());
        values.put(RESTAURANT_ALLOWS_PETS, restaurante.getAllowsPets());
        values.put(RESTAURANT_HAS_VEGAN, restaurante.getVegan());

        this.db.insert(RESTAURANTS_TABLE, null, values);
    }

    public void removerAllRestaurantesBD() {
        this.db.delete(RESTAURANTS_TABLE, null, null);
    }

    public ArrayList<Restaurante> getAllRestaurantesDB() {
        ArrayList<Restaurante> restaurantes = new ArrayList<>();
        Cursor cursor = this.db.query(RESTAURANTS_TABLE, new String[]{RESTAURANT_ID, RESTAURANT_MAX_PEOPLE, RESTAURANT_CURR_PEOPLE, RESTAURANT_NAME, RESTAURANT_IMAGE,
                        RESTAURANT_PHONE, RESTAURANT_EMAIL, RESTAURANT_DESCRIPTION, RESTAURANT_LOCATION, RESTAURANT_OPENING_HOUR, RESTAURANT_CLOSING_HOUR, RESTAURANT_WIFI_PASSWORD, RESTAURANT_ALLOWS_PETS, RESTAURANT_HAS_VEGAN},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Restaurante auxRestaurante = new Restaurante(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),
                        cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11),
                        cursor.getInt(12), cursor.getInt(13));

                restaurantes.add(auxRestaurante);
            } while (cursor.moveToNext());
        }
        return restaurantes;
    }
}
