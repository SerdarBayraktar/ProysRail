package net.proys.proysrail;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Proys Yazılım on 11.07.2019.
 */

public class Get_Set {
    private static String kullaniciAdi ="";
    private static String imalat="",sektör="";
    private static int kmbas=-13,kmson=-13,kmfark=-13,hatno=-13,kmOnline=0,kmfarkOnline=0;
    private static int position=0;
    private static String imalatIsgucuid ="",imalatIsgucu ="";
    private static int positionL2=0;
    private static String aciklamalar = "";
    private static int kullanici_id = 0;
    private static long kod =0;




    public static String getImalatIsgucuid() {
        return imalatIsgucuid;
    }

    public static void setImalatIsgucuid(String imalatIsgucuid) {
        Get_Set.imalatIsgucuid = imalatIsgucuid;
    }




    public static String getImalatIsgucu() {
        return imalatIsgucu;
    }

    public static void setImalatIsgucu(String imalatIsgucu) {
        Get_Set.imalatIsgucu = imalatIsgucu;
    }






    public static long getKod() {
        return kod;
    }

    public static void setKod(long kod) {
        Get_Set.kod = kod;
    }





    public static int getKullanici_id() {
        return kullanici_id;
    }

    public static void setKullanici_id(int kullanici_id) {
        Get_Set.kullanici_id = kullanici_id;
    }




    public static int getPositionL2() {
        return positionL2;
    }

    public static void setPositionL2(int positionL2) {
        Get_Set.positionL2 = positionL2;
    }




    public static int getPosition() {
        return position;
    }

    public static void setPosition(int position) {
        Get_Set.position = position;
    }





    public static int getKmOnline() {
        return kmOnline;
    }

    public static void setKmOnline(int kmOnline) {
        Get_Set.kmOnline = kmOnline;
    }





    public static int getKmfarkOnline() {
        return kmfarkOnline;
    }

    public static void setKmfarkOnline(int kmfarkOnline) {
        Get_Set.kmfarkOnline = kmfarkOnline;
    }





    public static int getHatno() {
        return hatno;
    }

    public static void setHatno(int hatno) {
        Get_Set.hatno = hatno;
    }



    public static String getAciklamalar() {
        return aciklamalar;
    }

    public static void setAciklamalar(String aciklamalar) {
        Get_Set.aciklamalar = aciklamalar;
    }



    public static String getKullaniciAdi() {

        return kullaniciAdi;
    }

    public static void setKullaniciAdi(String kullaniciAdi) {
        Get_Set.kullaniciAdi = kullaniciAdi;
    }





    public static int getKmbas() {
        return kmbas;
    }

    public static void setKmbas(int kmbas) {
        Get_Set.kmbas = kmbas;
    }




    public static int getKmson() {
        return kmson;
    }

    public static void setKmson(int kmson) {
        Get_Set.kmson = kmson;
    }





    public static void setKmfark(int kmfark) {
        Get_Set.kmfark = kmfark;
    }





    public void setImalat(String imalat) {
        this.imalat = imalat;
    }
    public String getImalat() {
        return imalat;
    }


    public String getSektör() {
        return sektör;
    }

    public void setSektör(String sektör) {
        this.sektör = sektör;
    }
}
