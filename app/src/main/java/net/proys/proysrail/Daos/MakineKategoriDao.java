package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.MakineKategoriEntity;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface MakineKategoriDao {


    @Insert
    void ekle(MakineKategoriEntity makineKategoriEntity);
}
