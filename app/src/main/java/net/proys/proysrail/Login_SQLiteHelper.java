package net.proys.proysrail;

import android.content.ContentValues;
import android.content.Context;
import net.proys.proysrail.SQLiteHelper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Proys Yazılım on 16.07.2019.
 */

public class Login_SQLiteHelper extends SQLiteOpenHelper {

    private static class LOGIN_YAPISI{
        private static final String TABLO_ADI = "LOGIN_TABLOSU";
        private static final String ID = "ID";
        private static final String ISIM = "ISIM";
        private static final String GOREV = "GOREV";
        private static final String EMAIL = "EMAIL";
        private static final String PASSWORD = "PASSWORD";
        private static final String FAVORI = "FAVORI";
        private static final String FAVORI_EKIP = "FAVORI_EKIP";
        private static final String FAVORI_GRUP = "FAVORI_GRUP";

        private static final String BILDIRI = "BILDIRI";
    }


    private Context context;
    private static final String VERITABANI_ADI = "LOGIN";
    private static final int VERITABANI_VERSIYON = 1;
    String LOGIN_TABLOSU_OLUSTUMA = "CREATE TABLE " + Login_SQLiteHelper.LOGIN_YAPISI.TABLO_ADI+"("+ Login_SQLiteHelper.LOGIN_YAPISI.ID+" INTEGER PRIMARY KEY , "
            + LOGIN_YAPISI.ISIM + " VARCHAR(255), "
            + LOGIN_YAPISI.GOREV + " VARCHAR(255), "
            + LOGIN_YAPISI.EMAIL + " VARCHAR(255), "
            + LOGIN_YAPISI.PASSWORD+ " VARCHAR(255), "
            + LOGIN_YAPISI.FAVORI + " VARCHAR(255), "
            + LOGIN_YAPISI.BILDIRI + " VARCHAR(255), "
            + LOGIN_YAPISI.FAVORI_EKIP+ " VARCHAR(255), "
            + LOGIN_YAPISI.FAVORI_GRUP+ " VARCHAR(255) "+")";

    public Login_SQLiteHelper(Context context) {
        super(context, VERITABANI_ADI, null, VERITABANI_VERSIYON);
        this.context = context;
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(LOGIN_TABLOSU_OLUSTUMA);
    }
    public void Write(int id,String isim,String gorev,String email,String password,String favori,String bildiri,String favori_ekip,String favori_grup){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Login_SQLiteHelper.LOGIN_YAPISI.ID,id);
        contentValues.put(LOGIN_YAPISI.ISIM,isim);
        contentValues.put(LOGIN_YAPISI.GOREV,gorev);
        contentValues.put(LOGIN_YAPISI.EMAIL,email);
        contentValues.put(LOGIN_YAPISI.PASSWORD,password);
        contentValues.put(LOGIN_YAPISI.FAVORI,favori);
        contentValues.put(LOGIN_YAPISI.BILDIRI,bildiri);
        contentValues.put(LOGIN_YAPISI.FAVORI_EKIP,favori_ekip);
        contentValues.put(LOGIN_YAPISI.FAVORI_GRUP,favori_grup);
        sqLiteDatabase.insert(Login_SQLiteHelper.LOGIN_YAPISI.TABLO_ADI,null,contentValues);
        sqLiteDatabase.close();
    }
    public String[] ReadUsername(String email){

        int id = 0;
        String isim = null;
        String gorev = null;
        String password = null;
        String favori = null;
        String bildiri = null;
        String favori_ekip = null;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {LOGIN_YAPISI.ID,
                LOGIN_YAPISI.ISIM,
                LOGIN_YAPISI.GOREV,
                LOGIN_YAPISI.EMAIL,
                LOGIN_YAPISI.PASSWORD,
                LOGIN_YAPISI.FAVORI,
                LOGIN_YAPISI.BILDIRI,
                LOGIN_YAPISI.FAVORI_EKIP
        };
        String[] selectionArgs = {String.valueOf(email)};
        Cursor cursor = sqLiteDatabase.query(Login_SQLiteHelper.LOGIN_YAPISI.TABLO_ADI,columns, LOGIN_YAPISI.EMAIL+" =?",selectionArgs,null,null,null);
        String[] login_bilgisi = new String[0];
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){

                id = cursor.getInt(cursor.getColumnIndex(LOGIN_YAPISI.ID));
                isim = cursor.getString(cursor.getColumnIndex(LOGIN_YAPISI.ISIM));
                gorev = cursor.getString(cursor.getColumnIndex(LOGIN_YAPISI.GOREV));
                password = cursor.getString(cursor.getColumnIndex(LOGIN_YAPISI.PASSWORD));
                favori = cursor.getString(cursor.getColumnIndex(LOGIN_YAPISI.FAVORI));
                bildiri = cursor.getString(cursor.getColumnIndex(LOGIN_YAPISI.BILDIRI));
                favori_ekip = cursor.getString(cursor.getColumnIndex(LOGIN_YAPISI.FAVORI_EKIP));
                login_bilgisi = new String[]{
                        String.valueOf(cursor.getCount()),
                        String.valueOf(id),
                        isim,
                        gorev,
                        password,
                        favori,
                        bildiri,
                        favori_ekip
                };

            }
        } else {
            login_bilgisi = new String[]{String.valueOf(cursor.getCount())};
        }
        cursor.close();
        sqLiteDatabase.close();
        return login_bilgisi;
    }
    public String[] ReadLoginforFavEkip(String kisi_id){

        int id = 0;
        String isim = null;
        String favori_ekip = null;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {LOGIN_YAPISI.ID,
                LOGIN_YAPISI.ISIM,
                LOGIN_YAPISI.FAVORI_EKIP
        };
        String[] selectionArgs = {String.valueOf(kisi_id)};
        Cursor cursor = sqLiteDatabase.query(Login_SQLiteHelper.LOGIN_YAPISI.TABLO_ADI,columns, LOGIN_YAPISI.ID+" =?",selectionArgs,null,null,null);
        String[] list = new String[0];
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){

                id = cursor.getInt(cursor.getColumnIndex(LOGIN_YAPISI.ID));
                isim = cursor.getString(cursor.getColumnIndex(LOGIN_YAPISI.ISIM));
                favori_ekip = cursor.getString(cursor.getColumnIndex(LOGIN_YAPISI.FAVORI_EKIP));
                list = favori_ekip.split("--");

            }
        } else {
            list = new String[]{String.valueOf(cursor.getCount())};
        }
        cursor.close();
        sqLiteDatabase.close();
        SQLiteHelper database = new SQLiteHelper(context);
        list = database.FavEkiphelperwisim(new LinkedList<>(Arrays.asList(list))).toArray(new String[0]);
        return list;
    }
    public String[] ReadLoginforFavGrup(String kisi_id){

        int id = 0;
        String isim = null;
        String favori_ekip = null;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {LOGIN_YAPISI.ID,
                LOGIN_YAPISI.ISIM,
                LOGIN_YAPISI.FAVORI_GRUP
        };
        String[] selectionArgs = {String.valueOf(kisi_id)};
        Cursor cursor = sqLiteDatabase.query(Login_SQLiteHelper.LOGIN_YAPISI.TABLO_ADI,columns, LOGIN_YAPISI.ID+" =?",selectionArgs,null,null,null);
        String[] list = new String[0];
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){

                id = cursor.getInt(cursor.getColumnIndex(LOGIN_YAPISI.ID));
                isim = cursor.getString(cursor.getColumnIndex(LOGIN_YAPISI.ISIM));
                favori_ekip = cursor.getString(cursor.getColumnIndex(LOGIN_YAPISI.FAVORI_GRUP));
                list = favori_ekip.split("--");

            }
        } else {
            list = new String[]{String.valueOf(cursor.getCount())};
        }
        cursor.close();
        sqLiteDatabase.close();
        SQLiteHelper database = new SQLiteHelper(context);
        list = database.FavEkiphelperwisim(new LinkedList<>(Arrays.asList(list))).toArray(new String[0]);
        return list;
    }



    public String[] ReadwithIdfor1b(int id){

        String email= null;
        String isim = null;
        String gorev = null;
        String password = null;
        String favori = null;
        String bildiri = null;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {LOGIN_YAPISI.ID,
                LOGIN_YAPISI.ISIM,
                LOGIN_YAPISI.GOREV,
                LOGIN_YAPISI.EMAIL,
                LOGIN_YAPISI.PASSWORD,
                LOGIN_YAPISI.FAVORI,
                LOGIN_YAPISI.BILDIRI
        };
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = sqLiteDatabase.query(Login_SQLiteHelper.LOGIN_YAPISI.TABLO_ADI,columns, LOGIN_YAPISI.ID+" =?",selectionArgs,null,null,null);
        String[] bildiri_listesi  = new String[0];
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                bildiri = cursor.getString(cursor.getColumnIndex(LOGIN_YAPISI.BILDIRI));
                bildiri_listesi = bildiri.split("--");
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return bildiri_listesi;
    }


   /* public void Update(id,yenideger){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(kolon,yenideger);
        sqLiteDatabase.update(tabloadi,contentValues,id+ "= ?",new);
        String[] {String.valueOf(id)};
        sqLiteDatabase.close();
        System.out.println("guncelleme");
    }
   public void delete(id){
       SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
       String[] silinecekVeri = {String.valueOf(id)};
       sqLiteDatabase.delete(tableadi,id"+=?",silinecekVeri);
       sqLiteDatabase.close();
       System.out.println("silindi");

   }*/
    
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
