package com.example.challenge.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exampe.challenge.exception.NomeDeSetorDuplicadoException;
import com.exampe.challenge.exception.SetorNotFoundException;
import com.example.challenge.dto.SetorDTO;
import com.example.challenge.entity.Setor;
import com.example.challenge.repository.SetorRepository;

@Service
public class SetorServiceImpl implements SetorService{
	
	@Autowired
	private SetorRepository setorRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public SetorDTO insert(SetorDTO setorDTO) {
		
		Setor setor = modelMapper.map(setorDTO,Setor.class);
		if(setorRepository.existsByName(setorDTO.getName())) {
			throw new NomeDeSetorDuplicadoException();
			
		}
		
		
		setor = setorRepository.save(setor);
		
		return modelMapper.map(setor, SetorDTO.class);
			
		
	}


	@Override
	public SetorDTO update(UUID id, SetorDTO setorDTO) {
		
		 
	    Setor setorExistente = setorRepository.findById(id)
	            .orElseThrow(() -> new SetorNotFoundException());

	    
	    modelMapper.map(setorDTO, setorExistente);

	    
	    if (setorRepository.existsByName(setorDTO.getName())) {
	        throw new NomeDeSetorDuplicadoException();
	    }

	
	    setorExistente = setorRepository.save(setorExistente);

	    
	    return modelMapper.map(setorExistente, SetorDTO.class);
	}
	
	@Override
	public void delete(UUID id) {
	    
	    Setor setorExistente = setorRepository.findById(id)
	            .orElseThrow(() -> new SetorNotFoundException());

	    setorRepository.delete(setorExistente);
	}

	


}
