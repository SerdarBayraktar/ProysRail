package net.proys.proysrail.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MakineKategoriEntity {

    @PrimaryKey
    public int kategori_id;

    public String kategori_isim;

    public boolean rekabet;

    public int getKategori_id() {
        return kategori_id;
    }

    public void setKategori_id(int kategori_id) {
        this.kategori_id = kategori_id;
    }

    public String getKategori_isim() {
        return kategori_isim;
    }

    public void setKategori_isim(String kategori_isim) {
        this.kategori_isim = kategori_isim;
    }

    public boolean isRekabet() {
        return rekabet;
    }

    public void setRekabet(boolean rekabet) {
        this.rekabet = rekabet;
    }

}
