package com.example.studentregister3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentregister3.db.Student
import com.example.studentregister3.db.StudentDao
import kotlinx.coroutines.launch

class StudentViewModel(private val dao:StudentDao): ViewModel() {
    var students = dao.getAllStudents()

    fun insertStudent(student: Student) = viewModelScope.launch {
        dao.insertStudent(student)
    }

    fun updateStudent(student: Student) = viewModelScope.launch {
        dao.updateStudent(student)
    }

    fun deleteStudent(student: Student) = viewModelScope.launch {
        dao.deleteStudent(student)
    }
}