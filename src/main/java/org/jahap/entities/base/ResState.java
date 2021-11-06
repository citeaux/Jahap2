package org.jahap.entities.base;

import java.io.Serializable;
import jakarta.persistence.*;
import org.jahap.entities.base.state;

/**
 * Entity implementation class for Entity: ResState
 *
 */
@Entity
@DiscriminatorValue(value="ResState")
public class ResState extends state implements Serializable, ResState_ie {

	
	private static final long serialVersionUID = 1L;

	public ResState() {
		
	}
   
}
