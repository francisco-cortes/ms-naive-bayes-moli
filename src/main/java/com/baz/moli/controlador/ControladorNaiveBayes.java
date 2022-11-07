package com.baz.moli.controlador;

import com.baz.excepciones.DtoExcepcion;
import com.baz.moli.dto.DtoPeticionNaiveBayes;
import com.baz.moli.dto.DtoRespuestaNaiveBayes;
import com.baz.moli.exception.ErrorInternoException;
import com.baz.moli.servicios.ServicioMonitoreo;
import com.baz.moli.servicios.ServicioNaiveBayes;
import com.baz.moli.util.Constantes;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 * <b>ModuloNaiveBayesController</b>
 * @descripcion: clase controller que aloja los endpoints;
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 09/06/22
 */

@RestController
@RequestMapping("/datos/naive-bayes")
@Tag(name = "Controlador - naive-bayes")
public class ControladorNaiveBayes {

  /*
  instancia del servicio monitoreo a través de inyección
   */
  @Inject
  private ServicioMonitoreo servicioMonitoreo;

  @Inject
  private ServicioNaiveBayes servicioNaiveBayes;

  /**
   * <b>naiveBayes</b>
   * @descripcion: endpoint principal.
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 09/06/22
   */
  @Operation(operationId = "1",
    summary = "Método que retorna un posible tipo de nombre utilizando probabilidades naiveBayes")
  @Parameter(name ="token",
    schema = @Schema(type = SchemaType.STRING),
    description = "Token para el iniciar al solicitud.",
    example = "022DEE73F8528EA4445B133DDB5B224848B2258B",
    in = ParameterIn.HEADER, required = Constantes.NO_REQUERIDO)
  @Parameter(name ="uid",
    schema = @Schema(type = SchemaType.STRING),
    description = "Uid para identification del service.",
    example = "UID123412341332",
    in = ParameterIn.HEADER, required = Constantes.ES_REQUERIDO)
  @APIResponses(value =
    {
      @APIResponse(
        responseCode = Constantes.CODIGO_HTTP_200,
        description = "Respuesta Controlada",
        content = @Content(mediaType = "application/json",
          schema =  @Schema(implementation = DtoRespuestaNaiveBayes.class))),
      @APIResponse(
        responseCode = Constantes.CODIGO_HTTP_400,
        description = "Solicitud incorrecta",
        content = @Content(mediaType = "application/json",
          schema =  @Schema(implementation = DtoExcepcion.class))),
      @APIResponse(
        responseCode = Constantes.CODIGO_HTTP_404,
        description = "No Encontrado",
        content = @Content(mediaType = "application/json",
          schema =  @Schema(implementation = DtoExcepcion.class))),
      @APIResponse(
        responseCode = Constantes.CODIGO_HTTP_500,
        description = "Error Interno en la aplicación",
        content = @Content(mediaType = "application/json",
          schema =  @Schema(implementation = DtoExcepcion.class))),
      @APIResponse(
        responseCode = Constantes.CODIGO_HTTP_500,
        description = "Error Interno en la aplicación",
        content = @Content(mediaType = "application/json",
          schema =  @Schema(implementation = ErrorInternoException.class))),

    })
  @PostMapping(value ="/calcula-nb",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
  public Response naiveBayes(@RequestHeader(name = "uid", required = Constantes.ES_REQUERIDO) String uid,
    @RequestBody DtoPeticionNaiveBayes request){
    /*
    modelo de datos con la salida
     */
    DtoRespuestaNaiveBayes respuestaNb = servicioNaiveBayes.naiveBayes(request,uid);
    /*
    retorna el modelo como entidad para parseo como Json
     */
    return Response.ok().entity(respuestaNb).build();
  }
}
