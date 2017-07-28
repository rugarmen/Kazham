package kazham.bean;

import java.io.Serializable;
import java.util.Map;
import java.util.List;
import java.util.Date;
import commons.mapper.ResourceManager;

public class LstConstante implements Serializable {

  /**
  *
  **/
  private static final long serialVersionUID = 1L;
  private String ideconstante;
  private String dscconstante;
  private String idptipodato;
  private String valor;
  private String usuario;
  private String valorclob;
  private List cursor;
  
  public LstConstante() {}

  public LstConstante(Map map) throws Exception {
    ResourceManager.populateDtoFromMap(this, map);
  }

	public String getIdeconstante() {
		return ideconstante;
	}
	
	public void setIdeconstante(String ideconstante) {
		this.ideconstante = ideconstante;
	}
	
	public String getDscconstante() {
		return dscconstante;
	}
	
	public void setDscconstante(String dscconstante) {
		this.dscconstante = dscconstante;
	}
	
	public String getIdptipodato() {
		return idptipodato;
	}
	
	public void setIdptipodato(String idptipodato) {
		this.idptipodato = idptipodato;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getValorclob() {
		return valorclob;
	}

	public void setValorclob(String valorclob) {
		this.valorclob = valorclob;
	}

	public List getCursor() {
	  return cursor;
	}

	public void setCursor(List cursor) {
	  this.cursor = cursor;
	}

}

