package net.proys.proysrail;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.proys.proysrail.Daos.MakineListeDao;
import net.proys.proysrail.Daos.MakinePuantajDao;
import net.proys.proysrail.Daos.MakineVerimsizlikDao;
import net.proys.proysrail.Entities.AciklamalarEntity;
import net.proys.proysrail.Entities.BildiriTipListeEntity;
import net.proys.proysrail.Entities.BildirilerEntity;
import net.proys.proysrail.Entities.CalisanListeEntity;
import net.proys.proysrail.Entities.CalisanPuantajEntity;
import net.proys.proysrail.Entities.CalisanVerimsizlikEntity;
import net.proys.proysrail.Entities.GetSetEntity;
import net.proys.proysrail.Entities.ImalatGerceklesmeEntity;
import net.proys.proysrail.Entities.ImalatListeEntity;
import net.proys.proysrail.Entities.KullaniciBildiriEslesmeEntity;
import net.proys.proysrail.Entities.MakineListeEntity;
import net.proys.proysrail.Entities.MakinePuantajEntity;
import net.proys.proysrail.Entities.MakineVerimsizlikEntity;
import net.proys.proysrail.Fragments.MakinePuantaj;
import net.proys.proysrail.Items.İsciPuantajItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.room.Room;

public class RoomHelper {
    private Context mContext;
    RoomDatabase database;
    protected RoomHelper(Context context) {
        this.mContext = context;
        database = RoomDatabase.getDatabase(mContext);
    }
    protected String ReadBildiriListesiforDateComparision(int kullanici_id,String bildiri_id){
        List<BildirilerEntity> entities = database.bildirilerDao().readTarihler(kullanici_id);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String tarih_str = null;
        if (entities.size()>0){
            Date enbüyük = null;
            for (int i=0;i<entities.size();i++) {
                if (bildiri_id.equals(entities.get(i).getBildiri_id().toString())) {
                    try {
                        Date tarih = simpleDateFormat.parse(entities.get(i).getBildiri_tarih());
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
        return tarih_str;

    }
    protected List<CalisanListeEntity> ReadPersonelforL4(String bildiri_id){// ekipler kısmı kesildi favekip yok dip not ekipler için kullanılıyor
        List<CalisanListeEntity> entities = database.calisanListeDao().readIsim(true);
        return entities;
    }
    protected List<String> ReadPersonelforL4Sepet(String gerceklesmeId,String imalatId,String bildiriId){//hazır
        List<String> isimler = new ArrayList<>();
        List<CalisanListeEntity> tumCalisanlar = database.calisanListeDao().readIsim(true);
        for (int i = 0; i < tumCalisanlar.size(); i++) {
            isimler.add(tumCalisanlar.get(i).getIsim());
        }
        List<CalisanPuantajEntity> calismislar = database.calisanPuantajDao().readCalisanlar(gerceklesmeId,imalatId,bildiriId,"calisti");
        for (int i = 0; i < calismislar.size(); i++) {
            String calisan = calismislar.get(i).getCalisan();
            isimler.remove(database.calisanListeDao().readCalisan(Integer.valueOf(calisan)).get(0).getIsim());
        }
        return isimler;
    }
    protected List<String> ReadPersonelforL4SepetMakine(String gerceklesmeId,String imalatId,String bildiriId){//hazır
        List<String> isimler = new ArrayList<>();
        List<MakineListeEntity> tumMakineler = database.makineListeDao().readAllMakineIsim();
        for (int i = 0; i < tumMakineler.size(); i++) {
            isimler.add(tumMakineler.get(i).getKisa_isim());
        }
        List<MakinePuantajEntity> calismislar = database.makinePuantajDao().readMakineler(gerceklesmeId,imalatId,bildiriId);
        for (int i = 0; i < calismislar.size(); i++) {
            String makine = calismislar.get(i).getMakine();
            isimler.remove(database.makineListeDao().readMakine(Integer.valueOf(makine)).get(0).getKisa_isim());
        }
        return isimler;
    }
    protected List<String> ReadPersonelforL4SepetSecilen(String gerceklesmeId,String imalatId,String bildiriId){//hazır
        List<CalisanPuantajEntity> calismislar = database.calisanPuantajDao().readCalisanlar(gerceklesmeId,imalatId,bildiriId,"calisti");
        List<String> isimler = new ArrayList<>();
        for (int i = 0; i < calismislar.size() ; i++) {
            String calisan = calismislar.get(i).getCalisan();
            isimler.add(database.calisanListeDao().readCalisan(Integer.valueOf(calisan)).get(0).getIsim());        }
        return isimler;
    }
    protected List<String> ReadPersonelforL4SepetSecilenMakine(String gerceklesmeId,String imalatId,String bildiriId){//hazır
        List<String> isimler = new ArrayList<>();
        List<MakinePuantajEntity> calismislar = database.makinePuantajDao().readMakineler(gerceklesmeId,imalatId,bildiriId);
        for (int i = 0; i < calismislar.size(); i++) {
            String makine = calismislar.get(i).getMakine();
            isimler.remove(database.makineListeDao().readMakine(Integer.valueOf(makine)).get(0).getKisa_isim());
        }
        return isimler;
    }
    protected int ReadTaslakforL4SepetSecilenClick(String gerceklesmeId,String imalatId,String bildiriId,String kaynakId){//hazır
        int x=1;
        CalisanPuantajEntity entity = database.calisanPuantajDao().readCalisan(gerceklesmeId,imalatId,bildiriId,kaynakId).get(0);
        database.calisanPuantajDao().deleteCalisanPuantaj(entity);
        return x;
    }
    protected int ReadTaslakforL4SepetSecilenClickMakine(String gerceklesmeId,String imalatId,String bildiriId,String makineId){
        int x=1;
        MakinePuantajEntity entity = database.makinePuantajDao().readMakine(gerceklesmeId,imalatId,bildiriId,makineId).get(0);
        database.makinePuantajDao().deleteMakinePuantaj(entity);
        return x;
    }
    protected String ReadPersonelwid(String calisanId){//id ver isim al
        return database.calisanListeDao().readCalisan(Integer.valueOf(calisanId)).get(0).getIsim();
    }
    protected String ReadPersonelwIsim(String isim){//isim ver id al
        return database.calisanListeDao().readCalisan(isim).get(0).getIsim();
    }
    protected Integer ReadImalatwisimforid(String isim){
        return database.imalatListeDao().readImalatId(isim).get(0).getImalat_id();
    }
    protected String ReadImalatwidforisim(Integer id){
        return database.imalatListeDao().readImalatIsmi(id).get(0).getIsim();
    }
    protected String ReadSektortwidforisim(Integer id){
        return database.sektorListeDao().readSektor(id).get(0).getIsim();
    }
    protected String ReadEtkenIdForIsim(String etkenId){
        return database.etkenListeDao().readIsim(Integer.valueOf(etkenId)).get(0).getIsim();
    }
    protected String ReadEtkenisim(String isim){
        return database.etkenListeDao().readEtken_id(isim).get(0).getEtken_id().toString();
    }
    protected List[] ReadIlerlemeKartlari(String bildiriId){
        List<ImalatGerceklesmeEntity> entities =  database.imalatGerceklesmeDao().readIlerleme(bildiriId);
        List<String> imalatlar = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        List<String> sektorler = new ArrayList<>();
        for (int i = 0; i <entities.size() ; i++) {
            if (imalatlar.contains(ReadImalatwidforisim(Integer.valueOf(entities.get(i).getImalat())))){
                int count = database.imalatGerceklesmeDao().readIlerleme(bildiriId,entities.get(i).getImalat()).size();
                numbers.add(count+1);
                imalatlar.add(ReadImalatwidforisim(Integer.valueOf(entities.get(i).getImalat())));
                sektorler.add(ReadSektortwidforisim(Integer.valueOf(entities.get(i).getSektor())));
            }else {
                imalatlar.add(ReadImalatwidforisim(Integer.valueOf(entities.get(i).getImalat())));
                numbers.add(1);
                sektorler.add(ReadSektortwidforisim(Integer.valueOf(entities.get(i).getSektor())));

            }
        }
        return new List[]{imalatlar,numbers,sektorler};

    }
    protected List[] ReadIsGucuKartlari(String bildiriId){
        List<CalisanPuantajEntity> entities =  database.calisanPuantajDao().readCalisan(bildiriId);
        List<String> imalatlar = new ArrayList<>();
        List<String> sektorler = new ArrayList<>();

        List<Integer> numbers = new ArrayList<>();
        List<Integer> isciSayisi = new ArrayList<>();
        for (int i = 0; i <entities.size() ; i++) {
            if (imalatlar.contains(ReadImalatwidforisim(Integer.valueOf(entities.get(i).getImalat())))){
                int count = database.imalatGerceklesmeDao().readIlerleme(bildiriId,entities.get(i).getImalat()).size();
                numbers.add(count+1);
                Integer sektorId = Integer.valueOf(database.imalatGerceklesmeDao().readGerceklesme(Integer.valueOf(entities.get(i).getGerceklesme())).get(0).getSektor());
                sektorler.add(ReadSektortwidforisim(sektorId));//imlatgerceklesmeden sektor çek sonra ismi çek

                imalatlar.add(ReadImalatwidforisim(Integer.valueOf(entities.get(i).getImalat())));
                isciSayisi.add(database.calisanPuantajDao().readCalisan(bildiriId,entities.get(i).getGerceklesme()).size());
            }else {
                imalatlar.add(ReadImalatwidforisim(Integer.valueOf(entities.get(i).getImalat())));
                numbers.add(1);
                Integer sektorId = Integer.valueOf(database.imalatGerceklesmeDao().readGerceklesme(Integer.valueOf(entities.get(i).getGerceklesme())).get(0).getSektor());
                sektorler.add(ReadSektortwidforisim(sektorId));//imlatgerceklesmeden sektor çek sonra ismi çek
                isciSayisi.add(database.calisanPuantajDao().readCalisan(bildiriId,entities.get(i).getGerceklesme()).size());
            }
        }
        return new List[]{imalatlar,numbers,isciSayisi,sektorler};

    }
    protected List[] ReadMakineKartlari(String bildiriId){
        List<MakinePuantajEntity> entities =  database.makinePuantajDao().readMakine(bildiriId);
        List<String> imalatlar = new ArrayList<>();
        List<String> sektorler = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        List<Integer> makineSayisi = new ArrayList<>();
        for (int i = 0; i <entities.size() ; i++) {
            if (imalatlar.contains(ReadImalatwidforisim(Integer.valueOf(entities.get(i).getImalat())))){
                int count = database.imalatGerceklesmeDao().readIlerleme(bildiriId,entities.get(i).getImalat()).size();
                numbers.add(count+1);
                Integer sektorId = Integer.valueOf(database.imalatGerceklesmeDao().readGerceklesme(Integer.valueOf(entities.get(i).getGerceklesme())).get(0).getSektor());
                sektorler.add(ReadSektortwidforisim(sektorId));//imlatgerceklesmeden sektor çek sonra ismi çek

                imalatlar.add(ReadImalatwidforisim(Integer.valueOf(entities.get(i).getImalat())));
                makineSayisi.add(database.makinePuantajDao().readMakine(bildiriId,entities.get(i).getGerceklesme()).size());
            }else {
                imalatlar.add(ReadImalatwidforisim(Integer.valueOf(entities.get(i).getImalat())));
                numbers.add(1);
                Integer sektorId = Integer.valueOf(database.imalatGerceklesmeDao().readGerceklesme(Integer.valueOf(entities.get(i).getGerceklesme())).get(0).getSektor());
                sektorler.add(ReadSektortwidforisim(sektorId));//imlatgerceklesmeden sektor çek sonra ismi çek
                makineSayisi.add(database.makinePuantajDao().readMakine(bildiriId,entities.get(i).getGerceklesme()).size());
            }
        }
        return new List[]{imalatlar,numbers,makineSayisi,sektorler};

    }
    protected List[] ReadAciklamaKartlari(String bildiriId){
        List<AciklamalarEntity> entities =  database.aciklamalarDao().readAciklamalar(bildiriId);
        List<String> imalatlar = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        List<Integer> aciklamaSayisi = new ArrayList<>();
        List<String> sektorler = new ArrayList<>();
        for (int i = 0; i <entities.size() ; i++) {
            if (imalatlar.contains(ReadImalatwidforisim(Integer.valueOf(entities.get(i).getImalat())))){
                int count = database.imalatGerceklesmeDao().readIlerleme(bildiriId,entities.get(i).getImalat()).size();
                numbers.add(count+1);
                imalatlar.add(ReadImalatwidforisim(Integer.valueOf(entities.get(i).getImalat())));
                Integer sektorId = Integer.valueOf(database.imalatGerceklesmeDao().readGerceklesme(Integer.valueOf(entities.get(i).getGerceklesme())).get(0).getSektor());
                sektorler.add(ReadSektortwidforisim(sektorId));//imlatgerceklesmeden sektor çek sonra ismi çek

                aciklamaSayisi.add(database.aciklamalarDao().readAciklamalar(bildiriId,entities.get(i).getGerceklesme()).size());
            }else {
                imalatlar.add(ReadImalatwidforisim(Integer.valueOf(entities.get(i).getImalat())));
                numbers.add(1);
                Integer sektorId = Integer.valueOf(database.imalatGerceklesmeDao().readGerceklesme(Integer.valueOf(entities.get(i).getGerceklesme())).get(0).getSektor());
                sektorler.add(ReadSektortwidforisim(sektorId));//imlatgerceklesmeden sektor çek sonra ismi çek
                aciklamaSayisi.add(database.aciklamalarDao().readAciklamalar(bildiriId,entities.get(i).getGerceklesme()).size());
            }
        }
        return new List[]{imalatlar,numbers,aciklamaSayisi,sektorler};

    }
    protected List[] ReadAciklamaL4(String bildiriId,String gerceklesmeId){
        List<AciklamalarEntity> entities = database.aciklamalarDao().readAciklamalar(bildiriId,gerceklesmeId);
        List<String> aciklamalar= new ArrayList<>();
        List<Integer> aciklama_idler= new ArrayList<>();
        for (int i = 0; i <entities.size() ; i++) {
            aciklamalar.add(entities.get(i).getAciklama());
            aciklama_idler.add(entities.get(i).getAciklama_id());
        }
        return new List[]{aciklamalar,aciklama_idler};
    }
    protected List<String> ReadAciklamaForRestAPI(String bildiriId,String gerceklesmeId){
        List<AciklamalarEntity> entities = database.aciklamalarDao().readAciklamalar(bildiriId,gerceklesmeId);
        List<String> aciklamalar= new ArrayList<>();
        for (int i = 0; i <entities.size() ; i++) {
            aciklamalar.add(entities.get(i).getAciklama());
        }
        return aciklamalar;
    }
    protected List[] ReadTaslakResourceforExListViewGroup(String bildiriId,String gerceklesmeId){
        List<CalisanPuantajEntity> entities = database.calisanPuantajDao().readCalisan(bildiriId, gerceklesmeId);
        List<String> kaynak_idler = new ArrayList<>();
        List<String> isimler = new ArrayList<>();
        List<Float> puantajlar = new ArrayList<>();
        for (int i = 0; i < entities.size(); i++) {
            kaynak_idler.add(entities.get(i).getCalisan());
            isimler.add(ReadPersonelwid(entities.get(i).getCalisan()));
            puantajlar.add(entities.get(i).getPuantaj());
        }
        return new List[]{kaynak_idler,isimler,puantajlar};
    }
    protected List[] ReadTaslakResourceforExListViewGroupMakine(String bildiriId,String gerceklesmeId){
        List<MakinePuantajEntity> entities = database.makinePuantajDao().readMakine(bildiriId, gerceklesmeId);
        List<String> kaynak_idler = new ArrayList<>();
        List<String> isimler = new ArrayList<>();
        List<Float> puantajlar = new ArrayList<>();
        for (int i = 0; i < entities.size(); i++) {
            kaynak_idler.add(entities.get(i).getMakine());
            isimler.add(ReadPersonelwid(entities.get(i).getMakine()));
            puantajlar.add(entities.get(i).getPuantaj());
        }
        return new List[]{kaynak_idler,isimler,puantajlar};
    }
    protected List[] ReadTaslakResourceforLsIscidetay(String bildiriId,String calisanId){
        List<CalisanVerimsizlikEntity> entities = database.calisanVerimsizlikDao().readVerimsizlik(bildiriId, calisanId);
        List<Float> degerler= new ArrayList<>();
        List<String> verimsizlikler = new ArrayList<>();
        for (int i = 0; i <entities.size() ; i++) {
            degerler.add(entities.get(i).getDeger());
            verimsizlikler.add(ReadEtkenIdForIsim(entities.get(i).getEtken()));
        }
        return new List[]{degerler,verimsizlikler};
    }
    protected List[] ReadTaslakResourceforLsMakinedetay(String bildiriId,String makineId){
        List<MakineVerimsizlikEntity> entities = database.makineVerimsizlikDao().readVerimsizlik(bildiriId, makineId);
        List<Float> degerler= new ArrayList<>();
        List<String> verimsizlikler = new ArrayList<>();
        for (int i = 0; i <entities.size() ; i++) {
            degerler.add(entities.get(i).getDeger());
            verimsizlikler.add(ReadEtkenIdForIsim(entities.get(i).getEtken()));
        }
        return new List[]{degerler,verimsizlikler};
    }
    public void PopUpUpdateCalisan(Float puantaj, String bildiriId,String gerceklesmeId,String calisanId){
        database.calisanPuantajDao().PopUpUpdate(puantaj, bildiriId, gerceklesmeId, calisanId);
    }
    public void PopUpUpdateMakine(Float puantaj, String bildiriId,String gerceklesmeId,String makineId){
        database.makinePuantajDao().PopUpUpdate(puantaj, bildiriId, gerceklesmeId, makineId);
    }
    public void UpdateTaslak(Integer hatNo, Integer gerceklesmeId){
        database.imalatGerceklesmeDao().update(hatNo, gerceklesmeId);
    }
    public void deleteBildiriler(String bildiriId){
        BildirilerEntity entity = new BildirilerEntity();
        entity.setBildiri_id(Long.valueOf(bildiriId));
        database.bildirilerDao().delete(entity);
    }
    public void DeleteTaslakListItem(Integer gerceklesmeId){
        ImalatGerceklesmeEntity entity = new ImalatGerceklesmeEntity();
        entity.setGerceklesme_id(gerceklesmeId);
        database.imalatGerceklesmeDao().delete(entity);
    }
    public void DeleteAciklamaEmpty(){
        database.aciklamalarDao().delete("");
    }
    public List<String> ReadImalatfL4(){
        List<String> imalatlar = new ArrayList<>();
        List<ImalatListeEntity> entities = database.imalatListeDao().readImalat(true);
        for (int i = 0; i <entities.size() ; i++) {
            imalatlar.add(entities.get(i).getIsim());
        }
        return imalatlar;
    }
    public ArrayList<İsciPuantajItem> readIscilikPuantaj(String tarih, String calisanIdler){
        ArrayList<İsciPuantajItem> isciPuantajItems = new ArrayList<>();
        String[] kaynakIdler = calisanIdler.split("--");
        for (int i = 0; i < kaynakIdler.length; i++) {
            List<CalisanPuantajEntity> entities = database.calisanPuantajDao().readIscilikPuantaj(tarih,kaynakIdler[i]);
            isciPuantajItems.add(new İsciPuantajItem(ReadPersonelwid(entities.get(0).getCalisan()),entities.get(0).getPuantaj(),"iscilik"));//todo kategoriye bir bak bence
        }
        return isciPuantajItems;
    }
    public String ReadTaslakResourceforIscidetayPuantaj(String bildiriId,String calisanId){
        List<CalisanPuantajEntity> entities = database.calisanPuantajDao().readIscidetay(bildiriId, calisanId);
        Float puantaj=0.0f;
        for (int i = 0; i < entities.size(); i++) {
            puantaj = puantaj + entities.get(i).getPuantaj();
        }
        return String.valueOf(puantaj);
    }
    public String ReadTaslakResourceforMakinedetayPuantaj(String bildiriId,String makineId){
        List<MakinePuantajEntity> entities = database.makinePuantajDao().readMakinedetay(bildiriId, makineId);
        Float puantaj=0.0f;
        for (int i = 0; i < entities.size(); i++) {
            puantaj = puantaj + entities.get(i).getPuantaj();
        }
        return String.valueOf(puantaj);
    }
    public void ReadTaslakResourceforExListViewChild(String bildiriId,String gerceklesmeId,List<String> idler,List<String> groupisimler){
        List<CalisanPuantajEntity> entities = database.calisanPuantajDao().readCalisan(bildiriId, gerceklesmeId);
        //bu l3 işçilik puantaj için gerekli
    }
    public String readGetSet(String key){
        if (database.getSetDao().readValue(key).size()==0){
            GetSetEntity entity = new GetSetEntity();
            entity.setKey(key);
            entity.setValue("");
            database.getSetDao().ekle(entity);
        }
        return database.getSetDao().readValue(key).get(0).getValue();
    }
    public void createGetSet(String key){
        if (database.getSetDao().readValue(key).size()==0){
            GetSetEntity entity = new GetSetEntity();
            entity.setKey(key);
            entity.setValue("");
            database.getSetDao().ekle(entity);
        }
    }
    public List[] ReadBildiriListesiforList(Integer kullaniciId){
        List<BildirilerEntity> entities1 = database.bildirilerDao().readAll();
        entities1.size();
        List<BildirilerEntity> entities = database.bildirilerDao().read(kullaniciId,0,2);
        List<String> isim = new ArrayList<>();
        List<String> deadline = new ArrayList<>();
        List<String> id = new ArrayList<>();
        List<String> tarihler = new ArrayList<>();
        for (int i = 0; i <entities.size() ; i++) {
            isim.add(readBildiritipi(entities.get(i).getBildiri_tipi())+"("+entities.get(i).getBildiri_tarih()+")");
            deadline.add("Son Bildiri Tarihi : "+ entities.get(i).getSon_giris());
            id.add(String.valueOf(entities.get(i).getBildiri_id()));
            tarihler.add(String.valueOf(entities.get(i).getBildiri_tarih()));

        }
        return new List[]{isim,deadline,id,tarihler};
    }
    public String readBildiritipi(Integer id){
        List<BildiriTipListeEntity> entities = database.bildiriTipListeDao().readAll();
        entities.size();

        return database.bildiriTipListeDao().read(id).get(0).getIsim();// java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
    }
    public void updateGetSet(String key,String value){
        GetSetEntity entity = new GetSetEntity();
        entity.setKey(key);
        entity.setValue(value);
        if (database.getSetDao().readValue(key).size()==0){
            database.getSetDao().ekle(entity);
        }else {
            database.getSetDao().update(entity);
        }
    }
    public List<String> ReadwithIdfor1b(int kullaniciId){
        List<KullaniciBildiriEslesmeEntity> entities = database.kullaniciBildiriEslesmeDao().read(kullaniciId);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < entities.size(); i++) {
            list.add(String.valueOf(entities.get(i).getBildiri_tipi()));
        }
        return list;
    }
    public boolean bildirilerCheckkwithConnection(Long bildiriId){
        int size = database.bildirilerDao().readBildiri(bildiriId).size();
        return size==0;//eşitse true vericek yani öyle bir bildiri yok yani çek getten
    }
}