package code.test9_fangfagongchang.factory;


import code.test9_fangfagongchang.operation.AddOperation;
import code.test9_fangfagongchang.operation.Operation;

public class addOperationFactory implements OperationFactory{
    @Override
    public Operation getOperation() {
        return new AddOperation();
    }
}
