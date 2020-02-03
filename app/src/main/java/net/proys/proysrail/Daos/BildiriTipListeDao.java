package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.BildiriTipListeEntity;
import net.proys.proysrail.Entities.EtkenListeEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface BildiriTipListeDao {


    @Insert
    void ekle(BildiriTipListeEntity bildiriTipListeEntity);

    @Query("select * from BildiriTipListeEntity")
    List<BildiriTipListeEntity> readAll();

    @Update
    void update(BildiriTipListeEntity bildiriTipListeEntity);
}
