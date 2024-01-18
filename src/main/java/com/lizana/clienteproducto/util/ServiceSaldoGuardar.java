package com.lizana.clienteproducto.util;

import com.lizana.clienteproducto.model.externosaldo.StatusResponse;
import com.lizana.clienteproducto.model.externosaldo.SaldoDto;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.*;


public class ServiceSaldoGuardar {
    public static Mono<StatusResponse> saveSaldo(SaldoDto dto){
        String url = "http://api-gateway:8080/saldo";
        WebClient.Builder builder = WebClient.builder();

        return builder.build()
            .post()
            .uri(url)
            .contentType(MediaType.APPLICATION_JSON)  // Especifica el tipo de contenido del cuerpo de la solicitud
            .body(BodyInserters.fromValue(dto))       // Convierte el objeto a un cuerpo de solicitud
            .retrieve()
            .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                clientResponse -> Mono.error(new RuntimeException("Error en la solicitud: " + clientResponse.statusCode())))
            .bodyToMono(com.lizana.clienteproducto.model.externosaldo.StatusResponse.class);

    }
}