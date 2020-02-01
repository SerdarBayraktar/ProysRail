package net.proys.proysrail.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Bildiriler.class,
                parentColumns = "bildiri_id",
                childColumns = "bildiri"
        ),
        @ForeignKey(
                entity = CalisanListe.class,
                parentColumns = "calisan_id",
                childColumns = "calisan"
        ),
        @ForeignKey(
                entity = ImalatListe.class,
                parentColumns = "imalat_id",
                childColumns = "imalat"
        ),
        @ForeignKey(
                entity = ImalatGerceklesme.class,
                parentColumns = "gerceklesme_id",
                childColumns = "gerceklesme"
        ),
        @ForeignKey(
                entity = EtkenGerceklesme.class,
                parentColumns = "etken_gercek_id",
                childColumns = "gerceklesme"
        )
})
public class CalisanVerimsizlik {
    @PrimaryKey(autoGenerate = true)
    public int calisanverimsizlik_id;

    public String bildiri;

    public String calisan;

    public String tarih;

    public String imalat;

    public String etken;

    public Float deger;

}
