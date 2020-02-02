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
                entity = ImalatGerceklesmeEntity.class,
                parentColumns = "gerceklesme_id",
                childColumns = "gerceklesme"
        )
})
public class CalisanPuantajEntity {

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