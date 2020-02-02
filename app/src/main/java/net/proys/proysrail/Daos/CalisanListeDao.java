package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.CalisanListeEntity;

import androidx.room.Insert;

public interface CalisanListeDao {


    @Insert
    void ekle(CalisanListeEntity calisanListeEntity);
}
