package com.klindziuk.mail.util;

import com.klindziuk.mail.model.User;
import org.testng.annotations.DataProvider;

/**
 * Created by Hp on 23/01/2018.
 */
public class DataProviderManager {

    @DataProvider
    public static Object[][] userDataProvider() {
        return new Object[][] {  { new User("cdptraining", "1practice1") } };
    }
}
