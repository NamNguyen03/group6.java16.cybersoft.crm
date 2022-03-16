/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.service.status;

import group6.java16.cybersoft.javabackend.crm.service.project.ProjectResponseModels;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author trunghau
 *
 */
public class StatusResponseModels {

	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class StatusTask {

		private int id;
		private String StatusName;

	}
}
