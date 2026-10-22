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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.umn.booth.ui.components.InitialsAvatar
import org.umn.booth.ui.theme.BoothPrimary
import org.umn.booth.ui.theme.BoothSuccess

@Composable
fun ProfileScreen(
    userInitials: String = "AR",
    userName: String = "Alex Rivera",
    userEmail: String = "alex.rivera@example.com",
    onExploreClick: () -> Unit,
    onReservationsClick: () -> Unit,
    onCommunityClick: () -> Unit,
    onSignOutClick: () -> Unit
) {
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
                    selected = false,
                    onClick = onCommunityClick,
                    icon = { Icon(Icons.Default.Groups, contentDescription = "Community") },
                    label = { Text("Community", fontSize = 11.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = BoothPrimary,
                        selectedTextColor = BoothPrimary,
                        indicatorColor = Color(0xFFEFF6FF)
                    )
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
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
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, Color(0xFFE2E8F0))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        InitialsAvatar(
                            initials = userInitials,
                            size = 72.dp,
                            backgroundColor = BoothPrimary
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = userName,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0F172A),
                            fontSize = 20.sp
                        )

                        Text(
                            text = userEmail,
                            fontSize = 12.5.sp,
                            color = Color(0xFF64748B)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = Color(0xFFEFF6FF)
                            ) {
                                Text(
                                    text = "Customer Account",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = BoothPrimary,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }

                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = Color(0xFFF0FDF4)
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(Icons.Default.VerifiedUser, contentDescription = null, tint = BoothSuccess, modifier = Modifier.size(13.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "SafePass Verified",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = BoothSuccess
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFFF8FAFC), RoundedCornerShape(14.dp))
                                .padding(vertical = 12.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ProfileStatItem(count = "6", label = "Tables Joined")
                            Box(modifier = Modifier.width(1.dp).height(28.dp).background(Color(0xFFCBD5E1)))
                            ProfileStatItem(count = "2", label = "Tables Hosted")
                            Box(modifier = Modifier.width(1.dp).height(28.dp).background(Color(0xFFCBD5E1)))
                            ProfileStatItem(count = "3", label = "Circles")
                        }
                    }
                }
            }

            item {
                Text(
                    text = "Account & Security",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
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
                        ProfileMenuRow(
                            icon = Icons.Default.Fingerprint,
                            title = "Biometric SafePass",
                            subtitle = "Face recognition verification active",
                            trailingText = "Active ✓"
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0xFFF1F5F9)))
                        Spacer(modifier = Modifier.height(12.dp))
                        ProfileMenuRow(
                            icon = Icons.Default.Notifications,
                            title = "Table Notifications",
                            subtitle = "Invites, circle alerts, and pass reminders",
                            trailingText = "On"
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0xFFF1F5F9)))
                        Spacer(modifier = Modifier.height(12.dp))
                        ProfileMenuRow(
                            icon = Icons.Default.Security,
                            title = "Privacy & Data",
                            subtitle = "Manage biometric cache & profile visibility",
                            trailingText = null
                        )
                    }
                }
            }

            item {
                Text(
                    text = "Actions",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
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
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { }
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(Color(0xFFEFF6FF), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Storefront, contentDescription = null, tint = BoothPrimary, modifier = Modifier.size(18.dp))
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text("Switch to Merchant Mode", fontWeight = FontWeight.Bold, fontSize = 13.5.sp, color = Color(0xFF0F172A))
                                Text("Manage cafe seats and bookings", fontSize = 11.5.sp, color = Color(0xFF64748B))
                            }
                            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color(0xFF94A3B8), modifier = Modifier.size(20.dp))
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0xFFF1F5F9)))
                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onSignOutClick() }
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(Color(0xFFFEE2E2), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Logout, contentDescription = null, tint = Color(0xFFDC2626), modifier = Modifier.size(18.dp))
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Text("Sign Out", fontWeight = FontWeight.Bold, fontSize = 13.5.sp, color = Color(0xFFDC2626))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ProfileStatItem(count: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = count, fontWeight = FontWeight.Bold, fontSize = 17.sp, color = BoothPrimary)
        Text(text = label, fontSize = 10.5.sp, color = Color(0xFF64748B))
    }
}

@Composable
private fun ProfileMenuRow(
    icon: ImageVector,
    title: String,
    subtitle: String,
    trailingText: String?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(Color(0xFFF1F5F9), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = BoothPrimary, modifier = Modifier.size(18.dp))
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 13.5.sp, color = Color(0xFF0F172A))
            Text(text = subtitle, fontSize = 11.sp, color = Color(0xFF64748B))
        }
        if (trailingText != null) {
            Surface(shape = RoundedCornerShape(8.dp), color = Color(0xFFEFF6FF)) {
                Text(
                    text = trailingText,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = BoothPrimary,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                )
            }
        } else {
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color(0xFF94A3B8), modifier = Modifier.size(18.dp))
        }
    }
}

