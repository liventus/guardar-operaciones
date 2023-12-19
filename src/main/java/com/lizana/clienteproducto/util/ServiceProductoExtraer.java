package com.lizana.clienteproducto.util;

import com.lizana.clienteproducto.model.DataTransfer;
import com.lizana.clienteproducto.model.externoproduct.ProductDto;
import com.lizana.clienteproducto.model.externoproduct.StatusResponse;
import com.lizana.clienteproducto.model.externosaldo.SaldoDto;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;



public class ServiceProductoExtraer {
    public interface ProductService {
        @GET("/product")
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

    public static Maybe<DataTransfer> serviceProductExtraerWc(SaldoDto saldoDto) {
        Retrofit retrofit = getRetrofitInstance();
        ProductService productService = retrofit.create(ProductService.class);
        System.out.println("serviceProductExtraerWc");
        return productService.getProduct(saldoDto.getProducto())
            .map(x->{
                DataTransfer dataTransfer = new DataTransfer();
                dataTransfer.setProductDtoResponse(x.getDetail());
                dataTransfer.setSaldoDtoResponse(saldoDto);
                return dataTransfer;
            }).toMaybe();
    }
}