package com.example.checkyourbrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startbutton ;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    TextView ques;   TextView check;
    TextView number;
    TextView time;
    Button playButton;
    ConstraintLayout gameLayout;
    int correctLocation;
    int score=0;
    int noOfQues =0;
    ArrayList<Integer> answers= new ArrayList<Integer>();
    public void playAGAIN(View view){
        score=0;
        noOfQues =0;
        time.setText("30s");
        number.setText("0/0");
        check.setVisibility(View.INVISIBLE);
        playButton.setVisibility(View.INVISIBLE);
        generateQues();
        new CountDownTimer(30000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                time.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                startbutton.setText("Game Over!");
                startbutton.setVisibility(View.VISIBLE);
                time.setText("0s");
                check.setText("Your Score is : " + Integer.toString(score) + "/" + Integer.toString(noOfQues));
                check.setVisibility(View.VISIBLE);
                playButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }
    public void generateQues(){
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        ques.setText(Integer.toString(a) + " + " + Integer.toString(b));
        answers.clear();
        correctLocation = rand.nextInt(4);
        int incorrect;
        for(int i =0 ;i<4;i++){
            if(i==correctLocation){
                answers.add(a + b);
            }else {
                incorrect = rand.nextInt(41);
                while(incorrect == a + b){
                    incorrect = rand.nextInt(41);
                }
                answers.add(incorrect);
            }
        }
        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));

    }
    public void choose(View view){
    if(view.getTag().toString().equals(Integer.toString(correctLocation))){
         score++;

         check.setText("CORRECT!");
         check.setVisibility(View.VISIBLE);
    } else{
        check.setText("WRONG!");
        check.setVisibility(View.VISIBLE);
    }
     noOfQues++;
     number.setText(Integer.toString(score) + "/" + Integer.toString(noOfQues));
     generateQues();
    }



    public void start (View view){

        startbutton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(ConstraintLayout.VISIBLE);
        playAGAIN(findViewById(R.id.playButton));
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startbutton = findViewById(R.id.start);
        ques = findViewById(R.id.ques);
        check=findViewById(R.id.check);
        check.setVisibility(View.INVISIBLE);
        number = findViewById(R.id.number);
        time=findViewById(R.id.time);
        gameLayout = findViewById(R.id.gameLayout);
        gameLayout.setVisibility(ConstraintLayout.INVISIBLE);
        playButton = findViewById(R.id.playButton);
         button1 = findViewById(R.id.button1);
         button2 = findViewById(R.id.button2);
         button3 = findViewById(R.id.button3);
         button4 = findViewById(R.id.button4);

    }
}