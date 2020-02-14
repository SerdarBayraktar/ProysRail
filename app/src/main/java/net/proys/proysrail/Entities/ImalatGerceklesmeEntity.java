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
    public Integer gerceklesme_id;

    public  String imalat;

    public String tarih;

    public Integer km_bas;

    public Integer km_bit;

    public String sektor;

    public Integer hat_no;

    public Integer ilerleme;

    public String bildiri;

    public Integer vardiya;

    public boolean minpi;
}
