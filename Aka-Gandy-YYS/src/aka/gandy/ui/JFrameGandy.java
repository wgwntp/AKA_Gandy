package aka.gandy.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import aka.gandy.config.GameConfig;
import aka.gandy.config.ModeConfig;
import aka.gandy.control.GandyControl;
import aka.gandy.dto.Dto;
import aka.gandy.utils.Utils;
import aka.gandy.win32.Window;

public class JFrameGandy extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private Dto dto;
	
	private boolean windwoNameIsLock = false;
	
	private boolean isMainWindow;

	public JFrameGandy(GandyControl gandyControl, Dto dto) {
		this.dto = dto;
		
		this.isMainWindow = gandyControl.isMainWindow();
		// �����Ϸ����
		//FrameConfig fCfg = GameConfig.getFrameConfig();
		JFrame.setDefaultLookAndFeelDecorated(true);
		// ���ñ���
		this.setTitle("Gandy-" + dto.getName());
		// ����Ĭ�Ϲر�
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// ���ô��ڴ�С
		this.setSize(700, 200);
		// ���ò��ɸı��С
		this.setResizable(false);
		// ���ô��ھ�����ʾ
		//FrameUtil.setFrameCenter(this);
		this.setLayout(new FlowLayout());
		Container panel = this.getContentPane();  
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  
		this.initComponent(panel);
		// Ĭ�ϴ���Ϊ��ʾ
		this.setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (isMainWindow) {
					System.out.println("release");
					GameConfig.releaseTemplateMap();
					System.exit(0); // �ر�
				}
			}
		});
	}
	
	private void initComponent(Container panel) {
		firstColumn(panel);
		secondColumn(panel);
	}
	
	private void firstColumn(Container panel) {
		JPanel panelOne = new JPanel();
		panelOne.setLayout(new FlowLayout());
		panelOne.setBackground(Color.GRAY);
		JLabel nameLabel = new JLabel("��Ϸ��������:");
		//nameLabel.setFont(new Font("����",Font.CENTER_BASELINE, 16));
		panelOne.add(nameLabel);
		JTextField nameText = new JTextField(40);
		nameText.setText("������ģ���� 0.10.6 Beta");
		//nameText.setFont(new Font("����",Font.CENTER_BASELINE, 16));
		panelOne.add(nameText);
		JLabel errLabel = new JLabel();
		JButton lockButton = new JButton("����");
		lockButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (windwoNameIsLock) {
					if (dto.isPause()) {
						lockButton.setText("����");
						nameText.setEnabled(true);
						dto.setHwnd(-1);
						windwoNameIsLock = !windwoNameIsLock;
					} else {
						errLabel.setText("������ͣGandy!");
					}
				} else {
					String title = nameText.getText();
					if (title.equals("")) {
						errLabel.setText("�����봰������!");
					} else {
						errLabel.setText("");
						int hwnd = Window.getHwnd(title);
						if (hwnd > 0) {
							dto.setHwnd(hwnd);
							nameText.setEnabled(false);
							lockButton.setText("�޸�");
							windwoNameIsLock = !windwoNameIsLock;
						} else {
							errLabel.setText("�������ƴ���!");
						}
					}
				}
			}
		});
		//lockButton.setFont(new Font("����",Font.CENTER_BASELINE, 16));
		
		panelOne.add(lockButton);
		panelOne.add(errLabel);
		panel.add(panelOne);
	}
	
	private void secondColumn(Container panel) {
		JPanel panelTwo = new JPanel();
		panelTwo.setBackground(Color.CYAN);
		panelTwo.setLayout(new FlowLayout());
		JLabel modeLabel = new JLabel("ģʽѡ��:");
		panelTwo.add(modeLabel);
		JComboBox<Item> comboBox = new JComboBox<Item>();
		List<Item> items = new ArrayList<>();
		
		for (String key : ModeConfig.modeToName.keySet()) {
			Item item = new Item(key, ModeConfig.modeToName.get(key));
			comboBox.addItem(item);
			items.add(item);
		}
		JButton button = new JButton("��ʼ");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (windwoNameIsLock) {
					boolean isPause = dto.isPause();
					if (isPause) {
						int index = comboBox.getSelectedIndex();
						int mode = items.get(index).getValue();
						dto.setMode(mode);
						button.setText("��ͣ");
						Utils.setWindow(dto.getHwnd(), isMainWindow);
					} else {
						button.setText("��ʼ");
					}
					dto.setPause(!isPause);
				} else {
					
				}
			}
		});
		
		JButton newGandyButton = new JButton("�½�����");
		newGandyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new GandyControl("P2", false);
			}
		});
		if (!isMainWindow) {
			newGandyButton.setVisible(false);
		}
		panelTwo.add(comboBox);
		panelTwo.add("beginButton", button);
		panelTwo.add(newGandyButton);
		panel.add("panelTwo", panelTwo);
	}
	
	class Item {
		private String key;

		private int value;

		public Item(String key, int value) {
			this.key = key;
			this.value = value;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
		
		public String toString() {
			return key;
		}
	}
}

