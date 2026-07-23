package com.example.navdrawerapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navdrawerapp.ui.theme.*
import kotlinx.coroutines.launch

/**
 * File ScreenContent.kt
 * =====================
 * Composable generik yang dipakai oleh Screen 1, Screen 2, dan Screen 3.
 *
 * Setiap screen memiliki pola yang sama:
 * - TopAppBar dengan tombol back arrow dan judul
 * - Area konten di tengah layar berisi teks
 *
 * Dengan membuat komponen generik ini, kita menghindari duplikasi kode.
 * Prinsip DRY (Don't Repeat Yourself)!
 *
 * Analogi: Seperti template surat. Isi dan judul berubah,
 * tapi formatnya tetap sama.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContent(
    title: String,               // Judul yang ditampilkan di TopAppBar & Content
    description: String,         // Teks deskripsi di area isi
    onBackClick: () -> Unit      // Callback ketika tombol back arrow diklik
) {
    // State untuk Snackbar
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Scaffold menyediakan struktur dasar: TopBar + Content area
    Scaffold(
        // === SNACKBAR HOST ===
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        
        // === TOP APP BAR ===
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = TopBarBackground,
                    titleContentColor = TopBarContent,
                    navigationIconContentColor = TopBarContent
                )
            )
        },
        containerColor = BackgroundScreen
    ) { paddingValues ->
        // === AREA KONTEN ===
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 1. Judul (Bold)
            Text(
                text = title,
                color = TextPrimary,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // 2. Deskripsi (Normal)
            Text(
                text = description,
                color = TextSecondary,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // 3. Button
            Button(
                onClick = {
                    // Munculkan Snackbar saat tombol diklik
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Fitur akan segera hadir",
                            duration = SnackbarDuration.Short
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary
                )
            ) {
                Text(text = "Pelajari Selengkapnya")
            }
        }
    }
}
