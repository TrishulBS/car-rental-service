package com.trishul.service;

import com.trishul.TestData;
import com.trishul.exception.InvalidVehicleIdException;
import com.trishul.exception.VehicleBookedException;
import com.trishul.model.account.User;
import com.trishul.model.reservation.Invoice;
import com.trishul.model.reservation.VehicleInventory;
import com.trishul.model.reservation.VehicleReservation;
import com.trishul.model.vehicle.HireableVehicle;
import com.trishul.repository.UserRepository;
import com.trishul.repository.VehicleInventoryRepository;
import com.trishul.repository.VehicleRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InvoiceBuilderUtilTest {
    @Test
    public void shouldBuildCancelledInvoice()
            throws VehicleBookedException, InvalidVehicleIdException {
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
        VehicleReservation vehicleReservation =
                userService.scanToReserve(vehicleList.get(1).getQrCode(), user.getId());

        vehicleReservation = userService.cancel(vehicleReservation.getReservationId());

        Invoice invoice = InvoiceBuilderUtil.buildCancelledInvoice(vehicleReservation);

        assertNotNull(invoice);
        assertEquals(invoice.getUsageCharges(), 50);
        assertEquals(invoice.getTaxes(), 9);
        assertEquals(invoice.getTotal(), 59);
    }
}