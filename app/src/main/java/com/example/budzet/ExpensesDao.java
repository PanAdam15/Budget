package com.example.budzet;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.w3c.dom.Element;

import java.util.List;

@Dao
public interface ExpensesDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(ExpensesEntity element);

    @Query("DELETE FROM Expenses")
    void deleteAll();

    @Delete
    void deleteSelected(ExpensesEntity expenses);

    @Query("SELECT * FROM Expenses")
    LiveData<List<ExpensesEntity>> getAlphabetizedElements();

    @Query("SELECT * FROM Expenses WHERE id=:id")
    LiveData<ExpensesEntity> getExpense(long id);

    @Update
    void update(ExpensesEntity phone);
}
