package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.BildiriTipListeEntity;
import net.proys.proysrail.Entities.BildirilerEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface BildirilerDao {

    @Insert
    void ekle(BildirilerEntity bildirilerEntity);

    @Query("select * from BildirilerEntity")
    List<BildirilerEntity> readAll();

    @Update
    void update(BildirilerEntity bildirilerEntity);

}
