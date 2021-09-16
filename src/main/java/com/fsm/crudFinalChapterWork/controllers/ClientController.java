package com.fsm.crudFinalChapterWork.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsm.crudFinalChapterWork.dto.ClientDto;
import com.fsm.crudFinalChapterWork.services.ClienteService;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
	@Autowired
	ClienteService clienteService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDto> findById(@PathVariable Long id){
		ClientDto clientById = clienteService.findById(id);
		return ResponseEntity.ok(clientById);
	}
	
	@GetMapping
	public ResponseEntity<Page<ClientDto>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction
			)
	{
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction),orderBy);
		
		
		return null;
	}
}
