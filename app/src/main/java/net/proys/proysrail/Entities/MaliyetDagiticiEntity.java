package net.proys.proysrail.Entities;

import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity//todo foreign keys
public class MaliyetDagiticiEntity {

    @PrimaryKey(autoGenerate = true)
    public Integer id;

    public Integer dagilan;

    public Integer toplayan;

    public Double oran;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDagilan() {
        return dagilan;
    }

    public void setDagilan(Integer dagilan) {
        this.dagilan = dagilan;
    }

    public Integer getToplayan() {
        return toplayan;
    }

    public void setToplayan(Integer toplayan) {
        this.toplayan = toplayan;
    }

    public Double getOran() {
        return oran;
    }

    public void setOran(Double oran) {
        this.oran = oran;
    }
}
