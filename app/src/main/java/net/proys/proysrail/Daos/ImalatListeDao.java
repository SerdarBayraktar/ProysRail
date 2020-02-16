package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.ImalatListeEntity;
import net.proys.proysrail.Entities.KullaniciBildiriEslesmeEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ImalatListeDao {



    @Insert
    void ekle(ImalatListeEntity imalatListeEntity);


    @Query("select * from ImalatListeEntity")
    List<ImalatListeEntity> readAll();

    @Update
    void update(ImalatListeEntity imalatListeEntity);

    @Query("select isim from ImalatListeEntity where imalat_id=:id")
    List<ImalatListeEntity> readImalatIsmi(Integer id);

    @Query("select imalat_id from ImalatListeEntity where isim=:isim")
    List<ImalatListeEntity> readImalatId(String isim);

    @Query("select * from ImalatListeEntity where imalat_id=:id")
    List<ImalatListeEntity> readImalat(Integer id);

    @Query("select * from ImalatListeEntity where isim=:isim")
    List<ImalatListeEntity> readImalat(String isim);

    @Query("select isim from ImalatListeEntity where takip=:takip")
    List<ImalatListeEntity> readImalat(Boolean takip);
}
