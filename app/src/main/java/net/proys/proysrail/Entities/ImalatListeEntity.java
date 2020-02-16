package net.proys.proysrail.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ImalatListeEntity {

    @PrimaryKey
    public Integer imalat_id;

    public String isim;

    public String uzun_isim;

    public Double bpl_adsa;

    public Double bpl_adsa_net;

    public Double bpl_adsa_butce;

    public String birim;

    public String takip_tipi;

    public Boolean takip;

    public Boolean kontrol;

    public Boolean puantaj;

    public Boolean maliyet;

    public Integer minpi_deger;

    public Integer getImalat_id() {
        return imalat_id;
    }

    public void setImalat_id(Integer imalat_id) {
        this.imalat_id = imalat_id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getUzun_isim() {
        return uzun_isim;
    }

    public void setUzun_isim(String uzun_isim) {
        this.uzun_isim = uzun_isim;
    }

    public Double getBpl_adsa() {
        return bpl_adsa;
    }

    public void setBpl_adsa(Double bpl_adsa) {
        this.bpl_adsa = bpl_adsa;
    }

    public Double getBpl_adsa_net() {
        return bpl_adsa_net;
    }

    public void setBpl_adsa_net(Double bpl_adsa_net) {
        this.bpl_adsa_net = bpl_adsa_net;
    }

    public Double getBpl_adsa_butce() {
        return bpl_adsa_butce;
    }

    public void setBpl_adsa_butce(Double bpl_adsa_butce) {
        this.bpl_adsa_butce = bpl_adsa_butce;
    }

    public String getBirim() {
        return birim;
    }

    public void setBirim(String birim) {
        this.birim = birim;
    }

    public String getTakip_tipi() {
        return takip_tipi;
    }

    public void setTakip_tipi(String takip_tipi) {
        this.takip_tipi = takip_tipi;
    }

    public Boolean isTakip() {
        return takip;
    }

    public void setTakip(Boolean takip) {
        this.takip = takip;
    }

    public Boolean isKontrol() {
        return kontrol;
    }

    public void setKontrol(Boolean kontrol) {
        this.kontrol = kontrol;
    }

    public Boolean isPuantaj() {
        return puantaj;
    }

    public void setPuantaj(Boolean puantaj) {
        this.puantaj = puantaj;
    }

    public Boolean isMaliyet() {
        return maliyet;
    }

    public void setMaliyet(Boolean maliyet) {
        this.maliyet = maliyet;
    }

    public Integer getMinpi_deger() {
        return minpi_deger;
    }

    public void setMinpi_deger(Integer minpi_deger) {
        this.minpi_deger = minpi_deger;
    }
}
