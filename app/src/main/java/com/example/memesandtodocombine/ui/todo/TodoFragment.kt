package com.example.memesandtodocombine.ui.todo

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.memesandtodocombine.databinding.FragmentTodoBinding
import com.example.memesandtodocombine.ui.meme.VolleySingleton.Companion.getInstance
import com.example.memesandtodocombine.ui.todo.roomDbPackage.TodoEntity
import com.example.memesandtodocombine.ui.todo.viewModel.TodoViewModel

// TODO: 26/01/22 Connect view model to this fragment
// TODO: 26/01/22 Connect adapter to fragment
// TODO: 26/01/22 give data to adapter from view model

class TodoFragment : Fragment() , ITodoRVAdapter {


    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel : TodoViewModel
//    var viewModel = ViewModelProvider(this).get(TodoViewModel::class.java)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTodoBinding.inflate(inflater,container,false)
        val view = binding.root
        Log.d("me","Fragment created")

        val saveBtn: Button = binding.saveBtn
        saveBtn.setOnClickListener { saveData() }

        val clearAll: Button = binding.clearAllBtn
        clearAll.setOnClickListener { clearAllData() }

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = RVAdapter(context,this)
        recyclerView.adapter = adapter
        Log.d("me","adapter created")
        //initialize view model now

//        viewModel = ViewModelProvider
//        viewModel = ViewModelProvider(context,ViewModelProvider.Factory.getInstace(application)).get(TodoViewModel)

//        viewModel = ViewModelProvider(context,
//        ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(TodoViewModel))

        viewModel = ViewModelProvider(this).get(TodoViewModel::class.java)


        viewModel.allTodo.observe(viewLifecycleOwner, Observer { list->
            list?.let{
                Log.d("model","viewModel is created")
                adapter.updateList(it)
            }
        })
        return view
    }

    private fun clearAllData() {
        Toast.makeText(context, "All Cleared", Toast.LENGTH_SHORT).show()
        viewModel.deleteAll()
    }


    private fun saveData() {
//This is working well
        val todoText = binding.textEnter.text.toString()
        val todoAmount = binding.amountEnter.text.toString()
        if(todoText.isNotEmpty()){
            Toast.makeText(context, "$todoText is saved", Toast.LENGTH_SHORT).show()
            viewModel.insert(TodoEntity(todoText,todoAmount))
            binding.textEnter.setText("")
            binding.amountEnter.setText("")
        }
        binding.textEnter.requestFocus()
    }
//
    override fun onItemClicked(todo: TodoEntity) {
        viewModel.delete(todo)
        Toast.makeText(context, "${todo.text} is deleted", Toast.LENGTH_SHORT).show()
    }

}