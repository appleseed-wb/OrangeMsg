package com.orange.msg.dispatcher;

/**
 * 消息动作调用
 */
public class ActionDispatcher {

    private AbstractAction action;

    public ActionDispatcher(){
        super();
    }

    public ActionDispatcher(AbstractAction action){
        this.action = action;
    }

    public void dispatch(){
        try {
            this.action.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAction(AbstractAction action) {
        this.action = action;
    }
}
