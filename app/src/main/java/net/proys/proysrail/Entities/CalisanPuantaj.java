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
        )
})
public class CalisanPuantaj {

    @PrimaryKey(autoGenerate = true)
    public int calisanpuantaj_id;

    public String bildiri;

    public String calisan;

    public String tarih;

    public String imalat;

    public Float puantaj;

    public Float fazla_mesai;

    public String puantaj_tipi;

    public String gerceklesme;


}