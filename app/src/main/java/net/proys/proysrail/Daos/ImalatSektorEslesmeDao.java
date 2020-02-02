package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.ImalatSektorEslesmeEntity;
import net.proys.proysrail.Entities.MakineKategoriEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ImalatSektorEslesmeDao {


    @Insert
    void ekle(ImalatSektorEslesmeEntity imalatSektorEslesmeEntity);

    @Query("select * from ImalatSektorEslesmeEntity")
    List<ImalatSektorEslesmeEntity> readAll();


    @Update
    void update(ImalatSektorEslesmeEntity imalatSektorEslesmeEntity);
}
