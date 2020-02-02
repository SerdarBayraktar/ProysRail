package net.proys.proysrail.Entities;

import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity//todo foreign keys
public class MaliyetDagiticiEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int dagilan;

    public int toplayan;

    public Double oran;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDagilan() {
        return dagilan;
    }

    public void setDagilan(int dagilan) {
        this.dagilan = dagilan;
    }

    public int getToplayan() {
        return toplayan;
    }

    public void setToplayan(int toplayan) {
        this.toplayan = toplayan;
    }

    public Double getOran() {
        return oran;
    }

    public void setOran(Double oran) {
        this.oran = oran;
    }
}
