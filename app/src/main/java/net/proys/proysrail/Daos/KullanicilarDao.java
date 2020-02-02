package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.KullanicilarEntity;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface KullanicilarDao {


    @Insert
    void ekle(KullanicilarEntity kullanicilarEntity);
}
