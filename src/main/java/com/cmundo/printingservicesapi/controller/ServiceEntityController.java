package com.cmundo.printingservicesapi.controller;

import com.cmundo.printingservicesapi.entity.ServiceEntity;
import com.cmundo.printingservicesapi.service.ServiceEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/api/services")
public class ServiceEntityController {

    @Autowired
    private ServiceEntityService serviceEntityService;

    @PostMapping("/{model}")
    public ResponseEntity<?> addService(@RequestBody ServiceEntity service,
                                        @PathVariable String model){
        ServiceEntity serviceEntity = serviceEntityService.createService(service,model);
        return new ResponseEntity<>(serviceEntity, HttpStatus.OK);
    }
    @GetMapping("/{model}")
    public ResponseEntity<?> getServicesByPrinterModel(@PathVariable String model){
        List<ServiceEntity> serviceEntities = serviceEntityService.findServicesByPrinterModel(model);
        return new ResponseEntity<>(serviceEntities, HttpStatus.OK);
    }
    @DeleteMapping("/id/{id}")
    public String deleteService(@PathVariable Integer id){
        return serviceEntityService.deleteServiceEntity(id);
    }
}
