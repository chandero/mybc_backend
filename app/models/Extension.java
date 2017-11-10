package models;

import java.util.List;

import dtos.ExtensionDto;

public class Extension {

	public Long exte_id;
	
	public Protocolo protocolo;
	
	public String exte_numero;
	
	public Boolean exte_esagente;
	
	public Boolean exte_directa;
	
	public Boolean exte_listar;
	
	public Boolean exte_privada;
	
	public String exte_alias;
	
	public String exte_claveweb;
	
	public String exte_clave;
	
	public String exte_descripcion;
	
	public String exte_contexto;
	
	public Boolean exte_estado;

	public List<Reglas_extension> reglas_extensions;
	
	public static ExtensionDto createDto(Extension e){
		ExtensionDto dto = new ExtensionDto();
		
		dto.exte_id = e.exte_id;
		dto.protocolo = Protocolo.createDto(e.protocolo);
		dto.exte_numero = e.exte_numero;
		dto.exte_descripcion = e.exte_descripcion;
		dto.exte_clave = e.exte_clave;
		dto.exte_claveweb = e.exte_claveweb;
		dto.exte_estado = e.exte_estado;
		
		return dto;
		
	}

}
