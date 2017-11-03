package code.test9_fangfagongchang.factory;


import code.test9_fangfagongchang.operation.AddOperation;
import code.test9_fangfagongchang.operation.DelOperation;
import code.test9_fangfagongchang.operation.Operation;

public class DelOperationFactory implements OperationFactory{
    @Override
    public Operation getOperation() {
        return new DelOperation();
    }
}
