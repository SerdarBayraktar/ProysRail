package net.proys.proysrail.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class KullanicilarEntity {

    @PrimaryKey(autoGenerate = true)//todo 100 - 200
    public int kullanici_id;

    public String isim;

    public String soyisim;

    public String isim_tam;

    public String kullanici_adi;

    public String email;//todo class is not email

    public String password;

    public boolean aktif;



}
