package com.elektra.naive.bayes.service;

import com.elektra.naive.bayes.dto.DtoPeticionNaiveBayes;
import com.elektra.naive.bayes.dto.DtoRespuestaNaiveBayes;
import com.elektra.naive.bayes.servicios.ServicioNaiveBayes;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.rmi.server.UID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class ServicioNaiveBayesTest {

  @Inject
  private ServicioNaiveBayes servicioNaiveBayes;
  private static final String UID = "UID123456789012";
  private static final String TOKEN = "022DEE73F8528EA4445B133DDB5B224848B2258B";
  @DisplayName("PruebaDaoFrecuencias Unitaria sobre naive bayes")
  @Test
  public void testCalculoNaiveBayesNombre(){

    /*
    Cambiar docker.internal.host por localhost
     */
    final String tipoNombreEsperado = "NOMBRE";
    DtoPeticionNaiveBayes request = new DtoPeticionNaiveBayes();
    request.setNombre("LEONARDO");
    request.setTipoNombre("APELLIDO");
    DtoRespuestaNaiveBayes nb = servicioNaiveBayes.naiveBayes(request,UID, TOKEN);

    assertEquals(nb.getTipoNombre(),tipoNombreEsperado);
  }

  @DisplayName("PruebaDaoFrecuencias Unitaria sobre naive bayes apellido")
  @Test
  public void testCalculoNaiveBayesApellido(){
    /*
    Cambiar docker.internal.host por localhost
     */
    final String tipoNombreEsperado = "APELLIDO";
    DtoPeticionNaiveBayes request = new DtoPeticionNaiveBayes();
    request.setNombre("VAZQUEZ");
    request.setTipoNombre("NOMBRE");
    DtoRespuestaNaiveBayes nb = servicioNaiveBayes.naiveBayes(request, UID,TOKEN);

    assertEquals(nb.getTipoNombre(),tipoNombreEsperado);
  }

}
