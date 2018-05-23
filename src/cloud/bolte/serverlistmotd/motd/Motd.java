package cloud.bolte.serverlistmotd.motd;

import java.net.InetAddress;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

public interface Motd {
	
	/**
	 * Get unformatted motd string
	 * @param ip IP address
	 * @return unformatted motd 
	 */
	public String getMOTD(InetAddress ip);
	
	/**
	 * Format motd string with color and vars
	 * @param motd MOTD that needs to be formatted
	 * @return formatted MOTD
	 */
	public String formatMotd(String motd, InetAddress ip);
}
