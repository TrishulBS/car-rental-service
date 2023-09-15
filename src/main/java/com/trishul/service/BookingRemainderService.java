package com.trishul.service;

import com.trishul.model.reservation.ReservationRemainder;

public interface BookingRemainderService {
    void notifyUser(ReservationRemainder reservationReminder);
}