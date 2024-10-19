package com.marquees.dailyfinance.operations

import android.util.Log
import com.google.gson.Gson
import com.marquees.dailyfinance.model.FinancialModel

class FinancialDataParser {
    fun parseFinancialData(jsonData: String): List<FinancialModel> {
        val gson = Gson()
        val parsedData = gson.fromJson(jsonData, Array<FinancialModel>::class.java).toList()
        Log.d("FinancialDataParser", "parseFinancialData: $parsedData")
        return parsedData
    }

    fun parseFinancialDataFromFile(fileName: String): List<FinancialModel> {
        // parse from FinancialData.json
        val jsonData = this::class.java.classLoader?.getResource(fileName)?.readText()
        val gson = Gson()
        val parsedData = gson.fromJson(jsonData, Array<FinancialModel>::class.java).toList()
        Log.d("FinancialDataParser", "parseFinancialData: $parsedData")
        return parsedData
    }
}