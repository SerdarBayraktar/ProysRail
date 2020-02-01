package net.proys.proysrail.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class EtkenListe {

    @PrimaryKey (autoGenerate = true)
    public int etken_id;

    public String isim;

    public Float vt_deger;

}
