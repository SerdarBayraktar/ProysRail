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
    public Integer id;

    public Integer imalat;

    public Integer sektor;

    public boolean tahmin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getImalat() {
        return imalat;
    }

    public void setImalat(Integer imalat) {
        this.imalat = imalat;
    }

    public Integer getSektor() {
        return sektor;
    }

    public void setSektor(Integer sektor) {
        this.sektor = sektor;
    }

    public boolean isTahmin() {
        return tahmin;
    }

    public void setTahmin(boolean tahmin) {
        this.tahmin = tahmin;
    }
}
