package xyz.survsmc.ms0ur.mobskillslog;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;


public class bd {

        public static Connection conn = null;
        public static Statement stat = null;

        //Создает подключение к базе данных
        public static boolean createConnection() {
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:" + MobsKillsLog.getJavaPlugin().getDataFolder()+ File.separator + "plugin.db");
                stat = conn.createStatement();
                stat.setQueryTimeout(2);

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            try {
                stat.executeUpdate("CREATE TABLE IF NOT EXISTS kills ("
                        + " id         INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + " killer     TEXT NOT NULL,"
                        + " vistim     TEXT NOT NULL,"
                        + " x     DOUBLE NOT NULL,"
                        + " y     DOUBLE NOT NULL,"
                        + " z     DOUBLE NOT NULL,"
                        + " time       TEXT NOT NULL"
                        + ")");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }

        public static boolean closeConnection() {
            try {

                if(conn.isClosed() || stat.isClosed()) return false;

                stat.close();
                conn.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

        }

        //Добавляет в базу данных новый килл
        public static boolean newKill(String killer, String vistim, double x, double y, double z) {
            String currentTime = new Date().toString();

            try {
                PreparedStatement insertStmt = conn.prepareStatement(
                        "INSERT INTO kills(killer, vistim, x, y, z, time) VALUES(?, ?, ?, ?, ?, ?)");

                insertStmt.setString(1, killer);
                insertStmt.setString(2, vistim);
                insertStmt.setDouble(3, x);
                insertStmt.setDouble(4, y);
                insertStmt.setDouble(5, z);
                insertStmt.setString(6, currentTime);
                insertStmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        public static ArrayList<String> getLastTenKills() {
            try {
                return killsList("SELECT * FROM kills ORDER BY ID DESC LIMIT 10;");
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

        public static ArrayList<String> search(double x, double y, double z) throws SQLException {
           try{
               return killsList("SELECT * FROM kills WHERE x=\""+x+"\" AND y=\""+y+"\" AND z=\""+z+"\" ORDER BY ID DESC LIMIT 5;");
           } catch (SQLException e) {
               e.printStackTrace();
           }
            return null;
        }

        public static ArrayList<String> search(double x, double y, double z, int radius){
            try {
                if (radius>10) return null;
                //int loges = (radius+radius)*(radius+radius)*(radius+radius);
                ArrayList<String> lst = new ArrayList<String>();
                for(int xi = -radius;xi<=radius;xi++){
                    for(int yi = -radius;yi<=radius;yi++){
                        for(int zi = -radius;zi<=radius;zi++){
                            lst.add(killsList("SELECT * FROM kills WHERE x=\""+x+xi+"\" AND y=\""+y+yi+"\" AND z=\""+z+zi+"\" ORDER BY ID DESC LIMIT 1;").get(0));
                        }
                    }
                }
                return lst;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static ArrayList<String> search(int mode, double x, double y, double z, int radius, String nick){//Поиск по координатам и нику игрока или сущности
            try {
                if (radius>10) return null;
                if (mode == 1) {//killer
                    ArrayList<String> lst = new ArrayList<String>();
                    for(int xi = -radius;xi<=radius;xi++){
                        for(int yi = -radius;yi<=radius;yi++){
                            for(int zi = -radius;zi<=radius;zi++){
                                lst.add(killsList("SELECT * FROM kills WHERE x=\""+x+xi+"\" AND y=\""+y+yi+"\" AND z=\""+z+zi+"\" AND killer=\""+nick+"\" ORDER BY ID DESC LIMIT 1;").get(0));
                            }
                        }
                    }
                    return lst;
                }
                if (mode == 2) {//vistim
                    ArrayList<String> lst = new ArrayList<String>();
                    for(int xi = -radius;xi<=radius;xi++){
                        for(int yi = -radius;yi<=radius;yi++){
                            for(int zi = -radius;zi<=radius;zi++){
                                lst.add(killsList("SELECT * FROM kills WHERE x=\""+x+xi+"\" AND y=\""+y+yi+"\" AND z=\""+z+zi+"\" AND vistim=\""+nick+"\" ORDER BY ID DESC LIMIT 1;").get(0));
                            }
                        }
                    }
                    return lst;
                }
                 //Search vistim

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static ArrayList<String> search(int mode, String nick) {
            try {
                if (mode == 1) return killsList("SELECT * FROM kills WHERE killer=\""+nick+"\" ORDER BY ID DESC LIMIT 5;"); //Search killer
                if (mode == 2) return killsList("SELECT * FROM kills WHERE vistim=\""+nick+"\" ORDER BY ID DESC LIMIT 5;"); //Search vistim

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        private static ArrayList<String> killsList(String sql) throws SQLException {
            ResultSet rs = stat.executeQuery(sql);

            ArrayList<String> result = new ArrayList<String>();

            while(rs.next()) {
                int id = rs.getInt("id");
                String killer = rs.getString("killer");
                String vistim = rs.getString("vistim");
                double x = rs.getDouble("x");
                double y = rs.getDouble("y");
                double z = rs.getDouble("z");
                String time = rs.getString("time");
                result.add(String.format("ID - %s: %s killed %s at %s at X:%s Y:%s Z:%s", id, killer, vistim, time, x, y, z));
            }

            return result;
        }

}
