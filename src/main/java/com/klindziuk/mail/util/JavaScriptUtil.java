package com.klindziuk.mail.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public final class JavaScriptUtil {

	private JavaScriptUtil() {
	}

	public static String getWindowTitle(WebDriver webDriver) {
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		return js.executeScript("return document.title;").toString();
	}

	public static void scrollDownPage(WebDriver webDriver, int x, int y) {
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript(String.format("window.scrollBy(%d,%d)", x, y));
	}

	public static void refresh(WebDriver webDriver) {
		JavascriptExecutor js =  (JavascriptExecutor) webDriver;
		js.executeScript("history.go(0)");
	}

	public static String getDomain(WebDriver webDriver) {
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		return js.executeScript("return document.domain;").toString();
	}

	public static String getPageUrl(WebDriver webDriver) {
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		return js.executeScript("return document.URL;").toString();
	}

}
