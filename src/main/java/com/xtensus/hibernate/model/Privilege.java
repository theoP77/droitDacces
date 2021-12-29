package com.xtensus.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = " privilege")
public class Privilege extends Entite implements java.io.Serializable {

	private static final long serialVersionUID = -7104201628204818950L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "privilegeId", unique = true, nullable = false) 
	private Integer privilegeId;
		 
	@Column(name = "codeInterface", length = 16277215)
	private String codeInterface;
	@Column(name = "actived", length = 254)
	private String actived;
	@Column(name = "butonCode1", length = 16277215)
	private String buton1;
	@Column(name = "butonCode2", length = 16277215)
	private String buton2;
	@Column(name = "butonCode3", length = 16277215)
	private String buton3;
	@Column(name = "butonCode4", length = 16277215)
	private String buton4;
		 
	public Privilege() {
	}

	public Privilege(String codeInterface, String actived, String buton1, String buton2, String buton3,
			String buton4) {
		super();
		this.setCodeInterface(codeInterface);
		this.setActived(actived);
		this.setButon1(buton1);
		this.setButon2(buton2);
		this.setButon3(buton3);
		this.setButon4(buton4);	
	}

	public Integer getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}

	public String getCodeInterface() {
		return codeInterface;
	}

	public void setCodeInterface(String codeInterface) {
		this.codeInterface = codeInterface;
	}

	public String getActived() {
		return actived;
	}

	public void setActived(String actived) {
		this.actived = actived;
	}

	public String getButon1() {
		return buton1;
	}

	public void setButon1(String buton1) {
		this.buton1 = buton1;
	}

	public String getButon2() {
		return buton2;
	}

	public void setButon2(String buton2) {
		this.buton2 = buton2;
	}

	public String getButon3() {
		return buton3;
	}

	public void setButon3(String buton3) {
		this.buton3 = buton3;
	}

	public String getButon4() {
		return buton4;
	}

	public void setButon4(String buton4) {
		this.buton4 = buton4;
	}

	@Override
	public String toString() {
		return "Privilege [privilegeId=" + privilegeId + ", codeInterface=" + codeInterface + ", actived=" + actived
				+ ", buton1=" + buton1 + ", buton2=" + buton2 + ", buton3=" + buton3 + ", buton4=" + buton4 + "]";
	}

}

