package JumpMyBallApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AgentEntity {
    int id;
    String agentType;
    String title;
    String email;
    String phone;
    String logo;
    String address;
    int priority;

    public AgentEntity(String agentType, String title, String email, String phone, String logo, String address, int priority) {
        this(-1,agentType,title,email,phone,logo,address,priority);
    }
}
