package fr.april.gps.webapi.authentication.repositories;

import fr.april.gps.webapi.authentication.entities.ProfileEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * by osow on 15/11/17.
 * for GPS
 */
public interface ProfileRepository extends PagingAndSortingRepository<ProfileEntity, String> {
}
