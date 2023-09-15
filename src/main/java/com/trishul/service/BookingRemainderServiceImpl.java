package com.trishul.service;

import com.trishul.model.account.User;
import com.trishul.model.common.NotificationStatus;
import com.trishul.model.reservation.ReservationRemainder;
import com.trishul.model.reservation.VehicleReservation;
import com.trishul.repository.UserRepository;
import com.trishul.repository.VehicleReservationRepository;

public class BookingRemainderServiceImpl implements BookingRemainderService {

    VehicleReservationRepository vehicleReservationRepository = new VehicleReservationRepository();

    @Override
    public void notifyUser(ReservationRemainder reservationReminder) {
        VehicleReservation vehicleReservation =
                vehicleReservationRepository.getVehicleReservation(reservationReminder.getReservationId());
        User user = UserRepository.userMap.get(vehicleReservation.getUsrId());
        System.out.println("Notified user" + user.getContact().getEmail());
        reservationReminder.setNotificationStatus(NotificationStatus.SENT);
    }
}