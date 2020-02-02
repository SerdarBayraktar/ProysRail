package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.SektorListeEntity;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface SektorListeDao {


    @Insert
    void ekle(SektorListeEntity sektorListeEntity);
}
