package net.proys.proysrail.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
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
})
public class KullaniciBildiriEslesmeEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int kullanici;

    public int bildiri_tipi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKullanici() {
        return kullanici;
    }

    public void setKullanici(int kullanici) {
        this.kullanici = kullanici;
    }

    public int getBildiri_tipi() {
        return bildiri_tipi;
    }

    public void setBildiri_tipi(int bildiri_tipi) {
        this.bildiri_tipi = bildiri_tipi;
    }
}
