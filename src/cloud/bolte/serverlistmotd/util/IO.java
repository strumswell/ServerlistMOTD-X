package cloud.bolte.serverlistmotd.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import cloud.bolte.serverlistmotd.Main;

public class IO {
	
	/**
	 * Feed inmemory HashMap with known IPs and UUIDs to identify the player in the serverlist.
	 * 
	 * @param f Flatfile 
	 * @param m HashMap containing IPs and UUIDs
	 */
	public static void loadFlatfileIntoHashMap(File f, Map<InetAddress, UUID> m) {
		long start = System.currentTimeMillis();
		if (f.exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(f));
				String l;
				while ((l = br.readLine()) != null) {
					String[] t = l.split("=", 2);
					if (t.length != 2)
						continue;
					m.put(InetAddress.getByName(t[0].replaceAll("/", "")), UUID.fromString(t[1]));
				}
				br.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			System.out.println("[ServerlistMOTD] Loaded userdata from IP_UUID.dat("+f.length()+"bytes) into memory in "
					+(System.currentTimeMillis()-start)+"ms.");
		}
	}

	/**
	 * Save inmemory HashMap into a Flatfile to store IPs and UUIDs longterm
	 * 
	 * @param f Flatfile containing IPs and UUIDs
	 * @param m Empty Hashmap
	 */
	public static void saveHashMapIntoFlatfile(File f, Map<InetAddress, UUID> m) {
		long start = System.currentTimeMillis();
		try {
			FileOutputStream fos = new FileOutputStream(f);
			fos.flush();
			fos.close();
			PrintWriter pw = new PrintWriter(f);
			for (Map.Entry<InetAddress, UUID> entry : m.entrySet())
				pw.println(String.valueOf(entry.getKey()).replaceAll("/", "") + "=" + entry.getValue());
			pw.flush();
			pw.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		System.out.println("[ServerlistMOTD] Saved userdata into IP_UUID.dat in "+(System.currentTimeMillis()-start)+"ms.");
	}

	/**
	 * Invertes a HashMap
	 * 
	 * @param map
	 * @return
	 */
	private static <K, V> Map<V, K> invert(Map<K, V> map) {
		Map<V, K> result = new HashMap<V, K>();
		for (Entry<K, V> entry : map.entrySet()) {
			result.put(entry.getValue(), entry.getKey());
		}
		return result;
	}
	
	
	/**
	 * Invertes HashMap two times to remove possible duplicates
	 */
	public static void removeUnusedEntries() {
		Main.IP_UUID = invert(invert(Main.IP_UUID));
	}

	/**
	 * Used for looking up a key in a HashMap
	 * 
	 * @param hm HashMap
	 * @param value Value in HashMap
	 * @return
	 */
	public static Object getKeyFromValue(Map<?, ?> hm, Object value) {
		for (Object o : hm.keySet()) {
			if (hm.get(o).equals(value)) {
				return o;
			}
		}
		return null;
	}
}
