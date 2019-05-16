package car.car_tp1_texier;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class to represent a ftp, call requests
 * 
 * @authors Marine, Celine et Leane
 *
 */
public class FtpRequest extends Thread {
	private Socket connection;
	private Socket data;
	private String filePath;
	private OutputStreamWriter sendRequest;
	private BufferedReader receiveRequest;
	private boolean open;
	private String login;
	private File fileToRename;
	private ServerSocket passif;
	private DataOutputStream dataOutputStream;

	/**
	 * Create a ftpRequest and initialize attributes
	 * 
	 * @param connection the socket linked to the server
	 * @param path       the path of the source directory
	 * @throws IOException
	 */
	public FtpRequest(Socket connection, String path) throws IOException {
		this.connection = connection;
		this.filePath = path;
		this.sendRequest = new OutputStreamWriter(connection.getOutputStream());
		this.receiveRequest = new BufferedReader(
				new InputStreamReader(new BufferedInputStream(connection.getInputStream())));
		this.open = true;
		this.passif = new ServerSocket(0);
	}

	/**
	 * Set the sendRequest's outputStreamWriter
	 * 
	 * @param o the new sendRequest's outputStreamWriter
	 */
	public void setSendRequest(OutputStreamWriter o) {
		this.sendRequest = o;
	}

	/**
	 * Set the connection's socket
	 * 
	 * @param s the new connection's socket
	 */
	public void setConnection(Socket s) {
		this.connection = s;
	}

	/**
	 * Set the data's socket
	 * 
	 * @param s the new data's socket
	 */
	public void setData(Socket s) {
		this.data = s;
	}

	/**
	 * Set the dataOutputStream
	 * 
	 * @param s the new dataOutputStream
	 */
	public void setDataOutputStream(DataOutputStream dos) {
		this.dataOutputStream = dos;
	}

	/**
	 * Run the request if it's open
	 */
	@Override
	public void run() {
		try {
			while (this.open) {
				sendRequest.write("220 Server available\n");
				sendRequest.flush();
				String request;
				while ((request = receiveRequest.readLine()) != null) {
					this.processRequest(request);
				}
			}
		} catch (IOException ex) {
			System.err.println();
		}
	}

	/**
	 * Process the request
	 * 
	 * @param request the request to be processed with arguments when there is
	 * @throws IOException
	 */
	public void processRequest(String request) throws IOException {
		String[] param = request.split("\\s+", 2);
		System.out.println("Execution of : " + request);
		switch (param[0]) {
		case "USER":
			login = param[1];
			this.processUSER(login);
			break;
		case "PASS":
			this.processPASS(login, param[1]);
			break;
		case "RETR":
			this.processRETR(param[1]);
			break;
		case "STOR":
			this.processSTOR(param[1]);
			break;
		case "LIST":
			this.processLIST();
			break;
		case "QUIT":
			this.processQUIT();
			break;
		case "PWD":
			this.processPWD();
			break;
		case "CWD":
			this.processCWD(param[1]);
			break;
		case "CDUP":
			this.processCDUP();
			break;
		case "TYPE":
			this.processTYPE(param[1]);
			break;
		case "PORT":
			this.processPORT(param[1]);
			break;
		case "PASV":
			this.processPASV();
			break;
		case "MKD":
			this.processMKD(param[1]);
			break;
		case "RNFR":
			this.processRNFR(param[1]);
			break;
		case "RNTO":
			this.processRNTO(param[1]);
			break;
		case "RMD":
			this.processRMD(param[1]);
			break;
		case "DELE":
			this.processDELE(param[1]);
			break;
		default:
			this.processNotImplemented();
			break;
		}
	}

	/**
	 * Change the current directory
	 * 
	 * @return a String indicating the state of the command
	 * @throws IOException
	 */
	public String processCDUP() throws IOException {
		return processCWD("..");
	}

	/**
	 * Change the path
	 * 
	 * @param path the name of the new path
	 * @return a String indicating the state of the command
	 * @throws IOException
	 */
	public String processCWD(String path) throws IOException {
		if (path.startsWith(File.separator)) {
			this.filePath = path;
		} else {
			if (!this.filePath.endsWith(File.separator)) {
				this.filePath += File.separator;
			}
			this.filePath += path;
		}
		String res = "200 New path : " + this.filePath + "\n";
		sendRequest.write(res);
		sendRequest.flush();
		return res;
	}

	/**
	 * Delete a file
	 * 
	 * @param filename the filename to be deleted
	 * @return a String indicating the state of the command
	 * @throws IOException
	 */
	public String processDELE(String filename) throws IOException {
		String res;
		File fileToRm = new File(this.filePath + "/" + filename);
		if (fileToRm.exists() && fileToRm.delete()) {
			res = "250 file removed\n";
		} else {
			res = "550 file not removed\n";
		}
		sendRequest.write(res);
		sendRequest.flush();
		return res;
	}

	/**
	 * List the entries of the filepath
	 * 
	 * @return the string indicating the state of the request
	 * @throws IOException
	 */
	public String processLIST() throws IOException {
		String res;
		sendRequest.write("125 OK\n");
		sendRequest.flush();
		dataOutputStream = new DataOutputStream(data.getOutputStream());
		String s = "";
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command("bash", "-c", "ls -la " + filePath);
		Process p = processBuilder.start();
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		// Taille totale
		stdInput.readLine();

		while ((s = stdInput.readLine()) != null) {
			dataOutputStream.writeBytes(s + "\n");
			dataOutputStream.flush();
		}
		dataOutputStream.close();
		data.close();
		res = "226 List OK\n";
		sendRequest.write(res);
		sendRequest.flush();
		return res;
	}

	/**
	 * Create a new directory
	 * 
	 * @param filename the filename of the new directory
	 * @return a String indicating the state of the command
	 * @throws IOException
	 */
	public String processMKD(String filename) throws IOException {
		String res;
		File dirToCreate = new File(this.filePath + "/" + filename);
		if (!dirToCreate.exists() && dirToCreate.mkdir()) {
			res = "257 directory created\n";
		} else {
			res = "550 directory already exists\n";
		}
		sendRequest.write(res);
		sendRequest.flush();
		return res;
	}

	/**
	 * Check if the password corresponds to the login in the database
	 * 
	 * @param login    login
	 * @param password the password entered
	 * @return a String indicating the state of the command
	 * 
	 * @throws IOException
	 */
	public String processPASS(String login, String password) throws IOException {
		String res;
		if (UsersPasswords.passwordIsOk(login, password)) {
			res = "230 User connected\n";
		} else {
			res = "430 Incorrect password\n";
		}
		sendRequest.write(res);
		sendRequest.flush();
		return res;
	}

	/**
	 * Set the mode to passif
	 * 
	 * @return a String indicating the state of the command
	 * @throws IOException
	 */
	public String processPASV() throws IOException {
		String res;
		int port = this.passif.getLocalPort();
		int a = port / 256;
		int b = port % 256;
		res = "227 Entering passive mode (127,0,0,1," + a + "," + b + ")\n";
		sendRequest.write(res);
		sendRequest.flush();
		this.data = this.passif.accept();
		return res;
	}

	/**
	 * Define Ip adress and port
	 * 
	 * @param string informations to get the address and port
	 * @return a String indicating the state of the command
	 * @throws IOException
	 */
	public String processPORT(String s) throws IOException {
		String res = "";
		String[] ipPort = s.split(",");
		String adress = ipPort[0] + "." + ipPort[1] + "." + ipPort[2] + "." + ipPort[3];
		int port = Integer.parseInt(ipPort[4]) * 256 + Integer.parseInt(ipPort[5]);
		System.out.println("ip : " + adress + " port : " + port);
		try {
			this.data = new Socket(adress, port);
			res = "200 Connection port" + port + "\n";
		} catch (Exception e) {
			res = "550 Request Not Executed";
		}
		sendRequest.write(res);
		sendRequest.flush();
		return res;
	}

	/**
	 * Print current path
	 * 
	 * @return the string indicating the state of the command
	 * @throws IOException
	 */
	public String processPWD() throws IOException {
		String res = "257 \"" + filePath + "\" is current path\n";
		sendRequest.write(res);
		sendRequest.flush();
		return res;
	}

	/**
	 * Close the connection
	 * 
	 * @return the string indicating the state of the request
	 * @throws IOException
	 *
	 */
	public String processQUIT() throws IOException {
		String res;
		res = "221 Deconnexion\n";
		sendRequest.write(res);
		sendRequest.flush();
		try {
			connection.close();
		} catch (IOException e) {
			res = "500 The requested action did not take place\n";
			sendRequest.write(res);
			sendRequest.flush();
		}
		this.open = false;
		return res;
	}

	/**
	 * Retrieve a copy of the file
	 * 
	 * @param filename the filename of the file to be retrieved
	 * @return the string indicating the state of the request
	 * @throws IOException
	 * 
	 */
	public String processRETR(String filename) throws IOException {
		String res;
		sendRequest.write("125 start\n");
		sendRequest.flush();
		try {
			dataOutputStream = new DataOutputStream(data.getOutputStream());
			File fileToTransfer = new File(this.filePath + "/" + filename);
			if (fileToTransfer.isFile() || fileToTransfer.isDirectory()) {
				InputStream depart = new FileInputStream(this.filePath + "/" + filename);
				while (depart.available() > 0) {
					dataOutputStream.write(depart.read());
				}
				depart.close();
				res = "226 Transfert complete\n";
			} else {
				res = "500 Transfer not complete\n";
			}
			dataOutputStream.close();
			data.close();
		} catch (Exception e) {
			res = "500 The requested action did not take place\n";
		}
		sendRequest.write(res);
		sendRequest.flush();
		return res;
	}

	/**
	 * Delete a directory
	 * 
	 * @param filename the filename to be deleted
	 * @return a String indicating the state of the command
	 * @throws IOException
	 */
	public String processRMD(String filename) throws IOException {
		String res;
		File dirToRm = new File(this.filePath + "/" + filename);
		if (dirToRm.exists() && dirToRm.isDirectory() && dirToRm.delete()) {
			res = "250 directory removed\n";
		} else {
			res = "550 directory not removed\n";
		}
		sendRequest.write(res);
		sendRequest.flush();
		return res;
	}

	/**
	 * Save the file to be rename on the server
	 * 
	 * @param filename the old filename
	 * @return a String indicating the state of the command
	 * @throws IOException
	 */
	public String processRNFR(String filename) throws IOException {
		String res;
		this.fileToRename = new File(this.filePath + "/" + filename);
		if (this.fileToRename.exists()) {
			res = "350 file or directory to rename saved\n";
		} else {
			res = "550 impossible the file or the directory do not exists\n";
		}
		sendRequest.write(res);
		sendRequest.flush();
		return res;
	}

	/**
	 * Rename a file on the server
	 * 
	 * @param filename the name of the new filename
	 * @return a String indicating the state of the command
	 * @throws IOException
	 */
	public String processRNTO(String filename) throws IOException {
		String res;
		File renamed = new File(this.filePath + "/" + filename);
		if (this.fileToRename != null && this.fileToRename.renameTo(renamed)) {
			res = "250 file or directory renamed\n";
			this.fileToRename = null;
		} else {
			res = "550 impossible to rename the file or the directory\n";
		}
		sendRequest.write(res);
		sendRequest.flush();
		return res;
	}

	/**
	 * Store the data
	 * 
	 * @param filename the filename of the file to be stored
	 * @return the string indicating the state of the request
	 * @throws IOException
	 * 
	 */
	public String processSTOR(String filename) throws IOException {
		String res;
		int read_bytes;
		try {
			sendRequest.write("125 start\n");
			sendRequest.flush();
			File fileToTransfer = new File(this.filePath + "/" + filename);
			File f = new File(this.filePath);
			f.mkdirs();
			OutputStream arrivee = new FileOutputStream(fileToTransfer);
			InputStream depart = this.data.getInputStream();
			while ((read_bytes = depart.read()) != -1) {
				arrivee.write(read_bytes);
			}
			arrivee.close();
			depart.close();
			data.close();
			res = "226 Transfert complete\n";
		} catch (Exception e) {
			res = "500 The requested action did not take place\n";
		}
		sendRequest.write(res);
		sendRequest.flush();
		return res;
	}

	/**
	 * Define the type of transfer
	 * 
	 * @param string The type of transfer
	 * @return a String indicating the state of the command
	 * @throws IOException
	 */
	public String processTYPE(String string) throws IOException {
		String res = "200 Type set to " + string + "\n";
		sendRequest.write(res);
		sendRequest.flush();
		return res;
	}

	/**
	 * Check if the user is in the database
	 * 
	 * @param login the login of the user
	 * @return a String indicating the state of the command
	 * 
	 * @throws IOException
	 */
	public String processUSER(String login) throws IOException {
		String res;
		if (UsersPasswords.userInBDD(login)) {
			res = "331 User OK, password required\n";
		} else {
			res = "430 User unknown\n";
		}
		sendRequest.write(res);
		sendRequest.flush();
		return res;
	}

	/**
	 * Default process for non implemented requests
	 * 
	 * @throws IOException
	 */
	public String processNotImplemented() throws IOException {
		String res = "504 Not Implemented\n";
		sendRequest.write(res);
		sendRequest.flush();
		return res;
	}

}