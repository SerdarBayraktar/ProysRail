package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.AciklamalarEntity;
import net.proys.proysrail.Entities.CalisanVerimsizlikEntity;

import androidx.room.Dao;
import androidx.room.Insert;
@Dao
public interface CalisanVerimsizlikDao {

    @Insert
    void ekle(CalisanVerimsizlikEntity calisanVerimsizlikEntity);
}
