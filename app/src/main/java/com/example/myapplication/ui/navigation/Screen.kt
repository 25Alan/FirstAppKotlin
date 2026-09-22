package com.example.myapplication.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.FileUpload
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Dashboard : Screen("dashboard", "Dashboard", Icons.Outlined.BarChart)
    object Expenses : Screen("expenses", "Gastos", Icons.Outlined.ReceiptLong)
    object Chat : Screen("chat", "Chat IA", Icons.Outlined.ChatBubbleOutline)
    object Import : Screen("import", "Importar", Icons.Outlined.FileUpload)

    companion object {
        val bottomNavItems = listOf(Dashboard, Expenses, Chat, Import)
    }
}
