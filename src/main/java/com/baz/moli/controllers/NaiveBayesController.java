package com.baz.moli.controllers;

import com.baz.moli.dtos.EstadoResponseDto;
import com.baz.moli.dtos.NaiveBayesRequestDto;
import com.baz.moli.dtos.NaiveBayesResponseDto;
import com.baz.moli.exception.ErrorInternoException;
import com.baz.moli.services.CalculoNaiveBayesService;
import com.baz.moli.services.MonitoreoService;
import com.baz.moli.utilis.Constantes;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * <b>ModuloNaiveBayesController</b>
 * @descripcion: clase controller que aloja los endpoints;
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 09/06/22
 */

@Path("/remesas/modulo-naive-bayes")
public class NaiveBayesController {

  /*
  instacia del servicio monitoreo a tra ves de inyeccion
   */
  @Inject
  private MonitoreoService monitoreoService;

  @Inject
  private CalculoNaiveBayesService calculoNaiveBayesService;

  /**
   * <b>naiveBayes</b>
   * @descripcion: endpoint principal.
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   *
   * @ultimaModificacion: 09/06/22
   */
  @POST
  @Path("/calcula-nb")
  @Operation(operationId = "1",
    summary = "Metodo que retorna un posible tipo de nombre utilizando probabilidades naiveBayes")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @APIResponses(value =
    {
      @APIResponse(
        responseCode = Constantes.HTTP_200,
        description = "Respuesta Controlada",
        content = @Content(mediaType = "application/json",
          schema =  @Schema(implementation = NaiveBayesResponseDto.class))),
      @APIResponse(
        responseCode = Constantes.HTTP_500,
        description = "Error Interno en la aplicación",
        content = @Content(mediaType = "application/json",
          schema =  @Schema(implementation = ErrorInternoException.class))),

    })
  public Response naiveBayes(@RequestBody NaiveBayesRequestDto request){
    /*
    modelo de datos con la salida
     */
    NaiveBayesResponseDto respuestaNb = calculoNaiveBayesService.naiveBayes(request);
    /*
    retorna el modelo como entidad para parseo como Json
     */
    return Response.ok().entity(respuestaNb).build();
  }

  /**
   * <b>status</b>
   * @descripcion: Método para validar el estado del microservicio
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   *
   * @ultimaModificacion: 20/06/22
   */
  @GET
  @Path("/estado")
  @Operation(
    summary = "Metodo de consulta al estado y Uid del microservicio",
    description = "description")
  @Produces(MediaType.APPLICATION_JSON)
  public Response status(){
    /*
    modelo con con los datos de salida
     */
    EstadoResponseDto estadoResponseDto = monitoreoService.generarUid();
    /*
    retorna el objeto como entidad para el parceo como json
     */
    return Response.ok().entity(estadoResponseDto).build();
  }
}
