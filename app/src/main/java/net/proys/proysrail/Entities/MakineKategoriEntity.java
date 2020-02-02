package net.proys.proysrail.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MakineKategoriEntity {

    @PrimaryKey
    public int kategori_id;

    public String kategori_isim;

    public boolean rekabet;
}
