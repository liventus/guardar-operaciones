package com.lizana.clienteproducto.service;

import com.lizana.clienteproducto.model.PerfilUser;
import com.lizana.clienteproducto.model.StatusResponse;
import io.reactivex.rxjava3.core.Maybe;

public interface ClienteProductoService {



    Maybe<StatusResponse> saveProductwc(PerfilUser ProductObject);


}
