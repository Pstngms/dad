package JumpMyBallApp.ui;

import JumpMyBallApp.entity.AgentEntity;
import JumpMyBallApp.manager.AgentEntityManager;
import JumpMyBallApp.util.BaseForm;

import javax.swing.*;
import java.sql.SQLException;

public class MainForm extends BaseForm {
    private JPanel mainPanel;
    private JButton viewButton;
    private JButton updateButton;
    private JButton addButton;

    public MainForm() {
        super(800, 600);
        setContentPane(mainPanel);
        initButtons();
        setVisible(true);
    }
    private void initButtons(){
        viewButton.addActionListener(e -> {
            dispose();
            new ViewAgentForm();
        });
        addButton.addActionListener(e -> {
            dispose();
            new CreateAgentForm();

        });
        updateButton.addActionListener(e -> {
            String s = JOptionPane.showInputDialog(this,"Введите ИД","Редактирование",JOptionPane.QUESTION_MESSAGE);
            if (s==null){
                return;
            }
            int id =-1;
            try {
                id =Integer.parseInt(s);
            }catch (Exception exception){
                JOptionPane.showMessageDialog(this,"Введите числовое значение","Ошибка",JOptionPane.ERROR_MESSAGE);
                return;
            }
            AgentEntity agent = null;
            try {
                agent = AgentEntityManager.selectById(id);

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            if (agent == null){
                JOptionPane.showMessageDialog(this,"Запись отсутсвует","Ошибка",JOptionPane.ERROR_MESSAGE);
                return;
            }
            dispose();
            new UpdateAgentForm(agent);

        });
    }
}
