package edu.psu.jrp5717.superyahtzee;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import androidx.annotation.NonNull;
@Entity(tableName = "winner_table")
public class Winner {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "winner")
    private String mWinner;
    private int mScore;
    public Winner(@NonNull String winner, int score)
    {
        this.mWinner = winner;
        this.mScore = score;
    }
    public String getWinner() { return this.mWinner; }
    public int getScore() { return this.mScore; }
}
