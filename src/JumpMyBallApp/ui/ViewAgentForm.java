package JumpMyBallApp.ui;

import JumpMyBallApp.entity.AgentEntity;
import JumpMyBallApp.manager.AgentEntityManager;
import JumpMyBallApp.util.BaseForm;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class ViewAgentForm extends BaseForm {
    private JPanel mainPanel;
    private JTextArea textArea;
    private JButton backButton;

    public ViewAgentForm() {
        super(800,600);
        setContentPane(mainPanel);
        initButton();
        initTextArea();
        setVisible(true);
    }
    private void initButton(){
        backButton.addActionListener(e -> {
            dispose();
            new MainForm();
        });
    }
    private void initTextArea(){
        try {
            List<AgentEntity> list = AgentEntityManager.selectAll();
            String s ="";
            for(AgentEntity a: list){
                s+=a;
                s+="\n";
            }
            textArea.setText(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
