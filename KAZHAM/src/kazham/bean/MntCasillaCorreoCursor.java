package kazham.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import commons.mapper.ResourceManager;

public class MntCasillaCorreoCursor  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idetercero;
	private String correo;
	private String clave;
	private String nombre;
	private String stscasillacorreo;
	private String usucreacion;
	private Date feccreacion;
	private String usumodif;
	private Date fecmodif;
	private String indgenerico;
	private String indexrow;
		
	public MntCasillaCorreoCursor() {}

	public MntCasillaCorreoCursor(Map map) throws Exception {
	    ResourceManager.populateDtoFromMap(this, map);
	}
	
	public Integer getIdetercero() {
		return idetercero;
	}

	public void setIdetercero(Integer idetercero) {
		this.idetercero = idetercero;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getStscasillacorreo() {
		return stscasillacorreo;
	}

	public void setStscasillacorreo(String stscasillacorreo) {
		this.stscasillacorreo = stscasillacorreo;
	}

	public String getIndgenerico() {
		return indgenerico;
	}

	public void setIndgenerico(String indgenerico) {
		this.indgenerico = indgenerico;
	}

	public String getUsucreacion() {
		return usucreacion;
	}

	public void setUsucreacion(String usucreacion) {
		this.usucreacion = usucreacion;
	}

	public Date getFeccreacion() {
		return feccreacion;
	}

	public void setFeccreacion(Date feccreacion) {
		this.feccreacion = feccreacion;
	}

	public String getUsumodif() {
		return usumodif;
	}

	public void setUsumodif(String usumodif) {
		this.usumodif = usumodif;
	}

	public Date getFecmodif() {
		return fecmodif;
	}

	public void setFecmodif(Date fecmodif) {
		this.fecmodif = fecmodif;
	}
	
	public String getIndexrow() {
		return indexrow;
	}
	
	public void setIndexrow(String indexrow) {
	  this.indexrow = indexrow;
	}
	
}