package net.proys.proysrail.Entities;


import java.sql.Date;

import androidx.annotation.NonNull;
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
                entity = ImalatGerceklesmeEntity.class,
                parentColumns = "gerceklesme_id",
                childColumns = "gerceklesme"
        )
})
public class AciklamalarEntity {

    @PrimaryKey(autoGenerate = true)
    public  Integer aciklama_id;

    public String tarih;

    public String aciklama;//todo text

    @NonNull
    public String bildiri;

    public String gerceklesme;
}
