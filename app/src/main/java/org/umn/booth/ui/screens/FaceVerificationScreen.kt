package org.umn.booth.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.umn.booth.ui.components.BoothPrimaryButton
import org.umn.booth.ui.components.BoothTopAppBar
import org.umn.booth.ui.theme.BoothBorder
import org.umn.booth.ui.theme.BoothPrimary
import org.umn.booth.ui.theme.BoothSuccess
import org.umn.booth.ui.theme.BoothTextMuted
import org.umn.booth.ui.theme.BoothTextPrimary
import org.umn.booth.ui.theme.BoothTextSecondary

enum class VerificationState {
    READY,
    SCANNING,
    VERIFIED
}

@Composable
fun FaceVerificationScreen(
    onNavigateBack: () -> Unit,
    onVerificationSuccess: () -> Unit
) {
    var verificationState by remember { mutableStateOf(VerificationState.READY) }
    var scanProgress by remember { mutableStateOf(0f) }

    // Scan animation trigger
    LaunchedEffect(verificationState) {
        if (verificationState == VerificationState.SCANNING) {
            scanProgress = 0f
            while (scanProgress < 1f) {
                delay(30)
                scanProgress += 0.02f
            }
            verificationState = VerificationState.VERIFIED
            delay(1000)
            onVerificationSuccess()
        }
    }

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
                .padding(horizontal = 24.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Step badge
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFFEFF6FF)
            ) {
                Text(
                    text = "• Step 3 of 3",
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = BoothPrimary,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Face Verification",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = BoothTextPrimary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Center your face in the oval guide to verify your identity against your uploaded profile picture.",
                style = MaterialTheme.typography.bodySmall,
                color = BoothTextSecondary,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Camera Viewfinder Simulation Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFF1E293B)),
                contentAlignment = Alignment.Center
            ) {
                // Oval Scanner Guide
                val infiniteTransition = rememberInfiniteTransition(label = "scanLine")
                val scanLineY by infiniteTransition.animateFloat(
                    initialValue = 0.2f,
                    targetValue = 0.8f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(1200, easing = FastOutSlowInEasing),
                        repeatMode = RepeatMode.Reverse
                    ),
                    label = "scanLineY"
                )

                Canvas(modifier = Modifier.fillMaxSize()) {
                    val ovalWidth = size.width * 0.58f
                    val ovalHeight = size.height * 0.76f
                    val left = (size.width - ovalWidth) / 2
                    val top = (size.height - ovalHeight) / 2

                    // Dashed oval guide
                    drawOval(
                        color = when (verificationState) {
                            VerificationState.READY -> Color.White.copy(alpha = 0.6f)
                            VerificationState.SCANNING -> Color(0xFF38BDF8)
                            VerificationState.VERIFIED -> Color(0xFF10B981)
                        },
                        topLeft = Offset(left, top),
                        size = Size(ovalWidth, ovalHeight),
                        style = Stroke(
                            width = 4f,
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(24f, 16f), 0f)
                        )
                    )

                    // Laser Scanning Line when scanning
                    if (verificationState == VerificationState.SCANNING) {
                        val currentY = top + (ovalHeight * scanLineY)
                        drawLine(
                            color = Color(0xFF38BDF8),
                            start = Offset(left + 20f, currentY),
                            end = Offset(left + ovalWidth - 20f, currentY),
                            strokeWidth = 5f
                        )
                    }
                }

                // Inner Status Icon / Feedback
                when (verificationState) {
                    VerificationState.READY -> {
                        Icon(
                            imageVector = Icons.Default.Face,
                            contentDescription = "Face Guide",
                            tint = Color.White.copy(alpha = 0.35f),
                            modifier = Modifier.size(72.dp)
                        )
                    }
                    VerificationState.SCANNING -> {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator(
                                progress = { scanProgress },
                                color = Color(0xFF38BDF8),
                                trackColor = Color.White.copy(alpha = 0.2f),
                                modifier = Modifier.size(54.dp)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Scanning facial landmarks... ${(scanProgress * 100).toInt()}%",
                                style = MaterialTheme.typography.labelMedium,
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                    VerificationState.VERIFIED -> {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = "Verified",
                                tint = BoothSuccess,
                                modifier = Modifier.size(64.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Match Confirmed (99.4%)",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Guidance Tips Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                GuidelineItem(
                    icon = Icons.Default.WbSunny,
                    label = "Even Light",
                    sub = "No glare"
                )
                GuidelineItem(
                    icon = Icons.Default.Face,
                    label = "Natural Pose",
                    sub = "Keep relaxed"
                )
                GuidelineItem(
                    icon = Icons.Default.RemoveRedEye,
                    label = "Eye Level",
                    sub = "Direct gaze"
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Security Guarantee Banner
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                border = androidx.compose.foundation.BorderStroke(1.dp, BoothBorder)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Shield,
                        contentDescription = null,
                        tint = BoothPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "BOOTH SafePass • Biometric Match Guarantee",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = BoothTextPrimary
                        )
                        Text(
                            text = "Your live scan is checked against your uploaded profile photo using zero-knowledge biometric encryption.",
                            style = MaterialTheme.typography.labelSmall,
                            color = BoothTextSecondary,
                            lineHeight = 14.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Action Buttons
            BoothPrimaryButton(
                text = when (verificationState) {
                    VerificationState.READY -> "Verify & Continue →"
                    VerificationState.SCANNING -> "Verifying Face..."
                    VerificationState.VERIFIED -> "Verified! Entering Booth..."
                },
                enabled = verificationState == VerificationState.READY,
                onClick = {
                    verificationState = VerificationState.SCANNING
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            androidx.compose.material3.TextButton(
                onClick = onVerificationSuccess,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Skip to Explore →",
                    style = MaterialTheme.typography.bodyMedium,
                    color = BoothTextSecondary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun GuidelineItem(
    icon: ImageVector,
    label: String,
    sub: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(Color(0xFFE2E8F0), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BoothPrimary,
                modifier = Modifier.size(18.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = BoothTextPrimary
        )
        Text(
            text = sub,
            style = MaterialTheme.typography.bodySmall,
            color = BoothTextMuted,
            fontSize = 11.sp
        )
    }
}
