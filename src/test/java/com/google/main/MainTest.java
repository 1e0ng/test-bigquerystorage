package com.google.main;

import static org.junit.Assert.*;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.DescriptorValidationException;

public class MainTest {

  @org.junit.Test
  public void testGetDescriptor() throws DescriptorValidationException {
    Descriptor descriptor = Main.getDescriptor();
    assert(descriptor.getFullName().equals("root"));
  }
}