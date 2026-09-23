package com.example.myapplication.ui.ingest

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.FileUpload
import androidx.compose.material.icons.outlined.FolderOpen
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.TableChart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.AIPrimary
import com.example.myapplication.ui.theme.Background
import com.example.myapplication.ui.theme.Border
import com.example.myapplication.ui.theme.FiscalGreen
import com.example.myapplication.ui.theme.MonoFamily
import com.example.myapplication.ui.theme.OnSurface
import com.example.myapplication.ui.theme.SurfaceContainerLow
import com.example.myapplication.ui.theme.TextDisabled
import com.example.myapplication.ui.theme.TextSecondary

// Hardcoded preview data — reemplazar con parsing real en la siguiente fase
private data class TransactionPreview(
    val date: String,
    val description: String,
    val amount: String,
    val category: String,
    val paymentType: String,
    val categoryColor: Color
)

private val hardcodedPreview = listOf(
    TransactionPreview("20/09/2026", "Supermercado Jumbo", "-\$84.200", "Alimentación", "Tarjeta Débito", Color(0xFF4CAF50)),
    TransactionPreview("20/09/2026", "Uber Viaje", "-\$6.450", "Transporte", "Tarjeta Crédito", Color(0xFF42A5F5)),
    TransactionPreview("19/09/2026", "Farmacia Salcobrand", "-\$15.800", "Salud", "Transferencia", Color(0xFFEC407A)),
)

@Composable
fun ImportScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(Modifier.height(20.dp))

        // Badge INGESTA • OFFLINE FIRST
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                shape = RoundedCornerShape(999.dp),
                color = FiscalGreen.copy(alpha = 0.12f)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        Modifier
                            .size(6.dp)
                            .background(FiscalGreen, RoundedCornerShape(999.dp))
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        "IMPORTACIÓN",
                        color = FiscalGreen,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = MonoFamily
                    )
                    Text(
                        " • SIN CONEXIÓN",
                        color = TextSecondary,
                        fontSize = 11.sp,
                        fontFamily = MonoFamily
                    )
                }
            }
        }

        Spacer(Modifier.height(14.dp))

        Text(
            text = "Importar Movimientos",
            style = MaterialTheme.typography.headlineLarge,
            color = OnSurface
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text = "Carga tus extractos bancarios o planillas para categorizarlos y analizarlos con IA localmente mediante Room.",
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondary
        )

        Spacer(Modifier.height(24.dp))

        LocalFileCard()

        Spacer(Modifier.height(12.dp))

        GoogleDriveCard()

        Spacer(Modifier.height(12.dp))

        CameraCard()

        Spacer(Modifier.height(28.dp))

        DataPreviewSection()

        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun LocalFileCard() {
    val dashedColor = Border

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow),
        border = BorderStroke(1.dp, Border)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Outlined.FolderOpen, null, tint = FiscalGreen, modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(8.dp))
                Text("Archivo Local", style = MaterialTheme.typography.titleMedium, color = OnSurface)
                Spacer(Modifier.weight(1f))
                Surface(
                    shape = RoundedCornerShape(999.dp),
                    color = FiscalGreen.copy(alpha = 0.15f)
                ) {
                    Text(
                        "RECOMENDADO",
                        color = FiscalGreen,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            Spacer(Modifier.height(4.dp))
            Text(
                "Extractos bancarios .csv, .xlsx o .tsv",
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary
            )

            Spacer(Modifier.height(14.dp))

            // Zona tappable — abre el selector de archivos
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        drawRoundRect(
                            color = dashedColor,
                            cornerRadius = CornerRadius(12.dp.toPx()),
                            style = Stroke(
                                width = 1.dp.toPx(),
                                pathEffect = PathEffect.dashPathEffect(floatArrayOf(12f, 8f), 0f)
                            )
                        )
                    }
                    .clickable { }
                    .padding(vertical = 28.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Outlined.FileUpload,
                        null,
                        tint = FiscalGreen,
                        modifier = Modifier.size(36.dp)
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        "Tocá para seleccionar tu extracto",
                        style = MaterialTheme.typography.bodyMedium,
                        color = OnSurface,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "CSV · XLSX · TSV  •  Máx. 15 MB",
                        fontSize = 11.sp,
                        color = TextSecondary,
                        textAlign = TextAlign.Center,
                        fontFamily = MonoFamily
                    )
                }
            }
        }
    }
}

@Composable
private fun GoogleDriveCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow),
        border = BorderStroke(1.dp, Border)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Outlined.Cloud, null, tint = AIPrimary, modifier = Modifier.size(28.dp))
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text("Google Drive", style = MaterialTheme.typography.titleSmall, color = OnSurface)
                Spacer(Modifier.height(2.dp))
                Text(
                    "Sincroniza hojas de cálculo desde Drive",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )
            }
            TextButton(onClick = { }) {
                Text("Conectar", color = AIPrimary, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                Icon(Icons.Outlined.ChevronRight, null, tint = AIPrimary, modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Composable
private fun CameraCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow.copy(alpha = 0.4f)),
        border = BorderStroke(1.dp, Border.copy(alpha = 0.3f))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Outlined.CameraAlt, null, tint = TextDisabled, modifier = Modifier.size(28.dp))
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    "Escanear con cámara (ML Kit)",
                    style = MaterialTheme.typography.titleSmall,
                    color = TextDisabled
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    "Próximamente — Fase 2: Procesamiento OCR en dispositivo con ML Kit Vision.",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextDisabled
                )
            }
        }
    }
}

@Composable
private fun DataPreviewSection() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Outlined.TableChart, null, tint = FiscalGreen, modifier = Modifier.size(18.dp))
        Spacer(Modifier.width(6.dp))
        Text(
            "Previsualización de datos",
            style = MaterialTheme.typography.titleMedium,
            color = OnSurface
        )
        Spacer(Modifier.weight(1f))
        Surface(
            shape = RoundedCornerShape(999.dp),
            color = FiscalGreen.copy(alpha = 0.15f)
        ) {
            Text(
                "Verificado",
                color = FiscalGreen,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
            )
        }
    }

    Spacer(Modifier.height(12.dp))

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow),
        border = BorderStroke(1.dp, Border)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // File info row
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Outlined.Description, null, tint = FiscalGreen, modifier = Modifier.size(22.dp))
                Spacer(Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "extracto_septiembre_2026.csv",
                        style = MaterialTheme.typography.bodyMedium,
                        color = OnSurface,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        "48 KB  •  64 registros detectados",
                        fontSize = 11.sp,
                        color = TextSecondary,
                        fontFamily = MonoFamily
                    )
                }
                Icon(Icons.Outlined.Refresh, null, tint = TextSecondary, modifier = Modifier.size(18.dp))
            }

            Spacer(Modifier.height(14.dp))
            HorizontalDivider(color = Border)
            Spacer(Modifier.height(12.dp))

            Text(
                "PRIMERAS 3 FILAS DETECTADAS",
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextSecondary,
                letterSpacing = 0.08.sp,
                fontFamily = MonoFamily
            )

            Spacer(Modifier.height(10.dp))

            hardcodedPreview.forEachIndexed { index, row ->
                PreviewTransactionRow(row)
                if (index < hardcodedPreview.lastIndex) {
                    Spacer(Modifier.height(10.dp))
                }
            }

            Spacer(Modifier.height(14.dp))
            HorizontalDivider(color = Border)
            Spacer(Modifier.height(12.dp))

            // Schema verified
            Row(verticalAlignment = Alignment.Top) {
                Icon(
                    Icons.Outlined.CheckCircle,
                    null,
                    tint = FiscalGreen,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(8.dp))
                Column {
                    Text(
                        "Esquema verificado",
                        style = MaterialTheme.typography.bodySmall,
                        color = FiscalGreen,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        "Estructura de columnas validada correctamente. Listo para persistir en SQLite Room sin colisiones.",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary
                    )
                }
            }
        }
    }

    Spacer(Modifier.height(16.dp))

    // Confirm button
    Button(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = FiscalGreen,
            contentColor = Color(0xFF0D1117)
        )
    ) {
        Icon(Icons.Outlined.CheckCircle, null, modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(8.dp))
        Text(
            "Confirmar e Importar 64 Registros",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
    }

    Spacer(Modifier.height(8.dp))

    TextButton(
        onClick = { },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            "Cancelar o cambiar archivo",
            color = TextSecondary,
            fontSize = 14.sp
        )
    }
}

@Composable
private fun PreviewTransactionRow(row: TransactionPreview) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .size(8.dp)
                .background(row.categoryColor, RoundedCornerShape(999.dp))
        )
        Spacer(Modifier.width(10.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                row.description,
                style = MaterialTheme.typography.bodySmall,
                color = OnSurface,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(row.date, fontSize = 10.sp, color = TextSecondary, fontFamily = MonoFamily)
                Text("•", fontSize = 10.sp, color = TextDisabled)
                Text(row.category, fontSize = 10.sp, color = row.categoryColor)
                Text("•", fontSize = 10.sp, color = TextDisabled)
                Text(row.paymentType, fontSize = 10.sp, color = TextSecondary)
            }
        }
        Spacer(Modifier.width(8.dp))
        Text(
            row.amount,
            style = MaterialTheme.typography.bodySmall,
            color = OnSurface,
            fontWeight = FontWeight.Bold,
            fontFamily = MonoFamily
        )
    }
}
