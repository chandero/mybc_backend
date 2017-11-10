package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.avaje.ebean.Model;

import dtos.ProtocoloDto;
import play.data.validation.Constraints;

@Entity
public class Protocolo extends Model {
	
	@Id
	public Long prot_id;
	
	@Constraints.Required
	public String prot_nombre;
	
	//bi-directional many-to-one association to Extension
	@OneToMany(mappedBy="protocolo")
	@OrderBy("exteId")
	private List<Extension> extensions;

	public static ProtocoloDto createDto(Protocolo p){
		ProtocoloDto dto = new ProtocoloDto();
	    if (p != null) {
			dto.proto_id = p.prot_id;
			dto.prot_nombre = p.prot_nombre;
	    }
		return dto;
	}
}
