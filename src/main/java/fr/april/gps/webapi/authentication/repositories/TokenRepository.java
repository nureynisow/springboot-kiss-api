package fr.april.gps.webapi.authentication.repositories;

import fr.april.gps.webapi.authentication.entities.TokenEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * by osow on 16/11/17.
 * for GPS
 */
public interface TokenRepository extends PagingAndSortingRepository<TokenEntity, String> {
	Long countByLogin(String login);

	TokenEntity findByJsonWebToken(String token);
}
