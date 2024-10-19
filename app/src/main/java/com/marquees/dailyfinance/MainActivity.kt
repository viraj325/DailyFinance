package com.marquees.dailyfinance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.marquees.dailyfinance.operations.FinancialDataParser
import com.marquees.dailyfinance.ui.theme.DailyFinanceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyFinanceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    FinancialUI()
                }
            }
        }
    }
}

@Composable
fun FinancialUI() {
    val parsedFinancialData = FinancialDataParser()
        .parseFinancialDataFromFile(LocalContext.current, "FinancialData.json")

    Column {
        HorizontalDivider()
        LazyColumn {
            items(parsedFinancialData.size) { index ->
                FinancialCardUI(parsedFinancialData[index].name)
            }
        }
    }
}

@Composable
private fun FinancialCardUI(name: String) {
    Text(name)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    DailyFinanceTheme {
        FinancialUI()
    }
}