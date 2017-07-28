package kazham.bean;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import commons.mapper.ResourceManager;

public class ListaLogAuditoria implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String ideauditoria;
	private String iderowid;
	private String ususisoperativo;
	private String nombremaquina;
	private String usubasedatos;	
	private String tipo;
	private String fecha;
	private String tabla;
	private String datonuevo;
	private String datoantiguo;	
	private String usuaplicacion;	
	

	private String tipodato;
	private String idearchivoblobact;
	private String idearchivoblobant;
	private String nombretabla;

	private String fechaini;
	private String fechafin; 
	
	public String getIdeauditoria() {
		return ideauditoria;
	}

	public void setIdeauditoria(String ideauditoria) {
		this.ideauditoria = ideauditoria;
	}

	public String getIderowid() {
		return iderowid;
	}

	public void setIderowid(String iderowid) {
		this.iderowid = iderowid;
	}

	public String getUsubasedatos() {
		return usubasedatos;
	}

	public void setUsubasedatos(String usubasedatos) {
		this.usubasedatos = usubasedatos;
	}

	

	public String getNombremaquina() {
		return nombremaquina;
	}

	public void setNombremaquina(String nombremaquina) {
		this.nombremaquina = nombremaquina;
	}

	public String getUsusisoperativo() {
		return ususisoperativo;
	}

	public void setUsusisoperativo(String ususisoperativo) {
		this.ususisoperativo = ususisoperativo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTabla() {
		return tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public String getDatonuevo() {
		return datonuevo;
	}

	public void setDatonuevo(String datonuevo) {
		this.datonuevo = datonuevo;
	}

	public String getDatoantiguo() {
		return datoantiguo;
	}

	public void setDatoantiguo(String datoantiguo) {
		this.datoantiguo = datoantiguo;
	}

	public String getUsuaplicacion() {
		return usuaplicacion;
	}

	public void setUsuaplicacion(String usuaplicacion) {
		this.usuaplicacion = usuaplicacion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getTipodato() {
		return tipodato;
	}

	public void setTipodato(String tipodato) {
		this.tipodato = tipodato;
	}

	public String getNombretabla() {
		return nombretabla;
	}

	public void setNombretabla(String nombretabla) {
		this.nombretabla = nombretabla;
	}

	public String getFechaini() {
		return fechaini;
	}

	public void setFechaini(String fechaini) {
		this.fechaini = fechaini;
	}

	public String getFechafin() {
		return fechafin;
	}

	public void setFechafin(String fechafin) {
		this.fechafin = fechafin;
	}

	@SuppressWarnings("rawtypes")
	private List cursor;
	
	
	public ListaLogAuditoria() {}

	public ListaLogAuditoria(Map map) throws Exception {
		ResourceManager.populateDtoFromMap(this, map);
	}

	public List getCursor() {
		return cursor;
	}

	public void setCursor(List cursor) {
		this.cursor = cursor;
	}

	public String getIdearchivoblobact() {
		return idearchivoblobact;
	}

	public void setIdearchivoblobact(String idearchivoblobact) {
		this.idearchivoblobact = idearchivoblobact;
	}

	public String getIdearchivoblobant() {
		return idearchivoblobant;
	}

	public void setIdearchivoblobant(String idearchivoblobant) {
		this.idearchivoblobant = idearchivoblobant;
	}
}
