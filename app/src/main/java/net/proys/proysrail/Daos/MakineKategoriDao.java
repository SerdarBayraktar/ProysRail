package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.MakineKategoriEntity;
import net.proys.proysrail.Entities.MakineListeEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MakineKategoriDao {


    @Insert
    void ekle(MakineKategoriEntity makineKategoriEntity);


    @Query("select * from MakineKategoriEntity")
    List<MakineKategoriEntity> readAll();


    @Update
    void update(MakineKategoriEntity makineKategoriEntity);
}
