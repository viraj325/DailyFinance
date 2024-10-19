package com.marquees.dailyfinance

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marquees.dailyfinance.model.FinancialModel
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
        Text(
            text = "Welcome to Daily Finance",
            fontStyle = MaterialTheme.typography.displayLarge.fontStyle,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )

        LazyColumn {
            items(parsedFinancialData.size) { index ->
                FinancialCardUI(parsedFinancialData[index])
            }
        }
    }
}

@Composable
private fun FinancialCardUI(item: FinancialModel) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = item.name,
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )

            Text(
                text = item.description,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )

            SuggestionChip(
                modifier = Modifier
                    .padding(16.dp),
                onClick = { Log.d("Suggestion chip", "hello world") },
                label = {
                    Text(item.type)
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    DailyFinanceTheme {
        FinancialUI()
    }
}