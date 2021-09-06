package com.example.appnotes.domain;

import com.example.appnotes.R;

import java.util.ArrayList;
import java.util.List;

public class DeviceNotesRepository implements NotesRepository{
    @Override
    public List<Note> getNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(new Note(R.string.apple,R.string.appleDesc,R.string.appleDate));
        notes.add(new Note(R.string.orange,R.string.orangeDesc,R.string.orangeDate));
        notes.add(new Note(R.string.banana,R.string.bananaDesc,R.string.bananaDate));
        return notes;
    }
}
