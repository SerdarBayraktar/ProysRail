package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.BildirilerEntity;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface BildirilerDao {



    @Insert
    void ekle(BildirilerEntity bildirilerEntity);
}
