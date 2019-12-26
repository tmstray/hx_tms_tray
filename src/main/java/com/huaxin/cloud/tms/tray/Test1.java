package com.huaxin.cloud.tms.tray;

import com.huaxin.cloud.tms.tray.common.constant.Constants;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String token="aa";
		token = token.replace(Constants.TOKEN_PREFIX, "");

		System.out.println(token);
	}

}
