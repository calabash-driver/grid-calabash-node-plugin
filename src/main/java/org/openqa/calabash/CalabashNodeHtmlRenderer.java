package org.openqa.calabash;

import org.openqa.grid.common.RegistrationRequest;
import org.openqa.grid.internal.TestSession;
import org.openqa.grid.internal.TestSlot;
import org.openqa.grid.internal.utils.HtmlRenderer;
import org.openqa.grid.web.utils.BrowserNameUtils;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Calabash node renderer used in the Selenium Grid hub.
 * 
 * @author ddary
 * 
 */
public class CalabashNodeHtmlRenderer implements HtmlRenderer {
  public static final String CALABASH_BROWSER_NAME = "calabash-android";
  public static final String DEVICE_NAME = "deviceName";
  public static final String LOCALE = "locale";
  public static final String DEVICE_ID = "deviceId";
  public static final String SDK_VERSION = "sdkVersion";
  public static final String AUT = "aut";

  private CalabashSessionProxy proxy;

  CalabashNodeHtmlRenderer(CalabashSessionProxy proxy) {
    this.proxy = proxy;
  }

  public String renderSummary() {
    StringBuilder builder = new StringBuilder();
    builder.append("<fieldset>");

    builder
        .append("<legend>")
        .append(
            "<img width='30' src='/grid/resources/images/mac.png' style='vertical-align:middle;' title='"
                + proxy.getClass().getSimpleName() + "'/>")
        .append(proxy.getClass().getSimpleName()).append("</legend>");

    builder.append("<div id='browsers'>");
    for (TestSlot slot : proxy.getTestSlots()) {
      builder.append("<a href='#' ");
      builder.append(" title='").append(slot.getCapabilities()).append("' ");
      builder.append(" >");

      String icon = null;

      if (CALABASH_BROWSER_NAME.equals(slot.getCapabilities().get(RegistrationRequest.BROWSER))) {
        icon = "android";
      } else {
        icon =
            BrowserNameUtils.consoleIconName(new DesiredCapabilities(slot.getCapabilities()),
                proxy.getRegistry());
      }

      builder.append("<img src='/grid/resources/images/" + icon + ".png' height='20px' ");

      builder.append(slot.getCapabilities().get(DEVICE_NAME));
      builder.append("</a>");
    }

    builder.append("</div>");
    builder.append("</fieldset>");
    return builder.toString();
  }
}
