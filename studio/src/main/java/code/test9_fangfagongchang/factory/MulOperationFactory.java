package code.test9_fangfagongchang.factory;


import code.test9_fangfagongchang.operation.MulOperation;
import code.test9_fangfagongchang.operation.Operation;

public class MulOperationFactory implements OperationFactory{
    @Override
    public Operation getOperation() {
        return new MulOperation();
    }
}
