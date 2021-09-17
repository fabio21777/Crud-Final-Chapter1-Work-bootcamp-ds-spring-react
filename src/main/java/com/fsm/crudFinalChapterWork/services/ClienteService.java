package com.fsm.crudFinalChapterWork.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsm.crudFinalChapterWork.controllers.exception.ControllerNotFoundException;
import com.fsm.crudFinalChapterWork.controllers.exception.DataBaseException;
import com.fsm.crudFinalChapterWork.dto.ClientDto;
import com.fsm.crudFinalChapterWork.entities.Client;
import com.fsm.crudFinalChapterWork.repositories.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;
	
	@Transactional
	public ClientDto findById(Long id) {
		Optional<Client>clientOptional = clienteRepository.findById(id);
		Client client = clientOptional.orElseThrow(() 
				-> new ControllerNotFoundException("Entity not found"));
		return new ClientDto(client);
	}
	
	@Transactional
	public Page<ClientDto>findAll(PageRequest pageRequest){
		Page<Client> clients = clienteRepository.findAll(pageRequest);
		return clients.map(client -> new ClientDto(client));
	}
	
	@Transactional
	public ClientDto insert(ClientDto clientDto) {
		Client clientEntity = new Client();
		clientDtoToClientEntity(clientDto,clientEntity);
		clienteRepository.save(clientEntity);
		return  new ClientDto(clientEntity);
	}

	@Transactional

	public ClientDto update(ClientDto clientDto, Long id) {
		try {
			Client clientEntity = clienteRepository.getOne(id);
			clientDtoToClientEntity(clientDto,clientEntity);
			clientEntity = clienteRepository.save(clientEntity);
			return new ClientDto(clientEntity);
		} catch (EntityNotFoundException e) {
			throw new ControllerNotFoundException("ID NOT FOUND" + id);
		}
		
	}
	
	@Transactional
	public void delete(Long id) {
		try {
			clienteRepository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ControllerNotFoundException("ID NOT FOUND" + id);
		}
		catch (DataBaseException e) {
			throw new DataBaseException("Integrity violetion" + id);
		}
	}
	
	private void clientDtoToClientEntity(ClientDto clientDto, Client clientEntity) {
		clientEntity.setName(clientDto.getName());
		clientEntity.setCpf(clientDto.getCpf());
		clientEntity.setIncome(clientDto.getIncome());
		clientEntity.setBirthDate(clientDto.getBirthDate());
		clientEntity.setChildren(clientDto.getChildren());
	}
	
	
	
	
}
