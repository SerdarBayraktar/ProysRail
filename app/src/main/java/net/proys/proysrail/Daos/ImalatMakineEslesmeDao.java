package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.ImalatMakineEslesmeEntity;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface ImalatMakineEslesmeDao {


    @Insert
    void ekle(ImalatMakineEslesmeEntity imalatMakineEslesmeEntity);
}
