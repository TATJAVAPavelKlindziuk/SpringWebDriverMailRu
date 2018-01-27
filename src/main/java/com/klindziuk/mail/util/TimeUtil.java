package com.klindziuk.mail.util;

import java.util.concurrent.TimeUnit;

/**
 * Created by Hp on 27/01/2018.
 */
public final class TimeUtil {

    private TimeUtil() {
    }

    public static void pause(int time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

