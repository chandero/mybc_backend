package models;

import java.util.List;

public class Conferencia_programa {

    public Integer copr_id;
    public Integer exco_id;
    public String  copr_fecha;
    public Integer copr_duracion;
    public Boolean copr_activa;
    public String  copr_descripcion;
    public List<Conferencia_invitado> invitados;

}