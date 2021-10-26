package com.example.budzet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int EDIT_BUDGET_ACTIVITY_REQUEST_CODE = 1;
    private static final int EDIT_GOALS_ACTIVITY_REQUEST_CODE = 2;
    TextView Savings, Sum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView Name = findViewById(R.id.ViewName);
        TextView TelNr = findViewById(R.id.ViewTel);
        TextView Email = findViewById(R.id.ViewEmail);
        Savings = findViewById(R.id.ViewGoal);
         Sum = findViewById(R.id.ViewSum);
        Button EditBudget = findViewById(R.id.ButtonEditBudget);
        Button EditGoals = findViewById(R.id.ButtonRecycler);

        EditBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, Budget.class);
               startActivityForResult(intent,EDIT_BUDGET_ACTIVITY_REQUEST_CODE);

            }
        });
        EditGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExpenseList.class);
                startActivityForResult(intent,EDIT_GOALS_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode,resultCode, intent);

        if(requestCode == EDIT_BUDGET_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            if(intent!=null){
                String IntentSum = intent.getStringExtra("Sum");
                String IntentSavings = intent.getStringExtra("Savings");
                Sum.setText("Stan konta: " + IntentSum);
                Savings.setText("Cel oszczednosciowy: "+ IntentSavings);
            }
        }
    }


}