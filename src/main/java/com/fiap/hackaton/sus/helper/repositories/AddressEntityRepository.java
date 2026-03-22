package com.fiap.hackaton.sus.helper.repositories;

import com.fiap.hackaton.sus.helper.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressEntityRepository extends JpaRepository<AddressEntity, Long> {
}