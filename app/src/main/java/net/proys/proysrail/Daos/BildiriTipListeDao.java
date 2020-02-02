package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.BildiriTipListeEntity;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface BildiriTipListeDao {


    @Insert
    void ekle(BildiriTipListeEntity bildiriTipListeEntity);
}
