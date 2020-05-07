package edu.psu.jrp5717.superyahtzee;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Winner.class}, version = 1, exportSchema = false)
public abstract class WinnerRoomDatabase extends RoomDatabase {
    public abstract WinnerDao winnerDao();

    private static volatile WinnerRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static WinnerRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WinnerRoomDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WinnerRoomDatabase.class, "winner_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
