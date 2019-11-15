package com.example.kotlincruddemo.domain

import com.example.kotlincruddemo.model.ForecastModel
import com.example.kotlincruddemo.model.ForecastResult
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class ForecastDataMapper {

    fun convertFromDataModel(forecastResult: ForecastResult): ForecastList {
        return ForecastList(
            forecastResult.city.name,
            forecastResult.city.country,
            convertForecastListToDomain(forecastResult.list)
        )
    }

    private fun convertForecastListToDomain(list: List<ForecastModel>): List<Forecast> {
        return list.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun convertForecastItemToDomain(forecast: ForecastModel): Forecast {
        return Forecast(
            convertDate(forecast.dt),
            forecast.weather[0].description,
            forecast.temp.max.toInt(),
            forecast.temp.min.toInt(),
            generateIconUrl(forecast.weather[0].icon)
        )
    }

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date)
    }

    private fun generateIconUrl(iconCode: String): String =
        "http://openweathermap.org/img/w/$iconCode.png"
}