package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.ImalatSektorEslesmeEntity;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface ImalatSektorEslesmeDao {


    @Insert
    void ekle(ImalatSektorEslesmeEntity imalatSektorEslesmeEntity);
}
