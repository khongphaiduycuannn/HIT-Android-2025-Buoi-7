package com.ndmq.buoi7

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ndmq.buoi7.adapter.NoteAdapter
import com.ndmq.buoi7.data.DataSource
import com.ndmq.buoi7.databinding.ActivityAllNotesBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AllNotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllNotesBinding


    private var getNotesJob: Job? = null


    // Kiểm tra khi nào add note/update note thành công thì mới load lại danh sách
    private val updateNoteLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            getNotes()
        }
    }


    private val noteAdapter = NoteAdapter(
        onClick = {
            val intent = Intent(this, AddNoteActivity::class.java)
            intent.putExtra(AddNoteActivity.KEY_EXTRA_NOTE, it)
            updateNoteLauncher.launch(intent)
        },
        onFavourite = { note ->
            if (note.favorite) {
                DataSource.unFavoriteNote(note.id)
            } else {
                DataSource.favoriteNote(note.id)
            }
        }
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvNotes.layoutManager = LinearLayoutManager(this)
        binding.rvNotes.adapter = noteAdapter
        getNotes()

        binding.tvAddNote.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            updateNoteLauncher.launch(intent)
        }
    }


    private fun getNotes() {
        getNotesJob?.cancel()
        getNotesJob = lifecycleScope.launch {
            val notes = DataSource.getAllNotes()
            noteAdapter.submitData(notes)
        }
    }
}