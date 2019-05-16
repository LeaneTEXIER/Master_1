package car.car_tp1_texier;

import static org.junit.Assert.assertEquals;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Class to test the ftpRequest class
 * 
 * @authors Marine, Celine et Leane
 *
 */
public class FtpRequestTest {

	private FtpRequest ftp;
	private OutputStreamWriter output;
	private Socket connection;
	private Socket data;
	private DataOutputStream dos;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		ftp = Mockito.mock(FtpRequest.class);
		output = Mockito.mock(OutputStreamWriter.class);
		Mockito.doCallRealMethod().when(ftp).setSendRequest(output);
		ftp.setSendRequest(output);
		connection = Mockito.mock(Socket.class);
		Mockito.doCallRealMethod().when(ftp).setConnection(connection);
		ftp.setConnection(connection);
		data = Mockito.mock(Socket.class);
		Mockito.doCallRealMethod().when(ftp).setData(data);
		ftp.setData(data);
		dos = Mockito.mock(DataOutputStream.class);
		Mockito.doCallRealMethod().when(ftp).setDataOutputStream(dos);
		ftp.setDataOutputStream(dos);
	}

	@Test
	public void testProcessUserOK() throws IOException {
		Mockito.doCallRealMethod().when(ftp).processUSER(Mockito.anyString());
		String reponse = ftp.processUSER("texierl");
		assertEquals("331 User OK, password required\n", reponse);
	}

	@Test
	public void testProcessUserFail() throws IOException {
		Mockito.doCallRealMethod().when(ftp).processUSER(Mockito.anyString());
		String reponse = ftp.processUSER("dumbo");
		assertEquals("430 User unknown\n", reponse);
	}

	@Test
	public void testProcessPassOK() throws IOException {
		Mockito.doCallRealMethod().when(ftp).processPASS(Mockito.anyString(), Mockito.anyString());
		String reponse = ftp.processPASS("user", "password");
		assertEquals("230 User connected\n", reponse);
	}

	@Test
	public void testProcessPassFail() throws IOException {
		Mockito.doCallRealMethod().when(ftp).processPASS(Mockito.anyString(), Mockito.anyString());
		String reponse = ftp.processPASS("user", "pwd");
		assertEquals("430 Incorrect password\n", reponse);
	}

	// @Test
	// public void testProcessListOK() throws IOException {
	// Mockito.doCallRealMethod().when(ftp).processLIST();
	// String reponse = ftp.processLIST();
	// assertEquals("226 List OK\n", reponse);
	// }

	// @Test
	// public void testProcessRetrOK() throws IOException {
	// Mockito.doCallRealMethod().when(ftp).processRETR(Mockito.anyString());
	// String reponse = ftp.processRETR("");
	// assertEquals("226 Transfer OK\n", reponse);
	// }

	@Test
	public void testProcessRetrFail() throws IOException {
		Mockito.doCallRealMethod().when(ftp).processPORT(Mockito.anyString());
		ftp.processPORT("259,64548,655,98,656,955");
		Mockito.doCallRealMethod().when(ftp).processRETR(Mockito.anyString());
		String reponse = ftp.processRETR("");
		assertEquals("500 The requested action did not take place\n", reponse);
	}

	@Test
	public void testProcessPortFail() throws IOException {
		Mockito.doCallRealMethod().when(ftp).processPORT(Mockito.anyString());
		String reponse = ftp.processPORT("259,64548,655,98,656,955");
		assertEquals("550 Request Not Executed", reponse);
	}

//	@Test
//	public void testProcessStorOK() throws IOException {
//		Mockito.doCallRealMethod().when(ftp).processSTOR(Mockito.anyString());
//		String reponse = ftp.processSTOR("newfile");
//		assertEquals("226 Transfert complete\n", reponse);
//	}

	@Test
	public void testProcessStorFail() throws IOException {
		Mockito.doCallRealMethod().when(ftp).processSTOR(Mockito.anyString());
		String reponse = ftp.processSTOR("");
		assertEquals("500 The requested action did not take place\n", reponse);
	}

	@Test
	public void testProcessQuitOK() throws IOException {
		Mockito.doCallRealMethod().when(ftp).processQUIT();
		String reponse = ftp.processQUIT();
		assertEquals("221 Deconnexion\n", reponse);
	}

	@Test
	public void testProcessQuitFail() throws IOException {
		Mockito.doThrow(new IOException()).when(connection).close();
		Mockito.doCallRealMethod().when(ftp).processQUIT();
		String reponse = ftp.processQUIT();
		assertEquals("500 The requested action did not take place\n", reponse);
	}

	@Test
	public void testProcessCWD() throws IOException {
		Mockito.doCallRealMethod().when(ftp).processCWD(Mockito.anyString());
		String path = "/dirToGo";
		String reponse = ftp.processCWD(path);
		assertEquals("200 New path : " + path + "\n", reponse);
	}

	@Test
	public void testProcessCDUP() throws IOException {
		Mockito.doCallRealMethod().when(ftp).processCWD(Mockito.anyString());
		String path = "/dirToGo";
		ftp.processCWD(path);
		Mockito.doCallRealMethod().when(ftp).processCDUP();
		String reponse = ftp.processCDUP();
		assertEquals("200 New path : " + path + "/.." + "\n", reponse);
	}

	@Test
	public void testProcessPWD() throws IOException {
		Mockito.doCallRealMethod().when(ftp).processCWD(Mockito.anyString());
		String path = "/dirToGo";
		ftp.processCWD(path);
		Mockito.doCallRealMethod().when(ftp).processPWD();
		String reponse = ftp.processPWD();
		assertEquals("257 \"" + path + "\" is current path\n", reponse);
	}

	@Test
	public void testProcessTYPE() throws IOException {
		Mockito.doCallRealMethod().when(ftp).processTYPE(Mockito.anyString());
		String type = "I";
		String reponse = ftp.processTYPE(type);
		assertEquals("200 Type set to " + type + "\n", reponse);
	}

	@Test
	public void testProcessNotImplemented() throws IOException {
		Mockito.doCallRealMethod().when(ftp).processNotImplemented();
		String reponse = ftp.processNotImplemented();
		assertEquals("504 Not Implemented\n", reponse);
	}

}
