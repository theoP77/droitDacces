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

import com.xtensus.hibernate.model.XteButon;
import com.xtensus.hibernate.model.XteInterface;
import com.xtensus.jsf.common.LanguageManagerBean;
import com.xtensus.jsf.common.VariableGlobale;
import com.xtensus.spring.service.impl.ApplicationManager;


@Component()
@Scope("request")
public class XteInterfaceGestionBean {

	private XteInterface xteInterface;
	private String divajouter;
	private String divmodifier;
	private String titre;
	private String infoChampObligatoir;
	private DataModel listXteInterface;
	private List<XteInterface> listXteInterf;
	private long records = 0;
	private boolean openaccor;
	private boolean openaccor1;
	private String hidebuttonValidateUser;
	private String hidebuttonDelUpUser;
	private String hideAccor;
	private String titleaccor;
	private List<XteButon> listeBouton;

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
	
	private boolean xteInterfaceLibelleShowFr;
	private boolean xteInterfaceCodeShowFr;
	private boolean xteInterfaceLibelleShowAr;
	private boolean xteInterfaceCodeShowAr;

	@Autowired
	public XteInterfaceGestionBean(
			@Qualifier("applicationManager") ApplicationManager appMgr) {
		this.appMgr = appMgr;
		listXteInterface = new ListDataModel();
		listXteInterf = new ArrayList<XteInterface>();
		divajouter = "block";
		divmodifier = "none";
		xteInterface = new XteInterface();
		infoChampObligatoir = "block";
		openaccor = false;
		openaccor1 = false;
		hidebuttonValidateUser = "block";
		hidebuttonDelUpUser = "none";
		hideAccor = "none";
		System.out.println("*************Bean XteInterfaceGestionBean Injecte***************");	
	}

	@PostConstruct
	public void Initialize() {
			
		System.out.println("###### dans init");
		
		titre = "Ajouter Interface";
		if( vb.getXteInterface() != null) 
			xteInterface=vb.getXteInterface();
		titleaccor =messageSource.getMessage("AjoutxteInterface",new Object[] {}, lm.createLocal());
		listXteInterf = new ArrayList<XteInterface>();
		try {
			listXteInterf = appMgr.getList(XteInterface.class);
			
			if (vb.getLocale().equals("ar")) {
				
				setXteInterfaceLibelleShowFr(false);
				setXteInterfaceCodeShowFr(false);
				setXteInterfaceLibelleShowAr(true);
				setXteInterfaceCodeShowAr(true);
				
			} else {
				
				setXteInterfaceLibelleShowFr(true);
				setXteInterfaceCodeShowFr(true);
				setXteInterfaceLibelleShowAr(false);
				setXteInterfaceCodeShowAr(false);
			}
			Collections.reverse(listXteInterf);
		} catch (Exception e) {
			System.out.println("erreur");
			e.printStackTrace();
		}
		listXteInterface.setWrappedData(listXteInterf);
		System.out.println("##### dans initialize fin ");
	}

	public void save() {
		
		status = false;
		status3 = false;
		
		try {
			appMgr.insert(xteInterface);
			setMessage(messageSource.getMessage("confirmInsert", new Object[] {}, lm.createLocal()));
			status = true;			
			System.out.println("***************Succes XteInterfaceGestionBean***************");

		} catch (Exception e) {
			e.printStackTrace();
			status3 = true;
			System.out.println("***************Erreur XteInterfaceGestionBean***************");
		}		
		hideAccor="none";
	}

	public void getSelectionRow() {

		try {
			
			xteInterface = (XteInterface) listXteInterface.getRowData();
			System.out.println("######## xteInterface ref " + xteInterface.getXteInterfaceRef());
			System.out.println("######## xteInterface lib " + xteInterface.getXteInterfaceLibelle());
			vb.setXteInterface(xteInterface);
			
			System.out.println("*******Selection XteInterfaceGestionBean*********");
			
		} catch (Exception e) {
			e.printStackTrace();
			
			System.out.println("*******ErreurDeSelection XteInterfaceGestionBean*******");
		}
		divajouter = "none";
		divmodifier = "block";
		infoChampObligatoir = "block";
		titre = "Modifier xteInterface";
		titleaccor =messageSource.getMessage("ModifierxteInterface",new Object[] {}, lm.createLocal());
		openaccor = true;
		openaccor1 = true;
		hideAccor = "block";
		hidebuttonValidateUser = "none";
		hidebuttonDelUpUser = "block";
	}

	public void updateSelectedRow() {
		
		try {
			System.out.println("updateSelectedRow xteInterface ref " + xteInterface.getXteInterfaceRef());
			System.out.println("updateSelectedRow xteInterface lib " + xteInterface.getXteInterfaceLibelle());
			status1 = false;
			status2 = false;
			
			System.out.println("########&&&&& xteInterface ref " + xteInterface.getXteInterfaceRef());
			System.out.println("########&&&&& xteInterface lib " + xteInterface.getXteInterfaceLibelle());
			appMgr.update(xteInterface);
			status1 = true;
			setMessage(messageSource.getMessage("confirmInsert", new Object[] {}, lm.createLocal()));
			// -méthode updateSelectedRow()
			
			} catch (Exception e) {
			e.printStackTrace();
			status2 = true;
			setMessage(messageSource.getMessage("erreurUpdate", new Object[] {}, lm.createLocal()));
			
			}
			divajouter = "block";
			divmodifier = "none";
			infoChampObligatoir = "block";
			titre = "Ajouter xteInterface";
			titleaccor =messageSource.getMessage("AjoutxteInterface",new Object[] {}, lm.createLocal());
		Initialize();
	}
	
	public void getSelectionRowSea() {
		
		try {		
			xteInterface = (XteInterface) listXteInterface.getRowData();
			System.out.println("######## xteInterface ref " + xteInterface.getXteInterfaceRef());
			System.out.println("######## xteInterface lib " + xteInterface.getXteInterfaceLibelle());
			vb.setXteInterface(xteInterface);

			System.out.println("*******Selection XteInterfaceGestionBean*********");

		} catch (Exception e) {
			e.printStackTrace();
			
			System.out.println("*******ErreurDeSelection XteInterfaceGestionBean*******");
		}
		divajouter = "none";
		divmodifier = "none";
		infoChampObligatoir = "none";
		titre = "Consulter parametre d'interface";
		titleaccor =messageSource.getMessage("ConsultationxteInterface",new Object[] {}, lm.createLocal());
		openaccor = true;
		openaccor1 = true;
		hideAccor = "block";
		hidebuttonValidateUser = "none";
		hidebuttonDelUpUser = "block";
	}
	
	public String findSelectedRow() {
		
	  xteInterface = (XteInterface) listXteInterface.getRowData();
		
	  int xteRef = xteInterface.getXteInterfaceRef();
	  vb.setReferenceInterface(xteRef);  
	  
	  try {
		  System.out.println("findSelectedRow xteInterface ref = " + xteInterface.getXteInterfaceRef());
		  System.out.println("findSelectedRow xteInterface lib = " + xteInterface.getXteInterfaceLibelle()); 
		  status1 = false; 
		  status2 = false;
	  
		  System.out.println("########&&&&& xteInterface ref = " + xteInterface.getXteInterfaceRef());
		  System.out.println("########&&&&& xteInterface lib = " + xteInterface.getXteInterfaceLibelle()); 	
	  
		  listeBouton = appMgr.getListeBoutonParRefInterface(xteRef);
		  System.out.println("Liste Boutons correspondants: ");	
		  for (XteButon xteButon : listeBouton) {
		  System.out.println(xteButon);	
		  }  
		  status1 = true;
		  setMessage(messageSource.getMessage("confirmInsert", new Object[] {},
				  lm.createLocal())); // -méthode findSelectedRow(
	
	  	} catch (Exception e) { 
	  		e.printStackTrace(); 
	  		status2 = true;
	  		setMessage(messageSource.getMessage("erreurUpdate", new Object[] {},
	  				lm.createLocal()));  
	  	} 
	  		divajouter = "block"; 
	  		divmodifier = "none"; 
	  		infoChampObligatoir = "none";
	  		titre = "Ajouter xteInterface"; 
	  		titleaccor=messageSource.getMessage("AjoutxteInterface",new Object[] {}, lm.createLocal());
	  Initialize();
	  
	  return "showParamInterface.xhtml";	  
	}
	
	public void getSelectionRowDel() {
		status4=false;
		System.out.println("###### dans getSelectionRowDel");
		try {
			xteInterface = (XteInterface) listXteInterface.getRowData();
			vb.setXteInterface(xteInterface);
			status4=true;			
			System.out.println("*******Selection XteInterfaceGestionBean*********");

			} catch (Exception e) {
				e.printStackTrace();
				status5=true;
				System.out.println("*******ErreurDeSelection XteInterfaceGestionBean*******");
			}
	}
	
	public void deleteSelectedRow() {

		try {
			System.out.println("########## dans deleteSelectedRow == " + xteInterface.getXteInterfaceRef());
			xteInterface = vb.getXteInterface();
			appMgr.delete(xteInterface);
			System.out.println("DeleteTerminee");
			
			} catch (Exception e) {
				status5=true;
				e.printStackTrace();
			}
		Initialize();
	}

	public void annulModif() {
		xteInterface = new XteInterface();
		divajouter = "block";
		divmodifier = "none";
		infoChampObligatoir = "block";
		titre = "Ajouter xteInterface";
	}

	public void viderchamp() {
		System.out.println("##### dans viderchamp");
		status = false;
		status3 = false;
		status1 = false;
		status2 = false;
		xteInterface = new XteInterface();
		titleaccor = messageSource.getMessage("AjoutxteInterface",new Object[] {}, lm.createLocal());;
		openaccor1 = true;
		openaccor = false;
		hidebuttonDelUpUser = "none";
		hidebuttonValidateUser = "block";
		hideAccor = "block";
		divajouter = "block";
		divmodifier = "none";
		infoChampObligatoir = "block";
		vb.setXteInterface(new XteInterface());
	}

	public String valide() {
		return ("OK");
	}

	// **************************** Getter && Setter********************

	@SuppressWarnings("unchecked")
	public long getRecords() {
		if (listXteInterface == null && listXteInterface.getWrappedData() == null)
			records = 0;
		else
			records = ((List<XteInterface>) listXteInterface.getWrappedData())
					.size();
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

	public void setXteInterface(XteInterface xteInterface) {
		this.xteInterface = xteInterface;
	}
	public XteInterface getXteInterface() {
		System.out.println("xteInterface"+xteInterface);
		return xteInterface;
	}
	
//  On va se servir dans le bean pour passer la liste des Interfaces pour remplir ou 
//	alimenter une liste déroulante pour choisir l'interface correspondante à un bouton
	public void setListXteInterface(DataModel listXteInterface) {
		this.listXteInterface = listXteInterface;
	}
	public DataModel getListXteInterface() {
		return listXteInterface;
	}

	public void setListXteInterf(List<XteInterface> listXteInterf) {
		this.listXteInterf = listXteInterf;
	}
	public List<XteInterface> getListXteInterf() {
		return listXteInterf;
	}

	public VariableGlobale getVb() {
		return vb;
	}
	public void setVb(VariableGlobale vb) {
		this.vb = vb;
	}

	public void setXteInterfaceLibelleShowFr(boolean xteInterfaceLibelleShowFr) {
		this.xteInterfaceLibelleShowFr = xteInterfaceLibelleShowFr;
	}
	public boolean isXteInterfaceLibelleShowFr() {
		return xteInterfaceLibelleShowFr;
	}

	public void setXteInterfaceCodeShowFr(boolean xteInterfaceCodeShowFr) {
		this.xteInterfaceCodeShowFr = xteInterfaceCodeShowFr;
	}
	public boolean isXteInterfaceCodeShowFr() {
		return xteInterfaceCodeShowFr;
	}

	public void setXteInterfaceLibelleShowAr(boolean xteInterfaceLibelleShowAr) {
		this.xteInterfaceLibelleShowAr = xteInterfaceLibelleShowAr;
	}
	public boolean isXteInterfaceLibelleShowAr() {
		return xteInterfaceLibelleShowAr;
	}

	public void setXteInterfaceCodeShowAr(boolean xteInterfaceCodeShowAr) {
		this.xteInterfaceCodeShowAr = xteInterfaceCodeShowAr;
	}
	public boolean isXteInterfaceCodeShowAr() {
		return xteInterfaceCodeShowAr;
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
	
	public List<XteButon> getListeBouton() {
		return listeBouton;
	}	
	public void setListeBouton(List<XteButon> listeBouton) {
		this.listeBouton = listeBouton;
	}

}