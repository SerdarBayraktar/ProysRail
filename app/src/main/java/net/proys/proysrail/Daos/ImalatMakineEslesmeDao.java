package net.proys.proysrail.Daos;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface ImalatMakineEslesmeDao {


    @Insert
    void ekle(ImalatMakineEslesmeDao imalatMakineEslesmeDao);
}
