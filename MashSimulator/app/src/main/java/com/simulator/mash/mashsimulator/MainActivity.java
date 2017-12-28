package com.simulator.mash.mashsimulator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Timer;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    TextView textBalance;
    TextView textExperience;
    TextView textButtonRaiseBalance;
    TextView textAutoRaiseBalance;
    GridLayout gridLayout;

    // Create a instance of MainNumbers
    MainNumbers mainNumbers = new MainNumbers(0,1,1,1);

    // Create a vector of units
    Vector<Unit> vectorOfUnits = new Vector<Unit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find the TextViews and set the colors
        textBalance = findViewById(R.id.textBalance);
        textExperience = findViewById(R.id.textExperience);
        textExperience.setTextColor(Color.RED);
        textButtonRaiseBalance = findViewById(R.id.textButtonRaiseBalance);
        textButtonRaiseBalance.setTextColor(Color.BLUE);
        textAutoRaiseBalance = findViewById(R.id.textAutoRaiseBalance);
        textAutoRaiseBalance.setTextColor(Color.GREEN);

        // Populate vector with fruits
        populateVectorOfUnits();

        // UI updated every seconds by Thread
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mainNumbers.setMainBalanceIncrease(mainNumbers.getMainAutoRaiseBalance() );

                                // Add value to  TextViews
                                textBalance.setText(String.valueOf(mainNumbers.getMainBalance()) );
                                textExperience.setText(String.valueOf(mainNumbers.getMainExperience()) );
                                textButtonRaiseBalance.setText(String.valueOf(mainNumbers.getMainButtonRaiseBalance()) );
                                textAutoRaiseBalance.setText(String.valueOf(mainNumbers.getMainAutoRaiseBalance()) );

                                showUnitButtons();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();
    }

    private void DisplayToast(String msg)
    {
        Toast.makeText(getBaseContext(), msg,
                Toast.LENGTH_SHORT).show();
    }

    private void populateVectorOfUnits(){
        Unit apple = new Unit(0,"Apple", 1, 1, 1, 5, 0,0);
        vectorOfUnits.add(apple);
        Unit peach = new Unit(1,"Peach", 2, 2, 1, 400, 0,10);
        vectorOfUnits.add(peach);
        Unit grape = new Unit(2,"Grape", 3, 3, 1, 900, 0,100);
        vectorOfUnits.add(grape);
    }

    private void showUnitButtons(){

        gridLayout = findViewById(R.id.gridLayout);
        gridLayout.removeAllViews();

        for (final Unit unit: vectorOfUnits) {
            if (unit.getMinExperience() <= mainNumbers.getMainExperience()) {

                LinearLayout llForUnit = new LinearLayout(this);
                LinearLayout llForTexts = new LinearLayout(this);

                TextView raiseExperienceText = new TextView(this);
                TextView raiseBalanceText = new TextView(this);
                TextView autoRaiseBalanceText = new TextView(this);

                final Button button = new Button(this);
                button.setId(unit.getId());
                button.setText(unit.getName());

                // If mainBalance less than the price of the unit,
                // button must be disabled
                if (unit.getPrice() > mainNumbers.getMainBalance() )
                {
                    button.setEnabled(false);
                }

                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        // Increase main numbers
                        mainNumbers.setMainButtonRaiseBalance(unit.getButtonRaiseBalance());
                        mainNumbers.setMainExperience(unit.getRaiseExperience());
                        mainNumbers.setMainAutoRaiseBalance(unit.getAutoRaiseBalance());
                        mainNumbers.setMainBalanceIncrease(mainNumbers.getMainButtonRaiseBalance());

                        // Increase the values of unit fields
                        unit.setAmount(unit.getAmount() + 1);
                        unit.setPrice((int) Math.ceil((unit.getPrice() * 1.1)));

                        // mainBalance is decreased by price
                        mainNumbers.setMainBalanceDecrease((int)unit.getPrice());

                        //DisplayToast( "You have " + String.valueOf(unit.getAmount()) + " " + unit.getName() + "s");
                    }
                });

                raiseExperienceText.setText( "+" + String.valueOf(unit.getRaiseExperience()) );
                raiseExperienceText.setTextColor(Color.RED);
                raiseBalanceText.setText( "+" + String.valueOf(unit.getButtonRaiseBalance()) );
                raiseBalanceText.setTextColor(Color.BLUE);
                autoRaiseBalanceText.setText("+" + String.valueOf(unit.getAutoRaiseBalance()) );
                autoRaiseBalanceText.setTextColor(Color.GREEN);

                llForTexts.setOrientation(LinearLayout.VERTICAL);
                llForTexts.addView(raiseExperienceText);
                llForTexts.addView(raiseBalanceText);
                llForTexts.addView(autoRaiseBalanceText);

                llForUnit.setOrientation(LinearLayout.HORIZONTAL);
                llForUnit.addView(button);
                llForUnit.addView(llForTexts);
                
                gridLayout.addView(llForUnit);
            }
        }
    }
}
