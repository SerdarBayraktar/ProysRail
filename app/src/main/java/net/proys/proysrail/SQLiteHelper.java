package net.proys.proysrail;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ListView;
import android.widget.RadioButton;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Proys Yazılım on 14.07.2019.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    private static class KITAP_TABLO_YAPISI{
        private static final String TABLO_ADI = "KITAP_BILGI_TABLOSU";
        private static final String ID = "ID";
        private static final String ISIM = "ISIM";
        private static final String HAT = "HAT";
        private static final String HAT_NO = "HAT_NO";
        private static final String KM_BAS = "KM_BAS";
        private static final String KM_BIT = "KM_BIT";
        private static final String AKTIF = "AKTIF";
        private static final String IMALAT = "IMALAT";
        private static final String BOLGE = "BOLGE";}
    private static class TASLAK_RESOURCE_YAPISI{
        private static final String TABLO_ADI = "TASLAK_RESOURCE_TABLOSU";
        private static final String ID = "ID";
        private static final String IMALAT = "IMALAT";
        private static final String TARIH = "TARIH";
        private static final String KAYNAK_ID = "KAYNAK_ID";
        private static final String TIP = "TIP";
        private static final String KATEGORI = "KATEGORI";
        private static final String PUANTAJ = "PUANTAJ";
        private static final String VERIM = "VERIM";
        private static final String SAYI = "SAYI";
    }
    private static class TASLAK_ACIKLAMALAR_YAPISI{
        private static final String TABLO_ADI ="TASLAK_ACIKLAMALAR_TABLOSU";
        private static final String ID = "ID";
        private static final String ACIKLAMA_ID = "ACIKLAMA_ID";
        private static final String TARIH = "TARIH";
        private static final String IMALAT = "IMALAT";
        private static final String ACIKLAMA = "ACIKLAMA";
        private static final String KOPYA_NO = "KOPYA_NO";
    }
    private static class IMALAT_TABLO_YAPISI{
        private static final String TABLO_ADI = "IMALAT_TABLOSU";
        private static final String ID = "ID";
       //private static final String ORDER = "ORDER";
        private static final String ISIM = "ISIM";
        private static final String ONCELIK = "ONCELIK";
        private static final String BIRIM = "BIRIM";
        private static final String BP_AS = "BP_AS";
        private static final String METRAJ = "METRAJ";
        private static final String BILDIRI = "BILDIRI";
        private static final String VT_SEKTOR = "VT_SEKTOR";
        private static final String VT_MESAFE ="VT_MESAFE";
        private static final String KM_AKTIF ="KM_AKTIF";
    }
    private static class TASLAK_TABLO_YAPISI{
        private static final String TABLO_ADI = "TASLAK_TABLOSU";
        private static final String ID = "ID";
        private static final String TARIH = "TARIH";
        private static final String IMALAT = "IMALAT";
        private static final String KOPYA_NO = "KOPYA_NO";
        private static final String SEKTOR = "SEKTOR";
        private static final String HAT_NO = "HAT_NO";
        private static final String KM_BAS = "KM_BAS";
        private static final String KM_SON = "KM_SON";
        private static final String MESAFE = "MESAFE";
        private static final String BIRIM = "BIRIM";
        private static final String SENT = "SENT";
    }
    private static class PERSONEL_TABLO_YAPISI{
        private static final String TABLO_ADI = "PERSONEL_TABLOSU";
        private static final String RESOURCE_ID = "RESOURCE_ID";
        //private static final String ISIM = "ISIM";
        private static final String KISA_ISIM = "KISA_ISIM";
        private static final String KOD = "KOD";
        private static final String KATEGORI = "KATEGORI";
        private static final String TIP = "TIP";
        //private static final String DETAY = "DETAY";
        //private static final String SORUMLU = "SORUMLU";
        private static final String EKIP_ID = "EKIP_ID";
        private static final String VT_PUANTAJ = "VT_PUANTAJ";
        private static final String VT_SAYI = "VT_SAYİ";
        //private static final String IMALAT_ID = "IMALAT_ID";
        //private static final String GIRIS_TARIH = "GIRIS_TARIHI";
        //private static final String CIKIS_TARIH = "CIKIS_TARIHI";

    }
    private static class BILDIRILER_TABLO_YAPISI{
        private static final String TABLO_ADI = "BILDIRILER_TABLOSU";
        private static final String ID = "ID";
        private static final String ISIM = "ISIM";
        private static final String RUTIN = "RUTIN";
        private static final String SIKLIK = "SIKLIK";
        private static final String ISTEK_TARIH = "ISTEK_TARIH";
        private static final String ISTEK_SAAT = "ISTEK_SAAT";
        private static final String TERMIN_TARIH = "TERMIN_TARIH";
        private static final String TERMIN_SAAT = "TERMIN_SAAT";
    }
    private static class VARSAYILAN_IMALAT_TABLO_YAPISI{
        private static final String TABLO_ADI = "VARSAYILAN_IMALAT_TABLOSU";
        private static final String ID = "ID";
        private static final String IMALAT = "IMALAT";
        private static final String SEKTOR = "SEKTOR";
        private static final String MESAFE = "MESAFE";
    }
    private static class BILDIRI_LISTESI_TABLO_YAPISI{
        private static final String TABLO_ADI = "BILDIRI_LISTESI_TABLOSU";
        private static final String KOD = "KOD";
        private static final String KULLANICI_ID = "KULLANICI_ID";
        private static final String BILDIRI_ID = "BILDIRI_ID";
        private static final String ISIM = "ISIM";
        private static final String TARIH = "TARIH";
        private static final String DEADLINE_TARIH = "DEADLINE_TARIH";
        private static final String DEADLINE_SAAT = "DEADLINE_SAAT";
        private static final String SENT = "SENT";
    }
    private static class TASLAK_MEDYA_TABLO_YAPISI{
        private static final String TABLO_ADI = "TASLAK_MEDYA_TABLOSU";
        private static final String BILDIRI_ID = "BILDIRI_ID";
        private static final String TARIH = "TARIH";
        private static final String IMALAT_ID = "IMALAT_ID";
        private static final String MEDYA_LINKI = "MEDYA_LINKI";
        private static final String ETIKETLER = "ETIKETLER";
    }
    private static class TASLAK_VERIMSIZLIK_TABLO_YAPISI{
        private static final String TABLO_ADI = "TASLAK_VERIMSIZLIK_TABLO";
        private static final String BILDIRI_ID = "BILDIRI_ID";
        private static final String TARIH = "TARIH";
        private static final String IMALAT_ID = "IMALAT_ID";
        private static final String ETKEN_ID = "ETKEN_ID";
        private static final String DEGER = "DEGER";
    }
    private static class ETKEN_LISTE_TABLO_YAPISI{
        private static final String TABLO_ADI = "ETKEN_LISTE_TABLOSU";
        private static final String ETKEN_ID = "ETKEN_ID";
        private static final String ISIM = "ISIM";
        private static final String VT_DEGER = "VT_DEGER";
        private static final String RADIO_BUTTON = "RADIO_BUTTON";
    }
    private static class GETTER_SETTER_TABLO_YAPISI{
        private static final String TABLO_ADI = "GETTER_SETTER_TABLOSU";
        private static final String KEY = "KEY";
        private static final String VALUE = "VALUE";
    }
    private Context context;
    Get_Set veri;
    private static final String VERITABANI_ADI = "VERITABANI";
    private static final int VERITABANI_VERSIYON = 1;
    String KITAP_TABLOSU_OLUSTURMA = "CREATE TABLE " + KITAP_TABLO_YAPISI.TABLO_ADI+"("+KITAP_TABLO_YAPISI.ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KITAP_TABLO_YAPISI.ISIM+" VARCHAR(255), "
            + KITAP_TABLO_YAPISI.HAT + " VARCHAR(255), "
            + KITAP_TABLO_YAPISI.HAT_NO+" VARCHAR(255), "
            + KITAP_TABLO_YAPISI.KM_BAS+" INTEGER, "
            + KITAP_TABLO_YAPISI.KM_BIT+" INTEGER, "
            + KITAP_TABLO_YAPISI.AKTIF+" INTEGER, "
            + KITAP_TABLO_YAPISI.IMALAT+" VARCHAR(255), "
            + KITAP_TABLO_YAPISI.BOLGE + " VARCHAR(255)"+")";
    String GETTER_SETTER_TABLOSU_OLUSTURMA = "CREATE TABLE "+GETTER_SETTER_TABLO_YAPISI.TABLO_ADI+"("+GETTER_SETTER_TABLO_YAPISI.KEY +" VARCHAR(255) PRIMARY KEY , "
            + GETTER_SETTER_TABLO_YAPISI.VALUE+ " VARCHAR(255)"+")";
    String IMALAT_TABLOSU_OLUSTURMA = "CREATE TABLE " + IMALAT_TABLO_YAPISI.TABLO_ADI+"("+IMALAT_TABLO_YAPISI.ID+" VARCHAR(255) PRIMARY KEY , "
            //+ IMALAT_TABLO_YAPISI.ORDER +" VARCHAR(255), "
            + IMALAT_TABLO_YAPISI.ISIM + " VARCHAR(255), "
            + IMALAT_TABLO_YAPISI.ONCELIK+" VARCHAR(255), "
            + IMALAT_TABLO_YAPISI.BIRIM+" VARCHAR(255), "
            + IMALAT_TABLO_YAPISI.BP_AS+" REAL, "
            + IMALAT_TABLO_YAPISI.METRAJ+" INTEGER, "
            + IMALAT_TABLO_YAPISI.BILDIRI+" VARCHAR(255) , "
            + IMALAT_TABLO_YAPISI.VT_SEKTOR+" INTEGER, "
            + IMALAT_TABLO_YAPISI.VT_MESAFE+" INTEGER, "
            + IMALAT_TABLO_YAPISI.KM_AKTIF+" INTEGER "+")";
    String TASLAK_TABLOSU_OLUSTURMA = "CREATE TABLE " + TASLAK_TABLO_YAPISI.TABLO_ADI +"("+ TASLAK_TABLO_YAPISI.ID+" LONG , "
            + TASLAK_TABLO_YAPISI.TARIH + " VARCHAR(255), "
            + TASLAK_TABLO_YAPISI.IMALAT + " VARCHAR(255), "
            + TASLAK_TABLO_YAPISI.KOPYA_NO + " INTEGER, "
            + TASLAK_TABLO_YAPISI.SEKTOR + " VARCHAR(255), "
            + TASLAK_TABLO_YAPISI.HAT_NO + " INTEGER, "
            + TASLAK_TABLO_YAPISI.KM_BAS + " INTEGER, "
            + TASLAK_TABLO_YAPISI.KM_SON + " INTEGER, "
            + TASLAK_TABLO_YAPISI.MESAFE + " INTEGER, "
            + TASLAK_TABLO_YAPISI.BIRIM + " VARCHAR(255), "
            + TASLAK_TABLO_YAPISI.SENT + " INTEGER "+")";
    String PERSONEL_TABLOSU_OLUSTURMA = "CREATE TABLE "+ PERSONEL_TABLO_YAPISI.TABLO_ADI + "("+ PERSONEL_TABLO_YAPISI.RESOURCE_ID + " VARCHAR(255) PRIMARY KEY , "
            + PERSONEL_TABLO_YAPISI.KISA_ISIM + " VARCHAR(255), "
            + PERSONEL_TABLO_YAPISI.KOD + " VARCHAR(255), "
            + PERSONEL_TABLO_YAPISI.KATEGORI + " VARCHAR(255), "
            + PERSONEL_TABLO_YAPISI.TIP + " VARCHAR(255), "
            + PERSONEL_TABLO_YAPISI.EKIP_ID + " VARCHAR(255), "
            + PERSONEL_TABLO_YAPISI.VT_PUANTAJ + " INTEGER, "
            + PERSONEL_TABLO_YAPISI.VT_SAYI + " INTEGER "+")";
    String BILDIRILER_TABLOSU_OLUSTURMA = "CREATE TABLE " + BILDIRILER_TABLO_YAPISI.TABLO_ADI+"("+BILDIRILER_TABLO_YAPISI.ID+" VARCHAR(255) PRIMARY KEY , "
            + BILDIRILER_TABLO_YAPISI.ISIM+" VARCHAR(255), "
            + BILDIRILER_TABLO_YAPISI.RUTIN + " INTEGER, "
            + BILDIRILER_TABLO_YAPISI.SIKLIK+" INTEGER, "
            + BILDIRILER_TABLO_YAPISI.ISTEK_TARIH+" INTEGER, "
            + BILDIRILER_TABLO_YAPISI.ISTEK_SAAT+" VARCHAR(255), "
            + BILDIRILER_TABLO_YAPISI.TERMIN_TARIH+" INTEGER, "
            + BILDIRILER_TABLO_YAPISI.TERMIN_SAAT+" VARCHAR(255) " +")";
    String TASLAK_RESOURCE_TABLOSU_OLUSTURMA = "CREATE TABLE " + TASLAK_RESOURCE_YAPISI.TABLO_ADI +"("+ TASLAK_RESOURCE_YAPISI.ID+" LONG , "
            + TASLAK_RESOURCE_YAPISI.TARIH + " VARCHAR(255), "
            + TASLAK_RESOURCE_YAPISI.KAYNAK_ID + " VARCHAR(255), "
            + TASLAK_RESOURCE_YAPISI.KATEGORI + " VARCHAR(255), "
            + TASLAK_RESOURCE_YAPISI.TIP + " VARCHAR(255), "
            + TASLAK_RESOURCE_YAPISI.IMALAT + " VARCHAR(255), "
            + TASLAK_RESOURCE_YAPISI.PUANTAJ + " INTEGER, "
            + TASLAK_RESOURCE_YAPISI.VERIM + " VARCHAR(255), "
            + TASLAK_RESOURCE_YAPISI.SAYI + " INTEGER "+")";
    String TASLAK_ACIKLAMALAR_OLUSTURMA = "CREATE TABLE " + TASLAK_ACIKLAMALAR_YAPISI.TABLO_ADI +"("+ TASLAK_ACIKLAMALAR_YAPISI.ACIKLAMA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TASLAK_ACIKLAMALAR_YAPISI.ID+" VARCHAR(255), "
            + TASLAK_ACIKLAMALAR_YAPISI.TARIH + " VARCHAR(255), "
            + TASLAK_ACIKLAMALAR_YAPISI.IMALAT + " VARCHAR(255), "
            + TASLAK_ACIKLAMALAR_YAPISI.KOPYA_NO + " INTEGER, "
            + TASLAK_ACIKLAMALAR_YAPISI.ACIKLAMA + " TEXT " +")";
    String VARSAYILAN_IMALAT_TABLOSU_OLUSTURMA = "CREATE TABLE " + VARSAYILAN_IMALAT_TABLO_YAPISI.TABLO_ADI +"("+ VARSAYILAN_IMALAT_TABLO_YAPISI.ID+" VARCHAR(255) PRIMARY KEY , "
            + VARSAYILAN_IMALAT_TABLO_YAPISI.IMALAT + " VARCHAR(255), "
            + VARSAYILAN_IMALAT_TABLO_YAPISI.SEKTOR + " VARCHAR(255), "
            + VARSAYILAN_IMALAT_TABLO_YAPISI.MESAFE + " INTEGER " +")";
    String BILDIRI_LISTESI_TABLOSU_OLUSTURMA = "CREATE TABLE " + BILDIRI_LISTESI_TABLO_YAPISI.TABLO_ADI+"("+BILDIRI_LISTESI_TABLO_YAPISI.KOD+" LONG PRIMARY KEY , "
            + BILDIRI_LISTESI_TABLO_YAPISI.KULLANICI_ID+" INTEGER, "
            + BILDIRI_LISTESI_TABLO_YAPISI.BILDIRI_ID + " VARCHAR(255), "
            + BILDIRI_LISTESI_TABLO_YAPISI.ISIM+" VARCHAR(255), "
            + BILDIRI_LISTESI_TABLO_YAPISI.TARIH+" VARCHAR(255), "
            + BILDIRI_LISTESI_TABLO_YAPISI.DEADLINE_TARIH+" VARCHAR(255), "
            + BILDIRI_LISTESI_TABLO_YAPISI.DEADLINE_SAAT+" VARCHAR(255), "
            + BILDIRI_LISTESI_TABLO_YAPISI.SENT+" INTEGER "+")";
    String TASLAK_MEDYA_TABLOSU_OLUSTURMA = "CREATE TABLE " + TASLAK_MEDYA_TABLO_YAPISI.TABLO_ADI+"("+TASLAK_MEDYA_TABLO_YAPISI.BILDIRI_ID+" VARCHAR(255), "
            + TASLAK_MEDYA_TABLO_YAPISI.TARIH+" VARCHAR(255), "
            + TASLAK_MEDYA_TABLO_YAPISI.IMALAT_ID + " VARCHAR(255), "
            + TASLAK_MEDYA_TABLO_YAPISI.MEDYA_LINKI+" VARCHAR(255), "
            + TASLAK_MEDYA_TABLO_YAPISI.ETIKETLER+" VARCHAR(255) " +")";
    String TASLAK_VERIMSIZLIK_TABLOSU_OLUSTURMA = "CREATE TABLE " + TASLAK_VERIMSIZLIK_TABLO_YAPISI.TABLO_ADI+"("+TASLAK_VERIMSIZLIK_TABLO_YAPISI.BILDIRI_ID+" VARCHAR(255), "
            + TASLAK_VERIMSIZLIK_TABLO_YAPISI.TARIH+" VARCHAR(255), "
            + TASLAK_VERIMSIZLIK_TABLO_YAPISI.IMALAT_ID + " VARCHAR(255), "
            + TASLAK_VERIMSIZLIK_TABLO_YAPISI.ETKEN_ID+" VARCHAR(255), "
            + TASLAK_VERIMSIZLIK_TABLO_YAPISI.DEGER+" INTEGER " +")";
    String ETKEN_LISTE_TABLOSU_OLUSTURMA = "CREATE TABLE " + ETKEN_LISTE_TABLO_YAPISI.TABLO_ADI+"("+ETKEN_LISTE_TABLO_YAPISI.ETKEN_ID+" VARCHAR(255) PRIMARY KEY, "
            + ETKEN_LISTE_TABLO_YAPISI.ISIM+" VARCHAR(255), "
            + ETKEN_LISTE_TABLO_YAPISI.VT_DEGER + " INTEGER, "
            + ETKEN_LISTE_TABLO_YAPISI.RADIO_BUTTON + " INTEGER "+")";
    public SQLiteHelper(Context context) {
        super(context, VERITABANI_ADI, null, VERITABANI_VERSIYON);
        this.context = context;
    }
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(PERSONEL_TABLOSU_OLUSTURMA);
        sqLiteDatabase.execSQL(KITAP_TABLOSU_OLUSTURMA);
        sqLiteDatabase.execSQL(IMALAT_TABLOSU_OLUSTURMA);
        sqLiteDatabase.execSQL(TASLAK_TABLOSU_OLUSTURMA);
        sqLiteDatabase.execSQL(BILDIRILER_TABLOSU_OLUSTURMA);
        sqLiteDatabase.execSQL(TASLAK_RESOURCE_TABLOSU_OLUSTURMA);
        sqLiteDatabase.execSQL(TASLAK_ACIKLAMALAR_OLUSTURMA);
        sqLiteDatabase.execSQL(BILDIRI_LISTESI_TABLOSU_OLUSTURMA);
        sqLiteDatabase.execSQL(TASLAK_MEDYA_TABLOSU_OLUSTURMA);
        sqLiteDatabase.execSQL(TASLAK_VERIMSIZLIK_TABLOSU_OLUSTURMA);
        sqLiteDatabase.execSQL(ETKEN_LISTE_TABLOSU_OLUSTURMA);
        sqLiteDatabase.execSQL(GETTER_SETTER_TABLOSU_OLUSTURMA);
        //sqLiteDatabase.execSQL(VARSAYILAN_IMALAT_TABLOSU_OLUSTURMA);
    }
    public void WriteBildiriler(String id,String isim,int rutin,int siklik,int istek_tarih,String istek_saat,int termin_tarih,String termin_saat){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BILDIRILER_TABLO_YAPISI.ID,id);
        contentValues.put(BILDIRILER_TABLO_YAPISI.ISIM,isim);
        contentValues.put(BILDIRILER_TABLO_YAPISI.RUTIN,rutin);
        contentValues.put(BILDIRILER_TABLO_YAPISI.SIKLIK,siklik);
        contentValues.put(BILDIRILER_TABLO_YAPISI.ISTEK_TARIH,istek_tarih);
        contentValues.put(BILDIRILER_TABLO_YAPISI.ISTEK_SAAT,istek_saat);
        contentValues.put(BILDIRILER_TABLO_YAPISI.TERMIN_TARIH,termin_tarih);
        contentValues.put(BILDIRILER_TABLO_YAPISI.TERMIN_SAAT,termin_saat);
        sqLiteDatabase.insert(BILDIRILER_TABLO_YAPISI.TABLO_ADI,null,contentValues);
        sqLiteDatabase.close();
    }
    public void WriteGet_Set(String key,String value){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GETTER_SETTER_TABLO_YAPISI.KEY,key);
        contentValues.put(GETTER_SETTER_TABLO_YAPISI.VALUE,value);
        sqLiteDatabase.insert(GETTER_SETTER_TABLO_YAPISI.TABLO_ADI,null,contentValues);
        sqLiteDatabase.close();
    }
    public String ReadGet_Set(String key){
        String value=null;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = new String[]{
                GETTER_SETTER_TABLO_YAPISI.VALUE,
                GETTER_SETTER_TABLO_YAPISI.KEY
        };
        String[] selection = new String[]{key};
        Cursor cursor = sqLiteDatabase.query(GETTER_SETTER_TABLO_YAPISI.TABLO_ADI,columns,GETTER_SETTER_TABLO_YAPISI.KEY +" =? ",selection,null,null,null);
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                value = cursor.getString(cursor.getColumnIndex(GETTER_SETTER_TABLO_YAPISI.VALUE));
            }
        }else{
            value = "Yok";
        }
        cursor.close();
        sqLiteDatabase.close();
        return value;
    }
    public void UpdateGet_Set(String key,String new_value){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GETTER_SETTER_TABLO_YAPISI.VALUE,new_value);
        sqLiteDatabase.update(GETTER_SETTER_TABLO_YAPISI.TABLO_ADI,contentValues,GETTER_SETTER_TABLO_YAPISI.KEY+ "= ?",new String[]{key});
        sqLiteDatabase.close();
    }
    public void WriteSektor(int id,String isim,String hat,String hat_no,int km_bas,int km_bit,int aktif,String imalat,String bolge){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KITAP_TABLO_YAPISI.ID,id);
        contentValues.put(KITAP_TABLO_YAPISI.ISIM,isim);
        contentValues.put(KITAP_TABLO_YAPISI.HAT,hat);
        contentValues.put(KITAP_TABLO_YAPISI.HAT_NO,hat_no);
        contentValues.put(KITAP_TABLO_YAPISI.KM_BAS,km_bas);
        contentValues.put(KITAP_TABLO_YAPISI.KM_BIT,km_bit);
        contentValues.put(KITAP_TABLO_YAPISI.AKTIF,aktif);
        contentValues.put(KITAP_TABLO_YAPISI.IMALAT,imalat);
        contentValues.put(KITAP_TABLO_YAPISI.BOLGE,bolge);
        sqLiteDatabase.insert(KITAP_TABLO_YAPISI.TABLO_ADI,null,contentValues);
        sqLiteDatabase.close();
    }
    public void WriteBildiriListesi(long kod,int kullanici_id,String bildiri_id,String isim,String tarih,String deadline_tarih,String deadline_saat,int sent){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BILDIRI_LISTESI_TABLO_YAPISI.KOD,kod);
        contentValues.put(BILDIRI_LISTESI_TABLO_YAPISI.KULLANICI_ID,kullanici_id);
        contentValues.put(BILDIRI_LISTESI_TABLO_YAPISI.BILDIRI_ID,bildiri_id);
        contentValues.put(BILDIRI_LISTESI_TABLO_YAPISI.ISIM,isim);
        contentValues.put(BILDIRI_LISTESI_TABLO_YAPISI.TARIH,tarih);
        contentValues.put(BILDIRI_LISTESI_TABLO_YAPISI.DEADLINE_TARIH,deadline_tarih);
        contentValues.put(BILDIRI_LISTESI_TABLO_YAPISI.DEADLINE_SAAT,deadline_saat);
        contentValues.put(BILDIRI_LISTESI_TABLO_YAPISI.SENT,sent);
        sqLiteDatabase.insert(BILDIRI_LISTESI_TABLO_YAPISI.TABLO_ADI,null,contentValues);
        sqLiteDatabase.close();
    }
    public void WriteImalat(String id,String isim,String oncelik,String birim,double bp_as,int metraj,String bildiri,int vt_sektor,int vt_mesafe,int km_aktif){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(IMALAT_TABLO_YAPISI.ID,id);
     //   contentValues.put(IMALAT_TABLO_YAPISI.ORDER,order);
        contentValues.put(IMALAT_TABLO_YAPISI.ISIM,isim);
        contentValues.put(IMALAT_TABLO_YAPISI.ONCELIK,oncelik);
        contentValues.put(IMALAT_TABLO_YAPISI.BIRIM,birim);
        contentValues.put(IMALAT_TABLO_YAPISI.BP_AS,bp_as);
        contentValues.put(IMALAT_TABLO_YAPISI.METRAJ,metraj);
        contentValues.put(IMALAT_TABLO_YAPISI.BILDIRI,bildiri);
        contentValues.put(IMALAT_TABLO_YAPISI.VT_SEKTOR,vt_sektor);
        contentValues.put(IMALAT_TABLO_YAPISI.VT_MESAFE,vt_mesafe);
        contentValues.put(IMALAT_TABLO_YAPISI.KM_AKTIF,km_aktif);
        sqLiteDatabase.insert(IMALAT_TABLO_YAPISI.TABLO_ADI,null,contentValues);
        sqLiteDatabase.close();
    }
    public void  WriteTaslak(Long id,String tarih,String imalat,int kopya_no,String sektor,int hat_no,int km_bas,int km_son,int mesafe,String birim,int sent){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASLAK_TABLO_YAPISI.ID,id);
        contentValues.put(TASLAK_TABLO_YAPISI.TARIH,tarih);
        contentValues.put(TASLAK_TABLO_YAPISI.IMALAT,imalat);
        contentValues.put(TASLAK_TABLO_YAPISI.KOPYA_NO,kopya_no);
        contentValues.put(TASLAK_TABLO_YAPISI.SEKTOR,sektor);
        contentValues.put(TASLAK_TABLO_YAPISI.HAT_NO,hat_no);
        contentValues.put(TASLAK_TABLO_YAPISI.KM_BAS,km_bas);
        contentValues.put(TASLAK_TABLO_YAPISI.KM_SON,km_son);
        contentValues.put(TASLAK_TABLO_YAPISI.MESAFE,mesafe);
        contentValues.put(TASLAK_TABLO_YAPISI.BIRIM,birim);
        contentValues.put(TASLAK_TABLO_YAPISI.SENT,sent);
        sqLiteDatabase.insert(TASLAK_TABLO_YAPISI.TABLO_ADI,null,contentValues);
        sqLiteDatabase.close();
    }
    public void  WriteTaslakResource(Long id,String tarih,String imalat,String kaynak_id,String tip,String kategori,int puantaj,int sayi,String  verim){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASLAK_RESOURCE_YAPISI.ID, id);
        contentValues.put(TASLAK_RESOURCE_YAPISI.TARIH, tarih);
        contentValues.put(TASLAK_RESOURCE_YAPISI.KAYNAK_ID, kaynak_id);
        contentValues.put(TASLAK_RESOURCE_YAPISI.KATEGORI, kategori);
        contentValues.put(TASLAK_RESOURCE_YAPISI.IMALAT, imalat);
        contentValues.put(TASLAK_RESOURCE_YAPISI.PUANTAJ, puantaj);
        contentValues.put(TASLAK_RESOURCE_YAPISI.TIP, tip);
        contentValues.put(TASLAK_RESOURCE_YAPISI.VERIM, verim);
        contentValues.put(TASLAK_RESOURCE_YAPISI.SAYI,sayi);
        sqLiteDatabase.insert(TASLAK_RESOURCE_YAPISI.TABLO_ADI,null,contentValues);
        sqLiteDatabase.close();
    }
    public void  WriteTaslakAciklamalar(String id,String tarih,String imalat,String aciklama){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASLAK_ACIKLAMALAR_YAPISI.ID, id);
        contentValues.put(TASLAK_ACIKLAMALAR_YAPISI.TARIH, tarih);
        contentValues.put(TASLAK_ACIKLAMALAR_YAPISI.IMALAT, imalat);
        contentValues.put(TASLAK_ACIKLAMALAR_YAPISI.ACIKLAMA, aciklama);
        sqLiteDatabase.insert(TASLAK_ACIKLAMALAR_YAPISI.TABLO_ADI,null,contentValues);
        sqLiteDatabase.close();
    }
    public void  WriteEtkenListe(String etken_id,String isim,int vt_deger,int radio_button){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ETKEN_LISTE_TABLO_YAPISI.ETKEN_ID, etken_id);
        contentValues.put(ETKEN_LISTE_TABLO_YAPISI.ISIM, isim);
        contentValues.put(ETKEN_LISTE_TABLO_YAPISI.VT_DEGER, vt_deger);
        contentValues.put(ETKEN_LISTE_TABLO_YAPISI.RADIO_BUTTON, radio_button);
        sqLiteDatabase.insert(ETKEN_LISTE_TABLO_YAPISI.TABLO_ADI,null,contentValues);
        sqLiteDatabase.close();
    }
    public void  WriteTaslakVerimsizlik(String bildiri_id, String tarih, String imalat_id, String etken_id, int deger){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASLAK_VERIMSIZLIK_TABLO_YAPISI.BILDIRI_ID, bildiri_id);
        contentValues.put(TASLAK_VERIMSIZLIK_TABLO_YAPISI.TARIH, tarih);
        contentValues.put(TASLAK_VERIMSIZLIK_TABLO_YAPISI.IMALAT_ID, imalat_id);
        contentValues.put(TASLAK_VERIMSIZLIK_TABLO_YAPISI.ETKEN_ID, etken_id);
        contentValues.put(TASLAK_VERIMSIZLIK_TABLO_YAPISI.DEGER, deger);
        sqLiteDatabase.insert(TASLAK_VERIMSIZLIK_TABLO_YAPISI.TABLO_ADI,null,contentValues);
        sqLiteDatabase.close();
    }

    public void  WritePersonel(String id, String kisa_isim, String kod, String kategori,String tip, String ekip_id,int vt_puantaj,int vt_sayi){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PERSONEL_TABLO_YAPISI.RESOURCE_ID,id);
        contentValues.put(PERSONEL_TABLO_YAPISI.KISA_ISIM,kisa_isim);
        contentValues.put(PERSONEL_TABLO_YAPISI.KOD,kod);
        contentValues.put(PERSONEL_TABLO_YAPISI.KATEGORI,kategori);
        contentValues.put(PERSONEL_TABLO_YAPISI.TIP,tip);
        contentValues.put(PERSONEL_TABLO_YAPISI.EKIP_ID,ekip_id);
        contentValues.put(PERSONEL_TABLO_YAPISI.VT_PUANTAJ,vt_puantaj);
        contentValues.put(PERSONEL_TABLO_YAPISI.VT_SAYI,vt_sayi);
        sqLiteDatabase.insert(PERSONEL_TABLO_YAPISI.TABLO_ADI,null,contentValues);
        sqLiteDatabase.close();
    }
    public String[] ReadBildiriListesifor1b(int kullanici_id){

        int kod = 0;
        String bildiri_id = null;
        String isim = null;
        String tarih = null;
        String deadline_tarih = null;
        String deadline_saat = null;
        int sent = 0;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {BILDIRI_LISTESI_TABLO_YAPISI.KOD,
                BILDIRI_LISTESI_TABLO_YAPISI.KULLANICI_ID,
                BILDIRI_LISTESI_TABLO_YAPISI.BILDIRI_ID,
                BILDIRI_LISTESI_TABLO_YAPISI.ISIM,
                BILDIRI_LISTESI_TABLO_YAPISI.TARIH,
                BILDIRI_LISTESI_TABLO_YAPISI.DEADLINE_TARIH,
                BILDIRI_LISTESI_TABLO_YAPISI.DEADLINE_SAAT,
                BILDIRI_LISTESI_TABLO_YAPISI.SENT};
        String[] selectionArgs = {String.valueOf(kullanici_id)};
        Cursor cursor = sqLiteDatabase.query(BILDIRI_LISTESI_TABLO_YAPISI.TABLO_ADI,columns,BILDIRI_LISTESI_TABLO_YAPISI.KULLANICI_ID+" =?",selectionArgs,null,null,null);
        String[] kitapBilgisi = new String[0];
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){

                kod = cursor.getInt(cursor.getColumnIndex(BILDIRI_LISTESI_TABLO_YAPISI.KOD));
                kullanici_id = cursor.getInt(cursor.getColumnIndex(BILDIRI_LISTESI_TABLO_YAPISI.KULLANICI_ID));
                bildiri_id = cursor.getString(cursor.getColumnIndex(BILDIRI_LISTESI_TABLO_YAPISI.BILDIRI_ID));
                isim= cursor.getString(cursor.getColumnIndex(BILDIRI_LISTESI_TABLO_YAPISI.ISIM));
                tarih = cursor.getString(cursor.getColumnIndex(BILDIRI_LISTESI_TABLO_YAPISI.TARIH));
                deadline_tarih= cursor.getString(cursor.getColumnIndex(BILDIRI_LISTESI_TABLO_YAPISI.DEADLINE_TARIH));
                deadline_saat = cursor.getString(cursor.getColumnIndex(BILDIRI_LISTESI_TABLO_YAPISI.DEADLINE_SAAT));
                sent = cursor.getInt(cursor.getColumnIndex(BILDIRI_LISTESI_TABLO_YAPISI.SENT));
                kitapBilgisi = new String[]{
                        String.valueOf(kod),
                        String.valueOf(kullanici_id),
                        bildiri_id,
                        isim,
                        tarih,
                        deadline_tarih,
                        deadline_saat,
                        String.valueOf(sent),

                };

            }
        } else {
            kitapBilgisi = new String[]{String.valueOf(cursor.getCount())};
        }
        cursor.close();
        sqLiteDatabase.close();
        return kitapBilgisi;
    }
    public List[] ReadEtkenListesiforlist(String bildiri_id,String imalat_id){
        String id= null;
        int deger = 0;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {TASLAK_VERIMSIZLIK_TABLO_YAPISI.DEGER,
                TASLAK_VERIMSIZLIK_TABLO_YAPISI.ETKEN_ID};
        String[] selectionArgs = {bildiri_id,imalat_id};
        Cursor cursor = sqLiteDatabase.query(TASLAK_VERIMSIZLIK_TABLO_YAPISI.TABLO_ADI,columns,TASLAK_VERIMSIZLIK_TABLO_YAPISI.BILDIRI_ID+" =?"+" AND "+ TASLAK_VERIMSIZLIK_TABLO_YAPISI.IMALAT_ID+ " =? ",selectionArgs,null,null,null);
        List<String> etkenler = new ArrayList<>();
        List<String> degerler = new ArrayList<>();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){

                id = cursor.getString(cursor.getColumnIndex(TASLAK_VERIMSIZLIK_TABLO_YAPISI.ETKEN_ID));
                deger= cursor.getInt(cursor.getColumnIndex(TASLAK_VERIMSIZLIK_TABLO_YAPISI.DEGER));
                etkenler.add(ReadEtkenListesiforisim(id));
                degerler.add(String.valueOf(deger));


            }
        }
        List[] list = new List[]{etkenler,degerler};
        cursor.close();
        sqLiteDatabase.close();
        return list;
    }
    public String ReadEtkenListesiforisim(String id){
        String isim= null;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {ETKEN_LISTE_TABLO_YAPISI.ISIM,
                ETKEN_LISTE_TABLO_YAPISI.ETKEN_ID,
                ETKEN_LISTE_TABLO_YAPISI.VT_DEGER};
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = sqLiteDatabase.query(ETKEN_LISTE_TABLO_YAPISI.TABLO_ADI,columns,ETKEN_LISTE_TABLO_YAPISI.ETKEN_ID+" =?",selectionArgs,null,null,null);
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                isim = cursor.getString(cursor.getColumnIndex(ETKEN_LISTE_TABLO_YAPISI.ISIM));
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return isim;
    }
    public String ReadEtkenListesiforid(String isim){
        String id= null;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {ETKEN_LISTE_TABLO_YAPISI.ISIM,
                ETKEN_LISTE_TABLO_YAPISI.ETKEN_ID,
                ETKEN_LISTE_TABLO_YAPISI.VT_DEGER};
        String[] selectionArgs = {String.valueOf(isim)};
        Cursor cursor = sqLiteDatabase.query(ETKEN_LISTE_TABLO_YAPISI.TABLO_ADI,columns,ETKEN_LISTE_TABLO_YAPISI.ISIM+" =?",selectionArgs,null,null,null);
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                id = cursor.getString(cursor.getColumnIndex(ETKEN_LISTE_TABLO_YAPISI.ETKEN_ID));
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return id;
    }
    public int ReadEtkenListesifordeger(String id){
        int deger= 0;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {ETKEN_LISTE_TABLO_YAPISI.ISIM,
                ETKEN_LISTE_TABLO_YAPISI.ETKEN_ID,
                ETKEN_LISTE_TABLO_YAPISI.VT_DEGER};
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = sqLiteDatabase.query(ETKEN_LISTE_TABLO_YAPISI.TABLO_ADI,columns,ETKEN_LISTE_TABLO_YAPISI.ETKEN_ID+" =?",selectionArgs,null,null,null);
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                deger = cursor.getInt(cursor.getColumnIndex(ETKEN_LISTE_TABLO_YAPISI.VT_DEGER));
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return deger;
    }
    public String[][] ReadEtkenListesi(){
        String isim = null;
        int radio = 0;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {ETKEN_LISTE_TABLO_YAPISI.ISIM,
                ETKEN_LISTE_TABLO_YAPISI.RADIO_BUTTON
        };
        Cursor cursor = sqLiteDatabase.query(ETKEN_LISTE_TABLO_YAPISI.TABLO_ADI,columns,null,null,null,null,null);
        List<String> isimler = new ArrayList<>();
        List<String> radiolar = new ArrayList<>();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                isim = cursor.getString(cursor.getColumnIndex(ETKEN_LISTE_TABLO_YAPISI.ISIM));
                radio = cursor.getInt(cursor.getColumnIndex(ETKEN_LISTE_TABLO_YAPISI.RADIO_BUTTON));
                isimler.add(isim);
                radiolar.add(String.valueOf(radio));
            }
        }
        String[] isimler_array = Arrays.copyOf(isimler.toArray(new String[isimler.size()]),isimler.toArray(new String[isimler.size()]).length,String[].class);
        String[] radiolar_array = Arrays.copyOf(radiolar.toArray(new String[radiolar.size()]),radiolar.toArray(new String[radiolar.size()]).length,String[].class);
        String[][] diziler = new String[][]{isimler_array,radiolar_array};
        cursor.close();
        sqLiteDatabase.close();
        return diziler;
    }
    public String[] ReadVerimsizlik(String bildiri_id,String imalat_id,String[] etken_idler){

        List<String> radiolar = new ArrayList<>();
        for (int i = 0; i<etken_idler.length;i++) {
            radiolar.add(ReadVerimsizlikPart2(bildiri_id,imalat_id,ReadEtkenListesiforid(etken_idler[i])));
        }
        String[] radiolar_array = Arrays.copyOf(radiolar.toArray(new String[radiolar.size()]), radiolar.toArray(new String[radiolar.size()]).length, String[].class);
        return radiolar_array;
    }
    public String ReadVerimsizlikPart2(String bildiri_id,String imalat_id,String etken_id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {
                TASLAK_VERIMSIZLIK_TABLO_YAPISI.ETKEN_ID,
                TASLAK_VERIMSIZLIK_TABLO_YAPISI.DEGER
        };
        String[] selectionargs = new String[]{bildiri_id,imalat_id, etken_id};
        Cursor cursor = sqLiteDatabase.query(TASLAK_VERIMSIZLIK_TABLO_YAPISI.TABLO_ADI, columns, TASLAK_VERIMSIZLIK_TABLO_YAPISI.BILDIRI_ID +" =? "+" AND "+TASLAK_VERIMSIZLIK_TABLO_YAPISI.IMALAT_ID +" =? "+" AND "+TASLAK_VERIMSIZLIK_TABLO_YAPISI.ETKEN_ID +" =? ", selectionargs, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.close();
            sqLiteDatabase.close();
            return "1";
        }else {
            cursor.close();
            sqLiteDatabase.close();
            return "0";
        }

    }
    public List[] ReadTaslakVerimsizlik(String bildiri_id,String imalat_id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {
                TASLAK_VERIMSIZLIK_TABLO_YAPISI.ETKEN_ID,
                TASLAK_VERIMSIZLIK_TABLO_YAPISI.DEGER
        };
        String[] selectionargs = new String[]{bildiri_id,imalat_id};
        List<String> etkenler = new ArrayList<>();
        List<Integer> degerler = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(TASLAK_VERIMSIZLIK_TABLO_YAPISI.TABLO_ADI, columns, TASLAK_VERIMSIZLIK_TABLO_YAPISI.BILDIRI_ID +" =? "+" AND "+TASLAK_VERIMSIZLIK_TABLO_YAPISI.IMALAT_ID +" =? ", selectionargs, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String etken = cursor.getString(cursor.getColumnIndex(TASLAK_VERIMSIZLIK_TABLO_YAPISI.ETKEN_ID));
                etkenler.add(etken);
                degerler.add(cursor.getInt(cursor.getColumnIndex(TASLAK_VERIMSIZLIK_TABLO_YAPISI.DEGER)));
            }
        }else {
        }
        List[] listeler = new List[]{etkenler,degerler};
        cursor.close();
        sqLiteDatabase.close();
        return listeler;

    }


    public int ReadBildiriListesifor1b(int kullanici_id,String bildiri_id){

        int kod = 0;
        String isim = null;
        String tarih = null;
        String deadline_tarih = null;
        String deadline_saat = null;
        int sent = 0;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {BILDIRI_LISTESI_TABLO_YAPISI.KOD,
                BILDIRI_LISTESI_TABLO_YAPISI.KULLANICI_ID,
                BILDIRI_LISTESI_TABLO_YAPISI.BILDIRI_ID,
                BILDIRI_LISTESI_TABLO_YAPISI.ISIM,
                BILDIRI_LISTESI_TABLO_YAPISI.TARIH,
                BILDIRI_LISTESI_TABLO_YAPISI.DEADLINE_TARIH,
                BILDIRI_LISTESI_TABLO_YAPISI.DEADLINE_SAAT,
                BILDIRI_LISTESI_TABLO_YAPISI.SENT};
        String[] selectionArgs = {String.valueOf(kullanici_id),bildiri_id};
        Cursor cursor = sqLiteDatabase.query(BILDIRI_LISTESI_TABLO_YAPISI.TABLO_ADI,columns,BILDIRI_LISTESI_TABLO_YAPISI.KULLANICI_ID+" =?"+ " AND "+BILDIRI_LISTESI_TABLO_YAPISI.BILDIRI_ID+" =?" ,selectionArgs,null,null,null);

       int count = cursor.getCount();
        cursor.close();
        sqLiteDatabase.close();
        return count ;
    }
    public String ReadBildiriListesiforDateComparision(int kullanici_id,String bildiri_id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {BILDIRI_LISTESI_TABLO_YAPISI.KOD,
                BILDIRI_LISTESI_TABLO_YAPISI.KULLANICI_ID,
                BILDIRI_LISTESI_TABLO_YAPISI.BILDIRI_ID,
                BILDIRI_LISTESI_TABLO_YAPISI.ISIM,
                BILDIRI_LISTESI_TABLO_YAPISI.TARIH,
                BILDIRI_LISTESI_TABLO_YAPISI.DEADLINE_TARIH,
                BILDIRI_LISTESI_TABLO_YAPISI.DEADLINE_SAAT,
                BILDIRI_LISTESI_TABLO_YAPISI.SENT};
        String[] selectionArgs = {String.valueOf(kullanici_id)};
        Cursor cursor = sqLiteDatabase.query(BILDIRI_LISTESI_TABLO_YAPISI.TABLO_ADI,columns,BILDIRI_LISTESI_TABLO_YAPISI.KULLANICI_ID+" =?",selectionArgs,null,null,null);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String tarih_str = null;
        if (cursor.getCount()>0){
            Date enbüyük = null;
            while (cursor.moveToNext()) {
                if (bildiri_id.equals(cursor.getString(cursor.getColumnIndex(BILDIRI_LISTESI_TABLO_YAPISI.BILDIRI_ID)))) {

                    try {
                        Date tarih = simpleDateFormat.parse(cursor.getString(cursor.getColumnIndex(BILDIRI_LISTESI_TABLO_YAPISI.TARIH)));

                        if (enbüyük == null) {
                            enbüyük = tarih;

                        } else {

                            if (tarih.compareTo(enbüyük) > 0) {
                                enbüyük = tarih;

                            }
                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    tarih_str = simpleDateFormat.format(enbüyük);
                }



            }
        } else {
            tarih_str = "0";
        }
        cursor.close();
        sqLiteDatabase.close();
        return tarih_str;
    }
    public String[] ReadPersonel(String id){
        String isim = null;
        String kisa_isim = null;
        String kod = null;
        String kategori = null;
        String tip = null;
        String detay = null;
        String sorumlu = null;
        String ekip_id = null;
        String imalat_id = null;
        String giris_tarih = null;
        String cikis_tarih = null;
        int vt_puantaj = 0;
        int vt_sayi = 0;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {
                PERSONEL_TABLO_YAPISI.RESOURCE_ID,
                PERSONEL_TABLO_YAPISI.KISA_ISIM,
                PERSONEL_TABLO_YAPISI.KOD,
                PERSONEL_TABLO_YAPISI.KATEGORI,
                PERSONEL_TABLO_YAPISI.TIP,
                PERSONEL_TABLO_YAPISI.EKIP_ID,
                PERSONEL_TABLO_YAPISI.VT_PUANTAJ,
                PERSONEL_TABLO_YAPISI.VT_SAYI
        };
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = sqLiteDatabase.query(PERSONEL_TABLO_YAPISI.TABLO_ADI,columns,PERSONEL_TABLO_YAPISI.RESOURCE_ID+" =?",selectionArgs,null,null,null);
        String[] taslak_bilgisi = new String[0];
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){

                id = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.RESOURCE_ID));
                kisa_isim = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.KISA_ISIM));
                kod = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.KOD));
                kategori = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.KATEGORI));
                tip = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.TIP));
                ekip_id = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.EKIP_ID));
                vt_puantaj = cursor.getInt(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.VT_PUANTAJ));
                vt_sayi = cursor.getInt(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.VT_SAYI));
                taslak_bilgisi = new String[]{
                        String.valueOf(id),
                        kisa_isim,
                        kod,
                        kategori,
                        tip,
                        ekip_id,
                        String.valueOf(vt_puantaj),
                        String.valueOf(vt_sayi)

                };
            }
        } else {
            taslak_bilgisi = new String[]{String.valueOf(cursor.getCount())};
        }
        cursor.close();
        sqLiteDatabase.close();
        return taslak_bilgisi;
    }
    public String[] ReadPersonelforL4(String bildiri_id,String tip){
        String kisa_isim = null;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {PERSONEL_TABLO_YAPISI.KISA_ISIM};
        Cursor cursor = sqLiteDatabase.query(PERSONEL_TABLO_YAPISI.TABLO_ADI,columns,PERSONEL_TABLO_YAPISI.TIP +" =? ",new String[]{tip},null,null,null);
        List<String> list = new ArrayList();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                kisa_isim = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.KISA_ISIM));
                list.add(kisa_isim);
            }
        }
        cursor.close();
        sqLiteDatabase.close();

        list = FavEkiphelper(bildiri_id,list);
        String[] kisiler = list.toArray(new String[list.size()]);
        return kisiler;
    }
    public List<String> FavEkiphelper(String bildiri_id ,List<String> list){
        SQLiteDatabase sqLiteDatabase1 = getWritableDatabase();
        String[] columns1 = {TASLAK_RESOURCE_YAPISI.KAYNAK_ID};//todo ekip to grup makine
        Cursor cursor1 = sqLiteDatabase1.query(TASLAK_RESOURCE_YAPISI.TABLO_ADI,columns1, TASLAK_RESOURCE_YAPISI.ID+" =? "+" AND "+TASLAK_RESOURCE_YAPISI.KATEGORI+" =? ",new String[]{bildiri_id,"ekip"},null,null,null);
        if (cursor1.getCount()>0){
            while (cursor1.moveToNext()){
                String id = cursor1.getString(cursor1.getColumnIndex(TASLAK_RESOURCE_YAPISI.KAYNAK_ID));
                list.remove(ReadPersonelwid(id));
            }
        }
        return list;
    }
    public List<String> FavEkiphelperwisim(List<String> list){
        SQLiteDatabase sqLiteDatabase1 = getWritableDatabase();
        String[] columns1 = {TASLAK_RESOURCE_YAPISI.KAYNAK_ID};//todo ekip to grup makine
        Cursor cursor1 = sqLiteDatabase1.query(TASLAK_RESOURCE_YAPISI.TABLO_ADI,columns1, TASLAK_RESOURCE_YAPISI.KATEGORI+" =? ",new String[]{"ekip"},null,null,null);
        if (cursor1.getCount()>0){
            while (cursor1.moveToNext()){
                String id = cursor1.getString(cursor1.getColumnIndex(TASLAK_RESOURCE_YAPISI.KAYNAK_ID));
                list.remove(id);
            }
        }
        return list;
    }
    public String[] ReadPersonelswEkip_id(String ekip_id){
        String personel_id = null;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {PERSONEL_TABLO_YAPISI.RESOURCE_ID,
                PERSONEL_TABLO_YAPISI.KATEGORI,
                PERSONEL_TABLO_YAPISI.EKIP_ID
        };
        Cursor cursor = sqLiteDatabase.query(PERSONEL_TABLO_YAPISI.TABLO_ADI,columns,PERSONEL_TABLO_YAPISI.EKIP_ID +" =? ",new String[]{ekip_id},null,null,null);
        List<String> list = new ArrayList();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                personel_id = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.RESOURCE_ID));
                list.add(personel_id);
            }
        }
        String[] id = list.toArray(new String[list.size()]);
        cursor.close();
        sqLiteDatabase.close();
        return id;
    }
    public String[] ReadMakineforL4(String kategori){
        String kisa_isim = null;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {PERSONEL_TABLO_YAPISI.KISA_ISIM};
        Cursor cursor = sqLiteDatabase.query(PERSONEL_TABLO_YAPISI.TABLO_ADI,columns,PERSONEL_TABLO_YAPISI.KATEGORI +" =? ",new String[]{kategori},null,null,null);
        List<String> list = new ArrayList();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                kisa_isim = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.KISA_ISIM));
                list.add(kisa_isim);
            }
        }
        String[] kisiler = list.toArray(new String[list.size()]);
        cursor.close();

        sqLiteDatabase.close();
        return kisiler;
    }
    public List<String> ReadPersonelforL4Sepet(String bildiri_id,String imalat_id){
        String kisa_isim = null;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {PERSONEL_TABLO_YAPISI.KISA_ISIM,
                PERSONEL_TABLO_YAPISI.RESOURCE_ID,
                PERSONEL_TABLO_YAPISI.KATEGORI
        };
        String[] selectionArgs = {"isci"};//TODO makine müdahale
        Cursor cursor = sqLiteDatabase.query(PERSONEL_TABLO_YAPISI.TABLO_ADI,columns,PERSONEL_TABLO_YAPISI.TIP +" =? ",selectionArgs,null,null,null);
        List<String> list = new ArrayList();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                kisa_isim = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.KISA_ISIM));
                list.add(kisa_isim);
            }
        }
        cursor.close();
        sqLiteDatabase.close();


        SQLiteDatabase sqLiteDatabase1 = getWritableDatabase();
        String[] columns1 = {
                TASLAK_RESOURCE_YAPISI.ID,//todo
                TASLAK_RESOURCE_YAPISI.KAYNAK_ID,
                TASLAK_RESOURCE_YAPISI.KATEGORI,
                TASLAK_RESOURCE_YAPISI.VERIM,
                TASLAK_RESOURCE_YAPISI.IMALAT
        };
        String[] selectionArgs1 = {String.valueOf(bildiri_id),"efor","isci",imalat_id};//TODO makine müdahale
        Cursor cursor1 = sqLiteDatabase1.query(TASLAK_RESOURCE_YAPISI.TABLO_ADI,columns1,TASLAK_RESOURCE_YAPISI.ID+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.VERIM+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.KATEGORI+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.IMALAT+" =?",selectionArgs1,null,null,null);
        if (cursor1.getCount()>0){
            while (cursor1.moveToNext()){
                list.remove(ReadPersonelwid(cursor1.getString(cursor1.getColumnIndex(TASLAK_RESOURCE_YAPISI.KAYNAK_ID))));
            }
        }
        cursor1.close();
        sqLiteDatabase1.close();
        return list;
    }
    public List<String> ReadPersonelforL4SepetMakine(String bildiri_id){
        String kisa_isim = null;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {PERSONEL_TABLO_YAPISI.KISA_ISIM,
                PERSONEL_TABLO_YAPISI.RESOURCE_ID,
                PERSONEL_TABLO_YAPISI.KATEGORI
        };
        String[] selectionArgs = {"makine"};//TODO makine müdahale
        Cursor cursor = sqLiteDatabase.query(PERSONEL_TABLO_YAPISI.TABLO_ADI,columns,PERSONEL_TABLO_YAPISI.TIP +" =? ",selectionArgs,null,null,null);
        List<String> list = new ArrayList();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                kisa_isim = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.KISA_ISIM));
                list.add(kisa_isim);
            }
        }
        cursor.close();
        sqLiteDatabase.close();


        SQLiteDatabase sqLiteDatabase1 = getWritableDatabase();
        String[] columns1 = {
                TASLAK_RESOURCE_YAPISI.ID,//todo
                TASLAK_RESOURCE_YAPISI.KAYNAK_ID,
                TASLAK_RESOURCE_YAPISI.KATEGORI,
                TASLAK_RESOURCE_YAPISI.VERIM,
        };
        String[] selectionArgs1 = {String.valueOf(bildiri_id),"efor","makine"};//TODO makine müdahale
        Cursor cursor1 = sqLiteDatabase1.query(TASLAK_RESOURCE_YAPISI.TABLO_ADI,columns1,TASLAK_RESOURCE_YAPISI.ID+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.VERIM+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.KATEGORI+" =?",selectionArgs1,null,null,null);
        if (cursor1.getCount()>0){
            while (cursor1.moveToNext()){
                list.remove(ReadPersonelwid(cursor1.getString(cursor1.getColumnIndex(TASLAK_RESOURCE_YAPISI.KAYNAK_ID))));
            }
        }
        cursor1.close();
        sqLiteDatabase1.close();
        return list;
    }

    public List<String> ReadPersonelforL4SepetSecilen(String bildiri_id,String imalatid){
        SQLiteDatabase sqLiteDatabase1 = getWritableDatabase();
        List<String> list = new ArrayList<>();
        String[] columns1 = {
                TASLAK_RESOURCE_YAPISI.ID,//todo
                TASLAK_RESOURCE_YAPISI.KAYNAK_ID,
                TASLAK_RESOURCE_YAPISI.KATEGORI,
                TASLAK_RESOURCE_YAPISI.VERIM,
        };
        String[] selectionArgs1 = {String.valueOf(bildiri_id),"efor","iscilik",imalatid};//TODO makine müdahale
        Cursor cursor1 = sqLiteDatabase1.query(TASLAK_RESOURCE_YAPISI.TABLO_ADI,columns1,TASLAK_RESOURCE_YAPISI.ID+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.VERIM+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.TIP+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.IMALAT+" =?",selectionArgs1,null,null,null);
        if (cursor1.getCount()>0){
            while (cursor1.moveToNext()){
                list.add(ReadPersonelwid(cursor1.getString(cursor1.getColumnIndex(TASLAK_RESOURCE_YAPISI.KAYNAK_ID))));

            }
        }
        cursor1.close();
        sqLiteDatabase1.close();
        return list;
    }
    public List<String> ReadPersonelforL4SepetSecilenMakine(String bildiri_id,String imalatid){
        SQLiteDatabase sqLiteDatabase1 = getWritableDatabase();
        List<String> list = new ArrayList<>();
        String[] columns1 = {
                TASLAK_RESOURCE_YAPISI.ID,//todo
                TASLAK_RESOURCE_YAPISI.KAYNAK_ID,
                TASLAK_RESOURCE_YAPISI.KATEGORI,
                TASLAK_RESOURCE_YAPISI.VERIM,
        };
        String[] selectionArgs1 = {String.valueOf(bildiri_id),"efor","makine",imalatid};//TODO makine müdahale
        Cursor cursor1 = sqLiteDatabase1.query(TASLAK_RESOURCE_YAPISI.TABLO_ADI,columns1,TASLAK_RESOURCE_YAPISI.ID+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.VERIM+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.TIP+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.IMALAT+" =?",selectionArgs1,null,null,null);
        if (cursor1.getCount()>0){
            while (cursor1.moveToNext()){
                list.add(ReadPersonelwid(cursor1.getString(cursor1.getColumnIndex(TASLAK_RESOURCE_YAPISI.KAYNAK_ID))));

            }
        }
        cursor1.close();
        sqLiteDatabase1.close();
        return list;
    }

    public int ReadTaslakforL4SepetSecilenClick(String bildiri_id,String id,String imalatid){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        int x=1;

        String[] columns = {
                TASLAK_RESOURCE_YAPISI.ID,
                TASLAK_RESOURCE_YAPISI.KAYNAK_ID,
                TASLAK_RESOURCE_YAPISI.KATEGORI,
                TASLAK_RESOURCE_YAPISI.VERIM,
        };
        String[] selectionArgs = {String.valueOf(bildiri_id),"efor","iscilik",id,imalatid};//TODO makine müdahale
        Cursor cursor = sqLiteDatabase.query(TASLAK_RESOURCE_YAPISI.TABLO_ADI,columns,TASLAK_RESOURCE_YAPISI.ID+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.VERIM+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.TIP+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.KAYNAK_ID+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.IMALAT+" =?",selectionArgs,null,null,null);
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                if (cursor.getString(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.KATEGORI)).equals("ekip")){//TODO ekip sordgusunu sql e yaptır
                    x=2;
                    SQLiteDatabase sqLiteDatabase1 = getWritableDatabase();
                    String[] silinecekVeri = {String.valueOf(bildiri_id),id};

                    sqLiteDatabase1.delete(TASLAK_RESOURCE_YAPISI.TABLO_ADI,TASLAK_RESOURCE_YAPISI.ID+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.VERIM+" =?",silinecekVeri);
                    sqLiteDatabase1.close();
                }
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        SQLiteDatabase sqLiteDatabase2 = getWritableDatabase();
        String[] silinecekVeri1 = {String.valueOf(bildiri_id),"efor","iscilik",id,imalatid};
        sqLiteDatabase2.delete(TASLAK_RESOURCE_YAPISI.TABLO_ADI,TASLAK_RESOURCE_YAPISI.ID+" =?"+" AND "+
                TASLAK_RESOURCE_YAPISI.VERIM+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.TIP+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.KAYNAK_ID+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.IMALAT+" =?",silinecekVeri1);
        sqLiteDatabase2.close();
        return x;
    }
    public int ReadTaslakforL4SepetSecilenClickMakine(String bildiri_id,String id,String imalatid){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        int x=1;

        String[] columns = {
                TASLAK_RESOURCE_YAPISI.ID,
                TASLAK_RESOURCE_YAPISI.KAYNAK_ID,
                TASLAK_RESOURCE_YAPISI.KATEGORI,
                TASLAK_RESOURCE_YAPISI.VERIM,
        };
        String[] selectionArgs = {String.valueOf(bildiri_id),"efor","makine",id,imalatid};//TODO makine müdahale
        Cursor cursor = sqLiteDatabase.query(TASLAK_RESOURCE_YAPISI.TABLO_ADI,columns,TASLAK_RESOURCE_YAPISI.ID+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.VERIM+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.TIP+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.KAYNAK_ID+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.IMALAT+" =?",selectionArgs,null,null,null);
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                if (cursor.getString(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.KATEGORI)).equals("grup")){//TODO ekip sordgusunu sql e yaptır
                    x=2;
                    SQLiteDatabase sqLiteDatabase1 = getWritableDatabase();
                    String[] silinecekVeri = {String.valueOf(bildiri_id),id};

                    sqLiteDatabase1.delete(TASLAK_RESOURCE_YAPISI.TABLO_ADI,TASLAK_RESOURCE_YAPISI.ID+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.VERIM+" =?",silinecekVeri);
                    sqLiteDatabase1.close();
                }
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        SQLiteDatabase sqLiteDatabase2 = getWritableDatabase();
        String[] silinecekVeri1 = {String.valueOf(bildiri_id),"efor","makine",id,imalatid};
        sqLiteDatabase2.delete(TASLAK_RESOURCE_YAPISI.TABLO_ADI,TASLAK_RESOURCE_YAPISI.ID+" =?"+" AND "+
                TASLAK_RESOURCE_YAPISI.VERIM+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.TIP+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.KAYNAK_ID+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.IMALAT+" =?",silinecekVeri1);
        sqLiteDatabase2.close();
        return x;
    }

    public String[] ReadPersonelwekip_adi(String kategori){
        String ekip_adi = null;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {
                PERSONEL_TABLO_YAPISI.KATEGORI,
                PERSONEL_TABLO_YAPISI.EKIP_ID
        };
        String[] selectionArgs = {String.valueOf(kategori)};
        Cursor cursor = sqLiteDatabase.query(PERSONEL_TABLO_YAPISI.TABLO_ADI,columns,PERSONEL_TABLO_YAPISI.KATEGORI+" =?",selectionArgs,null,null,null);
        List<String> list = new ArrayList();

        if (cursor.getCount()>0){
            while (cursor.moveToNext()){

                ekip_adi = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.EKIP_ID));


                    if (!list.contains(ekip_adi)){
                        list.add(ekip_adi);//TODO IIIJJ
                    }


            }
        }
        String[] iscilik = list.toArray(new String[list.size()]);
        cursor.close();
        sqLiteDatabase.close();
        return iscilik;
    }
    public String ReadPersonelwid(String id){
        String kisa_isim =null;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {
                PERSONEL_TABLO_YAPISI.RESOURCE_ID,
                PERSONEL_TABLO_YAPISI.KISA_ISIM
        };
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = sqLiteDatabase.query(PERSONEL_TABLO_YAPISI.TABLO_ADI,columns,PERSONEL_TABLO_YAPISI.RESOURCE_ID+" =?",selectionArgs,null,null,null);
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){

                kisa_isim = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.KISA_ISIM));
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return kisa_isim;
    }
    public String ReadPersonelwisim(String isim){
        String id =null;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {
                PERSONEL_TABLO_YAPISI.RESOURCE_ID,
                PERSONEL_TABLO_YAPISI.KISA_ISIM
        };
        String[] selectionArgs = {String.valueOf(isim)};
        Cursor cursor = sqLiteDatabase.query(PERSONEL_TABLO_YAPISI.TABLO_ADI,columns,PERSONEL_TABLO_YAPISI.KISA_ISIM+" =?",selectionArgs,null,null,null);
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){

                id = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.RESOURCE_ID));
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return id;
    }

    public List[] CreateL2IsgucuKartPart1(String bildiri_id){

        String imalat =null;
        HashMap<String,String> hashMap = new HashMap<>();
        HashMap<String,String> hashMap1 = new HashMap<>();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {
                TASLAK_TABLO_YAPISI.IMALAT,
        };
        String[] selectionArgs = {String.valueOf(bildiri_id),"1"};
        Cursor cursor = sqLiteDatabase.query(TASLAK_TABLO_YAPISI.TABLO_ADI,columns,TASLAK_TABLO_YAPISI.ID+" =? "+" AND "+ TASLAK_TABLO_YAPISI.KOPYA_NO + " =? ",selectionArgs,null,null,null);
        List<String> imalatlar_isim = new ArrayList<>();
        List<String> imalatlar_id = new ArrayList<>();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                imalat = cursor.getString(cursor.getColumnIndex(TASLAK_TABLO_YAPISI.IMALAT));
                imalatlar_isim.add(imalat);
            }
        }
        for (int i =0; i<imalatlar_isim.size();i++){
            imalatlar_id.add(ReadImalatwidforisim(imalatlar_isim.get(i)));
        }
        cursor.close();
        sqLiteDatabase.close();
        List[] lists = new List[]{imalatlar_isim,imalatlar_id};
        return lists;
    }
    public List[] CreateL2MakineKartPart1(String bildiri_id){

        String imalat =null;
        HashMap<String,String> hashMap = new HashMap<>();
        HashMap<String,String> hashMap1 = new HashMap<>();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {
                TASLAK_TABLO_YAPISI.IMALAT,
        };
        String[] selectionArgs = {String.valueOf(bildiri_id),"1"};
        Cursor cursor = sqLiteDatabase.query(TASLAK_TABLO_YAPISI.TABLO_ADI,columns,TASLAK_TABLO_YAPISI.ID+" =? "+" AND "+ TASLAK_TABLO_YAPISI.KOPYA_NO + " =? ",selectionArgs,null,null,null);
        List<String> imalatlar_isim = new ArrayList<>();
        List<String> imalatlar_id = new ArrayList<>();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                imalat = cursor.getString(cursor.getColumnIndex(TASLAK_TABLO_YAPISI.IMALAT));
                imalatlar_isim.add(imalat);
            }
        }
        for (int i =0; i<imalatlar_isim.size();i++){
            imalatlar_id.add(ReadImalatwidforisim(imalatlar_isim.get(i)));
        }
        cursor.close();
        sqLiteDatabase.close();
        List[] lists = new List[]{imalatlar_isim,imalatlar_id};
        return lists;
    }
    public List[] CreateL2AciklamaKartPart1(String bildiri_id){
        String imalat_id =null;
        HashMap<String,String> hashMap = new HashMap<>();
        HashMap<String,String> hashMap1 = new HashMap<>();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {
                TASLAK_ACIKLAMALAR_YAPISI.IMALAT,
        };
        String[] selectionArgs = {String.valueOf(bildiri_id),"1"};
        Cursor cursor = sqLiteDatabase.query(TASLAK_ACIKLAMALAR_YAPISI.TABLO_ADI,columns,TASLAK_ACIKLAMALAR_YAPISI.ID+" =? "+" AND "+ TASLAK_ACIKLAMALAR_YAPISI.KOPYA_NO + " =? ",selectionArgs,null,null,null);
        List<String> imalatlar_isim = new ArrayList<>();
        List<String> imalatlar_id = new ArrayList<>();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                imalat_id = cursor.getString(cursor.getColumnIndex(TASLAK_ACIKLAMALAR_YAPISI.IMALAT));
                if (!imalatlar_id.contains(imalat_id)){
                    imalatlar_id.add(imalat_id);
                }

            }
        }
        for (int i =0; i<imalatlar_id.size();i++){
            imalatlar_isim.add(ReadImalatwidforid(imalatlar_id.get(i)));
        }
        cursor.close();
        sqLiteDatabase.close();
        List[] lists = new List[]{imalatlar_isim,imalatlar_id};
        return lists;
    }

    public HashMap[] CreateL2IsgucuKartPart2(List<String> imalatlar_isim,List<String> imalatlar_id,String bildiri_id){

        HashMap<String,List<String>> hashMap = new HashMap<>();
        HashMap<String,List<String>> hashMap1 = new HashMap<>();

        for (int i =0; i<imalatlar_id.size();i++){
            List[] lists = CreateL2IsgucuKartPart3(bildiri_id,imalatlar_id,i);
            hashMap.put(imalatlar_isim.get(i),lists[0]);
            hashMap1.put(imalatlar_isim.get(i),lists[1]);
        }

        HashMap[] hashMaps = new HashMap[]{hashMap,hashMap1};
        return hashMaps;
    }
    public HashMap[] CreateL2MakineKartPart2(List<String> imalatlar_isim,List<String> imalatlar_id,String bildiri_id){

        HashMap<String,List<String>> hashMap = new HashMap<>();
        HashMap<String,List<String>> hashMap1 = new HashMap<>();
        HashMap<String,List<String>> hashMap2 = new HashMap<>();

        for (int i =0; i<imalatlar_id.size();i++){
            List[] lists = CreateL2MakineKartPart3(bildiri_id,imalatlar_id,i);
            hashMap.put(imalatlar_isim.get(i),lists[0]);
            hashMap1.put(imalatlar_isim.get(i),lists[1]);
            hashMap2.put(imalatlar_isim.get(i),lists[2]);
        }

        HashMap[] hashMaps = new HashMap[]{hashMap,hashMap1,hashMap2};
        return hashMaps;
    }
    public HashMap<String,List<String>> CreateL2AciklamaKartPart2(List<String> imalatlar_isim,List<String> imalatlar_id,String bildiri_id){
        HashMap<String,List<String>> hashMap = new HashMap<>();
        for (int i =0; i<imalatlar_id.size();i++){
            List<String> aciklamalar = CreateL2AciklamaKartPart3(bildiri_id,imalatlar_id,i);
            hashMap.put(imalatlar_isim.get(i),aciklamalar);
        }
        return hashMap;
    }

    public List[] CreateL2IsgucuKartPart3(String bildiri_id,List<String> imalatlar_id,int i){
        SQLiteDatabase sqLiteDatabase1 = getWritableDatabase();
        String[] sutunlar = {
                TASLAK_RESOURCE_YAPISI.IMALAT,
                TASLAK_RESOURCE_YAPISI.PUANTAJ,
                TASLAK_RESOURCE_YAPISI.ID,
                TASLAK_RESOURCE_YAPISI.KATEGORI,
                TASLAK_RESOURCE_YAPISI.VERIM,
                TASLAK_RESOURCE_YAPISI.KAYNAK_ID
        };
        String[] args = {bildiri_id,imalatlar_id.get(i),"iscilik","efor"};
        List<String> kaynak = new ArrayList<>();
        List<String> puantaj = new ArrayList<>();
        Cursor cursor2 = sqLiteDatabase1.query(TASLAK_RESOURCE_YAPISI.TABLO_ADI,sutunlar,TASLAK_RESOURCE_YAPISI.ID+" =? "+" AND "+TASLAK_RESOURCE_YAPISI.IMALAT+" =? "+" AND "+TASLAK_RESOURCE_YAPISI.TIP+" =? "+" AND "+TASLAK_RESOURCE_YAPISI.VERIM+" =? ",args,null,null,null);
        if (cursor2.getCount()>0){
            while (cursor2.moveToNext()){
                kaynak.add(ReadPersonelwid(cursor2.getString(cursor2.getColumnIndex(TASLAK_RESOURCE_YAPISI.KAYNAK_ID))));
                puantaj.add(cursor2.getString(cursor2.getColumnIndex(TASLAK_RESOURCE_YAPISI.PUANTAJ)));

            }
        }
        cursor2.close();
        sqLiteDatabase1.close();
        List[] back = new List[]{kaynak,puantaj};
        return back;

    }
    public List[] CreateL2MakineKartPart3(String bildiri_id,List<String> imalatlar_id,int i){
        SQLiteDatabase sqLiteDatabase1 = getWritableDatabase();
        String[] sutunlar = {
                TASLAK_RESOURCE_YAPISI.IMALAT,
                TASLAK_RESOURCE_YAPISI.PUANTAJ,
                TASLAK_RESOURCE_YAPISI.ID,
                TASLAK_RESOURCE_YAPISI.KATEGORI,
                TASLAK_RESOURCE_YAPISI.VERIM,
                TASLAK_RESOURCE_YAPISI.KAYNAK_ID,
                TASLAK_RESOURCE_YAPISI.SAYI
        };
        String[] args = {bildiri_id,imalatlar_id.get(i),"makine","efor"};
        List<String> kaynak = new ArrayList<>();
        List<String> puantaj = new ArrayList<>();
        List<String> sayi = new ArrayList<>();
        Cursor cursor2 = sqLiteDatabase1.query(TASLAK_RESOURCE_YAPISI.TABLO_ADI,sutunlar,TASLAK_RESOURCE_YAPISI.ID+" =? "+" AND "+TASLAK_RESOURCE_YAPISI.IMALAT+" =? "+" AND "+TASLAK_RESOURCE_YAPISI.TIP+" =? "+" AND "+TASLAK_RESOURCE_YAPISI.VERIM+" =? ",args,null,null,null);
        if (cursor2.getCount()>0){
            while (cursor2.moveToNext()){
                kaynak.add(ReadPersonelwid(cursor2.getString(cursor2.getColumnIndex(TASLAK_RESOURCE_YAPISI.KAYNAK_ID))));
                puantaj.add(cursor2.getString(cursor2.getColumnIndex(TASLAK_RESOURCE_YAPISI.PUANTAJ)));
                sayi.add(cursor2.getString(cursor2.getColumnIndex(TASLAK_RESOURCE_YAPISI.SAYI)));

            }
        }
        cursor2.close();
        sqLiteDatabase1.close();
        List[] back = new List[]{kaynak,puantaj,sayi};
        return back;

    }
    public List<String> CreateL2AciklamaKartPart3(String bildiri_id,List<String> imalatlar_id,int i){
        SQLiteDatabase sqLiteDatabase1 = getWritableDatabase();
        String[] sutunlar = {
                TASLAK_ACIKLAMALAR_YAPISI.IMALAT,
                TASLAK_ACIKLAMALAR_YAPISI.ACIKLAMA
        };
        String[] args = {bildiri_id,imalatlar_id.get(i)};
        List<String> aciklamalar = new ArrayList<>();
        Cursor cursor2 = sqLiteDatabase1.query(TASLAK_ACIKLAMALAR_YAPISI.TABLO_ADI,sutunlar,TASLAK_ACIKLAMALAR_YAPISI.ID+" =? "+" AND "+TASLAK_ACIKLAMALAR_YAPISI.IMALAT+" =? ",args,null,null,null);
        if (cursor2.getCount()>0){
            while (cursor2.moveToNext()){
                aciklamalar.add(cursor2.getString(cursor2.getColumnIndex(TASLAK_ACIKLAMALAR_YAPISI.ACIKLAMA)));
            }
        }
        cursor2.close();
        sqLiteDatabase1.close();
        return aciklamalar;
    }

    public String[] ReadBildiriler(String id){
        String isim = null;
        int rutin = 0;
        int siklik = 0;
        int istek_tarih = 0;
        String istek_saat = null;
        int termin_tarih = 0;
        String termin_saat = null;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {BILDIRILER_TABLO_YAPISI.ID,
                BILDIRILER_TABLO_YAPISI.ISIM,
                BILDIRILER_TABLO_YAPISI.RUTIN,
                BILDIRILER_TABLO_YAPISI.SIKLIK,
                BILDIRILER_TABLO_YAPISI.ISTEK_TARIH,
                BILDIRILER_TABLO_YAPISI.ISTEK_SAAT,
                BILDIRILER_TABLO_YAPISI.TERMIN_TARIH,
                BILDIRILER_TABLO_YAPISI.TERMIN_SAAT};
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = sqLiteDatabase.query(BILDIRILER_TABLO_YAPISI.TABLO_ADI,columns,BILDIRILER_TABLO_YAPISI.ID+" =?",selectionArgs,null,null,null);
        String[] kitapBilgisi = new String[0];
        String[] bilgiler = new String[cursor.getCount()];
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){

                isim = cursor.getString(cursor.getColumnIndex(BILDIRILER_TABLO_YAPISI.ISIM));
                rutin = cursor.getInt(cursor.getColumnIndex(BILDIRILER_TABLO_YAPISI.RUTIN));
                siklik = cursor.getInt(cursor.getColumnIndex(BILDIRILER_TABLO_YAPISI.SIKLIK));
                istek_tarih = cursor.getInt(cursor.getColumnIndex(BILDIRILER_TABLO_YAPISI.ISTEK_TARIH));
                istek_saat = cursor.getString(cursor.getColumnIndex(BILDIRILER_TABLO_YAPISI.ISTEK_SAAT));
                termin_tarih = cursor.getInt(cursor.getColumnIndex(BILDIRILER_TABLO_YAPISI.TERMIN_TARIH));
                termin_saat = cursor.getString(cursor.getColumnIndex(BILDIRILER_TABLO_YAPISI.TERMIN_SAAT));
                kitapBilgisi = new String[]{
                        String.valueOf(id),
                        isim,
                        String.valueOf(rutin),
                        String.valueOf(siklik),
                        String.valueOf(istek_tarih),
                        istek_saat,
                        String.valueOf(termin_tarih),
                        termin_saat
                };


            }
        } else {
            kitapBilgisi = new String[]{String.valueOf(cursor.getCount())};
        }
        cursor.close();
        sqLiteDatabase.close();
        return kitapBilgisi;
    }
    public String[] ReadBildirilerwIsim(String isim){
        String id = null;
        int rutin = 0;
        int siklik = 0;
        int istek_tarih = 0;
        String istek_saat = null;
        int termin_tarih = 0;
        String termin_saat = null;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {BILDIRILER_TABLO_YAPISI.ID,
                BILDIRILER_TABLO_YAPISI.ISIM,
                BILDIRILER_TABLO_YAPISI.RUTIN,
                BILDIRILER_TABLO_YAPISI.SIKLIK,
                BILDIRILER_TABLO_YAPISI.ISTEK_TARIH,
                BILDIRILER_TABLO_YAPISI.ISTEK_SAAT,
                BILDIRILER_TABLO_YAPISI.TERMIN_TARIH,
                BILDIRILER_TABLO_YAPISI.TERMIN_SAAT};
        String[] selectionArgs = {String.valueOf(isim)};
        Cursor cursor = sqLiteDatabase.query(BILDIRILER_TABLO_YAPISI.TABLO_ADI,columns,BILDIRILER_TABLO_YAPISI.ISIM+" =?",selectionArgs,null,null,null);
        String[] kitapBilgisi = new String[0];
        String[] bilgiler = new String[cursor.getCount()];
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){

                id = cursor.getString(cursor.getColumnIndex(BILDIRILER_TABLO_YAPISI.ID));
                rutin = cursor.getInt(cursor.getColumnIndex(BILDIRILER_TABLO_YAPISI.RUTIN));
                siklik = cursor.getInt(cursor.getColumnIndex(BILDIRILER_TABLO_YAPISI.SIKLIK));
                istek_tarih = cursor.getInt(cursor.getColumnIndex(BILDIRILER_TABLO_YAPISI.ISTEK_TARIH));
                istek_saat = cursor.getString(cursor.getColumnIndex(BILDIRILER_TABLO_YAPISI.ISTEK_SAAT));
                termin_tarih = cursor.getInt(cursor.getColumnIndex(BILDIRILER_TABLO_YAPISI.TERMIN_TARIH));
                termin_saat = cursor.getString(cursor.getColumnIndex(BILDIRILER_TABLO_YAPISI.TERMIN_SAAT));
                kitapBilgisi = new String[]{
                        String.valueOf(id),
                        isim,
                        String.valueOf(rutin),
                        String.valueOf(siklik),
                        String.valueOf(istek_tarih),
                        istek_saat,
                        String.valueOf(termin_tarih),
                        termin_saat
                };


            }
        } else {
            kitapBilgisi = new String[]{String.valueOf(cursor.getCount())};
        }
        cursor.close();
        sqLiteDatabase.close();
        return kitapBilgisi;
    }
    public String[] ReadSektor(String sektor){

        int id = 0;
        String hat = null;
        String hat_no = null;
        int km_bas = 0;
        int km_bit = 0;
        int aktif = 0;
        String imalat = null;
        String bolge = null;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {KITAP_TABLO_YAPISI.ID,
                KITAP_TABLO_YAPISI.ISIM,
                KITAP_TABLO_YAPISI.HAT,
                KITAP_TABLO_YAPISI.HAT_NO,
                KITAP_TABLO_YAPISI.KM_BAS,
                KITAP_TABLO_YAPISI.KM_BIT,
                KITAP_TABLO_YAPISI.AKTIF,
                KITAP_TABLO_YAPISI.IMALAT,
                KITAP_TABLO_YAPISI.BOLGE};
        String[] selectionArgs = {String.valueOf(sektor)};
        Cursor cursor = sqLiteDatabase.query(KITAP_TABLO_YAPISI.TABLO_ADI,columns,KITAP_TABLO_YAPISI.ISIM+" =?",selectionArgs,null,null,null);
        String[] kitapBilgisi = new String[0];
        String[] bilgiler = new String[cursor.getCount()];
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){

                id = cursor.getInt(cursor.getColumnIndex(KITAP_TABLO_YAPISI.ID));
                hat = cursor.getString(cursor.getColumnIndex(KITAP_TABLO_YAPISI.HAT));
                hat_no = cursor.getString(cursor.getColumnIndex(KITAP_TABLO_YAPISI.HAT_NO));
                km_bas = cursor.getInt(cursor.getColumnIndex(KITAP_TABLO_YAPISI.KM_BAS));
                km_bit = cursor.getInt(cursor.getColumnIndex(KITAP_TABLO_YAPISI.KM_BIT));
                aktif = cursor.getInt(cursor.getColumnIndex(KITAP_TABLO_YAPISI.AKTIF));
                imalat = cursor.getString(cursor.getColumnIndex(KITAP_TABLO_YAPISI.IMALAT));
                bolge = cursor.getString(cursor.getColumnIndex(KITAP_TABLO_YAPISI.BOLGE));
                kitapBilgisi = new String[]{
                        String.valueOf(id),
                        hat,
                        hat_no,
                        String.valueOf(km_bas),
                        String.valueOf(km_bit),
                        String.valueOf(aktif),
                        imalat,
                        bolge
                };


            }
        } else {
            kitapBilgisi = new String[]{String.valueOf(cursor.getCount())};
        }
        cursor.close();
        sqLiteDatabase.close();
        return kitapBilgisi;
    }
    public String[] ReadSektorwImalat(String imalat){//ımalat girince sektör filtrelemesini yapan read fonksiyonu
        int id = 0;
        String hat = null;
        String hat_no = null;
        int km_bas = 0;
        int km_bit = 0;
        int aktif = 0;
        String sektor = null;
        String bolge = null;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {KITAP_TABLO_YAPISI.ID,
                KITAP_TABLO_YAPISI.ISIM,
                KITAP_TABLO_YAPISI.HAT,
                KITAP_TABLO_YAPISI.HAT_NO,
                KITAP_TABLO_YAPISI.KM_BAS,
                KITAP_TABLO_YAPISI.KM_BIT,
                KITAP_TABLO_YAPISI.AKTIF,
                KITAP_TABLO_YAPISI.IMALAT,
                KITAP_TABLO_YAPISI.BOLGE};
        String[] selectionArgs = {String.valueOf(imalat)};
        Cursor cursor = sqLiteDatabase.query(KITAP_TABLO_YAPISI.TABLO_ADI,columns,KITAP_TABLO_YAPISI.IMALAT+" =?",selectionArgs,null,null,null);
        String[] kitapBilgisi = new String[0];
        String[] sektorler = new String[cursor.getCount()];
        int i = 0;
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){

                id = cursor.getInt(cursor.getColumnIndex(KITAP_TABLO_YAPISI.ID));
                hat = cursor.getString(cursor.getColumnIndex(KITAP_TABLO_YAPISI.HAT));
                hat_no = cursor.getString(cursor.getColumnIndex(KITAP_TABLO_YAPISI.HAT_NO));
                km_bas = cursor.getInt(cursor.getColumnIndex(KITAP_TABLO_YAPISI.KM_BAS));
                km_bit = cursor.getInt(cursor.getColumnIndex(KITAP_TABLO_YAPISI.KM_BIT));
                aktif = cursor.getInt(cursor.getColumnIndex(KITAP_TABLO_YAPISI.AKTIF));
                sektor = cursor.getString(cursor.getColumnIndex(KITAP_TABLO_YAPISI.ISIM));
                bolge = cursor.getString(cursor.getColumnIndex(KITAP_TABLO_YAPISI.BOLGE));
                kitapBilgisi = new String[]{
                        String.valueOf(id),
                        hat,
                        hat_no,
                        String.valueOf(km_bas),
                        String.valueOf(km_bit),
                        String.valueOf(aktif),
                        sektor,
                        bolge
                };
                sektorler[i] = kitapBilgisi[6];
                i++;
            }
        } else {
            kitapBilgisi = new String[]{String.valueOf(cursor.getCount())};
        }
        cursor.close();
        sqLiteDatabase.close();
        return sektorler;

    }
    public String[] ReadSektor(int id){

        String isim= null;
        String hat = null;
        String hat_no = null;
        int km_bas = 0;
        int km_bit = 0;
        int aktif = 0;
        String imalat = null;
        String bolge = null;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {KITAP_TABLO_YAPISI.ID,
                KITAP_TABLO_YAPISI.ISIM,
                KITAP_TABLO_YAPISI.HAT,
                KITAP_TABLO_YAPISI.HAT_NO,
                KITAP_TABLO_YAPISI.KM_BAS,
                KITAP_TABLO_YAPISI.KM_BIT,
                KITAP_TABLO_YAPISI.AKTIF,
                KITAP_TABLO_YAPISI.IMALAT,
                KITAP_TABLO_YAPISI.BOLGE};
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = sqLiteDatabase.query(KITAP_TABLO_YAPISI.TABLO_ADI,columns,KITAP_TABLO_YAPISI.ID+" =?",selectionArgs,null,null,null);
        String[] kitapBilgisi = new String[0];
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){

                isim = cursor.getString(cursor.getColumnIndex(KITAP_TABLO_YAPISI.ISIM));
                hat = cursor.getString(cursor.getColumnIndex(KITAP_TABLO_YAPISI.HAT));
                hat_no = cursor.getString(cursor.getColumnIndex(KITAP_TABLO_YAPISI.HAT_NO));
                km_bas = cursor.getInt(cursor.getColumnIndex(KITAP_TABLO_YAPISI.KM_BAS));
                km_bit = cursor.getInt(cursor.getColumnIndex(KITAP_TABLO_YAPISI.KM_BIT));
                aktif = cursor.getInt(cursor.getColumnIndex(KITAP_TABLO_YAPISI.AKTIF));
                imalat = cursor.getString(cursor.getColumnIndex(KITAP_TABLO_YAPISI.IMALAT));
                bolge = cursor.getString(cursor.getColumnIndex(KITAP_TABLO_YAPISI.BOLGE));
                kitapBilgisi = new String[]{
                        isim,
                        hat,
                        hat_no,
                        String.valueOf(km_bas),
                        String.valueOf(km_bit),
                        String.valueOf(aktif),
                        imalat,
                        bolge
                };

            }
        } else {
            kitapBilgisi = new String[]{String.valueOf(cursor.getCount())};
        }
        cursor.close();
        sqLiteDatabase.close();
        return kitapBilgisi;
    }
    public String  ReadImalatwidforisim(String isim){
        String id = null;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {IMALAT_TABLO_YAPISI.ID,
                //IMALAT_TABLO_YAPISI.ORDER,
                IMALAT_TABLO_YAPISI.ISIM,
        };
        String[] selectionArgs = {String.valueOf(isim)};
        Cursor cursor = sqLiteDatabase.query(IMALAT_TABLO_YAPISI.TABLO_ADI,columns,IMALAT_TABLO_YAPISI.ISIM+" =?",selectionArgs,null,null,null);
        String[] imalatBilgisi = new String[0];
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                id = cursor.getString(cursor.getColumnIndex(IMALAT_TABLO_YAPISI.ID));
            }
        } else {
            imalatBilgisi = new String[]{String.valueOf(cursor.getCount())};
        }
        cursor.close();
        sqLiteDatabase.close();
        return id;
    }
    public String  ReadImalatwidforid(String id){
        String isim = null;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {
                IMALAT_TABLO_YAPISI.ISIM,
        };
        String[] selectionArgs = {id};
        Cursor cursor = sqLiteDatabase.query(IMALAT_TABLO_YAPISI.TABLO_ADI,columns,IMALAT_TABLO_YAPISI.ID+" =?",selectionArgs,null,null,null);
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                isim = cursor.getString(cursor.getColumnIndex(IMALAT_TABLO_YAPISI.ISIM));
            }
        } else {
        }
        cursor.close();
        sqLiteDatabase.close();
        return isim;
    }
    public String[]  ReadImalat(String id){
        String isim = null;
        String oncelik = null;
        String birim = null;
        double bp_as = 0;
        int metraj = 0;
        String bildiri = null;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {IMALAT_TABLO_YAPISI.ID,
               //IMALAT_TABLO_YAPISI.ORDER,
                IMALAT_TABLO_YAPISI.ISIM,
                IMALAT_TABLO_YAPISI.ONCELIK,
                IMALAT_TABLO_YAPISI.BIRIM,
                IMALAT_TABLO_YAPISI.BP_AS,
                IMALAT_TABLO_YAPISI.METRAJ,
                IMALAT_TABLO_YAPISI.BILDIRI,
        };
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = sqLiteDatabase.query(IMALAT_TABLO_YAPISI.TABLO_ADI,columns,IMALAT_TABLO_YAPISI.ID+" =?",selectionArgs,null,null,null);
        String[] imalatBilgisi = new String[0];
       // List list = new ArrayList();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){

                //id = cursor.getString(cursor.getColumnIndex(IMALAT_TABLO_YAPISI.ID));
                isim = cursor.getString(cursor.getColumnIndex(IMALAT_TABLO_YAPISI.ISIM));
                oncelik = cursor.getString(cursor.getColumnIndex(IMALAT_TABLO_YAPISI.ONCELIK));
                birim = cursor.getString(cursor.getColumnIndex(IMALAT_TABLO_YAPISI.BIRIM));
                bp_as = cursor.getDouble(cursor.getColumnIndex(IMALAT_TABLO_YAPISI.BP_AS));
                metraj = cursor.getInt(cursor.getColumnIndex(IMALAT_TABLO_YAPISI.METRAJ));
                bildiri = cursor.getString(cursor.getColumnIndex(IMALAT_TABLO_YAPISI.BILDIRI));
                imalatBilgisi = new String[]{
                        isim,
                        oncelik,
                        birim,
                        String.valueOf(bp_as),
                        String.valueOf(metraj),
                        bildiri,
                };
              //  list.add(imalatBilgisi);

            }
        } else {
            imalatBilgisi = new String[]{String.valueOf(cursor.getCount())};
        }
        cursor.close();
        sqLiteDatabase.close();
        return imalatBilgisi;
    }
    public List ReadImalatwOncelik(String oncelik){

        String isim = null;
        String id = null;
        String birim = null;
        double bp_as = 0;
        int metraj = 0;
        String bildiri = null;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {IMALAT_TABLO_YAPISI.ID,
                //IMALAT_TABLO_YAPISI.ORDER,
                IMALAT_TABLO_YAPISI.ISIM,
                IMALAT_TABLO_YAPISI.ONCELIK,
                IMALAT_TABLO_YAPISI.BIRIM,
                IMALAT_TABLO_YAPISI.BP_AS,
                IMALAT_TABLO_YAPISI.METRAJ,
                IMALAT_TABLO_YAPISI.BILDIRI,
        };
        String[] selectionArgs = {String.valueOf(oncelik)};
        Cursor cursor = sqLiteDatabase.query(IMALAT_TABLO_YAPISI.TABLO_ADI,columns,IMALAT_TABLO_YAPISI.ONCELIK+" =?",selectionArgs,null,null,null);
        String[] imalatBilgisi = new String[0];
        List list = new ArrayList();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){

                id = cursor.getString(cursor.getColumnIndex(IMALAT_TABLO_YAPISI.ID));
                isim = cursor.getString(cursor.getColumnIndex(IMALAT_TABLO_YAPISI.ISIM));
                //oncelik = cursor.getString(cursor.getColumnIndex(IMALAT_TABLO_YAPISI.ONCELIK));
                birim = cursor.getString(cursor.getColumnIndex(IMALAT_TABLO_YAPISI.BIRIM));
                bp_as = cursor.getDouble(cursor.getColumnIndex(IMALAT_TABLO_YAPISI.BP_AS));
                metraj = cursor.getInt(cursor.getColumnIndex(IMALAT_TABLO_YAPISI.METRAJ));
                bildiri = cursor.getString(cursor.getColumnIndex(IMALAT_TABLO_YAPISI.BILDIRI));
                imalatBilgisi = new String[]{
                        isim,
                        id,
                        birim,
                        String.valueOf(bp_as),
                        String.valueOf(metraj),
                        bildiri,
                };
                list.add(imalatBilgisi);

            }
        } else {
            imalatBilgisi = new String[]{String.valueOf(cursor.getCount())};
        }
        cursor.close();
        sqLiteDatabase.close();
        return list;

    }
    public String[] ReadImalatwisim(String isim){
        String id = null;
       // String order = null;
        String oncelik = null;
        String birim = null;
        double bp_as = 0;
        int metraj = 0;
        String bildiri = null;
        int vt_sektor =0;
        int vt_mesafe = 0;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {IMALAT_TABLO_YAPISI.ID,
             //   IMALAT_TABLO_YAPISI.ORDER,
                IMALAT_TABLO_YAPISI.ISIM,
                IMALAT_TABLO_YAPISI.ONCELIK,
                IMALAT_TABLO_YAPISI.BIRIM,
                IMALAT_TABLO_YAPISI.BP_AS,
                IMALAT_TABLO_YAPISI.METRAJ,
                IMALAT_TABLO_YAPISI.BILDIRI,
                IMALAT_TABLO_YAPISI.VT_SEKTOR,
                IMALAT_TABLO_YAPISI.VT_MESAFE
        };
        String[] selectionArgs = {String.valueOf(isim)};
        Cursor cursor = sqLiteDatabase.query(IMALAT_TABLO_YAPISI.TABLO_ADI,columns,IMALAT_TABLO_YAPISI.ISIM+" =?",selectionArgs,null,null,null);
        String[] imalatBilgisi = new String[0];
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){

                id = cursor.getString(cursor.getColumnIndex(IMALAT_TABLO_YAPISI.ID));
               // order = cursor.getString(cursor.getColumnIndex(IMALfAT_TABLO_YAPISI.ORDER));
                oncelik = cursor.getString(cursor.getColumnIndex(IMALAT_TABLO_YAPISI.ONCELIK));
                birim = cursor.getString(cursor.getColumnIndex(IMALAT_TABLO_YAPISI.BIRIM));
                bp_as = cursor.getDouble(cursor.getColumnIndex(IMALAT_TABLO_YAPISI.BP_AS));
                metraj = cursor.getInt(cursor.getColumnIndex(IMALAT_TABLO_YAPISI.METRAJ));
                bildiri = cursor.getString(cursor.getColumnIndex(IMALAT_TABLO_YAPISI.BILDIRI));
                vt_sektor = cursor.getInt(cursor.getColumnIndex(IMALAT_TABLO_YAPISI.VT_SEKTOR));
                vt_mesafe = cursor.getInt(cursor.getColumnIndex(IMALAT_TABLO_YAPISI.VT_MESAFE));
                imalatBilgisi = new String[]{
                        String.valueOf(id),
                        oncelik,
                        birim,
                        String.valueOf(bp_as),
                        String.valueOf(metraj),
                        bildiri,
                        String.valueOf(vt_sektor),
                        String.valueOf(vt_mesafe)
                };

            }
        } else {
            imalatBilgisi = new String[]{String.valueOf(cursor.getCount())};
        }
        cursor.close();
        sqLiteDatabase.close();
        return imalatBilgisi;
    }
    public Integer ReadTaslakfimalatKopya(String id,String tarih,String imalat){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {
                TASLAK_TABLO_YAPISI.ID,
                TASLAK_TABLO_YAPISI.TARIH,
                TASLAK_TABLO_YAPISI.IMALAT,
        };
        String[] selectionArgs = {String.valueOf(id),tarih,imalat};
        Cursor cursor = sqLiteDatabase.query(TASLAK_TABLO_YAPISI.TABLO_ADI,columns,TASLAK_TABLO_YAPISI.ID+" =?"+ " AND "+ TASLAK_TABLO_YAPISI.TARIH+" =?"+ " AND "+ TASLAK_TABLO_YAPISI.IMALAT+" =?",selectionArgs,null,null,null);
        Integer kopya_sayisi = cursor.getCount();
        cursor.close();
        sqLiteDatabase.close();
        return kopya_sayisi;
    }
    public List[] ReadBildiriListesiforList(int kullanici_id,int sent){

        String isim = null;
        String tarih = null;
        String deadline_tarih = null;
        String deadline_saat = null;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {BILDIRI_LISTESI_TABLO_YAPISI.KOD,
                BILDIRI_LISTESI_TABLO_YAPISI.KULLANICI_ID,
                BILDIRI_LISTESI_TABLO_YAPISI.BILDIRI_ID,
                BILDIRI_LISTESI_TABLO_YAPISI.ISIM,
                BILDIRI_LISTESI_TABLO_YAPISI.TARIH,
                BILDIRI_LISTESI_TABLO_YAPISI.DEADLINE_TARIH,
                BILDIRI_LISTESI_TABLO_YAPISI.DEADLINE_SAAT,
                BILDIRI_LISTESI_TABLO_YAPISI.SENT};
        String[] selectionArgs = {String.valueOf(kullanici_id),String.valueOf(sent)};
        Cursor cursor = sqLiteDatabase.query(BILDIRI_LISTESI_TABLO_YAPISI.TABLO_ADI,columns,BILDIRI_LISTESI_TABLO_YAPISI.KULLANICI_ID+" =?"+ " AND "+BILDIRI_LISTESI_TABLO_YAPISI.SENT+" =?",selectionArgs,null,null,null);
        List<String> row1 = new ArrayList();
        List<String> row2= new ArrayList();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                isim= cursor.getString(cursor.getColumnIndex(BILDIRI_LISTESI_TABLO_YAPISI.ISIM));
                tarih = cursor.getString(cursor.getColumnIndex(BILDIRI_LISTESI_TABLO_YAPISI.TARIH));
                deadline_tarih= cursor.getString(cursor.getColumnIndex(BILDIRI_LISTESI_TABLO_YAPISI.DEADLINE_TARIH));
                deadline_saat = cursor.getString(cursor.getColumnIndex(BILDIRI_LISTESI_TABLO_YAPISI.DEADLINE_SAAT));
                row1.add(isim+" ("+String.valueOf(tarih)+")");
                row2.add("Son Bildiri Tarihi: "+ String.valueOf(deadline_tarih)+" "+ String.valueOf(deadline_saat));
            }
        } else {
            //do nothing
        }
        List[] rows = new List[]{row1,row2};
        cursor.close();
        sqLiteDatabase.close();
        return rows;
    }
    public List[] ReadTaslakfList1(String id,String tarih){
        String imalat = null;
        String sektor = null;
        int hat_no = 0;
        int km_bas = 0;
        int km_son = 0;
        int mesafe = 0;
        String birim = null;
        int sent = 0;
        int kopya_no = 0;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {
                TASLAK_TABLO_YAPISI.ID,
                TASLAK_TABLO_YAPISI.TARIH,
                TASLAK_TABLO_YAPISI.IMALAT,
                TASLAK_TABLO_YAPISI.KOPYA_NO,
                TASLAK_TABLO_YAPISI.SEKTOR,
                TASLAK_TABLO_YAPISI.HAT_NO,
                TASLAK_TABLO_YAPISI.KM_BAS,
                TASLAK_TABLO_YAPISI.KM_SON,
                TASLAK_TABLO_YAPISI.MESAFE,
                TASLAK_TABLO_YAPISI.BIRIM,
                TASLAK_TABLO_YAPISI.SENT
        };
        String[] selectionArgs = {String.valueOf(id),tarih};
        Cursor cursor = sqLiteDatabase.query(TASLAK_TABLO_YAPISI.TABLO_ADI,columns,TASLAK_TABLO_YAPISI.ID+" =?"+" AND "+ TASLAK_TABLO_YAPISI.TARIH+" =? ",selectionArgs,null,null,null);
        List<String> imalatlar = new ArrayList();
        List<String> imalatlar_id = new ArrayList();
        List<String> mesafeler = new ArrayList();
        List<String> km_baslar = new ArrayList();
        List<String> km_sonlar = new ArrayList();
        List<String> mesafe_birimler = new ArrayList();
        List<Integer> kopya_nolar = new ArrayList();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                id = cursor.getString(cursor.getColumnIndex(TASLAK_TABLO_YAPISI.ID));
                tarih = cursor.getString(cursor.getColumnIndex(TASLAK_TABLO_YAPISI.TARIH));
                imalat = cursor.getString(cursor.getColumnIndex(TASLAK_TABLO_YAPISI.IMALAT));
                kopya_no = cursor.getInt(cursor.getColumnIndex(TASLAK_TABLO_YAPISI.KOPYA_NO));
                sektor = cursor.getString(cursor.getColumnIndex(TASLAK_TABLO_YAPISI.SEKTOR));
                hat_no = cursor.getInt(cursor.getColumnIndex(TASLAK_TABLO_YAPISI.HAT_NO));
                km_bas = cursor.getInt(cursor.getColumnIndex(TASLAK_TABLO_YAPISI.KM_BAS));
                km_son = cursor.getInt(cursor.getColumnIndex(TASLAK_TABLO_YAPISI.KM_SON));
                mesafe = cursor.getInt(cursor.getColumnIndex(TASLAK_TABLO_YAPISI.MESAFE));
                birim = cursor.getString(cursor.getColumnIndex(TASLAK_TABLO_YAPISI.BIRIM));
                sent = cursor.getInt(cursor.getColumnIndex(TASLAK_TABLO_YAPISI.SENT));
                imalatlar.add(imalat);
                imalatlar_id.add(ReadImalatwidforisim(imalat));
                mesafeler.add(String.valueOf(mesafe));
                km_baslar.add(String.valueOf(km_bas));
                km_sonlar.add(String.valueOf(km_son));
                mesafe_birimler.add(ReadImalatwisim(imalat)[2]);
                kopya_nolar.add(kopya_no);
            }
        } else {
            // donothing because all lists willbe length of 0
        }
        List[] sonuc = new List[]{imalatlar_id,mesafeler,km_baslar,km_sonlar,mesafe_birimler,kopya_nolar,imalatlar};
        cursor.close();
        sqLiteDatabase.close();
        return sonuc;
    }
    public List[] ReadTaslakfList(String id,String tarih){
        String imalat = null;
        String sektor = null;
        int hat_no = 0;
        int km_bas = 0;
        int km_son = 0;
        int mesafe = 0;
        String birim = null;
        int sent = 0;
        int kopya_no = 0;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {
                TASLAK_TABLO_YAPISI.ID,
                TASLAK_TABLO_YAPISI.TARIH,
                TASLAK_TABLO_YAPISI.IMALAT,
                TASLAK_TABLO_YAPISI.KOPYA_NO,
                TASLAK_TABLO_YAPISI.SEKTOR,
                TASLAK_TABLO_YAPISI.HAT_NO,
                TASLAK_TABLO_YAPISI.KM_BAS,
                TASLAK_TABLO_YAPISI.KM_SON,
                TASLAK_TABLO_YAPISI.MESAFE,
                TASLAK_TABLO_YAPISI.BIRIM,
                TASLAK_TABLO_YAPISI.SENT
        };
        String[] selectionArgs = {String.valueOf(id),tarih};
        Cursor cursor = sqLiteDatabase.query(TASLAK_TABLO_YAPISI.TABLO_ADI,columns,TASLAK_TABLO_YAPISI.ID+" =?"+" AND "+ TASLAK_TABLO_YAPISI.TARIH+" =? ",selectionArgs,null,null,null);
        List<String> imalatlar = new ArrayList();
        List<String> mesafeler = new ArrayList();
        List<String> km_baslar = new ArrayList();
        List<String> km_sonlar = new ArrayList();
        List<String> mesafe_birimler = new ArrayList();
        List<Integer> kopya_nolar = new ArrayList();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                id = cursor.getString(cursor.getColumnIndex(TASLAK_TABLO_YAPISI.ID));
                tarih = cursor.getString(cursor.getColumnIndex(TASLAK_TABLO_YAPISI.TARIH));
                imalat = cursor.getString(cursor.getColumnIndex(TASLAK_TABLO_YAPISI.IMALAT));
                kopya_no = cursor.getInt(cursor.getColumnIndex(TASLAK_TABLO_YAPISI.KOPYA_NO));
                sektor = cursor.getString(cursor.getColumnIndex(TASLAK_TABLO_YAPISI.SEKTOR));
                hat_no = cursor.getInt(cursor.getColumnIndex(TASLAK_TABLO_YAPISI.HAT_NO));
                km_bas = cursor.getInt(cursor.getColumnIndex(TASLAK_TABLO_YAPISI.KM_BAS));
                km_son = cursor.getInt(cursor.getColumnIndex(TASLAK_TABLO_YAPISI.KM_SON));
                mesafe = cursor.getInt(cursor.getColumnIndex(TASLAK_TABLO_YAPISI.MESAFE));
                birim = cursor.getString(cursor.getColumnIndex(TASLAK_TABLO_YAPISI.BIRIM));
                sent = cursor.getInt(cursor.getColumnIndex(TASLAK_TABLO_YAPISI.SENT));
                imalatlar.add(imalat);
                mesafeler.add(String.valueOf(mesafe));
                km_baslar.add(String.valueOf(km_bas));
                km_sonlar.add(String.valueOf(km_son));
                mesafe_birimler.add(ReadImalatwisim(imalat)[2]);
                kopya_nolar.add(kopya_no);
            }
        } else {
            // donothing because all lists willbe length of 0
        }
        List[] sonuc = new List[]{imalatlar,mesafeler,km_baslar,km_sonlar,mesafe_birimler,kopya_nolar};
        cursor.close();
        sqLiteDatabase.close();
        return sonuc;
    }
    public List[] ReadTaslakResourceflist(String id,String[] imalat){
           List<String> personel_puantajlar = new ArrayList();
        List<String> makine_puantajlar = new ArrayList();
        List<String> personel_sayilari = new ArrayList();
        List<String> makine_sayilari = new ArrayList();
        List<String> verimlilik_orani = new ArrayList();
        List<String> ort_verimsizlik_sureler = new ArrayList();


        for (int i = 0; i<imalat.length;i++){
            String kaynak_id = null;
            String kategori = null;
            int puantaj = 0;
            String verim = null;
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            String[] columns = {
                    TASLAK_RESOURCE_YAPISI.ID,
                    TASLAK_RESOURCE_YAPISI.TARIH,
                    TASLAK_RESOURCE_YAPISI.KAYNAK_ID,
                    TASLAK_RESOURCE_YAPISI.KATEGORI,
                    TASLAK_RESOURCE_YAPISI.IMALAT,
                    TASLAK_RESOURCE_YAPISI.PUANTAJ,
                    TASLAK_RESOURCE_YAPISI.VERIM,
                    TASLAK_RESOURCE_YAPISI.SAYI
            };

            String[] selectionArgs = {String.valueOf(id),imalat[i]};

            Cursor cursor = sqLiteDatabase.query(TASLAK_RESOURCE_YAPISI.TABLO_ADI,columns,TASLAK_RESOURCE_YAPISI.ID+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.IMALAT+" =? ",selectionArgs,null,null,null);

            int kisi_sayisi=0;
            int personel_puantaj = 0;
            int makine_sayisi = 0;
            int makine_puantaj=0;
            int iscilik_verimsizlik=0;
            int makine_verimsizlik=0;


            if (cursor.getCount()>0){
                while (cursor.moveToNext()){
                    kategori = cursor.getString(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.KATEGORI));
                    puantaj = cursor.getInt(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.PUANTAJ));
                    if (kategori.equals("iscilik")){
                        if (puantaj>0){
                            kisi_sayisi = kisi_sayisi + 1;
                            personel_puantaj = personel_puantaj + puantaj;
                        }else if (puantaj<0){
                            iscilik_verimsizlik = iscilik_verimsizlik + puantaj;


                        }
                    }else if (kategori.equals("makine")){
                        if (puantaj>0){
                            makine_sayisi = makine_sayisi + 1;
                            makine_puantaj = makine_puantaj + puantaj;
                        }else if (puantaj<0){
                            makine_verimsizlik = makine_verimsizlik + puantaj;

                        }
                    }
                }
                int verimlilik = (personel_puantaj+ iscilik_verimsizlik)*100/personel_puantaj;
                double ort_verimsizlik_sure = ((double)iscilik_verimsizlik)/((double)kisi_sayisi);
                personel_puantajlar.add(String.valueOf(personel_puantaj));
                personel_sayilari.add(String.valueOf(kisi_sayisi));
                makine_puantajlar.add(String.valueOf(makine_puantaj));
                makine_sayilari.add(String.valueOf(makine_sayisi));
                verimlilik_orani.add(String.valueOf(verimlilik));
                //ort_verimsizlik_sureler.add(String.valueOf(String.format("%.2f", ort_verimsizlik_sure)));
                DecimalFormat df = new DecimalFormat("0.00");
                ort_verimsizlik_sureler.add(String.valueOf(df.format(ort_verimsizlik_sure)));

            } else {
                personel_puantajlar.add("0");
                personel_sayilari.add("0");
                makine_puantajlar.add("0");
                makine_sayilari.add("0");
                verimlilik_orani.add("100");
                ort_verimsizlik_sureler.add("0.00");
            }


            cursor.close();
            sqLiteDatabase.close();


            personel_puantajlar.add("0");
            personel_sayilari.add("0");
            makine_puantajlar.add("0");
            makine_sayilari.add("0");
            verimlilik_orani.add("100");
            ort_verimsizlik_sureler.add("0.00");
        }
        List[] degerler = new List[]{personel_sayilari,personel_puantajlar,makine_sayilari,makine_puantajlar,verimlilik_orani,ort_verimsizlik_sureler};
        return degerler;
    }
    public List[] ReadTaslakResourceflist1(String id,String[] imalat){
        List<String> personel_puantajlar = new ArrayList();
        List<String> makine_puantajlar = new ArrayList();
        List<String> personel_sayilari = new ArrayList();
        List<String> makine_sayilari = new ArrayList();
        List<String> verimlilik_orani = new ArrayList();
        List<String> ort_verimsizlik_sureler = new ArrayList();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String kategori = null;
        int puantaj = 0;
        String[] columns = {
                TASLAK_RESOURCE_YAPISI.ID,
                TASLAK_RESOURCE_YAPISI.TARIH,
                TASLAK_RESOURCE_YAPISI.KAYNAK_ID,
                TASLAK_RESOURCE_YAPISI.KATEGORI,
                TASLAK_RESOURCE_YAPISI.IMALAT,
                TASLAK_RESOURCE_YAPISI.PUANTAJ,
                TASLAK_RESOURCE_YAPISI.VERIM,
                TASLAK_RESOURCE_YAPISI.SAYI
        };
        String[] selectionArgs = {String.valueOf(id),ReadImalatwidforisim(imalat[0])};
        Cursor cursor = sqLiteDatabase.query(TASLAK_RESOURCE_YAPISI.TABLO_ADI,columns,TASLAK_RESOURCE_YAPISI.ID+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.IMALAT+" =? ",selectionArgs,null,null,null);
        int kisi_sayisi=0;
        int personel_puantaj = 0;
        int makine_sayisi = 0;
        int makine_puantaj=0;
        int iscilik_verimsizlik=0;
        int makine_verimsizlik=0;
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                kategori = cursor.getString(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.KATEGORI));
                puantaj = cursor.getInt(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.PUANTAJ));
                if (kategori.equals("iscilik")){
                    if (puantaj>0){
                        kisi_sayisi = kisi_sayisi + 1;
                        personel_puantaj = personel_puantaj + puantaj;
                    }else if (puantaj<0){
                        iscilik_verimsizlik = iscilik_verimsizlik + puantaj;
                    }
                }else if (kategori.equals("makine")){
                    if (puantaj>0){
                        makine_sayisi = makine_sayisi + 1;
                        makine_puantaj = makine_puantaj + puantaj;
                    }else if (puantaj<0){
                        makine_verimsizlik = makine_verimsizlik + puantaj;
                    }
                }
            }
            int verimlilik = (personel_puantaj+ iscilik_verimsizlik)*100/personel_puantaj;
            double ort_verimsizlik_sure = ((double)iscilik_verimsizlik)/((double)kisi_sayisi);
            personel_puantajlar.add(String.valueOf(personel_puantaj));
            personel_sayilari.add(String.valueOf(kisi_sayisi));
            makine_puantajlar.add(String.valueOf(makine_puantaj));
            makine_sayilari.add(String.valueOf(makine_sayisi));
            verimlilik_orani.add(String.valueOf(verimlilik));
            //ort_verimsizlik_sureler.add(String.valueOf(String.format("%.2f", ort_verimsizlik_sure)));
            DecimalFormat df = new DecimalFormat("0.00");
            ort_verimsizlik_sureler.add(String.valueOf(df.format(ort_verimsizlik_sure)));
        } else {
            personel_puantajlar.add("0");
            personel_sayilari.add("0");
            makine_puantajlar.add("0");
            makine_sayilari.add("0");
            verimlilik_orani.add("100");
            ort_verimsizlik_sureler.add("0.00");
        }
        List[] degerler = new List[]{personel_sayilari,personel_puantajlar,makine_sayilari,makine_puantajlar,verimlilik_orani,ort_verimsizlik_sureler};
        cursor.close();
        sqLiteDatabase.close();
        return degerler;
    }
    public List[] ReadTaslakResourceflistPart2(String id,String[] imalat,int i,List<String> personel_puantajlar, List<String> makine_puantajlar,List<String> personel_sayilari,
        List<String> makine_sayilari,List<String> verimlilik_orani,List<String> ort_verimsizlik_sureler
    ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String kategori = null;
        int puantaj = 0;
        String[] columns = {
                TASLAK_RESOURCE_YAPISI.ID,
                TASLAK_RESOURCE_YAPISI.TARIH,
                TASLAK_RESOURCE_YAPISI.KAYNAK_ID,
                TASLAK_RESOURCE_YAPISI.KATEGORI,
                TASLAK_RESOURCE_YAPISI.IMALAT,
                TASLAK_RESOURCE_YAPISI.PUANTAJ,
                TASLAK_RESOURCE_YAPISI.VERIM,
                TASLAK_RESOURCE_YAPISI.SAYI
        };
        String[] selectionArgs = {String.valueOf(id),ReadImalatwidforisim(imalat[i])};
        Cursor cursorn = sqLiteDatabase.query(TASLAK_RESOURCE_YAPISI.TABLO_ADI,columns,TASLAK_RESOURCE_YAPISI.ID+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.IMALAT+" =? ",selectionArgs,null,null,null);
        int kisi_sayisi=0;
        int personel_puantaj = 0;
        int makine_sayisi = 0;
        int makine_puantaj=0;
        int iscilik_verimsizlik=0;
        int makine_verimsizlik=0;
        if (cursorn.getCount()>0){
            Log.d("log","try");
            while (cursorn.moveToNext()){
                kategori = cursorn.getString(cursorn.getColumnIndex(TASLAK_RESOURCE_YAPISI.KATEGORI));
                puantaj = cursorn.getInt(cursorn.getColumnIndex(TASLAK_RESOURCE_YAPISI.PUANTAJ));
                if (kategori.equals("iscilik")){
                    if (puantaj>0){
                        kisi_sayisi = kisi_sayisi + 1;
                        personel_puantaj = personel_puantaj + puantaj;
                    }else if (puantaj<0){
                        iscilik_verimsizlik = iscilik_verimsizlik + puantaj;


                    }
                }else if (kategori.equals("makine")){
                    if (puantaj>0){
                        makine_sayisi = makine_sayisi + 1;
                        makine_puantaj = makine_puantaj + puantaj;
                    }else if (puantaj<0){
                        makine_verimsizlik = makine_verimsizlik + puantaj;
                    }
                }
            }
            int verimlilik = (personel_puantaj+ iscilik_verimsizlik)*100/personel_puantaj;
            double ort_verimsizlik_sure = ((double)iscilik_verimsizlik)/((double)kisi_sayisi);
            personel_puantajlar.add(String.valueOf(personel_puantaj));
            personel_sayilari.add(String.valueOf(kisi_sayisi));
            makine_puantajlar.add(String.valueOf(makine_puantaj));
            makine_sayilari.add(String.valueOf(makine_sayisi));
            verimlilik_orani.add(String.valueOf(verimlilik));
            //ort_verimsizlik_sureler.add(String.valueOf(String.format("%.2f", ort_verimsizlik_sure)));
            DecimalFormat df = new DecimalFormat("0.00");
            ort_verimsizlik_sureler.add(String.valueOf(df.format(ort_verimsizlik_sure)));
        } else {
            personel_puantajlar.add("0");
            personel_sayilari.add("0");
            makine_puantajlar.add("0");
            makine_sayilari.add("0");
            verimlilik_orani.add("100");
            ort_verimsizlik_sureler.add("0.00");
        }
        cursorn.close();
        sqLiteDatabase.close();
        List[] aradegerler = new List[]{personel_sayilari,personel_puantajlar,makine_sayilari,makine_puantajlar,verimlilik_orani,ort_verimsizlik_sureler};
        return aradegerler;

    }
    public String[] ReadTaslakAciklamaflist(String id,String tarih,String[] imalat){
        List<String> aciklamala_sayilari = new ArrayList();
        for (int i = 0; i<imalat.length;i++){
            /*String aciklama= null;
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            String[] columns = {
                    TASLAK_ACIKLAMALAR_YAPISI.ID,
                    TASLAK_ACIKLAMALAR_YAPISI.TARIH,
                    TASLAK_ACIKLAMALAR_YAPISI.IMALAT,
                    TASLAK_ACIKLAMALAR_YAPISI.ACIKLAMA,
            };
            String[] selectionArgs = {String.valueOf(id),tarih,ReadImalatwidforisim(imalat[i])};
            Cursor cursor = sqLiteDatabase.query(TASLAK_ACIKLAMALAR_YAPISI.TABLO_ADI,columns,TASLAK_ACIKLAMALAR_YAPISI.ID+" =?"+" AND "+TASLAK_ACIKLAMALAR_YAPISI.TARIH+" =?"+" AND "+TASLAK_ACIKLAMALAR_YAPISI.IMALAT+" =? ",selectionArgs,null,null,null);
            aciklamala_sayilari.add(String.valueOf(cursor.getCount()));
            *//*int aciklama_sayisi = 0;
            if (cursor.getCount()>0){
                while (cursor.moveToNext()){
                    aciklama = cursor.getString(cursor.getColumnIndex(TASLAK_ACIKLAMALAR_YAPISI.ACIKLAMA));
                }
                aciklamalar.add(String.valueOf(aciklama_sayisi));
            } else {
            }*//*
            cursor.close();
            sqLiteDatabase.close();*/
            aciklamala_sayilari.add("0");

        }
        String[] aciklama_sayilari_array = aciklamala_sayilari.toArray(new String[aciklamala_sayilari.size()]);
        return aciklama_sayilari_array;
    }
    public String[] ReadTaslakfinheritance(String id,String tarih,String imalat,int km_bas,int km_son){
        String sektor = null;
        int hat_no = 0;
        int mesafe = 0;
        String birim = null;
        int sent = 0;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {
                TASLAK_TABLO_YAPISI.ID,
                TASLAK_TABLO_YAPISI.TARIH,
                TASLAK_TABLO_YAPISI.IMALAT,
                TASLAK_TABLO_YAPISI.SEKTOR,
                TASLAK_TABLO_YAPISI.HAT_NO,
                TASLAK_TABLO_YAPISI.KM_BAS,
                TASLAK_TABLO_YAPISI.KM_SON,
                TASLAK_TABLO_YAPISI.MESAFE,
                TASLAK_TABLO_YAPISI.BIRIM,
                TASLAK_TABLO_YAPISI.SENT
        };
        String[] selectionArgs = {String.valueOf(id),tarih,imalat,String.valueOf(km_bas),String.valueOf(km_son)};
        Cursor cursor = sqLiteDatabase.query(TASLAK_TABLO_YAPISI.TABLO_ADI,columns,TASLAK_TABLO_YAPISI.ID+" =?"+" AND "+
                TASLAK_TABLO_YAPISI.TARIH+" =? "+" AND "+ TASLAK_TABLO_YAPISI.IMALAT+" =? "+" AND "+ TASLAK_TABLO_YAPISI.KM_BAS+" =? "+
                " AND "+ TASLAK_TABLO_YAPISI.KM_SON+" =? ",selectionArgs,null,null,null);

        if (cursor.getCount()>0){
            while (cursor.moveToNext()){

                sektor = cursor.getString(cursor.getColumnIndex(TASLAK_TABLO_YAPISI.SEKTOR));
                hat_no = cursor.getInt(cursor.getColumnIndex(TASLAK_TABLO_YAPISI.HAT_NO));
                System.out.println(sektor);

            }
        } else {

        }
        cursor.close();
        sqLiteDatabase.close();
        String[] degerler = new String[]{sektor,String.valueOf(hat_no)};
        return degerler;
    }

    public List[] ReadAciklamal4(String id,String imalat_id){
        String aciklama =null;
        Integer aciklama_id =0;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {
                TASLAK_ACIKLAMALAR_YAPISI.ACIKLAMA,
                TASLAK_ACIKLAMALAR_YAPISI.ACIKLAMA_ID,
        };
        String[] selectionArgs = {id,imalat_id};
        Cursor cursor = sqLiteDatabase.query(TASLAK_ACIKLAMALAR_YAPISI.TABLO_ADI,columns,TASLAK_ACIKLAMALAR_YAPISI.ID+" =?"+" AND "+ TASLAK_ACIKLAMALAR_YAPISI.IMALAT+ " =? ",selectionArgs,null,null,null);
        List<String> aciklamalar= new ArrayList<>();
        List<Integer> aciklama_idler= new ArrayList<>();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                aciklama = cursor.getString(cursor.getColumnIndex(TASLAK_ACIKLAMALAR_YAPISI.ACIKLAMA));
                aciklama_id = cursor.getInt(cursor.getColumnIndex(TASLAK_ACIKLAMALAR_YAPISI.ACIKLAMA_ID));
                aciklamalar.add(aciklama);
                aciklama_idler.add(aciklama_id);
            }
        } else {}
        cursor.close();
        sqLiteDatabase.close();
        List[] lists = new List[]{aciklamalar,aciklama_idler};
        return lists;
    }
    public List[] ReadAciklamal3(String id,String imalat_id,String kopya_no){
        String aciklama =null;
        Integer aciklama_id = 0;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {
                TASLAK_ACIKLAMALAR_YAPISI.ACIKLAMA_ID,
                TASLAK_ACIKLAMALAR_YAPISI.ACIKLAMA
        };
        String[] selectionArgs = {id,imalat_id,kopya_no};
        Cursor cursor = sqLiteDatabase.query(TASLAK_ACIKLAMALAR_YAPISI.TABLO_ADI,columns,TASLAK_ACIKLAMALAR_YAPISI.ID+" =? "+" AND "+ TASLAK_ACIKLAMALAR_YAPISI.IMALAT+ " =? "+" AND "+ TASLAK_ACIKLAMALAR_YAPISI.KOPYA_NO+ " =? ",selectionArgs,null,null,null);
        List<String> aciklamalar= new ArrayList<>();
        List<Integer> aciklama_idler= new ArrayList<>();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                aciklama = cursor.getString(cursor.getColumnIndex(TASLAK_ACIKLAMALAR_YAPISI.ACIKLAMA));
                aciklama_id = cursor.getInt(cursor.getColumnIndex(TASLAK_ACIKLAMALAR_YAPISI.ACIKLAMA_ID));
                aciklamalar.add(aciklama);
                aciklama_idler.add(aciklama_id);
            }
        } else {
        }
        cursor.close();
        sqLiteDatabase.close();
        List[] lists = new List[]{aciklamalar,aciklama_idler};
        return lists;
    }

    public int WriteTaslakL3(String id,String imalat,int kopya_no){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASLAK_ACIKLAMALAR_YAPISI.ID, id);
        contentValues.put(TASLAK_ACIKLAMALAR_YAPISI.IMALAT, imalat);
        contentValues.put(TASLAK_ACIKLAMALAR_YAPISI.ACIKLAMA, "Lütfen açıklama giriniz.");
        contentValues.put(TASLAK_ACIKLAMALAR_YAPISI.KOPYA_NO, kopya_no);
        sqLiteDatabase.insert(TASLAK_ACIKLAMALAR_YAPISI.TABLO_ADI,null,contentValues);
        sqLiteDatabase.close();
        return 2;

    }
    public void  WriteTaslakL4(String id,String imalat,int kopya_no){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASLAK_ACIKLAMALAR_YAPISI.ID, id);
        contentValues.put(TASLAK_ACIKLAMALAR_YAPISI.IMALAT, imalat);
        contentValues.put(TASLAK_ACIKLAMALAR_YAPISI.ACIKLAMA, "");
        contentValues.put(TASLAK_ACIKLAMALAR_YAPISI.KOPYA_NO, kopya_no);
        sqLiteDatabase.insert(TASLAK_ACIKLAMALAR_YAPISI.TABLO_ADI,null,contentValues);
        sqLiteDatabase.close();
    }
    public void UpdateAciklamal4(String aciklama_id,String aciklama){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASLAK_ACIKLAMALAR_YAPISI.ACIKLAMA,aciklama);
        String[] args = {aciklama_id};
        sqLiteDatabase.update(TASLAK_ACIKLAMALAR_YAPISI.TABLO_ADI,contentValues,TASLAK_ACIKLAMALAR_YAPISI.ACIKLAMA_ID+ " = ?",args);
        sqLiteDatabase.close();
    }


    public void UpdateAciklamal3(String id, String imalat_id,String aciklama,String kopya_no,String aciklama_old){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASLAK_ACIKLAMALAR_YAPISI.ACIKLAMA,aciklama);
        String[] args = {id,imalat_id,aciklama_old,kopya_no};
        sqLiteDatabase.update(TASLAK_ACIKLAMALAR_YAPISI.TABLO_ADI,contentValues,TASLAK_ACIKLAMALAR_YAPISI.ID+ " = ?"+" AND "+TASLAK_ACIKLAMALAR_YAPISI.IMALAT+ " = ?"+" AND "+TASLAK_ACIKLAMALAR_YAPISI.ACIKLAMA+ " = ?"+" AND "+TASLAK_ACIKLAMALAR_YAPISI.KOPYA_NO+ " = ?",args);
        sqLiteDatabase.close();
    }
    public String[] ReadTaslakResource(String id){
        String tarih = null;
        String kaynak_id = null;
        String kategori = null;
        String imalat = null;
        int puantaj = 0;
        String verim = null;
        int sayi = 0;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {
                TASLAK_RESOURCE_YAPISI.ID,
                TASLAK_RESOURCE_YAPISI.TARIH,
                TASLAK_RESOURCE_YAPISI.KAYNAK_ID,
                TASLAK_RESOURCE_YAPISI.KATEGORI,
                TASLAK_RESOURCE_YAPISI.IMALAT,
                TASLAK_RESOURCE_YAPISI.PUANTAJ,
                TASLAK_RESOURCE_YAPISI.VERIM,
                TASLAK_RESOURCE_YAPISI.SAYI
        };
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = sqLiteDatabase.query(TASLAK_RESOURCE_YAPISI.TABLO_ADI,columns,TASLAK_RESOURCE_YAPISI.ID+" =?",selectionArgs,null,null,null);
        String[] taslak_bilgisi = new String[0];
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){


                tarih = cursor.getString(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.TARIH));
                kaynak_id = cursor.getString(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.KAYNAK_ID));
                kategori = cursor.getString(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.KATEGORI));
                imalat = cursor.getString(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.IMALAT));
                puantaj = cursor.getInt(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.PUANTAJ));
                verim = cursor.getString(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.VERIM));
                sayi = cursor.getInt(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.SAYI));
                taslak_bilgisi = new String[]{
                        String.valueOf(id),
                        tarih,
                        kaynak_id,
                        kategori,
                        imalat,
                        String.valueOf(puantaj),
                        String.valueOf(verim),
                        String.valueOf(sayi)
                };

            }
        } else {
            taslak_bilgisi = new String[]{String.valueOf(cursor.getCount())};
        }
        cursor.close();
        sqLiteDatabase.close();
        return taslak_bilgisi;
    }
    public List<String>[] ReadTaslakResourceforExListViewGroup(String id,String imalatid){
        String tarih = null;
        String kaynak_id = null;
        String kategori = null;
        String imalat = null;
        int puantaj = 0;
        String verim = null;
        int sayi = 0;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {
                TASLAK_RESOURCE_YAPISI.ID,
                TASLAK_RESOURCE_YAPISI.KAYNAK_ID,
                TASLAK_RESOURCE_YAPISI.PUANTAJ,
                TASLAK_RESOURCE_YAPISI.VERIM,
                TASLAK_RESOURCE_YAPISI.TIP

        };//TODO LONG ID DEĞİŞTİR
        String[] selectionArgs = {String.valueOf(id),"iscilik","efor",imalatid};//TODO MAKİNEDE İSCİLİK İLE EFORU AYARLA
        Cursor cursor = sqLiteDatabase.query(TASLAK_RESOURCE_YAPISI.TABLO_ADI,columns,TASLAK_RESOURCE_YAPISI.ID+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.TIP+" =?"+ " AND "+TASLAK_RESOURCE_YAPISI.VERIM+" =?"+ " AND " + TASLAK_RESOURCE_YAPISI.IMALAT+ " =?",selectionArgs,null,null,null);
        List<String> kaynak_idler = new ArrayList<>();
        List<String> isimler = new ArrayList<>();
        List<String> puantajlar = new ArrayList<>();
        System.out.println(cursor.getCount());
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                kaynak_id = cursor.getString(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.KAYNAK_ID));
                puantaj = cursor.getInt(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.PUANTAJ));


                kaynak_idler.add(kaynak_id);
                isimler.add(ReadPersonelwid(kaynak_id));
                puantajlar.add(String.valueOf(puantaj));
            }
        }
        List<String>[] groups = new List[]{kaynak_idler,isimler,puantajlar};
        cursor.close();
        sqLiteDatabase.close();
        return groups;
    }
    public List<String>[] ReadTaslakResourceforExListViewGroupMakine(String id,String imalatid){
        String tarih = null;
        String kaynak_id = null;
        String kategori = null;
        String imalat = null;
        int puantaj = 0;
        String verim = null;
        int sayi = 0;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {
                TASLAK_RESOURCE_YAPISI.ID,
                TASLAK_RESOURCE_YAPISI.KAYNAK_ID,
                TASLAK_RESOURCE_YAPISI.PUANTAJ,
                TASLAK_RESOURCE_YAPISI.VERIM,
                TASLAK_RESOURCE_YAPISI.TIP,
                TASLAK_RESOURCE_YAPISI.SAYI

        };//TODO LONG ID DEĞİŞTİR
        String[] selectionArgs = {String.valueOf(id),"makine","efor",imalatid};//TODO MAKİNEDE İSCİLİK İLE EFORU AYARLA
        Cursor cursor = sqLiteDatabase.query(TASLAK_RESOURCE_YAPISI.TABLO_ADI,columns,TASLAK_RESOURCE_YAPISI.ID+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.TIP+" =?"+ " AND "+TASLAK_RESOURCE_YAPISI.VERIM+" =?"+ " AND " + TASLAK_RESOURCE_YAPISI.IMALAT+ " =?",selectionArgs,null,null,null);
        List<String> kaynak_idler = new ArrayList<>();
        List<String> isimler = new ArrayList<>();
        List<String> puantajlar = new ArrayList<>();
        List<String> sayilar = new ArrayList<>();
        System.out.println(cursor.getCount());
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                kaynak_id = cursor.getString(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.KAYNAK_ID));
                puantaj = cursor.getInt(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.PUANTAJ));
                sayi = cursor.getInt(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.SAYI));


                kaynak_idler.add(kaynak_id);
                isimler.add(ReadPersonelwid(kaynak_id));
                puantajlar.add(String.valueOf(puantaj));
                sayilar.add(String.valueOf(sayi));
            }
        }
        List<String>[] groups = new List[]{kaynak_idler,isimler,puantajlar,sayilar};
        cursor.close();
        sqLiteDatabase.close();
        return groups;
    }

    public List<String>[] ReadTaslakResourceforLsIscidetay(String bildiri_id,String kaynak_id){
        String tarih = null;
        String kategori = null;
        String imalat = null;
        int puantaj = 0;
        String verim = null;
        int sayi = 0;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {
                TASLAK_RESOURCE_YAPISI.ID,
                TASLAK_RESOURCE_YAPISI.KAYNAK_ID,
                TASLAK_RESOURCE_YAPISI.PUANTAJ,
                TASLAK_RESOURCE_YAPISI.VERIM,
                TASLAK_RESOURCE_YAPISI.TIP
        };
        String[] selectionArgs = {String.valueOf(bildiri_id),kaynak_id,"verimsiz"};
        Cursor cursor = sqLiteDatabase.query(TASLAK_RESOURCE_YAPISI.TABLO_ADI,columns,TASLAK_RESOURCE_YAPISI.ID+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.KAYNAK_ID+" =?"+ " AND "+TASLAK_RESOURCE_YAPISI.KATEGORI+" =?",selectionArgs,null,null,null);
        List<String> isimler = new ArrayList<>();
        List<String> puantajlar= new ArrayList<>();
        List<String> verimsizlikler = new ArrayList<>();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                puantaj = cursor.getInt(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.PUANTAJ));
                verim = cursor.getString(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.VERIM));
                verimsizlikler.add(ReadEtkenListesiforisim(verim));
                puantajlar.add(String.valueOf(puantaj));

            }
        }
        cursor.close();
        sqLiteDatabase.close();
        List<String>[] lists = new List[]{verimsizlikler,puantajlar};
        return lists;
    }
    public List<String>[] ReadTaslakResourceforLsMakinedetay(String bildiri_id,String kaynak_id){
        String tarih = null;
        String kategori = null;
        String imalat = null;
        int puantaj = 0;
        String verim = null;
        int sayi = 0;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {
                TASLAK_RESOURCE_YAPISI.ID,
                TASLAK_RESOURCE_YAPISI.KAYNAK_ID,
                TASLAK_RESOURCE_YAPISI.PUANTAJ,
                TASLAK_RESOURCE_YAPISI.VERIM,
                TASLAK_RESOURCE_YAPISI.TIP
        };
        String[] selectionArgs = {String.valueOf(bildiri_id),kaynak_id,"verimsiz"};
        Cursor cursor = sqLiteDatabase.query(TASLAK_RESOURCE_YAPISI.TABLO_ADI,columns,TASLAK_RESOURCE_YAPISI.ID+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.KAYNAK_ID+" =?"+ " AND "+TASLAK_RESOURCE_YAPISI.KATEGORI+" =?",selectionArgs,null,null,null);
        List<String> isimler = new ArrayList<>();
        List<String> puantajlar= new ArrayList<>();
        List<String> verimsizlikler = new ArrayList<>();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                puantaj = cursor.getInt(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.PUANTAJ));
                verim = cursor.getString(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.VERIM));
                verimsizlikler.add(ReadEtkenListesiforisim(verim));
                puantajlar.add(String.valueOf(puantaj));

            }
        }
        cursor.close();
        sqLiteDatabase.close();
        List<String>[] lists = new List[]{verimsizlikler,puantajlar};
        return lists;
    }

    public String ReadTaslakResourceforIscidetayPuantaj(String bildiri_id,String kaynak_id,String verim){
        String puantaj= null;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {
                TASLAK_RESOURCE_YAPISI.ID,
                TASLAK_RESOURCE_YAPISI.KAYNAK_ID,
                TASLAK_RESOURCE_YAPISI.PUANTAJ,
                TASLAK_RESOURCE_YAPISI.VERIM,
                TASLAK_RESOURCE_YAPISI.TIP

        };
        String[] selectionArgs = {String.valueOf(bildiri_id),kaynak_id,verim};
        Cursor cursor = sqLiteDatabase.query(TASLAK_RESOURCE_YAPISI.TABLO_ADI,columns,TASLAK_RESOURCE_YAPISI.ID+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.KAYNAK_ID+" =?"+ " AND "+TASLAK_RESOURCE_YAPISI.VERIM+" =?",selectionArgs,null,null,null);
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                puantaj = String.valueOf(cursor.getInt(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.PUANTAJ)));


            }
        }/*else {//algortmada revizyon yapıldı bu sebebten ötürü şu an kullanılımıyıor yaptığı iş eğer kaynak id si
         //verilen kişinin puantajı yoksa ekibini bulup ekibin vt puantajını yazdırıyor
            if (ReadPersonel(kaynak_id).length>5) {
                String ekip = ReadPersonel(kaynak_id)[5];
                if (ReadPersonel(ekip).length>6) {
                    puantaj = ReadPersonel(ekip)[6];
                }
            }
        }*/
        cursor.close();
        sqLiteDatabase.close();
        return puantaj;
    }
    public String ReadTaslakResourceforMakinedetayPuantaj(String bildiri_id,String kaynak_id,String verim){
        String puantaj= null;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {
                TASLAK_RESOURCE_YAPISI.ID,
                TASLAK_RESOURCE_YAPISI.KAYNAK_ID,
                TASLAK_RESOURCE_YAPISI.PUANTAJ,
                TASLAK_RESOURCE_YAPISI.VERIM,
                TASLAK_RESOURCE_YAPISI.TIP

        };
        String[] selectionArgs = {String.valueOf(bildiri_id),kaynak_id,verim};
        Cursor cursor = sqLiteDatabase.query(TASLAK_RESOURCE_YAPISI.TABLO_ADI,columns,TASLAK_RESOURCE_YAPISI.ID+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.KAYNAK_ID+" =?"+ " AND "+TASLAK_RESOURCE_YAPISI.VERIM+" =?",selectionArgs,null,null,null);
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                puantaj = String.valueOf(cursor.getInt(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.PUANTAJ)));


            }
        }/*else {//algortmada revizyon yapıldı bu sebebten ötürü şu an kullanılımıyıor yaptığı iş eğer kaynak id si
         //verilen kişinin puantajı yoksa ekibini bulup ekibin vt puantajını yazdırıyor
            if (ReadPersonel(kaynak_id).length>5) {
                String ekip = ReadPersonel(kaynak_id)[5];
                if (ReadPersonel(ekip).length>6) {
                    puantaj = ReadPersonel(ekip)[6];
                }
            }
        }*/
        cursor.close();
        sqLiteDatabase.close();
        return puantaj;
    }
    public String ReadTaslakResourceforMakinedetaySayi(String bildiri_id,String kaynak_id,String verim){
        String sayi= null;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {
                TASLAK_RESOURCE_YAPISI.ID,
                TASLAK_RESOURCE_YAPISI.KAYNAK_ID,
                TASLAK_RESOURCE_YAPISI.PUANTAJ,
                TASLAK_RESOURCE_YAPISI.SAYI,
                TASLAK_RESOURCE_YAPISI.VERIM,
                TASLAK_RESOURCE_YAPISI.TIP

        };
        String[] selectionArgs = {String.valueOf(bildiri_id),kaynak_id,verim};
        Cursor cursor = sqLiteDatabase.query(TASLAK_RESOURCE_YAPISI.TABLO_ADI,columns,TASLAK_RESOURCE_YAPISI.ID+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.KAYNAK_ID+" =?"+ " AND "+TASLAK_RESOURCE_YAPISI.VERIM+" =?",selectionArgs,null,null,null);
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                sayi = String.valueOf(cursor.getInt(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.SAYI)));


            }
        }/*else {//algortmada revizyon yapıldı bu sebebten ötürü şu an kullanılımıyıor yaptığı iş eğer kaynak id si
         //verilen kişinin puantajı yoksa ekibini bulup ekibin vt puantajını yazdırıyor
            if (ReadPersonel(kaynak_id).length>5) {
                String ekip = ReadPersonel(kaynak_id)[5];
                if (ReadPersonel(ekip).length>6) {
                    puantaj = ReadPersonel(ekip)[6];
                }
            }
        }*/
        cursor.close();
        sqLiteDatabase.close();
        return sayi;
    }

    public void PopUpUpdate(String bildiri_id,String imalat_id,int yeni_puantaj,String kaynak_id){
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(TASLAK_RESOURCE_YAPISI.PUANTAJ,yeni_puantaj);
            String[] values = new String[]{bildiri_id,imalat_id,kaynak_id};
            sqLiteDatabase.update(TASLAK_RESOURCE_YAPISI.TABLO_ADI,contentValues,TASLAK_RESOURCE_YAPISI.ID +" =? "+" AND "+TASLAK_RESOURCE_YAPISI.IMALAT +" =? "+" AND "+TASLAK_RESOURCE_YAPISI.KAYNAK_ID +" =?",values);
            sqLiteDatabase.close();
            Log.d("update",bildiri_id+" "+kaynak_id+" "+imalat_id+" "+yeni_puantaj+" ");
            System.out.println("asdasd error"+ bildiri_id+" "+kaynak_id+" "+imalat_id+" "+yeni_puantaj+" ");
    }
    public void PopUpUpdateWsayi(String bildiri_id,String imalat_id,int yeni_puantaj,String kaynak_id,int yeni_sayi){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASLAK_RESOURCE_YAPISI.PUANTAJ,yeni_puantaj);
        contentValues.put(TASLAK_RESOURCE_YAPISI.SAYI,yeni_sayi);
        String[] values = new String[]{bildiri_id,imalat_id,kaynak_id};
        sqLiteDatabase.update(TASLAK_RESOURCE_YAPISI.TABLO_ADI,contentValues,TASLAK_RESOURCE_YAPISI.ID +" =? "+" AND "+TASLAK_RESOURCE_YAPISI.IMALAT +" =? "+" AND "+TASLAK_RESOURCE_YAPISI.KAYNAK_ID +" =?",values);
        sqLiteDatabase.close();
        Log.d("update",bildiri_id+" "+kaynak_id+" "+imalat_id+" "+yeni_puantaj+" ");
        System.out.println("asdasd error"+ bildiri_id+" "+kaynak_id+" "+imalat_id+" "+yeni_puantaj+" ");
    }
    public HashMap<String,List<String>>[] ReadTaslakResourceforExListViewChild(String id,String imalatid,List<String> idler,List<String> groupisimler){//idler groupların idsi isimler groupların isimleri
        String tarih = null;
        String kaynak_id = null;
        String kategori = null;
        String imalat = null;
        int puantaj = 0;
        String verim = null;
        int sayi = 0;

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {
                TASLAK_RESOURCE_YAPISI.ID,
                TASLAK_RESOURCE_YAPISI.KAYNAK_ID,
                TASLAK_RESOURCE_YAPISI.PUANTAJ,
                TASLAK_RESOURCE_YAPISI.VERIM,
                TASLAK_RESOURCE_YAPISI.TIP

        };//TODO LONG ID DEĞİŞTİR
        String[] selectionArgs = {String.valueOf(id),"iscilik",imalatid};//TODO MAKİNEDE İSCİLİK İLE EFORU AYARLA
        Cursor cursor = sqLiteDatabase.query(TASLAK_RESOURCE_YAPISI.TABLO_ADI,columns,TASLAK_RESOURCE_YAPISI.ID+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.TIP+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.IMALAT+" =?",selectionArgs,null,null,null);
        List<String> isimler = new ArrayList<>();
        List<String> puantajlar = new ArrayList<>();
        HashMap<String,List<String>> listHash = new HashMap<>();
        HashMap<String,List<String>> listHashpuantaj = new HashMap<>();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                for (int i =0;i<idler.size();i++){
                    if (cursor.getString(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.VERIM)).equals(idler.get(i))){
                        kaynak_id = cursor.getString(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.KAYNAK_ID));
                        puantaj = cursor.getInt(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.PUANTAJ));
                        isimler.add(ReadPersonelwid(kaynak_id));
                        puantajlar.add(String.valueOf(puantaj));
                        listHash.put(groupisimler.get(i),isimler);
                        listHashpuantaj.put(groupisimler.get(i),puantajlar);

                    }else if (idler.get(i).equals(cursor.getString(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.KAYNAK_ID)))){
                        Log.d("groupisimler",groupisimler.get(i));
                        listHash.put(groupisimler.get(i),new ArrayList<String>());
                        listHashpuantaj.put(groupisimler.get(i),new ArrayList<String>());


                    }
                }

            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return new HashMap[]{listHash,listHashpuantaj};
    }
    public HashMap<String,List<String>>[] ReadTaslakResourceforExListViewChildMakine(String id,String imalatid,List<String> idler,List<String> groupisimler){//idler groupların idsi isimler groupların isimleri
        String tarih = null;
        String kaynak_id = null;
        String kategori = null;
        String imalat = null;
        int puantaj = 0;
        String verim = null;
        int sayi = 0;

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {
                TASLAK_RESOURCE_YAPISI.ID,
                TASLAK_RESOURCE_YAPISI.KAYNAK_ID,
                TASLAK_RESOURCE_YAPISI.PUANTAJ,
                TASLAK_RESOURCE_YAPISI.VERIM,
                TASLAK_RESOURCE_YAPISI.TIP

        };//TODO LONG ID DEĞİŞTİR
        String[] selectionArgs = {String.valueOf(id),"makine",imalatid};//TODO MAKİNEDE İSCİLİK İLE EFORU AYARLA
        Cursor cursor = sqLiteDatabase.query(TASLAK_RESOURCE_YAPISI.TABLO_ADI,columns,TASLAK_RESOURCE_YAPISI.ID+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.TIP+" =?"+" AND "+TASLAK_RESOURCE_YAPISI.IMALAT+" =?",selectionArgs,null,null,null);
        List<String> isimler = new ArrayList<>();
        List<String> puantajlar = new ArrayList<>();
        HashMap<String,List<String>> listHash = new HashMap<>();
        HashMap<String,List<String>> listHashpuantaj = new HashMap<>();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                for (int i =0;i<idler.size();i++){
                    if (cursor.getString(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.VERIM)).equals(idler.get(i))){
                        kaynak_id = cursor.getString(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.KAYNAK_ID));
                        puantaj = cursor.getInt(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.PUANTAJ));
                        isimler.add(ReadPersonelwid(kaynak_id));
                        puantajlar.add(String.valueOf(puantaj));
                        listHash.put(groupisimler.get(i),isimler);
                        listHashpuantaj.put(groupisimler.get(i),puantajlar);

                    }else if (idler.get(i).equals(cursor.getString(cursor.getColumnIndex(TASLAK_RESOURCE_YAPISI.KAYNAK_ID)))){
                        Log.d("groupisimler",groupisimler.get(i));
                        listHash.put(groupisimler.get(i),new ArrayList<String>());
                        listHashpuantaj.put(groupisimler.get(i),new ArrayList<String>());


                    }
                }

            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return new HashMap[]{listHash,listHashpuantaj};
    }

    public String[] ReadTaslakAciklamalar(String id){
        String tarih = null;
        String imalat = null;
        String aciklama = null;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {
                TASLAK_ACIKLAMALAR_YAPISI.ID,
                TASLAK_ACIKLAMALAR_YAPISI.TARIH,
                TASLAK_ACIKLAMALAR_YAPISI.IMALAT,
                TASLAK_ACIKLAMALAR_YAPISI.ACIKLAMA,
        };
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = sqLiteDatabase.query(TASLAK_ACIKLAMALAR_YAPISI.TABLO_ADI,columns,TASLAK_ACIKLAMALAR_YAPISI.ID+" =?",selectionArgs,null,null,null);
        String[] taslak_bilgisi = new String[0];
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){

                id = cursor.getString(cursor.getColumnIndex(TASLAK_ACIKLAMALAR_YAPISI.ID));
                tarih = cursor.getString(cursor.getColumnIndex(TASLAK_ACIKLAMALAR_YAPISI.TARIH));
                imalat = cursor.getString(cursor.getColumnIndex(TASLAK_ACIKLAMALAR_YAPISI.IMALAT));
                aciklama = cursor.getString(cursor.getColumnIndex(TASLAK_ACIKLAMALAR_YAPISI.ACIKLAMA));
                taslak_bilgisi = new String[]{
                        String.valueOf(id),
                        tarih,
                        imalat,
                        aciklama,
                };

            }
        } else {
            taslak_bilgisi = new String[]{String.valueOf(cursor.getCount())};
        }
        cursor.close();
        sqLiteDatabase.close();
        return taslak_bilgisi;
    }
    public Boolean ReadSektorfkmCheck(String hat){

        int id = 0;
        String sektor = null;
        String hat_no = null;
        int km_bas = 0;
        int km_bit = 0;
        int aktif = 0;
        String imalat = null;
        String bolge = null;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {KITAP_TABLO_YAPISI.ID,
                KITAP_TABLO_YAPISI.ISIM,
                KITAP_TABLO_YAPISI.HAT,
                KITAP_TABLO_YAPISI.HAT_NO,
                KITAP_TABLO_YAPISI.KM_BAS,
                KITAP_TABLO_YAPISI.KM_BIT,
                KITAP_TABLO_YAPISI.AKTIF,
                KITAP_TABLO_YAPISI.IMALAT,
                KITAP_TABLO_YAPISI.BOLGE};
        String[] selectionArgs = {String.valueOf(hat)};
        Cursor cursor = sqLiteDatabase.query(KITAP_TABLO_YAPISI.TABLO_ADI,columns,KITAP_TABLO_YAPISI.HAT+" =?",selectionArgs,null,null,null);
        String[] kitapBilgisi = new String[0];
        String[] bilgiler = new String[cursor.getCount()];
        Boolean dogrumu = true;
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                /*aktif = cursor.getInt(cursor.getColumnIndex(KITAP_TABLO_YAPISI.AKTIF));
                imalat = cursor.getString(cursor.getColumnIndex(KITAP_TABLO_YAPISI.IMALAT));
                bolge = cursor.getString(cursor.getColumnIndex(KITAP_TABLO_YAPISI.BOLGE));
                id = cursor.getInt(cursor.getColumnIndex(KITAP_TABLO_YAPISI.ID));

                hat = cursor.getString(cursor.getColumnIndex(KITAP_TABLO_YAPISI.HAT));
                hat_no = cursor.getString(cursor.getColumnIndex(KITAP_TABLO_YAPISI.HAT_NO));*/
                sektor = cursor.getString(cursor.getColumnIndex(KITAP_TABLO_YAPISI.ISIM));
                km_bas = cursor.getInt(cursor.getColumnIndex(KITAP_TABLO_YAPISI.KM_BAS));
                km_bit = cursor.getInt(cursor.getColumnIndex(KITAP_TABLO_YAPISI.KM_BIT));
                if (sektor!="Balastlı Ana Hat"){
                veri = new Get_Set();
                if ((km_bas<veri.getKmbas()&&(veri.getKmbas()<km_bit))||((km_bas<veri.getKmson())&&(veri.getKmson()<km_bit))){
                    dogrumu = false;
                    System.out.println("yanlıs1");
                }else if ((veri.getKmbas()<km_bas)&&(km_bit<veri.getKmson())){
                    dogrumu=false;
                    System.out.println("yanlıs");

                }else{
                    //sıkıntı yok
                    System.out.println("dogru");
                }
                if (!dogrumu){

                    cursor.close();
                    sqLiteDatabase.close();
                    return dogrumu;
                }


            }}
        } else {
            System.out.println("abi bisi yok");
            cursor.close();
            sqLiteDatabase.close();
            return false;
        }
        System.out.println("bitti");
        cursor.close();
        sqLiteDatabase.close();
        return true;
    }
    public void UpdateTaslak(String id,String tarih,String imalat,int kopya_no,String kolon,int yenideger)  {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(kolon,yenideger);
        sqLiteDatabase.update(TASLAK_TABLO_YAPISI.TABLO_ADI,contentValues,TASLAK_TABLO_YAPISI.ID+ "= ?"+" AND "+TASLAK_TABLO_YAPISI.TARIH+"= ?"+" AND "+TASLAK_TABLO_YAPISI.IMALAT+"= ?"+" AND "+TASLAK_TABLO_YAPISI.KOPYA_NO+"= ?",new String[]{id,tarih,imalat,String.valueOf(kopya_no)});
        sqLiteDatabase.close();
        System.out.println("guncelleme");
    }
    public void UpdateTaslak(String id,String tarih,String imalat,int kopya_no,String kolon,String yenideger)  {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(kolon,yenideger);
        sqLiteDatabase.update(TASLAK_TABLO_YAPISI.TABLO_ADI,contentValues,TASLAK_TABLO_YAPISI.ID+ "= ?"+" AND "+TASLAK_TABLO_YAPISI.TARIH+"= ?"+" AND "+TASLAK_TABLO_YAPISI.IMALAT+"= ?"+" AND "+TASLAK_TABLO_YAPISI.KOPYA_NO+"= ?",new String[]{id,tarih,imalat,String.valueOf(kopya_no)});
        sqLiteDatabase.close();
        System.out.println("guncelleme");
    }
    public void UpdateEtkenListeRadio(String etken,int yenideger){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ETKEN_LISTE_TABLO_YAPISI.RADIO_BUTTON,yenideger);
        sqLiteDatabase.update(ETKEN_LISTE_TABLO_YAPISI.TABLO_ADI,contentValues,ETKEN_LISTE_TABLO_YAPISI.ISIM+ "= ?",new String[]{etken});
        sqLiteDatabase.close();
    }
    public void UpdateBildiriListesi(long kod,String degisen,int yenideger){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(degisen,yenideger);
        sqLiteDatabase.update(BILDIRI_LISTESI_TABLO_YAPISI.TABLO_ADI,contentValues,BILDIRI_LISTESI_TABLO_YAPISI.KOD+ "= ?",new String[]{String.valueOf(kod)});
        sqLiteDatabase.close();

    }
    public void DeleteTaslakListItem(String id,String tarih,String imalat,String kopya_no) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] silinecekVeri = {String.valueOf(id),tarih,imalat,kopya_no};
        sqLiteDatabase.delete(TASLAK_TABLO_YAPISI.TABLO_ADI, TASLAK_TABLO_YAPISI.ID+ "= ?"+" AND "+TASLAK_TABLO_YAPISI.TARIH+ "= ?"+" AND "+TASLAK_TABLO_YAPISI.IMALAT+ "= ?"+" AND "+TASLAK_TABLO_YAPISI.KOPYA_NO+ "= ?", silinecekVeri);
        sqLiteDatabase.close();

    }

    public void DeleteAciklama(String id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] silinecekVeri = {String.valueOf(id)};
        sqLiteDatabase.delete(TASLAK_ACIKLAMALAR_YAPISI.TABLO_ADI, TASLAK_ACIKLAMALAR_YAPISI.ACIKLAMA_ID+ "= ?", silinecekVeri);
        sqLiteDatabase.close();

    }
    public void DeleteTaslakVerimsizlik(String bildiri_id,String imalat_id,String etken_id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] silinecekVeri = {bildiri_id,imalat_id,etken_id};
        sqLiteDatabase.delete(TASLAK_VERIMSIZLIK_TABLO_YAPISI.TABLO_ADI, TASLAK_VERIMSIZLIK_TABLO_YAPISI.BILDIRI_ID+ " = ?"+" AND "+TASLAK_VERIMSIZLIK_TABLO_YAPISI.IMALAT_ID+ " = ?"+" AND "+TASLAK_VERIMSIZLIK_TABLO_YAPISI.ETKEN_ID+ "= ?", silinecekVeri);
        sqLiteDatabase.close();

    }
    public void DeleteTaslak(String bildiri_id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] silinecekVeri = {bildiri_id,"tarih","","0","","0","0","0","0","","0"};
        sqLiteDatabase.delete(TASLAK_TABLO_YAPISI.TABLO_ADI, TASLAK_TABLO_YAPISI.ID+ " = ?"+" AND "+TASLAK_TABLO_YAPISI.TARIH+ " = ?"+
                " AND "+TASLAK_TABLO_YAPISI.IMALAT+ "= ?"+" AND "+TASLAK_TABLO_YAPISI.KOPYA_NO+ "= ?"+" AND "+TASLAK_TABLO_YAPISI.SEKTOR+
                "= ?"+" AND "+TASLAK_TABLO_YAPISI.HAT_NO+ "= ?"+" AND "+TASLAK_TABLO_YAPISI.KM_BAS+ "= ?"+" AND "+TASLAK_TABLO_YAPISI.KM_SON+
                "= ?"+" AND "+TASLAK_TABLO_YAPISI.MESAFE+ "= ?"+" AND "+TASLAK_TABLO_YAPISI.BIRIM
                + "= ?"+" AND "+TASLAK_TABLO_YAPISI.SENT+ "= ?", silinecekVeri);
        sqLiteDatabase.close();

    }
    public void UpdateTaslak(Long id,String tarih,String imalat,int kopya_no,String sektor,int hat_no,int km_bas,int km_son,int mesafe,String birim,int sent){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASLAK_TABLO_YAPISI.TARIH,tarih);
        contentValues.put(TASLAK_TABLO_YAPISI.IMALAT,imalat);
        contentValues.put(TASLAK_TABLO_YAPISI.KOPYA_NO,kopya_no);
        contentValues.put(TASLAK_TABLO_YAPISI.SEKTOR,sektor);
        contentValues.put(TASLAK_TABLO_YAPISI.HAT_NO,hat_no);
        contentValues.put(TASLAK_TABLO_YAPISI.KM_BAS,km_bas);
        contentValues.put(TASLAK_TABLO_YAPISI.KM_SON,km_son);
        contentValues.put(TASLAK_TABLO_YAPISI.MESAFE,mesafe);
        contentValues.put(TASLAK_TABLO_YAPISI.BIRIM,birim);
        contentValues.put(TASLAK_TABLO_YAPISI.SENT,sent);
        String[] args = {String.valueOf(id),"tarih","","0","","0","0","0","0","","0"};
        sqLiteDatabase.update(TASLAK_TABLO_YAPISI.TABLO_ADI,contentValues,TASLAK_TABLO_YAPISI.ID+ " = ?"+" AND "+TASLAK_TABLO_YAPISI.TARIH+ " = ?"+
                " AND "+TASLAK_TABLO_YAPISI.IMALAT+ "= ?"+" AND "+TASLAK_TABLO_YAPISI.KOPYA_NO+ "= ?"+" AND "+TASLAK_TABLO_YAPISI.SEKTOR+
                "= ?"+" AND "+TASLAK_TABLO_YAPISI.HAT_NO+ "= ?"+" AND "+TASLAK_TABLO_YAPISI.KM_BAS+ "= ?"+" AND "+TASLAK_TABLO_YAPISI.KM_SON+
                "= ?"+" AND "+TASLAK_TABLO_YAPISI.MESAFE+ "= ?"+" AND "+TASLAK_TABLO_YAPISI.BIRIM
                + "= ?"+" AND "+TASLAK_TABLO_YAPISI.SENT+ "= ?",args);
        sqLiteDatabase.close();

    }
    public void UpdateTaslak(Long id,String tarih,String imalat,int kopya_no,String sektor,int hat_no,int km_bas,int km_son,int mesafe,String birim,int sent,String old_kopya_no,String old_imalat){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASLAK_TABLO_YAPISI.TARIH,tarih);
        contentValues.put(TASLAK_TABLO_YAPISI.IMALAT,imalat);
        contentValues.put(TASLAK_TABLO_YAPISI.KOPYA_NO,kopya_no);
        contentValues.put(TASLAK_TABLO_YAPISI.SEKTOR,sektor);
        contentValues.put(TASLAK_TABLO_YAPISI.HAT_NO,hat_no);
        contentValues.put(TASLAK_TABLO_YAPISI.KM_BAS,km_bas);
        contentValues.put(TASLAK_TABLO_YAPISI.KM_SON,km_son);
        contentValues.put(TASLAK_TABLO_YAPISI.MESAFE,mesafe);
        contentValues.put(TASLAK_TABLO_YAPISI.BIRIM,birim);
        contentValues.put(TASLAK_TABLO_YAPISI.SENT,sent);
        String[] args = {String.valueOf(id),old_imalat,old_kopya_no};
        sqLiteDatabase.update(TASLAK_TABLO_YAPISI.TABLO_ADI,contentValues,TASLAK_TABLO_YAPISI.ID+ " = ?"+" AND "+TASLAK_TABLO_YAPISI.IMALAT+ " = ?"+
                " AND "+TASLAK_TABLO_YAPISI.KOPYA_NO+ "= ?",args);
        sqLiteDatabase.close();
    }

    public void DeleteTaslakResourceVerimsizlik(String bildiri_id,String imalat_id,String kaynak_id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] silinecekVeri = {bildiri_id,imalat_id,kaynak_id,"verimsiz"};
        sqLiteDatabase.delete(TASLAK_RESOURCE_YAPISI.TABLO_ADI, TASLAK_RESOURCE_YAPISI.ID+ " = ?"+" AND "+TASLAK_RESOURCE_YAPISI.IMALAT+ " = ?"+" AND "+TASLAK_RESOURCE_YAPISI.KAYNAK_ID+ "= ?"+" AND "+TASLAK_RESOURCE_YAPISI.KATEGORI+ "= ?", silinecekVeri);
        sqLiteDatabase.close();

    }

    public void DeleteTaslakResourceVerimsizlikforGrup(String bildiri_id,String imalat_id,String grup_id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String kaynak_id;
        String[] columns = new String[]{
                PERSONEL_TABLO_YAPISI.RESOURCE_ID

        };
        Cursor cursor = sqLiteDatabase.query(PERSONEL_TABLO_YAPISI.TABLO_ADI,columns,PERSONEL_TABLO_YAPISI.EKIP_ID+" =? ",new String[]{grup_id},null,null,null);
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                kaynak_id = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.RESOURCE_ID));

                String[] silinecekVeri = {bildiri_id,imalat_id,kaynak_id,"verimsiz"};
                sqLiteDatabase.delete(TASLAK_RESOURCE_YAPISI.TABLO_ADI, TASLAK_RESOURCE_YAPISI.ID+ " = ?"+" AND "+TASLAK_RESOURCE_YAPISI.IMALAT+ " = ?"+" AND "+TASLAK_RESOURCE_YAPISI.KAYNAK_ID+ "= ?"+" AND "+TASLAK_RESOURCE_YAPISI.KATEGORI+ "= ?", silinecekVeri);
            }
        }else{//do nothing

        }
        cursor.close();
        sqLiteDatabase.close();

    }
    public void DeleteTaslakResourceVerimsizlikforGrup(){

    }
    public String[] ReadImalatfL4(String bildiri){
        String imalat = null;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {IMALAT_TABLO_YAPISI.ID,
                //   IMALAT_TABLO_YAPISI.ORDER,
                IMALAT_TABLO_YAPISI.ISIM,
                IMALAT_TABLO_YAPISI.ONCELIK,
                IMALAT_TABLO_YAPISI.BIRIM,
                IMALAT_TABLO_YAPISI.BP_AS,
                IMALAT_TABLO_YAPISI.METRAJ,
                IMALAT_TABLO_YAPISI.BILDIRI,
                IMALAT_TABLO_YAPISI.VT_SEKTOR,
                IMALAT_TABLO_YAPISI.VT_MESAFE
        };
        Cursor cursor = sqLiteDatabase.query(IMALAT_TABLO_YAPISI.TABLO_ADI,columns,IMALAT_TABLO_YAPISI.BILDIRI+" =? ",new String[]{bildiri},null,null,null);
        List<String> imalatBilgisi = new ArrayList();

        if (cursor.getCount()>0){
            while (cursor.moveToNext()){

                imalat = cursor.getString(cursor.getColumnIndex(IMALAT_TABLO_YAPISI.ISIM));
                imalatBilgisi.add(imalat);

            }
        } else {

        }
        cursor.close();
        sqLiteDatabase.close();
        String[] imalatlar = imalatBilgisi.toArray(new String[imalatBilgisi.size()]);
        return imalatlar;
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

/*
    public String[] ReadVarsayilanImalatlar(String id){
        String imalat = null;
        String sektor = null;
        String mesafe = null;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {
                VARSAYILAN_IMALAT_TABLO_YAPISI.ID,
                VARSAYILAN_IMALAT_TABLO_YAPISI.IMALAT,
                VARSAYILAN_IMALAT_TABLO_YAPISI.SEKTOR,
                VARSAYILAN_IMALAT_TABLO_YAPISI.MESAFE,
        };
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = sqLiteDatabase.query(VARSAYILAN_IMALAT_TABLO_YAPISI.TABLO_ADI,columns,VARSAYILAN_IMALAT_TABLO_YAPISI.ID+" =?",selectionArgs,null,null,null);
        String[] taslak_bilgisi = new String[0];
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){

                id = cursor.getString(cursor.getColumnIndex(VARSAYILAN_IMALAT_TABLO_YAPISI.ID));
                imalat = cursor.getString(cursor.getColumnIndex(VARSAYILAN_IMALAT_TABLO_YAPISI.IMALAT));
                sektor = cursor.getString(cursor.getColumnIndex(VARSAYILAN_IMALAT_TABLO_YAPISI.SEKTOR));
                mesafe = cursor.getString(cursor.getColumnIndex(VARSAYILAN_IMALAT_TABLO_YAPISI.MESAFE));
                taslak_bilgisi = new String[]{
                        String.valueOf(id),
                        imalat,
                        sektor,
                        mesafe
                };

            }
        } else {
            taslak_bilgisi = new String[]{String.valueOf(cursor.getCount())};
        }
        cursor.close();
        sqLiteDatabase.close();
        return taslak_bilgisi;
    }
    public void  WriteVarsayilanImalatlar(String id,String imalat,String sektor,String mesafe){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VARSAYILAN_IMALAT_TABLO_YAPISI.ID, id);
        contentValues.put(VARSAYILAN_IMALAT_TABLO_YAPISI.IMALAT, imalat);
        contentValues.put(VARSAYILAN_IMALAT_TABLO_YAPISI.SEKTOR, sektor);
        contentValues.put(VARSAYILAN_IMALAT_TABLO_YAPISI.MESAFE, mesafe);
        sqLiteDatabase.insert(VARSAYILAN_IMALAT_TABLO_YAPISI.TABLO_ADI,null,contentValues);
        sqLiteDatabase.close();
    }


        public String[] ReadPersonelwImalat_idfmakine(String imalat_id){
        String isim = null;
        String kisa_isim = null;
        String kod = null;
        String kategori = null;
        String tip = null;
        String detay = null;
        String sorumlu = null;
        String ekip_adi = null;
        String id = null;
        String giris_tarih = null;
        String cikis_tarih = null;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {
                PERSONEL_TABLO_YAPISI.ID,
                PERSONEL_TABLO_YAPISI.ISIM,
                PERSONEL_TABLO_YAPISI.KISA_ISIM,
                PERSONEL_TABLO_YAPISI.KOD,
                PERSONEL_TABLO_YAPISI.KATEGORI,
                PERSONEL_TABLO_YAPISI.TIP,
                PERSONEL_TABLO_YAPISI.DETAY,
                PERSONEL_TABLO_YAPISI.SORUMLU,
                PERSONEL_TABLO_YAPISI.EKIP_ADI,
                PERSONEL_TABLO_YAPISI.IMALAT_ID,
                PERSONEL_TABLO_YAPISI.GIRIS_TARIH,
                PERSONEL_TABLO_YAPISI.CIKIS_TARIH
        };
        String[] selectionArgs = {String.valueOf(imalat_id)};
        Cursor cursor = sqLiteDatabase.query(PERSONEL_TABLO_YAPISI.TABLO_ADI,columns,PERSONEL_TABLO_YAPISI.IMALAT_ID+" =?",selectionArgs,null,null,null);
        String[] taslak_bilgisi = new String[0];
        List<String> list = new ArrayList();

        if (cursor.getCount()>0){
            while (cursor.moveToNext()){

                id = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.ID));
                isim = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.ISIM));
                kisa_isim = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.KISA_ISIM));
                kod = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.KOD));
                kategori = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.KATEGORI));
                tip = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.TIP));
                detay = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.DETAY));
                sorumlu = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.SORUMLU));
                ekip_adi = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.EKIP_ADI));
                imalat_id = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.IMALAT_ID));
                giris_tarih = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.GIRIS_TARIH));
                cikis_tarih = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.CIKIS_TARIH));
                taslak_bilgisi = new String[]{
                        String.valueOf(id),
                        isim,
                        kisa_isim,
                        kod,
                        kategori,
                        tip,
                        detay,
                        sorumlu,
                        ekip_adi,
                        imalat_id,
                        giris_tarih,
                        cikis_tarih
                };
                if (!list.contains(isim)) {
                    list.add(isim);
                }
            }
        } else {
            taslak_bilgisi = new String[]{String.valueOf(cursor.getCount())};
        }
        String[] kisiler = list.toArray(new String[list.size()]);
        cursor.close();
        sqLiteDatabase.close();
        return kisiler;
    }




    public String[] ReadPersonelwekip_adifmakina(String kategori){
        String isim = null;
        String kisa_isim = null;
        String kod = null;
        String ekip_adi = null;
        String tip = null;
        String detay = null;
        String sorumlu = null;
        String imalat_id = null;
        String id = null;
        String giris_tarih = null;
        String cikis_tarih = null;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] columns = {
                PERSONEL_TABLO_YAPISI.ID,
                PERSONEL_TABLO_YAPISI.ISIM,
                PERSONEL_TABLO_YAPISI.KISA_ISIM,
                PERSONEL_TABLO_YAPISI.KOD,
                PERSONEL_TABLO_YAPISI.KATEGORI,
                PERSONEL_TABLO_YAPISI.TIP,
                PERSONEL_TABLO_YAPISI.DETAY,
                PERSONEL_TABLO_YAPISI.SORUMLU,
                PERSONEL_TABLO_YAPISI.EKIP_ADI,
                PERSONEL_TABLO_YAPISI.IMALAT_ID,
                PERSONEL_TABLO_YAPISI.GIRIS_TARIH,
                PERSONEL_TABLO_YAPISI.CIKIS_TARIH
        };
        String[] selectionArgs = {String.valueOf(kategori)};
        Cursor cursor = sqLiteDatabase.query(PERSONEL_TABLO_YAPISI.TABLO_ADI,columns,PERSONEL_TABLO_YAPISI.KATEGORI+" =?",selectionArgs,null,null,null);
        String[] taslak_bilgisi = new String[0];
        List<String> list = new ArrayList();

        if (cursor.getCount()>0){
            while (cursor.moveToNext()){

                id = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.ID));
                isim = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.ISIM));
                kisa_isim = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.KISA_ISIM));
                kod = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.KOD));
                kategori = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.KATEGORI));
                tip = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.TIP));
                detay = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.DETAY));
                sorumlu = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.SORUMLU));
                ekip_adi = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.EKIP_ADI));
                imalat_id = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.IMALAT_ID));
                giris_tarih = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.GIRIS_TARIH));
                cikis_tarih = cursor.getString(cursor.getColumnIndex(PERSONEL_TABLO_YAPISI.CIKIS_TARIH));
                taslak_bilgisi = new String[]{
                        ekip_adi,
                        isim,
                        kisa_isim,
                        kod,
                        kategori,
                        tip,
                        detay,
                        sorumlu,
                        id,
                        imalat_id,
                        giris_tarih,
                        cikis_tarih
                };

                if (!list.contains(isim)){
                    list.add(isim);
                }


            }
        } else {
            taslak_bilgisi = new String[]{String.valueOf(cursor.getCount())};
        }
        String[] iscilik = list.toArray(new String[list.size()]);
        cursor.close();
        sqLiteDatabase.close();
        return iscilik;
    }

*/


}