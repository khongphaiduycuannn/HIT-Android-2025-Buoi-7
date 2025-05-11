package com.ndmq.buoi7.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ndmq.buoi7.data.Note
import com.ndmq.buoi7.databinding.ItemNoteBinding

class NoteAdapter(
    val onClick: (Note) -> Unit,
    val onFavourite: (Note) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(
        private val binding: ItemNoteBinding
    ) : ViewHolder(binding.root) {

        fun bind(note: Note) {
            binding.tvTitle.text = note.title
            binding.tvContent.text = note.content
            binding.tvIsFavourite.text = if (note.favorite) "💖" else "🖤"

            binding.tvIsFavourite.setOnClickListener {
                toggleFavourite(note)
                onFavourite(note)
            }

            binding.root.setOnClickListener {
                onClick(note)
            }
        }
    }

    private val notes = mutableListOf<Note>()

    fun submitData(list: List<Note>) {
        notes.clear()
        notes.addAll(list)
        notifyDataSetChanged()
    }


    // Update ui ngay lập tức, tránh trường hợp bị delay khi load lại data
    fun toggleFavourite(note: Note) {
        val index = notes.indexOf(note)
        if (index == -1) return
        notes[index] = note.copy(favorite = !note.favorite)
        notifyItemChanged(index)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int = notes.size
}