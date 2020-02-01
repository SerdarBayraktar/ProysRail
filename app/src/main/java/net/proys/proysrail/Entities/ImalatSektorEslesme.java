package net.proys.proysrail.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = ImalatListe.class,
                parentColumns = "imalat_id",
                childColumns = "imalat"
        ),
        @ForeignKey(
                entity = SektorListe.class,
                parentColumns = "sektor_id",
                childColumns = "sektor"
        )
})
public class ImalatSektorEslesme {

    public String imalat;

    public String sektor;

    public boolean tahmin;
}
