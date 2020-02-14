package net.proys.proysrail.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity

public class CalisanListeEntity {

    @PrimaryKey (autoGenerate = true)
    public Integer calisan_id;

    public String isim;

    public String soyisim;

    public String isim_tam;

    public boolean maviyaka;

    public boolean direkt;

    public String unvan;

    public String pozisyon;

    public String departman;

    public Double vt_puantaj;

    public String taseron;

    public String sorumlu;

    public String v_imalat;

    public Integer getCalisan_id() {
        return calisan_id;
    }

    public void setCalisan_id(Integer calisan_id) {
        this.calisan_id = calisan_id;
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

    public boolean isMaviyaka() {
        return maviyaka;
    }

    public void setMaviyaka(boolean maviyaka) {
        this.maviyaka = maviyaka;
    }

    public boolean isDirekt() {
        return direkt;
    }

    public void setDirekt(boolean direkt) {
        this.direkt = direkt;
    }

    public String getUnvan() {
        return unvan;
    }

    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }

    public String getPozisyon() {
        return pozisyon;
    }

    public void setPozisyon(String pozisyon) {
        this.pozisyon = pozisyon;
    }

    public String getDepartman() {
        return departman;
    }

    public void setDepartman(String departman) {
        this.departman = departman;
    }

    public Double getVt_puantaj() {
        return vt_puantaj;
    }

    public void setVt_puantaj(Double vt_puantaj) {
        this.vt_puantaj = vt_puantaj;
    }

    public String getTaseron() {
        return taseron;
    }

    public void setTaseron(String taseron) {
        this.taseron = taseron;
    }

    public String getSorumlu() {
        return sorumlu;
    }

    public void setSorumlu(String sorumlu) {
        this.sorumlu = sorumlu;
    }

    public String getV_imalat() {
        return v_imalat;
    }

    public void setV_imalat(String v_imalat) {
        this.v_imalat = v_imalat;
    }
}

