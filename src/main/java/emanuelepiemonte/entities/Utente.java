package emanuelepiemonte.entities;

import emanuelepiemonte.enums.Sesso;
import emanuelepiemonte.enums.UserType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "utenti")
public class Utente {
    @Id
    @GeneratedValue
    @Column(name = "utente_id")
    private UUID utenteId;
    private String nome;
    private String cognome;
    @Enumerated(EnumType.STRING)
    private Sesso sesso;
    private LocalDate data_di_nascita;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;
    private String pw;
    private String username;

    public Utente() {
    }

    public Utente(String nome, String cognome, Sesso sesso, LocalDate data_di_nascita, UserType userType, String pw) {
        this.nome = nome;
        this.cognome = cognome;
        this.sesso = sesso;
        this.data_di_nascita = data_di_nascita;
        this.pw = pw;
        this.userType = userType;
        this.username = (nome + cognome).toLowerCase();
    }

    public UUID getUtenteId() {
        return utenteId;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public Sesso getSesso() {
        return sesso;
    }

    public LocalDate getData_di_nascita() {
        return data_di_nascita;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "utenteId=" + utenteId +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", sesso=" + sesso +
                ", data_di_nascita=" + data_di_nascita +
                '}';
    }
}
