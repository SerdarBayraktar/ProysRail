package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.ImalatMakineEslesmeEntity;
import net.proys.proysrail.Entities.MakineListeEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ImalatMakineEslesmeDao {


    @Insert
    void ekle(ImalatMakineEslesmeEntity imalatMakineEslesmeEntity);



    @Query("Select * from ImalatMakineEslesmeEntity")
    List<ImalatMakineEslesmeEntity> readAll();

    @Update
    void update(ImalatMakineEslesmeEntity imalatMakineEslesmeEntity);

}
