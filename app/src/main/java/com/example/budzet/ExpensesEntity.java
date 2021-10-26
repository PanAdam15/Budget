package com.example.budzet;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Expenses")
public class ExpensesEntity {

    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name = "id")
    private long id;

    @NonNull
    @ColumnInfo(name = "Kwota")
    public double amount;

    @NonNull
    @ColumnInfo(name = "Data")
    public String date;

    @NonNull
    @ColumnInfo(name = "kategoria")
    public String category;

    public ExpensesEntity(double amount, @NonNull String date, @NonNull String category) {
        this.amount = amount;
        this.date = date;
        this.category = category;
    }
    @Ignore
    public ExpensesEntity(long id, double amount, @NonNull String date, @NonNull String category) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    @NonNull
    public String getCategory() {
        return category;
    }

    public void setCategory(@NonNull String category) {
        this.category = category;
    }
}