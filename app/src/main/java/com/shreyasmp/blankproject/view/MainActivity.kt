package com.shreyasmp.blankproject.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shreyasmp.blankproject.InstrumentModel
import com.shreyasmp.blankproject.InstrumentViewModel
import com.shreyasmp.blankproject.ui.theme.BlankProjectTheme


class MainActivity : ComponentActivity() {

    private val viewModel: InstrumentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BlankProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    SearchViewFinder(viewModel.instrumentList)
                }
            }
        }
    }
}

@Composable
fun SearchViewFinder(instList: List<InstrumentModel>) {

    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(""))
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(value = textFieldValue, onValueChange = { newValue ->
            textFieldValue = newValue
        },
            maxLines = 1,
            placeholder = {
                Text(text = "Enter type")
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var filteredList = listOf<InstrumentModel>()

            instList.let {
                filteredList = it.filter { instrument ->
                    instrument.instrument_type == textFieldValue.text
                }
            }
            items(filteredList) { instrument ->
                instrument.name?.let { name ->
                    Text(text = name)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BlankProjectTheme {

    }
}