package itu.p16.crypto.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Tentatives {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_tentatives", nullable = false)
    private Long idTentatives;
    @Basic
    @Column(name = "tentatives", nullable = false)
    private Integer tentatives;

    public Long getIdTentatives() {
        return idTentatives;
    }

    public void setIdTentatives(Long idTentatives) {
        this.idTentatives = idTentatives;
    }

    public Integer getTentatives() {
        return tentatives;
    }

    public void setTentatives(Integer tentatives) {
        this.tentatives = tentatives;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tentatives that = (Tentatives) o;

        if (idTentatives != null ? !idTentatives.equals(that.idTentatives) : that.idTentatives != null) return false;
        if (tentatives != null ? !tentatives.equals(that.tentatives) : that.tentatives != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTentatives != null ? idTentatives.hashCode() : 0;
        result = 31 * result + (tentatives != null ? tentatives.hashCode() : 0);
        return result;
    }

}
