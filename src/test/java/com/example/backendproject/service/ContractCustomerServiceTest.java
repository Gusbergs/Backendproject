package com.example.backendproject.service;

import com.example.backendproject.dto.ContractCustomersDtoMini;
import com.example.backendproject.models.ContractCustomer;
import com.example.backendproject.repo.ContractCustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
/*
@ExtendWith(MockitoExtension.class)
public class ContractCustomerServiceTest {

    @Mock
    private ContractCustomerRepo contractCustomerRepo;

    @InjectMocks
    private ContractCustomerService contractCustomerService;

    private ContractCustomer contractCustomer;

    @BeforeEach
    public void setUp() {
        contractCustomer = new ContractCustomer();
        contractCustomer.setContractCustomerId(1L);
        contractCustomer.setCompanyName("Test Company");
        contractCustomer.setContactName("Test Contact");
        contractCustomer.setCountry("Test Country");
    }

    @Test
    public void testContractCustomersDtoMini() {
        ContractCustomersDtoMini dto = contractCustomerService.contractCustomersDtoMini(contractCustomer);
        assertEquals(contractCustomer.getContractCustomerId(), dto.getId());
        assertEquals(contractCustomer.getCompanyName(), dto.getCompanyName());
        assertEquals(contractCustomer.getContactName(), dto.getContactName());
        assertEquals(contractCustomer.getCountry(), dto.getCountry());
    }

    @Test
    public void testSaveContractCustomer() {
        contractCustomerService.saveContractCustomer(contractCustomer);
        verify(contractCustomerRepo, times(1)).save(contractCustomer);
    }

    @Test
    public void testGetContractCustomerById() {
        when(contractCustomerRepo.findById(1L)).thenReturn(Optional.of(contractCustomer));
        Optional<ContractCustomer> foundCustomer = contractCustomerService.getContractCustomerById(1L);
        assertEquals(contractCustomer, foundCustomer.orElse(null));
        verify(contractCustomerRepo, times(1)).findById(1L);
    }

    @Test
    public void testGetAllContractCustomersDtoMiniPageable() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ContractCustomer> page = new PageImpl<>(Collections.singletonList(contractCustomer), pageable, 1);
        when(contractCustomerRepo.findAll(any(Pageable.class))).thenReturn(page);

        Page<ContractCustomersDtoMini> result = contractCustomerService.getAllContractCustomersDtoMiniPageable(pageable);
        assertEquals(1, result.getTotalElements());
        verify(contractCustomerRepo, times(1)).findAll(pageable);
    }

    @Test
    public void testFindAllByCountryOrContactNameOrCompanyNameContains() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ContractCustomer> page = new PageImpl<>(Collections.singletonList(contractCustomer), pageable, 1);
        when(contractCustomerRepo.findAllByCountryContainsOrContactNameContainsOrCompanyNameContains(anyString(), anyString(), anyString(), any(Pageable.class))).thenReturn(page);

        Page<ContractCustomersDtoMini> result = contractCustomerService.findAllByCountryOrContactNameOrCompanyNameContains("Test Country", "Test Contact", "Test Company", pageable);
        assertEquals(1, result.getTotalElements());
        verify(contractCustomerRepo, times(1)).findAllByCountryContainsOrContactNameContainsOrCompanyNameContains("Test Country", "Test Contact", "Test Company", pageable);
    }

}
 */
