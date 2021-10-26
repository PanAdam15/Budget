package com.example.budzet;

import android.content.Context;


import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ExpensesEntity.class}, version = 1, exportSchema = false)
public abstract class ExpensesRoomDatabase extends RoomDatabase {
    public abstract ExpensesDao expensesDao();

    private static volatile ExpensesRoomDatabase INSTANCE;

    static ExpensesRoomDatabase getDatabase(final Context context){
        if (INSTANCE==null){
            synchronized (ExpensesRoomDatabase.class){
                if (INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ExpensesRoomDatabase.class, "ExpensesDb")
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static  RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() ->{
                ExpensesDao dao = INSTANCE.expensesDao();

                dao.insert(new ExpensesEntity(123,"11-4-2021","WAZNE"));
                dao.insert(new ExpensesEntity(555.47,"21-7-2021","NIE WAZNE"));
                dao.insert(new ExpensesEntity(321.0,"31-1-2021","WAZNE"));

            });
        }

        //    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
//
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db){
//            super.onCreate(db);
//
//            databaseWriteExecutor.execute(() ->{
//                ElementDao dao = INSTANCE.elementDao();
//
//                dao.insert(PhoneEntity("Gugl"));
//                dao.insert(new PhoneEntity("SIAJOMI","miA2"));
//                dao.insert(new PhoneEntity("apl","Iphon0"));
//                dao.insert(new PhoneEntity("Szajsung","GalaxyBoomMax"));
//                dao.insert(new PhoneEntity("Azus","aniewiem"));
//                dao.insert(new PhoneEntity("huiawej","cingyang"));
//            });
//        }
    };
}