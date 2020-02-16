package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.CalisanVerimsizlikEntity;
import net.proys.proysrail.Entities.MakineVerimsizlikEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface MakineVerimsizlikDao {

    @Insert
    void ekle(MakineVerimsizlikEntity makineVerimsizlikEntity);

    @Query("select etken,deger from MakineVerimsizlikEntity where bildiri=:bildiriId and makine=:makineId")
    List<MakineVerimsizlikEntity> readVerimsizlik(String bildiriId, String makineId);
}
