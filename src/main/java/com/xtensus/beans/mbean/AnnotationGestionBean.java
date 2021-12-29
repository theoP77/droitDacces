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

//import com.xtensus.aop.LogClass;

import com.xtensus.hibernate.model.Annotation;
import com.xtensus.jsf.common.LanguageManagerBean;
import com.xtensus.jsf.common.VariableGlobale;
import com.xtensus.spring.service.impl.ApplicationManager;


@Component()
@Scope("request")
public class AnnotationGestionBean {

	private Annotation annotation;
	private String divajouter;
	private String divmodifier;
	private String titre;
	private String infoChampObligatoir;
	private DataModel listAnnotation;
	private List<Annotation> listAnno;
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
	
	private boolean annotationLibelleShowFr;
	private boolean annotationDescriptionShowFr;
	private boolean annotationLibelleShowAr;
	private boolean annotationDescriptionShowAr;

	@Autowired
	public AnnotationGestionBean(
			@Qualifier("applicationManager") ApplicationManager appMgr) {
		this.appMgr = appMgr;
		listAnnotation = new ListDataModel();
		listAnno = new ArrayList<Annotation>();
		divajouter = "block";
		divmodifier = "none";
		annotation = new Annotation();
		infoChampObligatoir = "block";
		openaccor = false;
		openaccor1 = false;
		hidebuttonValidateUser = "block";
		hidebuttonDelUpUser = "none";
		hideAccor = "none";
		System.out
				.println("*************Bean AnnotationGestionBean Injecte***************");
	}

	@PostConstruct
	public void Initialize() {
		System.out.println("###### dans init");
		
		titre = "Ajouter annotation";
//		annotation = new Annotation();
		if( vb.getAnnotation() != null) 
			annotation=vb.getAnnotation();
		titleaccor =messageSource.getMessage("Ajoutannotation",new Object[] {}, lm.createLocal());
		listAnno = new ArrayList<Annotation>();
		try {
			listAnno = appMgr.getList(Annotation.class);
			
			if (vb.getLocale().equals("ar")) {
				
				setAnnotationLibelleShowFr(false);
				setAnnotationDescriptionShowFr(false);
				setAnnotationLibelleShowAr(true);
				setAnnotationDescriptionShowAr(true);
				
			} else {
				
				setAnnotationLibelleShowFr(true);
				setAnnotationDescriptionShowFr(true);
				setAnnotationLibelleShowAr(false);
				setAnnotationDescriptionShowAr(false);
			}
			Collections.reverse(listAnno);
		} catch (Exception e) {
			System.out.println("erreur");
			e.printStackTrace();
		}
		listAnnotation.setWrappedData(listAnno);
		System.out.println("##### dans initialize fin ");
	}

	public void save() {
		status = false;
		status3 = false;
		try {

			appMgr.insert(annotation);
			setMessage(messageSource.getMessage("confirmInsert",
					new Object[] {}, lm.createLocal()));
			status = true;
			// -méthode save()
			
			System.out
					.println("***************Succes AnnotationGestionBean***************");

		} catch (Exception e) {
			e.printStackTrace();
			status3 = true;
			System.out
					.println("***************Erreur AnnotationGestionBean***************");
		}
		hideAccor="none";

	}

	// Get Selection Rows
	public void getSelectionRow() {

		try {
			
			annotation = (Annotation) listAnnotation.getRowData();
			System.out.println("######## annotation id " + annotation.getAnnotationId());
			System.out.println("######## annotation lib " + annotation.getAnnotationLibelle());
			vb.setAnnotation(annotation);

			// -méthode getSelectionRow()
			

			System.out
					.println("*******Selection AnnotationGestionBean*********");
//			Initialize();

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("*******ErreurDeSelection AnnotationGestionBean*******");
		}
		divajouter = "none";
		divmodifier = "block";
		infoChampObligatoir = "block";
		titre = "Modifier annotation";
		titleaccor =messageSource.getMessage("Consultationannotation",new Object[] {}, lm.createLocal());
		openaccor = true;
		openaccor1 = true;
		hideAccor = "block";
		hidebuttonValidateUser = "none";
		hidebuttonDelUpUser = "block";

	}

	public void updateSelectedRow() {
		

		try {System.out.println("updateSelectedRow annotation id " + annotation.getAnnotationId());
		System.out.println("updateSelectedRow annotation lib " + annotation.getAnnotationLibelle());
		status1 = false;
		status2 = false;
//			annotation=vb.getAnnotation();
			
			System.out.println("########&&&&& annotation id " + annotation.getAnnotationId());
			System.out.println("########&&&&& annotation lib " + annotation.getAnnotationLibelle());
			appMgr.update(annotation);
			status1 = true;
			setMessage(messageSource.getMessage("confirmInsert", new Object[] {}, lm.createLocal()));
			// -méthode updateSelectedRow()
			
		} catch (Exception e) {
			e.printStackTrace();
			status2 = true;
			setMessage(messageSource.getMessage("erreurUpdate",
					new Object[] {}, lm.createLocal()));
			
		}
		divajouter = "block";
		divmodifier = "none";
		infoChampObligatoir = "block";
		titre = "Ajouter annotation";
		titleaccor =messageSource.getMessage("Ajoutannotation",new Object[] {}, lm.createLocal());
		Initialize();

	}
	public void getSelectionRowDel() {
		status4=false;
		System.out.println("###### dans getSelectionRowDel");
		try {
			annotation = (Annotation) listAnnotation.getRowData();
			vb.setAnnotation(annotation);
			status4=true;
			// -méthode getSelectionRow()
			
			System.out
					.println("*******Selection AnnotationGestionBean*********");

		} catch (Exception e) {
			e.printStackTrace();
			status5=true;
			System.out
					.println("*******ErreurDeSelection AnnotationGestionBean*******");
		}

	}
	public void deleteSelectedRow() {

		try {
			System.out.println("########## dans deleteSelectedRow == " + annotation.getAnnotationId());
			annotation = vb.getAnnotation();
			appMgr.delete(annotation);
			System.out.println("DeleteTerminee");
			// -méthode deleteSelectedRow
			
		} catch (Exception e) {
			status5=true;
			e.printStackTrace();
		}
		Initialize();
	}

	public void annulModif() {
		annotation = new Annotation();
		divajouter = "block";
		divmodifier = "none";
		infoChampObligatoir = "block";
		titre = "Ajouter annotation";
	}

	public void viderchamp() {
		System.out.println("##### dans viderchamp");
		status = false;
		status3 = false;
		status1 = false;
		status2 = false;
		annotation = new Annotation();
		titleaccor = messageSource.getMessage("Ajoutannotation",new Object[] {}, lm.createLocal());;
		openaccor1 = true;
		openaccor = false;
		hidebuttonDelUpUser = "none";
		hidebuttonValidateUser = "block";
		hideAccor = "block";
		divajouter = "block";
		divmodifier = "none";
		infoChampObligatoir = "block";
		vb.setAnnotation(new Annotation());
	}

	public String valide() {
		return ("OK");
	}

	// **************************** Getter && Setter********************

	@SuppressWarnings("unchecked")
	public long getRecords() {
		if (listAnnotation == null && listAnnotation.getWrappedData() == null)
			records = 0;
		else
			records = ((List<Annotation>) listAnnotation.getWrappedData())
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

	public void setAnnotation(Annotation annotation) {
		this.annotation = annotation;
	}

	public Annotation getAnnotation() {
		System.out.println("annotation"+annotation);
		return annotation;
	}

	public void setListAnnotation(DataModel listAnnotation) {
		this.listAnnotation = listAnnotation;
	}

	public DataModel getListAnnotation() {
		return listAnnotation;
	}

	public void setListAnno(List<Annotation> listAnno) {
		this.listAnno = listAnno;
	}

	public List<Annotation> getListAnno() {
		return listAnno;
	}

	public VariableGlobale getVb() {
		return vb;
	}

	public void setVb(VariableGlobale vb) {
		this.vb = vb;
	}

	public void setAnnotationLibelleShowFr(boolean annotationLibelleShowFr) {
		this.annotationLibelleShowFr = annotationLibelleShowFr;
	}

	public boolean isAnnotationLibelleShowFr() {
		return annotationLibelleShowFr;
	}

	public void setAnnotationDescriptionShowFr(boolean annotationDescriptionShowFr) {
		this.annotationDescriptionShowFr = annotationDescriptionShowFr;
	}

	public boolean isAnnotationDescriptionShowFr() {
		return annotationDescriptionShowFr;
	}

	public void setAnnotationLibelleShowAr(boolean annotationLibelleShowAr) {
		this.annotationLibelleShowAr = annotationLibelleShowAr;
	}

	public boolean isAnnotationLibelleShowAr() {
		return annotationLibelleShowAr;
	}

	public void setAnnotationDescriptionShowAr(boolean annotationDescriptionShowAr) {
		this.annotationDescriptionShowAr = annotationDescriptionShowAr;
	}

	public boolean isAnnotationDescriptionShowAr() {
		return annotationDescriptionShowAr;
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