package com.sabo.mvvm_architecture_kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sabo.mvvm_architecture_kotlin.MainViewModel
import com.sabo.mvvm_architecture_kotlin.R
import com.sabo.mvvm_architecture_kotlin.models.Note

class NoteAdapter(val viewModel: MainViewModel, private val noteList: List<Note>) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        fun bind(note: Note) {
            binding.findViewById<TextView>(R.id.tvTitle).text = note.title

            binding.findViewById<ImageButton>(R.id.ibDelete).setOnClickListener {
                viewModel.remove(note)
                notifyItemRemoved(noteList.indexOf(note))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holderNote: NoteViewHolder, position: Int) {
        holderNote.bind(noteList[position])
    }

    override fun getItemCount(): Int {
        return noteList.size
    }
}