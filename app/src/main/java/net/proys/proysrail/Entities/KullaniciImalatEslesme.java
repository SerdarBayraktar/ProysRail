package net.proys.proysrail.Entities;

import java.sql.Date;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Kullanicilar.class,
                parentColumns = "kullanici_id",
                childColumns = "kullanici"
        ),
        @ForeignKey(
                entity = ImalatListe.class,
                parentColumns = "imalat_id",
                childColumns = "imalat"
        )
})
public class KullaniciImalatEslesme {


    public String kullanici;

    public String imalat;


}
