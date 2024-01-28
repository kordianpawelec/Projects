import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class ToDoApp extends JFrame{
	
	private DefaultListModel<String> taskListModel;
	private JList<String> taskList;
	private JTextField taskInput;
	
	private static final String TASK_FILE = "task.txt";
	
	public ToDoApp() {
		setTitle("To Do List App");
		setSize(300,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		initComponents();
		loadTaskFromFile();
	}

	private void initComponents() {
		taskListModel = new DefaultListModel<>();
		taskList = new JList<>(taskListModel);
		
		JScrollPane scrollPane = new JScrollPane(taskList);
		add(scrollPane, BorderLayout.CENTER);
		
		JPanel inputPanel = new JPanel(new BorderLayout());
		taskInput = new JTextField();
		JButton addButton = new JButton("Add Task");
		
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addTask();
				
			}
		});
		
		inputPanel.add(taskInput, BorderLayout.CENTER);
		inputPanel.add(addButton, BorderLayout.EAST);
		
		add(inputPanel, BorderLayout.SOUTH);
		
		taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton removeButton = new JButton("Remove Selected Task");
		removeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				removeTask();
				
			}
		});
		
		JButton completeButton = new JButton("Mark as Finished");
		completeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				markAsCompleted();
			}
		});
		
		JPanel buttonPanel = new JPanel(new GridLayout(1,2));
		buttonPanel.add(removeButton);
		buttonPanel.add(completeButton);
		
		add(buttonPanel, BorderLayout.NORTH);
		
		setVisible(true);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
			
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				saveTaskToFile();
			}
			
		});
	}
	
	

	private void loadTaskFromFile() {
		try {
			List<String> tasks = Files.readAllLines(Paths.get(TASK_FILE));
			taskListModel.addAll(tasks);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	protected void saveTaskToFile() {
		try {
			
			List<String> tasks = new ArrayList<>();
			for(int i=0; i<taskListModel.size();i++) {
				tasks.add(taskListModel.get(i));
			}
			Files.write(Paths.get(TASK_FILE), tasks);
			
		} catch (IOException e) {
			e.printStackTrace();
		
		}
		}
	
	
	private void addTask() {
		String task = taskInput.getText().trim();
		if(!task.isEmpty()) {
			taskListModel.addElement(task);
			taskInput.setText("");
		}
		
	}

	private void removeTask() {
		int selectedIndex = taskList.getSelectedIndex();
		if(selectedIndex != -1)
			taskListModel.remove(selectedIndex);
		
	}

	
	protected void markAsCompleted() {
		int selectedIndex = taskList.getSelectedIndex();
		if(selectedIndex != -1) {
			String task = taskListModel.get(selectedIndex);
			taskListModel.setElementAt("FINISHED " + task, selectedIndex);
			
		}
	}

}
