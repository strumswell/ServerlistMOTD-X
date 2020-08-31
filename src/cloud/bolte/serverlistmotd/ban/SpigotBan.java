package cloud.bolte.serverlistmotd.ban;

import org.bukkit.Bukkit;
import org.bukkit.BanList.Type;

import cloud.bolte.serverlistmotd.SpigotConfig;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

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
				.replace("DD", Bukkit.getBanList(Type.NAME).getBanEntry(playerName).getExpiration().getDate() + "")
				.replace("MM", Bukkit.getBanList(Type.NAME).getBanEntry(playerName).getExpiration().getMonth() + 1 + "")
				.replace("YYYY", Bukkit.getBanList(Type.NAME).getBanEntry(playerName).getExpiration().getYear() + 1900 + "")
				.replace("YY", Bukkit.getBanList(Type.NAME).getBanEntry(playerName).getExpiration().getYear() - 100 + "");
	}

	@Override
	public String time(String playerName) {
		return SpigotConfig.getFormatTime()
				.replace("hh", Bukkit.getBanList(Type.NAME).getBanEntry(playerName).getExpiration().getHours() + "")
				.replace("mm", Bukkit.getBanList(Type.NAME).getBanEntry(playerName).getExpiration().getMinutes() + "")
				.replace("ss", Bukkit.getBanList(Type.NAME).getBanEntry(playerName).getExpiration().getSeconds() + "");
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
