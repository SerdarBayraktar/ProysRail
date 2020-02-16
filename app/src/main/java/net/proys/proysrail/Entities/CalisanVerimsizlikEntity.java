package net.proys.proysrail.Entities;

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
                entity = CalisanListeEntity.class,
                parentColumns = "calisan_id",
                childColumns = "calisan"
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
public class CalisanVerimsizlikEntity {
    @PrimaryKey(autoGenerate = true)
    public Integer calisanverimsizlik_id;

    public String bildiri;

    public String calisan;

    public String tarih;

    public String imalat;

    public String etken;

    public Float deger;

    public Integer getCalisanverimsizlik_id() {
        return calisanverimsizlik_id;
    }

    public void setCalisanverimsizlik_id(Integer calisanverimsizlik_id) {
        this.calisanverimsizlik_id = calisanverimsizlik_id;
    }

    public String getBildiri() {
        return bildiri;
    }

    public void setBildiri(String bildiri) {
        this.bildiri = bildiri;
    }

    public String getCalisan() {
        return calisan;
    }

    public void setCalisan(String calisan) {
        this.calisan = calisan;
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

    public String getEtken() {
        return etken;
    }

    public void setEtken(String etken) {
        this.etken = etken;
    }

    public Float getDeger() {
        return deger;
    }

    public void setDeger(Float deger) {
        this.deger = deger;
    }
}
