package kazham.bean;

import java.io.Serializable;
import java.util.List;

public class ListaCorreoGenerico implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idetercero;
	private String correo;
	private String password;
	private String host;
	private String bodyacuse;
	private String asuntoacuse;
	private List cursor;
	public int getIdetercero() {
		return idetercero;
	}
	public void setIdetercero(int idetercero) {
		this.idetercero = idetercero;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public List getCursor() {
		return cursor;
	}
	public void setCursor(List cursor) {
		this.cursor = cursor;
	}
	public String getBodyacuse() {
		return bodyacuse;
	}
	public void setBodyacuse(String bodyacuse) {
		this.bodyacuse = bodyacuse;
	}
	public String getAsuntoacuse() {
		return asuntoacuse;
	}
	public void setAsuntoacuse(String asuntoacuse) {
		this.asuntoacuse = asuntoacuse;
	}
	
}
