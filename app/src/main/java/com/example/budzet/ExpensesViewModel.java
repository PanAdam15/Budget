package com.example.budzet;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ExpensesViewModel extends AndroidViewModel {
    private final ExpensesRepository mRepository;
    private final LiveData<List<ExpensesEntity>> mAllElements;
    private ExpensesDao phoneDao;
    private ExpensesRoomDatabase db;
    public ExpensesViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ExpensesRepository(application);
        mAllElements = mRepository.getAllElements();
        db = ExpensesRoomDatabase.getDatabase(application);
        phoneDao = db.expensesDao();

    }

    LiveData<List<ExpensesEntity>> getAllExpenses(){
        return mAllElements;
    }

    public void deleteAll(){
        mRepository.deleteAll();
    }
    public void deleteSelected(ExpensesEntity expense) { mRepository.deleteSelected(expense);}
    public void insert(ExpensesEntity expense){
        mRepository.insert(expense);
    }
    public void update(ExpensesEntity expense){
        mRepository.update(expense);
    }
    public LiveData<ExpensesEntity> getExpense(long id){
        return phoneDao.getExpense(id);
    }
}

