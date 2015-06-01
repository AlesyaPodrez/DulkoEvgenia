package com.springapp.mvc.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

import org.springframework.web.multipart.MultipartFile;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
import com.dropbox.core.DbxWriteMode;

public class Dropbox {

	private static final String APP_KEY = "ity220j3612xyu9";
	private static final String APP_SECRET = "n2b0v7radi8t2el";
	private static final String ACCESS_TOKEN = "LC6256kukzkAAAAAAAAA2K19HKiZ90LEqeyTQSvVaMxgF9ISGloqA2nLwSRCF5l5";

	private DbxClient client;

	public Dropbox() {

		DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

		DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0",
				Locale.getDefault().toString());
		DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
		client = new DbxClient(config, ACCESS_TOKEN);
	}
	
	

	public String UploadingFile(MultipartFile file) throws DbxException,
			IOException {
		FileInputStream inputStream = (FileInputStream) file.getInputStream();
		try {
			DbxEntry.File uploadedFile = client.uploadFile("/".concat(file.getOriginalFilename()),
					DbxWriteMode.add(), file.getSize(), inputStream);
			String url = client.createShareableUrl(uploadedFile.path);
			return url.substring(0, url.length()-5).concat("?raw=1");
		} finally {
			inputStream.close();
		}
	}

}
