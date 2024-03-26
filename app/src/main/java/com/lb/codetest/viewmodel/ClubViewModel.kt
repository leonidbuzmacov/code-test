package com.lb.codetest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lb.codetest.models.Squad
import com.lb.codetest.repository.ClubRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ClubViewModel(
    private val clubRepository: ClubRepository = ClubRepository()
) : ViewModel() {

    private val _squad: MutableStateFlow<Squad> = MutableStateFlow(Squad())
    val squadFlow: StateFlow<Squad> = _squad.asStateFlow()

    init {
        viewModelScope.launch {
            val squad = clubRepository.getSquad()
            _squad.value = squad
        }

    }
}