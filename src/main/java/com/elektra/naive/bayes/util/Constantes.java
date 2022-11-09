package com.elektra.naive.bayes.util;

import java.math.BigDecimal;

public class Constantes {

  public final static String NOMBRE_MS = "ms-naive-bayes-moli";

  /*
  Respuesta HTTP
   */
  public final static String CODIGO_HTTP_200 = "200";
  public final static String CODIGO_HTTP_400 = "400";
  public final static String CODIGO_HTTP_401 = "401";
  public final static String CODIGO_HTTP_404 = "404";
  public final static String CODIGO_HTTP_500 = "500";
  public final static String MENSAJE_CODIGO_400 = "Datos de entrada incorrectos, por favor valide su información.";

  public final static String MENSAJE_CODIGO_401 = "No estas autorizado, favor de validar.";
  public final static String MENSAJE_CODIGO_404 = "Recurso no encontrado";
  public final static String MENSAJE_CODIGO_500 = "Ocurrió un inconveniente al procesar la solicitud.";

  public final static String CODIGO_CLASIFICACION_CORRECTA =  "IDMNB00001";
  public final static String CODIGO_PROBABLE_NOMBRE = "IDMNB00002";
  public final static String CODIGO_PROBABLE_APELLIDO =  "IDMNB00003";
  public final static String CODIGO_SIN_FRECUENCIAS =  "IDMNB00004";
  public final static String CODIGO_TIPO_INCORRECTO  =  "IDMNB00005";
  public final static String CODIGO_ERROR_GENERAL =  "IDMNB00006";
  public final static String CODIGO_SOLICITUD_INCORRECTA =  "IDMNB00007";
  public final static String CODIGO_NO_AUTORIZADO =  "IDMNB00008";
  public final static String CODIGO_NO_ENCONTRADO =  "IDMNB00009";

  public static final String HANTEN = "?";
  public static final String IGUAL = "=";
  public static final int CONSTANTE_NB = 10;
  public static final int TIME_OUT = 32000;
  public static final String GET_METHOD = "GET";
  public static final int OK_HTTP_LIMIT = 299;
  public static final int VALOR_EXEPCION = -1;
  public static final int VALOR_CLASIFICACION_CORRECTA = 1;
  public static final int VALOR_CLASIFICACION_INCORRECTA = 0;
  public static final String MENSAJE_CLASIFICACION_CORRECTA = "CLASIFICACION CORRECTA";
  public static final String MENSAJE_ERROR_CLASIFICACION = "ERROR EN CLASIFICACION";
  public static final String PROBABLE_APELLIDO = "PROBABLEMENTE SEA UN APELLIDO";
  public static final String PROBABLE_NOMBRE = "PROBABLEMENTE SEA UN NOMBRE";
  public static final String ES_NOMBRE = "NOMBRE";
  public static final String ES_APELLIDO = "APELLIDO";
  public static final int ZERO_BY_DEFAULT = 0;
  public static final BigDecimal BIG_ZERO_BY_DEFAULT = BigDecimal.valueOf(0);
  public static final String C3REMESASC = "c3remesas";

  public static final int TAMANO_UDI = 15;
  public static final int CICLOS_UDI = 3;
  public static final int LONGITUD_MIN_ENTRADA = 1;
  public static final int LONGITUD_MAX_ENTRADA = 10;

  public static final String ESTADO_OK = "OK";
  public static final String CODIGO_EXITO = "E";
  public static final String MENSAJE_EXITO = "Operacion exitosa.";

  public static final boolean NO_REQUERIDO = false;
  public static final boolean ES_REQUERIDO = true;
}
