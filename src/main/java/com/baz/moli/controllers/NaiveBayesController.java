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
  @Operation(operationId = "1",
    summary = "Metodo que retorna un posible tipo de nombre utilizando probabilidades naiveBayes")
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
  @PostMapping(value ="/calcula-nb",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
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
}
