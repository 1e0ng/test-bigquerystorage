package com.google.main;

import com.google.cloud.bigquery.storage.v1.BQTableSchemaToProtoDescriptor;
import com.google.cloud.bigquery.storage.v1.TableFieldSchema;
import com.google.cloud.bigquery.storage.v1.TableSchema;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.DescriptorValidationException;

public class Main {
  public static void main(String[] args) throws DescriptorValidationException {

    final Descriptor descriptor = getDescriptor();

    System.out.println(descriptor.getFullName());
    System.out.print("OK");
  }

  public static Descriptor getDescriptor() throws DescriptorValidationException {
    final TableFieldSchema test_int =
        TableFieldSchema.newBuilder()
            .setType(TableFieldSchema.Type.INT64)
            .setMode(TableFieldSchema.Mode.NULLABLE)
            .setName("test_int")
            .build();
    final TableFieldSchema reuse_lvl2 =
        TableFieldSchema.newBuilder()
            .setType(TableFieldSchema.Type.STRUCT)
            .setMode(TableFieldSchema.Mode.NULLABLE)
            .setName("reuse_lvl2")
            .addFields(0, test_int)
            .build();
    final TableFieldSchema reuse_lvl1 =
        TableFieldSchema.newBuilder()
            .setType(TableFieldSchema.Type.STRUCT)
            .setMode(TableFieldSchema.Mode.NULLABLE)
            .setName("reuse_lvl1")
            .addFields(0, test_int)
            .addFields(0, reuse_lvl2)
            .build();
    final TableFieldSchema reuse_lvl1_1 =
        TableFieldSchema.newBuilder()
            .setType(TableFieldSchema.Type.STRUCT)
            .setMode(TableFieldSchema.Mode.NULLABLE)
            .setName("reuse_lvl1_1")
            .addFields(0, test_int)
            .addFields(0, reuse_lvl2)
            .build();
    final TableFieldSchema reuse_lvl1_2 =
        TableFieldSchema.newBuilder()
            .setType(TableFieldSchema.Type.STRUCT)
            .setMode(TableFieldSchema.Mode.NULLABLE)
            .setName("reuse_lvl1_2")
            .addFields(0, test_int)
            .addFields(0, reuse_lvl2)
            .build();
    final TableSchema tableSchema =
        TableSchema.newBuilder()
            .addFields(0, reuse_lvl1)
            .addFields(1, reuse_lvl1_1)
            .addFields(2, reuse_lvl1_2)
            .build();
    return BQTableSchemaToProtoDescriptor.convertBQTableSchemaToProtoDescriptor(tableSchema);
  }

  public static Descriptor getDescriptorForExtension() throws DescriptorValidationException {
    final TableFieldSchema stringField =
        TableFieldSchema.newBuilder()
            .setType(TableFieldSchema.Type.STRING)
            .setMode(TableFieldSchema.Mode.NULLABLE)
            .setName("str-列")
            .build();
    final TableFieldSchema intField =
        TableFieldSchema.newBuilder()
            .setType(TableFieldSchema.Type.INT64)
            .setMode(TableFieldSchema.Mode.NULLABLE)
            .setName("int-列")
            .build();
    final TableFieldSchema nestedField =
        TableFieldSchema.newBuilder()
            .setType(TableFieldSchema.Type.STRUCT)
            .setMode(TableFieldSchema.Mode.NULLABLE)
            .setName("nested-列")
            .addFields(0, intField)
            .build();
    final TableSchema tableSchema =
        TableSchema.newBuilder().addFields(0, stringField).addFields(1, nestedField).build();
    final Descriptor descriptor =
        BQTableSchemaToProtoDescriptor.convertBQTableSchemaToProtoDescriptor(tableSchema);
    return descriptor;
  }
}
