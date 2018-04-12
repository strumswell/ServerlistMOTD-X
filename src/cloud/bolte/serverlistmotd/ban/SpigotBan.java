package cloud.bolte.serverlistmotd.ban;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

import org.bukkit.Bukkit;
import org.bukkit.BanList.Type;

import cloud.bolte.serverlistmotd.SpigotConfig;

public class SpigotBan implements BanInterface {

	@Override
	public String banReason(String playerName) {
		return Bukkit.getBanList(Type.NAME).getBanEntry(playerName).getReason();
	}

	@Override
	public Long expires(String playerName) {
		return Long.getLong(Bukkit.getBanList(Type.NAME).getBanEntry(playerName).getExpiration().toString());
	}

	@Override
	public String banExpDateSec(String playerName) {
		return Bukkit.getBanList(Type.NAME).getBanEntry(playerName).getExpiration().getSeconds() + "";
	}

	@Override
	public String banExpDateMin(String playerName) {
		return Bukkit.getBanList(Type.NAME).getBanEntry(playerName).getExpiration().getMinutes() + "";
	}

	@Override
	public String banExpDateHour(String playerName) {
		return Bukkit.getBanList(Type.NAME).getBanEntry(playerName).getExpiration().getHours() + "";
	}

	@Override
	public String banExpDateDay(String playerName) {
		return Bukkit.getBanList(Type.NAME).getBanEntry(playerName).getExpiration().getDay() + "";
	}

	@Override
	public String banExpDateMonth(String playerName) {
		return Bukkit.getBanList(Type.NAME).getBanEntry(playerName).getExpiration().getMonth() + 1 + "";
	}

	@Override
	public String banExpDateYear(String playerName) {
		return Bukkit.getBanList(Type.NAME).getBanEntry(playerName).getExpiration().getYear() - 100 + "";

	}

	@Override
	public String date(String playerName) {
		return SpigotConfig.getFormatDate()
				.replaceAll("DD", Bukkit.getBanList(Type.NAME).getBanEntry(playerName).getExpiration().getDate() + "")
				.replaceAll("MM", Bukkit.getBanList(Type.NAME).getBanEntry(playerName).getExpiration().getMonth() + 1 + "")
				.replaceAll("YYYY", Bukkit.getBanList(Type.NAME).getBanEntry(playerName).getExpiration().getYear() + 1900 + "")
				.replaceAll("YY", Bukkit.getBanList(Type.NAME).getBanEntry(playerName).getExpiration().getYear() - 100 + "");
	}

	@Override
	public String time(String playerName) {
		return SpigotConfig.getFormatTime()
				.replaceAll("hh", Bukkit.getBanList(Type.NAME).getBanEntry(playerName).getExpiration().getHours() + "")
				.replaceAll("mm", Bukkit.getBanList(Type.NAME).getBanEntry(playerName).getExpiration().getMinutes() + "")
				.replaceAll("ss", Bukkit.getBanList(Type.NAME).getBanEntry(playerName).getExpiration().getSeconds() + "");
	}

	@Override
	public String banActor(String playerName) {
		return Bukkit.getBanList(Type.NAME).getBanEntry(playerName).getTarget();
	}

	@Override
	public String banCreated(String playerName) {
		return Bukkit.getBanList(Type.NAME).getBanEntry(playerName).getCreated() + "";
	}

	@Override
	public String banID(String playerName) {
		return Bukkit.getBanList(Type.NAME).getBanEntry(playerName).getClass().toString();
	}
}
