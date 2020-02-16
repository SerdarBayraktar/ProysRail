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
                entity = ImalatGerceklesmeEntity.class,
                parentColumns = "gerceklesme_id",
                childColumns = "gerceklesme"
        )
})*/
public class CalisanPuantajEntity {

    @PrimaryKey(autoGenerate = true)
    public Integer calisanpuantaj_id;

    public String bildiri;

    public String calisan;

    public String tarih;

    public String imalat;

    public Float puantaj;

    public Float fazla_mesai;

    public String puantaj_tipi;

    public String gerceklesme;





    public Integer getCalisanpuantaj_id() {
        return calisanpuantaj_id;
    }

    public void setCalisanpuantaj_id(Integer calisanpuantaj_id) {
        this.calisanpuantaj_id = calisanpuantaj_id;
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

    public Float getPuantaj() {
        return puantaj;
    }

    public void setPuantaj(Float puantaj) {
        this.puantaj = puantaj;
    }

    public Float getFazla_mesai() {
        return fazla_mesai;
    }

    public void setFazla_mesai(Float fazla_mesai) {
        this.fazla_mesai = fazla_mesai;
    }

    public String getPuantaj_tipi() {
        return puantaj_tipi;
    }

    public void setPuantaj_tipi(String puantaj_tipi) {
        this.puantaj_tipi = puantaj_tipi;
    }

    public String getGerceklesme() {
        return gerceklesme;
    }

    public void setGerceklesme(String gerceklesme) {
        this.gerceklesme = gerceklesme;
    }
}