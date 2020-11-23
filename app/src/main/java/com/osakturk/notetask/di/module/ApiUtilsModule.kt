package com.osakturk.notetask.di.module

import com.osakturk.notetask.di.scope.AppScope
import com.osakturk.notetask.network.AbstractApiUtils
import com.osakturk.notetask.network.ApiService
import com.osakturk.notetask.network.CustomCallbackSender
import com.osakturk.notetask.network.MockApiUtils
import dagger.Module
import dagger.Provides

/**
 * @author osakturk
 */

@Module(includes = [NetworkModule::class])
class ApiUtilsModule {
    @AppScope
    @Provides
    fun apiUtils(
            apiService: ApiService,
            customCallbackSender: CustomCallbackSender
    ): AbstractApiUtils {
        //Mock
        return MockApiUtils(apiService, customCallbackSender)
        //Live
//        return ApiUtils(apiService, customCallbackSender)
    }
}