package me.dfournier.architecturecomponent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val type = intent.getIntExtra("EXTRA_TYPE", 1)
            val fragment = when (type) {
                1 -> me.dfournier.architecturecomponent.movie.presentation.list.refresh.MovieListFragment.newInstance()
                2 -> me.dfournier.architecturecomponent.movie.presentation.list.db.MovieListFragment.newInstance()
                else -> me.dfournier.architecturecomponent.movie.presentation.list.refresh.MovieListFragment.newInstance()
            }

            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit()
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    companion object {
        fun getRefreshIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java).apply {
                putExtra("EXTRA_TYPE", 1)
            }
        }

        fun getDbIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java).apply {
                putExtra("EXTRA_TYPE", 2)
            }
        }
    }

}
