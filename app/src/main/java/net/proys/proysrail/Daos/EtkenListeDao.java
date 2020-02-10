package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.EtkenListeEntity;
import net.proys.proysrail.Entities.MaliyetDagiticiEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface EtkenListeDao {

    @Insert
    void ekle(EtkenListeEntity etkenListeEntity);

    @Query("select * from EtkenListeEntity")
    List<EtkenListeEntity> readAll();

    @Update
    void update(EtkenListeEntity etkenListeEntity);

/*

    @Query("select * from EtkenListeEntity Where etken_id=:etken_id")//todo bu da aynı sıkıntıyı verdi
    List<EtkenListeEntity> readIsim(int etken_id);//ReadEtkenListesiforisimroom    could be str

    @Query("select etken_id,isim from EtkenListeEntity where isim=:isim")
    List<EtkenListeEntity> readEtken_id(String isim);//ReadEtkenListesiforidroom        could be str

    @Query("select vt_deger,etken_id from EtkenListeEntity where etken_id=:etken_id")
    int readDeger(int etken_id);//ReadEtkenListesifordegerroom

    @Query("select isim from EtkenListeEntity")
    List<EtkenListeEntity> readAllIsimler();//ReadEtkenListesiroom orjinaldaki radiolar kaldırıldı


    @Query("select * from EtkenListeEntity")
    List<EtkenListeEntity> readEtken_id();//todo bu da aynı sıkıntıyı verdi
    // rror: The columns returned by the query does not have the fields [etken_id] in net.proys.proysrail.Entities.EtkenListeEntity
    // even though they are annotated as non-null or primitive. Columns returned by the query: [isim]
*/


}
