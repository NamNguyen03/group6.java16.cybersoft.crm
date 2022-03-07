/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.model;

import group6.java16.cybersoft.javabackend.crm.model.seedword.MyEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nam
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role implements MyEntity {
	private int id;
	private String name;
}
