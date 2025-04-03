package com.example.nobsv2;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.nobsv2.catfact.CatFactController;
import com.example.nobsv2.catfact.CatFactService;

public class CatFactAPITest {
    // To Implement

    @Mock
    private CatFactController catFactController;

    @InjectMocks
    private CatFactService catFactService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

}
