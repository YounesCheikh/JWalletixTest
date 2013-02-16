import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.payment.wltx.Walletix;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author Cheikh Younes
 * 
 */
public class Main {

	private final String VENDORID;
	private final String APIKEY;
	private Walletix w = null;

	public Main() {
		String[] info = loadVendorInfo();
		VENDORID = info[0];
		APIKEY = info[1];
		w = new Walletix(VENDORID, APIKEY, true);
	}

	public Walletix getWalletix() {
		return this.w;
	}
	private String[] loadVendorInfo() {
		String ret[] = { "", "" };
		try {
			InputStream is = new FileInputStream("walletixinfo.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(is);
			doc.getDocumentElement().normalize();
			NodeList nodeLst = doc.getElementsByTagName("vendorInfo");
			Node fstNode = nodeLst.item(0);

			if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

				Element fstElmnt = (Element) fstNode;
				NodeList fstNmElmntLst = fstElmnt
						.getElementsByTagName("vendorId");
				Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
				NodeList fstNm = fstNmElmnt.getChildNodes();
				ret[0] = ((Node) fstNm.item(0)).getNodeValue();
				NodeList lstNmElmntLst = fstElmnt
						.getElementsByTagName("apiKey");
				Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
				NodeList lstNm = lstNmElmnt.getChildNodes();
				ret[1] = ((Node) lstNm.item(0)).getNodeValue();
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			generateWltxInfoFile();
			JOptionPane
					.showMessageDialog(
							null,
							"Le fichier walletixinfo.xml est introuvable!\n"
									+ "Le programme à généré un fichier walletixinfo.xml\n"
									+ "Merci de le completer puis essayer d'ouvrir le programme a nouveau.",
							"Fatal Error", JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
		return ret;
	}

	private void generateWltxInfoFile() {
		String fstream = "walletixinfo.xml";
		String content = "<vendorInfo>\n"
				+ "<vendorId>your vendor id </vendorId>\n"
				+ "<apiKey>your api key</apiKey>\n</vendorInfo>\n";
		File file = new File(fstream);
		FileWriter fw;
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
		} catch (IOException e1) {
			System.err.println(e1.getMessage());
		}
	}

	public static void main(String[] args) {
		new Main();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main m = new Main();
					WltxWindow window = new WltxWindow(m.getWalletix());
					window.getFrmWalletixTest().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
