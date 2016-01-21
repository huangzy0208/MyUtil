package com.custom.common.callutil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 调用工具类 提供部分调用逻辑封装
 *
 * @author zhangmeng
 * @date 2015/10/26,14:51
 */

public class CallUtil {

    private final static Logger log = LoggerFactory.getLogger(CallUtil.class);

    /**
     * 循环调用函数体，直到最大次数
     *
     * @param call     调用实现
     * @param callCnt  调用次数
     * @param waitTime 等待时间
     */
    public static void cycleCall(CallBackInterface call, int callCnt, long waitTime, List<Object> args) {
        for (int i = 0; i < callCnt; i++) {
            String callMsg = call.callMethod(args);
            if (StringUtils.isEmpty(callMsg)) {
                call.onSuccess(args);
            } else {
                call.onError(args, callMsg);
            }
            callWait(waitTime);
        }
    }

    /**
     * 循环调用函数体，直到最大次数或者函数调用成功
     *
     * @param call     调用实现
     * @param callCnt  调用次数
     * @param waitTime 等待时间
     */
    public static void successCycleCall(CallBackInterface call, int callCnt, long waitTime, List<Object> args) throws InterruptedException {
        for (int i = 0; i < callCnt; i++) {
            String callMsg = call.callMethod(args);
            if (StringUtils.isEmpty(callMsg)) {
                break;
            } else {
                call.onError(args, callMsg);
            }
            callWait(waitTime);
        }
    }

    /**
     * 循环调用函数体，直到最大次数或者函数调用成功
     *
     * @param call    调用实现
     * @param callCnt 调用次数
     */
    public static final void successCycleCall(CallBackInterface call, int callCnt, List<Object> args) throws InterruptedException {
        successCycleCall(call, callCnt, 0, args);
    }

    /**
     * 等待一段时间
     *
     * @param waitTime 等待时间
     */
    public static void callWait(long waitTime) {
        if (waitTime > 0) {
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                log.error("sleep线程被中断", e);
            }
        }
    }
}
