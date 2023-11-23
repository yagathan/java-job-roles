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

import com.example.challenge.dto.CargoDTO;
import com.example.challenge.entity.Cargo;
import com.example.challenge.entity.Setor;
import com.example.challenge.repository.CargoRepository;
import com.example.challenge.repository.SetorRepository;
import com.example.challenge.service.CargoServiceImpl;

@SpringBootTest
public class CargoTests {

	@InjectMocks
	private CargoServiceImpl cargoService;

	@Mock
	private CargoRepository cargoRepository;

	@Mock
	private SetorRepository setorRepository;

	@Mock
	private ModelMapper modelMapper;

	@Test
	public void testInsertCargo() {

		CargoDTO cargoDTO = new CargoDTO();
		cargoDTO.setName("Cargo de Teste");
		UUID setId = UUID.randomUUID();
		cargoDTO.setSetorId(setId);
		Setor setor = new Setor();
		setor.setId(setId);
		when(setorRepository.findById(setId)).thenReturn(Optional.of(setor));
		when(cargoRepository.existsByName("Cargo de Teste")).thenReturn(false);
		when(cargoRepository.save(any(Cargo.class))).thenReturn(new Cargo());
		when(modelMapper.map(any(), eq(Cargo.class))).thenReturn(new Cargo());
		when(modelMapper.map(any(), eq(CargoDTO.class))).thenReturn(new CargoDTO());
		CargoDTO result = cargoService.insert(cargoDTO);
		assertNotNull(result);
		verify(setorRepository, times(1)).findById(setId);
		verify(cargoRepository, times(1)).existsByName("Cargo de Teste");
		verify(cargoRepository, times(1)).save(any(Cargo.class));
		verify(modelMapper, times(1)).map(any(), eq(Cargo.class));
		verify(modelMapper, times(1)).map(any(), eq(CargoDTO.class));
	}

	@Test
	public void testUpdateCargo() {
		UUID cargoId = UUID.randomUUID();
		CargoDTO cargoDTO = new CargoDTO();
		cargoDTO.setName("Cargo Atualizado");
        cargoDTO.setSetorId(UUID.randomUUID());

		Cargo cargoExistente = new Cargo();
		when(cargoRepository.findById(cargoId)).thenReturn(Optional.of(cargoExistente));
		when(setorRepository.findById(cargoDTO.getSetorId())).thenReturn(Optional.of(new Setor()));
		when(cargoRepository.existsByName(cargoDTO.getName())).thenReturn(false);
		when(cargoRepository.save(any(Cargo.class))).thenReturn(new Cargo());
		when(modelMapper.map(any(), eq(Cargo.class))).thenReturn(new Cargo());
		when(modelMapper.map(any(), eq(CargoDTO.class))).thenReturn(new CargoDTO());

		CargoDTO result = cargoService.update(cargoId, cargoDTO);

		assertNotNull(result);
		verify(cargoRepository, times(1)).findById(cargoId);
		verify(setorRepository, times(1)).findById(cargoDTO.getSetorId());
		verify(cargoRepository, times(1)).existsByName(cargoDTO.getName());
		verify(cargoRepository, times(1)).save(any(Cargo.class));
		verify(modelMapper, times(1)).map(any(), eq(Cargo.class));
		verify(modelMapper, times(1)).map(any(), eq(CargoDTO.class));
	}

	@Test
	public void testDeleteCargo() {
		UUID cargoId = UUID.randomUUID();
		Cargo cargoExistente = new Cargo();

		when(cargoRepository.findById(cargoId)).thenReturn(Optional.of(cargoExistente));

		cargoService.delete(cargoId);

		verify(cargoRepository, times(1)).findById(cargoId);
		verify(cargoRepository, times(1)).delete(cargoExistente);
	}
}