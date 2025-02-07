package itu.p16.crypto.firebase.listener.firestore;

import com.google.cloud.firestore.DocumentChange;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import itu.p16.crypto.entity.Users;
import itu.p16.crypto.firebase.firestore.users.UsersDocument;
import itu.p16.crypto.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserFirestoreListener {

    private final Firestore firestore;
    private final UsersRepository userRepository;

    @PostConstruct
    public void listenForChanges() {
//        System.out.println("🔥 Firestore Listener pour 'users' démarré...");
        firestore.collection("users")
                .addSnapshotListener((querySnapshot, e) -> {
                    if (e != null) {
                        System.out.println("❌ Listen failed: " + e);
                        return;
                    }
//                    System.out.println("📌 Firestore a détecté une modification...");

                    for (DocumentChange documentChange : querySnapshot.getDocumentChanges()) {
//                        System.out.println("🔄 Changement détecté : " + documentChange.getType());

                        switch (documentChange.getType()) {
                            case ADDED:
                                handleAddedDocument(documentChange.getDocument());
                                break;
                            case MODIFIED:
                                handleModifiedDocument(documentChange.getDocument());
                                break;
                            case REMOVED:
                                handleRemovedDocument(documentChange.getDocument());
                                break;
                        }
                    }
                });
    }


    // Gérer l'ajout d'un document
    private void handleAddedDocument(DocumentSnapshot documentSnapshot) {
        UsersDocument document = documentSnapshot.toObject(UsersDocument.class);
        Users user = convertToEntity(document);

        if (!user.isSyncFromFirestore()) {
            user.setIdTentatives(1);
            user.setIdRole(2);
            user.setSyncFromFirestore(true);
            userRepository.save(user);
        }
    }

    // Gérer la modification d'un document
    private void handleModifiedDocument(DocumentSnapshot documentSnapshot) {
        UsersDocument document = documentSnapshot.toObject(UsersDocument.class);
        Users user = convertToEntity(document);

        Optional<Users> existingUser = userRepository.findById(Math.toIntExact(user.getIdUsers()));
        if (existingUser.isPresent()) {
            Users existing = existingUser.get();

            if (!existing.equals(user) && !user.isSyncFromFirestore()) {
                user.setSyncFromFirestore(true);
                user.setIdTentatives(1);
                user.setIdRole(2);
                userRepository.save(user);  
            }
        }
    }

    // Gérer la suppression d'un document
    private void handleRemovedDocument(DocumentSnapshot documentSnapshot) {
        String userId = documentSnapshot.getId();
        userRepository.deleteById(Integer.valueOf(userId));
    }

    // Convertir le document Firestore en entité JPA
    private Users convertToEntity(UsersDocument document) {
        return document.toEntity();
    }
}
