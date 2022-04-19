package JumpMyBallApp.ui;

import JumpMyBallApp.entity.AgentEntity;
import JumpMyBallApp.manager.AgentEntityManager;
import JumpMyBallApp.util.BaseForm;

import javax.swing.*;
import java.sql.SQLException;

public class CreateAgentForm extends BaseForm {
    private JPanel mainPanel;
    private JTextField agentTypeTextField;
    private JTextField addressTextField;
    private JTextField logoTextField;
    private JTextField phoneTextField;
    private JTextField emailTextField;
    private JTextField titleTextField;
    private JSpinner prioritySpinner;
    private JButton backButton;
    private JButton addButton;

    public CreateAgentForm() {
        super(800,600);
        setContentPane(mainPanel);
        initButtons();
        setVisible(true);
    }
    private void initButtons(){
        backButton.addActionListener(e -> {
            dispose();
            new MainForm();
        });
        addButton.addActionListener(e -> {
            String agentType = agentTypeTextField.getText();
            String title = titleTextField.getText();
            String email = emailTextField.getText();
            String phone = phoneTextField.getText();
            String logo = logoTextField.getText();
            String address = addressTextField.getText();
            int priority = (int) prioritySpinner.getValue();

            AgentEntity agent = new AgentEntity(
                    agentType,
                    title,
                    email,
                    phone,
                    logo,
                    address,
                    priority
            );
            try {
                AgentEntityManager.insert(agent);
                JOptionPane.showMessageDialog(this,"Запись успешно создана","Добавление",JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new MainForm();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }
}
