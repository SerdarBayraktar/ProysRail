package net.proys.proysrail;

import android.content.Context;

import net.proys.proysrail.Daos.AciklamalarDao;
import net.proys.proysrail.Daos.BildiriTipListeDao;
import net.proys.proysrail.Daos.BildirilerDao;
import net.proys.proysrail.Daos.CalisanListeDao;
import net.proys.proysrail.Daos.CalisanPuantajDao;
import net.proys.proysrail.Daos.CalisanVerimsizlikDao;
import net.proys.proysrail.Daos.EtkenGerceklesmeDao;
import net.proys.proysrail.Daos.EtkenListeDao;
import net.proys.proysrail.Daos.GetSetDao;
import net.proys.proysrail.Daos.ImalatGerceklesmeDao;
import net.proys.proysrail.Daos.ImalatListeDao;
import net.proys.proysrail.Daos.ImalatMakineEslesmeDao;
import net.proys.proysrail.Daos.ImalatSektorEslesmeDao;
import net.proys.proysrail.Daos.KullaniciBildiriEslesmeDao;
import net.proys.proysrail.Daos.KullaniciImalatEslesmeDao;
import net.proys.proysrail.Daos.KullanicilarDao;
import net.proys.proysrail.Daos.MakineKategoriDao;
import net.proys.proysrail.Daos.MakineListeDao;
import net.proys.proysrail.Daos.MakinePuantajDao;
import net.proys.proysrail.Daos.MakineVerimsizlikDao;
import net.proys.proysrail.Daos.MaliyetDagiticiDao;
import net.proys.proysrail.Daos.SektorListeDao;
import net.proys.proysrail.Entities.AciklamalarEntity;
import net.proys.proysrail.Entities.BildiriTipListeEntity;
import net.proys.proysrail.Entities.BildirilerEntity;
import net.proys.proysrail.Entities.CalisanListeEntity;
import net.proys.proysrail.Entities.CalisanPuantajEntity;
import net.proys.proysrail.Entities.CalisanVerimsizlikEntity;
import net.proys.proysrail.Entities.EtkenGerceklesmeEntity;
import net.proys.proysrail.Entities.EtkenListeEntity;
import net.proys.proysrail.Entities.GetSetEntity;
import net.proys.proysrail.Entities.ImalatGerceklesmeEntity;
import net.proys.proysrail.Entities.ImalatListeEntity;
import net.proys.proysrail.Entities.ImalatMakineEslesmeEntity;
import net.proys.proysrail.Entities.ImalatSektorEslesmeEntity;
import net.proys.proysrail.Entities.KullaniciBildiriEslesmeEntity;
import net.proys.proysrail.Entities.KullaniciImalatEslesmeEntity;
import net.proys.proysrail.Entities.KullanicilarEntity;
import net.proys.proysrail.Entities.MakineKategoriEntity;
import net.proys.proysrail.Entities.MakineListeEntity;
import net.proys.proysrail.Entities.MakinePuantajEntity;
import net.proys.proysrail.Entities.MakineVerimsizlikEntity;
import net.proys.proysrail.Entities.MaliyetDagiticiEntity;
import net.proys.proysrail.Entities.SektorListeEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {
        AciklamalarEntity.class,
        BildirilerEntity.class,
        BildiriTipListeEntity.class,
        CalisanListeEntity.class,
        CalisanPuantajEntity.class,
        CalisanVerimsizlikEntity.class,
        EtkenGerceklesmeEntity.class,
        EtkenListeEntity.class,
        ImalatGerceklesmeEntity.class,
        ImalatListeEntity.class,
        ImalatMakineEslesmeEntity.class,
        ImalatSektorEslesmeEntity.class,
        KullaniciImalatEslesmeEntity.class,
        KullaniciBildiriEslesmeEntity.class,
        KullanicilarEntity.class,
        MakineKategoriEntity.class,
        MakineListeEntity.class,
        MakinePuantajEntity.class,
        MakineVerimsizlikEntity.class,
        MaliyetDagiticiEntity.class,
        SektorListeEntity.class,
        GetSetEntity.class
},version = 1)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {


    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile RoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static RoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDatabase.class, "ProysDB")
                            .addCallback(sRoomDatabaseCallback).allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     *
     * If you want to populate the database only when the database is created for the 1st time,
     * override RoomDatabase.Callback()#onCreate
     */
    private static androidx.room.RoomDatabase.Callback sRoomDatabaseCallback = new androidx.room.RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            /*databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                WordDao dao = INSTANCE.wordDao();
                dao.deleteAll();

                Word word = new Word("Hello");
                dao.insert(word);
                word = new Word("World");
                dao.insert(word);
            });*/
        }
    };


    public abstract AciklamalarDao aciklamalarDao();

    public abstract BildirilerDao bildirilerDao();

    public abstract BildiriTipListeDao bildiriTipListeDao();

    public abstract CalisanListeDao calisanListeDao();

    public abstract CalisanPuantajDao calisanPuantajDao();

    public abstract CalisanVerimsizlikDao calisanVerimsizlikDao();

    public abstract EtkenGerceklesmeDao etkenGerceklesmeDao();

    public abstract EtkenListeDao etkenListeDao();

    public abstract ImalatGerceklesmeDao imalatGerceklesmeDao();

    public abstract ImalatListeDao imalatListeDao();

    public abstract ImalatMakineEslesmeDao imalatMakineEslesmeDao();

    public abstract ImalatSektorEslesmeDao imalatSektorEslesmeDao();

    public abstract KullaniciImalatEslesmeDao kullaniciImalatEslesmeDao();

    public abstract KullanicilarDao kullanicilarDao();

    public abstract MakineKategoriDao makineKategoriDao();

    public abstract MakineListeDao makineListeDao();

    public abstract MakinePuantajDao makinePuantajDao();

    public abstract MakineVerimsizlikDao makineVerimsizlikDao();

    public abstract MaliyetDagiticiDao maliyetDagiticiDao();

    public abstract SektorListeDao sektorListeDao();

    public abstract KullaniciBildiriEslesmeDao kullaniciBildiriEslesmeDao();

    public abstract GetSetDao getSetDao();

}
