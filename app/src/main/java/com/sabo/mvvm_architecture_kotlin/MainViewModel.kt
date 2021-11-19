package com.sabo.mvvm_architecture_kotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sabo.mvvm_architecture_kotlin.models.Note

class MainViewModel : ViewModel() {

    var mutableLive = MutableLiveData<List<Note>>()
    var noteList = arrayListOf<Note>()

    fun add(note: Note){
        noteList.add(note)
        mutableLive.value = noteList
    }

    fun remove(note: Note){
        noteList.remove(note)
        mutableLive.value = noteList
    }
}