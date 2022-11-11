package com.elektra.naive.bayes.controlador;

import com.elektra.naive.bayes.dto.DtoPeticionNaiveBayes;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class PruebaControladorMonitoreo {

  @DisplayName("Prueba sobre el endpoint Status")
  @Test
  public void PruebaEstado(){
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
      .body("mensaje",equalTo("OK"));
  }
}
