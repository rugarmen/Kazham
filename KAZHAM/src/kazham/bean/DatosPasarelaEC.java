package kazham.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

import commons.mapper.ResourceManager;

public class DatosPasarelaEC implements Serializable{
	 private static final long serialVersionUID = 1L;
	  private Integer producto;
	  private Integer cotizacion;
	  private BigDecimal monto;
	  private String moneda;
	  private String viapago;

	  public DatosPasarelaEC() {}

	  @SuppressWarnings("rawtypes")
	public DatosPasarelaEC(Map map) throws Exception {
	    ResourceManager.populateDtoFromMap(this, map);
	  }

	public Integer getProducto() {
		return producto;
	}

	public void setProducto(Integer producto) {
		this.producto = producto;
	}

	public Integer getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(Integer cotizacion) {
		this.cotizacion = cotizacion;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getViapago() {
		return viapago;
	}

	public void setViapago(String viapago) {
		this.viapago = viapago;
	}
	  
	  
	  
}
