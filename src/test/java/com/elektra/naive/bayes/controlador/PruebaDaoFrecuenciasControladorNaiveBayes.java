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

  @DisplayName("Prueba desde el endpoint principal clasificación correcta")
  @Test
  public void PruebaNaiveBayes(){
    DtoPeticionNaiveBayes peticion = new DtoPeticionNaiveBayes();
    peticion.setNombre("JbMcAZhekXc33Y7NMH5kpA");
    peticion.setTipoNombre("NOMBRE");

    given()
      .when()
      .header("uid", "UID12323453451234")
      .header("Content-Type", "application/json")
      .body(peticion)
      .post("/datos/naive-bayes/calcula-naive-bayes")
      .then()
      .statusCode(200)
      .body("mensaje",equalTo(Constantes.CODIGO_CLASIFICACION_CORRECTA + " "
        + Constantes.MENSAJE_CLASIFICACION_CORRECTA));
  }

  @DisplayName("PruebaDaoFrecuencias sobre el endpoint Status probable nombre")
  @Test
  public void probarPosibleNombre(){
    DtoPeticionNaiveBayes peticion = new DtoPeticionNaiveBayes();
    peticion.setNombre("JbMcAZhekXc33Y7NMH5kpA");
    peticion.setTipoNombre("APELLIDO");
    given()
      .when()
      .header("uid", "UID12323453451234")
      .header("Content-Type", "application/json")
      .body(peticion)
      .post("/datos/naive-bayes/calcula-naive-bayes")
      .then()
      .statusCode(200)
      .body("mensaje",equalTo(Constantes.CODIGO_PROBABLE_NOMBRE + " " + Constantes.PROBABLE_NOMBRE));
  }

  @DisplayName("PruebaDaoFrecuencias sobre el endpoint Status probable apellido")
  @Test
  public void probarPosibleApellido(){
    DtoPeticionNaiveBayes peticion = new DtoPeticionNaiveBayes();
    peticion.setNombre("tjrljQgpkY3rjr2GCXySlA");
    peticion.setTipoNombre("NOMBRE");
    given()
      .when()
      .header("uid", "UID12323453451234")
      .header("Content-Type", "application/json")
      .body(peticion)
      .post("/datos/naive-bayes/calcula-naive-bayes")
      .then()
      .statusCode(200)
      .body("mensaje",equalTo(Constantes.CODIGO_PROBABLE_APELLIDO + " " + Constantes.PROBABLE_APELLIDO));
  }

  @DisplayName("PruebaDaoFrecuencias sobre el endpoint Status no encontrado (Sin frecuencias)")
  @Test
  public void probarNoEncontrado(){
    DtoPeticionNaiveBayes peticion = new DtoPeticionNaiveBayes();
    peticion.setNombre("cYd0NIDMCrp6j2iXa5eE-Q");
    peticion.setTipoNombre("NOMBRE");
    given()
      .when()
      .header("uid", "UID12323453451234")
      .header("Content-Type", "application/json")
      .body(peticion)
      .post("/datos/naive-bayes/calcula-naive-bayes")
      .then()
      .statusCode(200)
      .body("mensaje",equalTo(Constantes.CODIGO_SIN_FRECUENCIAS + " "
        + Constantes.MENSAJE_ERROR_CLASIFICACION));
  }

}
