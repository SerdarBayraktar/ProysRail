package net.proys.proysrail;

import android.content.Context;

import androidx.room.Room;

public class RoomHelper {
    private Context mContext;
    public RoomHelper(Context context) {
        this.mContext = context;

    }

    public void ReadVerimsizlik(){//todo
       RoomDatabase database = Room.databaseBuilder(mContext,RoomDatabase.class,"ProysDB").allowMainThreadQueries().build();



    }


}
