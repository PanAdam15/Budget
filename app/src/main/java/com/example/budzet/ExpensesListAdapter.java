package com.example.budzet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExpensesListAdapter extends RecyclerView.Adapter<ExpensesListAdapter.ExpensesViewHolder> {

    private List<ExpensesEntity> mExpensesList;
    private LayoutInflater mInflater;
    private ExpensesViewModel expensesViewModel;
    private ItemTouchHelper itemTouchHelper;

    interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setExpensesViewModel(ExpensesViewModel expensesViewModel) {
        this.expensesViewModel = expensesViewModel;
    }

    public ExpensesListAdapter(Context context,OnItemClickListener mOnItemClickListener) {
        mInflater = LayoutInflater.from(context);
        this.mExpensesList = null;
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public int getItemCount() {
        if (mExpensesList != null)
            return mExpensesList.size();
        return 0;
    }

    public void setExpensesList(List<ExpensesEntity> mExpensesList) {
        this.mExpensesList = mExpensesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExpensesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View row = mInflater.inflate(R.layout.activity_row, null);
        return new ExpensesViewHolder(row);

    }

    @Override
    public void onBindViewHolder(@NonNull ExpensesViewHolder expensesViewHolder, int rowNumber) {
        ExpensesEntity expenses = mExpensesList.get(rowNumber);
        expensesViewHolder.SetRow(rowNumber);
        expensesViewHolder.amountView.setText(String.valueOf(expenses.getAmount()));
        expensesViewHolder.dateView.setText(expenses.getDate());
        expensesViewHolder.categoryView.setText(expenses.getCategory());


        //phoneViewHolder.bindToWordViewHolder(rowNumber);
    }



    public void onItemSwiped(int position) {

        expensesViewModel.deleteSelected(mExpensesList.get(position));
        mExpensesList.remove(position);
        notifyItemRemoved(position);

    }


    public void setItemTouchHelper(ItemTouchHelper itemTouchHelper) {
        this.itemTouchHelper = itemTouchHelper;
    }


    public class ExpensesViewHolder extends RecyclerView.ViewHolder  {
        TextView amountView, dateView, categoryView;
        int rowNumber;

        public ExpensesViewHolder(@NonNull View mainRowElement) {
            super(mainRowElement);
            amountView = mainRowElement.findViewById(R.id.textView3);
            dateView = mainRowElement.findViewById(R.id.textView4);
            categoryView = mainRowElement.findViewById(R.id.textView5);
            mainRowElement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(getAdapterPosition());
                    //  Intent intent = new Intent(Context,EditPhone.class);
                    // intent.putExtra("id", mPhoneList.get(getAdapterPosition()).getId());
                    //mPhoneList.get(getAdapterPosition()).getId();
                }
            });

        }


        public void SetRow(int rowNumber) {
            this.rowNumber = rowNumber;

        }


    }
}