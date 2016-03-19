package com.example.hellodagger2;

import android.content.Context;
import mortar.MortarScope;
import mortar.bundler.BundleServiceRunner;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

public class MainPresenterTest {
  @Mock Context mockContext;
  @Mock MainView mockMainView;

  private Main.Presenter mainPresenter;

  @SuppressWarnings("WrongConstant") @Before public void setUp() {
    MockitoAnnotations.initMocks(this);

    String bundleServiceName = BundleServiceRunner.SERVICE_NAME;
    BundleServiceRunner bundleServiceRunner = new BundleServiceRunner();
    String mortarScope = MortarScope.class.getName();
    MortarScope mockMortarScope = MortarScope.buildRootScope()
        .withService(bundleServiceName, bundleServiceRunner)
        .build("MockRoot");

    given(mockContext.getSystemService(bundleServiceName)).willReturn(bundleServiceRunner);
    given(mockContext.getSystemService(mortarScope)).willReturn(mockMortarScope);
    given(mockMainView.getContext()).willReturn(mockContext);
  }

  @Test public void testShowOnLoad() {
    mainPresenter = new Main.Presenter();
    mainPresenter.takeView(mockMainView);

    verify(mockMainView).show(anyString());
  }
}
