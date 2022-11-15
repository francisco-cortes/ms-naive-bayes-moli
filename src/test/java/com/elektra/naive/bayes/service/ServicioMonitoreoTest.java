package com.elektra.naive.bayes.service;

import com.elektra.naive.bayes.dto.DtoRespuestaEstado;
import com.elektra.naive.bayes.servicios.ServicioMonitoreo;
import com.elektra.naive.bayes.util.Constantes;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class ServicioMonitoreoTest {

  @Inject
  private ServicioMonitoreo servicioMonitoreo;

  @DisplayName("PruebaDaoFrecuencias Unitaria sobre monitoreo")
  @Test
  public void testMonitoreo(){
    DtoRespuestaEstado dtoRespuestaEstado = servicioMonitoreo.generarUid();
    assertEquals(Constantes.ESTADO_OK, dtoRespuestaEstado.getMensaje());
  }

}
