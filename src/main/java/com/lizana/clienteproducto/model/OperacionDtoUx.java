package com.lizana.clienteproducto.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperacionDtoUx {

    private String id;
    private String IdSaldo;
    private String tipoDeOperacion;
    private Long monto;


}
