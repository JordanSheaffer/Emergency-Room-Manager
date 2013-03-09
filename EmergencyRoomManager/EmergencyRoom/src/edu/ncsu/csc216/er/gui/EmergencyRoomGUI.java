package edu.ncsu.csc216.er.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import edu.ncsu.csc216.er.hospital.EmergencyRoom;
import edu.ncsu.csc216.er.hospital.HospitalAdmissionsManager;

/**
 * User interface for EmergencyRoom.  Allows ER staff to monitor/update ER ward and
 * waiting room status.
 * @author Josh Stetson
 *
 */
public class EmergencyRoomGUI extends JFrame implements ActionListener {
	
	/** ID number used for object serialization. */
	private static final long serialVersionUID = 1L;
	/** Title for top of GUI. */
	private static final String APP_TITLE = "Emergency Room Manager";
	/** Panel for waiting room area */
	private JPanel pnlWaitingRoom = new JPanel();
	/** Panel for exam room area */
	private JPanel pnlExamRoom = new JPanel();
	/** Panel for patient info in waiting room */
	private JPanel pnlPatientInfo = new JPanel();
	/** Panel for admitting/releasing patients in waiting room */
	private JPanel pnlPatientControl = new JPanel();
	/** Panel for displaying waiting list */
	private JPanel pnlWaitingList = new JPanel();
	/** Combo box options for choosing patient kind */
	private String[] kinds = {"Kind", "Child", "Adult"};
	/** Combo box for choosing patient kind */
	private JComboBox<String> kind = new JComboBox<String>(kinds);
	/** Combo box options for choosing patient priority */
	private String[] priorities = {"Priority", "1", "2", "3", "4", "5"};
	/** Combo box for choosing patient priority */
	private JComboBox<String> priority = new JComboBox<String>(priorities);
	/** Combo box options for choosing patient priority */
	private String[] newPriorities = {" ", "1", "2", "3", "4", "5"};
	/** Combo box for setting the patient priority */
	private JComboBox<String> newPriority = new JComboBox<String>(newPriorities);
	/** Label for name field */	
	private JLabel nameLabel = new JLabel("Name");
	/** Text for name field */
	private JTextField nameField = new JTextField(20);
	/** Button for admitting a patient to the waiting list */
	private JButton admit = new JButton("Admit");
	/** Button for removing a patient from the waiting list */
	private JButton remove = new JButton("Remove Selected");
	/** Button for setting the priority of a patient on the waiting list */
	private JButton setPriority = new JButton("Set Priority for Selected");
	/** Label for filter field */
	private JLabel filterLabel = new JLabel("Enter Display Filter");
	/** Text for filter field */
	private JTextField filterField = new JTextField(20);
	/** Arraylist for waiting room */
	private ArrayList waitingRoomBackend = new ArrayList();
	/** Default list model for waiting room */
	private DefaultListModel waitingRoomModel = new DefaultListModel();
	/** JList for waiting room model */
	private JList waitingRoomList = new JList(waitingRoomModel);
	/** Scrollpane for waiting room list */
	private JScrollPane waitingListPane = new JScrollPane(waitingRoomList);
	/** Border for patient info */
	private TitledBorder patientInfoBorder = new TitledBorder("New Patient Information");
	/** Border for waiting room */
	private TitledBorder waitingRoomBorder = new TitledBorder("Waiting Room");
	/** Border for exam rooms */
	private TitledBorder examRoomBorder = new TitledBorder("Examination Rooms");
	/** Button for adding exam room */
	private JButton addRoom = new JButton("Add Examination Room");
	/** Button for filling exam rooms */
	private JButton fillRooms = new JButton("Fill Examination Rooms");
	/** Button for release a patient from an exam room */
	private JButton release = new JButton("Release Selected");
	/** Panel for exam room controls */
	private JPanel pnlExamRoomControl = new JPanel();
	/** Emergency room backend */
	private HospitalAdmissionsManager er;
	/** List model for exam room list */
	private DefaultListModel examModel = new DefaultListModel();
	/** List for exam room list */
	private JList examRoomList = new JList(examModel);
	/** List for exam room backend */
	private ArrayList<String> examBackend = new ArrayList<String>();
	/** Scrool pane for exam room list */
	private JScrollPane examPane = new JScrollPane(examRoomList);
	
	
	
	
	public EmergencyRoomGUI(String fileName) {
		super();
		if (fileName == null) {
			try {
				File file = new File(getFileName());
				Scanner s = new Scanner(file);
				er = new EmergencyRoom(s);
			} catch (IllegalArgumentException exp) {
				er = new EmergencyRoom();
			} catch (IllegalStateException exp) {
				er = new EmergencyRoom();
			} catch (FileNotFoundException e) {
				er = new EmergencyRoom();
			}
		} else {
			File file = new File(fileName);
			Scanner s = null;
			try {
				s = new Scanner(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			er = new EmergencyRoom(s);
		}
		
		// populate waiting list
		String[] patients = er.printPatients("").split("\n");
		for (int i = 0; i < patients.length; i++) {
			waitingRoomBackend.add(patients[i]);
		}
		fillWaitingListModel();
		
		// populate exam room list
		String[] wards = er.printWard().split("\n");
		for (int i = 0; i < wards.length; i++) {
			examBackend.add(wards[i]);
		}
		
		fillExamListModel();
		
		Container c = getContentPane();
		
		//Set up general GUI info
		setSize(800, 600);
		setLocation(300, 100);
		setTitle(APP_TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pnlWaitingRoom.setLayout(new BorderLayout());
		pnlWaitingRoom.add(pnlPatientInfo, BorderLayout.NORTH);
		pnlWaitingRoom.add(pnlPatientControl, BorderLayout.CENTER);
		pnlWaitingRoom.add(pnlWaitingList, BorderLayout.SOUTH);
		pnlWaitingRoom.setBorder(waitingRoomBorder);
		pnlExamRoom.setBorder(examRoomBorder);
		pnlWaitingRoom.setPreferredSize(new Dimension(525, 550));
		pnlWaitingList.setPreferredSize(new Dimension(525, 400));
		
		// add waiting room and exam room panels
		c.add(pnlWaitingRoom, BorderLayout.WEST);
		c.add(pnlExamRoom, BorderLayout.CENTER);
		
		// setup listeners
		admit.addActionListener(this);
		remove.addActionListener(this);
		setPriority.addActionListener(this);
		fillRooms.addActionListener(this);
		filterField.addActionListener(this);
		addRoom.addActionListener(this);
		release.addActionListener(this);
		
		// setup patient info panel 
		pnlPatientInfo.setLayout(new FlowLayout());
		pnlPatientInfo.setBorder(patientInfoBorder);		
		pnlPatientInfo.add(kind);
		pnlPatientInfo.add(priority);
		pnlPatientInfo.add(nameLabel);
		pnlPatientInfo.add(nameField);
		
		// setup patient control panel
		pnlPatientControl.setLayout(new FlowLayout());
		pnlPatientControl.add(admit);
		pnlPatientControl.add(remove);
		pnlPatientControl.add(setPriority);
		pnlPatientControl.add(newPriority);
		pnlPatientControl.add(filterLabel);
		pnlPatientControl.add(filterField);
		
		// setup waiting list
		pnlWaitingList.setLayout(new FlowLayout());
		pnlWaitingList.add(waitingListPane);
		waitingListPane.setPreferredSize(new Dimension(515, 395));
		waitingRoomList.setBorder(waitingRoomBorder);
		
		//Exam room setup
		examRoomList.setBorder(examRoomBorder);
		pnlExamRoomControl.setLayout(new BorderLayout());
		pnlExamRoomControl.add(addRoom, BorderLayout.NORTH);
		pnlExamRoomControl.add(fillRooms, BorderLayout.CENTER);
		pnlExamRoomControl.add(release, BorderLayout.SOUTH);
		pnlExamRoom.setLayout(new BorderLayout());
		pnlExamRoom.add(pnlExamRoomControl, BorderLayout.NORTH);
		pnlExamRoom.add(examPane, BorderLayout.CENTER);
		
		
		
		setVisible(true);


	}
	
	/**
	 * Updates the waiting list model 
	 */
    private void fillWaitingListModel(){
        while (!waitingRoomModel.isEmpty())
        	waitingRoomModel.remove(0);
         for (int k = 0; k < waitingRoomBackend.size(); k++) {
        	 waitingRoomModel.addElement((String) waitingRoomBackend.get(k));
         }
    }
    
    /**
     * Updates the exam list model
     */
    private void fillExamListModel(){
        while (!examModel.isEmpty())
            examModel.remove(0);
         for (int k = 0; k < examBackend.size(); k++) {
            examModel.addElement((String) examBackend.get(k));
         }
    }

    /**
     * Updates the GUI and model based on user input
     */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == admit) {
			String currentKind = (String) kind.getSelectedItem();
			int currentPriority = 0;
			try {
				currentPriority = Integer.parseInt((String) priority.getSelectedItem());
			} catch (NumberFormatException n) {
		
			}
			String currentName = (String) nameField.getText();
			boolean validName = false;
			boolean validKind = true;
			for (int i = 0; i < currentName.length(); i++) {
				if (!(currentName.charAt(i) == ' ')) {
					validName = true;
				}
			}
			if (currentKind.equals("Kind")) {
				validKind = false;
			}
			
			// Prompt user for valid inputs
			if (!validKind) {
				JOptionPane.showMessageDialog(this, "Select patient kind (Adult or Child)");
			} else if (currentPriority == 0) {
				JOptionPane.showMessageDialog(this, "Select patient priority (1 - 5)");
			} else if (!validName) {
				JOptionPane.showMessageDialog(this, "Enter patient name (cannot be blank)");
			} else {
				// Admit patient
				er.admit(currentKind, currentPriority, currentName);
				String[] patients = er.printPatients("").split("\n");
				while (!waitingRoomBackend.isEmpty()) {
					waitingRoomBackend.remove(0);
				}
				for (int i = 0; i < patients.length; i++) {
					waitingRoomBackend.add(patients[i]);
				}
				kind.setSelectedIndex(0);
				priority.setSelectedIndex(0);
				fillWaitingListModel();
			}

		} else if (e.getSource() == remove) {
			// remove patient from list
			String patient = (String) waitingRoomList.getSelectedValue();
			int index = waitingRoomList.getSelectedIndex();
			if (!(patient == null)) {
				er.remove(filterField.getText(), index);
				waitingRoomBackend.remove(index);			
				fillWaitingListModel();
			}
			
		} else if (e.getSource() == setPriority) {
			// update priority of selected patient
			int currentPriority = 0;
			try {
				currentPriority = Integer.parseInt((String) newPriority.getSelectedItem());
			} catch (NumberFormatException n) {
				JOptionPane.showMessageDialog(this, "Select patient priority (1 - 5)");
			}
			if (currentPriority > 0 && waitingRoomList.getSelectedValue() != null) {
				er.reassignPriority(filterField.getText(), waitingRoomList.getSelectedIndex(), currentPriority);
				String[] patients = er.printPatients("").split("\n");
				while (!waitingRoomBackend.isEmpty()) {
					waitingRoomBackend.remove(0);
				}
				for (int i = 0; i < patients.length; i++) {
					waitingRoomBackend.add(patients[i]);
				}
				fillWaitingListModel();
				newPriority.setSelectedIndex(0);
			}
	        
		} else if (e.getSource() == fillRooms) {
			// fill available exam rooms with appropriate patients
			er.fillRooms();
			String[] patients = er.printPatients("").split("\n");
			String[] rooms = er.printWard().split("\n");
	        while (!waitingRoomBackend.isEmpty()) {
	            waitingRoomBackend.remove(0);
	        }
	        for (int i = 0; i < patients.length; i++) {
	        	waitingRoomBackend.add(patients[i]);
	        }
	        while (!examBackend.isEmpty()) {
	            examBackend.remove(0);
	        }
	        for (int i = 0; i < rooms.length; i++) {
	        	examBackend.add(rooms[i]);
	        }
	        fillWaitingListModel();
	        fillExamListModel();

		} else if (e.getSource() == filterField) {
			// filter waiting list based on filter input
			String[] patients = er.printPatients(filterField.getText()).split("\n");
	        while (!waitingRoomBackend.isEmpty()) {
	            waitingRoomBackend.remove(0);
	        }
	        for (int i = 0; i < patients.length; i++) {
	        	waitingRoomBackend.add(patients[i]);
	        }
	        fillWaitingListModel();
	        
		} else if (e.getSource() == addRoom) {
			// add exam room to list
			er.addNewRoom();
			String[] rooms = er.printWard().split("\n");
			while (!examBackend.isEmpty()) {
	            examBackend.remove(0);
	        }
	        for (int i = 0; i < rooms.length; i++) {
	        	examBackend.add(rooms[i]);
	        }
	        fillExamListModel();
	        
		} else if (e.getSource() == release) {
			// release patient from exam room
			int index = examRoomList.getSelectedIndex();
			er.releaseFromRoom(index);
			String[] rooms = er.printWard().split("\n");
			while (!examBackend.isEmpty()) {
	            examBackend.remove(0);
	        }
	        for (int i = 0; i < rooms.length; i++) {
	        	examBackend.add(rooms[i]);
	        }
			
			fillExamListModel();
		}
		
	}
	
	/**
	 * Returns a file name generated through interactions with a {@link JFileChooser}
	 * object.
	 * @return the file name selected through {@link JFileChooser}
	 */
	private String getFileName() {
		JFileChooser fc = new JFileChooser("./");  //Open JFileChoose to current working directory
		int returnVal = fc.showOpenDialog(this);
		if (returnVal != JFileChooser.APPROVE_OPTION) {
			//Error or user canceled, either way no file name.
			throw new IllegalStateException();
		}
		File gameFile = fc.getSelectedFile();
		return gameFile.getAbsolutePath();
	}
	
	
	/**
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			new EmergencyRoomGUI(null);
		} else {
			new EmergencyRoomGUI(args[0]);
		}

	}

}
