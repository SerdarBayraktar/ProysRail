package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.AciklamalarEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface AciklamalarDao {

    @Insert
    void ekle(AciklamalarEntity aciklamalarEntity);

    @Delete
    void deleteAciklama(AciklamalarEntity aciklamalarEntity);

    @Query("select * from AciklamalarEntity where bildiri=:bildiriId")
    List<AciklamalarEntity> readAciklamalar(String bildiriId);

    @Query("select * from AciklamalarEntity where bildiri=:bildiriId and gerceklesme=:gerceklesmeId")
    List<AciklamalarEntity> readAciklamalar(String bildiriId,String gerceklesmeId);

    @Query("Update AciklamalarEntity set aciklama=:aciklama where aciklama_id=:aciklamaId")
    void updateAciklamal4(String aciklama,Integer aciklamaId);

    @Query("delete from aciklamalarentity where aciklama=:aciklama")
    void delete(String aciklama);
}
