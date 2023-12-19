package com.lizana.clienteproducto.model.externosaldo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusResponse {
    private Integer code;
    private String description;
    private SaldoDto detail;

}


