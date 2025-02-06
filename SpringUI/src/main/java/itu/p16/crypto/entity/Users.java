package itu.p16.crypto.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Map;

@Entity
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

    public Tentatives getTentativesByIdTentatives() {
        return tentativesByIdTentatives;
    }

    public void setTentativesByIdTentatives(Tentatives tentativesByIdTentatives) {
        this.tentativesByIdTentatives = tentativesByIdTentatives;
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
