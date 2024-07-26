package com.example.APISkeleton.persistance.repositories;

import com.example.APISkeleton.persistance.entities.Device;
import com.example.APISkeleton.persistance.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDeviceRepository extends CrudRepository<Device,Long> {
    Optional<Device> findByMAC(String MAC);
    Optional<List<Device>> findByUser(User user);

}
