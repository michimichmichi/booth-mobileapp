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
import org.umn.booth.ui.theme.BoothBadgeBg
import org.umn.booth.ui.theme.BoothBorder
import org.umn.booth.ui.theme.BoothPrimary
import org.umn.booth.ui.theme.BoothSuccess
import org.umn.booth.ui.theme.BoothTextMuted
import org.umn.booth.ui.theme.BoothTextPrimary
import org.umn.booth.ui.theme.BoothTextSecondary

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
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp,
                border = BorderStroke(1.dp, BoothBorder)
            ) {
                Box(modifier = Modifier.padding(16.dp)) {
                    Button(
                        onClick = onFinishSession,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = BoothPrimary)
                    ) {
                        Text(
                            text = "Finish Session →",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
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
            // Offline-Ready Room DB Cached Badge (Slide 12 Callout)
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFFF0FDF4),
                border = BorderStroke(1.dp, Color(0xFFBBF7D0))
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
                    Column {
                        Text(
                            text = "Offline-Ready • Room DB Cached",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF166534)
                        )
                        Text(
                            text = "Valid at venue table sensors and terminal scanners with zero cellular reception.",
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 11.sp,
                            color = Color(0xFF15803D),
                            lineHeight = 14.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Main Digital Pass Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, BoothBorder),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Venue & Table Header
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
                                    color = BoothTextPrimary
                                )
                            }
                            Text(
                                text = "Table 04 • Indoor Lounge",
                                style = MaterialTheme.typography.bodySmall,
                                color = BoothTextSecondary
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Color(0xFFDCFCE7)
                        ) {
                            Text(
                                text = "PAID",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF15803D)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Booking Info Grid
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "Arrival Time",
                                style = MaterialTheme.typography.bodySmall,
                                color = BoothTextMuted,
                                fontSize = 11.sp
                            )
                            Text(
                                text = "Today, 16:30 WIB",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = BoothTextPrimary
                            )
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "Assigned Seat",
                                style = MaterialTheme.typography.bodySmall,
                                color = BoothTextMuted,
                                fontSize = 11.sp
                            )
                            Text(
                                text = "Seat #4 (Sarah's)",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = BoothPrimary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Stylized QR Code with 4 Corner Scan Brackets (Slide 12)
                    Box(
                        modifier = Modifier
                            .size(210.dp)
                            .padding(6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            val strokeWidth = 3.dp.toPx()
                            val bracketLength = 24.dp.toPx()
                            val bracketColor = Color(0xFF64748B)

                            // Top-Left ┌
                            drawLine(bracketColor, Offset(0f, 0f), Offset(bracketLength, 0f), strokeWidth)
                            drawLine(bracketColor, Offset(0f, 0f), Offset(0f, bracketLength), strokeWidth)

                            // Top-Right ┐
                            drawLine(bracketColor, Offset(size.width, 0f), Offset(size.width - bracketLength, 0f), strokeWidth)
                            drawLine(bracketColor, Offset(size.width, 0f), Offset(size.width, bracketLength), strokeWidth)

                            // Bottom-Left └
                            drawLine(bracketColor, Offset(0f, size.height), Offset(bracketLength, size.height), strokeWidth)
                            drawLine(bracketColor, Offset(0f, size.height), Offset(0f, size.height - bracketLength), strokeWidth)

                            // Bottom-Right ┘
                            drawLine(bracketColor, Offset(size.width, size.height), Offset(size.width - bracketLength, size.height), strokeWidth)
                            drawLine(bracketColor, Offset(size.width, size.height), Offset(size.width, size.height - bracketLength), strokeWidth)
                        }

                        Box(
                            modifier = Modifier
                                .size(175.dp)
                                .background(Color.White)
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            MockQRCode(modifier = Modifier.fillMaxSize())

                            // Center Booth Logo Badge
                            Box(
                                modifier = Modifier
                                    .size(38.dp)
                                    .background(Color.White, CircleShape)
                                    .border(2.dp, BoothPrimary, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                BoothLogo(modifier = Modifier.size(24.dp))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Ticket Identifier
                    Text(
                        text = "BTH-882-X904",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        color = BoothTextPrimary,
                        letterSpacing = 2.sp
                    )

                    Text(
                        text = "Scan at the cashier counter upon arrival",
                        style = MaterialTheme.typography.bodySmall,
                        color = BoothTextSecondary,
                        fontSize = 11.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    androidx.compose.material3.HorizontalDivider(color = BoothBorder)

                    // Accordion: Pre-order & Bill Breakdown
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { isBreakdownExpanded = !isBreakdownExpanded }
                            .padding(vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Pre-Order & Bill Breakdown",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = BoothTextPrimary
                        )

                        Icon(
                            imageVector = if (isBreakdownExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = BoothTextSecondary
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
                            androidx.compose.material3.HorizontalDivider(color = BoothBorder)
                            Spacer(modifier = Modifier.height(6.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("Total Paid (QRIS)", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                                Text("Rp 57.000", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = BoothPrimary)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Presence indicator
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFEFF6FF), RoundedCornerShape(10.dp))
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
                                color = BoothPrimary
                            )
                        }

                        Text(
                            text = "3/4 Seats",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = BoothPrimary
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
    Canvas(modifier = modifier) {
        val count = 21
        val cellSize = size.width / count

        // Draw simulated QR matrix blocks
        for (i in 0 until count) {
            for (j in 0 until count) {
                // Keep center empty for Booth logo
                if (i in 8..12 && j in 8..12) continue

                // Corner finder patterns
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
                        color = Color(0xFF0F172A),
                        topLeft = Offset(i * cellSize, j * cellSize),
                        size = Size(cellSize, cellSize)
                    )
                }
            }
        }
    }
}
