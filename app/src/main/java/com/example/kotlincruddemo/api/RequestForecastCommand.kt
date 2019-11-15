package com.example.kotlincruddemo.api

import com.example.kotlincruddemo.domain.Command
import com.example.kotlincruddemo.domain.ForecastDataMapper
import com.example.kotlincruddemo.domain.ForecastList

class RequestForecastCommand(val zipCode: String) : Command<ForecastList> {

    override fun execute(): ForecastList {
        val forecastRequest = Request(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}