package com.xtensus.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Table(name = "xteinterface")
public class XteInterface extends Entite implements java.io.Serializable {

	private static final long serialVersionUID = -7104201628204818950L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "xteInterfaceRef ", unique = true, nullable = false)
	private Integer xteInterfaceRef;
	@Column(name = "xteInterfaceLibelle", length = 254)
	private String xteInterfaceLibelle;
	@Column(name = "xteInterfaceCode", length = 16277215)
	private String xteInterfaceCode;
	@Column(name = "xteInterfaceXhtml", length = 254)
	private String xteInterfaceXhtml;
	@Column(name = "xteInterfaceLibelle_AR", length = 254)
	private String xteInterfaceLibelleAr;
	@Column(name = "xteInterfaceCode_AR", length = 16277215)
	private String xteInterfaceCodeAr;
	
	public XteInterface() {
	}
	
	public XteInterface(String xteInterfaceLibelle) {
		this.setXteInterfaceLibelle(xteInterfaceLibelle);     
	}

	public XteInterface(String xteInterfaceLibelle, String xteInterfaceCode, 
			String xteInterfaceXhtml, String xteInterfaceLibelleAr,
			String xteInterfaceCodeAr) {
		this.setXteInterfaceLibelle(xteInterfaceLibelle);  
		this.setXteInterfaceCode(xteInterfaceCode);   
		this.setXteInterfaceXhtml(xteInterfaceXhtml); 
		this.setXteInterfaceLibelleAr(xteInterfaceLibelleAr);   
		this.setXteInterfaceCodeAr(xteInterfaceCodeAr);   
	}

	public Integer getXteInterfaceRef() {
		return xteInterfaceRef;
	}
	public void setXteInterfaceRef(Integer xteInterfaceRef) {
		this.xteInterfaceRef = xteInterfaceRef;
	}

	public String getXteInterfaceLibelle() {
		return xteInterfaceLibelle;
	}
	public void setXteInterfaceLibelle(String xteInterfaceLibelle) {
		this.xteInterfaceLibelle = xteInterfaceLibelle;
	}

	public String getXteInterfaceCode() {
		return xteInterfaceCode;
	}
	public void setXteInterfaceCode(String xteInterfaceCode) {
		this.xteInterfaceCode = xteInterfaceCode;
	}

	public String getXteInterfaceXhtml() {
		return xteInterfaceXhtml;
	}
	public void setXteInterfaceXhtml(String xteInterfaceXhtml) {
		this.xteInterfaceXhtml = xteInterfaceXhtml;
	}

	public String getXteInterfaceLibelleAr() {
		return xteInterfaceLibelleAr;
	}
	public void setXteInterfaceLibelleAr(String xteInterfaceLibelleAr) {
		this.xteInterfaceLibelleAr = xteInterfaceLibelleAr;
	}

	public String getXteInterfaceCodeAr() {
		return xteInterfaceCodeAr;
	}
	public void setXteInterfaceCodeAr(String xteInterfaceCodeAr) {
		this.xteInterfaceCodeAr = xteInterfaceCodeAr;
	}
	
	@Override
	public String toString() {
		return "XteInterface [xteInterfaceRef=" + xteInterfaceRef + ", xteInterfaceLibelle=" + xteInterfaceLibelle
				+ ", xteInterfaceCode=" + xteInterfaceCode + ", xteInterfaceXhtml=" + xteInterfaceXhtml
				+ ", xteInterfaceLibelleAr=" + xteInterfaceLibelleAr + ", xteInterfaceCodeAr=" + xteInterfaceCodeAr
				+ "]";
	}

}
