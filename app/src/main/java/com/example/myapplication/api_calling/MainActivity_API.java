package com.example.myapplication.api_calling;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity_API extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private myAdapter adapter;
    private List<responseModel> dataList;
    private apiSet apiService;
    private Snackbar snackbar;
    private responseModel deletedItem;
    private int deletedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_activity_main);

        recyclerView = findViewById(R.id.recyclcerId);
        progressBar = findViewById(R.id.progressbar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataList = new ArrayList<>();
        adapter = new myAdapter(dataList, new myAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(int position) {
                int id = Integer.parseInt(dataList.get(position).getId());
                deleteData(id, position);
            }
        });
        recyclerView.setAdapter(adapter);

        apiService = apiCntroller.getInstance().getApiALL();

        fetchData();
        setupSwipeToDelete();
    }

    private void fetchData() {
        progressBar.setVisibility(View.VISIBLE);

        Call<List<responseModel>> call = apiService.getData();

        call.enqueue(new Callback<List<responseModel>>() {
            @Override
            public void onResponse(Call<List<responseModel>> call, Response<List<responseModel>> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful()) {
                    dataList.clear();
                    dataList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity_API.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<responseModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity_API.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteData(int id, final int position) {
        Call<Void> call = apiService.deleteUser(id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    deletedItem = dataList.get(position);
                    deletedPosition = position;

                    dataList.remove(position);
                    adapter.notifyItemRemoved(position);

                    showUndoSnackbar();
                } else {
                    Toast.makeText(MainActivity_API.this, "Failed to delete ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity_API.this, "Failed to delete ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showUndoSnackbar() {
        snackbar = Snackbar.make(recyclerView, "User deleted", Snackbar.LENGTH_LONG);
        snackbar.setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undoDelete();
            }
        });
        snackbar.addCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                if (event != DISMISS_EVENT_ACTION) {
                    // Snack bar is dismissed without undo action, perform the actual delete
                    performDelete();
                }
            }
        });
        snackbar.show();
    }

    private void undoDelete() {
        if (deletedItem != null) {
            dataList.add(deletedPosition, deletedItem);
            adapter.notifyItemInserted(deletedPosition);
            recyclerView.scrollToPosition(deletedPosition);
            deletedItem = null;
        }
    }

    private void performDelete() {
        // Delete action is confirmed, perform the actual delete here
        if (deletedItem != null) {
            // Perform the delete operation on your data source if needed
            // ...

            Toast.makeText(MainActivity_API.this, "Deleted successfully", Toast.LENGTH_SHORT).show();
            deletedItem = null;
        }
    }

    private void setupSwipeToDelete() {
        Drawable deleteIcon = ContextCompat.getDrawable(this, R.drawable.ic_delete_24);
        ColorDrawable background = new ColorDrawable(Color.RED);

        ItemTouchHelper.SimpleCallback swipeToDeleteCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                int id = Integer.parseInt(dataList.get(position).getId());
                deleteData(id, position);
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                View itemView = viewHolder.itemView;
                int backgroundCornerOffset = 20;

                if (dX > 0) { // Swiping to the right
                    background.setBounds(itemView.getLeft(), itemView.getTop(), (int) dX, itemView.getBottom());
                } else if (dX < 0) { // Swiping to the left
                    background.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
                } else { // View is unswiped
                    background.setBounds(0, 0, 0, 0);
                }

                background.draw(c);

                int iconMargin = (itemView.getHeight() - deleteIcon.getIntrinsicHeight()) / 2;
                int iconTop = itemView.getTop() + (itemView.getHeight() - deleteIcon.getIntrinsicHeight()) / 2;
                int iconBottom = iconTop + deleteIcon.getIntrinsicHeight();

                if (dX > 0) { // Swiping to the right
                    int iconLeft = itemView.getLeft() + iconMargin;
                    int iconRight = itemView.getLeft() + iconMargin + deleteIcon.getIntrinsicWidth();
                    deleteIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
                } else if (dX < 0) { // Swiping to the left
                    int iconLeft = itemView.getRight() - iconMargin - deleteIcon.getIntrinsicWidth();
                    int iconRight = itemView.getRight() - iconMargin;
                    deleteIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
                } else { // View is unswiped
                    deleteIcon.setBounds(0, 0, 0, 0);
                }

                deleteIcon.draw(c);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}