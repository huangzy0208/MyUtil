package com.custom.common.callutil;

import java.util.List;

/**
 * 回调接口
 *
 * @author zhangmeng
 * @date 2015/10/26,14:46
 */

public interface CallBackInterface {
    /**
     * @return
     */
    String callMethod(List<Object> args);

    /**
     * 调用成功的回调
     */
    void onSuccess(List<Object> args);

    /**
     * 调用失败的回调
     */
    void onError(List<Object> args, String callMsg);
}
