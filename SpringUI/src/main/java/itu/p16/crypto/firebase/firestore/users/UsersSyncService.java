package itu.p16.crypto.firebase.firestore.users;

import com.google.cloud.firestore.Firestore;
import itu.p16.crypto.entity.Users;
import itu.p16.crypto.firebase.firestore.generalisation.GenericSyncService;
import itu.p16.crypto.service.UsersService;
import org.springframework.stereotype.Service;

@Service
public class UsersSyncService extends GenericSyncService<Users, UsersDocument> {

    public UsersSyncService(Firestore firestore, UsersService usersService) {
        super(firestore, usersService, "users");
    }

    @Override
    protected UsersDocument toDocument(Users entity) {
        return new UsersDocument(entity);
    }

    @Override
    protected Users toEntity(UsersDocument document) {
        return document.toEntity();
    }

    @Override
    protected String getEntityId(Users entity) {
        return entity.getIdUsers().toString();
    }

    @Override
    protected Class<UsersDocument> getDocumentClass() {
        return UsersDocument.class;
    }
}
