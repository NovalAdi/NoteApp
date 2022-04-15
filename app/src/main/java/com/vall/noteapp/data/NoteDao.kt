package com.vall.noteapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vall.noteapp.data.model.NoteData

//perintah-perintah untuk mengaksesk sebuah data di database
@Dao
interface NoteDao {
    //    Read atau menampilkan
    @Query("SELECt * FROM todo_table ORDER BY id ASC")
    fun getDataAll(): LiveData<List<NoteData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertData(noteData: NoteData)

    @Update
    fun updatedata(noteData: NoteData)

    @Delete
    fun deleteData(noteData: NoteData)

    @Query("DELETE FROM todo_table")
    fun deleteAllData()

    @Query("SELECT * FROM todo_table WHERE title LIKE :searchQuery")
    fun seachDatabase(searchQuery: String): LiveData<List<NoteData>>

    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END")
    fun shortByHighPriority(): LiveData<List<NoteData>>

    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
    fun shortByLowPriority(): LiveData<List<NoteData>>
}