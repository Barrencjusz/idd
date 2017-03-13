package pl.piteron.repository.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.piteron.entity.User;

/**
 * @author piotr.larysz
 */
@Validated
@CrossOrigin
@RepositoryRestResource
public interface ActionResultRepository extends PagingAndSortingRepository<User.Participation.ActionResult, String> {
}
