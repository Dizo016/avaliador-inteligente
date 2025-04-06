package com.ucsal.avaliador_inteligente.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/teste")
public class TestController {

    @GetMapping
    public String testar() {
        return "API funcionando";
    }
}
