package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.AciklamalarEntity;
import net.proys.proysrail.Entities.CalisanVerimsizlikEntity;

import androidx.room.Insert;

public interface CalisanVerimsizlikDao {

    @Insert
    void ekle(CalisanVerimsizlikEntity calisanVerimsizlikEntity);
}
