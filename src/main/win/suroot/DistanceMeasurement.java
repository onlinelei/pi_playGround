package main.win.suroot;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.trigger.GpioCallbackTrigger;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author qianlei
 * @create 2019-09-30 18:39
 * @desc 测量距离
 */
public class DistanceMeasurement {


    public static void main(String[] args) {

        final GpioController gpioController = GpioFactory.getInstance();
        final GpioPinDigitalOutput send = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_04, PinState.LOW);
        final GpioPinDigitalInput accepte = gpioController.provisionDigitalInputPin(RaspiPin.GPIO_05, PinPullResistance.PULL_DOWN);
        final Long star = null;


        // 绑定监听器记录收到信号时间
        accepte.addTrigger(new GpioCallbackTrigger(new Callable<Void>() {
            public Void call() throws Exception {
                long end = System.nanoTime();
                // 计算距离 TODO
                long consume = star.longValue() - end;

                return null;
            }
        }));


        while (Boolean.TRUE) {

            // 发送10微妙的高电平
            send.pulse(10, TimeUnit.MICROSECONDS);

            // 获取当前时间 微妙
            long millis = System.currentTimeMillis();
            long nanoTime = System.nanoTime();

            star.longValue() = System.nanoTime();


        }


    }
}
