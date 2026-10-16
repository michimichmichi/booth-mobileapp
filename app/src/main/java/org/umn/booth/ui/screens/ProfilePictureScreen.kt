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
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.umn.booth.ui.components.BoothPrimaryButton
import org.umn.booth.ui.components.BoothTopAppBar
import org.umn.booth.ui.components.InitialsAvatar
import org.umn.booth.ui.theme.BoothBorder
import org.umn.booth.ui.theme.BoothPrimary
import org.umn.booth.ui.theme.BoothSuccess
import org.umn.booth.ui.theme.BoothTextMuted
import org.umn.booth.ui.theme.BoothTextPrimary
import org.umn.booth.ui.theme.BoothTextSecondary

@Composable
fun ProfilePictureScreen(
    userInitials: String = "AR",
    onNavigateBack: () -> Unit,
    onContinue: () -> Unit
) {
    var hasPhotoSelected by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            BoothTopAppBar(
                title = "BOOTH",
                subtitle = "Registration",
                canNavigateBack = true,
                onNavigateBack = onNavigateBack
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Step Indicator Badge
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFFEFF6FF)
            ) {
                Text(
                    text = "• Step 2 of 3",
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = BoothPrimary,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Add Your Profile Picture",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = BoothTextPrimary
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Avatar Upload Container (Slide 6 UI)
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFEFF6FF))
                    .border(2.dp, if (hasPhotoSelected) BoothPrimary else BoothBorder, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                if (hasPhotoSelected) {
                    InitialsAvatar(
                        initials = userInitials,
                        size = 136.dp,
                        backgroundColor = BoothPrimary
                    )
                } else {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = BoothTextMuted,
                            modifier = Modifier.size(54.dp)
                        )
                        Text(
                            text = "Portrait photo",
                            style = MaterialTheme.typography.labelSmall,
                            color = BoothTextSecondary
                        )
                    }
                }

                // Camera overlay pill
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .align(Alignment.BottomEnd)
                        .clip(CircleShape)
                        .background(BoothPrimary)
                        .border(2.dp, Color.White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = "Change photo",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = if (hasPhotoSelected) "✓ Profile photo ready" else "No photo selected",
                style = MaterialTheme.typography.bodySmall,
                color = if (hasPhotoSelected) BoothSuccess else BoothTextMuted,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Action Buttons: Take Selfie & From Files
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = { hasPhotoSelected = true },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.5.dp, BoothPrimary)
                ) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = null,
                        tint = BoothPrimary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Take Selfie",
                        fontWeight = FontWeight.Bold,
                        color = BoothPrimary,
                        fontSize = 13.sp
                    )
                }

                Button(
                    onClick = { hasPhotoSelected = true },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Icon(
                        imageVector = Icons.Default.FolderOpen,
                        contentDescription = null,
                        tint = BoothTextPrimary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "From Files",
                        fontWeight = FontWeight.Bold,
                        color = BoothTextPrimary,
                        fontSize = 13.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Photo Requirements Card (Slide 6 checklist)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, BoothBorder)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Photo requirements",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = BoothTextPrimary
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    RequirementItem(
                        title = "Clear lighting & high resolution",
                        desc = "Even illumination without glare, shadows, or grain"
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    RequirementItem(
                        title = "Solo shot, facing directly forward",
                        desc = "Neutral posture with shoulders centered in frame"
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    RequirementItem(
                        title = "No sunglasses, masks, or beauty filters",
                        desc = "Eyes, eyebrows, and jawline must remain fully identifiable"
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(24.dp))

            // Continue Button
            BoothPrimaryButton(
                text = "Continue →",
                onClick = onContinue
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun RequirementItem(title: String, desc: String) {
    Row(verticalAlignment = Alignment.Top) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = BoothSuccess,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                color = BoothTextPrimary
            )
            Text(
                text = desc,
                style = MaterialTheme.typography.bodySmall,
                color = BoothTextSecondary,
                fontSize = 11.sp,
                lineHeight = 14.sp
            )
        }
    }
}
