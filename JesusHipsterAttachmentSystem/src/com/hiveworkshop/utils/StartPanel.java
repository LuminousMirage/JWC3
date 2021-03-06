package com.hiveworkshop.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.hiveworkshop.wc3.gui.BLPHandler;
import com.hiveworkshop.wc3.mdl.MDL;

public class StartPanel extends JPanel implements ActionListener {

	JLabel title;
	JLabel desc;
	JButton newProject, openProject;
	MainFrame frame;
	private final JFileChooser jfc = new JFileChooser();

	public StartPanel(final MainFrame frame) {
		this.frame = frame;
		setPreferredSize(new Dimension(800,600));
//		setBackground(Color.gray);
		final Color textColor = null;//Color.white;

		final Font smallFont = new Font("Arial",Font.BOLD,16);
		final Font medFont = new Font("Arial",Font.BOLD,28);
		final Font bigFont = new Font("Arial",Font.BOLD,46);
		title = new JLabel(Resources.getString("program.name"));
		title.setFont(bigFont);
		title.setIcon(new ImageIcon(MainFrame.HIPSTER_ICON));
		title.setForeground(textColor);
		add(title);
		desc = new JLabel(Resources.getString("program.desc"));
		desc.setFont(smallFont);
		desc.setForeground(textColor);
		add(desc);

		newProject = new JButton("New Project", new ImageIcon(BLPHandler.get().getGameTex("ReplaceableTextures\\CommandButtons\\BTNBasicStruct.blp").getScaledInstance(48, 48, Image.SCALE_SMOOTH)));
		newProject.setFont(medFont);
		newProject.setForeground(textColor);
		newProject.addActionListener(this);

		final JLabel newProjectTip = new JLabel("Requests that a new attachment model be created. Choose this");
		newProjectTip.setFont(smallFont);
		newProjectTip.setForeground(textColor);

		final JLabel newProjectTip2 = new JLabel("if you would like to start on something new.");
		newProjectTip2.setFont(smallFont);
		newProjectTip2.setForeground(textColor);

		openProject = new JButton("Open Project", new ImageIcon(BLPHandler.get().getGameTex("ReplaceableTextures\\CommandButtons\\BTNRepair.blp").getScaledInstance(48, 48, Image.SCALE_SMOOTH)));
		openProject.setFont(medFont);
		openProject.setForeground(textColor);
		openProject.addActionListener(this);

		final JLabel openProjectTip = new JLabel("Opens an attachment project. Choose this if you would");
		openProjectTip.setFont(smallFont);
		openProjectTip.setForeground(textColor);

		final JLabel openProjectTip2 = new JLabel("like to continue work that you started earlier.");
		openProjectTip2.setFont(smallFont);
		openProjectTip2.setForeground(textColor);

		add(newProject);
		add(openProject);

		final GroupLayout layout = new GroupLayout(this);
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGap(16)
				.addGroup(layout.createParallelGroup()
						.addComponent(title)
						.addComponent(desc)
						.addComponent(newProject)
						.addComponent(newProjectTip)
						.addComponent(newProjectTip2)
						.addComponent(openProject)
						.addComponent(openProjectTip)
						.addComponent(openProjectTip2)
				)
				.addGap(16));

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGap(16)
				.addComponent(title)
				.addGap(4)
				.addComponent(desc)
				.addGap(64)
				.addComponent(newProject)
				.addGap(4)
				.addComponent(newProjectTip)
				.addGap(4)
				.addComponent(newProjectTip2)
				.addGap(32)
				.addComponent(openProject)
				.addGap(4)
				.addComponent(openProjectTip)
				.addGap(4)
				.addComponent(openProjectTip2)
				.addGap(16));

		setLayout(layout);

		jfc.addChoosableFileFilter(new FileNameExtensionFilter("Warcraft III Binary Model \"*.mdx\"", "mdx"));
		jfc.addChoosableFileFilter(new FileNameExtensionFilter("Warcraft III Text-based Model \"*.mdl\"", "mdl"));
		jfc.setAcceptAllFileFilterUsed(false);
		jfc.setFileFilter(jfc.getChoosableFileFilters()[0]);
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		if( e.getSource() == newProject ) {
			frame.jumpToPanel(frame.baseChoicePanel);
		} else if ( e.getSource() == openProject ) {
			//JOptionPane.showMessageDialog(this, "Error: This feature is not yet implemented.", "ERROR", JOptionPane.ERROR_MESSAGE);
			final int x = jfc.showOpenDialog(frame);
			final File selectedFile = jfc.getSelectedFile();
			if( x == JFileChooser.APPROVE_OPTION && selectedFile != null ) {
//				JOptionPane.showMessageDialog(null, jfc.getSelectedFile() + ". Good choice.");
			} else {
				return;
			}

			final MDL mdlModel = MDL.read(selectedFile);
			frame.jumpToPanel(new EditPanel(frame,this,mdlModel));
		}
	}
}
