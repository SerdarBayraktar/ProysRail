package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.CalisanPuantajEntity;
import net.proys.proysrail.Entities.MakinePuantajEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface MakinePuantajDao {

    @Insert
    void ekle(MakinePuantajEntity makinePuantajEntity);

    @Query("select makine from MakinePuantajEntity where gerceklesme=:gerceklesme_id and imalat=:imalat and bildiri=:bildiri ")
    List<MakinePuantajEntity> readMakineler(String gerceklesme_id, String imalat, String bildiri);

    @Query("select * from MakinePuantajEntity where gerceklesme=:gerceklesmeId and imalat=:imalatId and bildiri=:bildiriId and makine=:kaynakId ")
    List<MakinePuantajEntity> readMakine(String gerceklesmeId, String imalatId, String bildiriId, String kaynakId);

    @Delete
    void deleteMakinePuantaj(MakinePuantajEntity makinePuantajEntity);

    @Query("select * from MakinePuantajEntity where bildiri=:bildiriId")
    List<MakinePuantajEntity> readMakine(String bildiriId);

    @Query("select * from MakinePuantajEntity where bildiri=:bildiriId and gerceklesme=:gerceklesmeId")
    List<MakinePuantajEntity> readMakine(String bildiriId,String gerceklesmeId);

    @Query("update MakinePuantajEntity set puantaj=:puantaj where bildiri=:bildiriId and gerceklesme=:gerceklesmeId and makine=:makineId")
    void PopUpUpdate(Float puantaj, String bildiriId,String gerceklesmeId,String makineId);

    @Query("select puantaj from MakinePuantajEntity where bildiri=:bildiriId and makine=:makineId")
    List<MakinePuantajEntity> readMakinedetay(String bildiriId,String makineId);
}
