package com.trishul.model.vehicle;


import com.trishul.model.common.Address;
import com.trishul.model.common.Coordinates;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleLocation {
    private String locationId;
    private Address address;
    private Coordinates coordinates;
}
