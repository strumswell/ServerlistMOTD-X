package cloud.bolte.serverlistmotd.ban;

import java.util.UUID;

import org.bukkit.Bukkit;

import cloud.bolte.serverlistmotd.SpigotConfig;
import me.confuser.banmanager.common.api.BmAPI;
//import me.confuser.banmanager.BmAPI; old version of BanManager

/*
 * ServerlistMOTD (c) by Strumswell, Philipp Bolte
 * ServerlistMOTD is licensed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * You should have received a copy of the license along with this work.
 * If not, see <http://creativecommons.org/licenses/by-nc-sa/3.0/>.
 */

public class BanManager implements BanInterface {

	public UUID getUUID(String playerName) {
		return Bukkit.getOfflinePlayer(playerName).getUniqueId();
	}

	@Override
	public String banReason(String playerName) {
		return BmAPI.getCurrentBan(playerName).getReason();
	}

	@Override
	public String banExpDateSec(String playerName) {
		if (this.expires(playerName) != null) {
			java.util.Date timestampconv = new java.util.Date((long) this.expires(playerName) * 1000);
			return timestampconv.getSeconds() + "";
		} else {
			return "0";
		}
	}

	@Override
	public Long expires(String playerName) {
		return BmAPI.getCurrentBan(playerName).getExpires();
	}

	@Override
	public String banExpDateMin(String playerName) {
		if (this.expires(playerName) != null) {
			java.util.Date timestampconv = new java.util.Date((long) this.expires(playerName) * 1000);
			return timestampconv.getMinutes() + "";
		} else {
			return "0";
		}
	}

	@Override
	public String banExpDateHour(String playerName) {
		if (this.expires(playerName) != null) {
			java.util.Date timestampconv = new java.util.Date((long) this.expires(playerName) * 1000);
			return timestampconv.getHours() + "";
		} else {
			return "0";
		}
	}

	@Override
	public String banExpDateDay(String playerName) {
		if (this.expires(playerName) != null) {
			java.util.Date timestampconv = new java.util.Date((long) this.expires(playerName) * 1000);
			return timestampconv.getDate() + "";
		} else {
			return "0";
		}
	}

	@Override
	public String banExpDateMonth(String playerName) {
		if (this.expires(playerName) != null) {
			java.util.Date timestampconv = new java.util.Date((long) this.expires(playerName) * 1000);
			return timestampconv.getMonth() + 1 + "";
		} else {
			return "0";
		}
	}

	@Override
	public String banExpDateYear(String playerName) {
		if (this.expires(playerName) != null) {
			java.util.Date timestampconv = new java.util.Date((long) this.expires(playerName) * 1000);
			return timestampconv.getYear() - 100 + "";
		} else {
			return "0";
		}
	}

	@Override
	public String date(String playerName) {
		if (this.expires(playerName) != null) {
			java.util.Date timestampconv = new java.util.Date((long) this.expires(playerName) * 1000);
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
		return BmAPI.getCurrentBan(playerName).getActor().getName();
	}

	@Override
	public String banCreated(String playerName) {
		return String.valueOf(BmAPI.getCurrentBan(playerName).getCreated());
	}

	@Override
	public String banID(String playerName) {
		return String.valueOf(BmAPI.getCurrentBan(playerName).getId());
	}
}
