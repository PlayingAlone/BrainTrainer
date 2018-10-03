package com.example.seth.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    public TextView time;
    public TextView textView;
    public TextView score;
    public TextView finalScore;
    public int[] boxs = new int[10];
    public int[] assigned = new int[]{7, 7, 7, 7, 7};
    public int num1;
    public int num2;
    public int answer;
    public int boxAnswer;
    public int randomInt;
    int numCorrect=0;
    int numQuestion=0;
    int userAnswer;
    public boolean check = false;
    public TextView box1;
    public TextView box2;
    public TextView box3;
    public TextView box4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        box1 = findViewById(R.id.box1);
        box2 = findViewById(R.id.box2);
        box3 = findViewById(R.id.box3);
        box4 = findViewById(R.id.box4);
        time = findViewById(R.id.time);
        textView = findViewById(R.id.textView);
        score=findViewById(R.id.score);
        finalScore=findViewById(R.id.finalScore);
        box1.setEnabled(false);
        box2.setEnabled(false);
        box3.setEnabled(false);
        box4.setEnabled(false);

    }

    public void onClick(View view) {
        box1.setEnabled(true);
        box2.setEnabled(true);
        box3.setEnabled(true);
        box4.setEnabled(true);
        finalScore.setText("");
        final Button start = (Button) view;

        start.setEnabled(false);

        new CountDownTimer(30000,1000){
            public void onTick(long miliSecsUntilDone){
                Log.i("Seconds Left",String.valueOf(miliSecsUntilDone/1000));
                time.setText(String.valueOf(miliSecsUntilDone/1000));
            }

            public void onFinish(){
                Log.i("We're done","no more countdown");
                start.setEnabled(true);
                time.setText("30");
                box1.setText("");
                box2.setText("");
                box3.setText("");
                box4.setText("");
                box1.setEnabled(false);
                box2.setEnabled(false);
                box3.setEnabled(false);
                box4.setEnabled(false);
                textView.setText("");
                score.setText("0/0");
                finalScore.setText(numCorrect+"/"+numQuestion);
                numCorrect=0;
                numQuestion=0;
            }
        }.start();

        askQuestion();
    }

    public void askQuestion() {

        score.setText(numCorrect+"/"+numQuestion);

        for (int k = 0; k < 4; k++) {
            assigned[k] = 7;
            boxs[k] = 0;
        }
        int min = 1;
        int max = 45;

        Random r = new Random();
        num1 = r.nextInt(max - min + 1) + min;
        num2 = r.nextInt(max - min + 1) + min;

        answer = num1 + num2;

        textView.setText(num1 + "+" + num2);

        int min1 = 0;
        int max1 = 3;
        int assign = r.nextInt(max1 - min1 + 1) + min1;

        boxs[assign] = answer;

        boxAnswer= assign+1;

        assigned[3] = assign;

        for (int i = 0; i < 3; i++) {

            do {
                randomInt = r.nextInt(45 - 1 + 1) + 1;
            } while (randomInt == answer);

            do {
                assign = r.nextInt(3 - 0 + 1) + 0;

                check = false;

                for (int j = 0; j < 4; j++) {
                    if (assigned[j] == assign) {
                        check = true;

                    }
                }
            } while (check == true);

            Log.i("broke", "" + i);
            assigned[i] = assign;
            boxs[assign] = randomInt;

        }


        box1.setText("" + boxs[0]);
        box2.setText("" + boxs[1]);
        box3.setText("" + boxs[2]);
        box4.setText("" + boxs[3]);


    }

    public void answerQuestion(View view){

        userAnswer= parseInt(view.getTag().toString());

        if (userAnswer==boxAnswer){
            numCorrect++;
        }
        numQuestion++;

        askQuestion();

    }

}
