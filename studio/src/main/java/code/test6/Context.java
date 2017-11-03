package code.test6;

import code.test6.state.IState;

/**
 * Created by Administrator on 2017/10/23.
 */
public class Context {
    private IState iState;

    public Context(IState iState) {
        this.iState = iState;
    }

    public IState getiState() {
        return iState;
    }

    public void setiState(IState iState) {
        this.iState = iState;
    }

    public void request(){
        iState.handle(this);
    }
}
