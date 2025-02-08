package itu.p16.crypto.firebase.firestore.users;

import com.google.cloud.Timestamp;
import itu.p16.crypto.entity.Users;
import itu.p16.crypto.firebase.firestore.generalisation.TimestampedDocument;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
public class UsersDocument implements TimestampedDocument {
    private Long idUsers;
    private String email;
    private String username;
    private String password;
    private Integer idRole;
    private boolean is_sync_from_firestore;

    private String createdAt;
    private String updatedAt;

    public UsersDocument(Users user) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.idUsers = user.getIdUsers();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.idRole = user.getIdRole();
        this.is_sync_from_firestore = user.isSyncFromFirestore();

        this.createdAt = dateFormat.format(new Date());
        this.updatedAt = dateFormat.format(new Date());
    }

    public Users toEntity() {
        Users user = new Users();
        user.setIdUsers(idUsers);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setIdRole(idRole);
        user.setSyncFromFirestore(is_sync_from_firestore);
        return user;
    }

    public static UsersDocument fromMap(Map<String, Object> map) {
        UsersDocument document = new UsersDocument();
        document.setEmail((String) map.get("email"));
        document.setUsername((String) map.get("username"));
        document.setPassword((String) map.get("password"));

        Object id = map.get("id_users");
        if (id instanceof Number) {
            document.setIdUsers(((Number) id).longValue());
        }
        Object idRole = map.get("id_role");
        if (id instanceof Number) {
            document.setIdRole((int) ((Number) idRole).longValue());
        }


        return document;
    }

}
