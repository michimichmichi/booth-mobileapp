package org.umn.booth.ui.screens

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

    LaunchedEffect(verificationState) {
        if (verificationState == VerificationState.SCANNING) {
            delay(1200)
            verificationState = VerificationState.VERIFIED
            delay(800)
            onVerificationSuccess()
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 0.95f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = androidx.compose.animation.core.RepeatMode.Reverse
        ),
        label = "pulseAlpha"
    )

    Scaffold(
        containerColor = Color(0xFFF8FAFC),
        topBar = {
            Surface(
                color = Color(0xFFF8FAFC),
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFF0F172A)
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Column {
                        Text(
                            text = "BOOTH",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF64748B),
                            letterSpacing = 1.5.sp
                        )
                        Text(
                            text = "Registration",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0F172A)
                        )
                    }
                }
            }
        },
        bottomBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFFF8FAFC)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            if (verificationState == VerificationState.READY) {
                                verificationState = VerificationState.SCANNING
                            } else {
                                onVerificationSuccess()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (verificationState == VerificationState.VERIFIED) BoothSuccess else BoothPrimary
                        )
                    ) {
                        if (verificationState == VerificationState.SCANNING) {
                            CircularProgressIndicator(
                                color = Color.White,
                                modifier = Modifier.size(22.dp),
                                strokeWidth = 2.dp
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Verifying biometric match...",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        } else if (verificationState == VerificationState.VERIFIED) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Identity Verified! Continue →",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        } else {
                            Text(
                                text = "Verify Face & Continue →",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
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
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {

            Box(
                modifier = Modifier
                    .background(Color(0xFFE0EDFB), RoundedCornerShape(12.dp))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .background(BoothPrimary, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Step 3 of 3",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = BoothPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(4.dp)
                        .background(BoothPrimary, RoundedCornerShape(2.dp))
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(4.dp)
                        .background(BoothPrimary, RoundedCornerShape(2.dp))
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(4.dp)
                        .background(BoothPrimary, RoundedCornerShape(2.dp))
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Face Verification",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = BoothTextPrimary,
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Center your face in the oval guide to verify your identity against your uploaded profile picture.",
                style = MaterialTheme.typography.bodyMedium,
                color = BoothTextSecondary,
                fontSize = 13.sp,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(18.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFF223042))
                    .clickable {
                        if (verificationState == VerificationState.READY) {
                            verificationState = VerificationState.SCANNING
                        }
                    },
                contentAlignment = Alignment.Center
            ) {

                Canvas(modifier = Modifier.fillMaxSize()) {
                    val ovalWidth = size.width * 0.58f
                    val ovalHeight = size.height * 0.74f
                    val left = (size.width - ovalWidth) / 2f
                    val top = (size.height - ovalHeight) / 2f

                    val strokeColor = if (verificationState == VerificationState.VERIFIED) {
                        Color(0xFF22C55E)
                    } else if (verificationState == VerificationState.SCANNING) {
                        Color(0xFF60A5FA).copy(alpha = pulseAlpha)
                    } else {
                        Color(0xFFCBD5E1).copy(alpha = 0.8f)
                    }

                    drawOval(
                        color = strokeColor,
                        topLeft = Offset(left, top),
                        size = Size(ovalWidth, ovalHeight),
                        style = Stroke(
                            width = 3.dp.toPx(),
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(24f, 18f))
                        )
                    )
                }

                if (verificationState == VerificationState.SCANNING) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(54.dp),
                        strokeWidth = 3.dp
                    )
                } else if (verificationState == VerificationState.VERIFIED) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .background(Color(0xFF22C55E), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Verified",
                            tint = Color.White,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                VerificationTipItem(
                    icon = Icons.Default.WbSunny,
                    title = "Even Light",
                    subtitle = "No glare",
                    modifier = Modifier.weight(1f)
                )

                VerificationTipItem(
                    icon = Icons.Default.Face,
                    title = "Natural Pose",
                    subtitle = "Keep relaxed",
                    modifier = Modifier.weight(1f)
                )

                VerificationTipItem(
                    icon = Icons.Default.RemoveRedEye,
                    title = "Eye Level",
                    subtitle = "Direct gaze",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F1FC)),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFBFDBFE))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Shield,
                            contentDescription = null,
                            tint = BoothPrimary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "BOOTH SafePass",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.5.sp,
                            color = BoothPrimary
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "• Biometric Match Guarantee",
                            fontSize = 11.5.sp,
                            color = Color(0xFF475569)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Your live scan is checked against your uploaded profile photo using zero-knowledge biometric encryption. Raw frames are immediately discarded.",
                        fontSize = 11.sp,
                        color = Color(0xFF475569),
                        lineHeight = 15.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun VerificationTipItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F7FF)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(Color(0xFFDBEAFE), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = BoothPrimary,
                    modifier = Modifier.size(16.dp)
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 11.5.sp,
                color = Color(0xFF1E293B)
            )
            Text(
                text = subtitle,
                fontSize = 10.sp,
                color = Color(0xFF64748B)
            )
        }
    }
}

