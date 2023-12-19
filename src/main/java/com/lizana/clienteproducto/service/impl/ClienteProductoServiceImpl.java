package com.lizana.clienteproducto.service.impl;

import com.lizana.clienteproducto.model.PerfilUser;
import com.lizana.clienteproducto.model.StatusResponse;
import com.lizana.clienteproducto.model.externosaldo.SaldoDto;
import com.lizana.clienteproducto.service.ClienteProductoService;
import com.lizana.clienteproducto.util.ServiceClient;
import com.lizana.clienteproducto.util.ServiceProducto;
import com.lizana.clienteproducto.util.ServiceSaldo;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class ClienteProductoServiceImpl implements ClienteProductoService {













    @Override
    public Maybe<StatusResponse> saveProductwc(PerfilUser dto) {

        System.out.println("inicio save:wc ");

        Mono<ResponseEntity<com.lizana.clienteproducto.model.externoclient.StatusResponse>> responseClient = ServiceClient.serviceClientWc(dto);

        @NonNull Maybe<ResponseEntity<com.lizana.clienteproducto.model.externoclient.StatusResponse>> convertidoMaybe = Maybe.fromPublisher(responseClient);
        responseClient.doOnSuccess(statusResponse -> {
            System.out.println(statusResponse.getBody().getCode());
            System.out.println(statusResponse.getBody().getDescription());
            System.out.println(statusResponse.getBody().getDetail().toString());
        })
                .doOnError(error -> {
                    // Manejar errores
                    System.err.println("Error al procesar la respuesta: " + error.getMessage());
                })
                .doOnTerminate(() -> {
                    // Acciones cuando la secuencia se completa
                    System.out.println("La secuencia se ha completado.");
                })
                .subscribe();


        Maybe<com.lizana.clienteproducto.model.externoproduct.StatusResponse> responseProduct = ServiceProducto.serviceProductWc(dto);
        responseProduct
                .doOnSuccess(statusResponse -> {
                    System.out.println(statusResponse.getCode());
                    System.out.println(statusResponse.getDescription());
                    System.out.println(statusResponse.getDetail().toString());
                })
                .doOnError(error -> {
                    // Manejar errores
                    System.err.println("Error al procesar la respuesta: " + error.getMessage());
                })
                .doOnComplete(() -> {
                    // Acciones cuando la secuencia se completa
                    System.out.println("La secuencia se ha completado1.");
                })
                .subscribe();


        @NonNull Maybe<StatusResponse> responseMerge = Maybe.zip(convertidoMaybe, responseProduct,
                        (valueCliente, valueProducto) -> metodoGrabar(valueCliente, valueProducto, dto))
                .flatMap(result -> result);
        return responseMerge;
    }

    private Maybe<StatusResponse> metodoGrabar(ResponseEntity<com
            .lizana.clienteproducto.model.externoclient.StatusResponse> valueCliente, com.lizana.clienteproducto.model
            .externoproduct.StatusResponse valueProduct,PerfilUser dto) {


        SaldoDto saldoDto = new SaldoDto();
        saldoDto.setProducto(valueProduct.getDetail().getIdDeProducto());
        saldoDto.setCliente(valueCliente.getBody().getDetail().getId());
        saldoDto.setFirmante(dto.getFirmante());
        saldoDto.setTitular(dto.getTitular());
        saldoDto.setSaldo(0);

        Maybe<com.lizana.clienteproducto.model.externosaldo.StatusResponse> responseSaldo = ServiceSaldo.serviceProductWc(saldoDto);



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
