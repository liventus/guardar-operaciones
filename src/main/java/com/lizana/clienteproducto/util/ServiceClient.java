package com.lizana.clienteproducto.util;

import com.lizana.clienteproducto.model.PerfilUser;
import com.lizana.clienteproducto.model.StatusResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


public class ServiceClient {



    public static Mono<ResponseEntity<com.lizana.clienteproducto.model.externoclient.StatusResponse>> serviceClientWc(PerfilUser dto){

        String url = "http://localhost:8080/client";
        WebClient.Builder builder = WebClient.builder();

        HttpHeaders headers = new HttpHeaders();
        headers.add("tipoDeDocumento", dto.getTipoDeDocumento());
        headers.add("numeroDeDocumento", dto.getNumeroDeDocumento());

        return builder.build()
                .get()
                .uri(url)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
                .toEntity(com.lizana.clienteproducto.model.externoclient.StatusResponse.class)
                .onErrorResume(throwable -> {
                    // Manejar errores reactivamente
                    System.err.println("Error al realizar la solicitud HTTP: " + throwable.getMessage());
                    return Mono.empty();
                });

    }

    public static Mono<ResponseEntity<StatusResponse>> serviceClient(PerfilUser dto){

        String url = "http://localhost:8080/client";
        WebClient.Builder builder = WebClient.builder();

        HttpHeaders headers = new HttpHeaders();
        headers.add("tipoDeDocumento", dto.getTipoDeDocumento());
        headers.add("numeroDeDocumento", dto.getNumeroDeDocumento());

        return builder.build()
                .get()
                .uri(url)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
                .toEntity(StatusResponse.class);

    }



}
