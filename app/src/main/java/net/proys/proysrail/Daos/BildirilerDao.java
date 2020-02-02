package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.BildirilerEntity;

import androidx.room.Insert;

public interface BildirilerDao {



    @Insert
    void ekle(BildirilerEntity bildirilerEntity);
}
