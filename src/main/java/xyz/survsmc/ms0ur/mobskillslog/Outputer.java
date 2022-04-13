package xyz.survsmc.ms0ur.mobskillslog;

import redempt.redlib.sql.SQLHelper;

public class Outputer {
    private static SQLHelper.Results result;
    private void getResult(int id){
        result = xyz.survsmc.ms0ur.mobskillslog.Sql.sqliteGetInfo(id);
    }
    public static String getStringResult(int id){
        result = xyz.survsmc.ms0ur.mobskillslog.Sql.sqliteGetInfo(id);
        return (result.getString(1));
    }

}

