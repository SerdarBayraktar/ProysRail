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
    public int etken_gercek_id;

    @ColumnInfo(name = "etken")
    public int etken;

    @ColumnInfo(name = "deger")
    public Double deger;

    @ColumnInfo(name = "gerceklesme")
    public int gerceklesme;

    public int getEtken_gercek_id() {
        return etken_gercek_id;
    }

    public void setEtken_gercek_id(int etken_gercek_id) {
        this.etken_gercek_id = etken_gercek_id;
    }

    public int getEtken() {
        return etken;
    }

    public void setEtken(int etken) {
        this.etken = etken;
    }

    public Double getDeger() {
        return deger;
    }

    public void setDeger(Double deger) {
        this.deger = deger;
    }

    public int getGerceklesme() {
        return gerceklesme;
    }

    public void setGerceklesme(int gerceklesme) {
        this.gerceklesme = gerceklesme;
    }
}
