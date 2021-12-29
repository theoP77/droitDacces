package com.xtensus.beans.mbean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.xtensus.hibernate.model.Privilege;
import com.xtensus.jsf.common.LanguageManagerBean;
import com.xtensus.jsf.common.VariableGlobale;
import com.xtensus.spring.service.impl.ApplicationManager;

@Component
@Scope("request")
public class PrivilegeGestionBean {

	private Privilege privilege;
	private String divajouter;
	private String divmodifier;
	private String titre;
	private String infoChampObligatoir;
	private DataModel listPrivilege;
	private List<Privilege> listPrivil;
	private long records = 0;
	private boolean openaccor;
	private boolean openaccor1;
	private String hidebuttonValidateUser;
	private String hidebuttonDelUpUser;
	private String hideAccor;
	private String titleaccor;

	@Autowired
	private VariableGlobale vb;
	// fixe
	private ApplicationManager appMgr;
	@Autowired
	private LanguageManagerBean lm;
	@Autowired
	private MessageSource messageSource;
	private String message;
	private boolean status;
	private boolean status1;
	private boolean status2;
	private boolean status3,status4,status5;
	
	@Autowired
	public PrivilegeGestionBean(@Qualifier("applicationManager") ApplicationManager appMgr) {
			this.appMgr = appMgr;
			listPrivilege = new ListDataModel();
			listPrivil = new ArrayList<Privilege>();
			divajouter = "block";
			divmodifier = "none";
			privilege = new Privilege();
			infoChampObligatoir = "block";
			openaccor = false;
			openaccor1 = false;
			hidebuttonValidateUser = "block";
			hidebuttonDelUpUser = "none";
			hideAccor = "none";
			System.out.println("*************Bean PrivilegeGestionBean Injecte***************");
				
	}

	@PostConstruct
	public void Initialize() {

		System.out.println("###### dans init");
		
		titre = "Ajouter Privilège";
		if( vb.getPrivilege() != null) 
			privilege=vb.getPrivilege();
		titleaccor =messageSource.getMessage("Ajoutprivilège",new Object[] {}, lm.createLocal());
		listPrivil = new ArrayList<Privilege>();
		try {
			listPrivil = appMgr.getList(Privilege.class);
			Collections.reverse(listPrivil);
		} catch (Exception e) {
			System.out.println("erreur");
			e.printStackTrace();
		}
		listPrivilege.setWrappedData(listPrivil);
		System.out.println("##### dans initialize fin ");

	}
		
	public void save() {
				
		status = false;
		status3 = false;
		try {

			appMgr.insert(privilege);
			setMessage(messageSource.getMessage("confirmInsert",
					new Object[] {}, lm.createLocal()));
			status = true;
			// -méthode save()
			
			System.out.println("***************Succes PrivilegeGestionBean***************");

		} catch (Exception e) {
			e.printStackTrace();
			status3 = true;
			System.out.println("***************Erreur PrivilegeGestionBean***************");
		}
		hideAccor="none";

	}
	 
	
	/*
	 * // Get Selection Rows public void getSelectionRow() {
	 * 
	 * try {
	 * 
	 * xteButon = (XteButon) listXteButon.getRowData();
	 * System.out.println("######## xteButon ref " + xteButon.getXteButonRef());
	 * System.out.println("######## xteButon lib " + xteButon.getXteButonLibelle());
	 * vb.setXteButon(xteButon);
	 * 
	 * System.out.println("*******Selection XteButonGestionBean*********");
	 * 
	 * } catch (Exception e) { e.printStackTrace();
	 * System.out.println("*******ErreurDeSelection XteButonGestionBean*******"); }
	 * divajouter = "none"; divmodifier = "block"; infoChampObligatoir = "block";
	 * titre = "Modifier Bouton"; titleaccor
	 * =messageSource.getMessage("ModifierXteButon",new Object[] {},
	 * lm.createLocal()); openaccor = true; openaccor1 = true; hideAccor = "block";
	 * hidebuttonValidateUser = "none"; hidebuttonDelUpUser = "block";
	 * 
	 * }
	 * 
	 * public void updateSelectedRow() {
	 * 
	 * try {System.out.println("updateSelectedRow xteButon ref " +
	 * xteButon.getXteButonRef());
	 * System.out.println("updateSelectedRow xteButon lib " +
	 * xteButon.getXteButonLibelle()); status1 = false; status2 = false;
	 * 
	 * System.out.println("########&&&&& xteButon ref " +
	 * xteButon.getXteButonRef()); System.out.println("########&&&&& xteButon lib "
	 * + xteButon.getXteButonLibelle()); appMgr.update(xteButon); status1 = true;
	 * setMessage(messageSource.getMessage("confirmInsert", new Object[] {},
	 * lm.createLocal())); // -méthode updateSelectedRow()
	 * 
	 * } catch (Exception e) { e.printStackTrace(); status2 = true;
	 * setMessage(messageSource.getMessage("erreurUpdate", new Object[] {},
	 * lm.createLocal()));
	 * 
	 * } divajouter = "block"; divmodifier = "none"; infoChampObligatoir = "block";
	 * titre = "Ajouter Bouton"; titleaccor
	 * =messageSource.getMessage("AjoutxteButon",new Object[] {}, lm.createLocal());
	 * Initialize();
	 * 
	 * }
	 * 
	 * 
	 * // Get Selection Rows public void getSelectionRowSea() {
	 * 
	 * try { xteButon = (XteButon) listXteButon.getRowData();
	 * System.out.println("######## xteButon ref " + xteButon.getXteButonRef());
	 * System.out.println("######## xteButon lib " + xteButon.getXteButonLibelle());
	 * vb.setXteButon(xteButon);
	 * 
	 * System.out.println("*******Selection XteButonGestionBean*********");
	 * 
	 * } catch (Exception e) { e.printStackTrace();
	 * System.out.println("*******ErreurDeSelection XteButonGestionBean*******"); }
	 * divajouter = "none"; divmodifier = "none"; infoChampObligatoir = "none";
	 * titre = "Consulter parametre de Bouton"; titleaccor
	 * =messageSource.getMessage("ConsultationxteButon",new Object[] {},
	 * lm.createLocal()); openaccor = true; openaccor1 = true; hideAccor = "block";
	 * hidebuttonValidateUser = "none"; hidebuttonDelUpUser = "block";
	 * 
	 * }
	 * 
	 * public void searchSelectedRow() {
	 * 
	 * try {System.out.println("searchSelectedRow xteButon ref" +
	 * xteButon.getXteButonRef());
	 * System.out.println("searchSelectedRow xteButon lib" +
	 * xteButon.getXteButonLibelle()); status1 = false; status2 = false;
	 * 
	 * System.out.println("########&&&&& xteButon ref" + xteButon.getXteButonRef());
	 * System.out.println("########&&&&& xteButon lib" +
	 * xteButon.getXteButonLibelle()); appMgr.find(xteButon); status1 = true;
	 * setMessage(messageSource.getMessage("confirmInsert", new Object[] {},
	 * lm.createLocal())); // -méthode searchSelectedRow()
	 * 
	 * } catch (Exception e) { e.printStackTrace(); status2 = true;
	 * setMessage(messageSource.getMessage("erreurUpdate", new Object[] {},
	 * lm.createLocal()));
	 * 
	 * } divajouter = "block"; divmodifier = "none"; infoChampObligatoir = "none";
	 * titre = "Ajouter xteButon";
	 * titleaccor=messageSource.getMessage("AjoutxteButon",new Object[] {},
	 * lm.createLocal()); Initialize();
	 * 
	 * }
	 */
	
	public void getSelectionRowDel() {
		status4=false;
		System.out.println("###### dans getSelectionRowDel");
		try {
			privilege = (Privilege) listPrivilege.getRowData();
			vb.setPrivilege(privilege);
			status4=true;
			System.out
					.println("*******Selection PrivilegeGestionBean*********");

		} catch (Exception e) {
			e.printStackTrace();
			status5=true;
			System.out
					.println("*******ErreurDeSelection PrivilegeGestionBean*******");
		}
 	}
	
	public void deleteSelectedRow() {

		try {
			System.out.println("########## dans deleteSelectedRow == " + privilege.getPrivilegeId());
			privilege = vb.getPrivilege();
			appMgr.delete(privilege);
			System.out.println("DeleteTerminee");
			
		} catch (Exception e) {
			status5=true;
			e.printStackTrace();
		}
		Initialize();
	}

	public void annulModif() {
		privilege = new Privilege();
		divajouter = "block";
		divmodifier = "none";
		infoChampObligatoir = "block";
		titre = "Ajouter parametre de privilege";

	}
	
	public void viderchamp() {	
		
		System.out.println("##### dans viderchamp");
		status = false;
		status3 = false;
		status1 = false;
		status2 = false;
		privilege = new Privilege();
		titleaccor = messageSource.getMessage("Ajoutprivilege",new Object[] {}, lm.createLocal());;
		openaccor1 = true;
		openaccor = false;
		hidebuttonDelUpUser = "none";
		hidebuttonValidateUser = "block";
		hideAccor = "block";
		divajouter = "block";
		divmodifier = "none";
		infoChampObligatoir = "block";
		vb.setPrivilege(new Privilege());
	}
	
	public void reinitialiser() {
		try {
			System.out.println("###### dans reinitialiser");
			hideAccor = "none";
			status = false;
			status1 = false;
			status4 = false;
			status5 = false;
			Initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String valide() {
		return ("OK");
	}
	
	// **************************** Getters and Setters********************

	
	
	
	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	public String getDivajouter() {
		return divajouter;
	}

	public void setDivajouter(String divajouter) {
		this.divajouter = divajouter;
	}

	public String getDivmodifier() {
		return divmodifier;
	}

	public void setDivmodifier(String divmodifier) {
		this.divmodifier = divmodifier;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getInfoChampObligatoir() {
		return infoChampObligatoir;
	}

	public void setInfoChampObligatoir(String infoChampObligatoir) {
		this.infoChampObligatoir = infoChampObligatoir;
	}

	public DataModel getListPrivilege() {
		return listPrivilege;
	}

	public void setlistPrivilege(DataModel listPrivilege) {
		this.listPrivilege = listPrivilege;
	}

	public List<Privilege> getListPrivil() {
		return listPrivil;
	}

	public void setlistPrivil(List<Privilege> listPrivil) {
		this.listPrivil = listPrivil;
	}

	@SuppressWarnings("unchecked")
	public long getRecords() {
		if (listPrivilege == null && listPrivilege.getWrappedData() == null)
			records = 0;
		else
			records = ((List<Privilege>) listPrivilege.getWrappedData()).size();
		
		return records;
	}

	public void setRecords(long records) {
		this.records = records;
	}

	public boolean isOpenaccor() {
		return openaccor;
	}

	public void setOpenaccor(boolean openaccor) {
		this.openaccor = openaccor;
	}

	public boolean isOpenaccor1() {
		return openaccor1;
	}

	public void setOpenaccor1(boolean openaccor1) {
		this.openaccor1 = openaccor1;
	}

	public String getHidebuttonValidateUser() {
		return hidebuttonValidateUser;
	}

	public void setHidebuttonValidateUser(String hidebuttonValidateUser) {
		this.hidebuttonValidateUser = hidebuttonValidateUser;
	}

	public String getHidebuttonDelUpUser() {
		return hidebuttonDelUpUser;
	}

	public void setHidebuttonDelUpUser(String hidebuttonDelUpUser) {
		this.hidebuttonDelUpUser = hidebuttonDelUpUser;
	}

	public String getHideAccor() {
		return hideAccor;
	}

	public void setHideAccor(String hideAccor) {
		this.hideAccor = hideAccor;
	}

	public String getTitleaccor() {
		return titleaccor;
	}

	public void setTitleaccor(String titleaccor) {
		this.titleaccor = titleaccor;
	}

	public VariableGlobale getVb() {
		return vb;
	}

	public void setVb(VariableGlobale vb) {
		this.vb = vb;
	}

	public ApplicationManager getAppMgr() {
		return appMgr;
	}

	public void setAppMgr(ApplicationManager appMgr) {
		this.appMgr = appMgr;
	}

	public LanguageManagerBean getLm() {
		return lm;
	}

	public void setLm(LanguageManagerBean lm) {
		this.lm = lm;
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isStatus1() {
		return status1;
	}

	public void setStatus1(boolean status1) {
		this.status1 = status1;
	}

	public boolean isStatus2() {
		return status2;
	}

	public void setStatus2(boolean status2) {
		this.status2 = status2;
	}

	public boolean isStatus3() {
		return status3;
	}

	public void setStatus3(boolean status3) {
		this.status3 = status3;
	}

	public boolean isStatus4() {
		return status4;
	}

	public void setStatus4(boolean status4) {
		this.status4 = status4;
	}

	public boolean isStatus5() {
		return status5;
	}

	public void setStatus5(boolean status5) {
		this.status5 = status5;
	}	
	
}
