package me.dfournier.architecturecomponent.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.dfournier.architecturecomponent.MainActivity
import me.dfournier.architecturecomponent.di.annotation.ActivityScoped
import me.dfournier.architecturecomponent.movie.MovieModule

/**
 * Created by dfournier on 28/02/18.
 */
@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [MovieModule::class])
    abstract fun mainActivity(): MainActivity

}