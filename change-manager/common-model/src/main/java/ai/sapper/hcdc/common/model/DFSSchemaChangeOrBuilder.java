// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: data_block.proto

package ai.sapper.hcdc.common.model;

public interface DFSSchemaChangeOrBuilder
    extends com.google.protobuf.MessageOrBuilder {

  // required .ai_sapper_hcdc_common_model.DFSTransaction transaction = 1;
  /**
   * <code>required .ai_sapper_hcdc_common_model.DFSTransaction transaction = 1;</code>
   */
  boolean hasTransaction();
  /**
   * <code>required .ai_sapper_hcdc_common_model.DFSTransaction transaction = 1;</code>
   */
  ai.sapper.hcdc.common.model.DFSTransaction getTransaction();
  /**
   * <code>required .ai_sapper_hcdc_common_model.DFSTransaction transaction = 1;</code>
   */
  ai.sapper.hcdc.common.model.DFSTransactionOrBuilder getTransactionOrBuilder();

  // required .ai_sapper_hcdc_common_model.DFSFile file = 2;
  /**
   * <code>required .ai_sapper_hcdc_common_model.DFSFile file = 2;</code>
   */
  boolean hasFile();
  /**
   * <code>required .ai_sapper_hcdc_common_model.DFSFile file = 2;</code>
   */
  ai.sapper.hcdc.common.model.DFSFile getFile();
  /**
   * <code>required .ai_sapper_hcdc_common_model.DFSFile file = 2;</code>
   */
  ai.sapper.hcdc.common.model.DFSFileOrBuilder getFileOrBuilder();

  // required string domain = 3;
  /**
   * <code>required string domain = 3;</code>
   */
  boolean hasDomain();
  /**
   * <code>required string domain = 3;</code>
   */
  java.lang.String getDomain();
  /**
   * <code>required string domain = 3;</code>
   */
  com.google.protobuf.ByteString
      getDomainBytes();

  // required string entityName = 4;
  /**
   * <code>required string entityName = 4;</code>
   */
  boolean hasEntityName();
  /**
   * <code>required string entityName = 4;</code>
   */
  java.lang.String getEntityName();
  /**
   * <code>required string entityName = 4;</code>
   */
  com.google.protobuf.ByteString
      getEntityNameBytes();

  // required string currentSchema = 5;
  /**
   * <code>required string currentSchema = 5;</code>
   */
  boolean hasCurrentSchema();
  /**
   * <code>required string currentSchema = 5;</code>
   */
  java.lang.String getCurrentSchema();
  /**
   * <code>required string currentSchema = 5;</code>
   */
  com.google.protobuf.ByteString
      getCurrentSchemaBytes();

  // required string updatedSchema = 6;
  /**
   * <code>required string updatedSchema = 6;</code>
   */
  boolean hasUpdatedSchema();
  /**
   * <code>required string updatedSchema = 6;</code>
   */
  java.lang.String getUpdatedSchema();
  /**
   * <code>required string updatedSchema = 6;</code>
   */
  com.google.protobuf.ByteString
      getUpdatedSchemaBytes();
}