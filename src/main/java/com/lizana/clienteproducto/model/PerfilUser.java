package com.lizana.clienteproducto.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilUser {

    private String id;
    private String tipoDeDocumento;
    private String numeroDeDocumento;
    private String idDeProducto;
    private Number titular;
    private Number firmante;


}
