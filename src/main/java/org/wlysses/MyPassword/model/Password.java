package org.wlysses.MyPassword.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString(callSuper = true)
public class Password extends GenericModel implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1L;

    private String title;
    private String user;
    private String password;

    private String notes;

    private String url;
    private String email;

}
