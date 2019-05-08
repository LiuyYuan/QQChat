package work;

import java.sql.*;

class DBConnector {
	
    private static final String ip = "localhost";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    //private static final String url = "jdbc:mysql://" + ip + ":3306/datasum?useUnicode=true&&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&characterEncoding=UTF-8&useSSL=false";
    private static final String url = "jdbc:mysql://" + ip + ":3306/datasum?serverTimezone=UTC&useSSL=false";
    private static final String username = "root";
    private static final String password = "liuy0212";
    private static Connection conn = null;
    private static Statement stmt;
    private static ResultSet rs;
    private String clas;
    private int temp1 = 0;
    static DBConnector dbConnector = new DBConnector();
    private static String name;

    private DBConnector(){
        try {
            Class.forName(driver);
            System.out.println("成功加载sql驱动");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    static Connection getConn() {
    	try {
    		conn = DriverManager.getConnection(url, username, password);
    		if(conn != null) {
    		System.out.print("成功连接到数据库！");
    		}
    	}catch(SQLException e){
    		 System.out.println("fail to connect the database!");
             e.printStackTrace();
    	}
    	
    	return conn;
    }
    static int suoyin(){
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            String sql = "select * from publish";
            rs = stmt.executeQuery(sql);
            int temp = 0;
            while (rs.next()) {
                if (temp < rs.getInt(1)) {
                    temp = rs.getInt(1);
                }
            }
            temp++;
            conn.close();
            stmt.close();
            rs.close();
            return temp;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    private boolean queryManager(String scnumber) {
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            String sql="select * from students";
            rs=stmt.executeQuery(sql);
            while(rs.next()) {
                if(scnumber.equals(rs.getString("scnumber")))
                    return true;
            }
            conn.close();
            stmt.close();
            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    void insertVote(String scnumber, String titl, String ta, String tb, String tc) {
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            if(!new DBConnector().queryManager(scnumber))
                return;
            String sql="select * from vote";
            rs=stmt.executeQuery(sql);
            int temp=1;
            while(rs.next()) {
                temp++;
            }
            String newtype1=new String(titl.getBytes(),"GBK");
            String newtype2=new String(ta.getBytes(),"GBK");
            String newtype3=new String(tb.getBytes(),"GBK");
            String newtype4=new String(tc.getBytes(),"GBK");
            sql="insert into vote (na,nb,nc,index1,title,ta,tb,tc) values (0,0,0,'"+temp+"','"+newtype1+"','"+newtype2+"','"+newtype3+"','"+newtype4+"')";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String selectTitle(int index)
    {
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            String sql="select * from vote where index1 ='"+index+"'";
            rs=stmt.executeQuery(sql);
            rs.next();
            String temp=rs.getString("title");
            conn.close();
            stmt.close();
            rs.close();
            return temp;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return " ";
    }
    String selectta(int index)
    {
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            String sql="select * from vote where index1='"+index+"'";
            rs=stmt.executeQuery(sql);
            rs.next();
            String temp=rs.getString("ta");
            conn.close();
            stmt.close();
            rs.close();
            return temp;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return " ";
    }
    String selecttb(int index)
    {
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            String sql="select * from vote where index1='"+index+"'";
            rs=stmt.executeQuery(sql);
            rs.next();
            return rs.getString("tb");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return " ";
    }
    String selecttc(int index)
    {
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            String sql="select * from vote where index1 ='"+index+"'";
            rs=stmt.executeQuery(sql);
            rs.next();
            return rs.getString("tc");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return " ";
    }

    int selectna(int index) {
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            String sql = "select * from vote where index1 ='" + index+"'";
            rs = stmt.executeQuery(sql);
            rs.next();
            return rs.getInt("na");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    int selectnb(int index) {
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            String sql = "select * from vote where index1 ='" + index+"'";
            rs = stmt.executeQuery(sql);
            rs.next();

            return rs.getInt("nb");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    int selectnc(int index) {
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            String sql = "select * from vote where index1 ='" + index+"'";
            rs = stmt.executeQuery(sql);
            rs.next();

            return rs.getInt("nc");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    void nextperson(int index, int flag) {
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            String sql = "select * from vote where index1 ='" + index +"'";
            rs = stmt.executeQuery(sql);
            rs.next();
            try {
            if (flag ==1){
                    sql = "update vote set na=" + (dbConnector.selectna(index) + 1 )+ " where index1 ='" + index+"'";
                    stmt.executeUpdate(sql);
                }
                if (flag ==2){
                    sql = "update vote set nb=" + (dbConnector.selectnb(index) + 1) + " where index1 ='" + index+"'";
                    stmt.executeUpdate(sql);
                }
                if (flag == 3){
                    sql = "update vote set nc=" + (dbConnector.selectnc(index) + 1) + " where index1 ='" + index+"'";
                    stmt.executeUpdate(sql);
                }
            }catch (Exception E ){
                E.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String selectbt(int index) {
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            String sql="select * from publish where suoyin ='"+index+"'";
            rs=stmt.executeQuery(sql);
            rs.next();
            return rs.getString("biaoti");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return " ";
    }
    String selectnr(int index) {
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            String sql="select * from publish where suoyin ='"+index+"'";
            rs=stmt.executeQuery(sql);
            rs.next();
            String temp=rs.getString("neirong");
            stmt.close();
            rs.close();
            return temp;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return " ";
    }
    int allPub() {
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            String sql="select * from publish";
            rs=stmt.executeQuery(sql);
            int temp=0;
            while(rs.next())
            {
                if(temp<rs.getInt(1))
                    temp=rs.getInt(1);
            }
            stmt.close();
            rs.close();
            return temp;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    int allVote(){
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            String sql="select * from vote";
            rs=stmt.executeQuery(sql);
            int temp=0;
            while(rs.next())
            {
                temp++;
            }
            return temp;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    static String getname(String scnumber){
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            String sql="select * from students where scnumber='"+scnumber+"'";
            rs=stmt.executeQuery(sql);
            rs.next();
            name=rs.getString("name");
            conn.close();
            stmt.close();
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return name;
    }
    String file2(int filenum){
        try {
            int aaa =0;
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            String sql="select * from file1";
            rs=stmt.executeQuery(sql);
            while (rs.next()){
                aaa++;
                if (aaa == filenum){
                    name=rs.getString("filename");
                    break;}
            }
            conn.close();
            stmt.close();
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return name;
    }
    int filenu(){
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            String sql="select * from file1 ";
            rs=stmt.executeQuery(sql);
            while (rs.next()){
                temp1++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return temp1;
    }
    void sendfile(String kname, String jname){
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            String sql="insert into file1 (filename,filepath) values ('"+kname+"','"+jname+"')";
            stmt.executeUpdate(sql);
            conn.close();
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    String[] getfilenames() {
        String []k = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            int i = 0;
            k = new String[filenu()];
            String sql = "SELECT * FROM file1";
            rs =stmt.executeQuery(sql);
            while (rs.next()) {
                k[i] = new String(rs.getString(1));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return k;
    }
    int getscnum(){
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            String sql="select * from students where zaixian = 1 ";
            rs=stmt.executeQuery(sql);
            while (rs.next()){
                temp1++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return temp1;
    }

    String[] getscname(){
        String l[] = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            int i = 0;
            l = new String[getscnum()];
            String sql = "SELECT * FROM students where zaixian = 1";
            rs =stmt.executeQuery(sql);
            while (rs.next()) {
                l[i] = rs.getString("name");
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
     }
     void rezaixian(String scnumber){
         try {
             conn = DriverManager.getConnection(url, username, password);
             stmt = conn.createStatement();
             String sql = "update students set zaixian = 0 where scnumber = "+scnumber;
             stmt.executeUpdate(sql);
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }
     void shangxian(String scnumber){
         try {
             conn = DriverManager.getConnection(url, username, password);
             stmt = conn.createStatement();
             String sql = "update students set zaixian = 1 where scnumber = "+scnumber;
             stmt.executeUpdate(sql);
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }
     String getscnum(String name){
         String scnumber = null;
         try {
             conn = DriverManager.getConnection(url, username, password);
             stmt = conn.createStatement();
             String sql = "SELECT * FROM students where name ='"+name+" '";
             rs =stmt.executeQuery(sql);
             rs.next();
             scnumber = rs.getString("scnumber");
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return scnumber;
     }
     int getsumna(int index){
        int sum1 = 0;
         try {
             conn = DriverManager.getConnection(url, username, password);
             stmt = conn.createStatement();
             String sql = "SELECT * FROM vote where index1 ='"+index+" '";
             rs =stmt.executeQuery(sql);
             rs.next();
             sum1 = rs.getInt("na");
             conn.close();
             stmt.close();
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return sum1;
     }
    int getsumnb(int index){
        int sum2 = 0;
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM vote where index1 ='"+index+" '";
            rs =stmt.executeQuery(sql);
            rs.next();
            sum2 = rs.getInt("nb");
            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum2;
    }
    int getsumnc(int index){
        int sum3 = 0;
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM vote where index1 ='"+index+" '";
            rs =stmt.executeQuery(sql);
            rs.next();
            sum3 = rs.getInt("nc");
            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sum3;
    }
    int getnumsc(){
        try {
            temp1 = 0;
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            String sql="select * from students ";
            rs=stmt.executeQuery(sql);
            while (rs.next()){
                temp1++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return temp1;
    }
}
