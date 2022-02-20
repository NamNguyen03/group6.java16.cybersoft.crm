package group6.java16.cybersoft.javabackend.crm.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import group6.java16.cybersoft.javabackend.crm.model.seedword.Auditable;
import group6.java16.cybersoft.javabackend.crm.model.seedword.MyEntity;

public class EntityRepo<T extends MyEntity> {
	/**
	 * set field for entity when query data from Database 
	 * 
	 * @param entity must not be {@literal null}.
	 * @param results must not be {@literal null}.
	 * @return entity 
	 * @throws SQLException 
	 */
	protected T setFiels(T entity ,ResultSet results ) throws SQLException {
		
		entity.setId(results.getInt("id"));
		
		if(entity instanceof Auditable) {
			((Auditable) entity).setCreateBy(results.getString("create_by"));
			((Auditable) entity).setUpdateBy(results.getString("update_by"));
			((Auditable) entity).setUpdateDate(results.getDate("update_date"));
			((Auditable) entity).setCreateDate(results.getDate("create_date"));
		}
		return entity;
	}  
}
