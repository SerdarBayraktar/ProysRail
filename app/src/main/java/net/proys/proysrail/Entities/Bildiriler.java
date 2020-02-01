package net.proys.proysrail.Entities;

import java.sql.Date;
import java.sql.Time;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = BildiriTipListe.class,
                parentColumns = "bildiritip_id",
                childColumns = "bildiri"
        ),
        @ForeignKey(
                entity = Kullanicilar.class,
                parentColumns = "kullanici_id",
                childColumns = "kullanici"
        )
})
public class Bildiriler {

    @PrimaryKey(autoGenerate = true)
    public String bildiri_id;

    public String bildiri_tipi;

    public Date bildiri_tarih;

    public String kullanici;

    public int kabul;

    public Time son_giris;//todo Time =?????????????

    public Date kabul_zamani;

}
