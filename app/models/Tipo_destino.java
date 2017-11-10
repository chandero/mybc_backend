package models;

import dtos.Tipo_destinoDto;

public class Tipo_destino {


	public Long tide_id;
	
	public String tide_descripcion;
	
	public String tide_clase;
	

	public static Tipo_destinoDto createDto(Tipo_destino td) {
		Tipo_destinoDto dto = new Tipo_destinoDto();
		
		dto.tide_id = td.tide_id;
		dto.tide_descripcion = td.tide_descripcion;
		dto.tide_clase = td.tide_clase;
		
		return dto;
	}		
	
}
