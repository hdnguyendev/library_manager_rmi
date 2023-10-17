import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private static String DB_URL = "jdbc:mysql://"+ Config.IP_SERVER +":" + Config.PORT_DB + "/" + Config.NAME_DB;
    private static String USER_NAME = Config.USER_DB;
    private static String PASSWORD = Config.PASSWORD_DB;

    public Connection getConnect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD);
            System.out.println(">> Connect successfully!");
        } catch (SQLException e) {
            System.out.println(">> Connect failure!");
            e.printStackTrace();
        }
        return connection;
    }
}
/*
*
*
* Connection name: Wi-Fi
State: Connected
Type: Wireless LAN
DNS Suffix:
IPv4 address: 192.168.1.3
IPv4 subnet mask: 255.255.255.0
IPv6 link-local address:
	fe80::b650:87d5:e877:e4c6%9
Default Gateway:
	192.168.1.1
DHCP servers:
	192.168.1.1
DNS servers:
	123.23.23.23
	123.26.26.26
WINS servers:
	192.168.1.1

Adapter name: Intel(R) Wi-Fi 6 AX201 160MHz
Physical address (MAC): 9843FA30A903
Speed: 216 Mbps*/