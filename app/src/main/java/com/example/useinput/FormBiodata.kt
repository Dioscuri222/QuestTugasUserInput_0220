package com.example.useinput

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.reflect.Modifier

@Composable
fun FormBiodata(modifier: Modifier = Modifier) {
    // Form BIODATA
    var namaDepan by remember { mutableStateOf("") }
    var namaBelakang by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var tglLahir by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }

    var isAndroidChecked by remember { mutableStateOf(false) }
    var isUiUxChecked by remember { mutableStateOf(false) }
    var isWebChecked by remember { mutableStateOf(false) }

    // SNACKBAR STATE & COROUTINE
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Template String untuk preview dari strings.xml
    val emptyDash = stringResource(id = R.string.empty_value)
    val androidSkill = stringResource(id = R.string.skill_android)
    val uiuxSkill = stringResource(id = R.string.skill_uiux)
    val webSkill = stringResource(id = R.string.skill_web)
    val previewFormat = stringResource(id = R.string.preview_biodata)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // 1. Gambar Latar Belakang (Header)
            Image(
                painter = painterResource(id = R.drawable.bg_header),
                contentDescription = stringResource(id = R.string.desc_bg_header),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),
                contentScale = ContentScale.Crop
            )

            // 2. Card/Surface Form yang Mengapung (Floating) di atas Background
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 220.dp),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                color = colorResource(id = R.color.bg_card_biodata)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 24.dp, vertical = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // --- Judul & Subjudul ---
                    Text(
                        text = stringResource(id = R.string.title_form_biodata),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.purple_title)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = stringResource(id = R.string.subtitle_form_biodata),
                        fontSize = 13.sp,
                        color = colorResource(id = R.color.text_subtitle_gray)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // --- Baris 1: Nama Depan & Nama Belakang ---
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedTextField(
                            value = namaDepan,
                            onValueChange = { namaDepan = it },
                            label = { Text(text = stringResource(id = R.string.label_nama_depan)) },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(16.dp)
                        )
                        OutlinedTextField(
                            value = namaBelakang,
                            onValueChange = { namaBelakang = it },
                            label = { Text(text = stringResource(id = R.string.label_nama_belakang)) },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // --- Baris 2: Email ---
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(text = stringResource(id = R.string.label_email)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // --- Baris 3: Tgl Lahir & Experience ---
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedTextField(
                            value = tglLahir,
                            onValueChange = { tglLahir = it },
                            label = { Text(text = stringResource(id = R.string.label_tgl_lahir)) },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = stringResource(id = R.string.desc_icon_calendar)
                                )
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(16.dp)
                        )
                        OutlinedTextField(
                            value = experience,
                            onValueChange = { experience = it },
                            label = { Text(text = stringResource(id = R.string.label_experience)) },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = stringResource(id = R.string.desc_icon_dropdown)
                                )
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // --- Section Skill ---
                    Text(
                        text = stringResource(id = R.string.title_pilih_skill),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.black),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Checkbox Android
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = isAndroidChecked,
                                onCheckedChange = { isAndroidChecked = it }
                            )
                            Text(
                                text = stringResource(id = R.string.skill_android),
                                fontSize = 13.sp
                            )
                        }

                        // Checkbox UI/UX
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = isUiUxChecked,
                                onCheckedChange = { isUiUxChecked = it }
                            )
                            Text(
                                text = stringResource(id = R.string.skill_uiux),
                                fontSize = 13.sp
                            )
                        }

                        // Checkbox Web
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = isWebChecked,
                                onCheckedChange = { isWebChecked = it }
                            )
                            Text(
                                text = stringResource(id = R.string.skill_web),
                                fontSize = 13.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(28.dp))

                    // --- Tombol PROCEED dengan Fitur Snackbar 5 Detik ---
                    Button(
                        onClick = {
                            // Mengumpulkan skill yang dipilih
                            val selectedSkillsList = mutableListOf<String>()
                            if (isAndroidChecked) selectedSkillsList.add(androidSkill)
                            if (isUiUxChecked) selectedSkillsList.add(uiuxSkill)
                            if (isWebChecked) selectedSkillsList.add(webSkill)

                            val skillText = if (selectedSkillsList.isNotEmpty()) {
                                selectedSkillsList.joinToString(", ")
                            } else {
                                emptyDash
                            }

                            val summaryMessage = String.format(
                                previewFormat,
                                namaDepan.ifEmpty { emptyDash },
                                namaBelakang.ifEmpty { emptyDash },
                                email.ifEmpty { emptyDash },
                                tglLahir.ifEmpty { emptyDash },
                                experience.ifEmpty { emptyDash },
                                skillText
                            )

                            // Tampilkan Snackbar selama tepat 5 detik
                            coroutineScope.launch {
                                val snackbarJob = launch {
                                    snackbarHostState.showSnackbar(message = summaryMessage)
                                }
                                delay(5000) // 5 Detik
                                snackbarJob.cancel()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.btn_proceed_bg)
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.btn_proceed),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.btn_proceed_text)
                        )
                }
            }
        }
    }
}