package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.MakineListeEntity;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface MakineListeDao {

    @Insert
    void ekle(MakineListeEntity makineListeEntity);
}
