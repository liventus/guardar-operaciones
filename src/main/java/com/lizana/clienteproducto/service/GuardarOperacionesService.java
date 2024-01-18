package com.lizana.clienteproducto.service;

import com.lizana.clienteproducto.model.OperacionDtoUx;
import com.lizana.clienteproducto.model.StatusResponse;

import reactor.core.publisher.Mono;

public interface GuardarOperacionesService {



    Mono<StatusResponse> saveOperacion(OperacionDtoUx ProductObject);


}
