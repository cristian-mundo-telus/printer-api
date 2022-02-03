package com.cmundo.printingservicesapi.repository;

import com.cmundo.printingservicesapi.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Integer> {
    List<ServiceEntity> findServiceEntityByPrinterModel(String model);
}
