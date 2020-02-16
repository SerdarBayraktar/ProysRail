package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.CalisanListeEntity;
import net.proys.proysrail.Entities.MakineListeEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MakineListeDao {

    @Insert
    void ekle(MakineListeEntity makineListeEntity);

    @Query("Select * from MakineListeEntity")
    List<MakineListeEntity> readAll();

    @Update
    void update(MakineListeEntity makineListeEntity);

    @Query("select * from MakineListeEntity where makine_id=:makineId")
    List<MakineListeEntity> readMakine(int makineId);

    @Query("select kisa_isim from MakineListeEntity ")
    List<MakineListeEntity> readAllMakineIsim();

}
