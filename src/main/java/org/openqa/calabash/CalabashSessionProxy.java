package org.openqa.calabash;

import org.openqa.grid.common.RegistrationRequest;
import org.openqa.grid.internal.Registry;
import org.openqa.grid.internal.TestSession;
import org.openqa.grid.internal.listeners.TestSessionListener;
import org.openqa.grid.internal.utils.HtmlRenderer;
import org.openqa.grid.selenium.proxy.DefaultRemoteProxy;

public class CalabashSessionProxy extends DefaultRemoteProxy implements TestSessionListener {
  private HtmlRenderer renderer = new CalabashNodeHtmlRenderer(this);
  private int totalTests = 0;

  public CalabashSessionProxy(RegistrationRequest request, Registry registry) {
    super(request, registry);
  }

  @Override
  public void beforeSession(TestSession session) {
    super.beforeSession(session);
    synchronized (this) {
      totalTests++;
    }
  }

  public synchronized int getTotalTests() {
    return totalTests;
  }

  @Override
  public HtmlRenderer getHtmlRender() {
    return renderer;
  }
}
