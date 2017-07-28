package kazham.bean;

import java.util.List;
import java.util.Map;

import commons.mapper.ResourceManager;

public class Usuario {

	private String ideusuario;
	private int ideacuerdo;
	 private List cursor;
	  public Usuario() {}

	  public Usuario(Map map) throws Exception {
	    ResourceManager.populateDtoFromMap(this, map);
	  }

	public String getIdeusuario() {
		return ideusuario;
	}

	public void setIdeusuario(String ideusuario) {
		this.ideusuario = ideusuario;
	}

	public int getIdeacuerdo() {
		return ideacuerdo;
	}

	public void setIdeacuerdo(int ideacuerdo) {
		this.ideacuerdo = ideacuerdo;
	}
}
