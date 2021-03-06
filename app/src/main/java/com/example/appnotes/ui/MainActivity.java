package com.example.appnotes.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.appnotes.R;
import com.example.appnotes.domain.Note;
import com.example.appnotes.ui.details.NoteDetailsActivity;
import com.example.appnotes.ui.details.NoteDetailsFragment;
import com.example.appnotes.ui.list.NotesListFragment;

public class MainActivity extends AppCompatActivity implements NotesListFragment.onNoteClicked {

    private static final String ARG_NOTE = "ARG_NOTE";
    private Note selectedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            Note note = savedInstanceState.getParcelable(ARG_NOTE);
            if (note != null && getResources().getBoolean(R.bool.isLandscape)) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.details_container, NoteDetailsFragment.newInstance(note), null)
                        .commit();
            }
        }
    }

    @Override
    public void onNoteOnClicked(Note note) {
        selectedNote = note;
        if (getResources().getBoolean(R.bool.isLandscape)) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.details_container, NoteDetailsFragment.newInstance(note), null)
                    .commit();
        } else {
            Intent intent = new Intent(this, NoteDetailsActivity.class);
            intent.putExtra(NoteDetailsActivity.ARG_NOTE, note);
            startActivity(intent);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        if (selectedNote != null) {
            outState.putParcelable(ARG_NOTE, selectedNote);
        }
        super.onSaveInstanceState(outState);
    }
}