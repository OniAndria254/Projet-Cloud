package itu.p16.crypto.entity;

import jakarta.persistence.*;

@Entity
public class Config {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_config", nullable = false)
    private Long idConfig;
    @Basic
    @Column(name = "compteur", nullable = false)
    private Integer compteur;
    @Basic
    @Column(name = "dureePIN", nullable = false)
    private Integer dureePin;

    public Long getIdConfig() {
        return idConfig;
    }

    public void setIdConfig(Long idConfig) {
        this.idConfig = idConfig;
    }

    public Integer getCompteur() {
        return compteur;
    }

    public void setCompteur(Integer compteur) {
        this.compteur = compteur;
    }

    public Integer getDureePin() {
        return dureePin;
    }

    public void setDureePin(Integer dureePin) {
        this.dureePin = dureePin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Config config = (Config) o;

        if (idConfig != null ? !idConfig.equals(config.idConfig) : config.idConfig != null) return false;
        if (compteur != null ? !compteur.equals(config.compteur) : config.compteur != null) return false;
        if (dureePin != null ? !dureePin.equals(config.dureePin) : config.dureePin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idConfig != null ? idConfig.hashCode() : 0;
        result = 31 * result + (compteur != null ? compteur.hashCode() : 0);
        result = 31 * result + (dureePin != null ? dureePin.hashCode() : 0);
        return result;
    }
}
