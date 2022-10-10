package com.baz.moli.service;

import com.baz.moli.dtos.NaiveBayesRequestDto;
import com.baz.moli.dtos.NaiveBayesResponseDto;
import com.baz.moli.services.CalculoNaiveBayesService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class CalculoNaiveBayesServiceTest {

  @Inject
  private CalculoNaiveBayesService calculoNaiveBayesService;

  @DisplayName("Prueba Unitaria sobre naive bayes")
  @Test
  public void testCalculoNaiveBayes(){
    /*
    Cambiar docker.internal.host por localhost
     */
    final String tipoNombreEsperado = "NOMBRE";
    NaiveBayesRequestDto request = new NaiveBayesRequestDto();
    request.setNombre("LEONARDO");
    request.setTipoNombre("APELLIDO");
    NaiveBayesResponseDto nb = calculoNaiveBayesService.naiveBayes(request);

    assertEquals(nb.getTipoNombre(),tipoNombreEsperado);
  }

}
