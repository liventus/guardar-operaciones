package com.lizana.clienteproducto.util;


import com.lizana.clienteproducto.model.DataTransfer;
import com.lizana.clienteproducto.model.externoproduct.StatusResponse;


import com.lizana.clienteproducto.model.externosaldo.SaldoDto;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


public class ServiceProductoExtraer {
  public static Mono<DataTransfer> getProduct(SaldoDto saldoDto) {

    String url = "http://api-gateway:8080/product";
    WebClient.Builder builder = WebClient.builder();

    HttpHeaders headers = new HttpHeaders();
    headers.add("productId", saldoDto.getProducto());


    return builder.build()
        .get()
        .uri(url)
        .headers(httpHeaders -> httpHeaders.addAll(headers))
        .retrieve().bodyToMono(com.lizana.clienteproducto.model.externoproduct.StatusResponse.class)
        .map(x -> {
              DataTransfer dataTransfer = new DataTransfer();
              dataTransfer.setProductDtoResponse(x.getDetail());
              dataTransfer.setSaldoDtoResponse(saldoDto);
              return dataTransfer;
            }


        );

  }
}