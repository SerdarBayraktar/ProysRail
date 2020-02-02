package net.proys.proysrail.Entities;

import java.sql.Date;

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
        ),
        @ForeignKey(
                entity = BildirilerEntity.class,
                parentColumns = "bildiri_id",
                childColumns = "bildiri"
        )

})
public class ImalatGerceklesmeEntity {

    @PrimaryKey(autoGenerate = true)
    public int gerceklesme_id;

    public  String imalat;

    public Date tarih;

    public int km_bas;

    public int km_bit;

    public String sektor;

    public int hat_no;

    public int ilerleme;

    public String bildiri;

    public int vardiya;

    public boolean minpi;
}
