package com.xtensus.beans.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.xtensus.hibernate.model.Urgence;
import com.xtensus.hibernate.model.XteButon;
import com.xtensus.jsf.common.LanguageManagerBean;
import com.xtensus.jsf.common.VariableGlobale;
import com.xtensus.spring.service.impl.ApplicationManager;

@Component
@Scope("request")
public class UrgenceGestionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Urgence urgence;
	private String divajouter;
	private String divmodifier;
	private String titre;
	private String infoChampObligatoir;
	private DataModel listUrgence;
	private List<Urgence> listUrg;
	private DualListModel<Urgence> listUrgDual;
	private List<Urgence> copylistUrg;

	private long records = 0;
	private boolean openaccor;
	private boolean openaccor1;
	private String hidebuttonValidateUser;
	private String hidebuttonDelUpUser;
	private String hideAccor;
	private boolean msgerror;
	private String titleaccor;
	@Autowired
	private VariableGlobale vb;
	// fixe

	@Autowired
	private ApplicationManager appMgr;
	@Autowired
	private LanguageManagerBean lm;
	@Autowired
	private MessageSource messageSource;
	private String message;
	private boolean status;
	private boolean status1;
	private boolean status2;
	private boolean status3;

	private Urgence urg;
	private boolean urgenceLibelleShowFr;
	private boolean urgenceDescriptionShowFr;
	private boolean urgenceLibelleShowAr;
	private boolean urgenceDescriptionShowAr;
	private boolean status4;
	private boolean status5;

	@Autowired
	public UrgenceGestionBean() {
		setListUrgence(new ListDataModel());
		listUrg = new ArrayList<Urgence>();
		copylistUrg = new ArrayList<Urgence>();
		System.out.println("*************Bean UrgenceGestionBean Injecte***************");
		divajouter = "block";
		divmodifier = "none";
		infoChampObligatoir = "block";
		openaccor = false;
		openaccor1 = false;
		msgerror = true;
		hidebuttonValidateUser = "block";
		hidebuttonDelUpUser = "none";
		hideAccor = "none";
		urgence = new Urgence();
		System.out.println("###### status dans constructeur == " + status);
	}

	@PostConstruct
	public void Initialize() {

		System.out.println("##### dans UrgenceGestionBean");
		System.out.println("#### messageSource == " + messageSource.toString());
		System.out.println("#### lm == " + lm.getLocale());
		titleaccor = messageSource.getMessage("Ajoutdegrdurgence", new Object[] {}, lm.createLocal());
		titleaccor = "Ajout Degrès d'urgence";
		System.out.println("##### titleaccor == " + titleaccor);
		titre = "Ajouter nature";
		System.out.println("#### vb == " + vb.getLocale());
//		if( vb.getUrgence() != null) {
//			urgence=vb.getUrgence();
//		System.out.println("#### vb.getUrgence= " + vb.getUrgence().getUrgenceLibelle());
//		}
		listUrg = new ArrayList<Urgence>();

		copylistUrg = new ArrayList<Urgence>();
//		System.out.println(vb.getLocale());
		try {
			listUrg = appMgr.getList(Urgence.class);
//			urgence=listUrg.get(0);
			if (vb.isInRestoration()) {
				listUrg.remove(listUrg.size() - 1);
			}
			List<Urgence> listUrgSource = new ArrayList<>();
			List<Urgence> listUrgTarget = new ArrayList<>();
			System.out.println(listUrg.size());

			for (Urgence urg : listUrg) {
				listUrgSource.add(urg);
			}
			listUrgDual = new DualListModel<>(listUrgSource, listUrgTarget);
			if (vb.getLocale().equals("ar")) {

				urgenceLibelleShowFr = false;
				urgenceDescriptionShowFr = false;
				urgenceLibelleShowAr = true;
				urgenceDescriptionShowAr = true;

			} else {

				urgenceLibelleShowFr = true;
				urgenceDescriptionShowFr = true;
				urgenceLibelleShowAr = false;
				urgenceDescriptionShowAr = false;
			}

			for (int i = listUrg.size(); i > 0; i--) {

				urg = listUrg.get(i - 1);
				copylistUrg.add(urg);
				urg = new Urgence();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		listUrgence.setWrappedData(listUrg);

	}

	public void save() {
		status = false;
		status3 = false;
		System.out.println("#### dans save");
		try {
			appMgr.insert(urgence);
//			openLevel1();
			status = true;
			// -méthode save()
//			LogClass logClass = new LogClass();
//			logClass.addTrack(
//					"ajout",
//					"Evénement de log d'ajout du type urgence "
//							+ urgence.getUrgenceId() + "-"
//							+ urgence.getUrgenceLibelle(), vb.getPerson(),
//					"INFO", appMgr);

			divajouter = "none";
			divmodifier = "block";
			infoChampObligatoir = "block";
			titre = "Modifier nature";
//			titleaccor = messageSource.getMessage("Consultationdegredurgence",new Object[] {}, lm.createLocal());
			titleaccor = "Détails Degrès d'urgence";
			openaccor = true;
			openaccor1 = true;
			msgerror = false;
			hideAccor = "block";
			hidebuttonValidateUser = "none";
			hidebuttonDelUpUser = "block";
			System.out.println("##### status dans save == " + status);
		} catch (Exception e) {
			status3 = true;
			e.printStackTrace();
		}

	}

	// Get Selection Rows
	public void getSelectionRow(Urgence u) {
		
		try {
			
			urgence = (Urgence) listUrgence.getRowData();
			System.out.println("######## urgence id " + urgence.getUrgenceId());
			System.out.println("######## urgence lib " + urgence.getUrgenceLibelle());
			vb.setUrgence(urgence);

			System.out.println("*******Selection UrgenceGestionBean*********");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*******ErreurDeSelection UrgenceGestionBean*******");
		}
		divajouter = "none";
		divmodifier = "block";
		infoChampObligatoir = "block";
		titre = "Modifier Urgence";
		titleaccor =messageSource.getMessage("Consultationdegredurgence",new Object[] {}, lm.createLocal());
		openaccor = true;
		openaccor1 = true;
		hideAccor = "block";
		hidebuttonValidateUser = "none";
		hidebuttonDelUpUser = "block";
	}	

//		System.out.println("##### divajouter == " + u);
//		System.out.println("##### divmodifier == " + divmodifier);
//		try {
//			urgence = (Urgence) listUrgence.getRowData();
//			System.out.println("##### urgence == " + urgence.getUrgenceId());
//			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("urgenceselectinne", urgence);
//			Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
//			Urgence x =(Urgence)flash.get("urgenceselectinne");
//			System.out.println("X "+x);
//			vb.setUrgence(urgence);
			// -méthode getSelectionRow()
//			LogClass logClass = new LogClass();
//			logClass.addTrack(
//					"consultation",
//					"Evénement de log de consultation du type urgence "
//							+ urgence.getUrgenceId() + "-"
//							+ urgence.getUrgenceLibelle(), vb.getPerson(),
//					"INFO", appMgr);

//			System.out.println("*******Selection UrgenceGestionBean*********" + urgence.getUrgenceLibelle());
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("*******ErreurDeSelection UrgenceGestionBean*******");
//		}
//		divajouter = "none";
//		divmodifier = "block";
//		infoChampObligatoir = "block";
//		titre = "Modifier nature";
//		titleaccor = messageSource.getMessage("Consultationdegredurgence",new Object[] {}, lm.createLocal());
//		titleaccor = "Modifier Degrès d'urgence";
//		openaccor = true;
//		openaccor1 = true;
//		msgerror = false;
//		hideAccor = "block";
//		hidebuttonValidateUser = "none";
//		hidebuttonDelUpUser = "block";

//	}
	
	public void updateSelectedRow() {
//		status1 = false;
//		status2 = false;

		try {
//			urgence = vb.getUrgence();
			System.out.println("#### urgence == " + urgence.getUrgenceLibelle());
			System.out.println("#### urgence == " + urgence.getUrgenceId());
			System.out.println("#### vb == " + vb);
			status1 = false;
			status2 = false;
//			System.out.println("#### vb.getUrgence() == " + vb.getUrgence());
			
//			Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

	        

//			Urgence x =(Urgence)flash.get("urgenceselectinne");
//			System.out.println("X "+x);
//			urgence=vb.getUrgence();
			appMgr.update(urgence);
			status1 = true;
			setMessage(messageSource.getMessage("confirmInsert", new Object[] {}, lm.createLocal()));
			System.out.println("UpdateTerminee");
			// -méthode updateSelectedRow()
//			LogClass logClass = new LogClass();
//			logClass.addTrack("modification",
//					"Evénement de log de modification du type urgence "
//							+ vb.getUrgence().getUrgenceId() + "-"
//							+ vb.getUrgence().getUrgenceLibelle(),
//					vb.getPerson(), "INFO", appMgr);

//			listUrg = new ArrayList<Urgence>();
//
//			Initialize();
//			urgence = new Urgence();
		} catch (Exception e) {
			e.printStackTrace();
			status2 = true;
			setMessage(messageSource.getMessage("erreurUpdate", new Object[] {}, lm.createLocal()));
		}
		divajouter = "block";
		divmodifier = "none";
		infoChampObligatoir = "block";
		titre = "Ajouter degré d'urgence";
		titleaccor = messageSource.getMessage("Ajoutdegrdurgence",new Object[] {}, lm.createLocal());
//		titleaccor = "Ajout Degrès d'urgence";
		Initialize();

	}

	public void getSelectionRowDel() {

		status4=false;
		System.out.println("##### divajouter == " + divajouter);
		System.out.println("##### divmodifier == " + divmodifier);
		try {
			urgence = (Urgence) listUrgence.getRowData();
			System.out.println("##### urgence == " + urgence.getUrgenceId());
			vb.setUrgence(urgence);
			// -méthode getSelectionRow()
//			LogClass logClass = new LogClass();
//			logClass.addTrack(
//					"consultation",
//					"Evénement de log de consultation du type urgence "
//							+ urgence.getUrgenceId() + "-"
//							+ urgence.getUrgenceLibelle(), vb.getPerson(),
//					"INFO", appMgr);

			System.out.println("*******Selection UrgenceGestionBean*********" + urgence.getUrgenceLibelle());
			status4 = true;
		} catch (Exception e) {
//			status4 = false;
			status5 = true;
			e.printStackTrace();
			System.out.println("*******ErreurDeSelection UrgenceGestionBean*******");
		}

	}


	public void deleteSelectedRow() {

		try {
			System.out.println("##### dans deleteSelectedRow == " + vb.getUrgence().getUrgenceId());
			urgence = vb.getUrgence();
			appMgr.delete(urgence);
			System.out.println("DeleteTerminee");
			// -méthode deleteSelectedRow
//			LogClass logClass = new LogClass();
//			logClass.addTrack(
//					"suppression",
//					"Evénement de log de suppression du type urgence "
//							+ urgence.getUrgenceId() + "-"
//							+ urgence.getUrgenceLibelle(), vb.getPerson(),
//					"INFO", appMgr);
			reinitialiser();
		} catch (Exception e) {
			status5 = true;
			e.printStackTrace();
		}
		System.out.println("#### status5 == " + status5);
		Initialize();

	}

	public void annulModif() {
		urg = new Urgence();
		divajouter = "block";
		divmodifier = "none";
		infoChampObligatoir = "block";
		titre = "Ajouter degré d'urgence";

	}

	public void picklist() {
		try {
			System.out.println("##### dans picklist");
//			System.out.println("##### listUrgDual == " + listUrgDual);

			System.out.println("#### Source pickList size == " + listUrgDual.getSource().size());
			System.out.println("#### Source pickList size == " + listUrgDual.getSource().toString());
			System.out.println("#### Targuet pickList size == " + listUrgDual.getTarget().size());
			System.out.println("#### Targuet pickList size == " + listUrgDual.getTarget().toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void viderchamp() {
		try {
			System.out.println("#### dans viderchamp");
			urgence = new Urgence();
//		vb.setUrgence(urgence);
			msgerror = true;
			titleaccor = "Ajout Degrès d'urgence";
			openaccor1 = true;
			openaccor = false;
			hidebuttonDelUpUser = "none";
			hidebuttonValidateUser = "block";
			hideAccor = "block";
			divajouter = "block";
			divmodifier = "none";
			infoChampObligatoir = "block";
			System.out.println("##### openaccor1 == " + openaccor1);
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	// **************************** Getter && Setter********************

	@SuppressWarnings("unchecked")
	public long getRecords() {
		if (listUrgence == null && listUrgence.getWrappedData() == null)
			records = 0;
		else
			records = ((List<Urgence>) listUrgence.getWrappedData()).size();
		return records;
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
		System.out.println("###### status == " + status);
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

	public boolean isMsgerror() {
		return msgerror;
	}

	public void setMsgerror(boolean msgerror) {
		this.msgerror = msgerror;
	}

	public String getTitleaccor() {
		return titleaccor;
	}

	public void setTitleaccor(String titleaccor) {
		this.titleaccor = titleaccor;
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

	public void setUrgence(Urgence urgence) {
		this.urgence = urgence;
	}

	public Urgence getUrgence() {
		return urgence;
	}

	public void setListUrgence(DataModel listUrgence) {
		this.listUrgence = listUrgence;
	}

	public DataModel getListUrgence() {
		return listUrgence;
	}

	public void setListUrg(List<Urgence> listUrg) {
		this.listUrg = listUrg;
	}

	public List<Urgence> getListUrg() {
		return listUrg;
	}

	public void setCopylistUrg(List<Urgence> copylistUrg) {
		this.copylistUrg = copylistUrg;
	}

	public List<Urgence> getCopylistUrg() {
		return copylistUrg;
	}

	public void setUrg(Urgence urg) {
		this.urg = urg;
	}

	public Urgence getUrg() {
		return urg;
	}

	public void setUrgenceLibelleShowFr(boolean urgenceLibelleShowFr) {
		this.urgenceLibelleShowFr = urgenceLibelleShowFr;
	}

	public boolean isUrgenceLibelleShowFr() {
		return urgenceLibelleShowFr;
	}

	public void setUrgenceDescriptionShowFr(boolean urgenceDescriptionShowFr) {
		this.urgenceDescriptionShowFr = urgenceDescriptionShowFr;
	}

	public boolean isUrgenceDescriptionShowFr() {
		return urgenceDescriptionShowFr;
	}

	public void setUrgenceLibelleShowAr(boolean urgenceLibelleShowAr) {
		this.urgenceLibelleShowAr = urgenceLibelleShowAr;
	}

	public boolean isUrgenceLibelleShowAr() {
		return urgenceLibelleShowAr;
	}

	public void setUrgenceDescriptionShowAr(boolean urgenceDescriptionShowAr) {
		this.urgenceDescriptionShowAr = urgenceDescriptionShowAr;
	}

	public boolean isUrgenceDescriptionShowAr() {
		return urgenceDescriptionShowAr;
	}

	public ApplicationManager getAppMgr() {
		return appMgr;
	}

	public void setAppMgr(ApplicationManager appMgr) {
		this.appMgr = appMgr;
	}

	public VariableGlobale getVb() {
		return vb;
	}

	public void setVb(VariableGlobale vb) {
		this.vb = vb;
	}

	public DualListModel<Urgence> getListUrgDual() {
		return listUrgDual;
	}

	public void setListUrgDual(DualListModel<Urgence> listUrgDual) {
		this.listUrgDual = listUrgDual;
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