package com.example.kotlinpro.common.base

import com.example.kotlinpro.common.resource.Resource
import kotlinx.coroutines.flow.flow
import java.lang.Exception

abstract class BaseRepository {

    protected fun <T> doRequest(request: suspend () -> T) = flow {

        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = request()))
        } catch (ioException: Exception) {
            emit(
                Resource.Error(
                    data = null, massage = ioException.localizedMessage ?: "Error Occurred"
                )
            )
        }
    }
}