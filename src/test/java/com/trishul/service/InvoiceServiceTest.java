package com.trishul.service;

import com.trishul.TestData;
import com.trishul.exception.InvalidVehicleIdException;
import com.trishul.exception.ReservationNotFoundException;
import com.trishul.exception.VehicleBookedException;
import com.trishul.model.account.User;
import com.trishul.model.reservation.Invoice;
import com.trishul.model.reservation.VehicleInventory;
import com.trishul.model.reservation.VehicleReservation;
import com.trishul.model.vehicle.HireableVehicle;
import com.trishul.repository.UserRepository;
import com.trishul.repository.VehicleInventoryRepository;
import com.trishul.repository.VehicleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InvoiceServiceTest {
    @AfterEach
    public void clean() {
        VehicleRepository.vehicleMap.clear();
        VehicleRepository.vehicles.clear();
        UserRepository.userMap.clear();
        UserRepository.userUserIdMap.clear();
        UserRepository.users.clear();
        VehicleInventoryRepository.vehicleInventoryList.clear();
    }

    @Test
    public void should_ComputeDailyInvoice() {
        VehicleRepository vehicleRepository = new VehicleRepository();
        User user = TestData.getUser("user@email.com");
        UserRepository.userMap.putIfAbsent("user@email.com", user);
        UserRepository.userUserIdMap.putIfAbsent(user.getId(), user);
        List<HireableVehicle> vehicleList = TestData.getHireableVehicles();
        for (HireableVehicle hireableVehicle : vehicleList) {
            vehicleRepository.addVehicle(hireableVehicle);
            VehicleInventoryRepository.vehicleInventoryList.add(new VehicleInventory(hireableVehicle));
        }
        UserService userService = new UserServiceImpl();
        VehicleReservation vehicleReservation = TestData.getVehicleReservation(user);
        InvoiceService invoiceService = new InvoiceServiceImpl();
        Invoice invoice = invoiceService.computeInvoice(vehicleReservation);
        assertNotNull(invoice);
        assertEquals(invoice.getUsageCharges(), 1600.0);
        assertEquals(invoice.getAddonCost(), 500.0);
        assertEquals(invoice.getTaxes(), 288.0);
        assertEquals(invoice.getTotal(), 1888.0);
    }

    @Test
    public void should_ComputeHourlyInvoice() throws VehicleBookedException, InvalidVehicleIdException, ReservationNotFoundException {
        VehicleRepository vehicleRepository = new VehicleRepository();
        List<HireableVehicle> vehicleList = TestData.getHireableVehicles();
        for (HireableVehicle hireableVehicle : vehicleList) {
            vehicleRepository.addVehicle(hireableVehicle);
            VehicleInventoryRepository.vehicleInventoryList.add(new VehicleInventory(hireableVehicle));
        }
        User user = TestData.getUser("user@email.com");
        UserRepository.userMap.putIfAbsent("user@email.com", user);
        UserRepository.userUserIdMap.putIfAbsent(user.getId(), user);

        UserService userService = new UserServiceImpl();
        VehicleReservation vehicleReservation =
                userService.scanToReserve(vehicleList.get(1).getQrCode(), user.getId());
        userService.returnVehicle(vehicleReservation.getReservationId(),
                vehicleList.get(1).getParkedLocation());
        InvoiceService invoiceService = new InvoiceServiceImpl();
        Invoice invoice = invoiceService.computeInvoice(vehicleReservation);
        assertNotNull(invoice);
        assertEquals(invoice.getUsageCharges(), 100);
        assertEquals(invoice.getTaxes(), 18);
        assertEquals(invoice.getTotal(), 118);
    }
}