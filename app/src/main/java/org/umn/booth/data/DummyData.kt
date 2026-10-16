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
            activeBoothsCount = 3,
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
            imageRes = R.drawable.img_kopi_nako,
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
            imageRes = R.drawable.img_kopi_nako,
            amenities = listOf(
                "Padel Courts Booking",
                "Lounge Cafe",
                "Shower & Locker",
                "Protein Smoothie Bar"
            ),
            activeBoothsCount = 4,
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
            hostRole = "UI/UX Designer • 14 tables hosted",
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
