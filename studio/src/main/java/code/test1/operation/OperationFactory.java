package code.test1.operation;

/**
 * Created by Administrator on 2017/10/16.
 */
public class OperationFactory {
    private OperationFactory(){

    }
    public static Operation createOperation(int operator){
        Operation result=null;
        switch (operator){
            case 1:
                result=new  AddOperation();
                break;
            case 2:
                result = new DelOperation();
                break;
            case 3:
                result = new MulOperation();
                break;
            case 4:
                result = new DivOperation();
                break;
            default:
                try {
                    throw new Exception("不支持的运算，请从 charoperation 选择");
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        return result;
    }

    public  static  enum CharOperation{
ADD("加法",1),DEL("减法",2),MUL("乘法",3),DIV("除法",4);
        // 成员变量
        private String name;
        private int index;
        CharOperation(String name, int index ) {
            this.name = name;
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return index;
        }

        public void setValue(int index) {
            this.index = index;
        }
    }

}
