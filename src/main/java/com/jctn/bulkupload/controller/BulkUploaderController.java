/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.controller;

import java.awt.Component;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author martin
 */
public class BulkUploaderController {

	public void runInSwingThread(Runnable r) {
		if (SwingUtilities.isEventDispatchThread()) {
			r.run();
		} else {
			SwingUtilities.invokeLater(r);
		}
	}

	/**
	 * Opens opens the given fileChooser.
	 *
	 * @param parent Parent component
	 * @param fileChooser File chooser.
	 */
	public void loadFileChooser(final Component parent, final JFileChooser fileChooser) {
		if (fileChooser == null) {
			throw new IllegalArgumentException("File chooser is null");
		}

		runInSwingThread(new Runnable() {

			@Override
			public void run() {
				FileSystemView fsView = FileSystemView.getFileSystemView();
				File[] roots = fsView.getRoots();
				File currentDir = new File("/");

				if (roots != null && roots.length > 0) {
					currentDir = roots[0];
				}

				fileChooser.setCurrentDirectory(currentDir);
				if (fileChooser.getChoosableFileFilters() != null && fileChooser.getChoosableFileFilters().length > 0) {
					fileChooser.resetChoosableFileFilters();
				}

				fileChooser.setFileFilter(new FileFilter() {

					@Override
					public boolean accept(File f) {
						return f.getPath().toLowerCase().endsWith(".csv");
					}

					@Override
					public String getDescription() {
						return "Comma-separated values (csv) files.";
					}
				});

				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.setDialogTitle("Choose CSV file for upload.");
				fileChooser.showOpenDialog(parent);
			}
		});
	}

	public void startUpload(String text, char[] password, String domain, File csvFile) {
		//parse the file
		//submit the parsed lines for processing
	}

	/**
	 * Returns true/false depending on if the input is valid.
	 * @param errors List of errors is stored here. It is cleared before the input is processed.
	 * @param adminUsername Admin username
	 * @param password Admin password
	 * @param adminDomain Admin domain
	 * @param csvFile CSV file to process
	 * @return true if no errors
	 */
	public boolean validateInput(List<String> errors, String adminUsername, char[] password, String adminDomain, File csvFile) {
		errors.clear();
		try {
			if (StringUtils.isEmpty(adminUsername)) {
				errors.add("Username is blank.");
			}

			if (StringUtils.isEmpty(adminDomain)) {
				errors.add("Domain is blank.");
			}

			if (password == null || password.length == 0 || StringUtils.isEmpty(new String(password))) {
				errors.add("Password is blank.");
			}

			if (csvFile == null || !csvFile.canRead()) {
				errors.add("CSV file not readable.");
			}
		} catch (Exception e) {
			errors.add("Unknown error: " + e.toString());
		}

		return errors.isEmpty();
	}
}
