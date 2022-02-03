package com.cmundo.printingservicesapi.service;

import com.cmundo.printingservicesapi.entity.Printer;
import com.cmundo.printingservicesapi.entity.ServiceEntity;
import com.cmundo.printingservicesapi.exception.ResourceNotCreatedException;
import com.cmundo.printingservicesapi.repository.PrinterRepository;
import com.cmundo.printingservicesapi.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceEntityService {
    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private PrinterRepository printerRepository;

    public ServiceEntity createService(ServiceEntity service, String model){
        Printer printer = printerRepository.findPrinterByModel(model.toUpperCase());
        if(printer == null){
            throw new ResourceNotCreatedException("Printer not found");
        }
        if(!printer.getPrinterStatus()){
            throw new ResourceNotCreatedException("Printer is not available");
        }
        try {
            service.setPrinter(printer);
            return serviceRepository.save(service);
        }catch (Exception e){
            throw new ResourceNotCreatedException("Service not created:(");
        }
    }

    public List<ServiceEntity> findServicesByPrinterModel(String model){
        List<ServiceEntity> serviceEntities = serviceRepository.findServiceEntityByPrinterModel(model.toUpperCase());
        Printer printer = printerRepository.findPrinterByModel(model.toUpperCase());
        if(printer == null) {
            throw new ResourceNotCreatedException("There are no printers with model "+ model);
        }else{
            return serviceEntities;
        }
    }

    public String deleteServiceEntity(Integer id){
        ServiceEntity service = serviceRepository.getById(id);
        if(service == null){
            throw new ResourceNotCreatedException("Service not found");
        }else{
            serviceRepository.delete(service);
            return "Service with id: "+id+" deleted";
        }
    }

}
