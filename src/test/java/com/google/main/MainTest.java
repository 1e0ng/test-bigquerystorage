package com.google.main;

import static org.junit.Assert.*;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.DescriptorValidationException;
import org.junit.Test;

public class MainTest {

  @Test
  public void testGetDescriptor() throws DescriptorValidationException {
    Descriptor descriptor = Main.getDescriptor();
    assert(descriptor.getFullName().equals("root"));
  }

  @Test
  public void testGetDescriptorForExtension() throws DescriptorValidationException {
     Descriptor descriptor = Main.getDescriptorForExtension();
    assert(descriptor.getFullName().equals("root"));
  }
}