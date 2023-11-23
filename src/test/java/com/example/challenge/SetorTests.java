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

import com.example.challenge.dto.SetorDTO;
import com.example.challenge.entity.Setor;
import com.example.challenge.repository.SetorRepository;
import com.example.challenge.service.SetorServiceImpl;

@SpringBootTest
public class SetorTests {
	

    @InjectMocks
    private SetorServiceImpl setorService;

    @Mock
    private SetorRepository setorRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testInsertSetor() {
        SetorDTO setorDTO = new SetorDTO();
        setorDTO.setName("Setor de Teste");
        setorDTO.setId(UUID.randomUUID());
        
        when(setorRepository.existsByName("Setor de Teste")).thenReturn(false);
        when(setorRepository.save(any(Setor.class))).thenReturn(new Setor());
        when(modelMapper.map(any(), eq(Setor.class))).thenReturn(new Setor());
        when(modelMapper.map(any(), eq(SetorDTO.class))).thenReturn(new SetorDTO());

        SetorDTO result = setorService.insert(setorDTO);
        

        assertNotNull(result);
        verify(setorRepository, times(1)).existsByName("Setor de Teste");
        verify(setorRepository, times(1)).save(any(Setor.class));
        verify(modelMapper, times(1)).map(any(), eq(Setor.class));
        verify(modelMapper, times(1)).map(any(), eq(SetorDTO.class));
    }
    
    @Test
    public void testUpdateSetor() {
        UUID setorId = UUID.randomUUID();
        SetorDTO setorDTO = new SetorDTO();
        setorDTO.setName("Setor Atualizado");

        Setor setorExistente = new Setor();
        when(setorRepository.findById(setorId)).thenReturn(Optional.of(setorExistente));
        when(setorRepository.existsByName(setorDTO.getName())).thenReturn(false);
        when(setorRepository.save(any(Setor.class))).thenReturn(new Setor());
        when(modelMapper.map(any(), eq(Setor.class))).thenReturn(new Setor());
        when(modelMapper.map(any(), eq(SetorDTO.class))).thenReturn(new SetorDTO());

        SetorDTO result = setorService.update(setorId, setorDTO);

        assertNotNull(result);
        verify(setorRepository, times(1)).findById(setorId);
        verify(setorRepository, times(1)).existsByName(setorDTO.getName());
        verify(setorRepository, times(1)).save(any(Setor.class));
        verify(modelMapper, times(1)).map(any(), eq(Setor.class));
        verify(modelMapper, times(1)).map(any(), eq(SetorDTO.class));
    }

    @Test
    public void testDeleteSetor() {
        UUID setorId = UUID.randomUUID();
        Setor setorExistente = new Setor();

        when(setorRepository.findById(setorId)).thenReturn(Optional.of(setorExistente));

        setorService.delete(setorId);

        verify(setorRepository, times(1)).findById(setorId);
        verify(setorRepository, times(1)).delete(setorExistente);
    }
}
    


