package com.example.studentregister3

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.studentregister3.databinding.ActivityMainBinding
import com.example.studentregister3.db.Student
import com.example.studentregister3.db.StudentDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: StudentViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dao = StudentDatabase.getInstance(application).studentDao()
        val factory = StudentViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory)[StudentViewModel::class.java]

        binding.apply {
            btnSave.setOnClickListener {
                viewModel.insertStudent(
                    Student(
                        0,
                        etName.text.toString(),
                        etEmail.text.toString()
                    )
                )
            }

            btnClear.setOnClickListener {
                etName.setText("")
                etEmail.setText("")
            }
        }
    }
}