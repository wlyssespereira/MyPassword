package org.wlysses.MyPassword.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
public class Group extends GenericModel implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1L;

    private String title;
    private String notes;

    private List<Password> passwordList;

}



