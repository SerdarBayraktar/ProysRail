package net.proys.proysrail.Entities;

import java.sql.Date;
import java.sql.Time;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = BildiriTipListeEntity.class,
                parentColumns = "bildiritip_id",
                childColumns = "bildiri_tipi"
        ),
        @ForeignKey(
                entity = KullanicilarEntity.class,
                parentColumns = "kullanici_id",
                childColumns = "kullanici"
        )
})
public class BildirilerEntity {

    @PrimaryKey(autoGenerate = true)
    public Long bildiri_id;

    public String bildiri_tipi;

    public String bildiri_tarih;

    public String kullanici;

    public int kabul;

    public String son_giris;

    public String kabul_zamani;

}
