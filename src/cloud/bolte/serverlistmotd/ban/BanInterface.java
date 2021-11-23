package cloud.bolte.serverlistmotd.ban;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

// TODO: Migrate to UUID
public interface BanInterface {
	
	/**
	 * @param playerName Players username
	 * @return Ban reason
	 */
	String banReason(String playerName);

	/**
	 * @param playerName
	 * @return Expiration date
	 */
	Long expires(String playerName);

	/**
	 * @param playerName
	 * @return Seconds of expiration date
	 */
	String banExpDateSec(String playerName);

	/**
	 * @param playerName
	 * @return Minutes of expiration date
	 */
	String banExpDateMin(String playerName);

	/**
	 * @param playerName	 
	 * @return Hours of expiration date
	 */
	String banExpDateHour(String playerName);

	/**
	 * @param playerName	 
	 * @return Day of expiration date
	 */
	String banExpDateDay(String playerName);

	/**
	 * @param playerName	 
	 * @return Month of expiration date
	 */
	String banExpDateMonth(String playerName);

	/**
	 * @param playerName	 
	 * @return Year of expiration date
	 */
	String banExpDateYear(String playerName);

	/**
	 * @param playerName	 
	 * @return Returns the date
	 */
	String date(String playerName);

	/**
	 * @param playerName	 
	 * @return Returns the time
	 */
	String time(String playerName);

	/**
	 * @param playerName	 
	 * @return Returns the actor
	 */
	String banActor(String playerName);

	/**
	 * @param playerName 
	 * @return Returns when the ban was created
	 */
	String banCreated(String playerName);

	/**
	 * @param playerName
	 * @return This method returns a unique ID belonging to a ban
	 */
	String banID(String playerName);
}
