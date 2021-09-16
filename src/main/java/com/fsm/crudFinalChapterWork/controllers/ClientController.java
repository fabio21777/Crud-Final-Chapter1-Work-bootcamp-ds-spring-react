package com.fsm.crudFinalChapterWork.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fsm.crudFinalChapterWork.dto.ClientDto;
import com.fsm.crudFinalChapterWork.services.ClienteService;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
	@Autowired
	ClienteService clienteService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDto> findById(@PathVariable Long id) {
		ClientDto clientById = clienteService.findById(id);
		return ResponseEntity.ok(clientById);
	}

	@GetMapping
	public ResponseEntity<Page<ClientDto>> findAll (@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		PageRequest pageRequest = PageRequest.of(
				page,
				linesPerPage,
				Direction.valueOf(direction),
				orderBy);
		Page<ClientDto> pages = clienteService.findAll(pageRequest);
		return ResponseEntity.ok().body(pages);
		
	}
	
	@PostMapping
	public ResponseEntity<ClientDto>inset(@RequestBody ClientDto clientDto){
		clientDto = clienteService.insert(clientDto); 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(clientDto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(clientDto);
	} 
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientDto>update(@RequestBody ClientDto clientDto,@PathVariable Long id){
		clientDto = clienteService.update(clientDto, id);
		return ResponseEntity.ok(clientDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Long id) {
		clienteService.delete(id);
	}
	
}
