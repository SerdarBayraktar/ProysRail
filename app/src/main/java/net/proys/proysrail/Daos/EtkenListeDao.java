package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.EtkenListeEntity;
import net.proys.proysrail.Entities.MaliyetDagiticiEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface EtkenListeDao {

    @Insert
    void ekle(EtkenListeEntity etkenListeEntity);


    @Query("select * from EtkenListeEntity")
    List<EtkenListeEntity> readAll();


    @Update
    void update(EtkenListeEntity etkenListeEntity);


}
