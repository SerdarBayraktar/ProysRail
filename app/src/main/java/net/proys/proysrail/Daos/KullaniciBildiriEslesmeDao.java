package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.KullaniciBildiriEslesmeEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface KullaniciBildiriEslesmeDao {


    @Insert
    void ekle(KullaniciBildiriEslesmeEntity kullaniciBildiriEslesmeEntity);


    @Query("select * from KullaniciBildiriEslesmeEntity")
    List<KullaniciBildiriEslesmeEntity> readAll();

    @Update
    void update(KullaniciBildiriEslesmeEntity kullaniciBildiriEslesmeEntity);
}
