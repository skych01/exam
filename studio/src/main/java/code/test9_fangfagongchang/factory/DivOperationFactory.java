package code.test9_fangfagongchang.factory;


import code.test9_fangfagongchang.operation.DivOperation;
import code.test9_fangfagongchang.operation.Operation;

public class DivOperationFactory implements OperationFactory{
    @Override
    public Operation getOperation() {
        return new DivOperation();
    }
}
