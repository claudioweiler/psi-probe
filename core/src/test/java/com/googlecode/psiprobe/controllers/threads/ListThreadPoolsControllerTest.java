package com.googlecode.psiprobe.controllers.threads;

import org.junit.Test;

import com.codebox.bean.JavaBeanTester;

/**
 * The Class ListThreadPoolsControllerTest.
 */
public class ListThreadPoolsControllerTest {

  /**
   * Javabean tester.
   */
  @Test
  public void javabeanTester() {
    JavaBeanTester.builder(ListThreadPoolsController.class).skip("applicationContext", "supportedMethods").test();
  }

}
