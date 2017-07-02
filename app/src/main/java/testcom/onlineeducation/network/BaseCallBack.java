package testcom.onlineeducation.network;

/**
 * Created by Administrator on 2017/6/15.
 */
public abstract class BaseCallBack {
   //成功回调
   public abstract void success(Object data);


   //失败回调
   public abstract void failed(int errorCode,Object data);


   public abstract void failed(Object data);
}
