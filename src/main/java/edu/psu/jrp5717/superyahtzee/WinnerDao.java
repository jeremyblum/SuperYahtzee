package edu.psu.jrp5717.superyahtzee;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface WinnerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Winner winner);

    @Query("DELETE FROM winner_table")
    void deleteAll();

    @Query("SELECT * FROM winner_table ORDER BY mScore DESC")
    LiveData<List<Winner>> getHighScores();
}
