package com.example.demo.controller;

import com.example.demo.modelDto.Empresas;
import com.example.demo.service.impl.EmpresaServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

//al indicarle que toma unicamente tipo application_xml_value patea todos los que no sean xml
@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaServiceImpl empresaService;
    @Operation(summary = "Guarda una lista XML")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "ok, Lectura de xml , y archivo procesado exitosamente"),
            @ApiResponse(responseCode = "400",description = "Archivo Invalido/No Encontrado"),
            @ApiResponse(responseCode = "400",description = "Una Variable no puede estar vacia")
    })
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> procesarXml(@Valid @RequestBody Empresas empresas){
        if (empresas == null){ return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Archivo Invalido/No Encontrado");}
         try {
            empresaService.create(empresas);
            return new ResponseEntity("ok, Lectura de xml , y archivo procesado exitosamente",HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}