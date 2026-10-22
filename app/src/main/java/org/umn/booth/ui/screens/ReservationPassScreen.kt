package org.umn.booth.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.umn.booth.ui.components.BoothLogo
import org.umn.booth.ui.components.BoothTopAppBar
import org.umn.booth.ui.components.InitialsAvatar
import org.umn.booth.ui.theme.BoothSuccess

@Composable
fun ReservationPassScreen(
    onNavigateBack: () -> Unit,
    onFinishSession: () -> Unit
) {
    var isBreakdownExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            BoothTopAppBar(
                title = "Reservation Pass",
                subtitle = "Active Session",
                canNavigateBack = true,
                onNavigateBack = onNavigateBack
            )
        },
        bottomBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding(),
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
            ) {
                Box(modifier = Modifier.padding(16.dp)) {
                    Button(
                        onClick = onFinishSession,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Text(
                            text = "Finish Session →",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = BoothSuccess.copy(alpha = 0.12f),
                border = BorderStroke(1.dp, BoothSuccess.copy(alpha = 0.3f))
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CloudDone,
                        contentDescription = null,
                        tint = BoothSuccess,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Saved on this device — works without internet.",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = BoothSuccess
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .background(BoothSuccess, CircleShape)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Kopi Nako Serpong",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                            Text(
                                text = "Table 04 • Glasshouse Corner",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = BoothSuccess.copy(alpha = 0.12f)
                        ) {
                            Text(
                                text = "PAID",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = BoothSuccess
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "Arrival Time",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 11.sp
                            )
                            Text(
                                text = "Today, 16:30 WIB",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "Assigned Seat",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 11.sp
                            )
                            Text(
                                text = "Seat 4 • Sarah's table",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Box(
                        modifier = Modifier
                            .size(210.dp)
                            .padding(6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        val bracketColor = MaterialTheme.colorScheme.onSurfaceVariant

                        Canvas(modifier = Modifier.fillMaxSize()) {
                            val strokeWidth = 3.dp.toPx()
                            val bracketLength = 24.dp.toPx()

                            drawLine(bracketColor, Offset(0f, 0f), Offset(bracketLength, 0f), strokeWidth)
                            drawLine(bracketColor, Offset(0f, 0f), Offset(0f, bracketLength), strokeWidth)

                            drawLine(bracketColor, Offset(size.width, 0f), Offset(size.width - bracketLength, 0f), strokeWidth)
                            drawLine(bracketColor, Offset(size.width, 0f), Offset(size.width, bracketLength), strokeWidth)

                            drawLine(bracketColor, Offset(0f, size.height), Offset(bracketLength, size.height), strokeWidth)
                            drawLine(bracketColor, Offset(0f, size.height), Offset(0f, size.height - bracketLength), strokeWidth)

                            drawLine(bracketColor, Offset(size.width, size.height), Offset(size.width - bracketLength, size.height), strokeWidth)
                            drawLine(bracketColor, Offset(size.width, size.height), Offset(size.width, size.height - bracketLength), strokeWidth)
                        }

                        Box(
                            modifier = Modifier
                                .size(175.dp)
                                .background(MaterialTheme.colorScheme.surface)
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            MockQRCode(modifier = Modifier.fillMaxSize())

                            Box(
                                modifier = Modifier
                                    .size(38.dp)
                                    .background(MaterialTheme.colorScheme.surface, CircleShape)
                                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                BoothLogo(modifier = Modifier.size(24.dp))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "BTH-882-X904",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        color = MaterialTheme.colorScheme.onSurface,
                        letterSpacing = 2.sp
                    )

                    Text(
                        text = "Show this code at the table to check in",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 11.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    androidx.compose.material3.HorizontalDivider(color = MaterialTheme.colorScheme.outline)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { isBreakdownExpanded = !isBreakdownExpanded }
                            .padding(vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Pre-Order & Receipt",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Icon(
                            imageVector = if (isBreakdownExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    if (isBreakdownExpanded) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(10.dp))
                                .padding(12.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("Iced Americano × 1", style = MaterialTheme.typography.bodySmall)
                                Text("Rp 32.000", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Medium)
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("French Fries × 1", style = MaterialTheme.typography.bodySmall)
                                Text("Rp 25.000", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Medium)
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            androidx.compose.material3.HorizontalDivider(color = MaterialTheme.colorScheme.outline)
                            Spacer(modifier = Modifier.height(6.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("Total Paid (QRIS)", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                                Text("Rp 57.000", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(10.dp))
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .background(BoothSuccess, CircleShape)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Sarah & 2 others are seated",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        Text(
                            text = "3 of 4 seats filled",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun MockQRCode(modifier: Modifier = Modifier) {
    val qrColor = MaterialTheme.colorScheme.onSurface
    Canvas(modifier = modifier) {
        val count = 21
        val cellSize = size.width / count

        for (i in 0 until count) {
            for (j in 0 until count) {

                if (i in 8..12 && j in 8..12) continue

                val isTopLeftCorner = (i < 7 && j < 7)
                val isTopRightCorner = (i >= count - 7 && j < 7)
                val isBottomLeftCorner = (i < 7 && j >= count - 7)

                val shouldDraw = when {
                    isTopLeftCorner -> (i == 0 || i == 6 || j == 0 || j == 6 || (i in 2..4 && j in 2..4))
                    isTopRightCorner -> (i == count - 1 || i == count - 7 || j == 0 || j == 6 || (i in count - 5..count - 3 && j in 2..4))
                    isBottomLeftCorner -> (i == 0 || i == 6 || j == count - 1 || j == count - 7 || (i in 2..4 && j in count - 5..count - 3))
                    else -> ((i * 7 + j * 13 + i * j) % 3 == 0 || (i + j) % 5 == 0)
                }

                if (shouldDraw) {
                    drawRect(
                        color = qrColor,
                        topLeft = Offset(i * cellSize, j * cellSize),
                        size = Size(cellSize, cellSize)
                    )
                }
            }
        }
    }
}

