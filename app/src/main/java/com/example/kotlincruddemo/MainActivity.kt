package com.example.kotlincruddemo

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlincruddemo.adapter.ForecastListAdapter
import com.example.kotlincruddemo.api.RequestForecastCommand
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recForecastList: RecyclerView = findViewById(R.id.forecast_list) as RecyclerView
        recForecastList.layoutManager = LinearLayoutManager(this)


        doAsync {
            val result = RequestForecastCommand("94043").execute()
            uiThread {
                //                recForecastList.adapter = ForecastListAdapter(result, object : OnItemClickListener {
//                    override fun invoke(forecast: Forecast) {
//                        toast("forecast.date --- ${forecast.date}")
//                    }
//                })
                recForecastList.adapter = ForecastListAdapter(result) { toast(it.date) }
            }
        }
    }


    fun Context.toast(msg: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, msg, duration).show()
    }
}
