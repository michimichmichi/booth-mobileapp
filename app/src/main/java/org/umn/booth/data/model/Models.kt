package org.umn.booth.data.model

import androidx.annotation.DrawableRes

data class Venue(
    val id: String,
    val name: String,
    val category: String,
    val address: String,
    val distanceKm: Double,
    val rating: Double,
    val reviewCount: Int,
    val priceRange: String,
    val operatingHours: String,
    @DrawableRes val imageRes: Int,
    val amenities: List<String>,
    val activeBoothsCount: Int,
    val isVerifiedPartner: Boolean = true
)

data class TableMember(
    val name: String,
    val initials: String,
    val isHost: Boolean = false,
    val seatNumber: Int
)

data class TableSession(
    val id: String,
    val venueId: String,
    val tableNumber: String,
    val locationArea: String,
    val title: String,
    val circleType: String,
    val hostName: String,
    val hostRole: String,
    val hostInitials: String,
    val totalSeats: Int,
    val filledSeats: Int,
    val tags: List<String>,
    val members: List<TableMember>
)

data class MenuItem(
    val id: String,
    val name: String,
    val category: String,
    val description: String,
    val price: Int,
    @DrawableRes val imageRes: Int
)

data class ReservationTicket(
    val ticketCode: String,
    val venueName: String,
    val areaName: String,
    val arrivalTime: String,
    val assignedSeat: String,
    val isPaid: Boolean,
    val totalAmount: Int,
    val itemsSummary: String
)
