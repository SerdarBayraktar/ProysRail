package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.AciklamalarEntity;
import net.proys.proysrail.Entities.CalisanPuantajEntity;

import androidx.room.Dao;
import androidx.room.Insert;
@Dao
public interface CalisanPuantajDao {


    @Insert
    void ekle(CalisanPuantajEntity calisanPuantajEntity);
}
