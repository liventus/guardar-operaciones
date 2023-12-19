package com.lizana.clienteproducto.model.externooperacion;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusResponse {
    private Integer code;
    private String description;
    private OperacionDto detail;

}


