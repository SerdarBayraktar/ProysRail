package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.GetSetEntity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface GetSetDao {
    @Insert
    void ekle(GetSetEntity getSetEntity);

    @Query("Select value from GetSetEntity Where `key` =:key")
    String readValue(String key);

    @Update
    void update(GetSetEntity getSetEntity);
}
