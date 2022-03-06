package group6.java16.cybersoft.javabackend.crm.model;

import java.sql.Date;

import group6.java16.cybersoft.javabackend.crm.model.seedword.Auditable;
import group6.java16.cybersoft.javabackend.crm.model.seedword.MyEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements MyEntity, Auditable {
	private int id;
	private String createBy;
	private Date createDate;
	private String updateBy;
	private Date updateDate;
	private String username;
	private String password;
	private String fullname;
	private String address;
	private String phone;
	private String passwordNew;
}
