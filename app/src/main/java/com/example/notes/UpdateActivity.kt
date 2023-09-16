package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notes.databinding.ActivityUpadateBinding

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpadateBinding
    private lateinit var db: NotesDatabaseHelper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpadateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDatabaseHelper(this)

        noteId = intent.getIntExtra("note_id", -1)
        if (noteId == -1 ){
            finish()
            return
        }

        val note = db.getNoteByID(noteId)
        binding.edtUpdateTitle.setText(note.title)
        binding.edtUpdateContent.setText(note.content)

        binding.updateSaveBtn.setOnClickListener {
            val newTitle = binding.edtUpdateTitle.text.toString()
            val newContent = binding.edtUpdateContent.text.toString()
            val updateNote = Note(noteId, newTitle, newContent)
            db.updateNote(updateNote)
            finish()
            Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()
        }
    }
}