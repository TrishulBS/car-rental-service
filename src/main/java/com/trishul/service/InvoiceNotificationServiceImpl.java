package com.trishul.service;

import com.trishul.model.account.User;
import com.trishul.model.common.NotificationStatus;
import com.trishul.model.reservation.InvoiceNotification;
import com.trishul.model.reservation.VehicleReservation;
import com.trishul.repository.UserRepository;
import com.trishul.repository.VehicleReservationRepository;

public class InvoiceNotificationServiceImpl implements InvoiceNotificationService {

    public void notifyUser(InvoiceNotification invoiceNotification) {
        VehicleReservation vehicleReservation = VehicleReservationRepository.vehicleReservationMap
                .get(invoiceNotification.getReservationId());
        User user = UserRepository.userUserIdMap.get(vehicleReservation.getUsrId());
        System.out.println("Notification sent " + user.getContact().getEmail());
        invoiceNotification.setNotificationStatus(NotificationStatus.SENT);
    }
}
