package net.proys.proysrail.Entities;


import java.sql.Time;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BildiriTipListeEntity {

    @PrimaryKey(autoGenerate = true)//todo 200 - 299
    public int bildiritip_id;

    public String isim;

    public boolean sistem;

    public boolean mobil_sistem;

    public int frekans;

    public boolean gunluk_rapor;

    public Time yayin_saat;

    public int giris_sure;

    public boolean bagimli;






}
