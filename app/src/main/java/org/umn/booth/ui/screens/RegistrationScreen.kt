package org.umn.booth.ui.screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.umn.booth.ui.components.BoothPrimaryButton
import org.umn.booth.ui.components.BoothTopAppBar
import org.umn.booth.ui.theme.BoothBorder
import org.umn.booth.ui.theme.BoothError
import org.umn.booth.ui.theme.BoothPrimary
import org.umn.booth.ui.theme.BoothSuccess
import org.umn.booth.ui.theme.BoothTextMuted
import org.umn.booth.ui.theme.BoothTextPrimary
import org.umn.booth.ui.theme.BoothTextSecondary

@Composable
fun RegistrationScreen(
    onNavigateBack: () -> Unit,
    onContinue: (String) -> Unit,
    onLoginClick: () -> Unit
) {
    // Form Input States (Pre-filled with realistic proposal data for seamless demo & testing)
    var fullName by remember { mutableStateOf("Alex Rivera") }
    var email by remember { mutableStateOf("alex.rivera@example.com") }
    var phone by remember { mutableStateOf("+1 (555) 000-0000") }
    var password by remember { mutableStateOf("SecurePass123!") }
    var passwordVisible by remember { mutableStateOf(false) }
    var agreedToTerms by remember { mutableStateOf(true) }

    // Validation touched states
    var isSubmitted by remember { mutableStateOf(false) }

    // Validation rules
    val isFullNameValid = fullName.trim().length >= 3
    val isEmailValid = email.matches(Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"))
    val isPhoneValid = phone.filter { it.isDigit() }.length >= 9
    val isPasswordValid = password.length >= 8

    val canSubmit = isFullNameValid && isEmailValid && isPhoneValid && isPasswordValid && agreedToTerms

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
                .padding(horizontal = 24.dp, vertical = 10.dp)
        ) {
            // Step Indicator Badge (Slide 5)
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFFEFF6FF)
            ) {
                Text(
                    text = "• Step 1 of 3",
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = BoothPrimary,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Create Customer Account",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = BoothTextPrimary
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 1. Full Name Field (Slide 5: Label above field)
            Text(
                text = "Full Name",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                color = BoothTextPrimary
            )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                placeholder = { Text("e.g. Alex Rivera", color = BoothTextMuted) },
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = null, tint = BoothTextSecondary)
                },
                isError = isSubmitted && !isFullNameValid,
                supportingText = {
                    if (isSubmitted && !isFullNameValid) {
                        Text("Name must be at least 3 characters", color = BoothError)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BoothPrimary,
                    unfocusedBorderColor = BoothBorder,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 2. Email Address Field (Slide 5: @ icon and label)
            Text(
                text = "Email Address",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                color = BoothTextPrimary
            )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("name@example.com", color = BoothTextMuted) },
                leadingIcon = {
                    Icon(Icons.Default.AlternateEmail, contentDescription = null, tint = BoothTextSecondary)
                },
                isError = isSubmitted && !isEmailValid,
                supportingText = {
                    if (isSubmitted && !isEmailValid) {
                        Text("Please enter a valid email address", color = BoothError)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BoothPrimary,
                    unfocusedBorderColor = BoothBorder,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 3. Phone Number Field (Slide 5: Country code prefix + Phone number)
            Text(
                text = "Phone Number",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                color = BoothTextPrimary
            )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                placeholder = { Text("(555) 000-0000", color = BoothTextMuted) },
                leadingIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(start = 12.dp, end = 4.dp)
                    ) {
                        Icon(Icons.Default.Phone, contentDescription = null, tint = BoothTextSecondary, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("+1", fontWeight = FontWeight.Bold, color = BoothTextPrimary, fontSize = 14.sp)
                    }
                },
                isError = isSubmitted && !isPhoneValid,
                supportingText = {
                    if (isSubmitted && !isPhoneValid) {
                        Text("Enter a valid phone number", color = BoothError)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BoothPrimary,
                    unfocusedBorderColor = BoothBorder,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 4. Password Field (Slide 5: Password strength info)
            Text(
                text = "Password",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                color = BoothTextPrimary
            )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Create secure password", color = BoothTextMuted) },
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = null, tint = BoothTextSecondary)
                },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = if (passwordVisible) "Hide password" else "Show password",
                            tint = BoothTextSecondary
                        )
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                isError = isSubmitted && !isPasswordValid,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BoothPrimary,
                    unfocusedBorderColor = BoothBorder,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

            // Password strength feedback (Slide 5)
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Password strength",
                    style = MaterialTheme.typography.bodySmall,
                    color = BoothTextSecondary,
                    fontSize = 11.sp
                )
                Text(
                    text = if (isPasswordValid) "Strong" else "Too short",
                    style = MaterialTheme.typography.labelSmall,
                    color = if (isPasswordValid) BoothSuccess else BoothError,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "Must be at least 8 characters with 1 number or symbol",
                style = MaterialTheme.typography.bodySmall,
                color = BoothTextMuted,
                fontSize = 11.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Terms & Conditions Checkbox (Slide 5)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { agreedToTerms = !agreedToTerms },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = agreedToTerms,
                    onCheckedChange = { agreedToTerms = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = BoothPrimary,
                        uncheckedColor = if (isSubmitted && !agreedToTerms) BoothError else BoothBorder
                    )
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "I agree to the Terms of Service and Privacy Policy, and consent to biometric verification for account authenticity.",
                    style = MaterialTheme.typography.bodySmall,
                    color = if (isSubmitted && !agreedToTerms) BoothError else BoothTextSecondary,
                    lineHeight = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Continue Button (Slide 5)
            BoothPrimaryButton(
                text = "Continue →",
                onClick = {
                    isSubmitted = true
                    if (canSubmit) {
                        onContinue(fullName)
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
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
                    color = BoothPrimary,
                    modifier = Modifier.clickable { onLoginClick() }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
