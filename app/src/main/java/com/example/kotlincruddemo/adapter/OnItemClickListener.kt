package com.example.kotlincruddemo.adapter

import com.example.kotlincruddemo.domain.Forecast

interface OnItemClickListener {
    operator fun invoke(forecast: Forecast)
}