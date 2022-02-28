/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.service.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nam
 *
 */
public class RoleRequestModels {
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class UpdateRoleRequest{
		private String roleName;
		private String email;
		private String projectName;
		private String usernameReq;
	}
}
