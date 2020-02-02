package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.EtkenListeEntity;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface EtkenListeDao {

    @Insert
    void ekle(EtkenListeEntity etkenListeEntity);


}
