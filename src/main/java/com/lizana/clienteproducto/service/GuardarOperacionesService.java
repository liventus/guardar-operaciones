package com.lizana.clienteproducto.service;

import com.lizana.clienteproducto.model.OperacionDtoUx;
import com.lizana.clienteproducto.model.StatusResponse;
import io.reactivex.rxjava3.core.Maybe;

public interface GuardarOperacionesService {



    Maybe<StatusResponse> saveOperacion(OperacionDtoUx ProductObject);


}
