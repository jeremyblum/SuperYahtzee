package edu.psu.jrp5717.superyahtzee;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import java.util.Random;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
    int numRolls = 3;
    Random rand = new Random();
    int [] die = {0,0,0,0,0};
    int [] nums;
    Button tempButton;
    int [][] scores ={
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}
    };
    Button[][] buttonViews;
    int tempScore = -1;
    int numberOfAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("LifeCycle", "onCreateInvoked");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dice_screen);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d("LifeCycle", "onRestoreInstanceState");
        die[0] = savedInstanceState.getInt("DIE1");
        die[1] = savedInstanceState.getInt("DIE2");
        die[2] = savedInstanceState.getInt("DIE3");
        die[3] = savedInstanceState.getInt("DIE4");
        die[4] = savedInstanceState.getInt("DIE5");
        scores[0] = savedInstanceState.getIntArray("SCORESX1");
        scores[1] = savedInstanceState.getIntArray("SCORESX2");
        scores[2] = savedInstanceState.getIntArray("SCORESX3");
        setDiceNumbers();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("DIE1", die[0]);
        outState.putInt("DIE2", die[1]);
        outState.putInt("DIE3", die[2]);
        outState.putInt("DIE4", die[3]);
        outState.putInt("DIE5", die[4]);
        outState.putIntArray("SCORESX1", scores[0]);
        outState.putIntArray("SCORESX2", scores[1]);
        outState.putIntArray("SCORESX3", scores[2]);

        Log.d("LifeCycle", "onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }
    @Override
    public void onPause(){
        Log.d("LifeCycle", "onPause");
        super.onPause();
    }
    @Override
    public void onStop() {
        Log.d("LifeCycle", "onStop");
        super.onStop();
    }

    public void scoreClick(View view) {
        nums = new int[]{0,0,0,0,0,0};
        for(int i = 0; i < 5; i++)
            nums[die[i]-1]++;
        setContentView(R.layout.game_screen);
        buttonViews = setButtonsForButtonArray();
        setScoreNumbers();
    }

    public void backClick(View view) {
        setContentView(R.layout.dice_screen);
        ((CheckBox) findViewById(R.id.checkBox1)).setChecked(false);
        ((CheckBox) findViewById(R.id.checkBox2)).setChecked(false);
        ((CheckBox) findViewById(R.id.checkBox3)).setChecked(false);
        ((CheckBox) findViewById(R.id.checkBox4)).setChecked(false);
        ((CheckBox) findViewById(R.id.checkBox5)).setChecked(false);
        setDiceNumbers();
        ((Button) findViewById(R.id.scorebutton)).setEnabled(true);
        Button roll = findViewById(R.id.rollbutton);
        roll.setText(getString(R.string.reroll,numRolls));
        if(numRolls < 1)
            roll.setEnabled(false);

    }
    public void submitClick(View view) {
        if(numberOfAnswers >= 13)
            finish();
        else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 13; j++) {
                    if (buttonViews[i][j].getId() == tempButton.getId()) {
                        scores[i][j] = tempScore;
                        break;
                    }
                }
            }

            tempButton = null;
            numRolls = 3;
            die = new int[]{0, 0, 0, 0, 0};
            if (++numberOfAnswers < 13)
                setContentView(R.layout.dice_screen);
            else
                calculateScores();
        }
    }

    public void rollClick(View view) {
        CheckBox[] x = new CheckBox[5];
        x[0] = findViewById(R.id.checkBox1);
        x[1] = findViewById(R.id.checkBox2);
        x[2] = findViewById(R.id.checkBox3);
        x[3] = findViewById(R.id.checkBox4);
        x[4] = findViewById(R.id.checkBox5);

        if(!x[0].isChecked() && !x[1].isChecked() && !x[2].isChecked() && !x[3].isChecked() && !x[4].isChecked())
            return;
        if(numRolls == 3) {
            for (int i = 0; i < 5; i++)
                die[i] = (rand.nextInt(6) + 1);
        }else if(numRolls > 0){
            for(int i = 0; i < 5; i++)
                if (x[i].isChecked())
                    die[i] = (rand.nextInt(6) + 1);
        }
        numRolls--;
        ((Button) findViewById(R.id.scorebutton)).setEnabled(true);
        Button roll = findViewById(R.id.rollbutton);
        roll.setText(getString(R.string.reroll,numRolls));
        if(numRolls < 1)
            roll.setEnabled(false);
        for(int i = 0; i < 5; i++)
            x[i].setChecked(false);
        setDiceNumbers();
    }

    private void setText(Button b, int num) {
        if(num == -1)
            b.setText("");
        else
            b.setText(getString(R.string.die,num));
    }

    public void onClick1(View view){
        if(tempButton != null) {
            setText(tempButton, -1);
        }
        tempButton = (Button) findViewById(view.getId());
        tempScore = nums[0];
        setText(tempButton, tempScore);
        ((Button) findViewById(R.id.submit)).setEnabled(true);
    }
    public void onClick2(View view){
        if(tempButton != null) {
            setText(tempButton, -1);
        }
        tempButton = (Button) findViewById(view.getId());
        setText(tempButton,nums[1] * 2);
        ((Button) findViewById(R.id.submit)).setEnabled(true);
    }
    public void onClick3(View view){
        if(tempButton != null) {
            setText(tempButton, -1);
        }
        tempButton = (Button) findViewById(view.getId());
        tempScore = nums[2] * 3;
        setText(tempButton, tempScore);
        ((Button) findViewById(R.id.submit)).setEnabled(true);
    }
    public void onClick4(View view){
        if(tempButton != null) {
            setText(tempButton, -1);
        }
        tempButton = (Button) findViewById(view.getId());
        tempScore = nums[3] * 4;
        setText(tempButton, tempScore);
        ((Button) findViewById(R.id.submit)).setEnabled(true);
    }
    public void onClick5(View view){
        if(tempButton != null) {
            setText(tempButton, -1);
        }
        tempButton = (Button) findViewById(view.getId());
        tempScore = nums[4] * 5;
        setText(tempButton, tempScore);
        ((Button) findViewById(R.id.submit)).setEnabled(true);
    }
    public void onClick6(View view){
        if(tempButton != null) {
            setText(tempButton, -1);
        }
        tempButton = (Button) findViewById(view.getId());
        tempScore = nums[5] * 6;
        setText(tempButton, tempScore);
        ((Button) findViewById(R.id.submit)).setEnabled(true);
    }
    public void onClick3ofaKind(View view){
        if(tempButton != null) {
            setText(tempButton, -1);
        }
        tempButton = (Button) findViewById(view.getId());
        tempScore = 0;
        for(int i = 0; i < 6; i++)
            if(nums[i] >= 3)
                tempScore = diceTotal();
        setText(tempButton, tempScore);
        ((Button) findViewById(R.id.submit)).setEnabled(true);
    }
    public void onClick4ofaKind(View view){
        if(tempButton != null) {
            setText(tempButton, -1);
        }
        tempButton = (Button) findViewById(view.getId());
        tempScore = 0;
        for(int i = 0; i < 6; i++)
            if(nums[i] >= 4)
                tempScore = diceTotal();
        setText(tempButton, tempScore);
        ((Button) findViewById(R.id.submit)).setEnabled(true);
    }
    public void onClickYahtzee(View view){
        if(tempButton != null) {
            setText(tempButton, -1);
        }
        tempButton = (Button) findViewById(view.getId());
        tempScore = 0;
        for(int i = 0; i < 6; i++)
            if(nums[i] == 5)
                tempScore = 50;
        setText(tempButton, tempScore);
        ((Button) findViewById(R.id.submit)).setEnabled(true);
    }
    public void onClickChance(View view){
        if(tempButton != null) {
            setText(tempButton, -1);
        }
        tempButton = (Button) findViewById(view.getId());
        tempScore = diceTotal();
        setText(tempButton,tempScore);
        ((Button) findViewById(R.id.submit)).setEnabled(true);
    }
    public void onClickFullHouse(View view){
        if(tempButton != null) {
            setText(tempButton, -1);
        }
        tempButton = (Button) findViewById(view.getId());
        tempScore = 0;
        int NumWithFour = 0;
        int NumWithZero = 0;

        for(int i = 0; i < 6; i++) {
            if(nums[i] == 4)
                NumWithFour++;
            else if(nums[i] == 0)
                NumWithZero++;
        }
        if(NumWithFour == 0 && NumWithZero >= 3)
            tempScore = 25;
        setText(tempButton,tempScore);
        ((Button) findViewById(R.id.submit)).setEnabled(true);
    }
    public void onClickSmallStraight(View view){
        if(tempButton != null) {
            setText(tempButton, -1);
        }
        tempButton = (Button) findViewById(view.getId());
        tempScore = 0;
        int count = 0;

        for(int i = 0; i < 6; i++) {
            if(nums[i] >=1)
                count++;
            else
                count = 0;
            if(count == 4)
                tempScore = 30;
        }
        setText(tempButton,tempScore);
        ((Button) findViewById(R.id.submit)).setEnabled(true);
    }
    public void onClickLargeStraight(View view){
        if(tempButton != null) {
            setText(tempButton, -1);
        }
        tempButton = (Button) findViewById(view.getId());
        tempScore = 0;
        int count = 0;

        for(int i = 0; i < 6; i++) {
            if(nums[i] >=1)
                count++;
            else
                count = 0;
            if(count == 5)
                tempScore= 40;
        }
        setText(tempButton,tempScore);
        ((Button) findViewById(R.id.submit)).setEnabled(true);
    }
    private int diceTotal(){
        int total = 0;
        for(int i = 0; i < 6; i++)
            total += (i + 1) * nums[i];
        return total;
    }

    private void setDiceNumbers() {
        ((TextView) findViewById(R.id.Die1)).setText(getString(R.string.die, die[0]));
        ((TextView) findViewById(R.id.Die2)).setText(getString(R.string.die, die[1]));
        ((TextView) findViewById(R.id.Die3)).setText(getString(R.string.die, die[2]));
        ((TextView) findViewById(R.id.Die4)).setText(getString(R.string.die, die[3]));
        ((TextView) findViewById(R.id.Die5)).setText(getString(R.string.die, die[4]));
    }

    private Button[][] setButtonsForButtonArray() {
        Button[][] x = new Button[6][13];
        x[0][0] = findViewById(R.id.button1_1);
        x[0][1] = findViewById(R.id.button1_2);
        x[0][2] = findViewById(R.id.button1_3);
        x[0][3] = findViewById(R.id.button1_4);
        x[0][4] = findViewById(R.id.button1_5);
        x[0][5] = findViewById(R.id.button1_6);
        x[0][6] = findViewById(R.id.button1_7);
        x[0][7] = findViewById(R.id.button1_8);
        x[0][8] = findViewById(R.id.button1_9);
        x[0][9] = findViewById(R.id.button1_10);
        x[0][10] = findViewById(R.id.button1_11);
        x[0][11] = findViewById(R.id.button1_12);
        x[0][12] = findViewById(R.id.button1_13);
        x[1][0] = findViewById(R.id.button2_1);
        x[1][1] = findViewById(R.id.button2_2);
        x[1][2] = findViewById(R.id.button2_3);
        x[1][3] = findViewById(R.id.button2_4);
        x[1][4] = findViewById(R.id.button2_5);
        x[1][5] = findViewById(R.id.button2_6);
        x[1][6] = findViewById(R.id.button2_7);
        x[1][7] = findViewById(R.id.button2_8);
        x[1][8] = findViewById(R.id.button2_9);
        x[1][9] = findViewById(R.id.button2_10);
        x[1][10] = findViewById(R.id.button1_11);
        x[1][11] = findViewById(R.id.button1_12);
        x[1][12] = findViewById(R.id.button1_13);
        x[2][0] = findViewById(R.id.button3_1);
        x[2][1] = findViewById(R.id.button3_2);
        x[2][2] = findViewById(R.id.button3_3);
        x[2][3] = findViewById(R.id.button3_4);
        x[2][4] = findViewById(R.id.button3_5);
        x[2][5] = findViewById(R.id.button3_6);
        x[2][6] = findViewById(R.id.button3_7);
        x[2][7] = findViewById(R.id.button3_8);
        x[2][8] = findViewById(R.id.button3_9);
        x[2][9] = findViewById(R.id.button3_10);
        x[2][10] = findViewById(R.id.button1_11);
        x[2][11] = findViewById(R.id.button1_12);
        x[2][12] = findViewById(R.id.button1_13);
        x[0][0] = findViewById(R.id.button1_1);
        x[0][1] = findViewById(R.id.button1_2);
        x[0][2] = findViewById(R.id.button1_3);
        x[0][3] = findViewById(R.id.button1_4);
        x[0][4] = findViewById(R.id.button1_5);
        x[0][5] = findViewById(R.id.button1_6);
        x[0][6] = findViewById(R.id.button1_7);
        x[0][7] = findViewById(R.id.button1_8);
        x[0][8] = findViewById(R.id.button1_9);
        x[0][9] = findViewById(R.id.button1_10);
        x[0][10] = findViewById(R.id.button1_11);
        x[0][11] = findViewById(R.id.button1_12);
        x[0][12] = findViewById(R.id.button1_13);
        x[1][0] = findViewById(R.id.button2_1);
        x[1][1] = findViewById(R.id.button2_2);
        x[1][2] = findViewById(R.id.button2_3);
        x[1][3] = findViewById(R.id.button2_4);
        x[1][4] = findViewById(R.id.button2_5);
        x[1][5] = findViewById(R.id.button2_6);
        x[1][6] = findViewById(R.id.button2_7);
        x[1][7] = findViewById(R.id.button2_8);
        x[1][8] = findViewById(R.id.button2_9);
        x[1][9] = findViewById(R.id.button2_10);
        x[1][10] = findViewById(R.id.button1_11);
        x[1][11] = findViewById(R.id.button1_12);
        x[1][12] = findViewById(R.id.button1_13);
        x[2][0] = findViewById(R.id.button3_1);
        x[2][1] = findViewById(R.id.button3_2);
        x[2][2] = findViewById(R.id.button3_3);
        x[2][3] = findViewById(R.id.button3_4);
        x[2][4] = findViewById(R.id.button3_5);
        x[2][5] = findViewById(R.id.button3_6);
        x[2][6] = findViewById(R.id.button3_7);
        x[2][7] = findViewById(R.id.button3_8);
        x[2][8] = findViewById(R.id.button3_9);
        x[2][9] = findViewById(R.id.button3_10);
        x[2][10] = findViewById(R.id.button1_11);
        x[2][11] = findViewById(R.id.button1_12);
        x[2][12] = findViewById(R.id.button1_13);
        return x;
    }

    private void setScoreNumbers() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 13; j++) {
                setText(buttonViews[i][j], scores[i][j]);
                if (scores[i][j] != -1)
                    buttonViews[i][j].setEnabled(false);
            }
        }
    }

    public void calculateScores() {
        int upperTotal = 0;
        for(int i = 0; i < 6; i++) {
            upperTotal += scores[0][i];
        }
        ((TextView) findViewById(R.id.text1_1)).setText(getString(R.string.die, upperTotal));
        if(upperTotal >= 63)
        {
            ((TextView) findViewById(R.id.text1_2)).setText(getString(R.string.die, 35));
            upperTotal += 35;
        } else {
            ((TextView) findViewById(R.id.text1_2)).setText(getString(R.string.die, 0));
        }
        ((TextView) findViewById(R.id.text1_3)).setText(getString(R.string.die, upperTotal));
        ((TextView) findViewById(R.id.text1_5)).setText(getString(R.string.die, upperTotal));
        int lowerTotal = 0;
        for(int i = 6; i < 13; i++) {
            lowerTotal += scores[0][i];
        }
        ((TextView) findViewById(R.id.text1_4)).setText(getString(R.string.die, lowerTotal));
        int columnTotal = upperTotal + lowerTotal;
        ((TextView) findViewById(R.id.text1_6)).setText(getString(R.string.die, columnTotal));
        columnTotal *= 6;
        ((TextView) findViewById(R.id.text1_7)).setText(getString(R.string.die, columnTotal));
        ((TextView) findViewById(R.id.text8)).setText(getString(R.string.die, columnTotal));
    }
}