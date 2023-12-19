package com.lizana.clienteproducto.controller;




import com.lizana.clienteproducto.model.OperacionDtoUx;
import com.lizana.clienteproducto.model.StatusResponse;
import com.lizana.clienteproducto.service.GuardarOperacionesService;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(GuardarOperacionesController.GUARDAROPERACIONES)
public class GuardarOperacionesController {
    public static final String GUARDAROPERACIONES = "/guardaroperaciones";

    @Autowired
    GuardarOperacionesService clienteProductoService;


    @PostMapping("/wc")
    public Maybe<StatusResponse> createwc(@RequestBody OperacionDtoUx product) {
        return clienteProductoService.saveOperacion(product);
    }







}
