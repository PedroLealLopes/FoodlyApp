package pt.ipleiria.estg.dei.foodlyandroid.modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class FoodlyBDHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "foodlyDB";
    private static final int DB_VERSION = 1;

    private static final String TABLE_RESTAURANTES = "Restaurantes";
    private static final String ID_RESTAURANTE = "id";
    private static final String MAX_PESSOAS_RESTAURANTE = "maxPessoas";
    private static final String CURR_PESSOAS_RESTAURANTE = "currPessoas";
    private static final String CAPA_RESTAURANTE = "capa";
    private static final String DESCRICAO_RESTAURANTE = "descricao";
    private static final String LOCALIZACAO_RESTAURANTE = "localizacao";
    private static final String HORA_ABERTURA_RESTAURANTE = "horaAbertura";
    private static final String HORA_FECHO_RESTAURANTE = "horaFecho";
    private static final String WIFI_RESTAURANTE = "wifi";
    private static final String ANIMAIS_RESTAURANTE = "animais";
    private static final String VEGETARIANO_RESTAURANTE = "vegetariano";

    private final SQLiteDatabase db;

    public FoodlyBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateTableRestaurante = "CREATE TABLE " + TABLE_RESTAURANTES + " (" +
                ID_RESTAURANTE + " INTEGER PRIMARY KEY, " +
                MAX_PESSOAS_RESTAURANTE + " INTEGER NOT NULL, " +
                CURR_PESSOAS_RESTAURANTE + " INTEGER NOT NULL, " +
                CAPA_RESTAURANTE + " TEXT, " +
                DESCRICAO_RESTAURANTE + " TEXT NOT NULL, " +
                LOCALIZACAO_RESTAURANTE + " TEXT NOT NULL, " +
                HORA_ABERTURA_RESTAURANTE + " TEXT NOT NULL, " +
                HORA_FECHO_RESTAURANTE + " TEXT NOT NULL, " +
                WIFI_RESTAURANTE + " TEXT NOT NULL, " +
                ANIMAIS_RESTAURANTE + " INTEGER DEFAULT 0 NOT NULL, " +
                VEGETARIANO_RESTAURANTE + " INTEGER DEFAULT 0 NOT NULL );";
        db.execSQL(sqlCreateTableRestaurante);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlDropTableRestaurante = " DROP TABLE IF EXISTS " + TABLE_RESTAURANTES;
        db.execSQL(sqlDropTableRestaurante);
        this.onCreate(db);
    }

    //METODOS CRUD

    public void adicionarResturanteDB(Restaurante restaurante) {
        ContentValues values = new ContentValues();
        values.put(ID_RESTAURANTE, restaurante.getId());
        values.put(MAX_PESSOAS_RESTAURANTE, restaurante.getMaxPessoas());
        values.put(CURR_PESSOAS_RESTAURANTE, restaurante.getCurrPessoas());
        values.put(CAPA_RESTAURANTE, restaurante.getCapa());
        values.put(DESCRICAO_RESTAURANTE, restaurante.getDescricao());
        values.put(LOCALIZACAO_RESTAURANTE, restaurante.getLocalizacao());
        values.put(HORA_ABERTURA_RESTAURANTE, restaurante.getHoraAbertura());
        values.put(HORA_FECHO_RESTAURANTE, restaurante.getHoraFecho());
        values.put(WIFI_RESTAURANTE, restaurante.getWifi());
        values.put(ANIMAIS_RESTAURANTE, restaurante.isAnimais());
        values.put(VEGETARIANO_RESTAURANTE, restaurante.isVegetariano());

        this.db.insert(TABLE_RESTAURANTES, null, values);
    }

    public boolean editarRestauranteDB(Restaurante restaurante) {
        ContentValues values = new ContentValues();
        values.put(ID_RESTAURANTE, restaurante.getId());
        values.put(MAX_PESSOAS_RESTAURANTE, restaurante.getMaxPessoas());
        values.put(CURR_PESSOAS_RESTAURANTE, restaurante.getCurrPessoas());
        values.put(CAPA_RESTAURANTE, restaurante.getCapa());
        values.put(DESCRICAO_RESTAURANTE, restaurante.getDescricao());
        values.put(LOCALIZACAO_RESTAURANTE, restaurante.getLocalizacao());
        values.put(HORA_ABERTURA_RESTAURANTE, restaurante.getHoraAbertura());
        values.put(HORA_FECHO_RESTAURANTE, restaurante.getHoraFecho());
        values.put(WIFI_RESTAURANTE, restaurante.getWifi());
        values.put(ANIMAIS_RESTAURANTE, restaurante.isAnimais());
        values.put(VEGETARIANO_RESTAURANTE, restaurante.isVegetariano());

        return this.db.update(TABLE_RESTAURANTES, values, "id=?", new String[]{restaurante.getId() + ""}) > 0;
    }

    public boolean removerRestauranteDB(int id) {
        return this.db.delete(TABLE_RESTAURANTES, "id=?", new String[]{id + ""}) > 0;
    }

    public void removerAllRestaurantesBD() {
        this.db.delete(TABLE_RESTAURANTES, null, null);
    }

    public ArrayList<Restaurante> getAllRestaurantesDB() {
        ArrayList<Restaurante> restaurantes = new ArrayList<>();
        Cursor cursor = this.db.query(TABLE_RESTAURANTES, new String[]{ID_RESTAURANTE, MAX_PESSOAS_RESTAURANTE, CURR_PESSOAS_RESTAURANTE, CAPA_RESTAURANTE, DESCRICAO_RESTAURANTE,
                        LOCALIZACAO_RESTAURANTE, HORA_ABERTURA_RESTAURANTE, HORA_FECHO_RESTAURANTE, WIFI_RESTAURANTE, ANIMAIS_RESTAURANTE, VEGETARIANO_RESTAURANTE},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Restaurante auxRestaurante = new Restaurante(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),
                        cursor.getString(8), cursor.getInt(9) > 0, cursor.getInt(10) > 0);

                restaurantes.add(auxRestaurante);
            } while (cursor.moveToNext());
        }
        return restaurantes;
    }
}
