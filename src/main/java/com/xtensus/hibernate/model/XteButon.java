package com.xtensus.hibernate.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "xtebuton")
public class XteButon extends Entite implements java.io.Serializable {

	private static final long serialVersionUID = -7104201628204818950L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "xteButonRef", unique = true, nullable = false) 
	private Integer xteButonRef;
	
	@ManyToOne  
	@JoinColumn(name="xteInterfaceRef") 
	private XteInterface xteInterface;
	 
	@Column(name = "xteButonLibelle", length = 254)
	private String xteButonLibelle;
	@Column(name = "xteButonCode", length = 16277215)
	private String xteButonCode;
	@Column(name = "xteButonLibelle_AR", length = 254)
	private String xteButonLibelleAr;
	@Column(name = "xteButonCode_AR", length = 16277215)
	private String xteButonCodeAr;
		 
	public XteButon() {
	}

	public XteButon(XteInterface xteInterface, String xteButonLibelle, String xteButonCode, 
			String xteButonLibelleAr, String xteButonCodeAr) {
		this.setXteInterface(xteInterface);
		this.setXteButonLibelle(xteButonLibelle);      
		this.setXteButonCode(xteButonCode); 
		this.setXteButonLibelleAr(xteButonLibelleAr);   
		this.setXteButonCodeAr(xteButonCodeAr); 
		
	}
	
	public XteInterface getXteInterface() {
        return xteInterface;
    }
    public void setXteInterface(XteInterface xteInterface) {
        this.xteInterface = xteInterface;
    }

	public Integer getXteButonRef() {
		return xteButonRef;
	}
	public void setXteButonRef(Integer xteButonRef) {
		this.xteButonRef = xteButonRef;
	}

	public String getXteButonLibelle() {
		return xteButonLibelle;
	}
	public void setXteButonLibelle(String xteButonLibelle) {
		this.xteButonLibelle = xteButonLibelle;
	}

	public String getXteButonCode() {
		return xteButonCode;
	}
	public void setXteButonCode(String xteButonCode) {
		this.xteButonCode = xteButonCode;
	}

	public String getXteButonLibelleAr() {
		return xteButonLibelleAr;
	}
	public void setXteButonLibelleAr(String xteButonLibelleAr) {
		this.xteButonLibelleAr = xteButonLibelleAr;
	}

	public String getXteButonCodeAr() {
		return xteButonCodeAr;
	}
	public void setXteButonCodeAr(String xteButonCodeAr) {
		this.xteButonCodeAr = xteButonCodeAr;
	}    

	@Override
	public String toString() {
		return "XteButon [xteButonRef=" + xteButonRef + ", xteInterface=" + xteInterface + ", xteButonLibelle="
				+ xteButonLibelle + ", xteButonCode=" + xteButonCode + ", xteButonLibelleAr=" + xteButonLibelleAr
				+ ", xteButonCodeAr=" + xteButonCodeAr + "]";
	}
	 

}

