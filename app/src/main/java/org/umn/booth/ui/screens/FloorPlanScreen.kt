package org.umn.booth.ui.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.umn.booth.data.DummyData
import org.umn.booth.data.model.TableSession
import org.umn.booth.ui.components.BoothTopAppBar
import org.umn.booth.ui.components.InitialsAvatar
import org.umn.booth.ui.components.TagChip
import org.umn.booth.ui.theme.BoothBorder
import org.umn.booth.ui.theme.BoothPrimary
import org.umn.booth.ui.theme.BoothSuccess
import org.umn.booth.ui.theme.BoothTextMuted
import org.umn.booth.ui.theme.BoothTextPrimary
import org.umn.booth.ui.theme.BoothTextSecondary
import org.umn.booth.ui.theme.BoothWarning

@Composable
fun FloorPlanScreen(
    venueId: String,
    onNavigateBack: () -> Unit,
    onProceedToPreOrder: (String) -> Unit
) {
    // 0 = Active Open Tables (Slide 10 Left Phone), 1 = Floor Plan & Join Table (Slide 10 Right Phone)
    var selectedViewMode by remember { mutableIntStateOf(1) }
    var selectedSessionId by remember { mutableStateOf("tbl-04") }
    var selectedSeatNumber by remember { mutableIntStateOf(3) }

    val sessions = DummyData.tableSessions
    val currentSession = sessions.find { it.id == selectedSessionId } ?: sessions.first()

    Scaffold(
        topBar = {
            BoothTopAppBar(
                title = if (selectedViewMode == 0) "Active Open Tables" else "Join ${currentSession.tableNumber}",
                subtitle = "Kopi Nako Serpong • Step 2 of 3",
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "SUBTOTAL • 2 ITEMS",
                            style = MaterialTheme.typography.labelSmall,
                            color = BoothTextMuted,
                            fontSize = 10.sp
                        )
                        Text(
                            text = "Rp 57.000",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = BoothPrimary
                        )
                    }

                    Button(
                        onClick = { onProceedToPreOrder(currentSession.id) },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = BoothPrimary)
                    ) {
                        Text("Confirm & Join Table →", fontWeight = FontWeight.Bold)
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
                .padding(16.dp)
        ) {
            // Mode Switcher between Slide 10 Left View (Open Tables) and Right View (Floor Plan & Join Table)
            TabRow(
                selectedTabIndex = selectedViewMode,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = BoothPrimary,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedViewMode]),
                        color = BoothPrimary,
                        height = 3.dp
                    )
                }
            ) {
                Tab(
                    selected = selectedViewMode == 0,
                    onClick = { selectedViewMode = 0 },
                    text = {
                        Text(
                            text = "Open Tables (2)",
                            fontWeight = if (selectedViewMode == 0) FontWeight.Bold else FontWeight.Medium
                        )
                    }
                )
                Tab(
                    selected = selectedViewMode == 1,
                    onClick = { selectedViewMode = 1 },
                    text = {
                        Text(
                            text = "Floor Plan & Details",
                            fontWeight = if (selectedViewMode == 1) FontWeight.Bold else FontWeight.Medium
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (selectedViewMode == 0) {
                // ==========================================
                // SLIDE 10 LEFT PHONE: ACTIVE OPEN TABLES LIST
                // ==========================================
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Active Open Tables",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = BoothTextPrimary
                    )
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFFEFF6FF)
                    ) {
                        Text(
                            text = "2 Open",
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                            style = MaterialTheme.typography.labelSmall,
                            color = BoothPrimary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Card 1: Table 04
                ActiveOpenTableCard(
                    tableNumber = "Table 04",
                    area = "Glasshouse Corner",
                    badge = "Focus Circle",
                    badgeColor = BoothPrimary,
                    badgeBg = Color(0xFFEFF6FF),
                    title = "Afternoon WFC & Coding Sprint",
                    hostName = "Sarah K.",
                    hostRole = "Host • UI/UX Designer • 14 tables hosted",
                    timeAgo = "Seated 25m ago",
                    initials = "SK",
                    seatsFilledText = "2 of 4 Seats Filled",
                    tags = listOf("Quiet Work", "No Loud Calls", "Split Bill Evenly"),
                    onJoinClick = {
                        selectedSessionId = "tbl-04"
                        selectedViewMode = 1
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Card 2: Table 07
                ActiveOpenTableCard(
                    tableNumber = "Table 07",
                    area = "Outdoor Canopy",
                    badge = "1 Seat Left!",
                    badgeColor = Color(0xFFDC2626),
                    badgeBg = Color(0xFFFEE2E2),
                    title = "Casual Coffee",
                    hostName = "David P.",
                    hostRole = "Community Host • Product Builder",
                    timeAgo = "Seated 10m ago",
                    initials = "DP",
                    seatsFilledText = "3 of 4 Seats Filled",
                    tags = listOf("Friendly Banter", "ID/EN Spoken", "Instant QRIS"),
                    onJoinClick = {
                        selectedSessionId = "tbl-07"
                        selectedViewMode = 1
                    }
                )

            } else {
                // ==========================================
                // SLIDE 10 RIGHT PHONE: FLOOR PLAN & JOIN TABLE
                // ==========================================
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.5.dp, BoothPrimary.copy(alpha = 0.3f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${currentSession.tableNumber} • ${currentSession.locationArea}",
                                style = MaterialTheme.typography.labelMedium,
                                color = BoothTextSecondary,
                                fontWeight = FontWeight.Bold
                            )

                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = Color(0xFFEFF6FF)
                            ) {
                                Text(
                                    text = currentSession.circleType,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = BoothPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = currentSession.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = BoothTextPrimary
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // Host Row with Avatar
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            InitialsAvatar(
                                initials = currentSession.hostInitials,
                                size = 42.dp,
                                backgroundColor = BoothPrimary
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = currentSession.hostName,
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = BoothTextPrimary
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        tint = BoothSuccess,
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                                Text(
                                    text = currentSession.hostRole,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = BoothTextSecondary,
                                    fontSize = 11.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Tags
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            currentSession.tags.forEach { tag ->
                                TagChip(text = tag)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Seated Members (Slide 10 Right Phone)
                Text(
                    text = "• ${currentSession.filledSeats} of ${currentSession.totalSeats} Seats Filled",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = BoothTextPrimary
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    MemberSeatItem(
                        name = "Sarah (H)",
                        initials = "SK",
                        isOccupied = true,
                        isSelected = false,
                        onClick = {}
                    )

                    MemberSeatItem(
                        name = "Arya",
                        initials = "A",
                        isOccupied = true,
                        isSelected = false,
                        onClick = {}
                    )

                    MemberSeatItem(
                        name = "Seat 3 (You)",
                        initials = "+",
                        isOccupied = false,
                        isSelected = selectedSeatNumber == 3,
                        onClick = { selectedSeatNumber = 3 }
                    )

                    MemberSeatItem(
                        name = "Seat 4",
                        initials = "+",
                        isOccupied = false,
                        isSelected = selectedSeatNumber == 4,
                        onClick = { selectedSeatNumber = 4 }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Floor Plan Reference (Slide 10)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Floor Plan Reference",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = BoothTextPrimary
                    )
                    Text(
                        text = "Indoor Lounge",
                        style = MaterialTheme.typography.bodySmall,
                        color = BoothTextSecondary
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Floor Plan Layout Grid
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, BoothBorder)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            FloorPlanTableTile(
                                tableCode = "T-01 Booth",
                                status = "Available",
                                isSelected = false,
                                modifier = Modifier.weight(1f),
                                onClick = {}
                            )

                            FloorPlanTableTile(
                                tableCode = "T-02 High Table",
                                status = "Available",
                                isSelected = false,
                                modifier = Modifier.weight(1f),
                                onClick = {}
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            FloorPlanTableTile(
                                tableCode = "T-03 Window",
                                status = "Occupied",
                                isSelected = false,
                                modifier = Modifier.weight(1f),
                                onClick = {}
                            )

                            FloorPlanTableTile(
                                tableCode = "Table 04",
                                status = "Selected • 2 Open",
                                isSelected = true,
                                modifier = Modifier.weight(1f),
                                onClick = {}
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun ActiveOpenTableCard(
    tableNumber: String,
    area: String,
    badge: String,
    badgeColor: Color,
    badgeBg: Color,
    title: String,
    hostName: String,
    hostRole: String,
    timeAgo: String,
    initials: String,
    seatsFilledText: String,
    tags: List<String>,
    onJoinClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, BoothBorder),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header: Table Number & Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$tableNumber • $area",
                    style = MaterialTheme.typography.bodySmall,
                    color = BoothTextSecondary,
                    fontWeight = FontWeight.Medium
                )

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = badgeBg
                ) {
                    Text(
                        text = badge,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = badgeColor,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = BoothTextPrimary
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Host Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    InitialsAvatar(initials = initials, size = 38.dp)
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = hostName,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = BoothTextPrimary
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(Icons.Default.Check, contentDescription = null, tint = BoothSuccess, modifier = Modifier.size(14.dp))
                        }
                        Text(
                            text = hostRole,
                            style = MaterialTheme.typography.bodySmall,
                            color = BoothTextSecondary,
                            fontSize = 11.sp
                        )
                    }
                }

                Text(
                    text = timeAgo,
                    style = MaterialTheme.typography.bodySmall,
                    color = BoothTextMuted,
                    fontSize = 11.sp
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Seats Filled & Join Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = seatsFilledText,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = BoothTextPrimary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row {
                        InitialsAvatar(initials = initials, size = 26.dp)
                        Spacer(modifier = Modifier.width((-6).dp))
                        InitialsAvatar(initials = "A", size = 26.dp, backgroundColor = Color(0xFF0D9488))
                        Spacer(modifier = Modifier.width(4.dp))
                        Box(
                            modifier = Modifier
                                .size(26.dp)
                                .background(Color(0xFFF1F5F9), CircleShape)
                                .border(1.dp, BoothBorder, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Add, contentDescription = null, tint = BoothTextMuted, modifier = Modifier.size(14.dp))
                        }
                    }
                }

                Button(
                    onClick = onJoinClick,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BoothPrimary)
                ) {
                    Text("Join Table 👥", fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Tags
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                tags.forEach { tag ->
                    TagChip(text = tag)
                }
            }
        }
    }
}

@Composable
private fun MemberSeatItem(
    name: String,
    initials: String,
    isOccupied: Boolean,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(54.dp)
                .clip(CircleShape)
                .background(
                    when {
                        isSelected -> BoothPrimary
                        isOccupied -> Color(0xFFE2E8F0)
                        else -> Color(0xFFF1F5F9)
                    }
                )
                .border(
                    width = if (isSelected) 2.dp else 1.dp,
                    color = if (isSelected) BoothPrimary else BoothBorder,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (isOccupied) {
                Text(
                    text = initials,
                    fontWeight = FontWeight.Bold,
                    color = BoothTextPrimary,
                    fontSize = 16.sp
                )
            } else if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Selected Seat",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Open Seat",
                    tint = BoothTextMuted,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = name,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelected) BoothPrimary else BoothTextSecondary,
            fontSize = 11.sp
        )
    }
}

@Composable
private fun FloorPlanTableTile(
    tableCode: String,
    status: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .height(72.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        color = if (isSelected) BoothPrimary else MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(if (isSelected) 2.dp else 1.dp, if (isSelected) BoothPrimary else BoothBorder)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = tableCode,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) Color.White else BoothTextPrimary
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = status,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 11.sp,
                color = if (isSelected) Color.White.copy(alpha = 0.85f) else BoothTextSecondary
            )
        }
    }
}
