package org.umn.booth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.umn.booth.navigation.BoothNavGraph
import org.umn.booth.ui.screens.ExploreScreen
import org.umn.booth.ui.screens.FaceVerificationScreen
import org.umn.booth.ui.screens.FloorPlanScreen
import org.umn.booth.ui.screens.PreOrderScreen
import org.umn.booth.ui.screens.RegistrationScreen
import org.umn.booth.ui.screens.ReservationPassScreen
import org.umn.booth.ui.screens.ReviewFeedbackScreen
import org.umn.booth.ui.screens.RoleSelectionScreen
import org.umn.booth.ui.screens.VenueDetailScreen
import org.umn.booth.ui.theme.BOOTHTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BOOTHTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    BoothNavGraph()
                }
            }
        }
    }
}



@Preview(name = "1. Role Selection", showBackground = true, showSystemUi = true)
@Composable
fun PreviewRoleSelection() {
    BOOTHTheme {
        RoleSelectionScreen(
            onContinue = {},
            onLoginClick = {}
        )
    }
}

@Preview(name = "2. Registration Form", showBackground = true, showSystemUi = true)
@Composable
fun PreviewRegistration() {
    BOOTHTheme {
        RegistrationScreen(
            onNavigateBack = {},
            onContinue = {},
            onLoginClick = {}
        )
    }
}

@Preview(name = "2b. Profile Picture Upload", showBackground = true, showSystemUi = true)
@Composable
fun PreviewProfilePicture() {
    BOOTHTheme {
        org.umn.booth.ui.screens.ProfilePictureScreen(
            userInitials = "AR",
            onNavigateBack = {},
            onContinue = {}
        )
    }
}

@Preview(name = "3. Face Verification Mockup", showBackground = true, showSystemUi = true)
@Composable
fun PreviewFaceVerification() {
    BOOTHTheme {
        FaceVerificationScreen(
            onNavigateBack = {},
            onVerificationSuccess = {}
        )
    }
}

@Preview(name = "4. Explore Dashboard", showBackground = true, showSystemUi = true)
@Composable
fun PreviewExplore() {
    BOOTHTheme {
        ExploreScreen(
            userInitials = "AR",
            onVenueClick = {},
            onJoinTableWithCode = {},
            onReservationsClick = {}
        )
    }
}

@Preview(name = "5. Venue Detail", showBackground = true, showSystemUi = true)
@Composable
fun PreviewVenueDetail() {
    BOOTHTheme {
        VenueDetailScreen(
            venueId = "kopi-nako-serpong",
            onNavigateBack = {},
            onBrowseSeatsClick = {}
        )
    }
}

@Preview(name = "6. Floor Plan & Join Table", showBackground = true, showSystemUi = true)
@Composable
fun PreviewFloorPlan() {
    BOOTHTheme {
        FloorPlanScreen(
            venueId = "kopi-nako-serpong",
            onNavigateBack = {},
            onProceedToPreOrder = {}
        )
    }
}

@Preview(name = "7. Pre-Order Menu", showBackground = true, showSystemUi = true)
@Composable
fun PreviewPreOrder() {
    BOOTHTheme {
        PreOrderScreen(
            tableId = "tbl-04",
            onNavigateBack = {},
            onConfirmAndPay = { _, _ -> }
        )
    }
}

@Preview(name = "8. Reservation Pass Ticket", showBackground = true, showSystemUi = true)
@Composable
fun PreviewReservationPass() {
    BOOTHTheme {
        ReservationPassScreen(
            onNavigateBack = {},
            onFinishSession = {}
        )
    }
}

@Preview(name = "9. Review & Feedback", showBackground = true, showSystemUi = true)
@Composable
fun PreviewReviewFeedback() {
    BOOTHTheme {
        ReviewFeedbackScreen(
            onNavigateBack = {},
            onSubmitSuccess = {}
        )
    }
}