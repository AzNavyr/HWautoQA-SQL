package ru.netology.data;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.DriverManager;

public class CleanInfo {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    @SneakyThrows
    public static void cleanDataBase() {
        val cleanCards = "DELETE FROM cards";
        val cleanAuthCodes = "DELETE FROM auth_codes";
        val cleanUser = "DELETE FROM users";
        val runner = new QueryRunner();
        DbUtils.loadDriver(JDBC_DRIVER);
        val conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        try {
            runner.execute(conn, cleanCards);
            runner.execute(conn, cleanAuthCodes);
            runner.execute(conn, cleanUser);
        } finally {
            DbUtils.close(conn);
        }
    }
}