package kazham.inisesion.bean;

import java.io.Serializable;
import java.util.Map;
import java.util.List;
import java.util.Date;
import commons.mapper.ResourceManager;

public class BsqUsuario implements Serializable {

  private static final long serialVersionUID = 1L;
  private String codusuario;
  private String clave;
  private String msgerror;
  private Integer status;
  private Integer idelog;
  private String nombre;
  private String apepat;
  private String apemat;
  private String tipoUsu;
  //INI JASENCIOSP 10/05/2013
  private String idpdominio;
  private String descdominio;
  //FIN JASENCIOSP 10/05/2013
  
  private String idpTipoUsuario;
  private String upn;//M.23524.01

  /* RCR GC 46163 INI*/
  private String stsusuario;  
  private String claveactual;
  private String nuevaclave;
  private String confirmaclave;
  private String usucreacion;
  
  /* RCR GC 46163 FIN */
  
  public BsqUsuario() {}

  public BsqUsuario(Map map) throws Exception {
    ResourceManager.populateDtoFromMap(this, map);
  }

public String getMsgerror() {
	return msgerror;
}

public void setMsgerror(String msgerror) {
	this.msgerror = msgerror;
}

public Integer getStatus() {
	return status;
}

public void setStatus(Integer status) {
	this.status = status;
}

public String getCodusuario() {
	return codusuario;
}

public void setCodusuario(String codusuario) {
	this.codusuario = codusuario;
}

public String getClave() {
	return clave;
}

public void setClave(String clave) {
	this.clave = clave;
}

public Integer getIdelog() {
	return idelog;
}

public void setIdelog(Integer idelog) {
	this.idelog = idelog;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getApepat() {
	return apepat;
}

public void setApepat(String apepat) {
	this.apepat = apepat;
}

public String getApemat() {
	return apemat;
}

public void setApemat(String apemat) {
	this.apemat = apemat;
}

public String getTipoUsu() {
	return tipoUsu;
}

public void setTipoUsu(String tipoUsu) {
	this.tipoUsu = tipoUsu;
}
//INI JASENCIOSP 10/05/2013
public String getIdpdominio() {
	return idpdominio;
}

public void setIdpdominio(String idpdominio) {
	this.idpdominio = idpdominio;
}
//FIN JASENCIOSP 10/05/2013

public String getDescdominio() {
	return descdominio;
}

public void setDescdominio(String descdominio) {
	this.descdominio = descdominio;
}

public String getIdpTipoUsuario() {
	return idpTipoUsuario;
}

public void setIdpTipoUsuario(String idpTipoUsuario) {
	this.idpTipoUsuario = idpTipoUsuario;
}

public String getUpn() {
	return upn;
}

public void setUpn(String upn) {
	this.upn = upn;
}

/* RCR GC 46163 INI*/

public String getStsusuario() {
	return stsusuario;
}

public void setStsusuario(String stsusuario) {
	this.stsusuario = stsusuario;
}


public String getClaveactual() {
	return claveactual;
}

public void setClaveactual(String claveactual) {
	this.claveactual = claveactual;
}

public String getNuevaclave() {
	return nuevaclave;
}

public void setNuevaclave(String nuevaclave) {
	this.nuevaclave = nuevaclave;
}

public String getConfirmaclave() {
	return confirmaclave;
}

public void setConfirmaclave(String confirmaclave) {
	this.confirmaclave = confirmaclave;
}

public String getUsucreacion() {
	return usucreacion;
}

public void setUsucreacion(String usucreacion) {
	this.usucreacion = usucreacion;
}
}

/* RCR GC 46163 FIN */
