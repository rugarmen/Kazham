package kazham.bean;
     
import java.io.Serializable;
import java.util.Map;
import java.util.List;
import java.util.Date;
import commons.mapper.ResourceManager;

public class ValCfgTippar implements Serializable {

    /**
    *
    **/
    private static final long serialVersionUID = 1L;
    private String codusuario;
    private String idetippar;
    private String result;

    public ValCfgTippar() {}

    public ValCfgTippar(Map map) throws Exception {
        ResourceManager.populateDtoFromMap(this, map);
    }
	
	public String getCodusuario() {
		return codusuario;
	}
	
	public void setCodusuario(String codusuario) {
		this.codusuario = codusuario;
	}
	
	public String getIdetippar() {
		return idetippar;
	}
	
	public void setIdetippar(String idetippar) {
		this.idetippar = idetippar;
	}
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	  
}

