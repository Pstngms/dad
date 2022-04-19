package JumpMyBallApp.manager;

import JumpMyBallApp.App;
import JumpMyBallApp.entity.AgentEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgentEntityManager {
    public static void insert (AgentEntity agent) throws SQLException {
        try(Connection c = App.getConnection())
        {
            String sql = "INSERT INTO agent(AgentType,Title,Email,Phone,Logo,Address,Priority)VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,agent.getAgentType());
            ps.setString(2,agent.getTitle());
            ps.setString(3,agent.getEmail());
            ps.setString(4,agent.getPhone());
            ps.setString(5,agent.getLogo());
            ps.setString(6,agent.getAddress());
            ps.setInt(7,agent.getPriority());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if(resultSet.next()){
                agent.setId(resultSet.getInt(1));
                return;
            }
        }
    }
    public static List<AgentEntity> selectAll() throws SQLException {
        try(Connection c = App.getConnection())
        {
            String sql ="SELECT * FROM agent";
            Statement s = c.createStatement();
            ResultSet resultSet = s.executeQuery(sql);
            List<AgentEntity> list = new ArrayList<>();
            while (resultSet.next()){
                list.add(new AgentEntity(
                        resultSet.getInt("ID"),
                        resultSet.getString("AgentType"),
                        resultSet.getString("Title"),
                        resultSet.getString("Email"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Logo"),
                        resultSet.getString("Address"),
                        resultSet.getInt("Priority")
                ));
            }
            return list;
        }
    }
    public static void update (AgentEntity agent) throws SQLException {
        try(Connection c = App.getConnection())
        {
            String sql = "UPDATE agent SET AgentType=?,Title=?,Email=?,Phone=?,Logo=?,Address=?,Priority=? WHERE ID =?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1,agent.getAgentType());
            ps.setString(2,agent.getTitle());
            ps.setString(3,agent.getEmail());
            ps.setString(4,agent.getPhone());
            ps.setString(5,agent.getLogo());
            ps.setString(6,agent.getAddress());
            ps.setInt(7,agent.getPriority());
            ps.setInt(8,agent.getId());
            ps.executeUpdate();
        }
    }
    public static AgentEntity selectById(int id) throws SQLException {
        try(Connection c = App.getConnection())
        {
            String sql ="SELECT * FROM agent WHERE ID=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                return new AgentEntity(
                        resultSet.getInt("ID"),
                        resultSet.getString("AgentType"),
                        resultSet.getString("Title"),
                        resultSet.getString("Email"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Logo"),
                        resultSet.getString("Address"),
                        resultSet.getInt("Priority")
                );
            }
            return null;
        }
    }

    public static void delete(int id) throws SQLException {
        try(Connection c = App.getConnection())
        {
            String sql ="DELETE FROM agent WHERE ID=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        }
    }

}
