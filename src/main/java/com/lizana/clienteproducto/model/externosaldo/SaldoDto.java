package com.lizana.clienteproducto.model.externosaldo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaldoDto {


    private String id;
    private String producto;
    private String cliente;
    private Number titular;
    private Number firmante;
    private Number saldo;



}
