package net.proys.proysrail;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.parse.Parse;
import com.parse.ParseInstallation;

import net.proys.proysrail.Entities.MakineKategoriEntity;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static RoomDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this,Anasayfa.class);
        startActivity(intent);
        //kopyala();
        data();
        //data1();
        //Parse.initialize(this);
        //ParseInstallation.getCurrentInstallation().saveInBackground();
        RoomStart();

        // Intent intent = new Intent(MainActivity.this,Anasayfa.class);
        //startActivity(intent);
    }
    public void kopyala(){
        DatabaseCopyHelper databaseCopyHelper = new DatabaseCopyHelper(this);
        try {
            databaseCopyHelper.createDataBase();
        }catch (IOException e){
            e.printStackTrace();
        }
        databaseCopyHelper.openDataBase();
    }

    protected void data(){

        SharedPreferences sp = getSharedPreferences("Database",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        System.out.println("ads "+ sp.getBoolean("issync",false));
        if(!sp.getBoolean("issync",false)) {
            Login_SQLiteHelper login_sqLiteHelper = new Login_SQLiteHelper(this);
            login_sqLiteHelper.Write(101,"Osman Tofan","Hat İşleri Şefi","","123","T0012--T0013--T0014--T0015","B0001--B0004","R0002","R0022");
            login_sqLiteHelper.Write(102,"Emre Yalçıntaş","Hat İşleri Mühendisi","","123","T0001--T0002","B0001--B0004","R0002","R0022");
            login_sqLiteHelper.Write(103,"Lider Ülkü","Hat İşleri Teknikeri","lider.ulku@ym.com.tr","123","T0012--T0013--T0014--T0015","B0001","R0002","R0022");
            login_sqLiteHelper.Write(104,"Sedat Yıldırım","Hat İşleri Formeni","","123","T0012--T0013--T0014--T0015","B0001--B0004","R0002","R0022");
            login_sqLiteHelper.Write(105,"Enes Güler","Makine İşletme Mühendisi","","123","","B0003--B0004","R0002","R0022");
            login_sqLiteHelper.Write(106,"Berkan Kanca","Harita Mühendisi","","123","T0023","B0001--B0004","R0002","R0022");
            login_sqLiteHelper.Write(107,"Melih Karakurt","Kaynak Mühendisi","","124","","B0002--B0004","R0002","R0022");
            login_sqLiteHelper.Write(108,"Proys","is sahibi","administrator","proys2019","T0012--T0013--T0014--T0015","B0001","R0002","R0022");
           //TODO BILDIRI KENDINE TANIMLI DEGIL DIKKAT KAYNAK ILERLEME
            login_sqLiteHelper.Write(109,"Proys","is sahibi","a","a","T0015","B0001","R0002","R0022");
            final SQLiteHelper database = new SQLiteHelper(this);
            database.WriteEtkenListe("V0001","Makine Arızası",-3,0);
            database.WriteEtkenListe("V0002","Hava Koşulları",-4,0);
            database.WriteEtkenListe("V0003","Sahaya Seyahat Süresi",-1,0);
            database.WriteEtkenListe("V0004","Tren Trafiği",-1,0);
            database.WriteEtkenListe("V0005","Akaryakıt İkmali",-1,0);
            database.WriteEtkenListe("V0006","Malzeme Bekleme",-1,0);

            database.WriteGet_Set("ImalatId","");
            //database.UpdateGet_Set("ImalatId","");
            //database.ReadGet_Set("ImalatId");

            database.WriteGet_Set("KullaniciAdi","");
            database.WriteGet_Set("KopyaNo","");
            database.WriteGet_Set("KullaniciId","");
/*

            database.WriteTaslakResource(109000120190924L,"23.09.2019","T0001","R0001","iscilik","verimsiz",-3,1,"V0001");
            database.WriteTaslakResource(101000120190923L,"23.09.2019","T0001","R0002","iscilik","ekip",10,1,"efor");
            database.WriteTaslakResource(101000120190923L,"23.09.2019","T0001","R0001","iscilik","isci",12,1,"R0002");
            database.WriteTaslakResource(101000120190923L,"23.09.2019","T0001","R0003","iscilik","isci",0,1,"R0002");
            database.WriteTaslakResource(101000120190923L,"23.09.2019","T0001","R0004","iscilik","isci",9,1,"R0002");
            database.WriteTaslakResource(101000120190923L,"23.09.2019","T0001","R0005","iscilik","isci",10,1,"R0002");
            database.WriteTaslakResource(101000120190923L,"23.09.2019","T0001","R0006","iscilik","isci",10,1,"R0002");
            database.WriteTaslakResource(101000120190923L,"23.09.2019","T0001","R0007","iscilik","isci",10,1,"R0002");
            database.WriteTaslakResource(101000120190923L,"23.09.2019","T0001","R0013","iscilik","isci",10,1,"R0002");
            database.WriteTaslakResource(101000120190923L,"23.09.2019","T0001","R0014","iscilik","isci",10,1,"R0002");
            database.WriteTaslakResource(101000120190923L,"23.09.2019","T0001","R0015","iscilik","isci",10,1,"R0002");
            database.WriteTaslakResource(101000120190923L,"23.09.2019","T0001","R0016","iscilik","isci",10,1,"R0002");
            database.WriteTaslakResource(101000120190923L,"23.09.2019","T0001","R0017","iscilik","isci",9,1,"efor");
*/

            database.WritePersonel("R0001","Abdülkadir Mustafa Yılmaz","123.672","iscilik","isci","R0002",8,1);
            database.WritePersonel("R0002","Balastlı Hatlar Ekibi (1)","124.707","iscilik","ekip","",8,1);
            database.WritePersonel("R0003","Bekir Gölpınar","123.152","iscilik","isci","R0002",8,1);
            database.WritePersonel("R0004","Sadık Görgülüarslan","464.075","iscilik","isci","R0002",8,1);
            database.WritePersonel("R0005","Serkan Yol","358.236","iscilik","isci","R0002",8,1);
            database.WritePersonel("R0006","Baki Karayalçın","876.241","iscilik","isci","R0002",8,1);
            database.WritePersonel("R0007","Erkan Çalık","123.762","iscilik","isci","R0002",8,1);
            database.WritePersonel("R0008","Burhan Savaş","123.874","iscilik","isci","R0002",8,1);
            database.WritePersonel("R0009","Celal Bozdoğan","445.780","iscilik","isci","R0002",8,1);
            database.WritePersonel("R0010","Eren Baştuğ","325.897","iscilik","isci","R0002",8,1);
            database.WritePersonel("R0011","Fatih Ölç","342.780","iscilik","isci","R0002",8,1);
            database.WritePersonel("R0012","Furkan Kuspınar","456.784","iscilik","isci","R0002",8,1);
            database.WritePersonel("R0013","Hacı Palabıyık","135.684","iscilik","isci","R0002",8,1);
            database.WritePersonel("R0014","Hasan Kuş","123.975","iscilik","isci","R0002",8,1);
            database.WritePersonel("R0015","Hıdır Yıldız","123.789","iscilik","isci","R0002",8,1);
            database.WritePersonel("R0016","İbrahim Özyürek","125.786","iscilik","isci","R0002",8,1);
            database.WritePersonel("R0017","Kazım Yiğit","243.562","iscilik","isci","",8,1);
            database.WritePersonel("R0020","Platform Vagon","053.122","makine","makine","R0022",8,16);
            database.WritePersonel("R0021","VAIACAR","067.578","makine","makine","R0022",8,1);
            database.WritePersonel("R0022","Panel Serme Grubu","","makine","grup","",8,1);
            database.WritePersonel("R0023","LDH 70","210","makine","makine","R0022",8,1);
/*

            database.WriteImalat("T0001", "Balastlı Hat Montajı", "birincil", "thm", 1.446, 402889, "B0001", 90168, 0,1);
            database.WriteImalat("T0002", "2. Kademe Balast Serimi", "birincil", "thm", 0.213, 412570, "B0001", 90026, 600,1);
            database.WriteImalat("T0003", "1. Kademe Buraj Yapılması", "birincil", "thm", 0.185, 402889, "", 90168, 0,1);
            database.WriteImalat("T0004", "2. Kademe Buraj Yapılması", "birincil", "thm", 0.103719517, 402889, "", 90168, 0,1);
            database.WriteImalat("T0005", "3. Kademe Buraj Yapılması", "birincil", "thm", 0.103719517, 402889, "", 90168, 0,1);
            database.WriteImalat("T0006", "Ray Alın Kaynaklarının Yapılması", "birincil", "thm", 0.284903457, 501216, "B0002", 90026, 540,1);
            database.WriteImalat("T0007", "Hattın Geriliminin Alınması", "birincil", "thm", 0.284903457, 430687, "B0002", 90026, 540,1);
            database.WriteImalat("T0008", "İnce Taşlama Yapıması", "birincil", "thm", 0.069067505, 505471, "B0002", 90026, 540,1);
            database.WriteImalat("T0009", "Koruyucu Taşlama Yapıması", "birincil", "thm", 0.2096614, 505471, "B0001", 90026, 5000,1);
            database.WriteImalat("T0010", "Makas Montajı", "birincil", "adet", 1303.116455, 99, "", 90167, 1,1);
            database.WriteImalat("T0011", "Makas Burajının Yapılması", "birincil", "adet", 239.6821112, 99, "", 90167, 1,1);
            database.WriteImalat("T0012", "Panelray Serimi", "birincil", "thm", 1.414897358, 98329, "B0001", 90001, 480,1);
            database.WriteImalat("T0013", "Balastsız Hat Rayı Montajı", "birincil", "thm", 1.808300411, 98329, "B0001", 90001, 480,1);
            database.WriteImalat("T0014", "Kendiliğinden Yerleşen Beton Dökülmesi", "birincil", "thm", 3.746058305, 98329, "B0001", 90001, 144,1);
            database.WriteImalat("T0015", "Dolgu Betonu Dökülmesi", "birincil", "ano", 1.816363329, 98329, "", 90001, 144,1);
            database.WriteImalat("T0020", "Travers Dağıtımı", "ikincil", "adet", 0, 0, "B0001", 90026, 540,1);
            database.WriteImalat("T0021", "Ray Dağıtımı", "ikincil", "adet", 0, 0, "B0001", 90026, 540,1);
            database.WriteImalat("T0022", "Makas Kaynağının Yapılması", "birincil", "adet", 407.2727273, 99, "", 90167, 1,1);
            database.WriteImalat("T0023", "Buraj Yapılması", "birincil", "thm", 0, 0, "", 90026, 3000,1);
            database.WriteImalat("T0024", "Makas Ön Montajı", "birincil", "adet", 0, 0, "", 90167, 1,1);
            //database.WriteImalat("T0025","Balastsız Hat Raylarının Dağıtılması","birincil","adet",0,0,"B0001",90001,540);
            database.WriteImalat("T0025", "Kot ve Eksen Ayarı", "ikincil", "ano", 0, 0, "B0001", 90001, 144,1);
            database.WriteImalat("T0026", "Tij ve Prob Montajı", "ikincil", "ano", 0, 0, "B0001", 90001, 144,1);
            database.WriteImalat("T0027", "Panel Serimi", "birincil", "ano", 0, 0, "B0002", 90026, 540,1);
            database.WriteImalat("T0028", "Panel Ön Montajı", "birincil", "adet", 0, 0, "B0001", 90168, 13,1);
            database.WriteImalat("T0029", "Geçici Makas Montajı", "birincil", "adet", 0, 0, "B0001", 90168, 1,1);
            database.WriteImalat("T0030", "Geçici Hat Montajı", "birincil", "thm", 0, 0, "B0001", 90168, 540,1);
            database.WriteImalat("T0031", "Konvansiyonel Hat Montajı", "birincil", "thm", 0, 0, "B0001", 90026, 540,1);
*/
            database.WriteSektor(100, "Kemerburgaz - Göktürk", "ana-hat", "1--2", 115052, 118183, 1, "T0015--T0016", "A1");
            database.WriteSektor(101, "E02 - İhsaniye", "ana-hat", "1--2", 120803, 128139, 1, "T0015--T0016", "A1");
            database.WriteSektor(102, "Havalimanı-1 - Hat Sonu", "ana-hat", "1--2", 134174, 138032, 1, "T0015--T0016", "A1");
            database.WriteSektor(103, "Gayrettepe - Kağıthane", "ana-hat", "1--2", 000000, 13798, 1, "T0015--T0016", "A1");
            database.WriteSektor(104, "İhsaniye - Havalimanİ-2", "ana-hat", "1--2", 128139, 131085, 1, "T0015--T0016", "A1");
            database.WriteSektor(105, "Hasdal - Kemerburgaz", "ana-hat", "1--2", 109440, 115052, 1, "T0015--T0016", "A1");
            database.WriteSektor(106, "Havalimanı-2 - Havalimaı-1", "ana-hat", "1--2", 131805, 134174, 1, "T0015--T0016", "A1");
            database.WriteSektor(107, "Göktürk - E02", "ana-hat", "1--2", 118182, 120823, 1, "T0015--T0016", "A1");
            database.WriteSektor(108, "Kağıthane - Hasdal", "ana-hat", "1--2", 103798, 109440, 1, "T0015--T0016", "A1");
            //database.WriteSektor(109, "M4 Cep Hattı", "ana-hat", "1", 000000, 100242, 1, "T0015--T0016", "A1");
            //database.WriteSektor(110, "Gayrettepe Loop Hattı", "ana-hat", "1", 000000, 100894, 1, "T0015--T0016", "A1");

            database.WriteImalat("T0015", "Faz-1 Betonu Dökülmesi", "birincil", "thm", 1, 1, "B0001", 104, 100,1);
            database.WriteImalat("T0016", "Faz-2 Betonu Dökülmesi (trapez kalıp dahil)", "birincil", "thm", 1, 1, "B0001", 104, 100,1);

/*
            database.WriteSektor(90001, "Tünel-189", "ana-hat", "1--2", 189862, 191084, 1, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "A1");
            database.WriteSektor(90002, "Tünel-194", "ana-hat", "1--2", 194175, 195069, 1, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "A1");
            database.WriteSektor(90003, "Tünel-198", "ana-hat", "1--2", 198103, 202944, 1, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "A1");
            database.WriteSektor(90004, "Tünel-212", "ana-hat", "1--2", 212905, 213703, 1, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "A1");
            database.WriteSektor(90005, "Tünel-250", "ana-hat", "1--2", 250080, 253669, 1, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "A1");
            database.WriteSektor(90006, "Tünel-263", "ana-hat", "1--2", 263081, 263802, 1, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "A2");
            database.WriteSektor(90007, "Tünel-264", "ana-hat", "1--2", 264526, 266425, 1, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "A2");
            database.WriteSektor(90008, "Tünel-268", "ana-hat", "1--2", 268996, 271734, 1, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "A2");
            database.WriteSektor(90009, "Tünel-282", "ana-hat", "1--2", 282286, 285972, 1, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "A2");
            database.WriteSektor(90010, "Tünel-296", "ana-hat", "1--2", 296384, 297369, 1, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "A2");
            database.WriteSektor(90011, "Tünel-299", "ana-hat", "1--2", 299311, 300297, 1, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "A2");
            database.WriteSektor(90012, "Tünel-301", "ana-hat", "1--2", 301121, 302882, 1, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "A2");
            database.WriteSektor(90013, "Tünel-304", "ana-hat", "1--2", 304321, 304882, 1, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "A2");
            database.WriteSektor(90014, "Tünel-305", "ana-hat", "1--2", 305124, 310432, 1, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "A2");
            database.WriteSektor(90015, "Tünel-318", "ana-hat", "1--2", 318989, 320345, 1, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "B1");
            database.WriteSektor(90016, "Tünel-325", "ana-hat", "1--2", 325981, 327744, 1, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "B1");
            database.WriteSektor(90017, "Tünel-344", "ana-hat", "1--2", 344881, 345385, 1, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "B1");
            database.WriteSektor(90018, "Tünel-346", "ana-hat", "1--2", 345809, 348578, 1, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "B1");
            database.WriteSektor(90019, "Tünel-372", "ana-hat", "1--2", 372654, 374844, 1, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "B2");
            database.WriteSektor(90020, "Tünel-383", "ana-hat", "1--2", 383739, 385396, 1, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "B2");
            database.WriteSektor(90021, "Tünel-387", "ana-hat", "1--2", 387395, 389065, 1, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "B2");
            database.WriteSektor(90022, "Tünel-389", "ana-hat", "1--2", 389504, 389987, 1, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "B2");
            database.WriteSektor(90023, "Tünel-390/B", "ana-hat", "1--2", 390699, 393317, 1, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "B2");
            database.WriteSektor(90024, "Km 266 Balastsız Bölge", "ana-hat", "1--2", 268760, 268996, 1, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "A1");
            database.WriteSektor(90025, "Sivas Balastsız Ana Hat", "ana-hat", "1--2", 405500, 406412, 0, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "B2");
            database.WriteSektor(90026, "Balastlı Ana Hat", "ana-hat", "1--2", 164004, 406221, 1, "T0027--T0031--T0020--T0021--T0002--T0023--T0006--T0007--T0008--T0009", "*5*");
            database.WriteSektor(90027, "Güney Hattı", "guney", "1--2", 394238, 396000, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90028, "Sivas Konvansiyonel Hat", "diger1", "1", 594214, 595244, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90029, "M-82 Yolu", "diger2", "1", 595213, 595337, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90030, "Sivas Manevra Hattı", "diger3", "1", 398111, 398634, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90031, "Yerköy Balastsız İstasyon Hattı", "istasyon1", "4", 0, 504, 0, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90032, "Yerköy Balastsız İstasyon Hattı", "istasyon2", "5", 0, 504, 0, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90033, "Yerköy Balastsız İstasyon Hattı", "istasyon3", "6", 0, 504, 0, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90034, "Yozgat Balastsız İstasyon Hattı", "istasyon4", "3", 0, 504, 0, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90035, "Yozgat Balastsız İstasyon Hattı", "istasyon5", "4", 0, 504, 0, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90036, "Yozgat Balastsız İstasyon Hattı", "istasyon6", "5", 0, 504, 0, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90037, "Sorgun Balastsız İstasyon Hattı", "istasyon7", "4", 0, 504, 0, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90038, "Sorgun Balastsız İstasyon Hattı", "istasyon8", "5", 0, 504, 0, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90039, "Sorgun Balastsız İstasyon Hattı", "istasyon9", "6", 0, 504, 0, "T0025--T0026--T0013--T0014--T0015--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90040, "Akdağmadeni Balastsız İstasyon Hattı", "istasyon10", "3", 0, 384, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0023--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90041, "Akdağmadeni Balastsız İstasyon Hattı", "istasyon11", "4", 0, 494, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0023--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90042, "Akdağmadeni Balastsız İstasyon Hattı", "istasyon12", "5", 0, 494, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0023--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90043, "Sivas Balastsız İstasyon Hattı", "istasyon13", "3", 0, 413, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0023--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90044, "Sivas Balastsız İstasyon Hattı", "istasyon14", "4", 0, 384, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0023--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90045, "Sivas Balastsız İstasyon Hattı", "istasyon15", "5", 0, 245, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0023--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90046, "Yerköy Balastlı İstasyon Hattı", "istasyon1", "4", 504, 791, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90047, "Yerköy Balastlı İstasyon Hattı", "istasyon2", "5", 504, 1173, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90048, "Yerköy Balastlı İstasyon Hattı", "istasyon3", "6", 504, 1173, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90049, "Yozgat Balastlı İstasyon Hattı", "istasyon4", "3", 504, 1048, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90050, "Yozgat Balastlı İstasyon Hattı", "istasyon5", "4", 504, 1048, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90051, "Yozgat Balastlı İstasyon Hattı", "istasyon6", "5", 504, 889, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90052, "Sorgun Balastlı İstasyon Hattı", "istasyon7", "4", 504, 897, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90053, "Sorgun Balastlı İstasyon Hattı", "istasyon8", "5", 504, 1283, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90054, "Sorgun Balastlı İstasyon Hattı", "istasyon9", "6", 504, 1283, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90055, "Akdağmadeni Balastlı İstasyon Hattı", "istasyon10", "3", 384, 704, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90056, "Akdağmadeni Balastlı İstasyon Hattı", "istasyon11", "4", 494, 869, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90057, "Akdağmadeni Balastlı İstasyon Hattı", "istasyon12", "5", 494, 869, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90058, "Sivas Balastlı İstasyon Hattı", "istasyon13", "3", 413, 592, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90059, "Sivas Balastlı İstasyon Hattı", "istasyon14", "4", 384, 473, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90060, "Sivas Balastlı İstasyon Hattı", "istasyon15", "5", 245, 675, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90061, "Yerköy Balastlı İstasyon Hattı", "istasyon16", "3", 0, 1015, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90062, "Yerköy Balastlı İstasyon Hattı", "istasyon17", "7", 0, 365, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90063, "Yerköy Balastlı İstasyon Hattı", "istasyon18", "8", 0, 148, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90064, "Sorgun Balastlı İstasyon Hattı", "istasyon19", "3", 0, 1120, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90065, "Sorgun Balastlı İstasyon Hattı", "istasyon20", "7", 0, 318, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90066, "Yıldızeli Balastlı İstasyon Hattı", "istasyon21", "3", 0, 1219, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90067, "Yıldızeli Balastlı İstasyon Hattı", "istasyon22", "4", 0, 1219, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90068, "Yıldızeli Balastlı İstasyon Hattı", "istasyon23", "5", 0, 1057, 0, "T0027--T0031--T0020--T0021--T0002--T0023--T0006--T0007--T0008--T0009", "");
            database.WriteSektor(90069, "Makas-1", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90070, "Makas-2", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90071, "Makas-3", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90072, "Makas-4", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90073, "Makas-5", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90074, "Makas-6", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90075, "Makas-7", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90076, "Makas-8", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90077, "Makas-9", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90078, "Makas-10", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90079, "Makas-11", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90080, "Makas-12", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90081, "Makas-13", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90082, "Makas-14", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90083, "Makas-15", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90084, "Makas-16", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90085, "Makas-17", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90086, "Makas-18", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90087, "Makas-19", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90088, "Makas-20", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90089, "Makas-21", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90090, "Makas-22", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90091, "Makas-23", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90092, "Makas-24", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90093, "Makas-25", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90094, "Makas-26", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90095, "Makas-27", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90096, "Makas-28", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90097, "Makas-29", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90098, "Makas-30", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90099, "Makas-31", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90100, "Makas-32", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90101, "Makas-33", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90102, "Makas-34", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90103, "Makas-35", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90104, "Makas-36", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90105, "Makas-37", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90106, "Makas-38", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90107, "Makas-39", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90108, "Makas-40", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90109, "Makas-41", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90110, "Makas-42", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90111, "Makas-43", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90112, "Makas-44", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90113, "Makas-45", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90114, "Makas-46", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90115, "Makas-47", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90116, "Makas-48", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90117, "Makas-49", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90118, "Makas-50", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90119, "Makas-51", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90120, "Makas-52", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90121, "Makas-53", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90122, "Makas-54", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90123, "Makas-55", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90124, "Makas-56", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90125, "Makas-57", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90126, "Makas-58", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90127, "Makas-59", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90128, "Makas-60", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90129, "Makas-61", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90130, "Makas-62", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90131, "Makas-63", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90132, "Makas-64", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90133, "Makas-65", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90134, "Makas-66", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90135, "Makas-67", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90136, "Makas-68", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90137, "Makas-69", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90138, "Makas-70", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90139, "Makas-71", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90140, "Makas-72", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90141, "Makas-73", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90142, "Makas-74", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90143, "Makas-75", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90144, "Makas-76", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90145, "Makas-77", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90146, "Makas-78", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90147, "Makas-79", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90148, "Makas-80", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90149, "Makas-81", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90150, "Makas-82", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90151, "Makas-83", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90152, "Makas-84", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90153, "Makas-85", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90154, "Makas-86", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90155, "Makas-87", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90156, "Makas-88", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90157, "Makas-89", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90158, "Makas-90", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90159, "Makas-91", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90160, "Makas-92", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90161, "Makas-93", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90162, "Makas-94", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90163, "Makas-95", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90164, "Makas-96", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90165, "Makas-97", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90166, "Makas-98", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90167, "Makas-99", "", "", 0, 0, 0, "T0010--T0011--T0022", "");
            database.WriteSektor(90168, "Sektör Yok", "", "", 0, 0, 0, "T0028--T0029--T0030", "");*/
            database.WriteBildiriler("B0001", "Günlük İlerleme", 1, 1, 0, "09.30", 1, "09.00");
            database.WriteBildiriler("B0002", "Kaynak İlerleme", 1, 1, 0, "09.30", 1, "09.00");
        }
        editor.putBoolean("issync", true);
        editor.commit();








        /*  }*/
    }
    protected void timer(){
        new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                Intent intent = new Intent(MainActivity.this,Anasayfa.class);
                startActivity(intent);

            }

        }.start();


    }
    protected void RoomStart(){
        //database.wordDao().ekle(word);
        database = Room.databaseBuilder(getApplicationContext(),RoomDatabase.class,"ProysDB").allowMainThreadQueries().build();

        MakineKategoriEntity makineKategoriEntity = new MakineKategoriEntity();
        makineKategoriEntity.setKategori_id(1);
        makineKategoriEntity.setKategori_isim("deneme");
        makineKategoriEntity.setRekabet(true);

        //database.makineKategoriDao().ekle(makineKategoriEntity);
        //List<MakineKategoriEntity> list = database.makineKategoriDao().ReadAll();
        //System.out.println("asd "+list.get(0).getKategori_isim());




    }

}
