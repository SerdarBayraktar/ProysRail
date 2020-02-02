package net.proys.proysrail.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ImalatListeEntity {

    @PrimaryKey(autoGenerate = true)
    public int imalat_id;//todo 10+

    public String isim;

    public String uzun_isim;

    public Float bpl_adsa;

    public Float bpl_adsa_net;

    public Float bpl_adsa_butce;

    public String birim;

    public String takip_tipi;

    public boolean takip;

    public boolean kontrol;

    public boolean puantaj;

    public boolean maliyet;

    public int minpi_deger;
}
