package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Model;

@Entity
public class Centro_Costo extends Model {
	
	@Id
	public Long ceco_id;
	
	public String ceco_nombre;
	
	public String ceco_codigo;
	
	public String ceco_extensiones;
	
    /**
     * Generic query helper for entity Centro_Costo with id Long
     */
    public static Find<Long,Centro_Costo> find = new Find<Long,Centro_Costo>(){};	

}
