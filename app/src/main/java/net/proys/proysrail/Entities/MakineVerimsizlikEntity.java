package net.proys.proysrail.Entities;

import java.sql.Date;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity/*(foreignKeys = {
        @ForeignKey(
                entity = BildirilerEntity.class,
                parentColumns = "bildiri_id",
                childColumns = "bildiri"
        ),
        @ForeignKey(
                entity = MakineListeEntity.class,
                parentColumns = "makine_id",
                childColumns = "makine"
        ),
        @ForeignKey(
                entity = ImalatListeEntity.class,
                parentColumns = "imalat_id",
                childColumns = "imalat"
        ),
        @ForeignKey(
                entity = EtkenGerceklesmeEntity.class,
                parentColumns = "etken_gercek_id",
                childColumns = "etken"
        )
})*/
public class MakineVerimsizlikEntity {

    @PrimaryKey(autoGenerate = true)
    public Integer makineverimsizlik_id;

    public String bildiri;

    public String makine;

    public String tarih;

    public String imalat;

    public Float deger;

    public String etken;

    public Integer getMakineverimsizlik_id() {
        return makineverimsizlik_id;
    }

    public void setMakineverimsizlik_id(Integer makineverimsizlik_id) {
        this.makineverimsizlik_id = makineverimsizlik_id;
    }

    public String getBildiri() {
        return bildiri;
    }

    public void setBildiri(String bildiri) {
        this.bildiri = bildiri;
    }

    public String getMakine() {
        return makine;
    }

    public void setMakine(String makine) {
        this.makine = makine;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getImalat() {
        return imalat;
    }

    public void setImalat(String imalat) {
        this.imalat = imalat;
    }

    public Float getDeger() {
        return deger;
    }

    public void setDeger(Float deger) {
        this.deger = deger;
    }

    public String getEtken() {
        return etken;
    }

    public void setEtken(String etken) {
        this.etken = etken;
    }
}
