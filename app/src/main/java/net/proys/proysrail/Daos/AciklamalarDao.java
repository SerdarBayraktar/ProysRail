package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.AciklamalarEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface AciklamalarDao {

    @Insert
    void ekle(AciklamalarEntity aciklamalarEntity);

}
