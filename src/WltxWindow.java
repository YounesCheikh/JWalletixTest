import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.payment.wltx.Walletix;

/**
 * 
 * @author Cheikh Younes
 * 
 */
public class WltxWindow {

	private JFrame frmWalletixTest;
	private JTextField txtDzd;
	private JTextField txtDzd_1;
	private JTextField txtDzd_2;
	private JTextField textField;
	private JButton btnCommander;
	private final int prixProduit1 = 20;
	private final int prixProduit2 = 50;
	private final int prixProduit3 = 100;
	private final static String callbackURL = "http://cyounes.com/thanks/";
	private JToggleButton tglbtnAjouterAuPanier, tglbtnAjouterAuPanier_1,
			tglbtnAjouterAuPanier_2;
	private JButton btnVerifierPaiement;
	private JButton btnCancelButton;
	private Desktop desktop;
	private String gpc = null;
	private Walletix w = null;

	/**
	 * Create the application.
	 */
	public WltxWindow(Walletix walletix) {
		this.w = walletix;
		desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		initialize();
	}

	public JFrame getFrmWalletixTest() {
		return frmWalletixTest;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWalletixTest = new JFrame();
		frmWalletixTest.setResizable(false);
		frmWalletixTest.setTitle("Walletix Test");
		frmWalletixTest.setBounds(100, 100, 460, 405);
		frmWalletixTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWalletixTest.getContentPane().setLayout(null);

		JLabel lblProduit = new JLabel("Produit 1");
		lblProduit.setBounds(10, 97, 95, 14);
		frmWalletixTest.getContentPane().add(lblProduit);

		JLabel lblProduit_1 = new JLabel("Produit 2");
		lblProduit_1.setBounds(10, 135, 95, 14);
		frmWalletixTest.getContentPane().add(lblProduit_1);

		JLabel lblProduit_2 = new JLabel("Produit 3");
		lblProduit_2.setBounds(10, 171, 95, 14);
		frmWalletixTest.getContentPane().add(lblProduit_2);

		txtDzd = new JTextField();
		txtDzd.setEditable(false);
		txtDzd.setText(prixProduit1 + " DZD");
		txtDzd.setBounds(115, 94, 86, 20);
		frmWalletixTest.getContentPane().add(txtDzd);
		txtDzd.setColumns(10);

		txtDzd_1 = new JTextField();
		txtDzd_1.setEditable(false);
		txtDzd_1.setText(prixProduit2 + " DZD");
		txtDzd_1.setBounds(115, 132, 86, 20);
		frmWalletixTest.getContentPane().add(txtDzd_1);
		txtDzd_1.setColumns(10);

		txtDzd_2 = new JTextField();
		txtDzd_2.setEditable(false);
		txtDzd_2.setText(prixProduit3 + " DZD");
		txtDzd_2.setBounds(115, 168, 86, 20);
		frmWalletixTest.getContentPane().add(txtDzd_2);
		txtDzd_2.setColumns(10);

		tglbtnAjouterAuPanier = new JToggleButton("Ajouter au panier");
		tglbtnAjouterAuPanier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tglbtnAjouterAuPanier.isSelected()) {
					textField.setText(""
							+ (Integer.parseInt(textField.getText()) + prixProduit1));
					btnCommander.setEnabled(true);
				} else {
					textField.setText(""
							+ (Integer.parseInt(textField.getText()) - prixProduit1));
					if (textField.getText().equals("0")) {
						btnCommander.setEnabled(false);
					}
				}
			}
		});
		tglbtnAjouterAuPanier.setBounds(238, 93, 180, 23);
		frmWalletixTest.getContentPane().add(tglbtnAjouterAuPanier);

		tglbtnAjouterAuPanier_1 = new JToggleButton("Ajouter au panier");
		tglbtnAjouterAuPanier_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tglbtnAjouterAuPanier_1.isSelected()) {
					textField.setText(""
							+ (Integer.parseInt(textField.getText()) + prixProduit2));
					btnCommander.setEnabled(true);
				} else {
					textField.setText(""
							+ (Integer.parseInt(textField.getText()) - prixProduit2));
					if (textField.getText().equals("0")) {
						btnCommander.setEnabled(false);
					}
				}
			}
		});
		tglbtnAjouterAuPanier_1.setBounds(238, 131, 180, 23);
		frmWalletixTest.getContentPane().add(tglbtnAjouterAuPanier_1);

		tglbtnAjouterAuPanier_2 = new JToggleButton("Ajouter au panier");
		tglbtnAjouterAuPanier_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tglbtnAjouterAuPanier_2.isSelected()) {
					textField.setText(""
							+ (Integer.parseInt(textField.getText()) + prixProduit3));
					btnCommander.setEnabled(true);
				} else {
					textField.setText(""
							+ (Integer.parseInt(textField.getText()) - prixProduit3));
					if (textField.getText().equals("0")) {
						btnCommander.setEnabled(false);
					}
				}
			}
		});
		tglbtnAjouterAuPanier_2.setBounds(238, 167, 180, 23);
		frmWalletixTest.getContentPane().add(tglbtnAjouterAuPanier_2);

		JLabel lblPrixTotal = new JLabel("Prix Total: ");
		lblPrixTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrixTotal.setBounds(35, 214, 144, 20);
		frmWalletixTest.getContentPane().add(lblPrixTotal);

		textField = new JTextField();
		textField.setBackground(Color.CYAN);
		textField.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField.setText("0");
		textField.setBounds(189, 214, 170, 20);
		frmWalletixTest.getContentPane().add(textField);
		textField.setColumns(10);

		btnCommander = new JButton("Commander");
		btnCommander.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					gpc = w.generatePaymentCode("100", textField.getText(),
							callbackURL);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frmWalletixTest,
							e1.getMessage(), "Erreur",
							JOptionPane.ERROR_MESSAGE);
				} finally {
					webBrowse("https://walletix.com/sandbox/payer?cp=" + gpc);
					btnVerifierPaiement.setEnabled(true);
					btnCancelButton.setEnabled(true);
				}
			}
		});
		btnCommander.setEnabled(false);
		btnCommander.setBounds(49, 267, 270, 23);
		frmWalletixTest.getContentPane().add(btnCommander);

		btnVerifierPaiement = new JButton("Verifier paiement");
		btnVerifierPaiement.setEnabled(false);
		btnVerifierPaiement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gpc != null) {
					boolean vp = false;
					try {
						vp = w.verifyPayment(gpc);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(frmWalletixTest,
								e1.getMessage(), "Erreur",
								JOptionPane.ERROR_MESSAGE);
					} finally {
						if (vp) {
							JOptionPane.showMessageDialog(frmWalletixTest,
									"Le Paiement de l'opération " + gpc
											+ "\n a été effectué avec succes",
									"Merci de votre confiance!",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(frmWalletixTest,
									"Le Paiement de l'opération " + gpc
											+ "\n n'a pas été encore effectué",
									"Paiement en cours...",
									JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			}
		});
		btnVerifierPaiement.setBounds(10, 301, 222, 23);
		frmWalletixTest.getContentPane().add(btnVerifierPaiement);

		JLabel lblWalletixJavaApi;
		try {
			lblWalletixJavaApi = new JLabel(new ImageIcon(new URL(
					"https://www.walletix.com/sites/default/files/logo.png")));
			lblWalletixJavaApi.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					webBrowse("http://walletix.com/");

				}
			});

			lblWalletixJavaApi.setBounds(43, 11, 343, 63);
			frmWalletixTest.getContentPane().add(lblWalletixJavaApi);

			btnCancelButton = new JButton("Annuler L'op\u00E9ration");
			btnCancelButton.setEnabled(false);
			btnCancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (gpc != null) {
						boolean dp = false;
						try {
							dp = w.deletePayment(gpc);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(frmWalletixTest,
									e.getMessage(), "Erreur",
									JOptionPane.ERROR_MESSAGE);
						} finally {
							if (dp) {
								JOptionPane
										.showMessageDialog(
												frmWalletixTest,
												"L'opération "
														+ gpc
														+ "\n a été supprimé avec succes",
												"Success",
												JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(frmWalletixTest,
										"L'opération " + gpc
												+ "\n n'a pas été supprimé",
										"Supprission imporssible",
										JOptionPane.WARNING_MESSAGE);
							}
						}
					}

				}
			});
			btnCancelButton.setBounds(242, 301, 176, 23);
			frmWalletixTest.getContentPane().add(btnCancelButton);

			JMenuBar menuBar = new JMenuBar();
			frmWalletixTest.setJMenuBar(menuBar);

			JMenu mnFile = new JMenu("File");
			mnFile.setEnabled(false);
			menuBar.add(mnFile);

			JMenu mnEdit = new JMenu("Edit");
			mnEdit.setEnabled(false);
			menuBar.add(mnEdit);

			JMenu menu = new JMenu("?");
			menuBar.add(menu);

			JMenuItem mntmAbout = new JMenuItem("About");
			mntmAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// for copying style
					JLabel label = new JLabel();
					Font font = label.getFont();

					// create some css from the label's font
					StringBuffer style = new StringBuffer("font-family:"
							+ font.getFamily() + ";");
					style.append("font-weight:"
							+ (font.isBold() ? "bold" : "normal") + ";");
					style.append("font-size:" + font.getSize() + "pt;");

					// html content
					JEditorPane ep = new JEditorPane(
							"text/html",
							"<html><body style=\""
									+ style
									+ "\">" //
									+ "<p align=\"center\"><a href=\"http://walletix.com/\">Walletix</a> Java API test</p>" //
									+ "<br /><p align=\"center\">2013 &copy; by <a href=\"http://twitter.com/cyounes\">cYounes</a></p> "
									+ "</body></html>");
					ep.addHyperlinkListener(new HyperlinkListener() {

						@Override
						public void hyperlinkUpdate(HyperlinkEvent e) {
							if (e.getEventType().toString().equals("ACTIVATED")) {
								webBrowse(e.getURL().toString());
							}
						}
					});
					ep.setEditable(false);
					ep.setBackground(label.getBackground());

					// show
					JOptionPane.showMessageDialog(frmWalletixTest, ep, "About",
							JOptionPane.PLAIN_MESSAGE);
				}
			});
			menu.add(mntmAbout);

			JMenuItem mntmApiDoc = new JMenuItem("API doc");
			mntmApiDoc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					webBrowse("http://cyounes.github.com/WalletixJavaApi/");
				}
			});
			menu.add(mntmApiDoc);

			JMenuItem mntmGetJavaApi = new JMenuItem("Get Java API");
			mntmGetJavaApi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					webBrowse("https://github.com/cyounes/WalletixJavaApi");
				}
			});
			menu.add(mntmGetJavaApi);
		} catch (MalformedURLException e1) {
			System.err.println(e1.getMessage());
		}
	}

	private void webBrowse(String arg) {
		URL url;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				url = new URL(arg);
				desktop.browse(url.toURI());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
