package net.proys.proysrail.Daos;

import net.proys.proysrail.Entities.ImalatGerceklesmeEntity;
import net.proys.proysrail.Entities.ImalatListeEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ImalatGerceklesmeDao {

    @Insert
    void ekle(ImalatGerceklesmeEntity imalatGerceklesmeEntity);

    @Query("select * from ImalatGerceklesmeEntity where bildiri=:bildiri")
    List<ImalatGerceklesmeEntity> readIlerleme(String bildiri);

    @Query("select * from ImalatGerceklesmeEntity where bildiri=:bildiri and imalat=:imalat")
    List<ImalatGerceklesmeEntity> readIlerleme(String bildiri, String imalat);

    @Query("select * from ImalatGerceklesmeEntity where gerceklesme_id=:gerceklesmeId")
    List<ImalatGerceklesmeEntity> readGerceklesme(Integer gerceklesmeId);

    @Query("update ImalatGerceklesmeEntity set hat_no=:hatNo where gerceklesme_id=:gerceklesmeId")
    void update(Integer hatNo,Integer gerceklesmeId);

    @Delete
    void delete(ImalatGerceklesmeEntity imalatGerceklesmeEntity);


}
