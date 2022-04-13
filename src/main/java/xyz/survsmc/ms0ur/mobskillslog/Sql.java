package xyz.survsmc.ms0ur.mobskillslog;
import redempt.redlib.sql.SQLHelper;

import java.sql.Connection;

import static xyz.survsmc.ms0ur.mobskillslog.MobsKillsLog.getJavaPlugin;

public class Sql {
    private static class Data{
        static Connection connection = SQLHelper.openSQLite(getJavaPlugin().getDataFolder().toPath().resolve("database.db"));
        static SQLHelper sql = new SQLHelper(connection);
    }

    public static void sqliteCreate(){
        Data.sql.execute("CREATE TABLE IF NOT EXISTS kills (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, time TEXT, killer TEXT, vistim TEXT, coordsx DOUBLE, coordsy DOUBLE, coordsz DOUBLE);");
    }

    public static void sqliteNewKill(String time, String killer, String vistim, double coordsx, double coordsy, double coordsz){
        Data.sql.execute("INSERT INTO kills ( time, killer, vistim, coordsx, coordsy, coordsz ) VALUES ( '"+time+"', '"+killer+"', '"+vistim+"', '"+coordsx+"', '"+coordsy+"', '"+coordsz+"'");
    }


    public static SQLHelper.Results sqliteGetInfo(int id){
        return Data.sql.queryResults("SELECT * FROM kills WHERE id = "+id+";");
    }
    public void sqliteGetInfoV(String vistim, int id){
        //soon
    }
    public void sqliteGetInfoK(String killer, int id){
        //soon
    }
}
