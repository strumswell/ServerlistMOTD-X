package cloud.bolte.serverlistmotd.ban;

public interface BanInterface {

	public String banReason(String playerName);

	/**
	 * @param uuid
	 *            Player's UUID
	 * @return Expiration date
	 */
	public Long expires(String playerName);

	/**
	 * @param uuid
	 *            Player's UUID
	 * @return Seconds of expiration date
	 */
	public String banExpDateSec(String playerName);

	/**
	 * @param uuid
	 *            Player's UUID
	 * @return Minutes of expiration date
	 */
	public String banExpDateMin(String playerName);

	/**
	 * @param uuid
	 *            Player's UUID
	 * @return Hours of expiration date
	 */
	public String banExpDateHour(String playerName);

	/**
	 * @param uuid
	 *            Player's UUID
	 * @return Day of expiration date
	 */
	public String banExpDateDay(String playerName);

	/**
	 * @param uuid
	 *            Player's UUID
	 * @return Month of expiration date
	 */
	public String banExpDateMonth(String playerName);

	/**
	 * @param uuid
	 *            Player's UUID
	 * @return Year of expiration date
	 */
	public String banExpDateYear(String playerName);

	/**
	 * @param uuid
	 *            Player's UUID
	 * @return Returns the date
	 */
	public String date(String playerName);

	/**
	 * @param uuid
	 *            Player's UUID
	 * @return Returns the time
	 */
	public String time(String playerName);

	/**
	 * @param uuid
	 *            Player's UUID
	 * @return Returns the actor
	 */
	public String banActor(String playerName);

	/**
	 * @param uuid
	 *            Player's UUID
	 * @return Returns when the ban was created
	 */
	public String banCreated(String playerName);

	/**
	 * @param uuid
	 *            Player's UUID
	 * @return This method returns a unique ID belonging to a ban
	 */
	public String banID(String playerName);
}
