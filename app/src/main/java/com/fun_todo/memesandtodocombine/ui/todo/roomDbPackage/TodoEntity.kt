package com.fun_todo.memesandtodocombine.ui.todo.roomDbPackage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class TodoEntity(
    @ColumnInfo(name = "todo") val text : String,
    @ColumnInfo(name = "amount") val amount : String){
    @PrimaryKey(autoGenerate = true) var id: Int = 0

}