package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.AciklamalarEntity;
import net.proys.proysrail.Entities.CalisanVerimsizlikEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CalisanVerimsizlikDao {

    @Insert
    void ekle(CalisanVerimsizlikEntity calisanVerimsizlikEntity);

    @Query("select etken,deger from CalisanVerimsizlikEntity where bildiri=:bildiriId and calisan=:calisanId")
    List<CalisanVerimsizlikEntity> readVerimsizlik(String bildiriId, String calisanId);

    @Query("Delete from CalisanVerimsizlikEntity where bildiri=:bildiriId and calisan=:calisanId and imalat=:imalatId")
    void deleteVerimsizlik(String bildiriId,String calisanId,String imalatId);
}
