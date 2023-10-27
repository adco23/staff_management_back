package com.school.staffmanagement.controller;

import com.school.staffmanagement.exception.BadRequestException;
import com.school.staffmanagement.exception.ResourceNotFoundException;
import com.school.staffmanagement.model.dto.ClientDto;
import com.school.staffmanagement.model.entity.Client;
import com.school.staffmanagement.model.payload.MessageResponse;
import com.school.staffmanagement.service.IClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @GetMapping("")
    public ResponseEntity<?> showAll() {
        List<Client> list = clientService.listAll();
        if (list == null || list.isEmpty()) {
            throw new ResourceNotFoundException("clients");
        }
        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("OK")
                        .data(list)
                        .build()
                , HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody ClientDto request) {
        Client client = null;
        try {
            if (!clientService.existsByEmail(request.getEmail())) {
                client = clientService.save(request);

                return new ResponseEntity<>(MessageResponse.builder()
                        .message("Client saved")
                        .data(ClientDto.builder()
                                .id(client.getId())
                                .email(client.getEmail())
                                .password(client.getPassword())
                                .rol(client.getRol())
                                .status(client.getStatus())
                                .createdAt(client.getCreatedAt())
                                .build()
                        )
                        .build(), HttpStatus.CREATED);
            } else {
                throw new BadRequestException("Client already exists");
            }
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ClientDto request, @PathVariable Long id) {
        Client client = null;
        try {
            if (clientService.existsById(id)) {
                request.setId(id);
                client = clientService.save(request);
                return new ResponseEntity<>(MessageResponse.builder()
                        .message("Client saved")
                        .data(ClientDto.builder()
                                .id(client.getId())
                                .email(client.getEmail())
                                .password(client.getPassword())
                                .rol(client.getRol())
                                .status(client.getStatus())
                                .createdAt(client.getCreatedAt())
                                .build()
                        )
                        .build(), HttpStatus.CREATED);
            } else {
                throw new ResourceNotFoundException("client", "id", id);
            }
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id) {
        Client client = null;
        try {
            client = clientService.findById(id);

            if (client == null) {
                throw new ResourceNotFoundException("client", "id", id);
            }

            return new ResponseEntity<>(MessageResponse.builder()
                    .message("OK")
                    .data(ClientDto.builder()
                            .id(client.getId())
                            .email(client.getEmail())
                            .password(client.getPassword())
                            .rol(client.getRol())
                            .status(client.getStatus())
                            .createdAt(client.getCreatedAt())
                            .build())
                    .build(), HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Client client = clientService.findById(id);
            clientService.delete(client);
            return new ResponseEntity<>(client, HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
