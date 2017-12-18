package com.simulator.mash.mashsimulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    LinearLayout linearLayout;

    // Create a timer
    Timer timer = new Timer();

    // Create a instance of MainNumbers
    MainNumbers mainNumbers = new MainNumbers(0,0,0,0);

    // Create a vector of units
    Vector<Unit> vectorOfUnits = new Vector<Unit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find the TextViews
        textBalance = findViewById(R.id.textBalance);
        textExperience = findViewById(R.id.textExperience);
        textButtonRaiseBalance = findViewById(R.id.textButtonRaiseBalance);
        textAutoRaiseBalance = findViewById(R.id.textAutoRaiseBalance);

        // Add value to  TextViews
        textBalance.setText(String.valueOf(mainNumbers.getMainBalance()) );
        textExperience.setText(String.valueOf(mainNumbers.getMainExperience()) );
        textButtonRaiseBalance.setText(String.valueOf(mainNumbers.getMainButtonRaiseBalance()) );
        textAutoRaiseBalance.setText(String.valueOf(mainNumbers.getMainAutoRaiseBalance()) );

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
                                mainNumbers.setMainBalance(mainNumbers.getMainAutoRaiseBalance() );
                                textBalance.setText(String.valueOf(mainNumbers.getMainBalance()) );
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();


        linearLayout = findViewById(R.id.linearLayout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for (final Unit unit: vectorOfUnits){
            final Button button = new Button(this);
            button.setId(unit.getId());
            button.setText(unit.getName());
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    //Increase numbers
                    mainNumbers.setMainButtonRaiseBalance(unit.getButtonRaiseBalance());
                    mainNumbers.setMainAutoRaiseBalance(unit.getAutoRaiseBalance());
                    mainNumbers.setMainBalance(mainNumbers.getMainButtonRaiseBalance() );
                    unit.setAmount(unit.getAmount() + 1);

                    // Update UI text
                    textBalance.setText(String.valueOf(mainNumbers.getMainBalance()) );
                    textExperience.setText(String.valueOf(mainNumbers.getMainExperience()));
                    textButtonRaiseBalance.setText(String.valueOf(mainNumbers.getMainButtonRaiseBalance()) );
                    textAutoRaiseBalance.setText(String.valueOf(mainNumbers.getMainAutoRaiseBalance()) );

                    // Display the second unit with Toast
                    DisplayToast(String.valueOf(unit.getAmount()));
                }
            });
            linearLayout.addView(button);
        }
    }

    private void DisplayToast(String msg)
    {
        Toast.makeText(getBaseContext(), msg,
                Toast.LENGTH_SHORT).show();
    }

    private void populateVectorOfUnits(){
        Unit apple = new Unit(0,"Apple", 1, 1, 1, 5, 0);
        vectorOfUnits.add(apple);
        Unit peach = new Unit(1,"Peach", 2, 2, 1, 5, 0);
        vectorOfUnits.add(peach);
        Unit grape = new Unit(2,"Grape", 3, 3, 1, 5, 0);
        vectorOfUnits.add(grape);
    }
}
