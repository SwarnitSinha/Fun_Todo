package com.example.memesandtodocombine.ui.todo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.example.memesandtodocombine.R
import com.example.memesandtodocombine.ui.todo.roomDbPackage.TodoEntity

class RVAdapter(private val context: Context?, private val listener: ITodoRVAdapter) : RecyclerView.Adapter<RVAdapter.TodoViewHolder>() {

    private val allTodo = ArrayList<TodoEntity>()

    inner class TodoViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val textEntered: TextView = itemView.findViewById(R.id.todo_text)
        val amountEntered: TextView = itemView.findViewById(R.id.todo_amount)
        val deleteBtn: ImageView = itemView.findViewById(R.id.del_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {

        val viewHolder = TodoViewHolder(LayoutInflater.from(context).inflate(R.layout.todo_item_view,parent,false))

        //There is only one delete btn in recycler view
        viewHolder.deleteBtn.setOnClickListener {
            listener.onItemClicked(allTodo[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = allTodo[position]
        holder.textEntered.text = currentTodo.text

        holder.amountEntered.text = currentTodo.amount

    }

    override fun getItemCount(): Int {
        return allTodo.size
    }
    fun updateList(newList : List<TodoEntity>){
        allTodo.clear()
        allTodo.addAll(newList)
        notifyDataSetChanged()
    }
}

//for handling clicks

interface ITodoRVAdapter{
    fun onItemClicked(todo : TodoEntity)
}