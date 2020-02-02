package net.proys.proysrail.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {//todo all check
        @ForeignKey(
                entity = EtkenListeEntity.class,
                parentColumns = "etken_id",
                childColumns = "etken"
        ),
        @ForeignKey(
                entity = ImalatGerceklesmeEntity.class,
                parentColumns = "gerceklesme_id",
                childColumns = "gerceklesme"
        )
})
public class EtkenGerceklesmeEntity {

    @PrimaryKey(autoGenerate = true)
    public int etken_gercek_id;

    public String etken;

    public Float deger;

    public String gerceklesme;

}
