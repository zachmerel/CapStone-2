package com.trilogyed.invoicecrudservice.dao;

import com.trilogyed.invoicecrudservice.dto.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceItemRepo extends JpaRepository<InvoiceItem, Integer> {
}
