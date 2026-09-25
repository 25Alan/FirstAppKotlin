package com.example.myapplication.ui.chat

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
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Autorenew
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.CompareArrows
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.PieChart
import androidx.compose.material.icons.outlined.Receipt
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Storage
import androidx.compose.material.icons.outlined.Store
import androidx.compose.material.icons.outlined.ThumbDown
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.AIPrimary
import com.example.myapplication.ui.theme.AISecondary
import com.example.myapplication.ui.theme.Background
import com.example.myapplication.ui.theme.FiscalGreen
import com.example.myapplication.ui.theme.MonoFamily
import com.example.myapplication.ui.theme.OnSurface
import com.example.myapplication.ui.theme.SecondaryContainer
import com.example.myapplication.ui.theme.SurfaceContainer
import com.example.myapplication.ui.theme.SurfaceContainerHigh
import com.example.myapplication.ui.theme.SurfaceContainerHighest
import com.example.myapplication.ui.theme.SurfaceContainerLow
import com.example.myapplication.ui.theme.SurfaceContainerLowest
import com.example.myapplication.ui.theme.Tertiary
import com.example.myapplication.ui.theme.TextDisabled
import com.example.myapplication.ui.theme.TextSecondary

@Composable
fun ChatScreen() {
    Column(modifier = Modifier.fillMaxSize().background(Background)) {
        AssistantHeaderBar()
        QuickPromptsRow()
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            TimestampDivider()
            Spacer(Modifier.height(12.dp))
            UserBubble("¿Cuánto llevo gastado en comida este mes y cuál fue la compra más cara?", "16:42")
            Spacer(Modifier.height(12.dp))
            AIResponseBubble()
            Spacer(Modifier.height(12.dp))
            StreamingIndicator()
            Spacer(Modifier.height(8.dp))
        }
        InputDock()
    }
}

@Composable
private fun AssistantHeaderBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(SurfaceContainerLow)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        Brush.linearGradient(listOf(SecondaryContainer, Tertiary)),
                        RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Outlined.AutoAwesome, null, tint = Color(0xFF0A0E14), modifier = Modifier.size(20.dp))
            }
            Spacer(Modifier.width(10.dp))
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Gemini 2.0 Flash", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = OnSurface)
                    Spacer(Modifier.width(6.dp))
                    Surface(shape = RoundedCornerShape(999.dp), color = AIPrimary.copy(alpha = 0.2f)) {
                        Text(
                            "FINANCE PRO",
                            fontSize = 9.sp,
                            fontFamily = MonoFamily,
                            fontWeight = FontWeight.Bold,
                            color = AIPrimary,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(6.dp).background(FiscalGreen, CircleShape))
                    Spacer(Modifier.width(5.dp))
                    Text("Listo para responder", fontSize = 11.sp, fontFamily = MonoFamily, color = FiscalGreen)
                }
            }
        }
        Surface(shape = RoundedCornerShape(999.dp), color = SurfaceContainerHigh) {
            Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Outlined.Storage, null, tint = AISecondary, modifier = Modifier.size(14.dp))
                Spacer(Modifier.width(5.dp))
                Text("64 gastos (Sep '26)", fontSize = 11.sp, fontFamily = MonoFamily, color = OnSurface)
            }
        }
    }
}

@Composable
private fun QuickPromptsRow() {
    val prompts = listOf(
        Pair(Icons.Outlined.PieChart,      "¿En qué gasté más este mes?"),
        Pair(Icons.Outlined.Restaurant,    "¿Cuánto gasté en comida?"),
        Pair(Icons.Outlined.CompareArrows, "Comparar con mes anterior"),
        Pair(Icons.Outlined.Autorenew,     "Gastos recurrentes"),
    )
    val tints = listOf(FiscalGreen, AISecondary, AIPrimary, FiscalGreen)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        prompts.forEachIndexed { i, (icon, label) ->
            Surface(
                shape = RoundedCornerShape(999.dp),
                color = SurfaceContainer
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(icon, null, tint = tints[i], modifier = Modifier.size(15.dp))
                    Spacer(Modifier.width(6.dp))
                    Text(label, fontSize = 13.sp, color = OnSurface)
                }
            }
        }
    }
}

@Composable
private fun TimestampDivider() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Surface(shape = RoundedCornerShape(999.dp), color = SurfaceContainer) {
            Text(
                "Hoy, 16:42  •  Datos de Sep 2026",
                fontSize = 11.sp,
                fontFamily = MonoFamily,
                color = TextSecondary,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
            )
        }
    }
}

@Composable
private fun UserBubble(message: String, time: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        Box(
            modifier = Modifier
                .background(
                    SurfaceContainerHigh,
                    RoundedCornerShape(topStart = 18.dp, topEnd = 4.dp, bottomStart = 18.dp, bottomEnd = 18.dp)
                )
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(message, fontSize = 14.sp, color = OnSurface, lineHeight = 20.sp)
        }
        Spacer(Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(time, fontSize = 11.sp, fontFamily = MonoFamily, color = TextSecondary)
            Spacer(Modifier.width(4.dp))
            Icon(Icons.Outlined.Send, null, tint = FiscalGreen, modifier = Modifier.size(12.dp))
        }
    }
}

@Composable
private fun AIResponseBubble() {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .border(
                    1.5.dp,
                    Brush.linearGradient(listOf(SecondaryContainer, Tertiary, FiscalGreen)),
                    CircleShape
                )
                .padding(2.dp)
                .background(SurfaceContainerLowest, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Outlined.AutoAwesome, null, tint = AIPrimary, modifier = Modifier.size(16.dp))
        }
        Spacer(Modifier.width(10.dp))
        Column(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        SurfaceContainerLow,
                        RoundedCornerShape(topStart = 4.dp, topEnd = 18.dp, bottomStart = 18.dp, bottomEnd = 18.dp)
                    )
                    .padding(16.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        buildAnnotatedString {
                            append("En ")
                            withStyle(SpanStyle(fontWeight = FontWeight.SemiBold, color = OnSurface)) { append("Septiembre 2026") }
                            append(" has gastado un total de ")
                            withStyle(SpanStyle(fontWeight = FontWeight.Bold, color = FiscalGreen, fontSize = 17.sp)) { append("\$620.000 CLP") }
                            append(" en la categoría ")
                            withStyle(SpanStyle(fontWeight = FontWeight.SemiBold, color = OnSurface)) { append("Alimentación") }
                            append(".")
                        },
                        fontSize = 14.sp,
                        color = OnSurface,
                        lineHeight = 21.sp
                    )
                    Text(
                        buildAnnotatedString {
                            append("Esto representa el ")
                            withStyle(SpanStyle(fontWeight = FontWeight.SemiBold, color = AIPrimary)) { append("43%") }
                            append(" de tus egresos totales registrados hasta la fecha.")
                        },
                        fontSize = 13.sp,
                        color = TextSecondary,
                        lineHeight = 19.sp
                    )

                    BreakdownCard()
                    HighlightCard()
                    TipCard()

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        ActionChip(Icons.Outlined.AddCircle, FiscalGreen, "Crear presupuesto de comida")
                        ActionChip(Icons.Outlined.Receipt, TextSecondary, "Ver las 14 cafeterías")
                    }
                }
            }

            Spacer(Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Outlined.ContentCopy, null, tint = TextSecondary, modifier = Modifier.size(13.dp))
                        Spacer(Modifier.width(3.dp))
                        Text("Copiar", fontSize = 11.sp, fontFamily = MonoFamily, color = TextSecondary)
                    }
                    Icon(Icons.Outlined.ThumbUp, null, tint = TextSecondary, modifier = Modifier.size(14.dp))
                    Icon(Icons.Outlined.ThumbDown, null, tint = TextSecondary, modifier = Modifier.size(14.dp))
                }
                Text("Gemini Flash  •  240ms", fontSize = 10.sp, fontFamily = MonoFamily, color = TextDisabled)
            }
        }
    }
}

@Composable
private fun BreakdownCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(SurfaceContainer, RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(8.dp).background(FiscalGreen, CircleShape))
                Spacer(Modifier.width(6.dp))
                Text("Supermercados", fontSize = 11.sp, fontFamily = MonoFamily, color = TextSecondary)
            }
            Text("\$488.000 CLP (78.7%)", fontSize = 11.sp, fontFamily = MonoFamily, color = OnSurface, fontWeight = FontWeight.SemiBold)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(999.dp))
                .background(SurfaceContainerHighest)
        ) {
            Box(Modifier.weight(78.7f).fillMaxSize().background(FiscalGreen))
            Box(Modifier.weight(13.5f).fillMaxSize().background(AISecondary))
            Box(Modifier.weight(7.8f).fillMaxSize().background(AIPrimary))
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Delivery: \$83.500", fontSize = 11.sp, fontFamily = MonoFamily, color = TextSecondary)
            Text("Cafeterías: \$48.500", fontSize = 11.sp, fontFamily = MonoFamily, color = TextSecondary)
        }
    }
}

@Composable
private fun HighlightCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(SurfaceContainerHigh, RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Outlined.ShoppingCart, null, tint = AISecondary, modifier = Modifier.size(15.dp))
            Spacer(Modifier.width(5.dp))
            Text("COMPRA MÁS CARA EN ALIMENTACIÓN", fontSize = 10.sp, fontFamily = MonoFamily, color = AISecondary, letterSpacing = 0.5.sp)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier.size(36.dp).background(SurfaceContainer, RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Outlined.Store, null, tint = FiscalGreen, modifier = Modifier.size(18.dp))
                }
                Spacer(Modifier.width(8.dp))
                Column {
                    Text("Supermercado Jumbo", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = OnSurface)
                    Text("20 de Sep  •  Tarjeta Crédito", fontSize = 10.sp, fontFamily = MonoFamily, color = TextSecondary)
                }
            }
            Text("\$84.200", fontSize = 16.sp, fontWeight = FontWeight.Bold, fontFamily = MonoFamily, color = OnSurface)
        }
    }
}

@Composable
private fun TipCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Brush.horizontalGradient(listOf(AIPrimary.copy(0.12f), SurfaceContainer)))
            .padding(12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(AIPrimary.copy(0.2f), RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Outlined.Lightbulb, null, tint = AIPrimary, modifier = Modifier.size(16.dp))
        }
        Spacer(Modifier.width(10.dp))
        Column {
            Text("Consejo de optimización Geminis AI:", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = AIPrimary)
            Spacer(Modifier.height(3.dp))
            Text(
                buildAnnotatedString {
                    append("Tienes ")
                    withStyle(SpanStyle(fontWeight = FontWeight.SemiBold, color = OnSurface)) { append("14 micro-compras") }
                    append(" en cafeterías que suman \$48.500 CLP. Reducir un 20% liberaría ")
                    withStyle(SpanStyle(fontWeight = FontWeight.SemiBold, color = FiscalGreen)) { append("~\$10.000 semanales") }
                    append(" para tu fondo de ahorro.")
                },
                fontSize = 12.sp,
                color = TextSecondary,
                lineHeight = 18.sp
            )
        }
    }
}

@Composable
private fun ActionChip(icon: androidx.compose.ui.graphics.vector.ImageVector, iconTint: Color, label: String) {
    Surface(shape = RoundedCornerShape(8.dp), color = SurfaceContainer) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, null, tint = iconTint, modifier = Modifier.size(14.dp))
            Spacer(Modifier.width(4.dp))
            Text(label, fontSize = 11.sp, color = OnSurface)
        }
    }
}

@Composable
private fun StreamingIndicator() {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier.size(32.dp).background(SurfaceContainerHigh, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Outlined.AutoAwesome, null, tint = AIPrimary, modifier = Modifier.size(16.dp))
        }
        Spacer(Modifier.width(10.dp))
        Row(
            modifier = Modifier
                .background(SurfaceContainerLow, RoundedCornerShape(12.dp))
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Box(Modifier.size(7.dp).background(AIPrimary, CircleShape))
            Box(Modifier.size(7.dp).background(AISecondary, CircleShape))
            Box(Modifier.size(7.dp).background(FiscalGreen, CircleShape))
            Spacer(Modifier.width(4.dp))
            Text("Gemini está analizando tus patrones...", fontSize = 13.sp, color = TextSecondary)
        }
    }
}

@Composable
private fun InputDock() {
    var inputText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(SurfaceContainerLowest)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(SurfaceContainerLow)
                .padding(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { }, modifier = Modifier.size(40.dp)) {
                Icon(Icons.Outlined.FilterList, null, tint = TextSecondary, modifier = Modifier.size(20.dp))
            }
            BasicTextField(
                value = inputText,
                onValueChange = { inputText = it },
                modifier = Modifier.weight(1f).padding(vertical = 10.dp),
                textStyle = TextStyle(color = OnSurface, fontSize = 14.sp),
                cursorBrush = SolidColor(AIPrimary),
                singleLine = true,
                decorationBox = { innerTextField ->
                    Box {
                        if (inputText.isEmpty()) {
                            Text("Pregúntale a Gemini sobre tus finanzas...", fontSize = 14.sp, color = TextSecondary)
                        }
                        innerTextField()
                    }
                }
            )
            IconButton(onClick = { }, modifier = Modifier.size(36.dp)) {
                Icon(Icons.Outlined.Mic, null, tint = TextSecondary, modifier = Modifier.size(20.dp))
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(FiscalGreen, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Outlined.Send, null, tint = Color(0xFF003912), modifier = Modifier.size(20.dp))
            }
        }

        Spacer(Modifier.height(6.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Outlined.Lock, null, tint = AISecondary, modifier = Modifier.size(11.dp))
                Spacer(Modifier.width(4.dp))
                Text("Datos cifrados y procesados localmente", fontSize = 10.sp, fontFamily = MonoFamily, color = TextSecondary)
            }
            Text("Geminis AI v1.0", fontSize = 10.sp, fontFamily = MonoFamily, color = FiscalGreen)
        }
    }
}
