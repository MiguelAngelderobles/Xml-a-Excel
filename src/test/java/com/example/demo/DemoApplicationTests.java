package com.example.demo;

import com.example.demo.controller.EmpresaController;
import com.example.demo.modelDto.Empresa;
import com.example.demo.modelDto.Empresas;
import com.example.demo.modelDto.Movimiento;
import com.example.demo.modelDto.Movimientos;
import com.example.demo.service.EmpresaService;
import com.example.demo.service.impl.EmpresaServiceImpl;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class DemoApplicationTests  extends TestCase {

	@Mock
	EmpresaServiceImpl empresaService;
	@InjectMocks
	EmpresaController empresaController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test void testCreateExcelOk() throws Exception {
		Empresas empresas = new Empresas();
		Movimiento movimiento = new Movimiento(123123.0,"concepto",12312.0);
		List<Movimiento> movimientoList = new ArrayList<>();
		movimientoList.add(movimiento);
		Movimientos movimientos = new Movimientos(movimientoList);
		Empresa empresa = new Empresa(1L,"10","denominacion","domicilio",121,"productor",movimientos);
		List<Empresa> empresaList = new ArrayList<>();
		empresaList.add(empresa);
		empresas.setEmpresas(empresaList);
		System.out.println("empresas" + empresas);

		ResponseEntity ok2 = new ResponseEntity("ok",HttpStatus.OK);
		assertEquals(empresaController.procesarXml(empresas),ok2);

	}

	@Test void FaltanteDato1() throws Exception {
		Empresas empresas1 = new Empresas();
		Movimiento movimiento1 = new Movimiento(123123.0,"null",12312.0);
		List<Movimiento> movimientoList1 = new ArrayList<>();
		movimientoList1.add(movimiento1);
		Movimientos movimientos1 = new Movimientos(movimientoList1);
		Empresa empresa1 = new Empresa(1L,"10","denominacion","domicilio",121,null,movimientos1);
		List<Empresa> empresaList1 = new ArrayList<>();
		empresaList1.add(empresa1);
		empresas1.setEmpresas(empresaList1);

		System.out.println("empresas" + empresaController.procesarXml(null));

		ResponseEntity ok2 = new ResponseEntity("ok",HttpStatus.BAD_REQUEST);
		assertEquals(empresaController.procesarXml(null),ok2);

	}


}
