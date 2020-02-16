package net.proys.proysrail.Entities;


import java.sql.Date;

import androidx.annotation.NonNull;
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
                entity = ImalatGerceklesmeEntity.class,
                parentColumns = "gerceklesme_id",
                childColumns = "gerceklesme"
        )
})*/
public class AciklamalarEntity {

    @PrimaryKey(autoGenerate = true)
    public  Integer aciklama_id;

    public String tarih;

    public String aciklama;//todo text

    public String imalat;
    @NonNull
    public String bildiri;

    public String gerceklesme;

    public Integer getAciklama_id() {
        return aciklama_id;
    }

    public String getImalat() {
        return imalat;
    }

    public void setImalat(String imalat) {
        this.imalat = imalat;
    }

    public void setAciklama_id(Integer aciklama_id) {
        this.aciklama_id = aciklama_id;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    @NonNull
    public String getBildiri() {
        return bildiri;
    }

    public void setBildiri(@NonNull String bildiri) {
        this.bildiri = bildiri;
    }

    public String getGerceklesme() {
        return gerceklesme;
    }

    public void setGerceklesme(String gerceklesme) {
        this.gerceklesme = gerceklesme;
    }
}
