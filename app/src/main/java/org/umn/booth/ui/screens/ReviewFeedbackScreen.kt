package org.umn.booth.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.umn.booth.ui.components.BoothPrimaryButton
import org.umn.booth.ui.components.BoothTopAppBar
import org.umn.booth.ui.components.InitialsAvatar
import org.umn.booth.ui.theme.BoothBorder
import org.umn.booth.ui.theme.BoothPrimary
import org.umn.booth.ui.theme.BoothTextMuted
import org.umn.booth.ui.theme.BoothTextPrimary
import org.umn.booth.ui.theme.BoothTextSecondary

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ReviewFeedbackScreen(
    onNavigateBack: () -> Unit,
    onSubmitSuccess: () -> Unit
) {
    // Review States
    var overallRating by remember { mutableIntStateOf(5) }
    var ambianceRating by remember { mutableIntStateOf(5) }
    var personalFeedback by remember { mutableStateOf("") }
    var addToCommunity by remember { mutableStateOf(true) }

    val selectedCompliments = remember {
        mutableStateListOf("Friendly & Welcoming", "Productive Work Vibe")
    }

    val availableCompliments = listOf(
        "Friendly & Welcoming",
        "Productive Work Vibe",
        "Punctual Members",
        "Great Conversations",
        "Fast Wi-Fi & Cozy Table"
    )

    Scaffold(
        topBar = {
            BoothTopAppBar(
                title = "Review and Feedback",
                canNavigateBack = true,
                onNavigateBack = onNavigateBack,
                actions = {
                    InitialsAvatar(
                        initials = "AR",
                        size = 36.dp,
                        modifier = Modifier.padding(end = 12.dp)
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            // Session Header
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, BoothBorder)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    androidx.compose.foundation.Image(
                        painter = androidx.compose.ui.res.painterResource(id = org.umn.booth.R.drawable.img_kopi_nako),
                        contentDescription = "Kopi Nako Serpong",
                        contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                        modifier = Modifier
                            .size(52.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Afternoon WFC & Tech Chat",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = BoothTextPrimary
                        )
                        Text(
                            text = "Kopi Nako Serpong, Table 04",
                            style = MaterialTheme.typography.bodySmall,
                            color = BoothTextSecondary
                        )
                        Text(
                            text = "Sarah K. (Table Host)",
                            style = MaterialTheme.typography.bodySmall,
                            color = BoothPrimary,
                            fontWeight = FontWeight.Medium,
                            fontSize = 11.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Rating Question 1: Overall Hangout Experience
            Text(
                text = "How was your hangout experience?",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = BoothTextPrimary
            )
            Text(
                text = "Your honest rating supports table curations",
                style = MaterialTheme.typography.bodySmall,
                color = BoothTextSecondary
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Star Rating Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                for (star in 1..5) {
                    IconButton(
                        onClick = { overallRating = star },
                        modifier = Modifier.size(46.dp)
                    ) {
                        Icon(
                            imageVector = if (star <= overallRating) Icons.Default.Star else Icons.Default.StarBorder,
                            contentDescription = "Star $star",
                            tint = Color(0xFFF59E0B),
                            modifier = Modifier.size(34.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Rating Question 2: Cafe Ambiance
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Cafe Ambiance",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = BoothTextPrimary
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFF59E0B),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "$ambianceRating.0 / 5.0",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF92400E)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Table Vibe Compliments Chips
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Table Vibe Compliments",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = BoothTextPrimary
                )
                Text(
                    text = "Tap to tag",
                    style = MaterialTheme.typography.bodySmall,
                    color = BoothTextMuted,
                    fontSize = 11.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                availableCompliments.forEach { compliment ->
                    val isSelected = selectedCompliments.contains(compliment)
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = if (isSelected) BoothPrimary else MaterialTheme.colorScheme.surfaceVariant,
                        border = BorderStroke(1.dp, if (isSelected) BoothPrimary else BoothBorder),
                        modifier = Modifier.clickable {
                            if (isSelected) {
                                selectedCompliments.remove(compliment)
                            } else {
                                selectedCompliments.add(compliment)
                            }
                        }
                    ) {
                        Text(
                            text = if (isSelected) "✓ $compliment" else compliment,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Medium,
                            color = if (isSelected) Color.White else BoothTextSecondary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Personal Feedback Text Area
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Personal Feedback",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = BoothTextPrimary
                )
                Text(
                    text = "${personalFeedback.length}/300",
                    style = MaterialTheme.typography.bodySmall,
                    color = BoothTextMuted,
                    fontSize = 11.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = personalFeedback,
                onValueChange = { if (it.length <= 300) personalFeedback = it },
                placeholder = {
                    Text(
                        "Share key highlights, topics discussed, or tips for future guests at this table...",
                        fontSize = 13.sp
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BoothPrimary,
                    unfocusedBorderColor = BoothBorder,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Add to Your Community Toggle Switch Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, BoothBorder)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Add to Your Community",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = BoothTextPrimary
                        )
                        Text(
                            text = "Receive priority invites whenever Sarah opens a new table in Serpong",
                            style = MaterialTheme.typography.bodySmall,
                            color = BoothTextSecondary,
                            fontSize = 11.sp,
                            lineHeight = 14.sp
                        )
                    }

                    Switch(
                        checked = addToCommunity,
                        onCheckedChange = { addToCommunity = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = BoothPrimary
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Submit Button
            BoothPrimaryButton(
                text = "Submit Review & Finish →",
                onClick = onSubmitSuccess
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextButton(
                onClick = onSubmitSuccess,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Skip for now",
                    style = MaterialTheme.typography.bodyMedium,
                    color = BoothTextSecondary
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
