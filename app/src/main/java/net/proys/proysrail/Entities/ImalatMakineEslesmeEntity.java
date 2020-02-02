package net.proys.proysrail.Entities;


import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = ImalatListeEntity.class,
                parentColumns = "imalat_id",
                childColumns = "imalat"
        ),
        @ForeignKey(
                entity = MakineListeEntity.class,
                parentColumns = "makine_id",
                childColumns = "makine"
        )
})
public class ImalatMakineEslesmeEntity {

    public String imalat;

    public String makine;

}
