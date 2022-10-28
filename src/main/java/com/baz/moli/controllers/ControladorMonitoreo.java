package com.baz.moli.controllers;

import com.baz.moli.dtos.EstadoResponseDto;
import com.baz.moli.services.MonitoreoService;
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
  private MonitoreoService monitoreoService;

  /**
   * <b>status</b>
   * @descripcion: Método para validar el estado del microservicio
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 13/10/22
   */
  @Operation(
    operationId = "2",
    summary = "Se realiza el test de disponibilidad al microservicio.")
  @GetMapping(value ="/status", produces = MediaType.APPLICATION_JSON_VALUE)
  public Response status(){
    /*
    modelo con con los datos de salida
     */
    EstadoResponseDto dtoEstadoResponse = MonitoreoService.generarUid();
    /*
    retorna el objeto como entidad para el parceo como json
     */
    return Response.ok().entity(dtoEstadoResponse).build();
  }

}
