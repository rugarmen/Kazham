package kazham.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import commons.mapper.ResourceManager;

public class LstLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idelog;
	private Integer idtransac;
	private String trama;
	private String tiplog;
	private String usucreacion;
	private String feccreacion;
	private String usuario;
	private Integer indexRow;
	private String fecini;
	private String fecfin;
	private String tipo;
	private String dscerror;

	private List<LstLog> cursor;
	
	public LstLog() {
		super();
	}
	@SuppressWarnings("rawtypes")
	public LstLog(Map params) throws Exception {
		ResourceManager.populateDtoFromMap(this, params);
	}

		public Integer getIndexRow() {
			return indexRow;
		}

		public void setIndexRow(Integer indexRow) {
			this.indexRow = indexRow;
		}

	public Integer getIdelog() {
		return idelog;
	}

	public void setIdelog(Integer idelog) {
		this.idelog = idelog;
	}

	public Integer getIdtransac() {
		return idtransac;
	}

	public void setIdtransac(Integer idtransac) {
		this.idtransac = idtransac;
	}

	public String getTrama() {
		return trama;
	}

	public void setTrama(String trama) {
		this.trama = trama;
	}

	public String getTiplog() {
		return tiplog;
	}

	public void setTiplog(String tiplog) {
		this.tiplog = tiplog;
	}

	public String getUsucreacion() {
		return usucreacion;
	}

	public void setUsucreacion(String usucreacion) {
		this.usucreacion = usucreacion;
	}

	public String getFeccreacion() {
		return feccreacion;
	}

	public void setFeccreacion(String feccreacion) {
		this.feccreacion = feccreacion;
	}



	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<LstLog> getCursor() {
		return cursor;
	}

	public void setCursor(List<LstLog> cursor) {
		this.cursor = cursor;
	}

	public String getFecini() {
		return fecini;
	}

	public void setFecini(String fecini) {
		this.fecini = fecini;
	}

	public String getFecfin() {
		return fecfin;
	}

	public void setFecfin(String fecfin) {
		this.fecfin = fecfin;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDscerror() {
		return dscerror;
	}
	public void setDscerror(String dscerror) {
		this.dscerror = dscerror;
	}

}
