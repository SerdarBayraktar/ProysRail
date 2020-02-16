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
                entity = ImalatGerceklesmeEntity.class,
                parentColumns = "gerceklesme_id",
                childColumns = "gerceklesme"
        )
})*/
public class MakinePuantajEntity {

    @PrimaryKey(autoGenerate = true)
    public Integer makinepuantaj_id;

    public String bildiri;

    public String makine;

    public String tarih;

    public String imalat;

    public Float puantaj;

    public String gerceklesme;

    public Integer getMakinepuantaj_id() {
        return makinepuantaj_id;
    }

    public void setMakinepuantaj_id(Integer makinepuantaj_id) {
        this.makinepuantaj_id = makinepuantaj_id;
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

    public Float getPuantaj() {
        return puantaj;
    }

    public void setPuantaj(Float puantaj) {
        this.puantaj = puantaj;
    }

    public String getGerceklesme() {
        return gerceklesme;
    }

    public void setGerceklesme(String gerceklesme) {
        this.gerceklesme = gerceklesme;
    }
}
