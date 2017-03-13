package pl.piteron.idd;

import java.util.Set;

import lombok.Data;

/**
 * @author piotr.larysz
 */
@Data
public class Action {

    private String id;

    private String name;

    private String friendlyName;

    private Set<String> tags;
}
