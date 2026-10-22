package org.umn.booth.data

import org.umn.booth.R
import org.umn.booth.data.model.MenuItem
import org.umn.booth.data.model.ReservationTicket
import org.umn.booth.data.model.TableMember
import org.umn.booth.data.model.TableSession
import org.umn.booth.data.model.Venue

object DummyData {
    val venues = listOf(
        Venue(
            id = "kopi-nako-serpong",
            name = "Kopi Nako Serpong",
            category = "WFC Friendly",
            address = "Jl. Boulevard Gading Serpong, Tangerang",
            distanceKm = 1.2,
            rating = 4.8,
            reviewCount = 320,
            priceRange = "IDR 35k - 85k",
            operatingHours = "Open • 08:00 - 22:00",
            imageRes = R.drawable.img_kopi_nako,
            amenities = listOf(
                "High-Speed Wi-Fi (100 Mbps)",
                "Plugs at Every Table",
                "Indoor Air Conditioned",
                "Glasshouse Garden",
                "Clean Musholla"
            ),
            activeBoothsCount = 2,
            isVerifiedPartner = true
        ),
        Venue(
            id = "titik-temu-bsd",
            name = "Titik Temu Coffee BSD",
            category = "WFC Friendly",
            address = "The Breeze BSD City, Tangerang",
            distanceKm = 2.4,
            rating = 4.7,
            reviewCount = 215,
            priceRange = "IDR 40k - 95k",
            operatingHours = "Open • 07:30 - 21:30",
            imageRes = R.drawable.img_titik_temu_breeze,
            amenities = listOf(
                "High-Speed Wi-Fi (150 Mbps)",
                "Dedicated Work Desks",
                "Outdoor Terrace",
                "Ergonomic Chairs"
            ),
            activeBoothsCount = 2,
            isVerifiedPartner = true
        ),
        Venue(
            id = "padel-hub-alamsutera",
            name = "Padel Pro & Lounge",
            category = "Sports & Padel",
            address = "Jl. Jalur Sutera, Alam Sutera",
            distanceKm = 3.8,
            rating = 4.9,
            reviewCount = 180,
            priceRange = "IDR 50k - 120k",
            operatingHours = "Open • 06:00 - 23:00",
            imageRes = R.drawable.img_padel_pro,
            amenities = listOf(
                "Padel Courts Booking",
                "Lounge Cafe",
                "Shower & Locker",
                "Protein Smoothie Bar"
            ),
            activeBoothsCount = 2,
            isVerifiedPartner = true
        ),
        Venue(
            id = "first-crack-bsd",
            name = "First Crack Coffee BSD",
            category = "WFC Friendly",
            address = "Ruko Vanya Park, BSD City",
            distanceKm = 2.1,
            rating = 4.8,
            reviewCount = 194,
            priceRange = "IDR 38k - 90k",
            operatingHours = "Open • 08:00 - 21:00",
            imageRes = R.drawable.img_titik_temu,
            amenities = listOf(
                "High-Speed Wi-Fi",
                "Quiet Workspace",
                "Meeting Pods",
                "Cold Brew Bar"
            ),
            activeBoothsCount = 2,
            isVerifiedPartner = true
        ),
        Venue(
            id = "anomali-coffee-gs",
            name = "Anomali Coffee Gading Serpong",
            category = "WFC Friendly",
            address = "Jl. Boulevard Gading Serpong",
            distanceKm = 1.5,
            rating = 4.7,
            reviewCount = 310,
            priceRange = "IDR 40k - 85k",
            operatingHours = "Open • 07:00 - 22:00",
            imageRes = R.drawable.img_anomali,
            amenities = listOf(
                "Single Origin Bar",
                "Outdoor Smoking Area",
                "High-Speed Wi-Fi",
                "Ample Power Plugs"
            ),
            activeBoothsCount = 2,
            isVerifiedPartner = true
        ),
        Venue(
            id = "tanamera-alamsutera",
            name = "Tanamera Coffee Alam Sutera",
            category = "WFC Friendly",
            address = "Flavor Bliss, Alam Sutera",
            distanceKm = 4.0,
            rating = 4.9,
            reviewCount = 420,
            priceRange = "IDR 45k - 110k",
            operatingHours = "Open • 07:00 - 21:00",
            imageRes = R.drawable.img_tanamera,
            amenities = listOf(
                "Award-Winning Roast",
                "Private Meeting Room",
                "Fast Wi-Fi",
                "Pet Friendly Outdoor"
            ),
            activeBoothsCount = 2,
            isVerifiedPartner = true
        ),
        Venue(
            id = "smash-hub-bsd",
            name = "Smash Hub Badminton & Cafe",
            category = "Sports & Padel",
            address = "Edutown BSD City",
            distanceKm = 3.2,
            rating = 4.6,
            reviewCount = 145,
            priceRange = "IDR 45k - 95k",
            operatingHours = "Open • 06:00 - 22:00",
            imageRes = R.drawable.img_smash_hub,
            amenities = listOf(
                "Badminton Courts",
                "Locker Room",
                "Juice & Espresso Bar",
                "Spectator Lounge"
            ),
            activeBoothsCount = 2,
            isVerifiedPartner = true
        ),
        Venue(
            id = "gading-padel-club",
            name = "Gading Padel & Social Club",
            category = "Sports & Padel",
            address = "Scientia Boulevard, Gading Serpong",
            distanceKm = 2.8,
            rating = 4.9,
            reviewCount = 260,
            priceRange = "IDR 55k - 130k",
            operatingHours = "Open • 06:00 - 23:00",
            imageRes = R.drawable.img_padel_court,
            amenities = listOf(
                "Panoramic Padel Courts",
                "Sauna & Shower",
                "Specialty Coffee",
                "Pro Shop"
            ),
            activeBoothsCount = 2,
            isVerifiedPartner = true
        )
    )

    val tableSessions = listOf(
        TableSession(
            id = "tbl-04",
            venueId = "kopi-nako-serpong",
            tableNumber = "Table 04",
            locationArea = "Glasshouse Corner",
            title = "Afternoon WFC & Coding Sprint",
            circleType = "Focus Circle",
            hostName = "Sarah K.",
            hostRole = "Host • UI/UX Designer • 14 tables hosted",
            hostInitials = "SK",
            totalSeats = 4,
            filledSeats = 2,
            tags = listOf("Quiet Work", "No Loud Calls", "Split Bill Evenly"),
            members = listOf(
                TableMember(name = "Sarah K.", initials = "SK", isHost = true, seatNumber = 1),
                TableMember(name = "Arya", initials = "A", isHost = false, seatNumber = 2)
            )
        ),
        TableSession(
            id = "tbl-07",
            venueId = "kopi-nako-serpong",
            tableNumber = "Table 07",
            locationArea = "Outdoor Canopy",
            title = "Casual Coffee & Startup Talk",
            circleType = "Networking",
            hostName = "David P.",
            hostRole = "Community Host • Product Builder",
            hostInitials = "DP",
            totalSeats = 4,
            filledSeats = 3,
            tags = listOf("Friendly Banter", "ID/EN Spoken", "Instant QRIS"),
            members = listOf(
                TableMember(name = "David P.", initials = "DP", isHost = true, seatNumber = 1),
                TableMember(name = "Rafi", initials = "R", isHost = false, seatNumber = 2),
                TableMember(name = "Nadia", initials = "N", isHost = false, seatNumber = 3)
            )
        ),
        TableSession(
            id = "tbl-titik-02",
            venueId = "titik-temu-bsd",
            tableNumber = "Table 02",
            locationArea = "Terrace Garden",
            title = "Product Designers Sync & Portfolio Review",
            circleType = "Creative Circle",
            hostName = "Jessica M.",
            hostRole = "Host • Product Designer • 9 tables hosted",
            hostInitials = "JM",
            totalSeats = 4,
            filledSeats = 2,
            tags = listOf("Figma & Design", "Coffee Lovers", "Casual"),
            members = listOf(
                TableMember(name = "Jessica M.", initials = "JM", isHost = true, seatNumber = 1),
                TableMember(name = "Randy", initials = "R", isHost = false, seatNumber = 2)
            )
        ),
        TableSession(
            id = "tbl-titik-05",
            venueId = "titik-temu-bsd",
            tableNumber = "Table 05",
            locationArea = "Quiet Mezzanine",
            title = "Deep Work & Writing Sprint",
            circleType = "Focus Circle",
            hostName = "Kevin T.",
            hostRole = "Host • Tech Lead • 20 tables hosted",
            hostInitials = "KT",
            totalSeats = 4,
            filledSeats = 3,
            tags = listOf("Silent Sprint", "Plugs Available", "Split Bill"),
            members = listOf(
                TableMember(name = "Kevin T.", initials = "KT", isHost = true, seatNumber = 1),
                TableMember(name = "Lia", initials = "L", isHost = false, seatNumber = 2),
                TableMember(name = "Dan", initials = "D", isHost = false, seatNumber = 3)
            )
        ),
        TableSession(
            id = "tbl-padel-01",
            venueId = "padel-hub-alamsutera",
            tableNumber = "Court 01 Lounge",
            locationArea = "Padel Club Lounge",
            title = "Morning Doubles & Post-Match Smoothies",
            circleType = "Sports Circle",
            hostName = "Marco V.",
            hostRole = "Host • Padel Enthusiast • 18 matches",
            hostInitials = "MV",
            totalSeats = 4,
            filledSeats = 3,
            tags = listOf("Padel Players", "Intermediate", "All Welcome"),
            members = listOf(
                TableMember(name = "Marco V.", initials = "MV", isHost = true, seatNumber = 1),
                TableMember(name = "Daniel", initials = "D", isHost = false, seatNumber = 2),
                TableMember(name = "Chris", initials = "C", isHost = false, seatNumber = 3)
            )
        ),
        TableSession(
            id = "tbl-padel-03",
            venueId = "padel-hub-alamsutera",
            tableNumber = "Court 03 Booth",
            locationArea = "Courtside Deck",
            title = "Beginner Padel Social & Meetup",
            circleType = "Active Circle",
            hostName = "Amanda L.",
            hostRole = "Host • Fitness Coach",
            hostInitials = "AL",
            totalSeats = 4,
            filledSeats = 2,
            tags = listOf("Beginners Welcome", "Rackets Provided", "Fun Matches"),
            members = listOf(
                TableMember(name = "Amanda L.", initials = "AL", isHost = true, seatNumber = 1),
                TableMember(name = "Sheila", initials = "S", isHost = false, seatNumber = 2)
            )
        ),
        TableSession(
            id = "tbl-crack-03",
            venueId = "first-crack-bsd",
            tableNumber = "Table 03",
            locationArea = "Brew Bar Corner",
            title = "Specialty Tasting & Remote Work",
            circleType = "Coffee Circle",
            hostName = "Budi S.",
            hostRole = "Host • Coffee Roaster • 11 tables hosted",
            hostInitials = "BS",
            totalSeats = 4,
            filledSeats = 2,
            tags = listOf("Manual Brew", "Quiet Work", "Co-working"),
            members = listOf(
                TableMember(name = "Budi S.", initials = "BS", isHost = true, seatNumber = 1),
                TableMember(name = "Linda", initials = "L", isHost = false, seatNumber = 2)
            )
        ),
        TableSession(
            id = "tbl-crack-08",
            venueId = "first-crack-bsd",
            tableNumber = "Table 08",
            locationArea = "Library Nook",
            title = "Fintech & Crypto Discussion",
            circleType = "Tech Circle",
            hostName = "Ryan H.",
            hostRole = "Host • Software Engineer",
            hostInitials = "RH",
            totalSeats = 4,
            filledSeats = 3,
            tags = listOf("Tech Talk", "No Shilling", "Coffee First"),
            members = listOf(
                TableMember(name = "Ryan H.", initials = "RH", isHost = true, seatNumber = 1),
                TableMember(name = "Alex", initials = "A", isHost = false, seatNumber = 2),
                TableMember(name = "Glenn", initials = "G", isHost = false, seatNumber = 3)
            )
        ),
        TableSession(
            id = "tbl-anomali-01",
            venueId = "anomali-coffee-gs",
            tableNumber = "Table 01",
            locationArea = "Front Veranda",
            title = "Digital Nomads Morning Coffee",
            circleType = "Social Circle",
            hostName = "Nadia R.",
            hostRole = "Host • Content Strategist • 8 tables hosted",
            hostInitials = "NR",
            totalSeats = 4,
            filledSeats = 2,
            tags = listOf("Friendly Vibes", "Work & Chat", "Morning Brew"),
            members = listOf(
                TableMember(name = "Nadia R.", initials = "NR", isHost = true, seatNumber = 1),
                TableMember(name = "Dimas", initials = "D", isHost = false, seatNumber = 2)
            )
        ),
        TableSession(
            id = "tbl-anomali-06",
            venueId = "anomali-coffee-gs",
            tableNumber = "Table 06",
            locationArea = "Main Hall",
            title = "Indie Hackers BSD Meetup",
            circleType = "Tech Circle",
            hostName = "Farhan D.",
            hostRole = "Host • Solo Founder • 15 tables hosted",
            hostInitials = "FD",
            totalSeats = 4,
            filledSeats = 3,
            tags = listOf("SaaS Founders", "Side Projects", "Feedback"),
            members = listOf(
                TableMember(name = "Farhan D.", initials = "FD", isHost = true, seatNumber = 1),
                TableMember(name = "Tommy", initials = "T", isHost = false, seatNumber = 2),
                TableMember(name = "Vina", initials = "V", isHost = false, seatNumber = 3)
            )
        ),
        TableSession(
            id = "tbl-tanamera-09",
            venueId = "tanamera-alamsutera",
            tableNumber = "Table 09",
            locationArea = "Roastery Booth",
            title = "Espresso Lovers & Startup Chat",
            circleType = "Networking",
            hostName = "Hendra W.",
            hostRole = "Host • Growth Specialist • 12 tables hosted",
            hostInitials = "HW",
            totalSeats = 4,
            filledSeats = 2,
            tags = listOf("Cold Brew Fans", "Networking", "Split Bill"),
            members = listOf(
                TableMember(name = "Hendra W.", initials = "HW", isHost = true, seatNumber = 1),
                TableMember(name = "Maya", initials = "M", isHost = false, seatNumber = 2)
            )
        ),
        TableSession(
            id = "tbl-tanamera-12",
            venueId = "tanamera-alamsutera",
            tableNumber = "Table 12",
            locationArea = "Alfresco Patio",
            title = "Creative Writers & Journaling",
            circleType = "Creative Circle",
            hostName = "Cindy A.",
            hostRole = "Host • Copywriter",
            hostInitials = "CA",
            totalSeats = 4,
            filledSeats = 3,
            tags = listOf("Quiet Focus", "Outdoor Breeze", "Writing Sprint"),
            members = listOf(
                TableMember(name = "Cindy A.", initials = "CA", isHost = true, seatNumber = 1),
                TableMember(name = "Ken", initials = "K", isHost = false, seatNumber = 2),
                TableMember(name = "Sara", initials = "S", isHost = false, seatNumber = 3)
            )
        ),
        TableSession(
            id = "tbl-smash-01",
            venueId = "smash-hub-bsd",
            tableNumber = "Booth B1",
            locationArea = "Spectator Lounge",
            title = "Badminton Matchday & Strategy",
            circleType = "Sports Circle",
            hostName = "Jonathan K.",
            hostRole = "Host • Club Captain • 22 matches hosted",
            hostInitials = "JK",
            totalSeats = 4,
            filledSeats = 3,
            tags = listOf("Match Analysis", "Energy Drinks", "Smash Lovers"),
            members = listOf(
                TableMember(name = "Jonathan K.", initials = "JK", isHost = true, seatNumber = 1),
                TableMember(name = "Bayu", initials = "B", isHost = false, seatNumber = 2),
                TableMember(name = "Edo", initials = "E", isHost = false, seatNumber = 3)
            )
        ),
        TableSession(
            id = "tbl-smash-04",
            venueId = "smash-hub-bsd",
            tableNumber = "Booth B4",
            locationArea = "Juice Corner",
            title = "Post-Workout Recovery & Coffee",
            circleType = "Active Circle",
            hostName = "Tiffany S.",
            hostRole = "Host • Nutritionist",
            hostInitials = "TS",
            totalSeats = 4,
            filledSeats = 2,
            tags = listOf("Healthy Drinks", "Cool Down", "Weekend Vibes"),
            members = listOf(
                TableMember(name = "Tiffany S.", initials = "TS", isHost = true, seatNumber = 1),
                TableMember(name = "Rina", initials = "R", isHost = false, seatNumber = 2)
            )
        ),
        TableSession(
            id = "tbl-gading-01",
            venueId = "gading-padel-club",
            tableNumber = "VIP Booth 1",
            locationArea = "Sunset Deck",
            title = "Padel Pro Social Doubles",
            circleType = "Sports Circle",
            hostName = "Leo G.",
            hostRole = "Host • Tournament Player • 16 games hosted",
            hostInitials = "LG",
            totalSeats = 4,
            filledSeats = 2,
            tags = listOf("Intermediate Level", "Pro Court", "Doubles"),
            members = listOf(
                TableMember(name = "Leo G.", initials = "LG", isHost = true, seatNumber = 1),
                TableMember(name = "Felix", initials = "F", isHost = false, seatNumber = 2)
            )
        ),
        TableSession(
            id = "tbl-gading-03",
            venueId = "gading-padel-club",
            tableNumber = "Booth 03",
            locationArea = "Pro Shop Lounge",
            title = "Evening Social & Match Review",
            circleType = "Social Circle",
            hostName = "Clarissa P.",
            hostRole = "Host • Community Lead",
            hostInitials = "CP",
            totalSeats = 4,
            filledSeats = 3,
            tags = listOf("Relaxed Vibe", "Padel Chat", "Drinks & Snacks"),
            members = listOf(
                TableMember(name = "Clarissa P.", initials = "CP", isHost = true, seatNumber = 1),
                TableMember(name = "Gary", initials = "G", isHost = false, seatNumber = 2),
                TableMember(name = "Natasha", initials = "N", isHost = false, seatNumber = 3)
            )
        )
    )

    val menuItems = listOf(
        MenuItem(
            id = "item-1",
            name = "Iced Americano",
            category = "Beverages",
            description = "Double shot espresso with iced mineral water",
            price = 32000,
            imageRes = R.drawable.img_iced_americano
        ),
        MenuItem(
            id = "item-2",
            name = "French Fries",
            category = "Snacks",
            description = "Crispy fries with sea salt & savory truffle dip",
            price = 25000,
            imageRes = R.drawable.img_french_fries
        )
    )

    val defaultTicket = ReservationTicket(
        ticketCode = "BTH-882-X904",
        venueName = "Kopi Nako Serpong",
        areaName = "Table 04 • Indoor Lounge",
        arrivalTime = "Today, 16:30 WIB",
        assignedSeat = "Seat #3 (Sarah's Table)",
        isPaid = true,
        totalAmount = 57000,
        itemsSummary = "Iced Americano (1), French Fries (1)"
    )
}

