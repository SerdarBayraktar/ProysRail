package net.proys.proysrail.Daos;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface MakineListeDao {

    @Insert
    void ekle(MakineListeDao makineListeDao);
}
