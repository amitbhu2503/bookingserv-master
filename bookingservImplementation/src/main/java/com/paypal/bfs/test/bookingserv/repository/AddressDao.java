package com.paypal.bfs.test.bookingserv.repository;

import com.paypal.bfs.test.bookingserv.api.model.Address;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.entity.AddressEntity;

public class AddressDao {

    public static Address getAddress(AddressEntity addressEntity){
        if(addressEntity != null){
            Address address =  new Address();
            address.setLine1(addressEntity.getLine1());
            address.setLine2(addressEntity.getLine2());
            address.setState(addressEntity.getState());
            address.setCity(addressEntity.getCity());
            address.setCountry(addressEntity.getCountry());
            address.setZipCode(addressEntity.getZipCode());
            return address;
        }
        return null;
    }

    public static AddressEntity getAddressEntity(Address address){
        if(address != null){
            AddressEntity addressEntity =  new AddressEntity();
            addressEntity.setLine1(address.getLine1());
            addressEntity.setLine2(address.getLine2());
            addressEntity.setState(address.getState());
            addressEntity.setCity(address.getCity());
            addressEntity.setCountry(address.getCountry());
            addressEntity.setZipCode(address.getZipCode());
            return addressEntity;
        }
        return null;
    }
}
