/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BulkUploader.java
 *
 * Created on Jul 12, 2010, 10:39:51 PM
 */
package com.jctn.bulkupload;

import com.jctn.bulkupload.controller.BulkUploaderController;
import com.jctn.bulkupload.controller.ProgressController;
import com.jctn.bulkupload.util.LogFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author martin
 */
public class BulkUploader extends javax.swing.JFrame {

	private static Logger logger = LogFactory.getLogger(BulkUploader.class);
	private BulkUploaderController guiController;
	private File csvFile;

	/** Creates new form BulkUploader */
	public BulkUploader() {
		initComponents();
		setLocationRelativeTo(null);
		guiController = new BulkUploaderController();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        csvFileChooser = new javax.swing.JFileChooser();
        panelAuthCredentials = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textFieldUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textFieldDomain = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        panelChooseFile = new javax.swing.JPanel();
        chooseFileButton = new javax.swing.JButton();
        labelCsvFilePath = new javax.swing.JLabel();
        startButtonPanel = new javax.swing.JPanel();
        uploadButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        statusPanel = new javax.swing.JPanel();
        statusMessageLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        csvFileChooser.setCurrentDirectory(new java.io.File("C:\\"));
            org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(com.jctn.bulkupload.DesktopApplication1.class).getContext().getResourceMap(BulkUploader.class);
            csvFileChooser.setDialogTitle(resourceMap.getString("csvFileChooser.dialogTitle")); // NOI18N
            csvFileChooser.setName("csvFileChooser"); // NOI18N
            csvFileChooser.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    csvFileChooserActionPerformed(evt);
                }
            });

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setTitle(resourceMap.getString("Form.title")); // NOI18N
            setMinimumSize(new java.awt.Dimension(500, 250));
            setName("Form"); // NOI18N
            getContentPane().setLayout(new java.awt.GridBagLayout());

            panelAuthCredentials.setName("panelAuthCredentials"); // NOI18N
            panelAuthCredentials.setLayout(new java.awt.GridBagLayout());

            jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
            jLabel1.setName("jLabel1"); // NOI18N
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.ipadx = 2;
            gridBagConstraints.ipady = 1;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
            gridBagConstraints.insets = new java.awt.Insets(4, 0, 1, 5);
            panelAuthCredentials.add(jLabel1, gridBagConstraints);

            textFieldUsername.setText(resourceMap.getString("textFieldUsername.text")); // NOI18N
            textFieldUsername.setMinimumSize(new java.awt.Dimension(100, 20));
            textFieldUsername.setName("textFieldUsername"); // NOI18N
            textFieldUsername.setPreferredSize(new java.awt.Dimension(100, 20));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.ipadx = 2;
            gridBagConstraints.ipady = 1;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints.insets = new java.awt.Insets(4, 0, 1, 5);
            panelAuthCredentials.add(textFieldUsername, gridBagConstraints);

            jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
            jLabel2.setName("jLabel2"); // NOI18N
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 2;
            gridBagConstraints.ipadx = 2;
            gridBagConstraints.ipady = 1;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
            gridBagConstraints.insets = new java.awt.Insets(4, 0, 1, 5);
            panelAuthCredentials.add(jLabel2, gridBagConstraints);

            jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
            jLabel3.setName("jLabel3"); // NOI18N
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.ipadx = 2;
            gridBagConstraints.ipady = 1;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
            gridBagConstraints.insets = new java.awt.Insets(4, 0, 1, 5);
            panelAuthCredentials.add(jLabel3, gridBagConstraints);

            textFieldDomain.setText(resourceMap.getString("textFieldDomain.text")); // NOI18N
            textFieldDomain.setMinimumSize(new java.awt.Dimension(100, 20));
            textFieldDomain.setName("textFieldDomain"); // NOI18N
            textFieldDomain.setPreferredSize(new java.awt.Dimension(100, 20));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.ipadx = 2;
            gridBagConstraints.ipady = 1;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints.insets = new java.awt.Insets(4, 0, 1, 5);
            panelAuthCredentials.add(textFieldDomain, gridBagConstraints);

            passwordField.setText(resourceMap.getString("passwordField.text")); // NOI18N
            passwordField.setMinimumSize(new java.awt.Dimension(100, 20));
            passwordField.setName("passwordField"); // NOI18N
            passwordField.setPreferredSize(new java.awt.Dimension(100, 20));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 2;
            gridBagConstraints.ipadx = 2;
            gridBagConstraints.ipady = 1;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints.insets = new java.awt.Insets(4, 0, 1, 5);
            panelAuthCredentials.add(passwordField, gridBagConstraints);

            jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
            jLabel4.setName("jLabel4"); // NOI18N
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
            panelAuthCredentials.add(jLabel4, gridBagConstraints);

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints.insets = new java.awt.Insets(0, 17, 0, 0);
            getContentPane().add(panelAuthCredentials, gridBagConstraints);

            panelChooseFile.setMinimumSize(new java.awt.Dimension(465, 37));
            panelChooseFile.setName("panelChooseFile"); // NOI18N
            panelChooseFile.setPreferredSize(new java.awt.Dimension(465, 40));
            panelChooseFile.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

            chooseFileButton.setText(resourceMap.getString("chooseFileButton.text")); // NOI18N
            chooseFileButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            chooseFileButton.setName("chooseFileButton"); // NOI18N
            chooseFileButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    chooseFileButtonActionPerformed(evt);
                }
            });
            panelChooseFile.add(chooseFileButton);

            labelCsvFilePath.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            labelCsvFilePath.setText(resourceMap.getString("labelCsvFilePath.text")); // NOI18N
            labelCsvFilePath.setMaximumSize(new java.awt.Dimension(400, 14));
            labelCsvFilePath.setName("labelCsvFilePath"); // NOI18N
            labelCsvFilePath.setPreferredSize(new java.awt.Dimension(300, 14));
            if(csvFile != null && csvFile.canRead()){
                labelCsvFilePath.setText(csvFile.getPath());
            }
            panelChooseFile.add(labelCsvFilePath);

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
            getContentPane().add(panelChooseFile, gridBagConstraints);

            startButtonPanel.setName("startButtonPanel"); // NOI18N

            uploadButton.setFont(resourceMap.getFont("uploadButton.font")); // NOI18N
            uploadButton.setText(resourceMap.getString("uploadButton.text")); // NOI18N
            uploadButton.setName("uploadButton"); // NOI18N
            uploadButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    uploadButtonActionPerformed(evt);
                }
            });
            startButtonPanel.add(uploadButton);

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
            getContentPane().add(startButtonPanel, gridBagConstraints);

            jSeparator2.setName("jSeparator2"); // NOI18N
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 4;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            getContentPane().add(jSeparator2, gridBagConstraints);

            statusPanel.setName("statusPanel"); // NOI18N
            statusPanel.setPreferredSize(new java.awt.Dimension(500, 35));

            statusMessageLabel.setText(resourceMap.getString("statusMessageLabel.text")); // NOI18N
            statusMessageLabel.setName("statusMessageLabel"); // NOI18N

            progressBar.setName("progressBar"); // NOI18N

            javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
            statusPanel.setLayout(statusPanelLayout);
            statusPanelLayout.setHorizontalGroup(
                statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(statusPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(statusMessageLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 299, Short.MAX_VALUE)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );
            statusPanelLayout.setVerticalGroup(
                statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(statusPanelLayout.createSequentialGroup()
                    .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statusPanelLayout.createSequentialGroup()
                            .addContainerGap(13, Short.MAX_VALUE)
                            .addComponent(statusMessageLabel))
                        .addGroup(statusPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
            );

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 5;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            getContentPane().add(statusPanel, gridBagConstraints);

            menuBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            menuBar.setName("menuBar"); // NOI18N

            fileMenu.setMnemonic('F');
            fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
            fileMenu.setName("fileMenu"); // NOI18N

            exitMenuItem.setMnemonic('x');
            exitMenuItem.setText(resourceMap.getString("exitMenuItem.text")); // NOI18N
            exitMenuItem.setName("exitMenuItem"); // NOI18N
            exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    exitMenuItemActionPerformed(evt);
                }
            });
            fileMenu.add(exitMenuItem);

            menuBar.add(fileMenu);

            helpMenu.setMnemonic('H');
            helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
            helpMenu.setName("helpMenu"); // NOI18N

            aboutMenuItem.setMnemonic('A');
            aboutMenuItem.setText(resourceMap.getString("aboutMenuItem.text")); // NOI18N
            aboutMenuItem.setName("aboutMenuItem"); // NOI18N
            aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    aboutMenuItemActionPerformed(evt);
                }
            });
            helpMenu.add(aboutMenuItem);

            menuBar.add(helpMenu);

            setJMenuBar(menuBar);

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
		System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

	private void chooseFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseFileButtonActionPerformed
		//load the file chooser
		guiController.loadFileChooser(this, csvFileChooser);
	}//GEN-LAST:event_chooseFileButtonActionPerformed

	private void csvFileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_csvFileChooserActionPerformed
		if (evt.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
			//Populate the text field with the path of the choosen file.
			guiController.runInSwingThread(new Runnable() {

				@Override
				public void run() {
					labelCsvFilePath.setText(csvFileChooser.getSelectedFile().getAbsolutePath());
					labelCsvFilePath.setToolTipText(labelCsvFilePath.getText());
					setCsvFile(csvFileChooser.getSelectedFile());
				}
			});
		}
	}//GEN-LAST:event_csvFileChooserActionPerformed

	private void uploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadButtonActionPerformed
		//validate input
		final List<String> errors = new ArrayList<String>();
		if (!guiController.validateInput(errors, textFieldUsername.getText(), passwordField.getPassword(), textFieldDomain.getText(), csvFile)) {
			guiController.runInSwingThread(new Runnable() {

				@Override
				public void run() {
					final String message = "Please address the following errors.\n\n" + StringUtils.join(errors, "\n");
					JOptionPane.showMessageDialog(BulkUploader.this,
							message,
							"Input Error",
							JOptionPane.ERROR_MESSAGE);
				}
			});
			return;
		}

		//start the magic
		Thread uploadTask = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					guiController.startUpload(new ProgressController(progressBar, statusMessageLabel), textFieldUsername.getText(), passwordField.getPassword(), textFieldDomain.getText(), csvFile);
				} catch (Exception e) {
					logger.error("Error processing file", e);
					guiController.runInSwingThread(new Runnable() {

						@Override
						public void run() {
							JOptionPane.showMessageDialog(BulkUploader.this,
									"There was an error processing the file. Please see logs for details.",
									"Processing Error",
									JOptionPane.ERROR_MESSAGE);
						}
					});

				}
			}
		}, "Upload Task");

		uploadTask.start();
	}//GEN-LAST:event_uploadButtonActionPerformed

	private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
		// TODO add your handling code here:
		guiController.runInSwingThread(new Runnable() {

			@Override
			public void run() {
				AboutDialog about = new AboutDialog(BulkUploader.this);
				about.setLocationRelativeTo(BulkUploader.this);
				about.setVisible(true);
			}
		});

	}//GEN-LAST:event_aboutMenuItemActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(final String args[]) {

		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					LogFactory.getLogger(BulkUploader.class).error("Error setting LAF", e);
				}
				//Create a new instance of this class
				BulkUploader bu = new BulkUploader();
				//Process any args
				if(args.length > 0){
					//First expected to be the path to the CSV file
					String filePath = args[0];
					File csv = new File(filePath);
					if (csv != null && csv.canRead()){
						bu.setCsvFile(csv);
						bu.labelCsvFilePath.setText(filePath);
					}
				}
				bu.setVisible(true);
			}
		});
	}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton chooseFileButton;
    private javax.swing.JFileChooser csvFileChooser;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelCsvFilePath;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPanel panelAuthCredentials;
    private javax.swing.JPanel panelChooseFile;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JPanel startButtonPanel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JTextField textFieldDomain;
    private javax.swing.JTextField textFieldUsername;
    private javax.swing.JButton uploadButton;
    // End of variables declaration//GEN-END:variables

	public void setCsvFile(File csvFile) {
		this.csvFile = csvFile;
	}
}
