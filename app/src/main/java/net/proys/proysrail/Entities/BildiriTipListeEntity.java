package net.proys.proysrail.Entities;


import java.sql.Time;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BildiriTipListeEntity {

    @PrimaryKey(autoGenerate = true)//todo 200 - 299
    public Integer bildiritip_id;

    public String isim;

    public boolean sistem;

    public boolean mobil_sistem;

    public Integer frekans;

    public boolean gunluk_rapor;

    public String yayin_saat;

    public Integer giris_sure;

    public Integer bagimli;


    public Integer getBildiritip_id() {
        return bildiritip_id;
    }

    public void setBildiritip_id(Integer bildiritip_id) {
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

    public Integer getFrekans() {
        return frekans;
    }

    public void setFrekans(Integer frekans) {
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

    public Integer getGiris_sure() {
        return giris_sure;
    }

    public void setGiris_sure(Integer giris_sure) {
        this.giris_sure = giris_sure;
    }

    public Integer getBagimli() {
        return bagimli;
    }

    public void setBagimli(Integer bagimli) {
        this.bagimli = bagimli;
    }
}
