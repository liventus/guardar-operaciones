package com.lizana.clienteproducto.controller;


import org.apache.log4j.Logger;
import com.lizana.clienteproducto.model.OperacionDtoUx;
import com.lizana.clienteproducto.model.StatusResponse;
import com.lizana.clienteproducto.service.GuardarOperacionesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping(GuardarOperacionesController.GUARDAROPERACIONES)

public class GuardarOperacionesController {

  private static final Logger logger = Logger.getLogger(GuardarOperacionesController.class);

  public static final String GUARDAROPERACIONES = "/guardaroperaciones";

  @Autowired
  GuardarOperacionesService clienteProductoService;

  @PostMapping(value = "/create", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE})
  public Mono<StatusResponse> create(@RequestBody OperacionDtoUx product) {
    logger.info("pasando por controller");
    return clienteProductoService.saveOperacion(product);
  }


  @PostMapping
  public Mono<StatusResponse> extraerPorCuenta(@RequestBody OperacionDtoUx product) {
    logger.info("pasando por controller");
    return clienteProductoService.saveOperacion(product);
  }


}
