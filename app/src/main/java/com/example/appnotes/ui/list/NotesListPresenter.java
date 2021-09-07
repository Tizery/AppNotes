package com.example.appnotes.ui.list;

import com.example.appnotes.domain.Note;
import com.example.appnotes.domain.NotesRepository;

import java.util.List;

public class NotesListPresenter {

    private final NotesListView view;
    private final NotesRepository repository;

    public NotesListPresenter(NotesListView view, NotesRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void requestNotes() {
        List<Note> result = repository.getNotes();
        view.showNotes(result);
    }

}
