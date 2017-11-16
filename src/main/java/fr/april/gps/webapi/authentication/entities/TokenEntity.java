package fr.april.gps.webapi.authentication.entities;

import fr.april.gps.webapi.common.models.EntityCore;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * by osow on 16/11/17.
 * for GPS
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Document(collection = "issuedToken")
@AllArgsConstructor
@Builder
public class TokenEntity extends EntityCore {

	@Indexed
	private String login;

	@Indexed(unique = true, expireAfterSeconds = 43200)
	private String jsonWebToken;

}
