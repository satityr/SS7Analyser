package com.inov.ss7analayser.testes;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class Test1ofmany extends JFrame {

	private JLabel countLabel1 = new JLabel("0");
	private JLabel statusLabel = new JLabel("Task not completed.");
	private JButton startButton = new JButton("Start");

	public Test1ofmany(String title) {
		super(title);

		setLayout(new GridBagLayout());

		countLabel1.setFont(new Font("serif", Font.BOLD, 28));

		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.NONE;

		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		add(countLabel1, gc);

		gc.gridx = 0;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		add(statusLabel, gc);

		gc.gridx = 0;
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 1;
		add(startButton, gc);

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				start();
			}
		});

		setSize(200, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	 private void start() {
		 SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>() {
			   @Override
			   protected Boolean doInBackground() throws Exception {
			    // Simulate doing something useful.
			    for (int i = 0; i <= 10; i++) {
			     Thread.sleep(1000);
			     
			     // The type we pass to publish() is determined
			     // by the second template parameter.
			     publish(i);
			    }

			    // Here we can return some object of whatever type
			    // we specified for the first template parameter.
			    // (in this case we're auto-boxing 'true').
			    return true;
			   }

			   // Can safely update the GUI from this method.
			   protected void done() {
			    
			    boolean status;
			    try {
			     // Retrieve the return value of doInBackground.
			     status = get();
			     statusLabel.setText("Completed with status: " + status);
			    } catch (InterruptedException e) {
			     // This is thrown if the thread's interrupted.
			    } catch (ExecutionException e) {
			     // This is thrown if we throw an exception
			     // from doInBackground.
			    }
			   }

			   @Override
			   // Can safely update the GUI from this method.
			   protected void process(List<Integer> chunks) {
			    // Here we receive the values that we publish().
			    // They may come grouped in chunks.
			    int mostRecentValue = chunks.get(chunks.size()-1);
			    
			    countLabel1.setText(Integer.toString(mostRecentValue));
			   }
			   
			   
			  };
			  
			  worker.execute();
		 }

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Test1ofmany("SwingWorker Demo");
			}
		});
	}
}
