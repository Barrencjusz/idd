package pl.piteron.repository;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.piteron.entity.Action;

/**
 * @author piotr.larysz
 */
@Validated
@CrossOrigin
@RepositoryRestResource
public interface ActionRepository extends PagingAndSortingRepository<Action, String> {

    @RestResource(path = "byTags")
    List<Action> findByTagsIn(@Param("tags") @NotNull List<String> tags);

    @ControllerAdvice
    class Advice {

        @ExceptionHandler({ConstraintViolationException.class, InvalidDataAccessApiUsageException.class})
        public String handle(HttpServletRequest request) {
            request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.BAD_REQUEST.value());
            return "/error";
        }
    }
}
