package edu.psu.jrp5717.superyahtzee;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import edu.psu.jrp5717.superyahtzee.WinnerRepository;

public class WinnerViewModel extends AndroidViewModel {
    private WinnerRepository mRepository;
    private LiveData<List<Winner>> mAllWinners;
    public WinnerViewModel(Application application) {
        super(application);
        mRepository = new WinnerRepository(application);
        mAllWinners = mRepository.getAllWinners();
    }
    LiveData<List<Winner>> getAllWinners() { return mAllWinners; }
    public void insert(Winner winner) { mRepository.insert(winner); }
}
