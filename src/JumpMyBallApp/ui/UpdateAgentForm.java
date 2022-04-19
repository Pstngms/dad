package JumpMyBallApp.ui;

import JumpMyBallApp.entity.AgentEntity;
import JumpMyBallApp.manager.AgentEntityManager;
import JumpMyBallApp.util.BaseForm;

import javax.swing.*;
import java.sql.SQLException;

public class UpdateAgentForm extends BaseForm {
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
    private JButton deleteButton;
    private JTextField idTextField;
    private AgentEntity agent;

    public UpdateAgentForm(AgentEntity agent) {
        super(800,600);
        setContentPane(mainPanel);
        this.agent=agent;
        initButtons();
        initFields();
        setVisible(true);
    }
    private void initFields(){
        idTextField.setText(String.valueOf(agent.getId()));
        agentTypeTextField.setText(agent.getAgentType());
        titleTextField.setText(agent.getTitle());
        emailTextField.setText(agent.getEmail());
        phoneTextField.setText(agent.getPhone());
        logoTextField.setText(agent.getLogo());
        addressTextField.setText(agent.getAddress());
        prioritySpinner.setValue(agent.getPriority());
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

            agent.setAgentType(agentType);
            agent.setTitle(title);
            agent.setEmail(email);
            agent.setPhone(phone);
            agent.setLogo(logo);
            agent.setAddress(address);
            agent.setPriority(priority);
            try {
                AgentEntityManager.update(agent);
                JOptionPane.showMessageDialog(this,"Запись успешно изменена","Добавление",JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new MainForm();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        deleteButton.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this,"Вы точно хотите удалить запись?","Удаление",JOptionPane.YES_NO_OPTION)
            == JOptionPane.YES_OPTION){
                try {
                    AgentEntityManager.delete(agent.getId());
                    JOptionPane.showMessageDialog(this,"Запись успешно удалена","Удаление",JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose();
                new MainForm();
            }
        });
    }
}
