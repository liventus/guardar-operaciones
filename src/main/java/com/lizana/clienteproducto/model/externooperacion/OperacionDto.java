package com.lizana.clienteproducto.model.externooperacion;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperacionDto {

    private String id;
    private String idSaldo;
    private String tipoDeOperacion;
    private Long monto;


}
