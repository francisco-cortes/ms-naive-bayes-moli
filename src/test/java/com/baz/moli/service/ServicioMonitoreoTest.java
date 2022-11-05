package com.baz.moli.service;

import com.baz.moli.dto.DtoRespuestaEstado;
import com.baz.moli.servicios.ServicioMonitoreo;
import com.baz.moli.util.Constantes;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class ServicioMonitoreoTest {

  @Inject
  private ServicioMonitoreo servicioMonitoreo;

  @DisplayName("Prueba Unitaria sobre monitoreo")
  @Test
  public void testMonitoreo(){
    DtoRespuestaEstado dtoRespuestaEstado = servicioMonitoreo.generarUid();
    assertEquals(Constantes.ESTADO_OK, dtoRespuestaEstado.getMensaje());
  }

}
