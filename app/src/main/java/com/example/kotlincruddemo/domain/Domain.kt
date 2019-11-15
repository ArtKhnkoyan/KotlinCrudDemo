package com.example.kotlincruddemo.domain


data class ForecastList(val city: String, val country: String, val dailyForecast: List<Forecast>){
    operator fun get(position: Int): Forecast = dailyForecast[position]
}
data class Forecast(val date: String, val description: String, val high: Int, val low: Int, val iconUrl: String)