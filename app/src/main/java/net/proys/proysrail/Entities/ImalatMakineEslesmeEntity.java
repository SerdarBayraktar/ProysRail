package net.proys.proysrail.Entities;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = ImalatListeEntity.class,
                parentColumns = "imalat_id",
                childColumns = "imalat"
        ),
        @ForeignKey(
                entity = MakineListeEntity.class,
                parentColumns = "makine_id",
                childColumns = "makine"
        )
})
public class ImalatMakineEslesmeEntity {

    @PrimaryKey(autoGenerate =true)
    public int id;

    public String imalat;

    public String makine;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImalat() {
        return imalat;
    }

    public void setImalat(String imalat) {
        this.imalat = imalat;
    }

    public String getMakine() {
        return makine;
    }

    public void setMakine(String makine) {
        this.makine = makine;
    }
}
