package net.proys.proysrail.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = MakineKategori.class,
                parentColumns = "kategori_id",
                childColumns = "kategori"
        )
})
public class MakineListe {

    @PrimaryKey(autoGenerate = true)//todo 9000+
    public int makine_id;

    public String kategori;

    public String tip;

    public String makine_ismi;

    public String kisa_isim;

    public String plaka;

    public Float vt_puantaj;

    public String taseron;

    public String grup_no;

    public String temin;

}
