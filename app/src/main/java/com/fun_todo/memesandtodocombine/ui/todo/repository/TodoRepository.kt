package com.fun_todo.memesandtodocombine.ui.todo.repository

import androidx.lifecycle.LiveData
import com.fun_todo.memesandtodocombine.ui.todo.roomDbPackage.TodoDAO
import com.fun_todo.memesandtodocombine.ui.todo.roomDbPackage.TodoEntity

class TodoRepository(private val todoDao: TodoDAO) {

    val allTodo: LiveData<List<TodoEntity>> = todoDao.getAll()

    suspend fun insert(todo: TodoEntity) {
        todoDao.insert(todo)
    }
    suspend fun delete(todo: TodoEntity){
        todoDao.delete(todo)
    }
    fun deleteAll(){
        todoDao.deleteAll()
    }

}