package com.comall.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class JdbcTemplateTest {

	private static JdbcTemplate jdbcTemplate;
	
	@BeforeClass
	public static void setUpClass() {
	String url = "jdbc:oracle:thin:@10.6.9.93:1521:ftestdb";
	String username = "cofcodb";
	String password = "fcofco";
	DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);
	dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	jdbcTemplate = new JdbcTemplate(dataSource);
	}
	/**
	 * spring jdbctemplate �������ݿ����
	 */
	@Test
	public void test() {
	//1.����SQL
	String sql = "select * from t_sellamount t where t.id =219116";
	jdbcTemplate.query(sql, new RowCallbackHandler() {
	@Override
	public void processRow(ResultSet rs) throws SQLException {
	//2.��������
	String value = rs.getString("id");
	System.out.println("Column TABLENAME:" + value);
	}
	});
	}

	/**
	 * һ���ǳ���׼������Oracle���ݿ��ʾ������
	 */
	@Test
	public void test01()
	{
	    Connection con = null;// ����һ�����ݿ�����
	    PreparedStatement pre = null;// ����Ԥ����������һ�㶼�������������Statement
	    ResultSet result = null;// ����һ�����������
	    try
	    {
	        Class.forName("oracle.jdbc.driver.OracleDriver");// ����Oracle��������
	        System.out.println("��ʼ�����������ݿ⣡");
//	        String url = "jdbc:oracle:" + "thin:@127.0.0.1:1521:XE";// 127.0.0.1�Ǳ�����ַ��XE�Ǿ����Oracle��Ĭ�����ݿ���
	        String url = "jdbc:oracle:thin:@10.6.9.93:1521:ftestdb";
	        String user = "cofcodb";// �û���,ϵͳĬ�ϵ��˻���
	        String password = "fcofco";// �㰲װʱѡ���õ�����
	        con = DriverManager.getConnection(url, user, password);// ��ȡ����
	        System.out.println("���ӳɹ���");
	        String sql = "select * from t_sellamount t where t.id =?";// Ԥ������䣬�������������
	        pre = con.prepareStatement(sql);// ʵ����Ԥ�������
	        pre.setInt(1, 219116);// ���ò�����ǰ���1��ʾ�����������������Ǳ�������������
	        result = pre.executeQuery();// ִ�в�ѯ��ע�������в���Ҫ�ټӲ���
	        while (result.next())
	            // ���������Ϊ��ʱ
	            System.out.println("ѧ��:" + result.getInt("id"));
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	    finally
	    {
	        try
	        {
	            // ��һ������ļ�������رգ���Ϊ���رյĻ���Ӱ�����ܡ�����ռ����Դ
	            // ע��رյ�˳�����ʹ�õ����ȹر�
	            if (result != null)
	                result.close();
	            if (pre != null)
	                pre.close();
	            if (con != null)
	                con.close();
	            System.out.println("���ݿ������ѹرգ�");
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }
	}
	
	
}
