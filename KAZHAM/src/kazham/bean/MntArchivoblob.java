package kazham.bean;

import java.io.Serializable;
import java.util.Map;
import java.util.List;
import java.util.Date;

import oracle.sql.BLOB;
import commons.mapper.ResourceManager;

public class MntArchivoblob implements Serializable {

    /**
    *
    **/
    private static final long serialVersionUID = 1L;
    private Integer idearchivoblob;
    private String nomarchivo;
    private String nomarchivo2;
    private String extension;
    private String bytes;
    private String descripcion;
    private byte[] arbytes;
    private BLOB archivoblob;
    		
    public MntArchivoblob() {}

    public MntArchivoblob(Map map) throws Exception {
      ResourceManager.populateDtoFromMap(this, map);
    }
	
	public Integer getIdearchivoblob() {
		return idearchivoblob;
	}
	
	public void setIdearchivoblob(Integer idearchivoblob) {
		this.idearchivoblob = idearchivoblob;
	}
	
	public String getNomarchivo() {
		return nomarchivo;
	}
	
	public void setNomarchivo(String nomarchivo) {
		this.nomarchivo = nomarchivo;
	}
	
	public String getNomarchivo2() {
		return nomarchivo2;
	}
	
	public void setNomarchivo2(String nomarchivo2) {
		this.nomarchivo2 = nomarchivo2;
	}
	
	public String getExtension() {
		return extension;
	}
	
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	public String getBytes() {
		return bytes;
	}
	
	public void setBytes(String bytes) {
		this.bytes = bytes;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public byte[] getArbytes() {
		return arbytes;
	}

	public void setArbytes(byte[] arbytes) {
		this.arbytes = arbytes;
	}

	public BLOB getArchivoblob() {
		return archivoblob;
	}

	public void setArchivoblob(BLOB archivoblob) {
		this.archivoblob = archivoblob;
	}

}

