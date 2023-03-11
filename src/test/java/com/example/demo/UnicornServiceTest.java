package com.example.demo;

import com.example.demo.unicorn.Unicorn;
import com.example.demo.unicorn.UnicornRepository;
import com.example.demo.unicorn.UnicornService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UnicornServiceTest {

    @Mock
    private UnicornRepository unicornRepository;

    private UnicornService unicornService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        unicornService = new UnicornService(unicornRepository);
    }

    @Test
    void testCreateUnicorn() {
        Unicorn unicorn = new Unicorn(null,"Sparkle Princess", "White", 104, "Gold", "Sapphire", 94, "cm", 104, "kg", new ArrayList<>());
        when(unicornRepository.save(any(Unicorn.class))).thenReturn(unicorn);

        Unicorn savedUnicorn = unicornService.createUnicorn(unicorn);

        assertEquals(unicorn, savedUnicorn);
        verify(unicornRepository, times(1)).save(unicorn);
    }

    @Test
    void testGetAllUnicorns() {
        List<Unicorn> unicorns = new ArrayList<>();
        when(unicornRepository.findAll()).thenReturn(unicorns);

        List<Unicorn> allUnicorns = unicornService.getAllUnicorns();

        assertEquals(unicorns, allUnicorns);
        verify(unicornRepository, times(1)).findAll();
    }

    @Test
    void testGetUnicornById() {
        Long id = 1L;
        Unicorn unicorn = new Unicorn(null,"Sparkle Princess", "White", 104, "Gold", "Sapphire", 94, "cm", 104, "kg", new ArrayList<>());
        when(unicornRepository.findById(id)).thenReturn(Optional.of(unicorn));

        Unicorn foundUnicorn = unicornService.getUnicornById(id);

        assertEquals(unicorn, foundUnicorn);
        verify(unicornRepository, times(1)).findById(id);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> unicornService.getUnicornById(2L));
        assertEquals("Unicorn not found", exception.getMessage());
    }

    @Test
    void testUpdateUnicorn() {
        System.out.println("HEYYYYYYYYYYY");
        Long id = 1L;
        Unicorn unicorn = new Unicorn(null,"Sparkle Princess", "White", 104, "Gold", "Sapphire", 94, "cm", 104, "kg", new ArrayList<>());
        when(unicornRepository.findById(id)).thenReturn(Optional.of(unicorn));
        when(unicornRepository.save(any(Unicorn.class))).thenReturn(unicorn);

        Unicorn updatedUnicorn = unicornService.updateUnicorn(id, unicorn);

        assertEquals(unicorn, updatedUnicorn);
        verify(unicornRepository, times(1)).findById(id);
        verify(unicornRepository, times(1)).save(unicorn);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> unicornService.updateUnicorn(2L, unicorn));
        assertEquals("Unicorn not found", exception.getMessage());
    }


}
