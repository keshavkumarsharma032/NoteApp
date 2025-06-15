
package com.example.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NotesAdapter notesAdapter;
    private ArrayList<String> notesList;
    private TextView emptyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        emptyText = findViewById(R.id.emptyText);
        FloatingActionButton fab = findViewById(R.id.fab);

        notesList = new ArrayList<>();
        notesAdapter = new NotesAdapter(notesList, position -> {
            notesList.remove(position);
            notesAdapter.notifyItemRemoved(position);
            toggleEmptyView();
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(notesAdapter);

        fab.setOnClickListener(v -> {
            notesList.add("New Note");
            notesAdapter.notifyItemInserted(notesList.size() - 1);
            toggleEmptyView();
        });

        toggleEmptyView();
    }

    private void toggleEmptyView() {
        if (notesList.isEmpty()) {
            emptyText.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            emptyText.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}
