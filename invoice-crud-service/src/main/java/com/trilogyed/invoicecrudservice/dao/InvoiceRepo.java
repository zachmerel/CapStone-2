package com.trilogyed.invoicecrudservice.dao;

import com.trilogyed.invoicecrudservice.dto.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoice, Integer> {
    List<Invoice> findInvoicesByCustomerId(int id);
}
