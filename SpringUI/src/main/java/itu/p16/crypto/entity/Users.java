package itu.p16.crypto.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
//import itu.p16.crypto.firestore.FirestoreEntityListener;
import itu.p16.crypto.firebase.listener.UserListener;
import jakarta.persistence.*;

import java.util.Map;

@Entity
//@EntityListeners(UserListener.class)
public class Users {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @JsonProperty("id_users")
    @Column(name = "id_users", nullable = false)
    private Long idUsers;
    @Basic
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Basic
    @Column(name = "username", nullable = false, length = 50)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    @Basic
    @Column(name = "id_tentatives", nullable = false)
    private Integer idTentatives;

    @Column(name = "is_sync_from_firestore", nullable = false)
    private boolean isSyncFromFirestore = false;

    public boolean isSyncFromFirestore() {
        return isSyncFromFirestore;
    }

    public void setSyncFromFirestore(boolean syncFromFirestore) {
        isSyncFromFirestore = syncFromFirestore;
    }

    @Column(name = "id_role")
    @JsonProperty("id_role")
    private Integer idRole;

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public Long getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(Long idUsers) {
        this.idUsers = idUsers;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIdTentatives() {
        return idTentatives;
    }

    public void setIdTentatives(Integer idTentatives) {
        this.idTentatives = idTentatives;
    }

    public static Users fromMap(Map<String, Object> userMap) {
        Users user = new Users();
        user.setIdUsers(Long.valueOf((Integer) userMap.get("id_users")));
        user.setEmail((String) userMap.get("email"));
        user.setUsername((String) userMap.get("username"));
        user.setPassword((String) userMap.get("password"));
//        user.setIdTentatives((userMap.get("id_tentatives") != null) ? (Integer) userMap.get("id_tentatives") : null);
        user.setIdRole((userMap.get("id_role") != null) ? (Integer) userMap.get("id_role") : null);
        return user;
    }


}
