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
import org.umn.booth.ui.screens.CommunityScreen
import org.umn.booth.ui.screens.ExploreScreen
import org.umn.booth.ui.screens.FaceVerificationScreen
import org.umn.booth.ui.screens.FloorPlanScreen
import org.umn.booth.ui.screens.PreOrderScreen
import org.umn.booth.ui.screens.ProfilePictureScreen
import org.umn.booth.ui.screens.ProfileScreen
import org.umn.booth.ui.screens.RegistrationScreen
import org.umn.booth.ui.screens.ReservationPassScreen
import org.umn.booth.ui.screens.ReviewFeedbackScreen
import org.umn.booth.ui.screens.RoleSelectionScreen
import org.umn.booth.ui.screens.SplashScreen
import org.umn.booth.ui.screens.VenueDetailScreen

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
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
    data object Community : Screen("community")
    data object Profile : Screen("profile")
}

@Composable
fun BoothNavGraph(
    darkTheme: Boolean = false,
    onDarkThemeChange: (Boolean) -> Unit = {},
    navController: NavHostController = rememberNavController()
) {
    var registeredUserInitials by remember { mutableStateOf("AR") }

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(Screen.Splash.route) {
            SplashScreen(
                onTimeout = {
                    navController.navigate(Screen.RoleSelection.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

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

        composable(Screen.ProfilePicture.route) {
            ProfilePictureScreen(
                userInitials = registeredUserInitials,
                onNavigateBack = { navController.popBackStack() },
                onContinue = {
                    navController.navigate(Screen.FaceVerification.route)
                }
            )
        }

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

        composable(Screen.Explore.route) {
            ExploreScreen(
                userInitials = registeredUserInitials,
                onVenueClick = { venueId ->
                    navController.navigate(Screen.VenueDetail.createRoute(venueId))
                },
                onJoinTableWithCode = { _ ->
                    navController.navigate(Screen.Community.route)
                },
                onReservationsClick = {
                    navController.navigate(Screen.ReservationPass.route)
                },
                onCommunityClick = {
                    navController.navigate(Screen.Community.route)
                },
                onProfileClick = {
                    navController.navigate(Screen.Profile.route)
                }
            )
        }

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
                },
                onReservationsClick = {
                    navController.navigate(Screen.ReservationPass.route)
                },
                onCommunityClick = {
                    navController.navigate(Screen.Community.route)
                },
                onProfileClick = {
                    navController.navigate(Screen.Profile.route)
                }
            )
        }

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
                    navController.navigate(Screen.ReservationPass.route)
                }
            )
        }

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

        composable(Screen.ReservationPass.route) {
            ReservationPassScreen(
                onNavigateBack = { navController.popBackStack() },
                onFinishSession = {
                    navController.navigate(Screen.ReviewFeedback.route)
                }
            )
        }

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

        composable(Screen.Community.route) {
            CommunityScreen(
                userInitials = registeredUserInitials,
                onExploreClick = { navController.navigate(Screen.Explore.route) },
                onReservationsClick = { navController.navigate(Screen.ReservationPass.route) },
                onProfileClick = { navController.navigate(Screen.Profile.route) },
                onCommunityTableClick = { venueId ->
                    navController.navigate(Screen.FloorPlan.createRoute(venueId))
                }
            )
        }

        composable(Screen.Profile.route) {
            ProfileScreen(
                userInitials = registeredUserInitials,
                userName = "Alex Rivera",
                userEmail = "alex.rivera@example.com",
                darkTheme = darkTheme,
                onDarkThemeChange = onDarkThemeChange,
                onExploreClick = { navController.navigate(Screen.Explore.route) },
                onReservationsClick = { navController.navigate(Screen.ReservationPass.route) },
                onCommunityClick = { navController.navigate(Screen.Community.route) },
                onSignOutClick = {
                    navController.navigate(Screen.RoleSelection.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}

