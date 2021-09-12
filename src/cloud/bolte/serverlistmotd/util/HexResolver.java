package cloud.bolte.serverlistmotd.util;

import net.md_5.bungee.api.ChatColor;
import java.util.regex.Matcher;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.awt.Color;
import java.util.List;

/**
 * Credits go to harry0198 https://github.com/harry0198/HexiTextLib MIT License
 * Small edits done by Strumswell
 */
public class HexResolver {
    private static final Pattern GRADIENT_PATTERN = Pattern.compile("<(gradient|g)(:#([a-fA-F0-9]){6})+>");
    private static final Pattern HEX_PATTERN = Pattern.compile("<(#[a-fA-F0-9]{6})>");
    private static final Pattern STOPPING_PATTERN = Pattern.compile("<(gradient|g)(:#([a-fA-F0-9]){6})+>|(§[a-fA-F0-9])|<(#[a-fA-F0-9]{6})>");

    /**
     * Checks if hex colour codes are supported for the version of minecraft.
     * @return True if supports hex. False if not.
     */
    public static boolean serverSupportsHex() {
        try {
            ChatColor.of(Color.BLACK);
            return true;
        } catch (NoSuchMethodError ignore) {
            return false;
        }
    }

    /**
     * Parses string into hex colours where applicable using custom pattern matcher
     * @param text String to parse into hex
     * @return Parsed Hex where applicable otherwise returns inputted string
     */
    public static String parseHexString(String text, Pattern hexPattern) {
        if (serverSupportsHex()) {
            text = parseGradients(text);
            Matcher hexColorMatcher = hexPattern.matcher(text);
            while (hexColorMatcher.find()) {
                String hex = hexColorMatcher.group(1);
                ChatColor color = ChatColor.of(hex);

                String before = text.substring(0, hexColorMatcher.start());
                String after = text.substring(hexColorMatcher.end());
                text = before + color + after;
                hexColorMatcher = hexPattern.matcher(text);

            }
        }
        return org.bukkit.ChatColor.translateAlternateColorCodes('&',text);
    }

    /**
     * Parses string into hex colours where applicable using default pattern matcher
     * @param text String to parse into hex
     * @return Parsed Hex where applicable otherwise returns inputted string
     */
    public static String parseHexString(String text) {
        return parseHexString(text, HexResolver.HEX_PATTERN);
    }

    /**
     * Parses string into a gradient colour coded string
     * @param text String to parse
     * @return String with gradient applied
     */
    private static String parseGradients(String text) {
        List<String> formatCodes = Arrays.asList("§o", "§k", "§l", "§n", "§r", "§m");
        String parsed = text;

        Matcher matcher = GRADIENT_PATTERN.matcher(parsed);
        while (matcher.find()) {
            StringBuilder parsedGradient = new StringBuilder();

            String match = matcher.group();
            int tagLength = match.startsWith("<gr") ? 10 : 3;

            int indexOfClose = match.indexOf(">");
            String hexContent = match.substring(tagLength, indexOfClose);
            List<Color> hexSteps = Arrays.stream(hexContent.split(":")).map(Color::decode).collect(Collectors.toList());

            int stop = findGradientStop(parsed, matcher.end());
            String content = parsed.substring(matcher.end(), stop);

            String cleanedContent = content;
            for (String code: formatCodes) {
                cleanedContent = cleanedContent.replace(code, "");
            }

            Gradient gradient = new Gradient(hexSteps, cleanedContent.length());

            String tempFormat = "";
            for (char c : content.toCharArray()) {
                if (c != '§' && tempFormat != "§") {
                    // This is a normal char
                    parsedGradient
                            .append(ChatColor.of(gradient.next()).toString())
                            .append(tempFormat)
                            .append(c);
                } else if (c == '§') {
                    // A custom formatting is starting
                    tempFormat = "§";
                } else if (c != '§' && tempFormat.contains("§")) {
                    // Type of custom formatting defined now
                    tempFormat += c;
                }
            }

            String before = parsed.substring(0, matcher.start());
            String after = parsed.substring(stop);
            parsed = before + parsedGradient + after;
            matcher = GRADIENT_PATTERN.matcher(parsed);
        }
        return parsed;
    }

    /**
     * Returns the index of the colour that is about to change to the next
     *
     * @param content The content to search through
     * @param searchAfter The index at which to search after
     * @return the index of the color stop, or the end of the string index if none is found
     */
    private static int findGradientStop(String content, int searchAfter) {
        Matcher matcher = STOPPING_PATTERN.matcher(content);
        while (matcher.find()) {
            if (matcher.start() > searchAfter)
                return matcher.start();
        }
        return content.length() - 1;
    }
}
