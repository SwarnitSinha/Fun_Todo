package com.example.memesandtodocombine.ui.todo.roomDbPackage

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todo: TodoEntity)
    @Delete
    suspend fun delete(todo: TodoEntity)

    @Query("Delete from todo_table")
    fun deleteAll()

    @Query("Select * from todo_table order by id ASC")
    fun getAll(): LiveData<List<TodoEntity>>
}