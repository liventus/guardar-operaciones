package com.lizana.clienteproducto.service.impl;

import com.lizana.clienteproducto.model.DataTransfer;
import com.lizana.clienteproducto.model.OperacionDtoUx;
import com.lizana.clienteproducto.model.StatusResponse;
import com.lizana.clienteproducto.model.externosaldo.SaldoDto;
import com.lizana.clienteproducto.service.GuardarOperacionesService;
import com.lizana.clienteproducto.util.ServiceOperacionGuardar;
import com.lizana.clienteproducto.util.ServiceProductoExtraer;
import com.lizana.clienteproducto.util.ServiceSaldoExtraer;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.stereotype.Service;

@Service
public class GuardarOperacionesServiceImpl implements GuardarOperacionesService {

  @Override
  public Maybe<StatusResponse> saveOperacion(OperacionDtoUx dto) {

    //extraer la data de la tabla de saldo


    return ServiceSaldoExtraer.extraerSaldo(dto.getIdSaldo())
        .map(x -> validarSaldoResponse(x))
        .flatMap(x -> ServiceProductoExtraer.serviceProductExtraerWc(x.getDetail()))
        .map(x->validarRespuestaProducto(x))
        .map(x-> metodoGrabar2(x));

  }

  private com.lizana.clienteproducto.model.externosaldo.StatusResponse
  validarSaldoResponse(com.lizana.clienteproducto.model.externosaldo.StatusResponse x) {

    System.out.println(x);


    return x;
  }

  private DataTransfer
  validarRespuestaProducto(DataTransfer x) {
    System.out.println(x);

    return x;
  }

  private Maybe<StatusResponse> metodoGrabar(DataTransfer valueProduct) {


    SaldoDto saldoDto = new SaldoDto();
    saldoDto.setProducto(valueProduct.getDetail().getIdDeProducto());
    saldoDto.setFirmante(dto.getFirmante());
    saldoDto.setTitular(dto.getTitular());
    saldoDto.setSaldo(0);

    Maybe<com.lizana.clienteproducto.model.externosaldo.StatusResponse> responseSaldo = ServiceOperacionGuardar.serviceProductWc(dto);


    return responseSaldo
        .map(statusResponse -> {
          System.out.println(statusResponse.getCode());
          System.out.println(statusResponse.getDescription());
          System.out.println(statusResponse.getDetail().toString());
          StatusResponse s = new StatusResponse();
          s.setCode(statusResponse.getCode());
          s.setDescription(statusResponse.getDescription());
          s.setDetail(statusResponse.getDetail());
          return s;
        });
  }

  private StatusResponse metodoGrabar2(DataTransfer dataTransfer) {
    //guardar saldo con nuevo saldo


    Maybe<com.lizana.clienteproducto.model.externosaldo.StatusResponse> responseSaldo = ServiceOperacionGuardar.serviceProductWc(dto);
    // guardar operacion

    return responseSaldo
        .map(statusResponse -> {
          System.out.println(statusResponse.getCode());
          System.out.println(statusResponse.getDescription());
          System.out.println(statusResponse.getDetail().toString());
          StatusResponse s = new StatusResponse();
          s.setCode(statusResponse.getCode());
          s.setDescription(statusResponse.getDescription());
          s.setDetail(statusResponse.getDetail());
          return s;
        });
  }
}
