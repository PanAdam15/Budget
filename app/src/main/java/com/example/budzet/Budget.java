package com.example.budzet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Budget extends AppCompatActivity {
        private EditText Sum, Savings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        Sum = findViewById(R.id.EditSum);
        Savings = findViewById(R.id.EditSavings);
        Button confirm = findViewById(R.id.ButtonConfirm);

        confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String IntentSum = Sum.getText().toString();
                String IntentSavings = Savings.getText().toString();
                    if(IntentSavings.isEmpty() | IntentSum.isEmpty()){
                        showToast("Dane nie moga byc puste");
                    }
                    else{
                        Intent intent = new Intent();
                        intent.putExtra("Sum",IntentSum);
                        intent.putExtra("Savings", IntentSavings);
                        setResult(RESULT_OK,intent);
                        finish();
                    }
            }
        });
    }
    private void showToast(String text) {
        Toast.makeText(Budget.this, text, Toast.LENGTH_SHORT).show();
    }
}
