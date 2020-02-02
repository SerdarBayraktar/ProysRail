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
                entity = ImalatGerceklesmeEntity.class,
                parentColumns = "gerceklesme_id",
                childColumns = "gerceklesme"
        )
})
public class MakinePuantajEntity {

    @PrimaryKey(autoGenerate = true)
    public int makinepuantaj_id;

    public String bildiri;

    public String makine;

    public String tarih;

    public String imalat;

    public Float puantaj;

    public String gerceklesme;
}
