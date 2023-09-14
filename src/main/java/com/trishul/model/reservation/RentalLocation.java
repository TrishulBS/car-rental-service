package com.trishul.model.reservation;

import com.trishul.model.common.Address;
import com.trishul.model.common.Coordinates;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalLocation {
    private String id;
    private Address address;
    private Coordinates coordinates;
}
