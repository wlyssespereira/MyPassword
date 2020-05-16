package org.wlysses.mypassword.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(of = "id")
@Entity
public class Entry implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    @Column(name="\"user\"")
    private String user;
    private String passwd;

    private String notes;

    private String url;
    private String email;

    public Entry(@JsonProperty UUID id,
                 @JsonProperty String title,
                 @JsonProperty String user,
                 @JsonProperty String passwd,
                 @JsonProperty String notes,
                 @JsonProperty String url,
                 @JsonProperty String email) {
        this.id = id;
        this.title = title;
        this.user = user;
        this.passwd = passwd;
        this.notes = notes;
        this.url = url;
        this.email = email;
    }

    public Entry(UUID id, Entry clone) {
        this.id = id;
        this.title = clone.getTitle();
        this.user = clone.getUser();
        this.passwd = clone.getPasswd();
        this.notes = clone.getNotes();
        this.url = clone.getUrl();
        this.email = clone.getEmail();
    }

    public Entry() {
        super();
    }
}
