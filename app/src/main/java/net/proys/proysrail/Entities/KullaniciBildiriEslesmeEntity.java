package net.proys.proysrail.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity/*(foreignKeys = {
        @ForeignKey(
                entity = KullanicilarEntity.class,
                parentColumns = "kullanici_id",
                childColumns = "kullanici"
        ),
        @ForeignKey(
                entity = BildiriTipListeEntity.class,
                parentColumns = "bildiritip_id",
                childColumns = "bildiri_tipi"
        )
})*/
public class KullaniciBildiriEslesmeEntity {

    @PrimaryKey(autoGenerate = true)
    public Integer id;

    public Integer kullanici;

    public Integer bildiri_tipi;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKullanici() {
        return kullanici;
    }

    public void setKullanici(Integer kullanici) {
        this.kullanici = kullanici;
    }

    public Integer getBildiri_tipi() {
        return bildiri_tipi;
    }

    public void setBildiri_tipi(Integer bildiri_tipi) {
        this.bildiri_tipi = bildiri_tipi;
    }
}
