package net.proys.proysrail.Daos;

import android.util.Log;

import net.proys.proysrail.Entities.BildiriTipListeEntity;
import net.proys.proysrail.Entities.BildirilerEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface BildirilerDao {

    @Insert
    void ekle(BildirilerEntity bildirilerEntity);

    @Query("select * from BildirilerEntity")
    List<BildirilerEntity> readAll();

    @Update
    void update(BildirilerEntity bildirilerEntity);

    @Query("select bildiri_tarih,bildiri_id,kullanici from BildirilerEntity where kullanici=:kullanici_id and bildiri_id=:bildiri_id")
    List<BildirilerEntity> ReadBildiriListesifor1b(int kullanici_id,String bildiri_id);//same name with sqlite and DO NOT FORGET TO GET COUNT OF LIST

    @Query("select kullanici,bildiri_tarih from BildirilerEntity where kullanici=:kullanici_id")
    List<BildirilerEntity> readTarihler(int kullanici_id);
    //Important note bildiri kullanici eslesmesi sonucu bildireler tablosunda satırında yok kararı verilmeden önce panelden bildirilerdao guncelle

    @Query("select * from BildirilerEntity where bildiri_id=:bildiriId")
    List<BildirilerEntity> readBildiri(Long bildiriId);

    @Delete
    void delete(BildirilerEntity bildirilerEntity);

    @Query("select * from BildirilerEntity where kullanici=:kullaniciId and kabul=:kabul1 or kabul=:kabul2")
    List<BildirilerEntity> read(Integer kullaniciId ,Integer kabul1,Integer kabul2);
}
