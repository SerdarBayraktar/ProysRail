package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.MakinePuantajEntity;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface MakinePuantajDao {

    @Insert
    void ekle(MakinePuantajEntity makinePuantajEntity);
}
