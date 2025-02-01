package itu.p16.crypto.entity;

import jakarta.persistence.*;

@Entity
public class Brouillon {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_brouillon", nullable = false)
    private Long idBrouillon;
    @Basic
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Basic
    @Column(name = "username", nullable = false, length = 50)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    public Long getIdBrouillon() {
        return idBrouillon;
    }

    public void setIdBrouillon(Long idBrouillon) {
        this.idBrouillon = idBrouillon;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Brouillon brouillon = (Brouillon) o;

        if (idBrouillon != null ? !idBrouillon.equals(brouillon.idBrouillon) : brouillon.idBrouillon != null)
            return false;
        if (email != null ? !email.equals(brouillon.email) : brouillon.email != null) return false;
        if (username != null ? !username.equals(brouillon.username) : brouillon.username != null) return false;
        if (password != null ? !password.equals(brouillon.password) : brouillon.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idBrouillon != null ? idBrouillon.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
