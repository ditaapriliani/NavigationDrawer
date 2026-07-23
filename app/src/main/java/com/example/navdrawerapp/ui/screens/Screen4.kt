package com.example.navdrawerapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.navdrawerapp.ui.theme.NavDrawerAppTheme

@Composable
fun Screen4(
    onBackClick: () -> Unit
) {
    ScreenContent(
        title = "Screen 4",
        description = "Halaman keempat berisi informasi mengenai manajemen data dan statistik penggunaan aplikasi secara real-time.",
        onBackClick = onBackClick
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Screen4Preview() {
    NavDrawerAppTheme {
        Screen4(onBackClick = {})
    }
}
