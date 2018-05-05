package cloud.bolte.serverlistmotd.ban;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

public interface BanInterface {
	
	/**
	 * @param playerName playerName
	 * @return Ban reason
	 */
	public String banReason(String playerName);

	/**
	 * @param playerName
	 *             playerName
	 * @return Expiration date
	 */
	public Long expires(String playerName);

	/**
	 * @param playerName
	 *             playerName
	 * @return Seconds of expiration date
	 */
	public String banExpDateSec(String playerName);

	/**
	 * @param playerName
	 *             playerName
	 * @return Minutes of expiration date
	 */
	public String banExpDateMin(String playerName);

	/**
	 * @param playerName
	 *             playerName
	 * @return Hours of expiration date
	 */
	public String banExpDateHour(String playerName);

	/**
	 * @param playerName
	 *             playerName
	 * @return Day of expiration date
	 */
	public String banExpDateDay(String playerName);

	/**
	 * @param playerName
	 *             playerName
	 * @return Month of expiration date
	 */
	public String banExpDateMonth(String playerName);

	/**
	 * @param playerName
	 *             playerName
	 * @return Year of expiration date
	 */
	public String banExpDateYear(String playerName);

	/**
	 * @param playerName
	 *             playerName
	 * @return Returns the date
	 */
	public String date(String playerName);

	/**
	 * @param playerName
	 *             playerName
	 * @return Returns the time
	 */
	public String time(String playerName);

	/**
	 * @param playerName
	 *             playerName
	 * @return Returns the actor
	 */
	public String banActor(String playerName);

	/**
	 * @param playerName
	 *             playerName
	 * @return Returns when the ban was created
	 */
	public String banCreated(String playerName);

	/**
	 * @param playerName
	 *             playerName
	 * @return This method returns a unique ID belonging to a ban
	 */
	public String banID(String playerName);
}
