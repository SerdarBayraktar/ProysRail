package net.proys.proysrail.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class KullanicilarEntity {

    @PrimaryKey(autoGenerate = true)
    public Integer kullanici_id;

    public String isim;

    public String soyisim;

    public String isim_tam;

    public String kullanici_adi;

    public String email;

    public String password;

    public Boolean aktif;


    public Integer getKullanici_id() {
        return kullanici_id;
    }

    public void setKullanici_id(Integer kullanici_id) {
        this.kullanici_id = kullanici_id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getSoyisim() {
        return soyisim;
    }

    public void setSoyisim(String soyisim) {
        this.soyisim = soyisim;
    }

    public String getIsim_tam() {
        return isim_tam;
    }

    public void setIsim_tam(String isim_tam) {
        this.isim_tam = isim_tam;
    }

    public String getKullanici_adi() {
        return kullanici_adi;
    }

    public void setKullanici_adi(String kullanici_adi) {
        this.kullanici_adi = kullanici_adi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAktif() {
        return aktif;
    }

    public void setAktif(boolean aktif) {
        this.aktif = aktif;
    }
}
