========================================================================
MID EXAM ODD SEMESTER 2026/2027
IF570L LAB MOBILE APPLICATION PROGRAMMING - UNIVERSITAS MULTIMEDIA NUSANTARA
========================================================================

Project Name : BOOTH (Social Matchmaking & Table Reservation App)
Group Name   : Le Mineral Le Group
Package Name : org.umn.booth
Platform     : Android (Jetpack Compose, Material 3, Kotlin)

------------------------------------------------------------------------
A. BRIEF DESCRIPTION OF THE APP
------------------------------------------------------------------------
BOOTH is an integrated Android mobile application designed to solve choice overload 
and coordination friction for young professionals, students, and communities 
engaging in Work From Cafe (WFC), community circles, and casual meetups. 

Key Features Implemented:
1. Role Selection (Customer vs Merchant)
2. Customer Registration Form with real-time input validation (Full Name, 
   Email Address format, Phone numeric format, and Password strength)
3. Live Biometric / Face Verification UI Simulation (Native Feature Mockup 
   featuring camera oval guide, biometric guarantee, and simulated scanning progress)
4. Main Dashboard / Place Exploring with search query, category filtering 
   (All Hubs, WFC Friendly, Sports & Padel), and Table Invite Code redemption
5. Venue Detail Screen consuming dynamic navigation arguments (venue ID), 
   displaying venue amenities, photos, operating hours, and average spend
6. Floor Plan & Table Selection featuring host profile (with user initials), 
   interactive seat reservation, and physical floor plan map
7. Pre-Order Menu with dynamic item counters, subtotal calculation in IDR, 
   and individual QRIS checkout policy notice
8. Active Reservation Pass Digital Ticket with simulated QR code and 
   Offline-Ready Room DB caching status badge
9. Venue & Table Review / Feedback with 5-star ratings, selectable vibe compliments, 
   character-counted feedback text area, and Community connection toggle.

------------------------------------------------------------------------
B. GROUP MEMBERS & TASK DIVISION
------------------------------------------------------------------------
Group Name: Le Mineral Le Group

Member 1:
- Name: [Student Full Name 1]
- NIM : [Student ID 1]
- Role: Lead UI & Theming Architect
- Specific Contribution: Designed and configured custom Material 3 Theme (Colors, 
  Typography, Shapes) matching Booth brand palette, and built Common UI Components 
  (BoothTopAppBar, InitialsAvatar, RatingPill, BoothPrimaryButton).

Member 2:
- Name: [Student Full Name 2]
- NIM : [Student ID 2]
- Role: Navigation & Form Validation Engineer
- Specific Contribution: Implemented Jetpack Compose NavHost with dynamic argument 
  passing (venueId, tableId) and back-stack handling; built Registration form with 
  real-time regex validation for email, phone, and password.

Member 3:
- Name: [Student Full Name 3]
- NIM : [Student ID 3]
- Role: Native Feature & Simulation Specialist
- Specific Contribution: Built Face Verification camera viewfinder mockup with 
  Canvas-drawn oval face guide, simulated laser scanning state transitions, and 
  biometric guarantee security card.

Member 4:
- Name: [Student Full Name 4]
- NIM : [Student ID 4]
- Role: State Management & Domain Data Modeler
- Specific Contribution: Modeled domain data classes (Venue, TableSession, MenuItem, 
  ReservationTicket) and implemented dynamic state interactions for pre-order cart, 
  category filtering, seat selection, and feedback reviews.

------------------------------------------------------------------------
C. WORKING SYSTEM & COLLABORATION
------------------------------------------------------------------------
The team adopted an agile workflow:
- Sprint 1: PPT/Figma mockup decomposition into Compose components and theme token definition.
- Sprint 2: Data models and Navigation Graph architecture with argument passing.
- Sprint 3: Screen UI layout implementation following Compose best practices (LazyColumn, 
  Modifier chaining, proper weight/alignment).
- Sprint 4: State management, form validation, and simulated hardware mockup integration.
- Sprint 5: Testing, Gradle assembleDebug verification, and deliverable packaging.

------------------------------------------------------------------------
D. RUBRIC MAPPING CHECKLIST (100% EXCELLENT CRITERIA)
------------------------------------------------------------------------
[✓] 1. Screen Implementation (>= 5 screens matching proposal mockups):
       - Form/Input Screen: RegistrationScreen.kt
       - Native-Feature Mockup: FaceVerificationScreen.kt (Camera / Biometric UI)
       - List Screen: ExploreScreen.kt (LazyColumn of venues, search & filter)
       - Detail Screen: VenueDetailScreen.kt (Dynamic arguments)
       - Free Choice Screens: RoleSelectionScreen, FloorPlanScreen, PreOrderScreen, 
         ReservationPassScreen, ReviewFeedbackScreen.
[✓] 2. UI Theming: Custom Material 3 theme (Color.kt, Type.kt, Shape.kt, Theme.kt) 
       using PPT deep blue (#145388), surface containers, and custom typography.
[✓] 3. State Management: remember, mutableStateOf, mutableIntStateOf, mutableStateListOf 
       used across all user interactions (form validation, cart total, seat picking, ratings).
[✓] 4. Navigation: NavHost / NavController with dynamic argument passing 
       ("venue_detail/{venueId}", "floor_plan/{venueId}", "pre_order/{tableId}").
[✓] 5. Form Input & Validation: Email regex check, phone numeric validation, required 
       fields, password strength, isError + supportingText feedback.
[✓] 6. Dummy Data: Rich Kotlin data classes and realistic mock data populated in DummyData.kt.
[✓] 7. Build Output: app-debug.apk generated successfully under app/build/outputs/apk/debug/.
