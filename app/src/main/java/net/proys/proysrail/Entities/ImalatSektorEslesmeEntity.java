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

    public int imalat;

    public int sektor;

    public boolean tahmin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImalat() {
        return imalat;
    }

    public void setImalat(int imalat) {
        this.imalat = imalat;
    }

    public int getSektor() {
        return sektor;
    }

    public void setSektor(int sektor) {
        this.sektor = sektor;
    }

    public boolean isTahmin() {
        return tahmin;
    }

    public void setTahmin(boolean tahmin) {
        this.tahmin = tahmin;
    }
}
