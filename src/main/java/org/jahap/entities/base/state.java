package org.jahap.entities.base;

import org.jahap.i18n.Resourcen;
import org.jahap.i18n.ResourcenManager;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import jakarta.persistence.*;

/**
 * Entity implementation class for Entity: state
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="STATETYPE" , discriminatorType=DiscriminatorType.STRING,length=20)
@Table(name="State")
public abstract class state implements Serializable, state_ie {

	   
	@Id
	@Basic(optional = false)
	@GeneratedValue
	@Column(name = "ID")
	private Integer id;
	@Column(name = "STATETYPE")
	private String StateType;
	@Column(name = "ISFREE")
	private String isFree;
	@Column(name = "NAME")
	private String name;
	@Column(name = "TRANSLATIONSTRING")
	private String translationString;
	private static final long serialVersionUID = 1L;

	public state() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	/* (non-Javadoc)
	 * @see org.jahap.entities.base.state_ie#getStateType()
	 */
	@Override
	public String getStateType() {
		return this.StateType;
	}

	/* (non-Javadoc)
	 * @see org.jahap.entities.base.state_ie#setStateType(java.lang.String)
	 */
	@Override
	public void setStateType(String StateType) {
		this.StateType = StateType;
	}   
	/* (non-Javadoc)
	 * @see org.jahap.entities.base.state_ie#getIsFree()
	 */
	@Override
	public String getIsFree() {
		return this.isFree;
	}

	/* (non-Javadoc)
	 * @see org.jahap.entities.base.state_ie#setIsFree(java.lang.String)
	 */
	@Override
	public void setIsFree(String isFree) {
		this.isFree = isFree;
	}   
	/* (non-Javadoc)
	 * @see org.jahap.entities.base.state_ie#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see org.jahap.entities.base.state_ie#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}   
	/* (non-Javadoc)
	 * @see org.jahap.entities.base.state_ie#getTranslationString()
	 */
	@Override
	public String getTranslationString() {
		return this.translationString;
	}

	public String getTranslation(){
		ResourcenManager jk;
		Resourcen kk= new Resourcen();
		jk=kk.getResourcenManager();
		jk.getFxResourceBundle("i18n.Systexts");
		return jk.getFxLangString(getTranslationString());
	}

	public void setTranslationString(String translationString) {
		this.translationString = translationString;
	}
   
}
