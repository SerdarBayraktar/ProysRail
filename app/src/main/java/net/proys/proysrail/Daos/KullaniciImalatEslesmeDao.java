package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.KullaniciImalatEslesmeEntity;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface KullaniciImalatEslesmeDao {


    @Insert
    void ekle(KullaniciImalatEslesmeEntity kullaniciImalatEslesmeEntity);
}
