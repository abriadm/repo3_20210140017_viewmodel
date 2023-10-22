@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.example.form_application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.form_application.Data.DataForm
import com.example.form_application.ui.theme.Form_applicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Form_applicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun TampilText(modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 30.dp, vertical = 25.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Form input", fontSize = 35.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(5.dp))
        FormText()
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FormText() {
    // Text field untuk nama
    var textForm by remember { mutableStateOf("") }
    // Phone field untuk nomor hp
    var phoneForm by remember { mutableStateOf("") }

    val context = LocalContext.current
    val dataForm: DataForm
    // data dari file TryViewModel.kt
    val uiState by TryViewModel.uiState.CollectAsState()
    dataForm = uiState

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Untuk text nama lengkap
        OutlinedTextField(
            value = textForm,
            singleLine = true,
            onValueChange = { textForm = it },
            label = { Text(text = "Nama Lengkap") },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
        )
        // Untuk nomor hp
        OutlinedTextField(
            value = phoneForm,
            singleLine = true,
            onValueChange = { phoneForm = it },
            label = { Text(text = "Telpon") },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
        )
        // masalah di file DataSource [110:111]
        SelectJK(options = jenis.map { id -> context.resources.getString(id) },
            onSelectionChanged = {TryViewModel.setJenisK(it)})
        // masalah di file TryViewModel [113]
        Button(onClick = {TryViewModel.insertData(textNama, textTlp, dataForm.gender)},
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "Submit",
                fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.padding(10.dp))
        TextHasil(nama = "Andi", telpon = "123", JenisKelamin = "Laki-Laki")
    }
}

@Composable
fun SelectJK(options: List<String>, onSelectionChanged: (String) -> Unit = {}) {
    var selectedValue by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        options.forEach {  item ->
            Row(modifier = Modifier.selectable(
                selected = selectedValue == item,
                onClick = {
                    selectedValue = item
                    onSelectionChanged(item)
                }
            ),
                horizontalArrangement = Arrangement.Center
                ) {
                RadioButton(selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                )
                Text(item)
            }
        }
    }
}

// Function untuk menampilkan setelah text di input oleh user
@Composable
fun TextHasil(nama: String, telpon: String, JenisKelamin: String) {
    ElevatedCard(elevation = CardDefaults.cardElevation(
        defaultElevation = 6.dp
    ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)) {
        Text(text = "Nama : " + nama,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp))
        Text(text = "Telepon : " + telpon,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp))
        Text(text = "Jenis Kelamin : " + JenisKelamin,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Form_applicationTheme {
        TampilText()
    }
}