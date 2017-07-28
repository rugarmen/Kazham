package kazham.bean;

import java.util.List;
import java.util.Map;

import commons.mapper.ResourceManager;

public class Usuario {

	private String ideusuario;
	private String name;
	private String email;
	private String password;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List getCursor() {
		return cursor;
	}

	public void setCursor(List cursor) {
		this.cursor = cursor;
	}

	
}
