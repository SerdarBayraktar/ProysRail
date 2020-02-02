package net.proys.proysrail.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity

public class CalisanListeEntity {

    @PrimaryKey (autoGenerate = true)
    public int calisan_id;//todo 1100+

    public String isim;

    public String soyisim;

    public String isim_tam;

    public boolean maviyaka;

    public boolean direkt;

    public String unvan;

    public String pozisyon;

    public String departman;

    public Float vt_puantaj;

    public String taseron;

    public String sorumlu;

    public String v_imalat;

}

