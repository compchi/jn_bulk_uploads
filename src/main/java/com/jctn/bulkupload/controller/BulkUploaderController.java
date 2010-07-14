/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.controller;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileSystemView;

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
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.setDialogTitle("Choose CSV file for upload.");
				fileChooser.showOpenDialog(parent);
			}
		});
	}
}
