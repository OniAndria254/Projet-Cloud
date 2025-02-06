package itu.p16.crypto.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
//import itu.p16.crypto.firestore.FirestoreEntityListener;
import jakarta.persistence.*;

import java.util.Map;

@Entity
//@EntityListeners(FirestoreEntityListener.class)
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
    @ManyToOne
    @JoinColumn(name = "id_tentatives", referencedColumnName = "id_tentatives", nullable = false)
    private Tentatives tentativesByIdTentatives;

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

    public Tentatives getTentativesByIdTentatives() {
        return tentativesByIdTentatives;
    }

    public void setTentativesByIdTentatives(Tentatives tentativesByIdTentatives) {
        this.tentativesByIdTentatives = tentativesByIdTentatives;
    }

    public static Users fromMap(Map<String, Object> map) {
        Users user = new Users();
        user.setEmail((String) map.get("email"));
        user.setUsername((String) map.get("username"));
        user.setPassword((String) map.get("password"));

        // Vérifier si l'ID est présent et l'assigner
        Object id = map.get("id_users");
        if (id instanceof Number) {
            user.setIdUsers(((Number) id).longValue());
        }

        return user;
    }

}
