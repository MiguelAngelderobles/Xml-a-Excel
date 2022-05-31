package com.example.demo.modelDto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "Movimiento")
public class Movimiento {
    @JacksonXmlProperty(localName = "SaldoCtaCte")
    @NotEmpty(message = "La saldoCtaCte es obligatorio.")
    private Double saldoCtaCte;
    @JacksonXmlProperty(localName = "Concepto")
    @NotEmpty(message = "La concepto es obligatorio.")
    private String concepto;
    @JacksonXmlProperty(localName = "Importe")
    @NotEmpty(message = "El importe es obligatorio.")
    private Double importe;
    @Override
    public String toString() {
        return "MovimientoDto{" +
                ", saldoCtaCte=" + saldoCtaCte +
                ", concepto='" + concepto + '\'' +
                ", importe=" + importe +
                '}';
    }
}
