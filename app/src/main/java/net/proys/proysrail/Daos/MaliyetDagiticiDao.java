package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.MaliyetDagiticiEntity;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface MaliyetDagiticiDao {


    @Insert
    void ekle(MaliyetDagiticiEntity maliyetDagiticiEntity);
}
