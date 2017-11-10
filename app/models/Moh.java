package models;

import dtos.MohDto;

public class Moh  {

	public Long moh_id;
	
	public String moh_nombre;
	
	public String moh_archivo;
	
	public static MohDto createDto(Moh moh) {
		MohDto dto = new MohDto();
		
		dto.moh_id = moh.moh_id;
		dto.moh_nombre = moh.moh_nombre;
		dto.moh_archivo = moh.moh_archivo;
		
		return dto;
	}		
	
}
