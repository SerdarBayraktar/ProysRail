package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.BildiriTipListeEntity;

import androidx.room.Insert;

public interface BildiriTipListeDao {


    @Insert
    void ekle(BildiriTipListeEntity bildiriTipListeEntity);
}
