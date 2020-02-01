package net.proys.proysrail.Entities;

import java.sql.Date;

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
                entity = MakineListe.class,
                parentColumns = "makine_id",
                childColumns = "makine"
        ),
        @ForeignKey(
                entity = ImalatListe.class,
                parentColumns = "imalat_id",
                childColumns = "imalat"
        ),
        @ForeignKey(
                entity = EtkenGerceklesme.class,
                parentColumns = "etken_gercek_id",
                childColumns = "etken"
        )
})
public class MakineVerimsizlik {

    @PrimaryKey(autoGenerate = true)
    public int makineverimsizlik_id;

    public String bildiri;

    public String makine;

    public Date tarih;

    public String imalat;

    public Float deger;

    public String etken;
}
