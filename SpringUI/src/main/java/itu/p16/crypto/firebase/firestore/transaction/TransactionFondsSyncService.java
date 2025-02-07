package itu.p16.crypto.firebase.firestore.transaction;

import com.google.cloud.firestore.Firestore;
import itu.p16.crypto.entity.TransactionFonds;
import itu.p16.crypto.firebase.firestore.generalisation.GenericSyncService;
import itu.p16.crypto.service.TransactionFondsService;
import org.springframework.stereotype.Service;

@Service
public class TransactionFondsSyncService extends GenericSyncService<TransactionFonds, TransactionFondsDocument> {

    public TransactionFondsSyncService(Firestore firestore, TransactionFondsService transactionFondsService) {
        super(firestore, transactionFondsService, "transaction_fonds");
    }

    @Override
    protected TransactionFondsDocument toDocument(TransactionFonds entity) {
        return new TransactionFondsDocument(entity);
    }

    @Override
    protected TransactionFonds toEntity(TransactionFondsDocument document) {
        return document.toEntity();
    }

    @Override
    protected String getEntityId(TransactionFonds entity) {
        return entity.getIdTransactionFonds().toString();
    }

    @Override
    protected Class<TransactionFondsDocument> getDocumentClass() {
        return TransactionFondsDocument.class;
    }
}
