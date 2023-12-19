package com.lizana.clienteproducto.model.externoproduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String id;
    private String idDeProducto;
    private Number comision;
    private String tipoDeCuenta;
    private String claseDeCuenta;
    private Number limitMaxDepositoMensual;
    private Number limitMaxRetiroMensual;
    private Number limitMaxPagoMensual;
    private Number limitMaxCredito;



}
