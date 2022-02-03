package com.cmundo.printingservicesapi.repository;

import com.cmundo.printingservicesapi.entity.Printer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrinterRepository extends JpaRepository<Printer, Integer> {
    Printer findPrinterByModel(String model);
    List<Printer> findPrinterByPrinterStatusIsTrue();
    List<Printer> findPrinterByPrinterStatusIsFalse();

}
