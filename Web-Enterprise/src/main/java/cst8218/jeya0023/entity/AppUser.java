/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.jeya0023.entity;

import java.io.Serializable;
import java.util.HashMap;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.security.enterprise.identitystore.PasswordHash;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ajithyugan Jeyakumar
 */
@Entity
@Table(name = "APPUSER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AppUser.findAll", query = "SELECT c FROM AppUser c"),
    @NamedQuery(name = "AppUser.findById", query = "SELECT c FROM AppUser c WHERE c.id = :id"),
    @NamedQuery(name = "AppUser.findByGroupname", query = "SELECT c FROM AppUser c WHERE c.groupname = :groupname"),
    @NamedQuery(name = "AppUser.findByPassword", query = "SELECT c FROM AppUser c WHERE c.password = :password"),
    @NamedQuery(name = "AppUser.findByUserid", query = "SELECT c FROM AppUser c WHERE c.userid = :userid")})
public class AppUser implements Serializable {

        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Basic(optional = false)
        @NotNull
        @Column(name = "ID")
        @NotNull
        private Long id;
        @Column(name = "GROUPNAME")
        @NotNull
        private String groupname;
        @NotNull
        @Column(name = "PASSWORD")
        private String password;
        @NotNull
        @Column(name = "USERID")
        private String userid;
   


    public AppUser() {
    }

    public AppUser(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getPassword() {
        return "";
    }

    public void setPassword(String mySecretPassword) {
        if (!(mySecretPassword.length()==0)){
        // initialize a PasswordHash object which will generate password hashes
        Instance<? extends PasswordHash> instance = CDI.current().select(Pbkdf2PasswordHash.class);
        PasswordHash passwordHash = instance.get();
        passwordHash.initialize(new HashMap<String,String>()); // todo: are the defaults good enough?
        // now we can generate a password entry for a given password
        String passwordEntry = mySecretPassword; //pretend the user has chosen a password mySecretPassword
        passwordEntry = passwordHash.generate(passwordEntry.toCharArray());
        //at this point, passwordEntry refers to a salted/hashed password entry corresponding to mySecretPassword
        this.password = passwordEntry;
}
}

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppUser)) {
            return false;
        }
        AppUser other = (AppUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.lab_04.AppUser[ id=" + id + " ]";
    }
    
}
