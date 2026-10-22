A. BOOTH APP DESCRIPTION
------------------------------------------------------------------------------

Booth is a social app that connects people with shared interests and activities
while making it effortless to meet up and reserve spaces. Designed to solve
common hangout headaches such as venue indecision, lengthy location debates,
awkward bill splitting, and last minute cancellations. Booth brings community
discovery, table reservations, and food or drink pre-ordering into one smooth
flow. Through the app, users can easily discover cafes and sports hubs, join
active open tables hosted by peers with similar hobbies, pick their favorite
seats, pre-order their orders in advance, and get digital reservation passes,
creating a comfortable, hassle-free hangout experience for both guests and
local venues.

Booth Core Features:

1. Active Open Tables
   Users can discover and join active open tables hosted by peers based on
   specific interest circles. Each open table clearly displays the host's
   profile, seated member avatars, remaining seat counts, and table ground
   rules so members know the vibe before arriving.

2. Smart Midpoint Location Recommendation
   Solves the exhausting debates by calculating a fair central meeting point
   based on users' respective locations. The app automatically recommends
   curated cafes, co working hubs, and sports venues closest to the group's
   midpoint, ensuring fair travel distances and transparent budget expectations
   for everyone.

3. Community Circles & Private Table Invites
   Supports both public community matching and private group gatherings. Users
   can share or enter private invite codes to join closed table circles, paired
   with a dedicated Community Hub that saves joined circles and favorite hosts
   for quick re-booking in future meetups.

4. Online Table Reservation & Food Pre-Ordering
   Combines space reservation and refreshments into one seamless flow. While
   selecting their table and specific seat number on the interactive floor
   plan, users can pre-order beverages and snacks with live subtotal
   calculation, ensuring their order is prepared and served as soon as they
   take their seat.

5. Upfront Split-Bill & Commitment
   Eliminates awkward end of meetup bill calculations. Because each participant
   selects their own seat and pre-orders their individual items directly within
   the table booking step, every person covers their exact portion upfront,
   preventing split-bill confusion and protecting venues against costly no-show
   cancellations.

6. Digital Offline QR Check-In Pass
   Generates a digital reservation ticket equipped with an offline-ready QR
   pass that summarizes the table code, assigned seat number, arrival time, and
   pre-ordered menu. Users simply present the QR pass at the venue counter for
   instant, contactless check in.


B. FULL NAMES OF ALL GROUP MEMBERS
------------------------------------------------------------------------------

1. William Asabha Purnamadjaja (00000115707)
2. Sebastian Benaya (00000124636)
3. Michelle Olivia Herijanto (00000114755)
4. Fellix Fernando Williams Lim (00000108757)


C. TASK DIVISION AND GROUP WORKING SYSTEM
------------------------------------------------------------------------------

Working System & Methodology:

Our group divided the work by assigning specific screens to each member so that
everyone contributed directly to coding the app:

- Screen Division: Each member was responsible for building their assigned
  screens, including the layout, buttons, and state logic.
- Collaboration via Git: We used GitHub to share our code, work on our
  respective branches, and merge them into the main project.


D. SPECIFIC TASK AND CONTRIBUTION OF EACH INDIVIDUAL MEMBER
------------------------------------------------------------------------------

1. Member 1: William Asabha Purnamadjaja - 00000115707

   Assigned Screens and tasks: UI & App design and Onboarding & Registration
   Flow

   - SplashScreen: App launch screen with branded logo transition.
   - RoleSelectionScreen: Role picker (Customer vs. Merchant) with interactive
     radio state.
   - RegistrationScreen (Form/Input Screen): Multi-field registration with live
     validation (Indonesian +62 phone format, email regex, required fields,
     error borders/helper text, and the Autofill Demo button).

2. Member 2: Sebastian Benaya - 00000124636

   Assigned Screens and tasks: UI & App design, Identity, Community & Profile
   Flow

   - ProfilePictureScreen: Dynamic initials avatar display derived from
     registered user name.
   - FaceVerificationScreen (Native-Feature Mockup): Biometric facial recognition
     radar scan simulation granting verified SafePass status.
   - CommunityScreen: Saved community circles, trusted table hosts, and invite
     code redemption.
   - ProfileScreen: User activity statistics, Face Verification security toggle,
     and account settings.

3. Member 3: Michelle Olivia Herijanto - 00000114755

   Assigned Screens and tasks: UI & App design, Discovery, Detail & Navigation
   Flow

   - ExploreScreen (List Screen): Main dashboard with LazyColumn venue cards,
     category filter chips ("All Hubs", "WFC Friendly", "Sports & Padel"), and
     real-time search queries.
   - VenueDetailScreen (Detail Screen): Dynamic place details displaying custom
     imagery, amenities, operating hours, and venue-specific Active Open Tables.
   - BoothNavGraph: Centralized Jetpack Compose NavHost architecture with
     dynamic argument passing (venueId and tableId routing).

4. Member 4: Fellix Fernando Williams Lim - 00000108757

   Assigned Screens: UI & App, Reservation, Pre-Order & Pass Flow

   - FloorPlanScreen: Interactive table layout and individual seat picker.
   - PreOrderScreen: Integrated menu item counter (+/-) with live subtotal
     calculation and upfront split-bill commitment.
   - ReservationPassScreen: Confirmed digital reservation ticket with offline QR
     pass check-in.
   - DummyData Architecture: Centralized mock models (Venues, Table Sessions,
     Menus, Tickets).


PREREQUISITES
------------------------------------------------------------------------------

- Android Studio recent enough to support Android Gradle Plugin 9.4.1 and
  Android SDK Platform 37.
- JDK 17 or newer. The JDK bundled with Android Studio works.
- Internet access on the first Gradle sync so the wrapper and dependencies can
  download.
