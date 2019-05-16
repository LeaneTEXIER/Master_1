/**
 * 
 */
package com.tp2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;

/**
 * @authors Celine, Marine et Leane
 *
 */
public class Ftp {

	private FTPSClient client;

	/**
	 * Create the FTP client and connects it
	 * 
	 * @param port     the port
	 * @param adress   the adress
	 * @param mode     the mode
	 * @param username the username
	 * @param password the password
	 */
	public Ftp(String mode, String adress, int port, String username, String password) {
		this.client = new FTPSClient();
		try {
			this.client.setControlEncoding("UTF-8");
			this.client.connect(adress, port);
			int reply = this.client.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				this.client.disconnect();
				System.out.println("Couldn't connect to " + adress + ", port:  " + port);
				System.exit(1);
				return;
			} else {
				System.out.println("Connected to " + adress + ", port:  " + port);
			}
			this.client.login(username, password);
			if (mode.equals("actif")) {
				this.client.enterLocalActiveMode();
			} else {
				this.client.enterLocalPassiveMode();
			}
			System.out.println("Mode set to " + mode);
			this.client.execPBSZ(0);
			this.client.execPROT("P");
			this.client.type(FTP.BINARY_FILE_TYPE);
			this.client.setFileType(FTP.BINARY_FILE_TYPE);

			reply = this.client.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				this.client.disconnect();
				System.out.println("Wrong login or password");
				System.exit(1);
				return;
			} else {
				System.out.println(username + " connected !");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Print current directory
	 * 
	 * @return the string indicating the current directory
	 */
	public String pwd() {
		try {
			String path = this.client.printWorkingDirectory();
			return path;
		} catch (IOException e) {
			e.printStackTrace();
			return "Error";
		}
	}

	/**
	 * Print the directories and files. If pathname is null, prints from "/"
	 * 
	 * @param pathname the pathname
	 * @return a string in html with the directories and files in the pathname
	 */
	public String ls(String pathname) {
		try {
			String res = "<ul>";
			FTPFile[] files = this.client.listFiles(pathname);
			if (!pathname.equals("") && !pathname.equals("/") && !pathname.equals(null)) {
				int p = pathname.lastIndexOf("/");
				String s = pathname.substring(0, p);
				res += "<li> <a href=\"http://localhost:8080/myapp/myresource/ls/" + s + "\">..</a></li>";
			}
			for (FTPFile ftpFile : files) {
				if (!ftpFile.isFile()) {
					res += "<li> <a href=\"http://localhost:8080/myapp/myresource/ls/" + pathname + "/"
							+ ftpFile.getName() + "\">" + ftpFile.getName() + "</a></li>";
				} else {
					res += "<li>" + ftpFile.getName() + "</li>";
				}
			}
			res += "</ul>";
			return res;
		} catch (IOException e) {
			e.printStackTrace();
			return "Error";
		}
	}

	/**
	 * Delete a file
	 * 
	 * @param pathname the path of the file
	 * @return a string indicating what happened
	 */
	public String deleteFile(String pathname) {
		try {
			if (this.client.deleteFile(pathname)) {
				return "File deleted";
			} else {
				return "Could not delete file";
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "Error while deleting file";
		}
	}

	/**
	 * Create a directory
	 * 
	 * @param directory the name of the directory
	 * @return a string indicating what happened
	 */
	public String createDirectory(String directory) {
		try {
			if (this.client.makeDirectory(directory)) {
				return "Directory created";
			} else {
				return "Directory already existed";
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "Error while creating directory";
		}
	}

	/**
	 * Delete a directory
	 * 
	 * @param pathname the name of the directory
	 * @return a string indicating what happened
	 */
	public String deleteDirectory(String pathname) {
		try {
			deleteDirectoryRec(pathname);
			return "Directory deleted";

		} catch (IOException e) {
			e.printStackTrace();
			return "Error while deleting directory";
		}
	}

	public void deleteDirectoryRec(String pathname) throws IOException {
		FTPFile[] files = this.client.listFiles(pathname);
		for (FTPFile ftpFile : files) {
			if (ftpFile.isDirectory()) {
				deleteDirectoryRec(pathname + "/" + ftpFile.getName());
				this.client.removeDirectory(pathname + "/" + ftpFile.getName());
			} else {
				String deleteFilePath = pathname + "/" + ftpFile.getName();
				this.client.deleteFile(deleteFilePath);
			}
		}
		this.client.removeDirectory(pathname);
	}

	/**
	 * Rename a file or a directory
	 * 
	 * @param file    the file or the directory
	 * @param newName the new name
	 * @return a string indicating what happened
	 */
	public String rename(String file, String newName) {
		try {
			boolean renamed = this.client.rename(file, newName);
			if (renamed) {
				return "File or directory renamed";
			} else {
				return "Could not rename file or directory";
			}

		} catch (IOException e) {
			e.printStackTrace();
			return "Error while renaming file or directory";
		}
	}

	/**
	 * Upload a file or a directory
	 * 
	 * @param name the file or the directory
	 * @return a string indicating what happened
	 */
	public String upload(String name) {
		try {
			File file = new File(name);
			if (!file.exists()) {
				return "File or directory doesn't exist";
			} else {
				uploadRec(name);
				return "File or directory upload";
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "Error while uploading file";
		}
	}

	/**
	 * Upload a file or the directory recursively
	 * 
	 * @param pathname the file or the directory
	 */
	public void uploadRec(String pathname) throws IOException {
		File file = new File(pathname);
		if (file.isDirectory()) {
			int p = pathname.lastIndexOf("/");
			String d = pathname.substring(p + 1);
			if (!this.client.makeDirectory(d)) {
				throw new IOException();
			}
			this.client.changeWorkingDirectory(d);
			File[] files = file.listFiles();
			for (File f : files) {
				String name = f.getName();
				uploadRec(pathname + "/" + name);
			}
			this.client.changeToParentDirectory();
		} else {
			InputStream in = new FileInputStream(pathname);
			if (this.client.storeFile(file.getName(), in)) {
				in.close();
			} else {
				in.close();
				throw new IOException();
			}
		}
	}

	/**
	 * Download a file or a directory from name to user.home/pathname
	 * 
	 * @param name     the path of the file or the directory
	 * @param pathname the pathname where we want to download the file or the
	 *                 directory
	 * @return a string indicating what happened
	 */
	public String download(String name, String pathname) {
		try {
			downloadRec(name, pathname);
			return "File or directory download";
		} catch (Exception e) {
			return "Error while retrieving file";
		}
	}

	/**
	 * Download a file or a directory from name to user.home/pathname recursively
	 * 
	 * @param name     the path of the file or the directory
	 * @param pathname the pathname where we want to download the file or the
	 *                 directory
	 * @throws IOException
	 */
	public void downloadRec(String name, String pathname) throws IOException {
		File file = new File(name);
		String path = System.getProperty("user.home") + "/" + pathname + "/" + file.getName();
		if (isDirectory(name)) {
			File newDir = new File(path);
			newDir.mkdirs();

			FTPFile[] files = this.client.listFiles(name);
			for (FTPFile f : files) {
				String n = f.getName();
				downloadRec(name + "/" + n, pathname + "/" + file.getName());
			}
		} else {
			OutputStream out = new FileOutputStream(path);

			if (this.client.retrieveFile(name, out)) {
				out.close();

			} else {
				out.close();
			}
		}

	}

	public boolean isDirectory(String name) throws IOException {
		String pwd = this.client.printWorkingDirectory();
		boolean directoryExists = this.client.changeWorkingDirectory(name);
		this.client.changeWorkingDirectory(pwd);
		return directoryExists;
	}

}
