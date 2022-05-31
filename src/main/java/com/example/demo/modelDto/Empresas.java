package com.example.demo.modelDto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;
import javax.validation.constraints.NotNull;
import java.util.List;

//agarra etiqueta empresas

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empresas {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Empresa")
    @NotNull(message = "La lista de emp no puede estar vacia es obligatorio.")
    private List<Empresa> empresas;
}
