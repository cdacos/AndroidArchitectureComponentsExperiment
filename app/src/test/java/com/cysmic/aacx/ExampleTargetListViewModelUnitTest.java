package com.cysmic.aacx;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.cysmic.aacx.model.TargetListViewModel;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import static org.junit.Assert.assertNotNull;

public class ExampleTargetListViewModelUnitTest {
  @Rule
  public TestRule rule = new InstantTaskExecutorRule();

  @Test
  public void initTest() {
    TargetListViewModel viewModel = new TargetListViewModel(new FakeTargetRepository());
    assertNotNull(viewModel.getData().getValue());
  }
}
