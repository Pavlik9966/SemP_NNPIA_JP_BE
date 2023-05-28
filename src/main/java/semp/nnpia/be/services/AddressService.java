package semp.nnpia.be.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import semp.nnpia.be.domains.Address;
import semp.nnpia.be.repositories.IAddressRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AddressService {
    private final IAddressRepository addressRepository;

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address getAddressById(final Long id) throws ResourceNotFoundException {
        Optional<Address> result = addressRepository.findById(id);

        if (result.isEmpty()) throw new ResourceNotFoundException();

        return result.get();
    }

    public Address getExactMatchAddress(final Address address) {
        return addressRepository.findAddressByStreetAddressAndCityAndZipCodeAndState(
                address.getStreetAddress(),
                address.getCity(),
                address.getZipCode(),
                address.getState()
        );
    }
}