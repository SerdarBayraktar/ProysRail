package net.proys.proysrail.Entities;

import java.sql.Date;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = BildirilerEntity.class,
                parentColumns = "bildiri_id",
                childColumns = "bildiri"
        ),
        @ForeignKey(
                entity = MakineListeEntity.class,
                parentColumns = "makine_id",
                childColumns = "makine"
        ),
        @ForeignKey(
                entity = ImalatListeEntity.class,
                parentColumns = "imalat_id",
                childColumns = "imalat"
        ),
        @ForeignKey(
                entity = EtkenGerceklesmeEntity.class,
                parentColumns = "etken_gercek_id",
                childColumns = "etken"
        )
})
public class MakineVerimsizlikEntity {

    @PrimaryKey(autoGenerate = true)
    public Integer makineverimsizlik_id;

    public String bildiri;

    public String makine;

    public String tarih;

    public String imalat;

    public Float deger;

    public String etken;
}
