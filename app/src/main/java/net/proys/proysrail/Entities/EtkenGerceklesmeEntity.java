package net.proys.proysrail.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity/*(foreignKeys = {//todo all check
        @ForeignKey(
                entity = EtkenListeEntity.class,
                parentColumns = "etken_id",
                childColumns = "etken"
        ),
        @ForeignKey(
                entity = ImalatGerceklesmeEntity.class,
                parentColumns = "gerceklesme_id",
                childColumns = "gerceklesme"
        )
})*/
public class EtkenGerceklesmeEntity {

    @PrimaryKey(autoGenerate = true)
    public Integer etken_gercek_id;

    @ColumnInfo(name = "etken")
    public Integer etken;

    @ColumnInfo(name = "deger")
    public Double deger;

    @ColumnInfo(name = "gerceklesme")
    public Integer gerceklesme;

    public Integer getEtken_gercek_id() {
        return etken_gercek_id;
    }

    public void setEtken_gercek_id(Integer etken_gercek_id) {
        this.etken_gercek_id = etken_gercek_id;
    }

    public Integer getEtken() {
        return etken;
    }

    public void setEtken(Integer etken) {
        this.etken = etken;
    }

    public Double getDeger() {
        return deger;
    }

    public void setDeger(Double deger) {
        this.deger = deger;
    }

    public Integer getGerceklesme() {
        return gerceklesme;
    }

    public void setGerceklesme(Integer gerceklesme) {
        this.gerceklesme = gerceklesme;
    }
}
