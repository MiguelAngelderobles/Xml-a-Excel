package com.example.demo.controller;

import com.example.demo.modelDto.Empresas;
import com.example.demo.service.impl.EmpresaServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaServiceImpl empresaService;
    @Operation(summary = "Guarda una lista XML")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "ok, Lectura de xml , y archivo procesado exitosamente"),
            @ApiResponse(responseCode = "400",description = "Archivo Invalido/No Encontrado")
    })
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> procesarXml(@Valid @RequestBody Empresas empresas){
        try {
            empresaService.create(empresas);
            return new ResponseEntity("ok, Lectura de xml , y archivo procesado exitosamente",HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}