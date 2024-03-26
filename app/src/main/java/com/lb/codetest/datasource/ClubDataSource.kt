package com.lb.codetest.datasource

import com.lb.codetest.api.ClubApi
import com.lb.codetest.models.Squad
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ClubDataSource(
    private val clubApi: ClubApi = ClubApi(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getSquad(): Squad =
        withContext(dispatcher) {
            clubApi.getSquad()
        }
}