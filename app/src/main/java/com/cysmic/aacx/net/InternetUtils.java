package com.cysmic.aacx.net;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

// See https://stackoverflow.com/questions/3905358/how-to-ping-external-ip-from-java-android
public class InternetUtils {
  private static final String TAG = InternetUtils.class.getName();
  private static final Pattern RE_PING_TIME = Pattern.compile("icmp_seq=1 .* time=([0-9.]*)", Pattern.CASE_INSENSITIVE);

  @Inject
  public InternetUtils() { }

  // It's not always ICMP, and otherwise tries a TCP connection
  // Seems hopeless on SDK < 26 - at least on the emulator
  /*
  public float connect(String domain) throws IOException {
    long start = System.currentTimeMillis();
    //noinspection ResultOfMethodCallIgnored
    InetAddress.getByName(domain).isReachable(5000);
    long end = System.currentTimeMillis();
    return (float)(end - start);
  }
  */

  // Hopefully the device has ping installed and accessible...
  public float ping(String domain) throws Exception {
    float result = -1;
    Process ping = Runtime.getRuntime().exec(String.format("ping -c 1 %s", domain));
    try (BufferedReader stdInput = new BufferedReader(new InputStreamReader(ping.getInputStream()))) {
      try (BufferedReader stdError = new BufferedReader(new InputStreamReader(ping.getErrorStream()))) {
        String si;
        while ((si = stdInput.readLine()) != null) {
//          Log.d(TAG, "I: " + si);
          Matcher m = RE_PING_TIME.matcher(si);
          if (m.find()) {
            String time = m.group(1);
            Log.d(TAG, "I: time: " + time + " | " + si);
            result = Float.parseFloat(time);
            break;
          }
        }
        String se;
        if ((se = stdError.readLine()) != null) {
//          Log.d(TAG, "E: " + se);
          throw new RuntimeException(se);
        }
        ping.waitFor();
        ping.destroy();
      }
    }
    return result;
  }
}
