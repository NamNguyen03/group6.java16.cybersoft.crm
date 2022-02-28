/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.model;

import java.sql.Date;

import group6.java16.cybersoft.javabackend.crm.model.seedword.Auditable;
import group6.java16.cybersoft.javabackend.crm.model.seedword.MyEntity;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author nam
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Project implements MyEntity, Auditable {
	private int id;
	private String createBy;
	private Date createDate;
	private String updateBy;
	private Date updateDate;
	private String name;
	private String description;
	private String status;
}
