package net.proys.proysrail.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {//todo all check
        @ForeignKey(
                entity = EtkenGerceklesme.class,
                parentColumns = "imalat_id",
                childColumns = "etken"
        ),
        @ForeignKey(
                entity = SektorListe.class,
                parentColumns = "sektor_id",
                childColumns = "sektor"
        )
})
public class EtkenGerceklesme {

    @PrimaryKey(autoGenerate = true)
    public int etken_gercek_id;

    public String etken;

}
