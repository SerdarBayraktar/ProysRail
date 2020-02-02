package net.proys.proysrail.Entities;

import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity//todo foreign keys
public class MaliyetDagiticiEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String dagilan;

    public String toplayan;

    public Float oran;
}
