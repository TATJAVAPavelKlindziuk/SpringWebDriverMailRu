package com.klindziuk.mail.util;

import com.klindziuk.mail.model.User;
import org.testng.annotations.DataProvider;

public class DataProviderManager {

    @DataProvider
    public static Object[][] userDataProvider() {
        return new Object[][] {  { new User("cdptraining", "1practice1") } };
    }
}
