package com.elektra.naive.bayes.controlador;

import com.elektra.naive.bayes.dto.DtoPeticionNaiveBayes;
import com.elektra.naive.bayes.util.Constantes;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class PruebaDaoFrecuenciasControladorNaiveBayes {

  private static final String RUTA = "/datos/naive-bayes/calcula-naive-bayes";
  private static final String UID = "UID123456789012";
  private static final String ENCABEZADO_UID = "uid";
  private static final String TOKEN = "022DEE73F8528EA4445B133DDB5B224848B2258B";
  private static final String ENCABEZADO_TOKEN = "token";
  private static final String CONTENT = "Content-Type";
  private static final String APLICATION = "application/json";
  private static final String MENSAJE = "mensaje";
  private static final String DETALLES = "detalles";
  private static final String ES_NOMBRE = "NOMBRE";
  private static final String ES_APELLIDO = "APELLIDO";
  private static final String LEONARDO = "JbMcAZhekXc33Y7NMH5kpA";
  private static final String GARCIA = "tjrljQgpkY3rjr2GCXySlA";

  @DisplayName("Prueba desde el endpoint principal clasificación correcta")
  @Test
  public void PruebaNaiveBayes(){
    DtoPeticionNaiveBayes peticion = new DtoPeticionNaiveBayes();
    peticion.setNombre(LEONARDO);
    peticion.setTipoNombre(ES_NOMBRE);

    given()
      .when()
      .header(ENCABEZADO_UID,UID)
      .header(ENCABEZADO_TOKEN,TOKEN)
      .header(CONTENT, APLICATION)
      .body(peticion)
      .post(RUTA)
      .then()
      .statusCode(200)
      .body(MENSAJE,equalTo(Constantes.CODIGO_CLASIFICACION_CORRECTA + " "
        + Constantes.MENSAJE_CLASIFICACION_CORRECTA));
  }

  @DisplayName("Prueba desde el endpoint principal clasificación correcta para apellido")
  @Test
  public void PruebaNaiveBayesApellido(){
    DtoPeticionNaiveBayes peticion = new DtoPeticionNaiveBayes();
    peticion.setNombre(GARCIA);
    peticion.setTipoNombre(ES_APELLIDO);

    given()
      .when()
      .header(ENCABEZADO_UID,UID)
      .header(ENCABEZADO_TOKEN,TOKEN)
      .header(CONTENT, APLICATION)
      .body(peticion)
      .post(RUTA)
      .then()
      .statusCode(200)
      .body(MENSAJE,equalTo(Constantes.CODIGO_CLASIFICACION_CORRECTA + " "
        + Constantes.MENSAJE_CLASIFICACION_CORRECTA));
  }

  @DisplayName("PruebaDaoFrecuencias sobre el endpoint Status probable nombre")
  @Test
  public void probarPosibleNombre(){
    DtoPeticionNaiveBayes peticion = new DtoPeticionNaiveBayes();
    peticion.setNombre(LEONARDO);
    peticion.setTipoNombre(ES_APELLIDO);
    given()
      .when()
      .header(ENCABEZADO_UID,UID)
      .header(ENCABEZADO_TOKEN,TOKEN)
      .header(CONTENT, APLICATION)
      .body(peticion)
      .post(RUTA)
      .then()
      .statusCode(200)
      .body(MENSAJE,equalTo(Constantes.CODIGO_PROBABLE_NOMBRE + " " + Constantes.PROBABLE_NOMBRE));
  }

  @DisplayName("PruebaDaoFrecuencias sobre el endpoint Status probable apellido")
  @Test
  public void probarPosibleApellido(){
    DtoPeticionNaiveBayes peticion = new DtoPeticionNaiveBayes();
    peticion.setNombre(GARCIA);
    peticion.setTipoNombre(ES_NOMBRE);
    given()
      .when()
      .header(ENCABEZADO_UID,UID)
      .header(ENCABEZADO_TOKEN,TOKEN)
      .header(CONTENT, APLICATION)
      .body(peticion)
      .post(RUTA)
      .then()
      .statusCode(200)
      .body(MENSAJE,equalTo(Constantes.CODIGO_PROBABLE_APELLIDO + " " + Constantes.PROBABLE_APELLIDO));
  }

  @DisplayName("PruebaDaoFrecuencias sobre el endpoint Status no encontrado (Sin frecuencias)")
  @Test
  public void probarNoEncontrado(){
    DtoPeticionNaiveBayes peticion = new DtoPeticionNaiveBayes();
    peticion.setNombre("DXwSIXp6Gw1jpuWIRit6PQ");
    peticion.setTipoNombre(ES_NOMBRE);
    given()
      .when()
      .header(ENCABEZADO_UID,UID)
      .header(ENCABEZADO_TOKEN,TOKEN)
      .header(CONTENT, APLICATION)
      .body(peticion)
      .post(RUTA)
      .then()
      .statusCode(200)
      .body(MENSAJE,equalTo(Constantes.CODIGO_SIN_FRECUENCIAS + " "
        + Constantes.MENSAJE_ERROR_CLASIFICACION));
  }

  @DisplayName("uid invalido")
  @Test
  public void probarUidInvalido(){
    DtoPeticionNaiveBayes peticion = new DtoPeticionNaiveBayes();
    peticion.setNombre(GARCIA);
    peticion.setTipoNombre(ES_NOMBRE);
    given()
      .when()
      .header(ENCABEZADO_UID,"UID")
      .header(ENCABEZADO_TOKEN,TOKEN)
      .header(CONTENT, APLICATION)
      .body(peticion)
      .post(RUTA)
      .then()
      .statusCode(200)
      .body(MENSAJE,equalTo(Constantes.MENSAJE_CODIGO_400));
  }

  @DisplayName("Recurso no encontrado")
  @Test
  public void PruebaRecursoNoEncontrado(){
    DtoPeticionNaiveBayes peticion = new DtoPeticionNaiveBayes();
    peticion.setNombre(LEONARDO);
    peticion.setTipoNombre(ES_NOMBRE);

    given()
      .when()
      .header(ENCABEZADO_UID,UID)
      .header(ENCABEZADO_TOKEN,TOKEN)
      .header(CONTENT, APLICATION)
      .body(peticion)
      .post(RUTA + "/")
      .then()
      .statusCode(200)
      .body(MENSAJE,equalTo(Constantes.MENSAJE_CODIGO_404));
  }

  @DisplayName("uid invalido")
  @Test
  public void probarTokenInvalido(){
    DtoPeticionNaiveBayes peticion = new DtoPeticionNaiveBayes();
    peticion.setNombre(GARCIA);
    peticion.setTipoNombre(ES_NOMBRE);
    given()
      .when()
      .header(ENCABEZADO_UID,UID)
      .header(ENCABEZADO_TOKEN,"TOKEN")
      .header(CONTENT, APLICATION)
      .body(peticion)
      .post(RUTA)
      .then()
      .statusCode(200)
      .body(MENSAJE,equalTo(Constantes.MENSAJE_CODIGO_400));
  }

  @DisplayName("tipo nombre invalido")
  @Test
  public void probarTipoNombreInvalido(){
    DtoPeticionNaiveBayes peticion = new DtoPeticionNaiveBayes();
    peticion.setNombre(GARCIA);
    peticion.setTipoNombre("ES_NOMBRE");
    given()
      .when()
      .header(ENCABEZADO_UID,UID)
      .header(ENCABEZADO_TOKEN,TOKEN)
      .header(CONTENT, APLICATION)
      .body(peticion)
      .post(RUTA)
      .then()
      .statusCode(200)
      .body(MENSAJE,equalTo(Constantes.CODIGO_TIPO_INCORRECTO + " " +
        Constantes.MENSAJE_ERROR_CLASIFICACION));
  }

}
