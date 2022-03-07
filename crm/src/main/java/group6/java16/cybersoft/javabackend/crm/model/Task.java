/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.model;

import java.sql.Date;

import group6.java16.cybersoft.javabackend.crm.model.seedword.Auditable;
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
public class Task implements Auditable,MyEntity{
	private int id;
	private String createBy;
	private Date createDate;
	private String updateBy;
	private Date updateDate;
	private String taskName;
	private String descript;
	private int user_id;
	private int status_id;
	private int project_id;

}
