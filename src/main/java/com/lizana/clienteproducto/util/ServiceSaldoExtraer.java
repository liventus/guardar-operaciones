package com.lizana.clienteproducto.util;


import com.lizana.clienteproducto.model.externosaldo.StatusResponse;
import com.lizana.clienteproducto.model.externosaldo.SaldoDto;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;


public class ServiceSaldoExtraer {
    public interface ProductService {
        @GET("/saldo")
        @Headers("Content-Type: application/json")
        Single<StatusResponse> getProduct(@Header("productId") String productId);
    }

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    public static Maybe<StatusResponse> extraerSaldo(String id) {
        Retrofit retrofit = getRetrofitInstance();
        ProductService productService = retrofit.create(ProductService.class);

        return productService.getProduct(id)
                .toMaybe();
    }
}