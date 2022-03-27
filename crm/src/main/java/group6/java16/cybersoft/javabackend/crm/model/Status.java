/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.model;

import group6.java16.cybersoft.javabackend.crm.model.seedword.MyEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author trunghau
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Status implements MyEntity {
	private int id;
	private String statusName;
	
	

	
	

}
