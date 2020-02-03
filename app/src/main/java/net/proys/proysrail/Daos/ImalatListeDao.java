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
}
