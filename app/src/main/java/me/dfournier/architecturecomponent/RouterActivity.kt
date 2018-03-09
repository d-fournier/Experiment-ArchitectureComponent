package me.dfournier.architecturecomponent

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_router.*

class RouterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_router)

        refresh.setOnClickListener {
            startActivity(MainActivity.getRefreshIntent(this))
        }
        db.setOnClickListener {
            startActivity(MainActivity.getDbIntent(this))
        }
    }

}
