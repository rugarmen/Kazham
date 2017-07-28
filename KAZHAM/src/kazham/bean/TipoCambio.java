
/**
 * Funcion: clase bean para obtener el tipo de cambio 
 * Creación:  - Sergio Castañeda
 * Modificaciones:
 * SCASTANEDM		1.0			05/01/2015			Version Inicial del Componente 
 * 													
 */

package kazham.bean;

import java.util.List;
import java.util.Map;

import commons.mapper.ResourceManager;

public class TipoCambio {
	
	private String monedaini;
	private String monedafin;
	private String tipotasa;
	private String fechacambio;
	private Double tipocambio;
	private Integer respuesta;


	public String getMonedaini() {
		return monedaini;
	}

	public void setMonedaini(String monedaini) {
		this.monedaini = monedaini;
	}

	public String getMonedafin() {
		return monedafin;
	}

	public void setMonedafin(String monedafin) {
		this.monedafin = monedafin;
	}

	public String getTipotasa() {
		return tipotasa;
	}

	public void setTipotasa(String tipotasa) {
		this.tipotasa = tipotasa;
	}

	public String getFechacambio() {
		return fechacambio;
	}

	public void setFechacambio(String fechacambio) {
		this.fechacambio = fechacambio;
	}

	public Double getTipocambio() {
		return tipocambio;
	}

	public void setTipocambio(Double tipocambio) {
		this.tipocambio = tipocambio;
	}

	public Integer getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(Integer respuesta) {
		this.respuesta = respuesta;
	}

	public TipoCambio() {}

	public TipoCambio(Map map) throws Exception {
		ResourceManager.populateDtoFromMap(this, map);
	}


}
