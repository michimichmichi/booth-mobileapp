package org.umn.booth.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.umn.booth.ui.components.InitialsAvatar
import org.umn.booth.ui.theme.BoothPrimary
import org.umn.booth.ui.theme.BoothSuccess

data class JoinedCommunity(
    val id: String,
    val name: String,
    val venueName: String,
    val hostName: String,
    val hostInitials: String,
    val circleType: String,
    val memberCount: Int,
    val nextSession: String
)

@Composable
fun CommunityScreen(
    userInitials: String = "AR",
    onExploreClick: () -> Unit,
    onReservationsClick: () -> Unit,
    onProfileClick: () -> Unit,
    onCommunityTableClick: (String) -> Unit
) {
    var inviteCodeInput by remember { mutableStateOf("") }
    var joinSuccessMessage by remember { mutableStateOf<String?>(null) }

    val joinedCommunities = remember {
        mutableStateListOf(
            JoinedCommunity(
                id = "com-1",
                name = "Afternoon WFC & Tech Chat",
                venueName = "Kopi Nako Serpong • Table 04",
                hostName = "Sarah K.",
                hostInitials = "SK",
                circleType = "Focus Circle",
                memberCount = 18,
                nextSession = "Today • 16:30 WIB"
            ),
            JoinedCommunity(
                id = "com-2",
                name = "Casual Coffee & Startup Talk",
                venueName = "Titik Temu Coffee BSD • Table 07",
                hostName = "David P.",
                hostInitials = "DP",
                circleType = "Networking",
                memberCount = 12,
                nextSession = "Tomorrow • 10:00 WIB"
            ),
            JoinedCommunity(
                id = "com-3",
                name = "Padel Pro Social Doubles",
                venueName = "Padel Pro & Lounge • Court 2",
                hostName = "Marco V.",
                hostInitials = "MV",
                circleType = "Sports & Padel",
                memberCount = 24,
                nextSession = "Saturday • 08:00 WIB"
            )
        )
    }

    Scaffold(
        containerColor = Color(0xFFF8FAFC),
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    selected = false,
                    onClick = onExploreClick,
                    icon = { Icon(Icons.Default.Search, contentDescription = "Explore") },
                    label = { Text("Explore", fontSize = 11.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = BoothPrimary,
                        selectedTextColor = BoothPrimary,
                        indicatorColor = Color(0xFFEFF6FF)
                    )
                )
                NavigationBarItem(
                    selected = false,
                    onClick = onReservationsClick,
                    icon = { Icon(Icons.Default.DateRange, contentDescription = "Reservations") },
                    label = { Text("Reservations", fontSize = 11.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = BoothPrimary,
                        selectedTextColor = BoothPrimary,
                        indicatorColor = Color(0xFFEFF6FF)
                    )
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Default.Groups, contentDescription = "Community") },
                    label = { Text("Community", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = BoothPrimary,
                        selectedTextColor = BoothPrimary,
                        indicatorColor = Color(0xFFEFF6FF)
                    )
                )
                NavigationBarItem(
                    selected = false,
                    onClick = onProfileClick,
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile", fontSize = 11.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = BoothPrimary,
                        selectedTextColor = BoothPrimary,
                        indicatorColor = Color(0xFFEFF6FF)
                    )
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF8FAFC)),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 48.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Community",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0F172A),
                            fontSize = 24.sp
                        )
                        Text(
                            text = "Your saved circles & verified hangout buddies",
                            fontSize = 12.5.sp,
                            color = Color(0xFF64748B)
                        )
                    }

                    InitialsAvatar(
                        initials = userInitials,
                        size = 38.dp,
                        backgroundColor = BoothPrimary
                    )
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F7FF)),
                    border = BorderStroke(1.dp, Color(0xFFBFDBFE))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(30.dp)
                                    .background(Color(0xFFDBEAFE), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Groups,
                                    contentDescription = null,
                                    tint = BoothPrimary,
                                    modifier = Modifier.size(17.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Join a Circle with Code",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF0F172A)
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedTextField(
                                value = inviteCodeInput,
                                onValueChange = { inviteCodeInput = it.uppercase() },
                                placeholder = { Text("e.g. BTH-882", fontSize = 13.sp, color = Color(0xFF94A3B8)) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(10.dp),
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedContainerColor = Color.White,
                                    unfocusedContainerColor = Color.White,
                                    focusedBorderColor = BoothPrimary,
                                    unfocusedBorderColor = Color(0xFFCBD5E1)
                                )
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Button(
                                onClick = {
                                    val code = if (inviteCodeInput.isNotBlank()) inviteCodeInput else "BTH-882"
                                    joinedCommunities.add(
                                        0,
                                        JoinedCommunity(
                                            id = "com-${System.currentTimeMillis()}",
                                            name = "Circle $code • Community Lounge",
                                            venueName = "Kopi Nako Serpong • Table 04",
                                            hostName = "Sarah K.",
                                            hostInitials = "SK",
                                            circleType = "Focus Circle",
                                            memberCount = 19,
                                            nextSession = "Today • 17:00 WIB"
                                        )
                                    )
                                    joinSuccessMessage = "Successfully joined Circle $code!"
                                    inviteCodeInput = ""
                                },
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = BoothPrimary,
                                    contentColor = Color.White
                                )
                            ) {
                                Text("Join Circle", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color.White)
                            }
                        }

                        if (joinSuccessMessage != null) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = BoothSuccess,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = joinSuccessMessage!!,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = BoothSuccess
                                )
                            }
                        }
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Joined Communities (${joinedCommunities.size})",
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = Color(0xFF0F172A)
                    )
                    Text(
                        text = "Saved",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = BoothPrimary
                    )
                }
            }

            items(joinedCommunities) { community ->
                CommunityItemCard(
                    community = community,
                    onOpenSession = { onCommunityTableClick("kopi-nako-serpong") }
                )
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Saved Table Hosts",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = Color(0xFF0F172A)
                )
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, Color(0xFFE2E8F0))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        SavedHostRow(
                            name = "Sarah K.",
                            role = "UI/UX Designer • 14 tables hosted",
                            initials = "SK",
                            rating = "4.9 ★",
                            avatarBg = BoothPrimary
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0xFFF1F5F9)))
                        Spacer(modifier = Modifier.height(12.dp))
                        SavedHostRow(
                            name = "David P.",
                            role = "Product Builder • 9 tables hosted",
                            initials = "DP",
                            rating = "4.8 ★",
                            avatarBg = Color(0xFF1E293B)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CommunityItemCard(
    community: JoinedCommunity,
    onOpenSession: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onOpenSession() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFFEFF6FF)
                ) {
                    Text(
                        text = community.circleType,
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.Bold,
                        color = BoothPrimary,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFFF0FDF4)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.size(6.dp).background(BoothSuccess, CircleShape))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Joined",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = BoothSuccess
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = community.name,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color(0xFF0F172A)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = community.venueName,
                fontSize = 12.sp,
                color = Color(0xFF64748B)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF8FAFC), RoundedCornerShape(10.dp))
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    InitialsAvatar(initials = community.hostInitials, size = 28.dp, backgroundColor = BoothPrimary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "Host: ${community.hostName}",
                            fontSize = 11.5.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0F172A)
                        )
                        Text(
                            text = "${community.memberCount} members in circle",
                            fontSize = 10.5.sp,
                            color = Color(0xFF64748B)
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFFEFF6FF)
                ) {
                    Text(
                        text = community.nextSession,
                        fontSize = 10.5.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = BoothPrimary,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun SavedHostRow(
    name: String,
    role: String,
    initials: String,
    rating: String,
    avatarBg: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            InitialsAvatar(initials = initials, size = 36.dp, backgroundColor = avatarBg)
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = name, fontWeight = FontWeight.Bold, fontSize = 13.5.sp, color = Color(0xFF0F172A))
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = BoothPrimary, modifier = Modifier.size(13.dp))
                }
                Text(text = role, fontSize = 11.sp, color = Color(0xFF64748B))
            }
        }

        Surface(shape = RoundedCornerShape(8.dp), color = Color(0xFFEFF6FF)) {
            Text(
                text = rating,
                fontSize = 11.5.sp,
                fontWeight = FontWeight.Bold,
                color = BoothPrimary,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

