package com.klindziuk.mail.util;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public final class JavaScriptUtil {
	private static final Logger LOGGER = Logger.getLogger(JavaScriptUtil.class);

	private JavaScriptUtil() {
	}

	public static String getWindowTitle(WebDriver webDriver) {
		LOGGER.info("Get window title");
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		return js.executeScript("return document.title;").toString();
	}

	public static void scrollDownPage(WebDriver webDriver, int x, int y) {
		LOGGER.info(String.format("Scroll by (%d,%d)", x, y));
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript(String.format("window.scrollBy(%d,%d)", x, y));
	}

	public static void refresh(WebDriver webDriver) {
		LOGGER.info("Refreshing page");
		JavascriptExecutor js =  (JavascriptExecutor) webDriver;
		js.executeScript("history.go(0)");
	}

	public static String getDomain(WebDriver webDriver) {
		LOGGER.info("Get document domain");
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		return js.executeScript("return document.domain;").toString();
	}

	public static String getPageUrl(WebDriver webDriver) {
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		String url = js.executeScript("return document.URL;").toString();
		LOGGER.trace(String.format("Get page url - '%s' ",url));
		return url;
	}

}
