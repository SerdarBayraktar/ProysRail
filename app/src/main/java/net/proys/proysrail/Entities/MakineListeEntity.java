package net.proys.proysrail.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = MakineKategoriEntity.class,
                parentColumns = "kategori_id",
                childColumns = "kategori"
        )
})
public class MakineListeEntity {
    public Integer getMakine_id() {
        return makine_id;
    }

    public void setMakine_id(Integer makine_id) {
        this.makine_id = makine_id;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getMakine_ismi() {
        return makine_ismi;
    }

    public void setMakine_ismi(String makine_ismi) {
        this.makine_ismi = makine_ismi;
    }

    public String getKisa_isim() {
        return kisa_isim;
    }

    public void setKisa_isim(String kisa_isim) {
        this.kisa_isim = kisa_isim;
    }

    public String getPlaka() {
        return plaka;
    }

    public void setPlaka(String plaka) {
        this.plaka = plaka;
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

    public Integer getGrup_no() {
        return grup_no;
    }

    public void setGrup_no(Integer grup_no) {
        this.grup_no = grup_no;
    }

    public String getTemin() {
        return temin;
    }

    public void setTemin(String temin) {
        this.temin = temin;
    }

    @PrimaryKey(autoGenerate = true)//todo 9000+
    public Integer makine_id;

    public String kategori;

    public String tip;

    public String makine_ismi;

    public String kisa_isim;

    public String plaka;

    public Double vt_puantaj;

    public String taseron;

    public Integer grup_no;

    public String temin;

}
