package com.artonov.codelabroom.ui.insert

import android.app.Application
import androidx.lifecycle.ViewModel
import com.artonov.codelabroom.database.Note
import com.artonov.codelabroom.repository.NoteRepository

class NoteAddUpdateViewModel(application: Application) : ViewModel() {
    private val mNoteRepository: NoteRepository = NoteRepository(application)

    fun insert(note: Note) {
        mNoteRepository.insert(note)
    }

    fun update(note: Note) {
        mNoteRepository.update(note)
    }
    fun delete(note: Note) {
        mNoteRepository.delete(note)
    }
}