package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.BildirilerEntity;
import net.proys.proysrail.Entities.KullanicilarEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface KullanicilarDao {


    @Insert
    void ekle(KullanicilarEntity kullanicilarEntity);


    @Query("select * from Kullanicilarentity")
    List<KullanicilarEntity> readAll();

    @Update
    void update(KullanicilarEntity kullanicilarEntity);

    @Query("select * from KullanicilarEntity where email=:email")
    List<KullanicilarEntity> read(String email);

    @Query("select * from KullanicilarEntity where kullanici_id=:kullaniciId")
    List<KullanicilarEntity> readId(Integer kullaniciId);
}
