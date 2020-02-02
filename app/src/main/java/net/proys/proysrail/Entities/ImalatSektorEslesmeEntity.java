package net.proys.proysrail.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = ImalatListeEntity.class,
                parentColumns = "imalat_id",
                childColumns = "imalat"
        ),
        @ForeignKey(
                entity = SektorListeEntity.class,
                parentColumns = "sektor_id",
                childColumns = "sektor"
        )
})
public class ImalatSektorEslesmeEntity {

    @PrimaryKey(autoGenerate =true)
    public int id;

    public String imalat;

    public String sektor;

    public boolean tahmin;
}
