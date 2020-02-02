package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.MakineKategoriEntity;
import net.proys.proysrail.Entities.MaliyetDagiticiEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MaliyetDagiticiDao {


    @Insert
    void ekle(MaliyetDagiticiEntity maliyetDagiticiEntity);

    @Query("select * from MaliyetDagiticiEntity")
    List<MaliyetDagiticiEntity> readAll();


    @Update
    void update(MaliyetDagiticiEntity maliyetDagiticiEntity);
}
