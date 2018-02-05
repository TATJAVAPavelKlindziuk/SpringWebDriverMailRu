package com.klindziuk.mail.title;

import com.klindziuk.mail.pageobject.BasePage;

/**
 * Created by Hp on 05/02/2018.
 */
public class PageTitle {
    private BasePage basePage;

    public PageTitle(BasePage basePage) {
        this.basePage = basePage;
    }

    public String getPageTitle(){
        return basePage.getTitle();
    }
}
