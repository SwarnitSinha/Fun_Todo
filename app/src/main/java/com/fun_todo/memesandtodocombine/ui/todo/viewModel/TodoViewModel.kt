package com.fun_todo.memesandtodocombine.ui.todo.viewModel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.bumptech.glide.Glide.init
import com.fun_todo.memesandtodocombine.ui.todo.repository.TodoRepository
import com.fun_todo.memesandtodocombine.ui.todo.roomDbPackage.TodoDatabase
import com.fun_todo.memesandtodocombine.ui.todo.roomDbPackage.TodoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    // - Repository is completely separated from the UI through the ViewModel.
    private val repository: TodoRepository
    val allTodo: LiveData<List<TodoEntity>>

    init {
        val dao = TodoDatabase.getDatabase(application).getTodoDao()
        repository = TodoRepository(dao)
        allTodo = repository.allTodo
    }

    fun insert(todo: TodoEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(todo)
    }

    fun delete(todo: TodoEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(todo)
    }
    fun deleteAll() = viewModelScope.launch(Dispatchers.IO){
        Log.d("TAG","Function is called from view model")
        repository.deleteAll()
    }
}