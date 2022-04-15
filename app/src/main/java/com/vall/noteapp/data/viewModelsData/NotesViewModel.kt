package com.vall.noteapp.data.viewModelsData

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.vall.noteapp.data.NoteDatabase
import com.vall.noteapp.data.model.NoteData
import com.vall.noteapp.data.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val noteDao = NoteDatabase.getDatabse(application).noteDao()
    private val repository: NoteRepository
    val getAllData: LiveData<List<NoteData>>
    val shortByHighPriority: LiveData<List<NoteData>>
    val shortByLowPriority: LiveData<List<NoteData>>

    init {
        repository = NoteRepository(noteDao)
        getAllData = repository.getAllData
        shortByHighPriority = repository.shortByHighPriority
        shortByLowPriority = repository.shortByLowPriority
    }

    fun insertData(noteData: NoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(noteData)
        }
    }

    fun updateData(noteData: NoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDate(noteData)
        }
    }

    fun delateData(noteData: NoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteData(noteData)
        }
    }

    fun deleteAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllDAta()
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<NoteData>> {
        return repository.searchQuery(searchQuery)
    }
}