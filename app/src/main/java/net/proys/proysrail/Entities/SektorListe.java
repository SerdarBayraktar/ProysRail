package net.proys.proysrail.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SektorListe {

    @PrimaryKey(autoGenerate = true)
    public int sektor_id;

    public String isim;

    public int hat_no;

    public int km_bas;

    @NonNull
    public int km_bit;
}
