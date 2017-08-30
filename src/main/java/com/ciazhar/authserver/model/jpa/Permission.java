package com.ciazhar.authserver.model.jpa;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by ciazhar on 5/27/17.
 */


/**
 * Model untuk permmision
 */
@Entity
public class Permission {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    @Column(name = "id_permission")
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role id_role;

    @NotNull
    @Column(name = "nama_permission")
    private String nama;

    @NotNull
    @Column(name = "label_permission")
    private String label;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Role getId_role() {
        return id_role;
    }

    public void setId_role(Role id_role) {
        this.id_role = id_role;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
