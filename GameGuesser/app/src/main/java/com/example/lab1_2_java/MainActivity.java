package com.example.lab1_2_java;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int randomNumber = 0;
    private int score = 0;
    private int inputNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refresh();
    }

    private void refresh()
    {
        Random rand = new Random();
        randomNumber = rand.nextInt(100);
        score = 0;

        TextView feedback = (TextView)findViewById(R.id.feedbackView);
        feedback.setTextColor(Color.BLACK);
        feedback.setText("Wpisz liczbę i zatwierdź przyciskiem!");
    }


    public void buttonSubmit(View view)
    {
        InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        EditText input = (EditText) findViewById(R.id.inputNumberField);


        try{
            inputNumber = Integer.parseInt(input.getText().toString());
            TextView feedback = (TextView)findViewById(R.id.feedbackView);

            if(inputNumber == randomNumber)
            {
                score++;
                feedback.setTextColor(Color.parseColor("#188E00"));

                if(score == 1)
                {
                    feedback.setText("Brawo!\nWylosowana liczba to: " + Integer.toString(inputNumber) + "\nTwoja liczba strzałów to: " + Integer.toString(score) + "\nZłoty strzał! Trafione za 1 razem!");
                }
                else if(score > 1 && score <= 3)
                {
                    feedback.setText("Brawo!\nWylosowana liczba to: " + Integer.toString(inputNumber) + "\nTwoja liczba strzałów to: " + Integer.toString(score) + "\nNiesamowity wynik!");
                }
                else if(score > 3 && score <= 5)
                {
                    feedback.setText("Brawo!\nWylosowana liczba to: " + Integer.toString(inputNumber) + "\nTwoja liczba strzałów to: " + Integer.toString(score) + "\nDobry wynik!");
                }
                else if(score > 5 && score <=7)
                {
                    feedback.setText("Brawo!\nWylosowana liczba to: " + Integer.toString(inputNumber) + "\nTwoja liczba strzałów to: " + Integer.toString(score) + "\nŚredni wynik :/");
                }
                else
                {
                    feedback.setText("Brawo!\nWylosowana liczba to: " + Integer.toString(inputNumber) + "\nTwoja liczba strzałów to: " + Integer.toString(score) + "\nBardzo słaby wynik :(\nNaucz się grać...");
                }

            }
            else if(inputNumber > randomNumber)
            {
                feedback.setTextColor(Color.BLACK);
                feedback.setText("Twoja liczba jest za duża :(");
                score++;
            }
            else if(inputNumber < randomNumber)
            {
                feedback.setTextColor(Color.BLACK);
                feedback.setText("Twoja liczba jest za mała :(");
                score++;
            }
        }
        catch (Exception e)
        {
            TextView feedback = (TextView)findViewById(R.id.feedbackView);
            feedback.setTextColor(Color.BLACK);
            feedback.setText("Niepoprawne dane...");
        }

        input.setText("");
    }

    public void buttonLosuj(View view)
    {
        refresh();
    }
}