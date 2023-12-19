package com.lizana.clienteproducto.model;

import com.lizana.clienteproducto.model.externoproduct.ProductDto;
import com.lizana.clienteproducto.model.externosaldo.SaldoDto;
import com.lizana.clienteproducto.util.ServiceSaldoExtraer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataTransfer {

  SaldoDto saldoDtoResponse;
  ProductDto productDtoResponse;


}
