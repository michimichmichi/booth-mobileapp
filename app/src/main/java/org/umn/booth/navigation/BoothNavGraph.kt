package org.umn.booth.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.umn.booth.ui.screens.ExploreScreen
import org.umn.booth.ui.screens.FaceVerificationScreen
import org.umn.booth.ui.screens.FloorPlanScreen
import org.umn.booth.ui.screens.PreOrderScreen
import org.umn.booth.ui.screens.ProfilePictureScreen
import org.umn.booth.ui.screens.RegistrationScreen
import org.umn.booth.ui.screens.ReservationPassScreen
import org.umn.booth.ui.screens.ReviewFeedbackScreen
import org.umn.booth.ui.screens.RoleSelectionScreen
import org.umn.booth.ui.screens.VenueDetailScreen

sealed class Screen(val route: String) {
    data object RoleSelection : Screen("role_selection")
    data object Registration : Screen("registration")
    data object ProfilePicture : Screen("profile_picture")
    data object FaceVerification : Screen("face_verification")
    data object Explore : Screen("explore")
    data object VenueDetail : Screen("venue_detail/{venueId}") {
        fun createRoute(venueId: String) = "venue_detail/$venueId"
    }
    data object FloorPlan : Screen("floor_plan/{venueId}") {
        fun createRoute(venueId: String) = "floor_plan/$venueId"
    }
    data object PreOrder : Screen("pre_order/{tableId}") {
        fun createRoute(tableId: String) = "pre_order/$tableId"
    }
    data object ReservationPass : Screen("reservation_pass")
    data object ReviewFeedback : Screen("review_feedback")
}

@Composable
fun BoothNavGraph(
    navController: NavHostController = rememberNavController()
) {
    var registeredUserInitials by remember { mutableStateOf("AR") }

    NavHost(
        navController = navController,
        startDestination = Screen.RoleSelection.route
    ) {
        // 1. Role Selection Screen (Slide 4)
        composable(Screen.RoleSelection.route) {
            RoleSelectionScreen(
                onContinue = { role ->
                    navController.navigate(Screen.Registration.route)
                },
                onLoginClick = {
                    navController.navigate(Screen.Explore.route)
                }
            )
        }

        // 2. Registration Form Screen (Slide 5 - Form / Input)
        composable(Screen.Registration.route) {
            RegistrationScreen(
                onNavigateBack = { navController.popBackStack() },
                onContinue = { name ->
                    val initials = name.trim().split(" ")
                        .filter { it.isNotEmpty() }
                        .map { it.first().uppercase() }
                        .take(2)
                        .joinToString("")
                    if (initials.isNotEmpty()) {
                        registeredUserInitials = initials
                    }
                    navController.navigate(Screen.ProfilePicture.route)
                },
                onLoginClick = {
                    navController.navigate(Screen.Explore.route)
                }
            )
        }

        // 2b. Profile Picture Screen (Slide 6 - Step 2 of 3)
        composable(Screen.ProfilePicture.route) {
            ProfilePictureScreen(
                userInitials = registeredUserInitials,
                onNavigateBack = { navController.popBackStack() },
                onContinue = {
                    navController.navigate(Screen.FaceVerification.route)
                }
            )
        }

        // 3. Face Verification Screen (Slide 7 - Native Feature Mockup)
        composable(Screen.FaceVerification.route) {
            FaceVerificationScreen(
                onNavigateBack = { navController.popBackStack() },
                onVerificationSuccess = {
                    navController.navigate(Screen.Explore.route) {
                        popUpTo(Screen.RoleSelection.route) { inclusive = true }
                    }
                }
            )
        }

        // 4. Explore Dashboard Screen (Slide 8 - List Screen)
        composable(Screen.Explore.route) {
            ExploreScreen(
                userInitials = registeredUserInitials,
                onVenueClick = { venueId ->
                    navController.navigate(Screen.VenueDetail.createRoute(venueId))
                },
                onJoinTableWithCode = { _ ->
                    navController.navigate(Screen.FloorPlan.createRoute("kopi-nako-serpong"))
                },
                onReservationsClick = {
                    navController.navigate(Screen.ReservationPass.route)
                }
            )
        }

        // 5. Venue Detail Screen (Slide 9 - Detail Screen with Dynamic Argument)
        composable(
            route = Screen.VenueDetail.route,
            arguments = listOf(
                navArgument("venueId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val venueId = backStackEntry.arguments?.getString("venueId") ?: "kopi-nako-serpong"
            VenueDetailScreen(
                venueId = venueId,
                onNavigateBack = { navController.popBackStack() },
                onBrowseSeatsClick = { id: String ->
                    navController.navigate(Screen.FloorPlan.createRoute(id))
                }
            )
        }

        // 6. Floor Plan & Table Selection Screen (Slide 10)
        composable(
            route = Screen.FloorPlan.route,
            arguments = listOf(
                navArgument("venueId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val venueId = backStackEntry.arguments?.getString("venueId") ?: "kopi-nako-serpong"
            FloorPlanScreen(
                venueId = venueId,
                onNavigateBack = { navController.popBackStack() },
                onProceedToPreOrder = { tableId ->
                    navController.navigate(Screen.PreOrder.createRoute(tableId))
                }
            )
        }

        // 7. Pre-Order Menu Screen (Slide 11)
        composable(
            route = Screen.PreOrder.route,
            arguments = listOf(
                navArgument("tableId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val tableId = backStackEntry.arguments?.getString("tableId") ?: "tbl-04"
            PreOrderScreen(
                tableId = tableId,
                onNavigateBack = { navController.popBackStack() },
                onConfirmAndPay = { _, _ ->
                    navController.navigate(Screen.ReservationPass.route)
                }
            )
        }

        // 8. Digital Ticket / Reservation Pass Screen (Slide 12)
        composable(Screen.ReservationPass.route) {
            ReservationPassScreen(
                onNavigateBack = { navController.popBackStack() },
                onFinishSession = {
                    navController.navigate(Screen.ReviewFeedback.route)
                }
            )
        }

        // 9. Review and Feedback Screen (Slide 13)
        composable(Screen.ReviewFeedback.route) {
            ReviewFeedbackScreen(
                onNavigateBack = { navController.popBackStack() },
                onSubmitSuccess = {
                    navController.navigate(Screen.Explore.route) {
                        popUpTo(Screen.Explore.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
