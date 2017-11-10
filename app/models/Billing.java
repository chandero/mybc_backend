package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Model;

import play.data.format.Formats;

@Entity
public class Billing extends Model {

	@Id
	public Long bill_id;
	
	public String bill_nombre;
	
	public String bill_descripcion;
	
	public Boolean bill_estado;
	
	@Formats.DateTime(pattern="yyyy/MM/dd")
	public Date bill_ciclodesde;
	
	@Formats.DateTime(pattern="yyyy/MM/dd")
	public Date bill_ciclohasta;
	
	@Formats.DateTime(pattern="yyyy/MM/dd")
	public Date bill_validezdesde;
	
	@Formats.DateTime(pattern="yyyy/MM/dd")
	public Date bill_validezhasta;
	
	public Integer bill_diacorte;
	
	public String  bill_horainicial;
	
	public String bill_horafinal;
	
    /**
     * Generic query helper for entity Billing with id Long
     */
    public static Find<Long,Billing> find = new Find<Long,Billing>(){};			
	
}
