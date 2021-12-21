package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service;

import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.Address;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Der AddressService implementiert die Business-Logic für die Adressen.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void saveAddress(Address address){
        addressRepository.save(address);
    }

    public Address getAddressById(Long id){
        return addressRepository.getById(id);
    }

    public List<Address> getAllAddresses(){
        return addressRepository.findAll();
    }
}
