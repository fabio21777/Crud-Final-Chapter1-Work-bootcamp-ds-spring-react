package com.fsm.crudFinalChapterWork.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsm.crudFinalChapterWork.dto.ClientDto;
import com.fsm.crudFinalChapterWork.entities.Client;
import com.fsm.crudFinalChapterWork.repositories.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;
	
	@Transactional
	public ClientDto findById(Long id) {
		Optional<Client>client = clienteRepository.findById(id);
		return new ClientDto(client.get()) ;
	}
	
	@Transactional
	public Page<ClientDto>findAll(PageRequest pageRequest){
		Page<Client> clients = clienteRepository.findAll(pageRequest);
		return clients.map(client -> new ClientDto(client));
	}
	
	
}
