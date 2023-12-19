package com.lizana.clienteproducto.util;

import com.lizana.clienteproducto.model.externooperacion.OperacionDto;
import com.lizana.clienteproducto.model.externooperacion.StatusResponse;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Maybe;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public class ServiceOperacionGuardar {
    public interface ProductService {
        @POST("/operations")
        @Headers("Content-Type: application/json")
        Maybe<StatusResponse> postProduct(@Body OperacionDto operacionDto);

    }

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    public static Maybe<StatusResponse> serviceProductWc(OperacionDto dto) {
        Retrofit retrofit = getRetrofitInstance();
        ProductService productService = retrofit.create(ProductService.class);

        return productService.postProduct(dto);
    }
}