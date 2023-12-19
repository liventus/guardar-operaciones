package com.lizana.clienteproducto.util;

import com.lizana.clienteproducto.model.externosaldo.StatusResponse;
import com.lizana.clienteproducto.model.externosaldo.SaldoDto;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Maybe;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.*;


public class ServiceSaldoGuardar {
    public interface ProductService {
        @POST("/saldo")
        @Headers("Content-Type: application/json")
        Maybe<StatusResponse> postProduct(@Body SaldoDto saldoDto);

    }

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    public static Maybe<StatusResponse> serviceProductWc(SaldoDto dto) {
        Retrofit retrofit = getRetrofitInstance();
        ProductService productService = retrofit.create(ProductService.class);

        return productService.postProduct(dto);
    }
}