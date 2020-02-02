package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.CalisanListeEntity;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface CalisanListeDao {


    @Insert
    void ekle(CalisanListeEntity calisanListeEntity);
}
