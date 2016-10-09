package au.edu.unimelb.tcp.client;
import org.kohsuke.args4j.Option;


public class ComLineValues {
	
	// the cmdline will be 
	// -u username
	// -k password
	@Option(required=true, name = "-h", aliases="--host", usage="Server host address")
	private String host;
	
	@Option(required=false, name="-p", aliases="--port", usage="Server port number")
	private int port = 4444;
	
	// add an attribute -- userId
	@Option(required=true, name = "-r", aliases="--userId", usage="User Id")
	private String userId;
	
	// add an attribute -- password
	@Option(required=true, name = "-k", aliases="--password", usage="Client password")
	private String password;

	@Option(required=true, name = "-i", aliases="--identity", usage="Client identity")
	private String identity;

	
	@Option(required=false, name = "-d", aliases="--debug", usage="Debug mode")
	private boolean debug = false;
	
	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}
		
	public String getUserId() {
		return userId;
	}
		
	public String getPassword() {
		return password;
	}
	
	public String getIdeneity() {
		return identity;
	}
	
	public boolean isDebug() {
		return debug;
	}
}
