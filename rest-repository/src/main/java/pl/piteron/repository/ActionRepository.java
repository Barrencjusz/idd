package pl.piteron.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.piteron.entity.Action;

/**
 * @author piotr.larysz
 */
@RepositoryRestResource
public interface ActionRepository extends PagingAndSortingRepository<Action, String> {

    List<Action> findByTagsIn(@Param("tags") List<String> tags);
}
