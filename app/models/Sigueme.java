package models;

import dtos.SiguemeDto;

public class Sigueme {
	

	public Long sigu_id;

	public Extension extension;

	public Moh moh;
	
	public Boolean sigu_confllamada;
	
	public Integer sigu_tiempotimbre;
	
	public String sigu_lista;
	
	public String sigu_nombreprefijo;
	
	public Boolean sigu_activo;
	
	public String sigu_destino;
	
	public Tipo_destino tipo_destino;
	
	public Boolean sigu_anunciollamante;
	
	public String sigu_extlist;


	public static SiguemeDto createDto(Sigueme s){
		SiguemeDto dto = new SiguemeDto();
		
		dto.extension = Extension.createDto(s.extension);
		dto.moh = Moh.createDto(s.moh);
		dto.sigu_activo = s.sigu_activo;
		dto.sigu_anunciollamante = s.sigu_anunciollamante;
		dto.sigu_confllamada = s.sigu_confllamada;
		dto.sigu_destino = s.sigu_destino;
		dto.sigu_extlist = s.sigu_extlist;
		dto.sigu_lista = s.sigu_lista;
		dto.sigu_nombreprefijo = s.sigu_nombreprefijo;
		dto.sigu_id = s.sigu_id;
		dto.sigu_tiempotimbre = s.sigu_tiempotimbre;
		dto.tipo_destino = Tipo_destino.createDto(s.tipo_destino);
		
		return dto;
	}
	
}
