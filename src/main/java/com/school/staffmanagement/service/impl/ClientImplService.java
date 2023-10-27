package com.school.staffmanagement.service.impl;

import com.school.staffmanagement.repository.ClientRepository;
import com.school.staffmanagement.model.dto.ClientDto;
import com.school.staffmanagement.model.entity.Client;
import com.school.staffmanagement.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientImplService implements IClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    @Override
    public boolean existsById(Long id) {
        return clientRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Client> listAll() {
        return (List) clientRepository.findAll();
    }

    @Override
    public Boolean existsByEmail(String email) {
        return clientRepository.existsByEmail(email);
    }

    @Transactional
    @Override
    public Client save(ClientDto clientDto) {
        if (clientRepository.existsByEmail(clientDto.getEmail())) {
            Client existing = clientRepository.findById(clientDto.getId()).orElse(null);
            Client client = Client.builder()
                    .id(existing.getId())
                    .email(clientDto.getEmail())
                    .password(clientDto.getPassword())
                    .rol(clientDto.getRol())
                    .status(clientDto.getStatus())
                    .createdAt(existing.getCreatedAt())
                    .build();

            return clientRepository.save(client);
        }

        Client client = Client.builder()
                .id(clientDto.getId())
                .email(clientDto.getEmail())
                .password(clientDto.getPassword())
                .rol(clientDto.getRol() == null ? "client" : clientDto.getRol())
                .status(clientDto.getStatus() == null ? "active" : clientDto.getStatus())
                .build();

        return clientRepository.save(client);
    }

    @Transactional(readOnly = true)
    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Client client) {
        clientRepository.delete(client);
    }
}
