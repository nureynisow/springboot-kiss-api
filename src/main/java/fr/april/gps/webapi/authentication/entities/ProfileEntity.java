package fr.april.gps.webapi.authentication.entities;


import fr.april.gps.webapi.common.models.EntityCore;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * by osow on 15/11/17.
 * for GPS
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Document(collection = "profiles")
@AllArgsConstructor
@NoArgsConstructor
public class ProfileEntity extends EntityCore {

	@Indexed(unique = true)
	private String login;

	private String password;

	private String firstName, lastName;
}
