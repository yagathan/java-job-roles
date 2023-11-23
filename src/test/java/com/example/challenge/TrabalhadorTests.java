package com.example.challenge;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.challenge.dto.TrabalhadorDTO;
import com.example.challenge.entity.Cargo;
import com.example.challenge.entity.Setor;
import com.example.challenge.entity.Trabalhador;
import com.example.challenge.repository.CargoRepository;
import com.example.challenge.repository.SetorRepository;
import com.example.challenge.repository.TrabalhadorRepository;
import com.example.challenge.service.TrabalhadorServiceImpl;

@SpringBootTest
public class TrabalhadorTests {
	
	 @InjectMocks
	    private TrabalhadorServiceImpl trabalhadorService;

	    @Mock
	    private TrabalhadorRepository trabalhadorRepository;

	    @Mock
	    private ModelMapper modelMapper;

	    @Mock
	    private SetorRepository setorRepository;

	    @Mock
	    private CargoRepository cargoRepository;

	    @Test
	    public void testInsertTrabalhador() {
	        TrabalhadorDTO trabalhadorDTO = new TrabalhadorDTO();
	        trabalhadorDTO.setCpf("12345678901");
	        UUID setorId = UUID.randomUUID();
	        UUID cargoId = UUID.randomUUID();
	        trabalhadorDTO.setSetorId(setorId);
	        trabalhadorDTO.setCargoId(cargoId);

	        when(trabalhadorRepository.existsByCpf("12345678901")).thenReturn(false);
	        when(setorRepository.findById(setorId)).thenReturn(Optional.of(new Setor()));
	        when(cargoRepository.findById(cargoId)).thenReturn(Optional.of(new Cargo()));
	        when(trabalhadorRepository.save(any(Trabalhador.class))).thenReturn(new Trabalhador());
	        when(modelMapper.map(any(), eq(Trabalhador.class))).thenReturn(new Trabalhador());
	        when(modelMapper.map(any(), eq(TrabalhadorDTO.class))).thenReturn(new TrabalhadorDTO());

	        TrabalhadorDTO result = trabalhadorService.insert(trabalhadorDTO);

	        assertNotNull(result);
	        verify(trabalhadorRepository, times(1)).existsByCpf("12345678901");
	        verify(setorRepository, times(1)).findById(setorId);
	        verify(cargoRepository, times(1)).findById(cargoId);
	        verify(trabalhadorRepository, times(1)).save(any(Trabalhador.class));
	        verify(modelMapper, times(1)).map(any(), eq(Trabalhador.class));
	        verify(modelMapper, times(1)).map(any(), eq(TrabalhadorDTO.class));
	    }

	    @Test
	    public void testUpdateTrabalhador() {
	        UUID trabalhadorId = UUID.randomUUID();
	        TrabalhadorDTO trabalhadorDTO = new TrabalhadorDTO();
	        trabalhadorDTO.setCpf("12345678901");

	        Trabalhador trabalhadorExistente = new Trabalhador();
	        when(trabalhadorRepository.findById(trabalhadorId)).thenReturn(Optional.of(trabalhadorExistente));
	        when(trabalhadorRepository.save(any(Trabalhador.class))).thenReturn(new Trabalhador());
	        when(modelMapper.map(any(), eq(Trabalhador.class))).thenReturn(new Trabalhador());
	        when(modelMapper.map(any(), eq(TrabalhadorDTO.class))).thenReturn(new TrabalhadorDTO());

	        TrabalhadorDTO result = trabalhadorService.update(trabalhadorId, trabalhadorDTO);

	        assertNotNull(result);
	        verify(trabalhadorRepository, times(1)).findById(trabalhadorId);
	        verify(trabalhadorRepository, times(1)).save(any(Trabalhador.class));
	        verify(modelMapper, times(1)).map(any(), eq(Trabalhador.class));
	        verify(modelMapper, times(1)).map(any(), eq(TrabalhadorDTO.class));
	    }

	    @Test
	    public void testDeleteTrabalhador() {
	        UUID trabalhadorId = UUID.randomUUID();
	        Trabalhador trabalhadorExistente = new Trabalhador();

	        when(trabalhadorRepository.findById(trabalhadorId)).thenReturn(Optional.of(trabalhadorExistente));

	        trabalhadorService.delete(trabalhadorId);

	        verify(trabalhadorRepository, times(1)).findById(trabalhadorId);
	        verify(trabalhadorRepository, times(1)).delete(trabalhadorExistente);
	    }
	}


