package pl.piteron.idd;

import java.util.List;

import lombok.Data;

/**
 * @author piotr.larysz
 */
public class EmbeddedActions extends EmbeddedResponse<EmbeddedActions.Actions> {

    @Data
    public static class Actions {

        private List<Action> actions;
    }
}
