package com.trishul.service;

import com.trishul.exception.VehicleNotExistsException;
import com.trishul.model.vehicle.HireableVehicle;

public interface VehicleService {

    HireableVehicle getVehicleById(String id);

    HireableVehicle getVehicleByQrCode(String qrCode);

    HireableVehicle addVehicle(HireableVehicle hireableVehicle);

    void updateQrCode(String vehicleId, String qrCode) throws VehicleNotExistsException;

    void removeVehicle(String vehicleId) throws VehicleNotExistsException;
}
