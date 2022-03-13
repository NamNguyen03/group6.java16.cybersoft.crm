/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.share.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author nam
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MyNotification {
	@Getter
	private String message;
	private boolean isError;
	
	public boolean getIsError() {
		return isError;
	}
}
