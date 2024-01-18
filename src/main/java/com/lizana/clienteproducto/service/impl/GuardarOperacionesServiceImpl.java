package com.lizana.clienteproducto.service.impl;

import com.lizana.clienteproducto.model.DataTransfer;
import com.lizana.clienteproducto.model.OperacionDtoUx;
import com.lizana.clienteproducto.model.StatusResponse;
import com.lizana.clienteproducto.model.externooperacion.OperacionDto;
import com.lizana.clienteproducto.model.externosaldo.SaldoDto;
import com.lizana.clienteproducto.service.GuardarOperacionesService;
import com.lizana.clienteproducto.util.ServiceOperacionGuardar;
import com.lizana.clienteproducto.util.ServiceProductoExtraer;
import com.lizana.clienteproducto.util.ServiceSaldoExtraer;
import com.lizana.clienteproducto.util.ServiceSaldoGuardar;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class GuardarOperacionesServiceImpl implements GuardarOperacionesService {

  private static final Logger logger = Logger.getLogger(GuardarOperacionesServiceImpl.class);


  @Override
  public Mono<StatusResponse> saveOperacion(OperacionDtoUx dto) {
    logger.info("GuardarOperacionesServiceImpl.class");
    return ServiceSaldoExtraer.getSaldo(dto.getIdSaldo())
        .map(this::validarSaldoResponse)
        .flatMap(x -> ServiceProductoExtraer.getProduct(x.getDetail()))
        .flatMap(x -> metodoGrabar2(x,dto));

  }

  private com.lizana.clienteproducto.model.externosaldo.StatusResponse
  validarSaldoResponse(com.lizana.clienteproducto.model.externosaldo.StatusResponse x) {
    logger.info("validarSaldoResponse.class: " + x);
    return x;
  }
   private Mono<StatusResponse> metodoGrabar2(DataTransfer dataTransfer,OperacionDtoUx dto) {
    logger.info("metodoGrabar2" );
    SaldoDto newSaldoRequest = dataTransfer.getSaldoDtoResponse();
    newSaldoRequest.setSaldo(dataTransfer.getSaldoDtoResponse().getSaldo().longValue()+dto.getMonto());


    Mono<com.lizana.clienteproducto.model.externosaldo
        .StatusResponse> maybeSaldo = ServiceSaldoGuardar
        .saveSaldo(newSaldoRequest);
    //aca fuarda saldo

    OperacionDto newOperacion = new OperacionDto();

    newOperacion.setIdSaldo(dataTransfer.getSaldoDtoResponse().getId());
    newOperacion.setTipoDeOperacion(dto.getTipoDeOperacion());
    newOperacion.setMonto(dto.getMonto());

    Mono<com.lizana.clienteproducto.model.externooperacion
        .StatusResponse> maybeOperacion = ServiceOperacionGuardar
        .saveOperation(newOperacion);
    return Mono.zip(maybeSaldo, maybeOperacion,
        (valueCliente, valueProducto) -> validarGuardado(valueCliente, valueProducto));
  }

  private StatusResponse validarGuardado(com.lizana.clienteproducto.model.externosaldo
                                     .StatusResponse valueCliente, com.lizana.clienteproducto
                                     .model.externooperacion.StatusResponse valueProducto) {
    System.out.println("validarGuardado");

    StatusResponse statusResponse = new StatusResponse();
    statusResponse.setCode(HttpStatus.CREATED.value());
    statusResponse.setDescription(HttpStatus.CREATED.name());

    return statusResponse;
  }
}
