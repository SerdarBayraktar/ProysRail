package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.MaliyetDagiticiEntity;
import net.proys.proysrail.Entities.SektorListeEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface SektorListeDao {


    @Insert
    void ekle(SektorListeEntity sektorListeEntity);

    @Query("select * from SektorListeEntity")
    List<SektorListeEntity> readAll();


    @Update
    void update(SektorListeEntity sektorListeEntity);
}
