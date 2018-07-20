package com.charhoo.springlearn.tx;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * spring事务
 */
public class BookService {

    private PlatformTransactionManager transactionManager;

    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void serviceMethod() {
        /**
         * 定义事务：隔离级别，传播行为，超时，是否只读等
         */
        TransactionDefinition definition = new DefaultTransactionDefinition();
        TransactionStatus txStatus = getTransactionManager().getTransaction(definition);
        try {
            // dao1.doDataAccess();
            // dao2.doDataAccess();
            // ...
        } catch(DataAccessException e) {
            getTransactionManager().rollback(txStatus);
            throw e;
        }
        getTransactionManager().commit(txStatus);
    }


}
