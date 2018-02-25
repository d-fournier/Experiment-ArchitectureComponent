package me.dfournier.architecturecomponent.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import me.dfournier.architecturecomponent.CustomApplication
import me.dfournier.architecturecomponent.di.module.ActivityBindingModule
import me.dfournier.architecturecomponent.di.module.ApplicationModule
import me.dfournier.architecturecomponent.di.module.ViewModelModule
import javax.inject.Singleton

/**
 * Created by dfournier on 28/02/18.
 */
@Singleton
@Component(modules = [
    ApplicationModule::class,
    AndroidSupportInjectionModule::class,
    ActivityBindingModule::class,
    ViewModelModule::class
])
interface ApplicationComponent : AndroidInjector<CustomApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): ApplicationComponent.Builder

        fun build(): ApplicationComponent
    }

}