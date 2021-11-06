package org.jahap.entities.base;

import java.io.Serializable;
import jakarta.persistence.*;
import org.jahap.entities.base.state;

/**
 * Entity implementation class for Entity: ResState
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("ResGroupState")
public class ResGroupState extends state implements Serializable, ResGroupState_ie {

	
	private static final long serialVersionUID = 1L;

	public ResGroupState() {
		
	}
   
}
