package edu.psu.jrp5717.superyahtzee;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;

class WinnerRepository {
    private WinnerDao mWinnerDao;
    private LiveData<List<Winner>> mAllWinners;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    WinnerRepository(Application application) {
        WinnerRoomDatabase db = WinnerRoomDatabase.getDatabase(application);
        mWinnerDao = db.winnerDao();
        mAllWinners = mWinnerDao.getHighScores();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Winner>> getAllWinners() {
        return mAllWinners;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Winner winner) {
        WinnerRoomDatabase.databaseWriteExecutor.execute(() -> {
            mWinnerDao.insert(winner);
        });
    }
}
