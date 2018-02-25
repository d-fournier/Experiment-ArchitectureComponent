package me.dfournier.architecturecomponent.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

/**
 * Created by dfournier on 01/03/18.
 */
@Module
abstract class ApplicationModule {

    @Binds
    abstract fun bindContext(application: Application): Context

}