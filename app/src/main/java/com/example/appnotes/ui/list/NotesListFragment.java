package com.example.appnotes.ui.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appnotes.R;
import com.example.appnotes.domain.DeviceNotesRepository;
import com.example.appnotes.domain.Note;
import com.example.appnotes.ui.details.NoteDetailsActivity;

import java.util.List;

public class NotesListFragment extends Fragment implements NotesListView {

    public interface onNoteClicked {
        void onNoteOnClicked(Note note);
    }

    //    private static final String KEY_SELECTED_NOTE = "KEY_SELECTED_NOTE";
//    private static final String ARG_NOTE = "ARG_NOTE";
    private NotesListPresenter presenter;
    private LinearLayout container;
    private onNoteClicked onNoteClicked;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof onNoteClicked) {
            onNoteClicked = (onNoteClicked) context;
        }
    }

    @Override
    public void onDetach() {
        onNoteClicked = null;
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new NotesListPresenter(this, new DeviceNotesRepository());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        container = view.findViewById(R.id.root);
        presenter.requestNotes();
    }

    @Override
    public void showNotes(List<Note> notes) {
        for (Note note : notes) {
            View noteItem = LayoutInflater.from(requireContext()).inflate(R.layout.item_note, container, false);
            noteItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onNoteClicked != null) {
                        onNoteClicked.onNoteOnClicked(note);
                    }
                }
            });
            TextView noteName = noteItem.findViewById(R.id.note_name);
            noteName.setText(note.getName());
            container.addView(noteItem);
        }
    }
}