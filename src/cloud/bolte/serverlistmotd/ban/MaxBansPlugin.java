package cloud.bolte.serverlistmotd.ban;

import java.util.Date;

import org.maxgamer.maxbans.MaxBans;
import org.maxgamer.maxbans.banmanager.Ban;
import org.maxgamer.maxbans.banmanager.TempBan;

import cloud.bolte.serverlistmotd.SpigotConfig;
import cloud.bolte.serverlistmotd.variables.RandomPlayerVariable;
import cloud.bolte.serverlistmotd.variables.TimeVariable;
import cloud.bolte.serverlistmotd.variables.WeatherVariable;

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

public class MaxBansPlugin implements BanInterface {

	public Ban getBan(String playerName) {
		return MaxBans.instance.getBanManager().getBan(playerName);
	}

	@Override
	public String banReason(String playerName) {
		return getBan(playerName).getReason();
	}

	@Override
	public Long expires(String playerName) {
		if (getBan(playerName) instanceof TempBan) {
			TempBan temp = (TempBan) getBan(playerName);
			return temp.getExpires();
		} else {
			return 3555L;
		}
	}

	@Override
	public String banExpDateSec(String playerName) {
		if (this.expires(playerName) != null) {
			Date timestampconv = new Date(this.expires(playerName));
			return timestampconv.getSeconds() + "";
		} else {
			return "0";
		}
	}

	@Override
	public String banExpDateMin(String playerName) {
		if (this.expires(playerName) != null) {
			Date timestampconv = new Date(this.expires(playerName));
			return timestampconv.getMinutes() + "";
		} else {
			return "0";
		}
	}

	@Override
	public String banExpDateHour(String playerName) {
		if (this.expires(playerName) != null) {
			Date timestampconv = new Date(this.expires(playerName));
			return timestampconv.getHours() + "";
		} else {
			return "0";
		}
	}

	@Override
	public String banExpDateDay(String playerName) {
		if (this.expires(playerName) != null) {
			Date timestampconv = new Date(this.expires(playerName));
			return timestampconv.getDate() + "";
		} else {
			return "0";
		}
	}

	@Override
	public String banExpDateMonth(String playerName) {
		if (this.expires(playerName) != null) {
			Date timestampconv = new Date(this.expires(playerName));
			return timestampconv.getMonth() + 1 + "";
		} else {
			return "0";
		}
	}

	@Override
	public String banExpDateYear(String playerName) {
		if (this.expires(playerName) != null) {
			Date timestampconv = new Date(this.expires(playerName));
			return timestampconv.getYear() - 100 + "";
		} else {
			return "0";
		}
	}

	@Override
	public String date(String playerName) {
		if (this.expires(playerName) != null) {
			Date timestampconv = new Date(this.expires(playerName));
			return SpigotConfig.getFormatDate().replaceAll("DD", banExpDateDay(playerName))
					.replaceAll("MM", banExpDateMonth(playerName))
					.replaceAll("YYYY", timestampconv.getYear() + 1900 + "")
					.replaceAll("YY", banExpDateYear(playerName));
		} else {
			return "0";
		}
	}

	@Override
	public String time(String playerName) {
		if (this.expires(playerName) != null) {
			return SpigotConfig.getFormatTime().replaceAll("hh", banExpDateHour(playerName))
					.replaceAll("mm", banExpDateMin(playerName)).replaceAll("ss", banExpDateSec(playerName));
		} else {
			return "0";
		}
	}

	@Override
	public String banActor(String playerName) {
		// TODO Auto-generated method stub
		return playerName;
	}

	@Override
	public String banCreated(String playerName) {
		// TODO Auto-generated method stub
		return getBan(playerName).getCreated() + "";
	}

	@Override
	public String banID(String playerName) {
		// TODO Auto-generated method stub
		return getBan(playerName).getId();
	}

	public String getBanMOTD(String playerName, String playerIP) {

		if (expires(playerName) == 3555L) {
			return SpigotConfig.getBanForeverMotd().replaceAll("%player%", playerName)
					.replaceAll("%reason%", banReason(playerName)).replaceAll("%time%", TimeVariable.getTime())
					.replaceAll("%weather%", WeatherVariable.getWeather()).replaceAll("%line%", "\n")
					.replaceAll("%randomplayer%", RandomPlayerVariable.getRandomPlayer());
		} else {
			return SpigotConfig.getBanTempMotd().replaceAll("%player%", playerName)
					.replaceAll("%reason%", banReason(playerName)).replaceAll("%expdate%", date(playerName))
					.replaceAll("%exptime%", time(playerName)).replaceAll("%expsec%", banExpDateSec(playerName))
					.replaceAll("%expmin%", banExpDateMin(playerName))
					.replaceAll("%exphour%", banExpDateHour(playerName))
					.replaceAll("%expday%", banExpDateDay(playerName))
					.replaceAll("%expmonth%", banExpDateMonth(playerName))
					.replaceAll("%expyear%", banExpDateYear(playerName)).replaceAll("%actor%", banActor(playerName))
					.replaceAll("%creationdate%", banCreated(playerName)).replaceAll("%banid%", banID(playerName))
					.replaceAll("%time%", TimeVariable.getTime()).replaceAll("%weather%", WeatherVariable.getWeather())
					.replaceAll("%line%", "\n").replaceAll("%randomplayer%", RandomPlayerVariable.getRandomPlayer())
					.replaceAll("%player%", playerName);
		}
	}
}
