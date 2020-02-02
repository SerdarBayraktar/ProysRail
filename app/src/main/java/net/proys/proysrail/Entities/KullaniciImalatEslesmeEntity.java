package net.proys.proysrail.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = KullanicilarEntity.class,
                parentColumns = "kullanici_id",
                childColumns = "kullanici"
        ),
        @ForeignKey(
                entity = ImalatListeEntity.class,
                parentColumns = "imalat_id",
                childColumns = "imalat"
        )
})
public class KullaniciImalatEslesmeEntity {


    @PrimaryKey(autoGenerate = true)
    public int id;

    public String kullanici;

    public String imalat;


}
