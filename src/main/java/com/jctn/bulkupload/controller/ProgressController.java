package com.jctn.bulkupload.controller;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import org.apache.log4j.Logger;

/**
 * Handles progress updates.
 *
 * @author Martin
 */
public class ProgressController {

	private static final Logger logger = Logger.getLogger(ProgressController.class);
	JProgressBar progressBar;
	JLabel progressLabel;
	final String originalLabelText;

	/**
	 * Creates a new instance of this controller.
	 * @param progressBar
	 * @param progressLabel
	 */
	public ProgressController(JProgressBar progressBar, JLabel progressLabel) {
		if (progressBar == null) {
			throw new IllegalArgumentException("Supplied progress bar is null.");
		}

		if (progressLabel == null) {
			throw new IllegalArgumentException("Supplied progress label is null.");
		}

		this.progressBar = progressBar;
		this.progressLabel = progressLabel;
		this.originalLabelText = progressLabel.getText();
	}

	protected void runInSwingThread(Runnable r) {
		if (SwingUtilities.isEventDispatchThread()) {
			r.run();
		} else {
			SwingUtilities.invokeLater(r);
		}
	}

	/**
	 * Sets the current task for this controller.
	 * @param task String representing the task being performed
	 * @param min Min value
	 * @param max Max value
	 */
	public void setCurrentTask(final String task,
			final int min,
			final int max) {

		Runnable r = new Runnable() {

			@Override
			public void run() {
				progressLabel.setText(task);
				progressBar.setMinimum(min);
				progressBar.setMaximum(max);
				logger.info("Progress task: " + task);
			}
		};

		runInSwingThread(r);
	}

	/**
	 * Sets the appropriate model object to (in)determinate.
	 * @param indeterminate If true the model will be set to indeterminate.
	 */
	public void setIndeterminate(final boolean indeterminate) {
		Runnable r = new Runnable() {

			@Override
			public void run() {
				progressBar.setIndeterminate(indeterminate);
			}
		};

		runInSwingThread(r);
	}

	/**
	 * Sets the current task progress.
	 * @param progress Integer indicating the current progress.
	 */
	public void setProgress(final int progress) {
		Runnable r = new Runnable() {

			@Override
			public void run() {
				progressBar.setValue(progress);
			}
		};

		runInSwingThread(r);
	}

	/**
	 * Should be called when the current task is complete. This will reset the
	 * state of the underlying model.
	 */
	public void done() {

		setProgress(0);

		Runnable r = new Runnable() {

			@Override
			public void run() {
				progressLabel.setText(originalLabelText);
			}
		};

		runInSwingThread(r);
	}

	/**
	 * Returns a reference to the underlying progress bar.
	 * @return
	 */
	public JProgressBar getProgressBar() {
		return progressBar;
	}
}
