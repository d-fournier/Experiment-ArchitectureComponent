package me.dfournier.architecturecomponent

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import me.dfournier.architecturecomponent.di.DaggerApplicationComponent
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by dfournier on 28/02/18.
 */
class CustomApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>


    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.builder()
                .application(this)
                .build()
                .inject(this)

        Timber.plant(Timber.DebugTree())
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

}