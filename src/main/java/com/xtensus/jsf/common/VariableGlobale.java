package com.xtensus.jsf.common;

import java.io.Serializable;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//import com.xtensus.beans.common.ListeDestinatairesModel;
//import com.xtensus.beans.common.GBO.ChequeModel;
//import com.xtensus.beans.common.GBO.courrierInformationOriginaleModel;
//import com.xtensus.beans.utils.ComposantDynamique;
//import com.xtensus.beans.utils.ContactModel;
//import com.xtensus.beans.utils.CourrierConsulterInformations;
//import com.xtensus.beans.utils.CourrierDossierListe;
//import com.xtensus.beans.utils.CourrierInformations;
//import com.xtensus.beans.utils.ExpediteurType;
//import com.xtensus.beans.utils.ListeDetailsDynamique;
//import com.xtensus.beans.utils.RechercheMulticritereModel;
//import com.xtensus.beans.utils.RecherchePmModel;
//import com.xtensus.beans.utils.RecherchePpModel;
//import com.xtensus.beans.utils.RechercheUnitModel;
//import com.xtensus.beans.utils.RechercheUserModel;
//import com.xtensus.beans.utils.RoleModel;
//import com.xtensus.beans.utils.SuiviCourrierCourrier;
//import com.xtensus.cmis.classes.DMSAccessLayer;
//import com.xtensus.faxmail.beans.AttachmentFileBean;
//import com.xtensus.faxmail.utils.GedUtils;
//import com.xtensus.gnl.entity.Evenement;
//import com.xtensus.gnl.entity.Message;
//import com.xtensus.gnl.entity.Notification;
//import com.xtensus.gnl.entity.Trace;
import com.xtensus.hibernate.model.Annotation;
import com.xtensus.hibernate.model.Privilege;
//import com.xtensus.hibernate.model.AoConsultation;
//import com.xtensus.hibernate.model.Classement_archivage_niveau_01;
//import com.xtensus.hibernate.model.Classement_archivage_niveau_02;
//import com.xtensus.hibernate.model.Confidentialite;
//import com.xtensus.hibernate.model.Courrier;
//import com.xtensus.hibernate.model.CourrierDonneeSupplementaire;
//import com.xtensus.hibernate.model.Document;
//import com.xtensus.hibernate.model.Dossier;
//import com.xtensus.hibernate.model.Etat;
//import com.xtensus.hibernate.model.Expdestexterne;
//import com.xtensus.hibernate.model.Fax;
//import com.xtensus.hibernate.model.FaxMail;
//import com.xtensus.hibernate.model.File;
//import com.xtensus.hibernate.model.Groupecontact;
//import com.xtensus.hibernate.model.Mail;
//import com.xtensus.hibernate.model.Nature;
//import com.xtensus.hibernate.model.NatureCategorie;
//import com.xtensus.hibernate.model.Pm;
//import com.xtensus.hibernate.model.Pp;
//import com.xtensus.hibernate.model.Proprietes;
//import com.xtensus.hibernate.model.Sujetmailing;
//import com.xtensus.hibernate.model.Transaction;
//import com.xtensus.hibernate.model.TransactionDestination;
//import com.xtensus.hibernate.model.TransactionDocument;
//import com.xtensus.hibernate.model.Transmission;
//import com.xtensus.hibernate.model.Typetransaction;
//import com.xtensus.hibernate.model.Unite;
//import com.xtensus.hibernate.model.Mail;
import com.xtensus.hibernate.model.Urgence;
import com.xtensus.hibernate.model.XteButon;
import com.xtensus.hibernate.model.XteInterface;

//import com.xtensus.ldap.model.Group;
//import com.xtensus.ldap.model.ItemSelected;

import com.xtensus.ldap.model.Person;

@Component
@Scope("session")
public class VariableGlobale implements Serializable {
	
	
	private static final long serialVersionUID = 5827434242920385885L;
	
	private Annotation annotation;
	private Urgence urgence;
	private String locale = "fr_FR";
	private String localFr = "fr";
	private XteInterface xteInterface;
	private XteButon xteButon;
	private Privilege privilege;
		
	private boolean inRestoration = false;

	private Person person;
	private int referenceInterface;
	private int refButon;
	
	public Annotation getAnnotation() {
		return annotation;
	}

	public void setAnnotation(Annotation annotation) {
		this.annotation = annotation;
	}

	public Urgence getUrgence() {
		return urgence;
	}

	public void setUrgence(Urgence urgence) {
		this.urgence = urgence;
	}
	
	public XteInterface getXteInterface() {
		return xteInterface;
	}

	public void setXteInterface(XteInterface xteInterface) {
		this.xteInterface = xteInterface;
	}
	
	public XteButon getXteButon() {
		return xteButon;
	}

	public void setXteButon(XteButon xteButon) {
		this.xteButon = xteButon;
	}
	
	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getLocalFr() {
		return localFr;
	}

	public void setLocalFr(String localFr) {
		this.localFr = localFr;
	}

	public boolean isInRestoration() {
		return inRestoration;
	}

	public void setInRestoration(boolean inRestoration) {
		this.inRestoration = inRestoration;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public int getReferenceInterface() {
		return referenceInterface;
	}

	public void setReferenceInterface(int referenceInterface) {
		this.referenceInterface = referenceInterface;
	}

	public int getRefButon() {
		return refButon;
	}

	public void setRefButon(int refButon) {
		this.refButon = refButon;
	}	
	
}