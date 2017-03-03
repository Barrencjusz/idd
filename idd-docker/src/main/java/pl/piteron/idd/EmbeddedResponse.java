package pl.piteron.idd;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author piotr.larysz
 */
public class EmbeddedResponse<T> {

    @JsonProperty("_embedded")
    private T embedded;

    public T getEmbedded() {
        return embedded;
    }

    public void setEmbedded(T embedded) {
        this.embedded = embedded;
    }
}
