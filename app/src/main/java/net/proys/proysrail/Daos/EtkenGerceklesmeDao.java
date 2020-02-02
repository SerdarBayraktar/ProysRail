package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.EtkenGerceklesmeEntity;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface EtkenGerceklesmeDao {

    @Insert
    public void ekle(EtkenGerceklesmeEntity etkenGerceklesme);

}
