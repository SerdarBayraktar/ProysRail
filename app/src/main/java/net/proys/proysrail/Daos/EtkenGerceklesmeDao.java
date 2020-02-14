package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.EtkenGerceklesmeEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface EtkenGerceklesmeDao {

    @Insert
    void ekle(EtkenGerceklesmeEntity etkenGerceklesme);
    //"select * ,EtkenListeEntity.isim  from EtkenGerceklesmeEntity Inner join EtkenListeEntity On etken=etken_id Where gerceklesme=:gerceklesme"
    @Query("select *  from EtkenGerceklesmeEntity Where gerceklesme=:gerceklesme")
    List<EtkenGerceklesmeEntity> read(int gerceklesme);//ReadEtkenListesiforlistroom

    @Query("select etken_gercek_id,gerceklesme,etken from EtkenGerceklesmeEntity where gerceklesme=:gerceklesme_id and etken=:etken_id")
    List<EtkenGerceklesmeEntity> readNumberforradio(int gerceklesme_id,int etken_id);//ReadVerimsizlikPart2room

    @Query("select deger,gerceklesme,etken from EtkenGerceklesmeEntity as entity where gerceklesme=:gerceklesme_id")
    List<EtkenGerceklesmeEntity> readEtkenDeger(int gerceklesme_id);//ReadTaslakVerimsizlikroom
}
