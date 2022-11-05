package com.baz.moli.controlador;

import com.baz.moli.dto.DtoRespuestaEstado;
import com.baz.moli.servicios.ServicioMonitoreo;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/datos/naive-bayes")
@Tag(name = "Monitoreo - naive-bayes")
public class ControladorMonitoreo {

  @Inject
  private ServicioMonitoreo servicioMonitoreo;

  /**
   * <b>Estado</b>
   * @descripcion: Método para validar el estado del microservicio
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 13/10/22
   */
  @Operation(
    operationId = "2",
    summary = "Se realiza el test de disponibilidad al microservicio.")
  @GetMapping(value ="/status", produces = MediaType.APPLICATION_JSON_VALUE)
  public Response Estado(){
    /*
    modelo con los datos de salida
     */
    DtoRespuestaEstado dtoEstadoResponse = ServicioMonitoreo.generarUid();
    /*
    retorna el objeto como entidad para el parseo como json
     */
    return Response.ok().entity(dtoEstadoResponse).build();
  }

}
