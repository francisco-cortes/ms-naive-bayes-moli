package com.elektra.naive.bayes.controlador;

import com.elektra.naive.bayes.dto.DtoRespuestaEstado;
import com.elektra.naive.bayes.servicios.ServicioMonitoreo;
import com.elektra.naive.bayes.util.Constantes;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
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
  @APIResponses(value =
    {
      @APIResponse(
        responseCode = Constantes.CODIGO_HTTP_200,
        description = "Respuesta Controlada",
        content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = DtoRespuestaEstado.class))),
    })
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
