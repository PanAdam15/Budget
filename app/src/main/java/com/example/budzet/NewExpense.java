package com.example.budzet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class NewExpense extends AppCompatActivity {

    private EditText editUpdateAmount, editUpdateDate, editUpdateCategory;
    private Bundle pack;
    private Long id;
    private double updateAmount;
    private String  updateDate, updateCategory;
    private LiveData<ExpensesEntity> expenses;
    ExpensesViewModel expensesModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editUpdateAmount = findViewById(R.id.EditUpdateAmount);
        editUpdateDate = findViewById(R.id.EdutUpdateData);
        editUpdateCategory = findViewById(R.id.EditUpdateCategory);
        Button update = findViewById(R.id.ButtonUpdate);
        Button cancel = findViewById(R.id.ButtonUpdateCancel);

        pack = getIntent().getExtras();
        if(pack!= null) {
            id = pack.getLong("id");
            updateAmount = pack.getDouble("amount");
            updateDate = pack.getString("date");
            updateCategory = pack.getString("category");

            expensesModel = new ViewModelProvider(this).get(ExpensesViewModel.class);
            expenses = expensesModel.getExpense(id);



            expenses.observe(this, new Observer<ExpensesEntity>() {
                @Override
                public void onChanged(ExpensesEntity expenses) {
                    editUpdateAmount.setText(String.valueOf(updateAmount));
                    editUpdateDate.setText(updateDate);
                    editUpdateCategory.setText(updateCategory);
                }
            });

        } else
            update.setText("Dodaj");

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedAmount = editUpdateAmount.getText().toString();
                String updatedDate = editUpdateDate.getText().toString();
                String updatedCategory = editUpdateCategory.getText().toString();

                if (updatedAmount.trim().length() == 0 | updatedDate.trim().length() == 0 | updatedCategory.trim().length() == 0 ) {
                    showToast("Dane nie moga byc puste");

                } else {
                    double insAmount = Double.valueOf(editUpdateAmount.getText().toString());
                    Intent Updateintent = new Intent();
                    Updateintent.putExtra("ID",id);
                    Updateintent.putExtra("amount",insAmount);
                    Updateintent.putExtra("date",updatedDate);
                    Updateintent.putExtra("category",updatedCategory);
                    setResult(RESULT_OK,Updateintent);
                    finish();
                }
            }

        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }
    private void showToast(String text) {
        Toast.makeText(NewExpense.this, text, Toast.LENGTH_SHORT).show();
    }

}

