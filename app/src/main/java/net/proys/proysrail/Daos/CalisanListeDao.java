package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.CalisanListeEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CalisanListeDao {


    @Insert
    void ekle(CalisanListeEntity calisanListeEntity);

    @Query("Select * from CalisanListeEntity")
    List<CalisanListeEntity> readAll();
}
