package com.junyi.declarative.transaction.demo;

public interface FooService {
    public void insertRecord();
    public void insertThenRollback() throws RollbackException;
    public void invokeInsertThenRollback() throws RollbackException;
}
