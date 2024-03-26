package com.lb.codetest.repository

import com.lb.codetest.datasource.ClubDataSource
import com.lb.codetest.models.Squad

class ClubRepository(private val clubDataSource: ClubDataSource = ClubDataSource()) {
    suspend fun getSquad(): Squad = clubDataSource.getSquad()
}