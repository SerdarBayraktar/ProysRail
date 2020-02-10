package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.ImalatGerceklesmeEntity;
import net.proys.proysrail.Entities.ImalatListeEntity;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface ImalatGerceklesmeDao {

    @Insert
    void ekle(ImalatGerceklesmeEntity imalatGerceklesmeEntity);
}
