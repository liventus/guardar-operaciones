package com.lizana.clienteproducto.util;


import com.lizana.clienteproducto.model.externosaldo.StatusResponse;
import com.lizana.clienteproducto.service.impl.GuardarOperacionesServiceImpl;


import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;



public class ServiceSaldoExtraer {

    public static Mono<StatusResponse> getSaldo(String id){

        String url = "http://api-gateway:8080/saldo";
        WebClient.Builder builder = WebClient.builder();

        HttpHeaders headers = new HttpHeaders();
        headers.add("productId", id);


        return builder.build()
            .get()
            .uri(url)
            .headers(httpHeaders -> httpHeaders.addAll(headers))
            .retrieve().bodyToMono(com.lizana.clienteproducto.model.externosaldo.StatusResponse.class);



    }
}