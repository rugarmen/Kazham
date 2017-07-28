package kazham.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import commons.mapper.ResourceManager;

public class MntMailLogCursor  implements Serializable{
	  public MntMailLogCursor() {}

	  public MntMailLogCursor(Map map) throws Exception {
	    ResourceManager.populateDtoFromMap(this, map);
	    
	  
	    
	  }
	  private int idelog;
	    private int idemail;
	    private String frommail;
	    private String tomail;
	    private String token;
	    private String acuserecibo;
	    private List cursor;
	    private String asuntomail;
		public String getAsuntomail() {
			return asuntomail;
		}

		public void setAsuntomail(String asuntomail) {
			this.asuntomail = asuntomail;
		}

		public List getCursor() {
			return cursor;
		}

		public void setCursor(List cursor) {
			this.cursor = cursor;
		}

		public int getIdelog() {
			return idelog;
		}

		public String getAcuserecibo() {
			return acuserecibo;
		}

		public void setAcuserecibo(String acuserecibo) {
			this.acuserecibo = acuserecibo;
		}

		public void setIdelog(int idelog) {
			this.idelog = idelog;
		}

		public int getIdemail() {
			return idemail;
		}

		public void setIdemail(int idemail) {
			this.idemail = idemail;
		}

		public String getFrommail() {
			return frommail;
		}

		public void setFrommail(String frommail) {
			this.frommail = frommail;
		}

		public String getTomail() {
			return tomail;
		}

		public void setTomail(String tomail) {
			this.tomail = tomail;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}
}
