package com.marquees.dailyfinance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        .parseFinancialDataFromFile("FinancialData.json")

    Column {
        HorizontalDivider()
        LazyColumn {
            items(parsedFinancialData.size) { index ->
                // When an item's [name] updates, the adapter for that item
                // will recompose. This will not recompose when [header] changes
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