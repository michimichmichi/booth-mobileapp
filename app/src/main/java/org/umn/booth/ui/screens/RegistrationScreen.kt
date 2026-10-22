package org.umn.booth.ui.screens

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.umn.booth.ui.theme.BoothBorder
import org.umn.booth.ui.theme.BoothPrimary
import org.umn.booth.ui.theme.BoothTextMuted
import org.umn.booth.ui.theme.BoothTextPrimary
import org.umn.booth.ui.theme.BoothTextSecondary

@Composable
fun RegistrationScreen(
    onNavigateBack: () -> Unit,
    onContinue: (String) -> Unit,
    onLoginClick: () -> Unit
) {

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var agreedToTerms by remember { mutableStateOf(false) }

    var isSubmitted by remember { mutableStateOf(false) }

    val isFullNameValid = fullName.trim().length >= 3
    val isEmailValid = email.contains("@") && email.contains(".")
    val isPhoneValid = phone.filter { it.isDigit() }.length in 8..15
    val isPasswordValid = password.length >= 8
    val isFormValid = isFullNameValid && isEmailValid && isPhoneValid && isPasswordValid && agreedToTerms

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
                            isSubmitted = true
                            if (isFormValid) {
                                val resolvedName = fullName.trim()
                                val initials = resolvedName.split(" ")
                                    .mapNotNull { it.firstOrNull()?.toString() }
                                    .take(2)
                                    .joinToString("")
                                    .uppercase()
                                onContinue(if (initials.isNotEmpty()) initials else "AR")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BoothPrimary,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = "Continue →",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { onLoginClick() }
                    ) {
                        Text(
                            text = "Already have an account? ",
                            style = MaterialTheme.typography.bodySmall,
                            color = BoothTextSecondary
                        )
                        Text(
                            text = "Log In",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            color = BoothPrimary
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
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
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
                            text = "Step 1 of 3",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = BoothPrimary
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFFEFF6FF),
                    modifier = Modifier.clickable {
                        fullName = "Alex Rivera"
                        email = "alex.rivera@example.com"
                        phone = "81234567890"
                        password = "SecurePassword123!"
                        agreedToTerms = true
                        isSubmitted = false
                    }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = BoothPrimary,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Autofill Demo",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = BoothPrimary
                        )
                    }
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
                        .background(Color(0xFFDCE7F3), RoundedCornerShape(2.dp))
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(4.dp)
                        .background(Color(0xFFDCE7F3), RoundedCornerShape(2.dp))
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Create Customer Account",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = BoothTextPrimary,
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Full Name",
                fontSize = 13.5.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1E293B)
            )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                placeholder = { Text("e.g. Alex Rivera", color = Color(0xFF94A3B8)) },
                isError = isSubmitted && !isFullNameValid,
                supportingText = {
                    if (isSubmitted && !isFullNameValid) {
                        Text(
                            text = "Full name is required (min 3 characters)",
                            color = Color(0xFFDC2626),
                            fontSize = 11.sp
                        )
                    }
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = if (isSubmitted && !isFullNameValid) Color(0xFFDC2626) else Color(0xFF94A3B8),
                        modifier = Modifier.size(20.dp)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = BoothPrimary,
                    unfocusedBorderColor = Color(0xFFE2E8F0),
                    errorBorderColor = Color(0xFFDC2626)
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Email Address",
                fontSize = 13.5.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1E293B)
            )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("name@example.com", color = Color(0xFF94A3B8)) },
                isError = isSubmitted && !isEmailValid,
                supportingText = {
                    if (isSubmitted && !isEmailValid) {
                        Text(
                            text = "Valid email address required (e.g. name@example.com)",
                            color = Color(0xFFDC2626),
                            fontSize = 11.sp
                        )
                    }
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AlternateEmail,
                        contentDescription = null,
                        tint = if (isSubmitted && !isEmailValid) Color(0xFFDC2626) else Color(0xFF94A3B8),
                        modifier = Modifier.size(20.dp)
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = BoothPrimary,
                    unfocusedBorderColor = Color(0xFFE2E8F0),
                    errorBorderColor = Color(0xFFDC2626)
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Phone Number",
                fontSize = 13.5.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1E293B)
            )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = phone,
                onValueChange = { input ->

                    phone = input.filter { it.isDigit() || it == '-' || it == ' ' }
                },
                placeholder = { Text("812-3456-7890", color = Color(0xFF94A3B8)) },
                isError = isSubmitted && !isPhoneValid,
                supportingText = {
                    if (isSubmitted && !isPhoneValid) {
                        Text(
                            text = "Valid phone number required (min 8 digits)",
                            color = Color(0xFFDC2626),
                            fontSize = 11.sp
                        )
                    }
                },
                leadingIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(start = 12.dp, end = 6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = null,
                            tint = if (isSubmitted && !isPhoneValid) Color(0xFFDC2626) else BoothPrimary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "+62",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = Color(0xFF1E293B)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .width(1.dp)
                                .height(20.dp)
                                .background(Color(0xFFCBD5E1))
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = BoothPrimary,
                    unfocusedBorderColor = Color(0xFFE2E8F0),
                    errorBorderColor = Color(0xFFDC2626)
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Password",
                fontSize = 13.5.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1E293B)
            )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Create secure password", color = Color(0xFF94A3B8)) },
                isError = isSubmitted && !isPasswordValid,
                supportingText = {
                    if (isSubmitted && !isPasswordValid) {
                        Text(
                            text = "Password must be at least 8 characters",
                            color = Color(0xFFDC2626),
                            fontSize = 11.sp
                        )
                    }
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = if (isSubmitted && !isPasswordValid) Color(0xFFDC2626) else Color(0xFF94A3B8),
                        modifier = Modifier.size(20.dp)
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = "Toggle password visibility",
                            tint = Color(0xFF94A3B8),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = BoothPrimary,
                    unfocusedBorderColor = Color(0xFFE2E8F0),
                    errorBorderColor = Color(0xFFDC2626)
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Password strength",
                fontSize = 11.5.sp,
                color = Color(0xFF64748B)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                repeat(4) { idx ->
                    val isSegmentFilled = when (idx) {
                        0 -> password.isNotEmpty()
                        1 -> password.length >= 6
                        2 -> password.length >= 8
                        3 -> password.any { it.isDigit() || !it.isLetterOrDigit() }
                        else -> false
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(3.5.dp)
                            .background(
                                if (isSegmentFilled) BoothPrimary else Color(0xFFDCE7F3),
                                RoundedCornerShape(2.dp)
                            )
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Must be at least 8 characters with 1 number or symbol",
                fontSize = 11.sp,
                color = Color(0xFF94A3B8)
            )

            Spacer(modifier = Modifier.height(18.dp))

            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { agreedToTerms = !agreedToTerms }
            ) {
                Checkbox(
                    checked = agreedToTerms,
                    onCheckedChange = { agreedToTerms = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = BoothPrimary,
                        uncheckedColor = if (isSubmitted && !agreedToTerms) Color(0xFFDC2626) else Color(0xFF94A3B8)
                    ),
                    modifier = Modifier.padding(end = 6.dp)
                )

                Text(
                    text = buildAnnotatedString {
                        append("I agree to the ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = BoothPrimary)) {
                            append("Terms of Service")
                        }
                        append(" and ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = BoothPrimary)) {
                            append("Privacy Policy")
                        }
                        append(", and consent to biometric verification for account authenticity.")
                    },
                    fontSize = 11.5.sp,
                    color = if (isSubmitted && !agreedToTerms) Color(0xFFDC2626) else Color(0xFF64748B),
                    lineHeight = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

