package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.KullaniciImalatEslesmeEntity;
import net.proys.proysrail.Entities.KullanicilarEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface KullaniciImalatEslesmeDao {


    @Insert
    void ekle(KullaniciImalatEslesmeEntity kullaniciImalatEslesmeEntity);



    @Query("select * from KullaniciImalatEslesmeEntity")
    List<KullaniciImalatEslesmeEntity> readAll();

    @Update
    void update(KullaniciImalatEslesmeEntity kullaniciImalatEslesmeEntity);
}
