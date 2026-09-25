package com.example.myapplication.ui.dashboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.DirectionsSubway
import androidx.compose.material.icons.outlined.LocalPharmacy
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.PieChart
import androidx.compose.material.icons.outlined.Receipt
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material.icons.outlined.Wallet
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.example.myapplication.ui.theme.OnSurface
import com.example.myapplication.ui.theme.SurfaceContainer
import com.example.myapplication.ui.theme.SurfaceContainerHigh
import com.example.myapplication.ui.theme.SurfaceContainerHighest
import com.example.myapplication.ui.theme.SurfaceContainerLow
import com.example.myapplication.ui.theme.TextDisabled
import com.example.myapplication.ui.theme.TextSecondary
import com.example.myapplication.ui.theme.Warning

private data class CategoryData(val name: String, val amount: String, val percentage: Int, val color: Color)
private data class MonthEntry(val label: String, val valueM: Float, val isCurrent: Boolean = false)
private data class RecentTx(
    val name: String,
    val amount: String,
    val time: String,
    val category: String,
    val catColor: Color,
    val detail: String,
    val icon: ImageVector,
    val iconTint: Color
)

private val categories = listOf(
    CategoryData("Alimentación",    "$620.000", 43, FiscalGreen),
    CategoryData("Transporte",      "$314.000", 22, AISecondary),
    CategoryData("Servicios & Hogar", "$257.000", 18, Warning),
    CategoryData("Entretenimiento", "$157.000", 11, Color(0xFFEC4899)),
    CategoryData("Salud & Otros",   "$80.500",   6, AIPrimary),
)

private val monthlyData = listOf(
    MonthEntry("May", 1.15f),
    MonthEntry("Jun", 1.30f),
    MonthEntry("Jul", 1.22f),
    MonthEntry("Ago", 1.37f),
    MonthEntry("Sep", 1.43f, true),
)

private val recentTxs = listOf(
    RecentTx("Supermercado Líder",  "-\$45.200", "Hoy, 14:32",    "Alimentación",    FiscalGreen,        "Débito *4891",   Icons.Outlined.ShoppingCart,    FiscalGreen),
    RecentTx("Metro de Santiago",   "-\$12.500", "Ayer, 18:45",   "Transporte",      AISecondary,        "Bip! QR",        Icons.Outlined.DirectionsSubway, AISecondary),
    RecentTx("Farmacia Cruz Verde", "-\$18.900", "24 Sep, 11:10", "Salud",           AIPrimary,          "Crédito *1204",  Icons.Outlined.LocalPharmacy,   AIPrimary),
    RecentTx("Netflix Mensual",     "-\$8.990",  "23 Sep, 04:00", "Entretenimiento", Color(0xFFEC4899),  "Recurrente",     Icons.Outlined.Movie,           Color(0xFFEC4899)),
)

@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(Modifier.height(12.dp))
        GreetingSection()
        Spacer(Modifier.height(16.dp))
        AIInsightCard()
        Spacer(Modifier.height(12.dp))
        TotalSpentCard()
        Spacer(Modifier.height(12.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            TopCategoryCard(Modifier.weight(1f))
            RegistrosCard(Modifier.weight(1f))
        }
        Spacer(Modifier.height(12.dp))
        CategoryDonutCard()
        Spacer(Modifier.height(12.dp))
        ExpenseEvolutionCard()
        Spacer(Modifier.height(12.dp))
        RecentActivitySection()
        Spacer(Modifier.height(12.dp))
        QuickActionCard()
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun GreetingSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Surface(
            shape = RoundedCornerShape(999.dp),
            color = SurfaceContainer
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    Modifier
                        .size(7.dp)
                        .background(FiscalGreen, CircleShape)
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    "Sincronizado",
                    fontSize = 11.sp,
                    fontFamily = MonoFamily,
                    color = TextSecondary
                )
            }
        }
        Text(
            "Sep 2026",
            fontSize = 11.sp,
            fontFamily = MonoFamily,
            fontWeight = FontWeight.SemiBold,
            color = AISecondary
        )
    }

    Spacer(Modifier.height(10.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                "Hola, Alan",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = OnSurface,
                letterSpacing = (-0.5).sp
            )
            Text(
                "Balance y distribución analítica de tus finanzas",
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary
            )
        }
        IconButton(onClick = { }) {
            Icon(Icons.Outlined.Sync, null, tint = FiscalGreen, modifier = Modifier.size(22.dp))
        }
    }
}

@Composable
private fun AIInsightCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.horizontalGradient(
                    listOf(AIPrimary.copy(alpha = 0.14f), AISecondary.copy(alpha = 0.07f))
                )
            )
            .border(1.dp, AIPrimary.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.Top) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(AIPrimary.copy(alpha = 0.2f), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Outlined.AutoAwesome, null, tint = AIPrimary, modifier = Modifier.size(22.dp))
            }
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "GEMINI INTELLIGENCE",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = MonoFamily,
                        color = AIPrimary,
                        letterSpacing = 0.5.sp
                    )
                    Spacer(Modifier.width(6.dp))
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = AIPrimary.copy(alpha = 0.2f)
                    ) {
                        Text(
                            "PROACTIVO",
                            fontSize = 9.sp,
                            fontFamily = MonoFamily,
                            color = AIPrimary,
                            modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                        )
                    }
                }
                Spacer(Modifier.height(4.dp))
                Text(
                    "Detectamos un alza del +18% en delivery de fin de semana.",
                    style = MaterialTheme.typography.bodySmall,
                    color = OnSurface
                )
                Spacer(Modifier.height(10.dp))
                TextButton(
                    onClick = { },
                    modifier = Modifier
                        .clip(RoundedCornerShape(999.dp))
                        .background(AIPrimary.copy(alpha = 0.15f))
                        .height(32.dp)
                ) {
                    Text(
                        "Conversar con IA",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = AIPrimary
                    )
                    Spacer(Modifier.width(2.dp))
                    Icon(Icons.Outlined.ChevronRight, null, tint = AIPrimary, modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}

@Composable
private fun TotalSpentCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainer)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(SurfaceContainerHigh, RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Outlined.Wallet, null, tint = FiscalGreen, modifier = Modifier.size(18.dp))
                    }
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "TOTAL GASTADO (MES)",
                        fontSize = 11.sp,
                        fontFamily = MonoFamily,
                        color = TextSecondary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Surface(
                    shape = RoundedCornerShape(999.dp),
                    color = FiscalGreen.copy(alpha = 0.12f)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Outlined.ArrowUpward, null, tint = FiscalGreen, modifier = Modifier.size(12.dp))
                        Spacer(Modifier.width(2.dp))
                        Text("+4.2%", fontSize = 11.sp, fontFamily = MonoFamily, color = FiscalGreen, fontWeight = FontWeight.SemiBold)
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.Bottom) {
                Text("$", fontSize = 14.sp, color = TextSecondary, fontWeight = FontWeight.Medium)
                Spacer(Modifier.width(2.dp))
                Text(
                    "1.428.500",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = MonoFamily,
                    color = OnSurface,
                    letterSpacing = (-1).sp
                )
                Spacer(Modifier.width(6.dp))
                Text("CLP", fontSize = 11.sp, fontFamily = MonoFamily, color = TextSecondary, modifier = Modifier.padding(bottom = 4.dp))
            }

            Text(
                "Aprox. \$1.520 USD  •  Presupuesto mensual: 78% consumido",
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary
            )

            Spacer(Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(999.dp))
                    .background(SurfaceContainerHighest)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.78f)
                        .height(6.dp)
                        .clip(RoundedCornerShape(999.dp))
                        .background(FiscalGreen)
                )
            }
        }
    }
}

@Composable
private fun TopCategoryCard(modifier: Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainer)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("MAYOR GASTO", fontSize = 10.sp, fontFamily = MonoFamily, color = TextSecondary)
                Icon(Icons.Outlined.Restaurant, null, tint = FiscalGreen, modifier = Modifier.size(18.dp))
            }
            Spacer(Modifier.height(10.dp))
            Text("Alimentación", style = MaterialTheme.typography.titleMedium, color = OnSurface, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text("\$620.000", fontSize = 18.sp, fontWeight = FontWeight.Bold, fontFamily = MonoFamily, color = FiscalGreen)
            Text("43% del total", fontSize = 11.sp, fontFamily = MonoFamily, color = TextSecondary)
        }
    }
}

@Composable
private fun RegistrosCard(modifier: Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainer)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("REGISTROS", fontSize = 10.sp, fontFamily = MonoFamily, color = TextSecondary)
                Icon(Icons.Outlined.Receipt, null, tint = AISecondary, modifier = Modifier.size(18.dp))
            }
            Spacer(Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Text("64", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = OnSurface)
                Spacer(Modifier.width(4.dp))
                Text("ítems", fontSize = 11.sp, fontFamily = MonoFamily, color = TextSecondary, modifier = Modifier.padding(bottom = 3.dp))
            }
            Text("100% categorizado", fontSize = 11.sp, fontFamily = MonoFamily, color = FiscalGreen)
        }
    }
}

@Composable
private fun CategoryDonutCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainer)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Distribución por Categoría", style = MaterialTheme.typography.titleMedium, color = OnSurface)
                    Text("5 categorías activas este mes", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                }
                Icon(Icons.Outlined.PieChart, null, tint = TextSecondary, modifier = Modifier.size(20.dp))
            }

            Spacer(Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.size(200.dp)) {
                    val strokeWidth = 38.dp.toPx()
                    val radius = (size.minDimension - strokeWidth) / 2f
                    val center = Offset(size.width / 2f, size.height / 2f)
                    val topLeft = Offset(center.x - radius, center.y - radius)
                    val arcSize = Size(radius * 2f, radius * 2f)
                    val gap = 2.5f

                    var startAngle = -90f
                    categories.forEach { cat ->
                        val sweep = (cat.percentage / 100f) * 360f
                        drawArc(
                            color = cat.color,
                            startAngle = startAngle + gap / 2f,
                            sweepAngle = sweep - gap,
                            useCenter = false,
                            topLeft = topLeft,
                            size = arcSize,
                            style = Stroke(width = strokeWidth, cap = StrokeCap.Butt)
                        )
                        startAngle += sweep
                    }
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Mayor Gasto", fontSize = 10.sp, fontFamily = MonoFamily, color = TextSecondary)
                    Text("43%", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = FiscalGreen, fontFamily = MonoFamily)
                    Text("Alimentación", fontSize = 10.sp, fontFamily = MonoFamily, color = TextSecondary)
                }
            }

            Spacer(Modifier.height(12.dp))

            categories.forEach { cat ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(SurfaceContainerLow)
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            Modifier
                                .size(10.dp)
                                .background(cat.color, CircleShape)
                        )
                        Spacer(Modifier.width(10.dp))
                        Text(cat.name, style = MaterialTheme.typography.bodyMedium, color = OnSurface)
                    }
                    Row {
                        Text(cat.amount, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold, color = OnSurface)
                        Spacer(Modifier.width(8.dp))
                        Text("${cat.percentage}%", fontSize = 11.sp, fontFamily = MonoFamily, color = TextSecondary)
                    }
                }
                Spacer(Modifier.height(4.dp))
            }
        }
    }
}

@Composable
private fun ExpenseEvolutionCard() {
    val maxVal = monthlyData.maxOf { it.valueM }
    val avgVal = monthlyData.map { it.valueM }.average().toFloat()

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainer)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Evolución de Gastos", style = MaterialTheme.typography.titleMedium, color = OnSurface)
                    Text("Últimos 5 meses de registro", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                }
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = SurfaceContainerHigh
                ) {
                    Text(
                        "Prom: \$1.29M",
                        fontSize = 11.sp,
                        fontFamily = MonoFamily,
                        color = FiscalGreen,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            val barColor = SurfaceContainerHighest
            val currentColor = FiscalGreen
            val avgLineColor = Warning.copy(alpha = 0.6f)

            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
            ) {
                val slotWidth = size.width / monthlyData.size
                val barWidth = slotWidth * 0.45f
                val chartHeight = size.height
                val avgY = chartHeight - (avgVal / maxVal) * chartHeight * 0.88f

                drawLine(
                    color = avgLineColor,
                    start = Offset(0f, avgY),
                    end = Offset(size.width, avgY),
                    strokeWidth = 1.5.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 6f))
                )

                monthlyData.forEachIndexed { i, entry ->
                    val barHeight = (entry.valueM / maxVal) * chartHeight * 0.88f
                    val x = i * slotWidth + (slotWidth - barWidth) / 2f
                    val y = chartHeight - barHeight

                    drawRoundRect(
                        color = if (entry.isCurrent) currentColor.copy(alpha = 0.85f) else barColor,
                        topLeft = Offset(x, y),
                        size = Size(barWidth, barHeight),
                        cornerRadius = CornerRadius(6.dp.toPx())
                    )

                    if (entry.isCurrent) {
                        drawRoundRect(
                            color = currentColor.copy(alpha = 0.15f),
                            topLeft = Offset(x - 4.dp.toPx(), y - 4.dp.toPx()),
                            size = Size(barWidth + 8.dp.toPx(), barHeight + 4.dp.toPx()),
                            cornerRadius = CornerRadius(8.dp.toPx())
                        )
                    }
                }
            }

            Spacer(Modifier.height(6.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                monthlyData.forEach { entry ->
                    Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        Text(
                            entry.label,
                            fontSize = 11.sp,
                            fontFamily = MonoFamily,
                            color = if (entry.isCurrent) FiscalGreen else TextSecondary,
                            fontWeight = if (entry.isCurrent) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RecentActivitySection() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text("Actividad Reciente", style = MaterialTheme.typography.titleMedium, color = OnSurface)
            Text("Últimos movimientos detectados", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
        }
        TextButton(onClick = { }) {
            Text("Ver todo en Gastos", fontSize = 12.sp, color = FiscalGreen, fontWeight = FontWeight.SemiBold)
            Icon(Icons.Outlined.ChevronRight, null, tint = FiscalGreen, modifier = Modifier.size(16.dp))
        }
    }

    Spacer(Modifier.height(8.dp))

    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        recentTxs.forEach { tx ->
            RecentTxRow(tx)
        }
    }
}

@Composable
private fun RecentTxRow(tx: RecentTx) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainer)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(SurfaceContainerHigh, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(tx.icon, null, tint = tx.iconTint, modifier = Modifier.size(22.dp))
            }
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(tx.name, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold, color = OnSurface, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(tx.time, style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                    Text("  •  ", style = MaterialTheme.typography.bodySmall, color = TextDisabled)
                    Text(tx.category, style = MaterialTheme.typography.bodySmall, color = tx.catColor)
                }
            }
            Spacer(Modifier.width(8.dp))
            Column(horizontalAlignment = Alignment.End) {
                Text(tx.amount, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = OnSurface, fontFamily = MonoFamily)
                Text(tx.detail, fontSize = 10.sp, fontFamily = MonoFamily, color = TextSecondary)
            }
        }
    }
}

@Composable
private fun QuickActionCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.horizontalGradient(listOf(SurfaceContainer, SurfaceContainerHigh))
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .background(FiscalGreen, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Outlined.Add, null, tint = Color(0xFF003912), modifier = Modifier.size(22.dp))
        }
        Spacer(Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text("¿Nuevo gasto realizado?", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold, color = OnSurface)
            Text("Ingreso manual o desde archivo", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
        }
        Icon(Icons.Outlined.ChevronRight, null, tint = TextSecondary, modifier = Modifier.size(20.dp))
    }
}
