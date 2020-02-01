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
                entity = MakineListe.class,
                parentColumns = "makine_id",
                childColumns = "makine"
        )
})
public class ImalatMakineEslesme {

    public String imalat;

    public String makine;

}
