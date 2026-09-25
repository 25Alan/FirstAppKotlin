package com.example.myapplication.ui.expenses

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Bolt
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.FileUpload
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.LocalGasStation
import androidx.compose.material.icons.outlined.LocalPharmacy
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Today
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.AIPrimary
import com.example.myapplication.ui.theme.AISecondary
import com.example.myapplication.ui.theme.Background
import com.example.myapplication.ui.theme.Border
import com.example.myapplication.ui.theme.FiscalGreen
import com.example.myapplication.ui.theme.MonoFamily
import com.example.myapplication.ui.theme.NegativeAlert
import com.example.myapplication.ui.theme.OnSurface
import com.example.myapplication.ui.theme.SurfaceContainer
import com.example.myapplication.ui.theme.SurfaceContainerHigh
import com.example.myapplication.ui.theme.SurfaceContainerLow
import com.example.myapplication.ui.theme.TextDisabled
import com.example.myapplication.ui.theme.TextSecondary
import com.example.myapplication.ui.theme.Warning

private data class Transaction(
    val name: String,
    val category: String,
    val time: String,
    val amount: String,
    val icon: ImageVector,
    val iconTint: Color
)

private data class TransactionGroup(
    val dateLabel: String,
    val dateIcon: ImageVector,
    val total: String,
    val transactions: List<Transaction>
)

private val transactionGroups = listOf(
    TransactionGroup("HOY, 20 SEP", Icons.Outlined.Today, "-\$90.650", listOf(
        Transaction("Supermercado Jumbo",  "Alimentación", "14:32", "-\$84.200", Icons.Outlined.ShoppingCart,    FiscalGreen),
        Transaction("Uber Viaje",          "Transporte",   "09:15", "-\$6.450",  Icons.Outlined.DirectionsCar,   AISecondary),
    )),
    TransactionGroup("AYER, 19 SEP", Icons.Outlined.History, "-\$52.800", listOf(
        Transaction("Farmacia Salcobrand", "Salud",         "18:40", "-\$15.800", Icons.Outlined.LocalPharmacy,  AIPrimary),
        Transaction("Café Starbucks",      "Alimentación",  "11:20", "-\$4.900",  Icons.Outlined.Restaurant,     FiscalGreen),
        Transaction("Cuenta Luz Enel",     "Servicios",     "08:00", "-\$32.100", Icons.Outlined.Bolt,           Warning),
    )),
    TransactionGroup("16 SEP", Icons.Outlined.CalendarToday, "-\$42.000", listOf(
        Transaction("Gasolina Shell",       "Transporte",      "19:10", "-\$28.000", Icons.Outlined.LocalGasStation, AISecondary),
        Transaction("Cine Hoyts Entradas",  "Entretenimiento", "21:00", "-\$14.000", Icons.Outlined.Movie,           Color(0xFFEC4899)),
    )),
)

private val filterChips = listOf("Todos (64)", "Alimentación", "Transporte", "Servicios", "Salud", "Ocio", "Este Mes")

@Composable
fun ExpensesScreen() {
    var selectedFilter by remember { mutableIntStateOf(0) }
    var searchText by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize().background(Background)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            Spacer(Modifier.height(12.dp))
            SearchBar(searchText, onTextChange = { searchText = it })
            Spacer(Modifier.height(10.dp))
            FilterChipsRow(selectedFilter, onSelect = { selectedFilter = it })
            Spacer(Modifier.height(12.dp))
            SummaryCard()
            Spacer(Modifier.height(16.dp))
            transactionGroups.forEachIndexed { i, group ->
                GroupHeader(group)
                Spacer(Modifier.height(6.dp))
                group.transactions.forEach { tx ->
                    TransactionRow(tx)
                    Spacer(Modifier.height(6.dp))
                }
                if (i < transactionGroups.lastIndex) Spacer(Modifier.height(10.dp))
            }
            Spacer(Modifier.height(12.dp))
            AIInsightCard()
            Spacer(Modifier.height(88.dp))
        }

        // FAB Importar
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(FiscalGreen)
                .padding(horizontal = 18.dp, vertical = 14.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Outlined.FileUpload, null, tint = Color(0xFF003912), modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(8.dp))
                Text("Importar", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF003912))
            }
        }
    }
}

@Composable
private fun SearchBar(text: String, onTextChange: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
                .background(SurfaceContainer)
                .padding(horizontal = 14.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Outlined.Search, null, tint = TextSecondary, modifier = Modifier.size(20.dp))
            Spacer(Modifier.width(10.dp))
            BasicTextField(
                value = text,
                onValueChange = onTextChange,
                modifier = Modifier.weight(1f),
                textStyle = TextStyle(color = OnSurface, fontSize = 14.sp),
                cursorBrush = SolidColor(FiscalGreen),
                singleLine = true,
                decorationBox = { inner ->
                    Box {
                        if (text.isEmpty()) Text("Buscar por comercio o nota...", fontSize = 14.sp, color = TextSecondary)
                        inner()
                    }
                }
            )
        }
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(SurfaceContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Outlined.FilterList, null, tint = TextSecondary, modifier = Modifier.size(22.dp))
        }
    }
}

@Composable
private fun FilterChipsRow(selected: Int, onSelect: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        filterChips.forEachIndexed { i, label ->
            val isSelected = selected == i
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .background(if (isSelected) FiscalGreen.copy(alpha = 0.15f) else SurfaceContainer)
                    .border(
                        1.dp,
                        if (isSelected) FiscalGreen else Border,
                        RoundedCornerShape(999.dp)
                    )
                    .padding(horizontal = 14.dp, vertical = 6.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    if (isSelected) {
                        Icon(Icons.Outlined.Check, null, tint = FiscalGreen, modifier = Modifier.size(13.dp))
                    }
                    Text(
                        label,
                        fontSize = 12.sp,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                        color = if (isSelected) FiscalGreen else TextSecondary
                    )
                }
            }
        }
    }
}

@Composable
private fun SummaryCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceContainerLow)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(SurfaceContainer)
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(Icons.Outlined.CalendarMonth, null, tint = FiscalGreen, modifier = Modifier.size(16.dp))
                Text("Septiembre 2026", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = OnSurface)
            }
            Text("KAKEIBO LEDGER", fontSize = 10.sp, fontFamily = MonoFamily, color = TextDisabled, letterSpacing = 1.sp)
        }

        Column {
            Text("Egresos computados", fontSize = 12.sp, color = TextSecondary)
            Spacer(Modifier.height(2.dp))
            Text(
                "-\$1.428.500",
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = MonoFamily,
                color = OnSurface,
                letterSpacing = (-0.5).sp
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(6.dp).background(FiscalGreen, CircleShape))
            Spacer(Modifier.width(6.dp))
            Text("64 movimientos", fontSize = 11.sp, fontFamily = MonoFamily, color = TextSecondary)
        }
    }
}

@Composable
private fun GroupHeader(group: TransactionGroup) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Icon(group.dateIcon, null, tint = TextSecondary, modifier = Modifier.size(15.dp))
            Text(group.dateLabel, fontSize = 11.sp, fontFamily = MonoFamily, fontWeight = FontWeight.SemiBold, color = TextSecondary, letterSpacing = 0.5.sp)
        }
        Text(group.total, fontSize = 11.sp, fontFamily = MonoFamily, color = TextDisabled)
    }
}

@Composable
private fun TransactionRow(tx: Transaction) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(SurfaceContainer)
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .background(tx.iconTint.copy(alpha = 0.15f), RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(tx.icon, null, tint = tx.iconTint, modifier = Modifier.size(20.dp))
        }
        Spacer(Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(tx.name, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = OnSurface, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(tx.category, fontSize = 12.sp, color = TextSecondary)
                Text("  ·  ", fontSize = 12.sp, color = TextDisabled, fontFamily = MonoFamily)
                Text(tx.time, fontSize = 11.sp, fontFamily = MonoFamily, color = TextDisabled)
            }
        }
        Spacer(Modifier.width(8.dp))
        Text(tx.amount, fontSize = 16.sp, fontWeight = FontWeight.Bold, fontFamily = MonoFamily, color = OnSurface)
        Spacer(Modifier.width(4.dp))
        IconButton(onClick = { }, modifier = Modifier.size(32.dp)) {
            Icon(Icons.Outlined.Delete, null, tint = TextDisabled, modifier = Modifier.size(18.dp))
        }
    }
}

@Composable
private fun AIInsightCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Brush.horizontalGradient(listOf(AIPrimary.copy(0.12f), AISecondary.copy(0.06f))))
            .border(1.dp, AIPrimary.copy(0.25f), RoundedCornerShape(16.dp))
            .padding(14.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(AIPrimary.copy(0.2f), RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Outlined.AutoAwesome, null, tint = AIPrimary, modifier = Modifier.size(18.dp))
        }
        Column {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("Geminis AI", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = AIPrimary)
                Text("•", fontSize = 12.sp, color = TextDisabled)
                Text("Análisis semanal", fontSize = 11.sp, fontFamily = MonoFamily, color = TextSecondary)
            }
            Spacer(Modifier.height(4.dp))
            Text(
                "Has destinado un 18% menos a ocio respecto al mes pasado. La mayor concentración sigue en alimentación básica.",
                fontSize = 13.sp,
                color = OnSurface,
                lineHeight = 19.sp
            )
        }
    }
}
