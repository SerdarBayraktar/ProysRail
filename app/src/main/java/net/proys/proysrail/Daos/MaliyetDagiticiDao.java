package net.proys.proysrail.Daos;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface MaliyetDagiticiDao {


    @Insert
    void ekle(MaliyetDagiticiDao maliyetDagiticiDao);
}
