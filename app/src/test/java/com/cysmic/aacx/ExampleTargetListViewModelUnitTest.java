package com.cysmic.aacx;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.cysmic.aacx.model.TargetListViewModel;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ExampleTargetListViewModelUnitTest {
  @Rule
  public TestRule rule = new InstantTaskExecutorRule();

  @Test
  public void initTest() {
    TargetListViewModel viewModel = new TargetListViewModel(new FakeTargetRepository());
    assertNotNull(viewModel);
    assertTrue(viewModel.loadData());
    assertNotNull(viewModel.getData());
    assertNotNull(viewModel.getData().getValue());
  }
}
