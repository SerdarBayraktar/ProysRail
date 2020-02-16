package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.AciklamalarEntity;
import net.proys.proysrail.Entities.CalisanPuantajEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CalisanPuantajDao {


    @Insert
    void ekle(CalisanPuantajEntity calisanPuantajEntity);

    @Query("select calisan from CalisanPuantajEntity where gerceklesme=:gerceklesme_id and imalat=:imalat and bildiri=:bildiri and puantaj_tipi=:calisti ")
    List<CalisanPuantajEntity> readCalisanlar(String gerceklesme_id, String imalat, String bildiri, String calisti);

    @Query("select * from CalisanPuantajEntity where gerceklesme=:gerceklesmeId and imalat=:imalatId and bildiri=:bildiriId and puantaj_tipi=:kaynakId")
    List<CalisanPuantajEntity> readCalisan(String gerceklesmeId, String imalatId, String bildiriId, String kaynakId);

    @Delete
    void deleteCalisanPuantaj(CalisanPuantajEntity calisanPuantajEntity);

    @Query("select * from CalisanPuantajEntity")
    List<CalisanPuantajEntity> readAll();

    @Query("select * from CalisanPuantajEntity where bildiri=:bildiriId")
    List<CalisanPuantajEntity> readCalisan(String bildiriId);

    @Query("select * from CalisanPuantajEntity where bildiri=:bildiriId and gerceklesme=:gerceklesmeId")
    List<CalisanPuantajEntity> readCalisan(String bildiriId,String gerceklesmeId);

    @Query("select puantaj,calisan from CalisanPuantajEntity where bildiri=:bildiriId and gerceklesme=:gerceklesmeId and puantaj_tipi=:calisti")
    List<CalisanPuantajEntity> readCalisan(String bildiriId,String gerceklesmeId, String calisti);

    @Query("update CalisanPuantajEntity set puantaj=:puantaj where bildiri=:bildiriId and gerceklesme=:gerceklesmeId and calisan=:calisanId")
    void PopUpUpdate(Float puantaj, String bildiriId,String gerceklesmeId,String calisanId);

    @Query("select calisan,puantaj from CalisanPuantajEntity where tarih=:tarih and calisan=:calisanId")
    List<CalisanPuantajEntity> readIscilikPuantaj(String tarih, String calisanId);

    @Query("select puantaj from CalisanPuantajEntity where bildiri=:bildiriId and calisan=:calisanId")
    List<CalisanPuantajEntity> readIscidetay(String bildiriId,String calisanId);


}
