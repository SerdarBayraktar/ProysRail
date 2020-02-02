package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.MakineVerimsizlikEntity;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface MakineVerimsizlikDao {

    @Insert
    void ekle(MakineVerimsizlikEntity makineVerimsizlikEntity);
}
