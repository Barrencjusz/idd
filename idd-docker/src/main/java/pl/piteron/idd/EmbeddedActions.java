package pl.piteron.idd;

import java.util.List;

/**
 * @author piotr.larysz
 */
public class EmbeddedActions extends EmbeddedResponse<EmbeddedActions.Actions> {

    public static class Actions {

        private List<ActionCopy> actions;

        public List<ActionCopy> getActions() {
            return actions;
        }

        public void setActions(List<ActionCopy> actions) {
            this.actions = actions;
        }
    }
}
