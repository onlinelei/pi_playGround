package main.win.suroot;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.gpio.trigger.GpioCallbackTrigger;
import com.pi4j.jni.SerialInterruptEvent;
import com.pi4j.jni.SerialInterruptListener;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author qianlei
 * @create 2019-09-30 18:39
 * @desc 测量距离
 */
public class DistanceMeasurement {

    static long start;
    static final GpioController gpioController = GpioFactory.getInstance();
    static final GpioPinDigitalOutput send = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_04, PinState.LOW);
    static final GpioPinDigitalInput accepter = gpioController.provisionDigitalInputPin(RaspiPin.GPIO_05, PinPullResistance.PULL_DOWN);

    public static void main(String[] args) throws InterruptedException {

        // 绑定监听器 收到信号处理
        accepter.addTrigger(new GpioCallbackTrigger(new Callable<Void>() {
            public Void call() throws Exception {
                long end = System.nanoTime();
                // 计算距离 TODO
                /**
                 * 超声波速度 343m/s 34cm/us
                 */
                long consume = end - start;

                System.out.println("time：" + consume + "distance:" + consume/1000 * 34);
                return null;
            }
        }));

        accepter.addListener(new GpioPinListenerDigital() {
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                event.getState();
            }
        });


        while (Boolean.TRUE){
            // 切换高低电平
            if (send.isLow()){
                send.high();
                Thread.sleep(10);
                send.low();
                Thread.sleep(5000);
            }
            start = System.nanoTime();
        }
    }
}
