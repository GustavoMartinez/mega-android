package com.app.megaandroid.viewmodel

import androidx.lifecycle.ViewModel
import com.app.megaandroid.repository.ContenidoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContenidosViewModel @Inject constructor (
    private val repository: ContenidoRepository
) : ViewModel() {

}