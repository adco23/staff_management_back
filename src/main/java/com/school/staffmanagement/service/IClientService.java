package com.school.staffmanagement.service;

import com.school.staffmanagement.model.dto.ClientDto;
import com.school.staffmanagement.model.entity.Client;

import java.util.List;

public interface IClientService {
    Client save(ClientDto clientDto);
    Client findById(Long id);
    void delete(Client user);
    boolean existsById(Long id);
    List<Client> listAll();
    Boolean existsByEmail(String email);
}
