package com.elektra.naive.bayes.service;

import com.elektra.naive.bayes.dto.DtoPeticionNaiveBayes;
import com.elektra.naive.bayes.dto.DtoRespuestaNaiveBayes;
import com.elektra.naive.bayes.servicios.ServicioNaiveBayes;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class ServicioNaiveBayesTest {

  @Inject
  private ServicioNaiveBayes servicioNaiveBayes;

  @DisplayName("Prueba Unitaria sobre naive bayes")
  @Test
  public void testCalculoNaiveBayesNombre(){
    /*
    Cambiar docker.internal.host por localhost
     */
    final String tipoNombreEsperado = "NOMBRE";
    DtoPeticionNaiveBayes request = new DtoPeticionNaiveBayes();
    request.setNombre("LEONARDO");
    request.setTipoNombre("APELLIDO");
    DtoRespuestaNaiveBayes nb = servicioNaiveBayes.naiveBayes(request,"TESTUID");

    assertEquals(nb.getTipoNombre(),tipoNombreEsperado);
  }

  @DisplayName("Prueba Unitaria sobre naive bayes apellido")
  @Test
  public void testCalculoNaiveBayesApellido(){
    /*
    Cambiar docker.internal.host por localhost
     */
    final String tipoNombreEsperado = "APELLIDO";
    DtoPeticionNaiveBayes request = new DtoPeticionNaiveBayes();
    request.setNombre("VAZQUEZ");
    request.setTipoNombre("NOMBRE");
    DtoRespuestaNaiveBayes nb = servicioNaiveBayes.naiveBayes(request,"TESTUID");

    assertEquals(nb.getTipoNombre(),tipoNombreEsperado);
  }

}
