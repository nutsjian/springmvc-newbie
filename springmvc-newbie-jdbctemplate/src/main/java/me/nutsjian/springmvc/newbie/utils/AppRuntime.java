package me.nutsjian.springmvc.newbie.utils;

public class AppRuntime {

	private static String productRootFolder;
	private static String productHomeFolder;

	/**
	 * 获取"emba_home"上一层目录
	 */
	public static String getProductRootFolder() {
		return productRootFolder;
	}

	/**
	 * 获取"emba_home"目录的实际路径
	 */
	public static String getProductHomeFolder() {
		return productHomeFolder;
	}

}
