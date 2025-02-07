package itu.p16.crypto.firebase.firestore.transactioncrypto;

import com.google.cloud.firestore.Firestore;
import itu.p16.crypto.entity.TransactionCrypto;
import itu.p16.crypto.firebase.firestore.generalisation.GenericSyncService;
import itu.p16.crypto.service.TransactionCryptoService;
import org.springframework.stereotype.Service;

@Service
public class TransactionCryptoSyncService extends GenericSyncService<TransactionCrypto, TransactionCryptoDocument> {

    public TransactionCryptoSyncService(Firestore firestore, TransactionCryptoService transactionCryptoService) {
        super(firestore, transactionCryptoService, "transaction_crypto");
    }

    @Override
    protected TransactionCryptoDocument toDocument(TransactionCrypto entity) {
        return new TransactionCryptoDocument(entity);
    }

    @Override
    protected TransactionCrypto toEntity(TransactionCryptoDocument document) {
        return document.toEntity();
    }

    @Override
    protected String getEntityId(TransactionCrypto entity) {
        return entity.getIdTransactionCrypto().toString();
    }

    @Override
    protected Class<TransactionCryptoDocument> getDocumentClass() {
        return TransactionCryptoDocument.class;
    }
}
