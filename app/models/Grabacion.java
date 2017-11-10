package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Model;

import play.data.validation.Constraints;

@Entity
public class Grabacion extends Model {

	@Id
	public Long grab_id;
	
	@Constraints.Required
	public String grab_nombre;
	
	public String grab_descripcion;
	
    /**
     * Generic query helper for entity Computer with id Long
     */
    public static Find<Long,Grabacion> find = new Find<Long,Grabacion>(){};
	
}
