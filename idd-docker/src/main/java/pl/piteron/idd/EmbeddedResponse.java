package pl.piteron.idd;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author piotr.larysz
 */
@Data
public class EmbeddedResponse<T> {

    @JsonProperty("_embedded")
    private T embedded;
}
