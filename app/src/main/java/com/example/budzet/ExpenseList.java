package com.example.budzet;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ExpenseList extends AppCompatActivity implements ExpensesListAdapter.OnItemClickListener {

    private ExpensesViewModel mElementViewModel;
    private ExpensesListAdapter mAdapter;
    private List<ExpensesEntity> mExpensesList;
    private static final int NEW_GOALS_ACTIVITY_REQUEST_CODE = 3;
    private static final int UPDATE_GOALS_ACTIVITY_REQUEST_CODE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);
        FloatingActionButton addNewButton;
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        mAdapter = new ExpensesListAdapter(this,this);

        ItemTouchHelper.Callback callback = new MyItemTouchHelper(mAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        mAdapter.setItemTouchHelper(itemTouchHelper);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(mAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        mElementViewModel = new ViewModelProvider(this).get(ExpensesViewModel.class);
        mAdapter.setExpensesViewModel(mElementViewModel);

        mElementViewModel.getAllExpenses().observe(this,elements->{
            mAdapter.setExpensesList(elements);
            mExpensesList = elements;
        });
        addNewButton = findViewById(R.id.fabMain);

        addNewButton.setOnClickListener(v -> {
            Intent intent = new Intent(ExpenseList.this, NewExpense.class);
            startActivityForResult(intent, NEW_GOALS_ACTIVITY_REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent pack) {
        super.onActivityResult(requestCode, resultCode, pack);

        if (requestCode == NEW_GOALS_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            if(pack!=null)
            {

                mElementViewModel.insert(new ExpensesEntity(pack.getLongExtra("ID", 0),
                                                            pack.getDoubleExtra("amount", 0),
                                                            pack.getStringExtra("date"),
                                                            pack.getStringExtra("category")));
                showToast("Dodanie wartosci");
            }
        }
        else if (requestCode == UPDATE_GOALS_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            if (pack != null) {

                ExpensesEntity expense = new ExpensesEntity(pack.getLongExtra("ID", 0),
                                                            pack.getDoubleExtra("amount", 0),
                                                            pack.getStringExtra("date"),
                                                            pack.getStringExtra("category"));
                showToast("Zaktualizowano");
                mElementViewModel.update(expense);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        if(id == R.id.first_option){
            mElementViewModel.deleteAll();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(ExpenseList.this,NewExpense.class);
        intent.putExtra("id",mExpensesList.get(position).getId());
        intent.putExtra("amount",mExpensesList.get(position).getAmount());
        intent.putExtra("date",mExpensesList.get(position).getDate());
        intent.putExtra("category",mExpensesList.get(position).getCategory());

        startActivityForResult(intent, UPDATE_GOALS_ACTIVITY_REQUEST_CODE);
    }

        private void showToast(String text) {
            Toast.makeText(ExpenseList.this, text, Toast.LENGTH_SHORT).show();
        }

}
