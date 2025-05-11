package com.ndmq.buoi7

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ndmq.buoi7.data.DataSource
import com.ndmq.buoi7.data.Note
import com.ndmq.buoi7.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {

    companion object {
        const val KEY_EXTRA_NOTE = "EXTRA_NOTE"
    }


    private lateinit var binding: ActivityAddNoteBinding


    private var extraNote: Note? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        extraNote = intent.getParcelableExtra(KEY_EXTRA_NOTE)
        if (extraNote != null) {
            binding.edtTitle.setText(extraNote!!.title)
            binding.edtContent.setText(extraNote!!.content)
        }


        binding.tvSave.setOnClickListener {
            val title = binding.edtTitle.text.toString()
            val content = binding.edtContent.text.toString()
            val note = Note(title = title, content = content)
            if (extraNote != null) {
                note.id = extraNote!!.id
                DataSource.updateNote(note)
            } else {
                DataSource.addNote(note)
            }
            setResult(RESULT_OK)
            finish()
        }
    }
}