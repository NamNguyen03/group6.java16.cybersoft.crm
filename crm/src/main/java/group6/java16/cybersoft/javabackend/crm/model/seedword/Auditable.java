package group6.java16.cybersoft.javabackend.crm.model.seedword;

import java.sql.Date;

public interface Auditable {
	void setCreateDate(Date createDate);
	Date getCreateDate(); 
	void setCreateBy(String username);
	String getCreateBy();
	void setUpdateDate(Date updateDate);
	Date getUpdateDate();
	void setUpdateBy(String username);
	String getUpdateBy();
}
