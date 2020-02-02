package net.proys.proysrail.Entities;

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
                entity = CalisanListeEntity.class,
                parentColumns = "calisan_id",
                childColumns = "calisan"
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
public class CalisanVerimsizlikEntity {
    @PrimaryKey(autoGenerate = true)
    public int calisanverimsizlik_id;

    public String bildiri;

    public String calisan;

    public String tarih;

    public String imalat;

    public String etken;

    public Float deger;

}
