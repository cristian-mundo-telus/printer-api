package com.cmundo.printingservicesapi.service;

import com.cmundo.printingservicesapi.entity.Printer;
import com.cmundo.printingservicesapi.exception.ResourceNotCreatedException;
import com.cmundo.printingservicesapi.repository.PrinterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrinterService {
    @Autowired
    private PrinterRepository printerRepository;

    public Printer createPrinter(Printer printer){
        try {
            printer.setModel(printer.getModel().toUpperCase());
            return printerRepository.save(printer);
        }catch (Exception e){
            throw new ResourceNotCreatedException("Not created, model must be unique or missing fields");
        }
    }

    public List<Printer> getPrinters(){
        return printerRepository.findAll();
    }

    public List<Printer> getPrinterInUse(){
        return printerRepository.findPrinterByPrinterStatusIsFalse();
    }

    public List<Printer> getPrintersAvailable(){
        return printerRepository.findPrinterByPrinterStatusIsTrue();
    }

    public String getPrinterStatus(String model){
        Printer printer = printerRepository.findPrinterByModel(model.toUpperCase());
        if (printer == null){
            throw new ResourceNotCreatedException("printer not found");
        }else{
            return printer.getPrinterStatus() ? "The printer" + model + " is available" : "The printer" + model + " is unavailable";
        }
    }

    public Printer updatePrinterStatus(String model) {
        Printer printer = printerRepository.findPrinterByModel(model.toUpperCase());
        if(printer == null){
            throw new ResourceNotCreatedException("Printer does not exists");
        }else{
            printer.setPrinterStatus(!printer.getPrinterStatus());
            return printerRepository.save(printer);
        }
    }

    public Printer updatePrinterInfo(Printer printer, String model){
      Printer printerToUpdate = printerRepository.findPrinterByModel(model.toUpperCase());
      if(printerToUpdate == null){
          throw new ResourceNotCreatedException("Printer not found");
      }else{
          printerToUpdate.setPrinterName(printer.getPrinterName());
          printerToUpdate.setPrinterStatus(printer.getPrinterStatus());
          printerToUpdate.setModel(printer.getModel().toUpperCase());

          return printerRepository.save(printerToUpdate);
      }
    }
    public String deletePrinter(String model){
        Printer printer = printerRepository.findPrinterByModel(model.toUpperCase());
        if(printer == null){
            throw new ResourceNotCreatedException("Printer not found");
        }else{
            printerRepository.delete(printer);
            return "Printer with model: "+model+" deleted";
        }
    }

}
