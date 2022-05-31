package com.example.demo.service.impl;

import com.example.demo.modelDto.Empresas;
import com.example.demo.service.EmpresaService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

//crea un archivo xsl, yo lo pude abrir con libreoffice y guarda correctamente en la carpeta resources ya que no puedo guardarla en el disco por falta de permisos
@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Override
    public void create(Empresas empresas) throws Exception {
        this.manualValidate(empresas);
        Workbook workbook = new HSSFWorkbook();

            Sheet sheet = workbook.createSheet("Empresas");

            Sheet sheet2 = workbook.createSheet("Movimientos");

            Map<String, Object[]> datos = new TreeMap<String, Object[]>();

            Map<String, Object[]> datos2 = new TreeMap<String, Object[]>();

            datos.put("1", new Object[]{"NroContrato", "CUIT", "DENOMINACION","DOMICILIO","CODIGOPOSTAL","PRODUCTOR"});

            datos2.put("1", new Object[]{"NroContrato", "SaldoCtaCte", "Concepto","Importe"});

            int fila =  2;
            int fila2 = 2;
            //Validacion manual a fallo de @valid y binlding

            for(int i = 0; i < empresas.getEmpresas().size(); i++){
                datos.put(String.valueOf(fila),new Object[]{String.valueOf(empresas.getEmpresas().get(i).getNroContrato()), empresas.getEmpresas().get(i).getCuit(), empresas.getEmpresas().get(i).getDenominacion(), empresas.getEmpresas().get(i).getDomicilio(), empresas.getEmpresas().get(i).getCodigoPostal(), empresas.getEmpresas().get(i).getProductor()});
                for(int j = 0; j < empresas.getEmpresas().get(i).getMovimientos().getMovimiento().size(); j++){
                    datos2.put(String.valueOf(fila2),new Object[]{String.valueOf(empresas.getEmpresas().get(i).getNroContrato()),String.valueOf(empresas.getEmpresas().get(i).getMovimientos().getMovimiento().get(j).getSaldoCtaCte()), empresas.getEmpresas().get(i).getMovimientos().getMovimiento().get(j).getConcepto(),String.valueOf(empresas.getEmpresas().get(i).getMovimientos().getMovimiento().get(j).getImporte())});
                    fila2 = fila2 + 1;
                }
                    fila = fila+1;
            }

            Set keyset = datos.keySet();
            int numeroRenglon = 0;
            for (Object key : keyset) {
                Row row = sheet.createRow(numeroRenglon++);
                Object[] arregloObjetos = datos.get(key);
                int numeroCelda = 0;
                for (Object obj : arregloObjetos) {
                    Cell cell = row.createCell(numeroCelda++);
                    if (obj instanceof String) {
                        cell.setCellValue((String) obj);
                    } else if (obj instanceof Integer) {
                        cell.setCellValue((Integer) obj);
                    }
                }
            }

        Set keyset2 = datos2.keySet();
        int numeroRenglon2 = 0;
        for (Object key2 : keyset2) {
            Row row2 = sheet2.createRow(numeroRenglon2++);
            Object[] arregloObjetos2 = datos2.get(key2);
            int numeroCelda2 = 0;
            for (Object obj2 : arregloObjetos2) {
                Cell cell2 = row2.createCell(numeroCelda2++);
                if (obj2 instanceof String) {
                    cell2.setCellValue((String) obj2);
                } else if (obj2 instanceof Integer) {
                    cell2.setCellValue((Integer) obj2);
                }
            }
        }

        try{
            File file = new File("src/main/resources/excelFinal.xlsm");
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
        }catch (Exception e){
            e.getMessage();
        }

    }

    //validaciones manuales a falta de un bildings para errores
    public void manualValidate(Empresas empresas) throws Exception {
        int fila =  2;
        int fila2 = 2;
        if(empresas.getEmpresas() == null || empresas.getEmpresas().size() == 0){ throw new Exception("Archivo Invalido/No Encontrado"); }
        for(int i = 0; i < empresas.getEmpresas().size(); i++){
            if (String.valueOf(empresas.getEmpresas().get(i).getNroContrato()).isEmpty()){ throw new Exception("El numero de contrato no puede estar vacia");}
            if (String.valueOf(empresas.getEmpresas().get(i).getCuit()).isEmpty()){ throw new Exception("El cuit no puede estar vacio");}
            if (String.valueOf(empresas.getEmpresas().get(i).getDenominacion()).isEmpty() ){ throw new Exception("La denominacion no puede estar vacia");}
            if (String.valueOf(empresas.getEmpresas().get(i).getCodigoPostal()).isEmpty()){ throw new Exception("El codigo postal no puede estar vacio");}
            if (String.valueOf(empresas.getEmpresas().get(i).getDomicilio()).isEmpty() ){ throw new Exception("El domicilio no puede estar vacia");}
            if (String.valueOf(empresas.getEmpresas().get(i).getMovimientos()).isEmpty()){ throw new Exception("La Lista de Movimientos no puede estar vacia");}

            for(int j = 0; j < empresas.getEmpresas().get(i).getMovimientos().getMovimiento().size(); j++){
                if (empresas.getEmpresas().get(i).getMovimientos().getMovimiento().get(j).getSaldoCtaCte() == null){ throw new Exception("El saldo de la cuenta no puede estar vacia");}
                if (empresas.getEmpresas().get(i).getMovimientos().getMovimiento().get(j).getImporte() == null){ throw new Exception("El importe no puede estar vacio");}
                if (empresas.getEmpresas().get(i).getMovimientos().getMovimiento().get(j).getConcepto()== null){ throw new Exception("El concepto no puede estar vacia");}
                fila2 = fila2 + 1;
            }
            fila = fila+1;
        }
    }


}

