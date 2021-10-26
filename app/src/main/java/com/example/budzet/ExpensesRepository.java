package com.example.budzet;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ExpensesRepository {
    private final ExpensesDao mElementDao;
    private final LiveData<List<ExpensesEntity>> mAllElements;

    ExpensesRepository(Application application) {
        ExpensesRoomDatabase expensesRoomDatabase =
                ExpensesRoomDatabase.getDatabase(application);
        mElementDao = expensesRoomDatabase.expensesDao();
        mAllElements = mElementDao.getAlphabetizedElements();
    }
    LiveData<List<ExpensesEntity>> getAllElements(){
        return mAllElements;
    }

    void deleteAll(){
        ExpensesRoomDatabase.databaseWriteExecutor.execute(() -> {
            mElementDao.deleteAll();
        });
    }
    void deleteSelected(ExpensesEntity expense){
        ExpensesRoomDatabase.databaseWriteExecutor.execute(() -> {
            mElementDao.deleteSelected(expense);
        });
    }

    public void insert(ExpensesEntity expense){
        ExpensesRoomDatabase.databaseWriteExecutor.execute(() -> {
            mElementDao.insert(expense);
        });
    }
    public void update(ExpensesEntity expense){
        ExpensesRoomDatabase.databaseWriteExecutor.execute(() -> {
            mElementDao.update(expense);
        });
    }
    LiveData<ExpensesEntity> getExpense(long id){
        return mElementDao.getExpense(id);
    }

}
