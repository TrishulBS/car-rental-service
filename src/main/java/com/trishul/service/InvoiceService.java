package com.trishul.service;

import com.trishul.model.reservation.Invoice;
import com.trishul.model.reservation.VehicleReservation;

public interface InvoiceService {
    Invoice computeInvoice(VehicleReservation vehicleReservation);
}