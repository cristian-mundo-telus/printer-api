package com.cmundo.printingservicesapi.controller;

import com.cmundo.printingservicesapi.entity.Printer;
import com.cmundo.printingservicesapi.service.PrinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/printer")
public class PrintingController {

    @Autowired
    private PrinterService printerService;

    @PostMapping("")
    public ResponseEntity<?> createPrinter(@RequestBody Printer printer){
        Printer newPrinter = printerService.createPrinter(printer);
        return new ResponseEntity<>(newPrinter, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllPrinters(){
        List<Printer> printers = printerService.getPrinters();
        return new ResponseEntity<>(printers, HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<?> getPrintersAvailable(){
        List<Printer> printers = printerService.getPrintersAvailable();
        return new ResponseEntity<>(printers, HttpStatus.OK);
    }
    @GetMapping("/unavailable")
    public ResponseEntity<?> getPrinterUnavailable(){
        List<Printer> printers = printerService.getPrinterInUse();
        return new ResponseEntity<>(printers, HttpStatus.OK);
    }
    @GetMapping("/status/{model}")
    public String getPrinterStatus(@PathVariable String model){
        String status = printerService.getPrinterStatus(model);
        return status;
    }
    @PatchMapping("/{model}")
    public ResponseEntity<?> updateStatus(@PathVariable String model){
        Printer printerUpdated = printerService.updatePrinterStatus(model);
        return new ResponseEntity<>(printerUpdated, HttpStatus.OK);
    }
    @PutMapping("/{model}")
    public ResponseEntity<?> updatePrinter(@PathVariable String model, @RequestBody Printer printer){
        Printer printerToUpdate = printerService.updatePrinterInfo(printer, model);
        return new ResponseEntity<>(printerToUpdate, HttpStatus.OK);
    }
    @DeleteMapping("/{model}")
    public String deletePrinter(@PathVariable String model){
        String message = printerService.deletePrinter(model);
        return message;
    }




}
