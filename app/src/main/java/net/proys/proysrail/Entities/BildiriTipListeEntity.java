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

    public String yayin_saat;

    public int giris_sure;

    public int bagimli;


    public int getBildiritip_id() {
        return bildiritip_id;
    }

    public void setBildiritip_id(int bildiritip_id) {
        this.bildiritip_id = bildiritip_id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public boolean isSistem() {
        return sistem;
    }

    public void setSistem(boolean sistem) {
        this.sistem = sistem;
    }

    public boolean isMobil_sistem() {
        return mobil_sistem;
    }

    public void setMobil_sistem(boolean mobil_sistem) {
        this.mobil_sistem = mobil_sistem;
    }

    public int getFrekans() {
        return frekans;
    }

    public void setFrekans(int frekans) {
        this.frekans = frekans;
    }

    public boolean isGunluk_rapor() {
        return gunluk_rapor;
    }

    public void setGunluk_rapor(boolean gunluk_rapor) {
        this.gunluk_rapor = gunluk_rapor;
    }

    public String getYayin_saat() {
        return yayin_saat;
    }

    public void setYayin_saat(String yayin_saat) {
        this.yayin_saat = yayin_saat;
    }

    public int getGiris_sure() {
        return giris_sure;
    }

    public void setGiris_sure(int giris_sure) {
        this.giris_sure = giris_sure;
    }

    public int getBagimli() {
        return bagimli;
    }

    public void setBagimli(int bagimli) {
        this.bagimli = bagimli;
    }
}
