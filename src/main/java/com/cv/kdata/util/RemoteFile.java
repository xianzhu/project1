package com.cv.kdata.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class RemoteFile {
	private String username;
	private String password;
	private String address;
	private static String ftpProtocal = "ftp://%s:%s@%s%s"; // "ftp://user:pwd@172.16.31.69/aaa.txt"

	public boolean readFile(String file, OutputStream outputStream) {

		String url = String.format(ftpProtocal, username, password, address, file);
		try {
			// URL urlfile = new URL("ftp://user:pwd@172.16.31.69/aaa.txt");
			URL urlFile = new URL(url);

			InputStream inputStream = urlFile.openStream();
			if (inputStream == null) {
				return false;
			}
			byte[] buffer = new byte[1024];
			int i = -1;
			while ((i = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, i);
			}
			outputStream.flush();
			outputStream.close();
			inputStream.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean ftpReadFile(String file, OutputStream outputStream) {
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			// 1.连接服务器
			ftp.connect(address);
			// 2.登录服务器 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);
			// 3.判断登陆是否成功
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return false;
			}

			file = new String(file.getBytes(),FTP.DEFAULT_CONTROL_ENCODING);
			InputStream inputStream = ftp.retrieveFileStream(file);
			if (inputStream == null) {
				return false;
			}
			byte[] buffer = new byte[1024];
			int i = -1;
			while ((i = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, i);
			}

			outputStream.flush();
			outputStream.close();
			inputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return true;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public static void main(String args[]) {
		RemoteFile file = new RemoteFile();
		file.setAddress("116.62.42.50");
		file.setPassword("1QAZ3edc2WSX");
		file.setUsername("appuser");
		String path = "/home/appuser/workdir/report/cv/2015年中国IPO市场十大事件.pdf";
//		String path = "/home/appuser/workdir/report/cv/2015年中国IPO市场十大事件.pdf";

		try {
			path = new String(path.getBytes(),FTP.DEFAULT_CONTROL_ENCODING);
			OutputStream outputStream = new FileOutputStream(new File("C:/dev/test.pdf"));
//			file.readFile(path, outputStream);
			file.ftpReadFile(path, outputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
