package com.example.kotlincruddemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlincruddemo.R
import com.example.kotlincruddemo.domain.Forecast
import com.example.kotlincruddemo.domain.ForecastList
import com.example.kotlincruddemo.ui.utils.ctx
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class ForecastListAdapter(val forecastList: ForecastList, val listener: (Forecast) -> Unit) :
    RecyclerView.Adapter<ForecastListAdapter.ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view: View =
            LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent, false)
        return ForecastViewHolder(view, listener)
    }

    override fun getItemCount(): Int =
        forecastList.dailyForecast.size

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.initData(forecastList[position])

    }

    class ForecastViewHolder(view: View, val itemClick: (Forecast) -> Unit) :
        RecyclerView.ViewHolder(view) {
        private val iconView = view.find<ImageView>(R.id.icon)
        private val dateView = view.find<TextView>(R.id.date)
        private val descriptionView = view.find<TextView>(R.id.description)
        private val maxTemperatureView = view.find<TextView>(R.id.maxTemperature)
        private val minTemperatureView = view.find<TextView>(R.id.minTemperature)

        fun initData(forecast: Forecast) {
            with(forecast) {
                Picasso.with(itemView.ctx).load(iconUrl).into(iconView)
                dateView.text = date
                descriptionView.text = description
                maxTemperatureView.text = "$high"
                minTemperatureView.text = "$low"
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}